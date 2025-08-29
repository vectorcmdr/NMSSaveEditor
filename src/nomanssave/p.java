package nomanssave;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.util.Collections;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class p extends JDialog {
  private final JTable D;
  
  private final TableRowSorter E;
  
  private List F;
  
  private List G = null;
  
  private static p H = null;
  
  private p(Frame paramFrame) {
    super(paramFrame);
    setSize(aH.cI * 2, aH.cI + aH.cH);
    setResizable(false);
    setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
    setModal(true);
    JPanel jPanel1 = new JPanel();
    setContentPane(jPanel1);
    jPanel1.setLayout(new BorderLayout(0, 0));
    JScrollPane jScrollPane = new JScrollPane();
    this.D = new JTable();
    this.D.setSelectionMode(2);
    this.D.setModel(new q(this));
    this.D.getColumnModel().getColumn(0).setMaxWidth(24);
    this.E = new TableRowSorter<>(this.D.getModel());
    this.E.setSortable(0, false);
    this.D.setRowSorter(this.E);
    jScrollPane.setViewportView(this.D);
    jPanel1.add(jScrollPane);
    JPanel jPanel2 = new JPanel();
    jPanel2.setLayout(new FlowLayout(2));
    jPanel1.add(jPanel2, "South");
    JButton jButton1 = new JButton("Add");
    jButton1.addActionListener(new r(this));
    jPanel2.add(jButton1);
    getRootPane().setDefaultButton(jButton1);
    JButton jButton2 = new JButton("Cancel");
    jButton2.addActionListener(new s(this));
    jPanel2.add(jButton2);
    getRootPane().registerKeyboardAction(new t(this), KeyStroke.getKeyStroke(27, 0), 2);
  }
  
  private String[] d() {
    this.D.clearSelection();
    this.E.setSortKeys(Collections.emptyList());
    this.E.sort();
    this.D.updateUI();
    this.G = null;
    setLocationRelativeTo(getParent());
    setVisible(true);
    return (this.G == null) ? new String[0] : (String[])this.G.toArray((Object[])new String[0]);
  }
  
  public static String[] b(Container paramContainer) {
    if (H == null) {
      Frame frame = JOptionPane.getFrameForComponent(paramContainer);
      H = new p(frame);
    } 
    H.F = ey.bl();
    H.setTitle("Add Known Technologies");
    return H.d();
  }
  
  public static String[] c(Container paramContainer) {
    if (H == null) {
      Frame frame = JOptionPane.getFrameForComponent(paramContainer);
      H = new p(frame);
    } 
    H.F = ey.bm();
    H.setTitle("Add Known Products");
    return H.d();
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\p.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */