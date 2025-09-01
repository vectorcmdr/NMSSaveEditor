package nomanssave;

class bF implements bK {
   // $FF: synthetic field
   final bE ey;

   bF(bE var1) {
      this.ey = var1;
   }

   public String getID() {
      return "ExtremeSurvival";
   }

   public boolean isSpecial() {
      return false;
   }

   public String ab() {
      return Double.toString(bE.a(this.ey).dT());
   }

   public void l(String var1) {
      double var2 = Double.parseDouble(var1);
      bE.a(this.ey).g(var2);
   }
}
