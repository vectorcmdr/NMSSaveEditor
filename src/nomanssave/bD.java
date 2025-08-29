package nomanssave;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.UIManager;

class bD extends DefaultListCellRenderer {
  private bD(bl parambl) {}
  
  public Component getListCellRendererComponent(JList<?> paramJList, Object paramObject, int paramInt, boolean paramBoolean1, boolean paramBoolean2) {
    Component component = super.getListCellRendererComponent(paramJList, paramObject, paramInt, paramBoolean1, paramBoolean2);
    if (paramObject == null && component instanceof JLabel) {
      JLabel jLabel = (JLabel)component;
      jLabel.setText(" ");
    } 
    if (paramObject instanceof er && component instanceof JLabel) {
      er er = (er)paramObject;
      JLabel jLabel = (JLabel)component;
      if (er.aW()) {
        if (paramBoolean1) {
          jLabel.setBackground(UIManager.getColor("Frigate.positiveTraitHighlight"));
        } else {
          jLabel.setForeground(UIManager.getColor("Frigate.positiveTraitColor"));
        } 
      } else if (paramBoolean1) {
        jLabel.setBackground(UIManager.getColor("Frigate.negativeTraitHighlight"));
      } else {
        jLabel.setForeground(UIManager.getColor("Frigate.negativeTraitColor"));
      } 
    } 
    return component;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\bD.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */