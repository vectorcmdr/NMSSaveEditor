using NMSE.Models;
using System.IO.Compression;
using System.Text;

namespace NMSE.IO;

/// <summary>
/// Manages loading and saving NMS save files.
/// Handles Steam, GOG, Xbox Game Pass, and PS4 save locations.
/// </summary>
public class SaveFileManager
{
    /// <summary>
    /// ISO-8859-1 (Latin-1) encoding which maps bytes 0x00-0xFF to Unicode code points 1:1.
    /// Used instead of UTF-8 when reading save files so that binary data embedded in JSON
    /// string values (e.g. TechBox item IDs) is preserved as individual characters rather
    /// than being corrupted by invalid-UTF-8 replacement.  The JSON parser then detects
    /// characters ≥ 0x80 inside string tokens and produces BinaryData objects.
    /// </summary>
    private static readonly Encoding Latin1 = Encoding.GetEncoding(28591);
    public enum Platform { Steam, XboxGamePass, PS4, GOG, Unknown }

    public class SaveSlot
    {
        public int Index { get; set; }
        public string? FilePath { get; set; }
        public string? MetadataPath { get; set; }
        public bool IsEmpty { get; set; } = true;
        public DateTime LastModified { get; set; }
        public Platform Platform { get; set; }
    }

    private static readonly byte[] Lz4Magic = { 0xE5, 0xA1, 0xED, 0xFE };

    public static Platform DetectPlatform(string directory)
    {
        if (File.Exists(Path.Combine(directory, "containers.index")))
            return Platform.XboxGamePass;
        if (Directory.GetFiles(directory, "save*.hg").Length > 0 ||
            File.Exists(Path.Combine(directory, "accountdata.hg")))
            return Platform.Steam;
        if (Directory.GetFiles(directory, "savedata*.hg").Length > 0)
            return Platform.PS4;
        return Platform.Unknown;
    }

    public static string? FindDefaultSaveDirectory()
    {
        // Steam default location
        string? steamPath = Path.Combine(
            Environment.GetFolderPath(Environment.SpecialFolder.ApplicationData),
            "HelloGames", "NMS");

        if (Directory.Exists(steamPath))
        {
            var dirs = Directory.GetDirectories(steamPath);
            if (dirs.Length > 0)
                return dirs[0]; // Return first profile directory
        }

        // Xbox Game Pass location
        string localAppData = Environment.GetFolderPath(Environment.SpecialFolder.LocalApplicationData);
        string xboxPath = Path.Combine(localAppData, "Packages");
        if (Directory.Exists(xboxPath))
        {
            var nmsDirs = Directory.GetDirectories(xboxPath, "HelloGames*");
            if (nmsDirs.Length > 0)
                return nmsDirs[0];
        }

        return null;
    }

    public static void BackupSaveDirectory(string saveDirectory)
    {
        string exeDir = AppDomain.CurrentDomain.BaseDirectory;
        string backupRoot = Path.Combine(exeDir, "Save Backups");
        Directory.CreateDirectory(backupRoot);

        string dirName = new DirectoryInfo(saveDirectory).Name;
        string backupPattern = $"{dirName}_*.zip";
        var existingBackups = Directory.GetFiles(backupRoot, backupPattern)
            .OrderBy(f => File.GetCreationTimeUtc(f))
            .ToList();

        // If there are 10 or more backups, delete the oldest one
        if (existingBackups.Count >= 10)
        {
            File.Delete(existingBackups[0]);
            existingBackups.RemoveAt(0);
        }

        string timestamp = DateTime.Now.ToString("yyyyMMdd_HHmmss");
        string backupName = $"{dirName}_{timestamp}.zip";
        string backupPath = Path.Combine(backupRoot, backupName);

        // Avoid zipping if already exists for this second
        if (!File.Exists(backupPath))
        {
            ZipFile.CreateFromDirectory(saveDirectory, backupPath, CompressionLevel.Optimal, false);
        }
    }

