package nomanssave;

class w implements Runnable {
   // $FF: synthetic field
   private final boolean ba;

   w(boolean var1) {
      this.ba = var1;
   }

   public void run() {
      try {
         Application.g(new Application(this.ba, (Application)null));
         Application.h(Application.H()).setVisible(true);
      } catch (Exception var2) {
         var2.printStackTrace();
         System.exit(1);
      }

   }
}
