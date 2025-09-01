package nomanssave;

class bI implements bK {
   // $FF: synthetic field
   final bE ey;

   bI(bE var1) {
      this.ey = var1;
   }

   public String getID() {
      return "TWordsLearnt";
   }

   public boolean isSpecial() {
      return true;
   }

   public String ab() {
      return Integer.toString(bE.a(this.ey).b(eU.kt));
   }

   public void l(String var1) {
      throw new RuntimeException("Cannot set words learnt");
   }
}
