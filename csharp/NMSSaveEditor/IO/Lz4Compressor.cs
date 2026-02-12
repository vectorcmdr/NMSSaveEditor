namespace NMSSaveEditor.IO;

/// <summary>
/// Native C# implementation of LZ4 fast compression algorithm.
/// Replaces the Java net.jpountz.lz4 library with a pure managed implementation.
/// </summary>
public static class Lz4Compressor
{
    private const int MinMatch = 4;
    private const int HashLog = 16;
    private const int HashTableSize = 1 << HashLog;
    private const int MaxInputSize = 0x7E000000;
    private const int LastLiterals = 5;
    private const int MFLimit = 12;
    private const int MLBits = 4;
    private const int MLMask = (1 << MLBits) - 1;
    private const int RunBits = 4;
    private const int RunMask = (1 << RunBits) - 1;

    public static int MaxCompressedLength(int inputLength) =>
        inputLength + (inputLength / 255) + 16;

    public static int Compress(byte[] source, int sourceOffset, int sourceLength,
                                byte[] dest, int destOffset, int maxDestLength)
    {
        if (sourceLength == 0) return 0;
        if (sourceLength > MaxInputSize)
            throw new ArgumentException("Input too large");

        int[] hashTable = new int[HashTableSize];
        Array.Fill(hashTable, -1);

        int srcIdx = sourceOffset;
        int srcEnd = sourceOffset + sourceLength;
        int srcLimit = srcEnd - LastLiterals;
        int matchLimit = srcEnd - MFLimit;
        int dstIdx = destOffset;
        int dstEnd = destOffset + maxDestLength;
        int anchor = srcIdx;

        if (sourceLength >= MFLimit)
        {
            srcIdx++;
            int forwardHash = Hash(source, srcIdx);

            while (true)
            {
                int forwardIdx = srcIdx;
                int step = 1;
                int searchLimit = srcIdx + (1 << 6);

                // Find a match
                int matchRef;
                do
                {
                    int hash = forwardHash;
                    srcIdx = forwardIdx;
                    forwardIdx += step++;
                    if (forwardIdx > matchLimit) goto _lastLiterals;

                    matchRef = hashTable[hash];
                    hashTable[hash] = srcIdx - sourceOffset;
                    forwardHash = Hash(source, forwardIdx);
                }
                while (matchRef < 0 ||
                       srcIdx - (matchRef + sourceOffset) > 65535 ||
                       !Equal4(source, matchRef + sourceOffset, srcIdx));

                matchRef += sourceOffset;

                // Write literals
                int litLen = srcIdx - anchor;
                int tokenPos = dstIdx++;
                if (dstIdx + litLen + (litLen >> 8) + 2 > dstEnd) throw new InvalidOperationException("Output buffer too small");

                if (litLen >= RunMask)
                {
                    dest[tokenPos] = (byte)(RunMask << MLBits);
                    int rem = litLen - RunMask;
                    while (rem >= 255) { dest[dstIdx++] = 255; rem -= 255; }
                    dest[dstIdx++] = (byte)rem;
                }
                else
                {
                    dest[tokenPos] = (byte)(litLen << MLBits);
                }

                Buffer.BlockCopy(source, anchor, dest, dstIdx, litLen);
                dstIdx += litLen;

                // Encode match
                while (true)
                {
                    int offset = srcIdx - matchRef;
                    dest[dstIdx++] = (byte)(offset & 0xFF);
                    dest[dstIdx++] = (byte)((offset >> 8) & 0xFF);

                    srcIdx += MinMatch;
                    matchRef += MinMatch;
                    int matchLen = 0;
                    while (srcIdx < srcLimit && source[srcIdx] == source[matchRef])
                    {
                        srcIdx++;
                        matchRef++;
                        matchLen++;
                    }

                    if (matchLen >= MLMask)
                    {
                        dest[tokenPos] |= (byte)MLMask;
                        int rem = matchLen - MLMask;
                        while (rem >= 255) { dest[dstIdx++] = 255; rem -= 255; }
                        dest[dstIdx++] = (byte)rem;
                    }
                    else
                    {
                        dest[tokenPos] |= (byte)matchLen;
                    }

                    anchor = srcIdx;
                    if (srcIdx >= matchLimit) goto _lastLiterals;

                    hashTable[Hash(source, srcIdx - 2)] = (srcIdx - 2) - sourceOffset;
                    int h = Hash(source, srcIdx);
                    matchRef = hashTable[h] + sourceOffset;
                    hashTable[h] = srcIdx - sourceOffset;

                    if (matchRef < sourceOffset || srcIdx - matchRef > 65535 || !Equal4(source, matchRef, srcIdx))
                        break;

                    tokenPos = dstIdx++;
                    dest[tokenPos] = 0;
                }

                forwardHash = Hash(source, ++srcIdx);
            }
        }

        _lastLiterals:
        int lastLitLen = srcEnd - anchor;
        if (dstIdx + lastLitLen + 1 + (lastLitLen / 255) > dstEnd)
            throw new InvalidOperationException("Output buffer too small");

        if (lastLitLen >= RunMask)
        {
            dest[dstIdx++] = (byte)(RunMask << MLBits);
            int rem = lastLitLen - RunMask;
            while (rem >= 255) { dest[dstIdx++] = 255; rem -= 255; }
            dest[dstIdx++] = (byte)rem;
        }
        else
        {
            dest[dstIdx++] = (byte)(lastLitLen << MLBits);
        }

        Buffer.BlockCopy(source, anchor, dest, dstIdx, lastLitLen);
        dstIdx += lastLitLen;

        return dstIdx - destOffset;
    }

    private static int Hash(byte[] data, int index)
    {
        int v = data[index] | (data[index + 1] << 8) | (data[index + 2] << 16) | (data[index + 3] << 24);
        return ((v * -1640531535) >> (32 - HashLog)) & (HashTableSize - 1);
    }

    private static bool Equal4(byte[] data, int a, int b)
    {
        return data[a] == data[b] && data[a + 1] == data[b + 1] &&
               data[a + 2] == data[b + 2] && data[a + 3] == data[b + 3];
    }
}
