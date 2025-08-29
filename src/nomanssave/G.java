package nomanssave;

import javax.swing.JTextField;

public abstract class G extends JTextField {
  public G() {
    addFocusListener(new H(this));
  }
  
  public void N() {
    setText(g(getText()));
  }
  
  public void f(String paramString) {
    setText(g(paramString));
  }
  
  protected abstract String g(String paramString);
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\G.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */