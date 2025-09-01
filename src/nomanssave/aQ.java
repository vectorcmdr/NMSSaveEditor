package nomanssave;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
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

public class aQ extends JDialog {
   private Dimension dk;
   private Dimension dl;
   private Dimension dm;
   private Dimension dn = null;
   private JTextField do;
   private JTextField dp;
   private static aQ dq;

   private aQ(Frame var1) {
      super(var1);
      this.setResizable(false);
      this.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
      this.setTitle("Expand Inventory");
      this.setModal(true);
      JPanel var2 = new JPanel();
      this.setContentPane(var2);
      var2.setLayout(new BorderLayout(0, 0));
      JPanel var3 = new JPanel();
      var3.setLayout(new FormLayout(new ColumnSpec[]{FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("100px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("250px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC}));
      JLabel var4 = new JLabel("Width:");
      var3.add(var4, "2, 2, left, center");
      this.do = new JTextField();
      this.do.addFocusListener(new aR(this));
      var3.add(this.do, "4, 2, fill, default");
      JLabel var5 = new JLabel("Height:");
      var3.add(var5, "2, 4, left, center");
      this.dp = new JTextField();
      this.dp.addFocusListener(new aS(this));
      var3.add(this.dp, "4, 4, fill, default");
      var2.add(var3);
      JPanel var6 = new JPanel();
      var6.setLayout(new FlowLayout(2));
      var2.add(var6, "South");
      JButton var7 = new JButton("Save");
      var7.addActionListener(new aT(this));
      var6.add(var7);
      this.getRootPane().setDefaultButton(var7);
      JButton var8 = new JButton("Cancel");
      var8.addActionListener(new aU(this));
      var6.add(var8);
      this.getRootPane().registerKeyboardAction(new aV(this), KeyStroke.getKeyStroke(27, 0), 2);
      this.pack();
   }

   private Dimension a(Dimension var1, Dimension var2, Dimension var3) {
      this.dk = var1;
      this.dl = var2;
      this.dm = var3;
      this.do.setText(Integer.toString(var1.width));
      this.dp.setText(Integer.toString(var1.height));
      this.dn = null;
      this.setLocationRelativeTo(this.getParent());
      this.setVisible(true);
      return this.dn;
   }

   public static Dimension a(Container var0, Dimension var1, Dimension var2, Dimension var3) {
      if (dq == null) {
         Frame var4 = JOptionPane.getFrameForComponent(var0);
         dq = new aQ(var4);
      }

      return dq.a(var1, var2, var3);
   }

   // $FF: synthetic method
   static JTextField a(aQ var0) {
      return var0.do;
   }

   // $FF: synthetic method
   static Dimension b(aQ var0) {
      return var0.dk;
   }

   // $FF: synthetic method
   static Dimension c(aQ var0) {
      return var0.dl;
   }

   // $FF: synthetic method
   static Dimension d(aQ var0) {
      return var0.dm;
   }

   // $FF: synthetic method
   static JTextField e(aQ var0) {
      return var0.dp;
   }

   // $FF: synthetic method
   static void a(aQ var0, Dimension var1) {
      var0.dn = var1;
   }
}
