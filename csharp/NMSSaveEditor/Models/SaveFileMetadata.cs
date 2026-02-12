namespace NMSSaveEditor.Models;

/// <summary>
/// Metadata about a save file - version, compression type, play time, etc.
/// Port of Java class fS.
/// </summary>
public class SaveFileMetadata
{
    public int Version { get; set; }
    public int PlayTime { get; set; }
    public bool IsCompressed { get; set; }
    public string? Name { get; set; }
    public string? Description { get; set; }

    public SaveFileMetadata Clone() => new()
    {
        Version = Version,
        PlayTime = PlayTime,
        IsCompressed = IsCompressed,
        Name = Name,
        Description = Description
    };

    public string PlayTimeFormatted
    {
        get
        {
            int hours = PlayTime / 3600;
            int minutes = (PlayTime % 3600) / 60;
            int seconds = PlayTime % 60;
            return hours > 0 ? $"{hours}:{minutes:D2}:{seconds:D2}" : $"{minutes}:{seconds:D2}";
        }
    }
}
