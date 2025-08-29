package nomanssave;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

class cP extends DefaultListCellRenderer {
  cP(cN paramcN) {}
  
  public Component getListCellRendererComponent(JList<?> paramJList, Object paramObject, int paramInt, boolean paramBoolean1, boolean paramBoolean2) {
    Component component = super.getListCellRendererComponent(paramJList, paramObject, paramInt, paramBoolean1, paramBoolean2);
    if (paramObject == null && component instanceof JLabel) {
      JLabel jLabel = (JLabel)component;
      jLabel.setText(" ");
    } 
    if (component instanceof JLabel) {
      boolean bool = false;
      Enum[] arrayOfEnum;
      int i = (arrayOfEnum = cN.e(this.gt)).length;
      for (byte b = 0; b < i; b++) {
        Enum enum_ = arrayOfEnum[b];
        if (enum_ == paramObject) {
          bool = true;
          break;
        } 
      } 
      JLabel jLabel = (JLabel)component;
      if (!bool)
        if (paramBoolean1) {
          jLabel.setBackground(cN.ag());
        } else {
          jLabel.setForeground(cN.aB());
        }  
    } 
    return component;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\cP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */