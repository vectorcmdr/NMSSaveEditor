package nomanssave;

class u implements fR {
   // $FF: synthetic field
   final Application aZ;

   u(Application var1) {
      this.aZ = var1;
   }

   public void a(fq var1) {
      if (Application.a(this.aZ) && Application.b(this.aZ) == var1) {
         Application.a(this.aZ, true);
      }
   }

   public void a(fq var1, int var2, String var3) {
      if (Application.a(this.aZ) && Application.b(this.aZ) == var1) {
         Application.b(this.aZ, true);
         if (Application.c(this.aZ) >= 0 && Application.d(this.aZ)[Application.c(this.aZ)].getIndex() == var2) {
            Application.c(this.aZ, true);
            if (Application.e(this.aZ) >= 0 && Application.f(this.aZ)[Application.e(this.aZ)].K().equals(var3)) {
               Application.d(this.aZ, true);
            }
         }

      }
   }
}
