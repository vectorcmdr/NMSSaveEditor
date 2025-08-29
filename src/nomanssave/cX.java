package nomanssave;

import java.awt.Rectangle;
import javax.swing.text.BadLocationException;

class cX implements Runnable {
  cX(cW paramcW) {}
  
  public void run() {
    try {
      int i = cW.a(this.gL).getDocument().getLength();
      Rectangle rectangle = cW.a(this.gL).modelToView(i);
      if (rectangle != null && rectangle.y != cW.b(this.gL)) {
        cW.c(this.gL);
        this.gL.repaint();
        cW.a(this.gL, rectangle.y);
      } 
    } catch (BadLocationException badLocationException) {}
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\cX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */