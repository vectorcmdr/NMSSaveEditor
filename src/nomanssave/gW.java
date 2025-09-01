package nomanssave;

public class gW {
   public static void i(byte[] var0) {
      StringBuilder var1 = new StringBuilder();
      var1.append("Data: " + var0.length);
      var1.append(System.lineSeparator());
      var1.append("  ");
      StringBuilder var2 = new StringBuilder();

      for(int var3 = 0; var3 < var0.length; ++var3) {
         var1.append(Integer.toString((var0[var3] & 240) >> 4, 16));
         var1.append(Integer.toString(var0[var3] & 15, 16));
         if (var0[var3] >= 32 && var0[var3] < 127) {
            var2.append((char)(var0[var3] & 255));
         } else {
            var2.append('?');
         }

         if (var3 % 16 == 15) {
            var1.append("  ");
            var1.append(var2);
            var1.append(System.lineSeparator());
            var1.append("  ");
            var2 = new StringBuilder();
         }
      }

      if (var2.length() > 0) {
         while(var2.length() < 16) {
            var1.append("  ");
            var2.append(" ");
         }

         var1.append("  ");
         var1.append(var2);
      }

      System.out.println(var1.toString());
   }

   public static void a(long[] var0) {
      byte[] var1 = new byte[var0.length * 4];

      for(int var2 = 0; var2 < var0.length; ++var2) {
         var1[var2 * 4 + 3] = (byte)((int)(var0[var2] >> 24 & 255L));
         var1[var2 * 4 + 2] = (byte)((int)(var0[var2] >> 16 & 255L));
         var1[var2 * 4 + 1] = (byte)((int)(var0[var2] >> 8 & 255L));
         var1[var2 * 4] = (byte)((int)(var0[var2] & 255L));
      }

      i(var1);
   }
}
