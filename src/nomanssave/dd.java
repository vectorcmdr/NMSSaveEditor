package nomanssave;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Dialog.ModalExclusionType;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

public class dd extends JDialog {
   private final JList gS;
   private List gT;
   private int gU;
   private static dd gV = null;

   private dd(Frame var1) {
      super(var1);
      this.setSize(300, 300);
      this.setResizable(false);
      this.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
      this.setTitle("Move Item");
      this.setModal(true);
      JPanel var2 = new JPanel();
      this.setContentPane(var2);
      var2.setLayout(new BorderLayout(0, 0));
      JScrollPane var3 = new JScrollPane();
      this.gS = new JList();
      this.gS.setSelectionMode(0);
      this.gS.setModel(new de(this));
      this.gS.addMouseListener(new df(this));
      var3.setViewportView(this.gS);
      var2.add(var3);
      JPanel var4 = new JPanel();
      var4.setLayout(new FlowLayout(2));
      var2.add(var4, "South");
      JButton var5 = new JButton("Move");
      var5.addActionListener(new dg(this));
      var4.add(var5);
      this.getRootPane().setDefaultButton(var5);
      JButton var6 = new JButton("Cancel");
      var6.addActionListener(new dh(this));
      var4.add(var6);
      this.getRootPane().registerKeyboardAction(new di(this), KeyStroke.getKeyStroke(27, 0), 2);
   }

   private int a(List var1, int var2) {
      this.gT = var1;
      this.gS.updateUI();
      this.gS.setSelectedIndex(this.gU);
      this.gU = var2;
      this.setLocationRelativeTo(this.getParent());
      this.setVisible(true);
      return this.gU;
   }

   public static int a(Container var0, List var1, int var2) {
      if (gV == null) {
         Frame var3 = JOptionPane.getFrameForComponent(var0);
         gV = new dd(var3);
      }

      return gV.a(var1, var2);
   }

   // $FF: synthetic method
   static List a(dd var0) {
      return var0.gT;
   }

   // $FF: synthetic method
   static JList b(dd var0) {
      return var0.gS;
   }

   // $FF: synthetic method
   static void a(dd var0, int var1) {
      var0.gU = var1;
   }
}
