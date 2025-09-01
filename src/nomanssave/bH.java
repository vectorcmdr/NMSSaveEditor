package nomanssave;

class bH implements bK {
   // $FF: synthetic field
   final bE ey;

   bH(bE var1) {
      this.ey = var1;
   }

   public String getID() {
      return "TWordsLearnt";
   }

   public boolean isSpecial() {
      return true;
   }

   public String ab() {
      return Integer.toString(bE.a(this.ey).b(eU.ks));
   }

   public void l(String var1) {
      throw new RuntimeException("Cannot set words learnt");
   }
}
