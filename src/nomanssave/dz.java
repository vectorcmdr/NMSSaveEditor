package nomanssave;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

public class dz extends JDialog {
   private final JList hr;
   private ft[] hs;
   private int gU;
   private static dz ht = null;

   private dz(Frame var1) {
      super(var1);
      this.setSize(300, 400);
      this.setResizable(false);
      this.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
      this.setTitle("Save File As");
      this.setModal(true);
      JPanel var2 = new JPanel();
      this.setContentPane(var2);
      var2.setLayout(new BorderLayout(0, 0));
      JScrollPane var3 = new JScrollPane();
      this.hr = new JList();
      this.hr.setSelectionMode(0);
      this.hr.setModel(new dA(this));
      var3.setViewportView(this.hr);
      var2.add(var3);
      JPanel var4 = new JPanel();
      var4.setLayout(new FlowLayout(2));
      var2.add(var4, "South");
      JButton var5 = new JButton("Replace/Save");
      var5.addActionListener(new dB(this));
      var4.add(var5);
      this.getRootPane().setDefaultButton(var5);
      JButton var6 = new JButton("Cancel");
      var6.addActionListener(new dC(this));
      var4.add(var6);
      this.getRootPane().registerKeyboardAction(new dD(this), KeyStroke.getKeyStroke(27, 0), 2);
   }

   private int a(ft[] var1, int var2) {
      this.hs = var1;
      this.hr.updateUI();
      this.hr.setSelectedIndex(var2);
      this.gU = -1;
      this.setLocationRelativeTo(this.getParent());
      this.setVisible(true);
      return this.gU;
   }

   public static int a(Container var0, ft[] var1, int var2) {
      if (ht == null) {
         Frame var3 = JOptionPane.getFrameForComponent(var0);
         ht = new dz(var3);
      }

      return ht.a(var1, var2);
   }

   // $FF: synthetic method
   static ft[] a(dz var0) {
      return var0.hs;
   }

   // $FF: synthetic method
   static JList b(dz var0) {
      return var0.hr;
   }

   // $FF: synthetic method
   static void a(dz var0, int var1) {
      var0.gU = var1;
   }
}
