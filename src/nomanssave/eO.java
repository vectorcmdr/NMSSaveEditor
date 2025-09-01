package nomanssave;

class eO {
   final String id;

   eO(String var1) {
      this.id = var1;
   }

   public boolean equals(Object var1) {
      eM var2 = (eM)var1;
      return var2.jY ? this.id.startsWith(var2.id + "#") : this.id.equals(var2.id);
   }
}
