package nomanssave;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class Q extends JDialog {
   private W bw;
   private int bx;
   private int by;
   private W bz = null;
   private JTextField bA;
   private JTextField bB;
   private static Q bC;

   private Q(Frame var1) {
      super(var1);
      this.setResizable(false);
      this.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
      this.setTitle("Change Stack Sizes");
      this.setModal(true);
      JPanel var2 = new JPanel();
      this.setContentPane(var2);
      var2.setLayout(new BorderLayout(0, 0));
      JPanel var3 = new JPanel();
      var3.setLayout(new FormLayout(new ColumnSpec[]{FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("100px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("250px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("20dlu"), FormFactory.LINE_GAP_ROWSPEC}));
      JLabel var4 = new JLabel("Substances:");
      var3.add(var4, "2, 2, left, center");
      this.bA = new JTextField();
      this.bA.addFocusListener(new R(this));
      var3.add(this.bA, "4, 2, fill, default");
      JLabel var5 = new JLabel("Products:");
      var3.add(var5, "2, 4, left, center");
      this.bB = new JTextField();
      this.bB.addFocusListener(new S(this));
      var3.add(this.bB, "4, 4, fill, default");
      JLabel var6 = new JLabel("<html><font color=\"red\"><b>Please Note: No Man's Sky sometimes reverts these settings back to default.</b></font></html>");
      var3.add(var6, "2, 6, 3, 1, fill, center");
      var2.add(var3);
      JPanel var7 = new JPanel();
      var7.setLayout(new FlowLayout(2));
      var2.add(var7, "South");
      JButton var8 = new JButton("Save");
      var8.addActionListener(new T(this));
      var7.add(var8);
      this.getRootPane().setDefaultButton(var8);
      JButton var9 = new JButton("Cancel");
      var9.addActionListener(new U(this));
      var7.add(var9);
      this.getRootPane().registerKeyboardAction(new V(this), KeyStroke.getKeyStroke(27, 0), 2);
      this.pack();
   }

   private W a(W var1, int var2, int var3) {
      this.bw = var1;
      this.bx = var2;
      this.by = var3;
      this.bA.setText(Integer.toString(var1.bE));
      this.bB.setText(Integer.toString(var1.bF));
      this.bz = null;
      this.setLocationRelativeTo(this.getParent());
      this.setVisible(true);
      return this.bz;
   }

   public static W a(Container var0, W var1, int var2, int var3) {
      if (bC == null) {
         Frame var4 = JOptionPane.getFrameForComponent(var0);
         bC = new Q(var4);
      }

      return bC.a(var1, var2, var3);
   }

   // $FF: synthetic method
   static JTextField a(Q var0) {
      return var0.bA;
   }

   // $FF: synthetic method
   static int b(Q var0) {
      return var0.bx;
   }

   // $FF: synthetic method
   static W c(Q var0) {
      return var0.bw;
   }

   // $FF: synthetic method
   static JTextField d(Q var0) {
      return var0.bB;
   }

   // $FF: synthetic method
   static int e(Q var0) {
      return var0.by;
   }

   // $FF: synthetic method
   static void a(Q var0, W var1) {
      var0.bz = var1;
   }
}
