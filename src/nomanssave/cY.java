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
import java.util.Collections;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class cY extends JDialog {
   private JComboBox gM;
   private List gN = Collections.emptyList();
   private int gO = -1;
   private static cY gP = null;

   private cY(Frame var1) {
      super(var1);
      this.setResizable(false);
      this.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
      this.setTitle("Move Base Computer");
      this.setModal(true);
      JPanel var2 = new JPanel();
      this.setContentPane(var2);
      var2.setLayout(new BorderLayout(0, 0));
      JPanel var3 = new JPanel();
      var3.setLayout(new FlowLayout(0));
      var3.add(new JLabel("Please select a base part to swap your base computer with."));
      var2.add(var3, "North");
      JPanel var4 = new JPanel();
      var4.setLayout(new FormLayout(new ColumnSpec[]{FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("100px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("250px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC}));
      JLabel var5 = new JLabel("Base Part:");
      var4.add(var5, "2, 2, left, center");
      this.gM = new JComboBox();
      this.gM.setModel(new cZ(this));
      var4.add(this.gM, "4, 2, fill, default");
      var2.add(var4, "Center");
      JPanel var6 = new JPanel();
      var6.setLayout(new FlowLayout(2));
      var2.add(var6, "South");
      JButton var7 = new JButton("Save");
      var7.addActionListener(new da(this));
      var6.add(var7);
      this.getRootPane().setDefaultButton(var7);
      JButton var8 = new JButton("Cancel");
      var8.addActionListener(new db(this));
      var6.add(var8);
      this.getRootPane().registerKeyboardAction(new dc(this), KeyStroke.getKeyStroke(27, 0), 2);
      this.pack();
   }

   private int b(List var1) {
      this.gN = var1;
      this.setLocationRelativeTo(this.getParent());
      this.gM.setSelectedIndex(0);
      this.gM.updateUI();
      this.gO = -1;
      this.setVisible(true);
      return this.gO;
   }

   public static int a(Container var0, List var1) {
      if (gP == null) {
         Frame var2 = JOptionPane.getFrameForComponent(var0);
         gP = new cY(var2);
      }

      return gP.b(var1);
   }

   // $FF: synthetic method
   static List a(cY var0) {
      return var0.gN;
   }

   // $FF: synthetic method
   static JComboBox b(cY var0) {
      return var0.gM;
   }

   // $FF: synthetic method
   static void a(cY var0, int var1) {
      var0.gO = var1;
   }
}
