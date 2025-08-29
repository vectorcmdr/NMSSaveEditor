package nomanssave;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.UIManager;

class dL extends DefaultListCellRenderer {
  dL(dJ paramdJ) {}
  
  public Component getListCellRendererComponent(JList<?> paramJList, Object paramObject, int paramInt, boolean paramBoolean1, boolean paramBoolean2) {
    JLabel jLabel = (JLabel)super.getListCellRendererComponent(paramJList, paramObject, paramInt, paramBoolean1, paramBoolean2);
    if (paramObject == null) {
      jLabel.setText("");
    } else {
      eM eM = (eM)paramObject;
      if (eM.aW()) {
        if (paramBoolean1) {
          jLabel.setBackground(UIManager.getColor("Settlement.positivePerkHighlight"));
        } else {
          jLabel.setForeground(UIManager.getColor("Settlement.positivePerkColor"));
        } 
      } else if (paramBoolean1) {
        jLabel.setBackground(UIManager.getColor("Settlement.negativePerkHighlight"));
      } else {
        jLabel.setForeground(UIManager.getColor("Settlement.negativePerkColor"));
      } 
    } 
    return jLabel;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\dL.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */