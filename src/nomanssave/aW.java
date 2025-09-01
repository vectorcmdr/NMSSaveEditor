package nomanssave;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class aW extends JDialog {
   private JTextField ds;
   private JCheckBox dt;
   private JCheckBox du;
   private JRadioButton dv;
   private JRadioButton dw;
   private static aW dx;

   private aW(cy var1) {
      super(var1);
      this.setSize(400, 250);
      this.setResizable(false);
      this.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
      this.setTitle("Find");
      this.setModal(true);
      JPanel var2 = new JPanel();
      this.setContentPane(var2);
      var2.setLayout(new BorderLayout(0, 0));
      JPanel var3 = new JPanel();
      var3.setLayout(new FormLayout(new ColumnSpec[]{FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("100px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("250px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC}));
      JLabel var4 = new JLabel("Find:");
      var3.add(var4, "2, 2, left, center");
      this.ds = new JTextField();
      var3.add(this.ds, "4, 2, fill, default");
      var2.add(var3);
      JPanel var5 = new JPanel();
      var5.setLayout(new GridLayout(1, 2));
      var5.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Direction"), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
      this.dv = new JRadioButton("Forward");
      this.dv.setSelected(true);
      var5.add(this.dv);
      this.dw = new JRadioButton("Backward");
      var5.add(this.dw);
      ButtonGroup var6 = new ButtonGroup();
      var6.add(this.dv);
      var6.add(this.dw);
      var3.add(var5, "2, 4, 3, 1");
      JPanel var7 = new JPanel();
      var7.setLayout(new GridLayout(1, 2));
      var7.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Options"), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
      this.dt = new JCheckBox("Case Sensitive");
      this.dt.setSelected(true);
      var7.add(this.dt);
      this.du = new JCheckBox("Wrap Search");
      var7.add(this.du);
      var3.add(var7, "2, 6, 3, 1");
      JPanel var8 = new JPanel();
      var8.setLayout(new FlowLayout(2));
      var2.add(var8, "South");
      JButton var9 = new JButton("Find");
      var9.setMnemonic(10);
      var9.addActionListener(new aX(this, var1));
      var8.add(var9);
      this.getRootPane().setDefaultButton(var9);
      JButton var10 = new JButton("Cancel");
      var10.setMnemonic(27);
      var10.addActionListener(new aY(this));
      var8.add(var10);
      this.getRootPane().registerKeyboardAction(new aZ(this), KeyStroke.getKeyStroke(27, 0), 2);
      this.pack();
   }

   public static void a(cy var0, String var1) {
      if (dx == null) {
         dx = new aW(var0);
      }

      dx.setLocationRelativeTo(var0);
      if (var1 != null) {
         dx.ds.setText(var1);
      }

      dx.ds.setSelectionStart(0);
      dx.ds.setSelectionEnd(dx.ds.getText().length());
      dx.ds.requestFocus();
      dx.setVisible(true);
   }

   // $FF: synthetic method
   static JTextField a(aW var0) {
      return var0.ds;
   }

   // $FF: synthetic method
   static JRadioButton b(aW var0) {
      return var0.dw;
   }

   // $FF: synthetic method
   static JCheckBox c(aW var0) {
      return var0.dt;
   }

   // $FF: synthetic method
   static JCheckBox d(aW var0) {
      return var0.du;
   }
}
