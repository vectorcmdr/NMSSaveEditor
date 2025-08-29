package nomanssave;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class cT extends JFileChooser {
  private static cT gv = null;
  
  private static final String name = "Ship Export File";
  
  private static final ImageIcon fH = Application.a("UI-SHIPICON.PNG", 16, 16);
  
  private JCheckBox fO;
  
  public static cT aC() {
    if (gv == null)
      gv = new cT(); 
    return gv;
  }
  
  private cT() {
    setFileSelectionMode(0);
    setAcceptAllFileFilterUsed(false);
    setFileView(new cU(this));
    setFileFilter(new cV(this));
    setDialogTitle("Choose Ship Export File");
    JPanel jPanel = new JPanel();
    jPanel.setLayout(new BoxLayout(jPanel, 1));
    jPanel.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 2));
    jPanel.add(new JLabel("Export Options:"));
    this.fO = new JCheckBox("Products/Substances");
    jPanel.add(this.fO);
    setAccessory(jPanel);
    UIManager.addPropertyChangeListener(paramPropertyChangeEvent -> {
          if ("lookAndFeel".equals(paramPropertyChangeEvent.getPropertyName()))
            SwingUtilities.updateComponentTreeUI(this); 
        });
  }
  
  public boolean aw() {
    return this.fO.isSelected();
  }
  
  public int showSaveDialog(Component paramComponent) {
    getAccessory().setVisible(true);
    return super.showSaveDialog(paramComponent);
  }
  
  public int showOpenDialog(Component paramComponent) {
    getAccessory().setVisible(false);
    return super.showOpenDialog(paramComponent);
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\cT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */