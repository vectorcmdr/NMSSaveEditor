package nomanssave;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Dialog.ModalExclusionType;
import java.util.Collections;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.TableRowSorter;

public class p extends JDialog {
   private final JTable D;
   private final TableRowSorter E;
   private List F;
   private List G = null;
   private static p H = null;

   private p(Frame var1) {
      super(var1);
      this.setSize(aH.cI * 2, aH.cI + aH.cH);
      this.setResizable(false);
      this.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
      this.setModal(true);
      JPanel var2 = new JPanel();
      this.setContentPane(var2);
      var2.setLayout(new BorderLayout(0, 0));
      JScrollPane var3 = new JScrollPane();
      this.D = new JTable();
      this.D.setSelectionMode(2);
      this.D.setModel(new q(this));
      this.D.getColumnModel().getColumn(0).setMaxWidth(24);
      this.E = new TableRowSorter(this.D.getModel());
      this.E.setSortable(0, false);
      this.D.setRowSorter(this.E);
      var3.setViewportView(this.D);
      var2.add(var3);
      JPanel var4 = new JPanel();
      var4.setLayout(new FlowLayout(2));
      var2.add(var4, "South");
      JButton var5 = new JButton("Add");
      var5.addActionListener(new r(this));
      var4.add(var5);
      this.getRootPane().setDefaultButton(var5);
      JButton var6 = new JButton("Cancel");
      var6.addActionListener(new s(this));
      var4.add(var6);
      this.getRootPane().registerKeyboardAction(new t(this), KeyStroke.getKeyStroke(27, 0), 2);
   }

   private String[] d() {
      this.D.clearSelection();
      this.E.setSortKeys(Collections.emptyList());
      this.E.sort();
      this.D.updateUI();
      this.G = null;
      this.setLocationRelativeTo(this.getParent());
      this.setVisible(true);
      return this.G == null ? new String[0] : (String[])this.G.toArray(new String[0]);
   }

   public static String[] b(Container var0) {
      if (H == null) {
         Frame var1 = JOptionPane.getFrameForComponent(var0);
         H = new p(var1);
      }

      H.F = ey.bl();
      H.setTitle("Add Known Technologies");
      return H.d();
   }

   public static String[] c(Container var0) {
      if (H == null) {
         Frame var1 = JOptionPane.getFrameForComponent(var0);
         H = new p(var1);
      }

      H.F = ey.bm();
      H.setTitle("Add Known Products");
      return H.d();
   }

   // $FF: synthetic method
   static List a(p var0) {
      return var0.F;
   }

   // $FF: synthetic method
   static JTable b(p var0) {
      return var0.D;
   }

   // $FF: synthetic method
   static void a(p var0, List var1) {
      var0.G = var1;
   }

   // $FF: synthetic method
   static List c(p var0) {
      return var0.G;
   }
}
