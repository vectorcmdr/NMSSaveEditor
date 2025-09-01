package net.jpountz.lz4;

enum LZ4Constants {
   static final int DEFAULT_COMPRESSION_LEVEL = 9;
   static final int MAX_COMPRESSION_LEVEL = 17;
   static final int MEMORY_USAGE = 14;
   static final int NOT_COMPRESSIBLE_DETECTION_LEVEL = 6;
   static final int MIN_MATCH = 4;
   static final int HASH_LOG = 12;
   static final int HASH_TABLE_SIZE = 4096;
   static final int SKIP_STRENGTH = Math.max(6, 2);
   static final int COPY_LENGTH = 8;
   static final int LAST_LITERALS = 5;
   static final int MF_LIMIT = 12;
   static final int MIN_LENGTH = 13;
   static final int MAX_DISTANCE = 65536;
   static final int ML_BITS = 4;
   static final int ML_MASK = 15;
   static final int RUN_BITS = 4;
   static final int RUN_MASK = 15;
   static final int LZ4_64K_LIMIT = 65547;
   static final int HASH_LOG_64K = 13;
   static final int HASH_TABLE_SIZE_64K = 8192;
   static final int HASH_LOG_HC = 15;
   static final int HASH_TABLE_SIZE_HC = 32768;
   static final int OPTIMAL_ML = 18;
}
