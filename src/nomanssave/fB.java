package nomanssave;

class fB extends fH implements fr {
   // $FF: synthetic field
   final fA ma;

   fB(fA var1) {
      super(var1, "savedata00.hg", true);
      this.ma = var1;
   }

   public eY M() {
      return fA.b(this.readBytes(), eG.jW);
   }

   public void k(eY var1) {
      this.a("ps4_accountdata", (fn)null, (String)null, (String)null);
      this.writeBytes(fA.l(var1));
   }
}
