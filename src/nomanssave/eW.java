package nomanssave;

public class eW {
   final eV kE = new eV();

   public eW h(Object var1) {
      if (var1 != null && !fh.a(var1.getClass())) {
         throw new RuntimeException("Unsupported type: " + var1.getClass().getSimpleName());
      } else {
         this.kE.e(var1);
         return this;
      }
   }

   public eV bC() {
      return this.kE;
   }
}
