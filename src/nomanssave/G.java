package nomanssave;

import javax.swing.JTextField;

public abstract class G extends JTextField {
   public G() {
      this.addFocusListener(new H(this));
   }

   public void N() {
      this.setText(this.g(this.getText()));
   }

   public void f(String var1) {
      this.setText(this.g(var1));
   }

   protected abstract String g(String var1);
}
