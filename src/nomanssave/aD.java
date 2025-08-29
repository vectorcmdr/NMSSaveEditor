package nomanssave;

import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.util.stream.Stream;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class aD extends JDialog {
  private JComboBox cw;
  
  private JTextField cx;
  
  private boolean cy;
  
  public static aD cz = null;
  
  private aD(Frame paramFrame) {
    super(paramFrame);
    setMinimumSize(new Dimension(400, 10));
    setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
    setTitle("Editor Settings");
    setModal(true);
    ba ba = new ba();
    this.cw = new JComboBox();
    this.cw.setModel(new aE(this));
    ba.a("Look & Feel", this.cw);
    this.cx = new JTextField();
    ba.a("Inventory Scale", this.cx);
    ba.Y();
    JPanel jPanel = new JPanel();
    ba.a(jPanel);
    JButton jButton1 = new JButton("Apply");
    jButton1.addActionListener(new aF(this));
    jPanel.add(jButton1);
    JButton jButton2 = new JButton("Cancel");
    jButton2.addActionListener(new aG(this));
    jPanel.add(jButton2);
    setContentPane(ba);
    pack();
  }
  
  private boolean S() {
    String str = aH.getProperty("LookAndFeel");
    aI aI = Stream.<aI>of(aI.values()).filter(paramaI -> paramaI.name().equalsIgnoreCase(paramString)).findFirst().orElse(aI.cN);
    this.cw.setSelectedItem(aI);
    this.cx.setText(Double.toString(aH.a("InventoryScaling", 1.0D)));
    setLocationRelativeTo(getParent());
    this.cy = false;
    setVisible(true);
    return this.cy;
  }
  
  public static boolean d(Container paramContainer) {
    if (cz == null) {
      Frame frame = JOptionPane.getFrameForComponent(paramContainer);
      cz = new aD(frame);
    } 
    return cz.S();
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\aD.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */