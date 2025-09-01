package nomanssave;

public class fa {
   final eY kM = new eY();

   public fa d(String var1, Object var2) {
      if (var1 == null) {
         throw new NullPointerException();
      } else if (!eY.bF().matcher(var1).matches()) {
         throw new RuntimeException("Invalid name: " + var1);
      } else if (var2 != null && !fh.a(var2.getClass())) {
         throw new RuntimeException("Unsupported type: " + var2.getClass().getSimpleName());
      } else {
         this.kM.a(var1, var2);
         return this;
      }
   }

   public eY bH() {
      return this.kM;
   }
}
