package nomanssave;

class w implements Runnable {
  w(boolean paramBoolean) {}
  
  public void run() {
    try {
      Application.g(new Application(this.ba, null));
      Application.h(Application.H()).setVisible(true);
    } catch (Exception exception) {
      exception.printStackTrace();
      System.exit(1);
    } 
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\w.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */