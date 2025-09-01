package nomanssave;

class cS implements gD {
   final String filename;
   final int index;
   // $FF: synthetic field
   final cN gt;

   cS(cN var1, String var2) {
      this.gt = var1;
      this.filename = var2;
      this.index = cN.a(var1).size() + 1;
   }

   public String K() {
      return this.filename;
   }

   public String toString() {
      return "Unknown " + this.index;
   }
}
