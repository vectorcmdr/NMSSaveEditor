package net.jpountz.lz4;

public class LZ4Exception extends RuntimeException {
   private static final long serialVersionUID = 1L;

   public LZ4Exception(String msg, Throwable t) {
      super(msg, t);
   }

   public LZ4Exception(String msg) {
      super(msg);
   }

   public LZ4Exception() {
   }
}