    /// <summary>
    /// Load a save file and return the JSON data.
    /// Handles both compressed (LZ4) and uncompressed save files.
    /// </summary>
    public static JsonObject LoadSaveFile(string filePath)
    {
        byte[] fileData = File.ReadAllBytes(filePath);

        string json;
        if (IsLz4Compressed(fileData))
        {
            json = DecompressLz4Save(fileData);
        }
        else
        {
            json = Latin1.GetString(fileData);
        }

        var result = JsonObject.Parse(json);

        // Register the PlayerStateData context transform.
        // NMS saves nest PlayerStateData under BaseContext or ExpeditionContext
        // depending on the ActiveContext field
        if (result.Get("PlayerStateData") == null)
        {
            result.RegisterTransform("PlayerStateData", obj =>
            {
                if (obj is not JsonObject root) return "PlayerStateData";
                var activeContext = root.Get("ActiveContext") as string;
                if (activeContext == "Main" && root.GetValue("BaseContext.PlayerStateData") != null)
                    return "BaseContext.PlayerStateData";
                if (activeContext == "Season" && root.GetValue("ExpeditionContext.PlayerStateData") != null)
                    return "ExpeditionContext.PlayerStateData";
                return "PlayerStateData";
            });
        }

        return result;
    }

    /// <summary>
    /// Save JSON data back to a file with LZ4 compression.
    /// </summary>
    public static void SaveToFile(string filePath, JsonObject data, bool compress = true)
    {
        string json = data.ToFormattedString();
        byte[] jsonBytes = Latin1.GetBytes(json);

        // Create backup
        if (File.Exists(filePath))
        {
            string backupPath = filePath + ".backup";
            File.Copy(filePath, backupPath, true);
        }

        if (compress)
        {
            using var fs = File.Create(filePath);
            using var compressor = new Lz4CompressorStream(fs);
            compressor.Write(jsonBytes, 0, jsonBytes.Length);
        }
        else
        {
            File.WriteAllBytes(filePath, jsonBytes);
        }
    }

    private static bool IsLz4Compressed(byte[] data)
    {
        if (data.Length < 16) return false;
        return data[0] == Lz4Magic[0] && data[1] == Lz4Magic[1] &&
               data[2] == Lz4Magic[2] && data[3] == Lz4Magic[3];
    }

    private static string DecompressLz4Save(byte[] data)
    {
        using var ms = new MemoryStream();
        int offset = 0;

        while (offset < data.Length)
        {
            // Read 16-byte header
            if (offset + 16 > data.Length) break;

            // Verify magic
            if (data[offset] != Lz4Magic[0] || data[offset + 1] != Lz4Magic[1] ||
                data[offset + 2] != Lz4Magic[2] || data[offset + 3] != Lz4Magic[3])
                break;

            int compressedLen = data[offset + 4] | (data[offset + 5] << 8) |
                               (data[offset + 6] << 16) | (data[offset + 7] << 24);
            int uncompressedLen = data[offset + 8] | (data[offset + 9] << 8) |
                                 (data[offset + 10] << 16) | (data[offset + 11] << 24);
            offset += 16;

            if (compressedLen < 0 || uncompressedLen < 0)
                throw new IOException("Corrupt save file: negative length values");
            if (compressedLen > 256 * 1024 * 1024 || uncompressedLen > 256 * 1024 * 1024)
                throw new IOException("Corrupt save file: block size exceeds 256MB limit");
            if (offset + compressedLen > data.Length)
                throw new IOException("Corrupt save file: compressed data exceeds file length");

            // Decompress this block
            using var blockStream = new MemoryStream(data, offset, compressedLen);
            using var lz4Stream = new Lz4DecompressorStream(blockStream, uncompressedLen);
            byte[] decompressed = new byte[uncompressedLen];
            int read = 0;
            while (read < uncompressedLen)
            {
                int n = lz4Stream.Read(decompressed, read, uncompressedLen - read);
                if (n <= 0) break;
                read += n;
            }
            ms.Write(decompressed, 0, read);

            offset += compressedLen;
        }

        return Latin1.GetString(ms.ToArray());
    }

    /// <summary>
    /// Format play time as MM:SS or H:MM:SS string.
    /// </summary>
    public static string FormatPlayTime(long seconds)
    {
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long secs = seconds % 60;
        return hours > 0 ? $"{hours}:{minutes:D2}:{secs:D2}" : $"{minutes}:{secs:D2}";
    }
}
