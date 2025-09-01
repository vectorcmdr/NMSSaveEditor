package nomanssave;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Dialog.ModalExclusionType;
import java.util.stream.Collectors;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.Border;

public class cg extends JDialog {
   private JTextField fn;
   private JLabel fo;
   private JTextField fp;
   private JTextField fq;
   private JLabel fr;
   private JTextField fs;
   private G ft;
   private JLabel fu;
   private G fv;
   private JTextField fw;
   private JTextField fx;
   private JTextArea fy;
   private JTextArea fz;
   private ey fA;
   private gQ fB;
   private Integer fC;
   private Integer fD;
   public static cg fE = null;

   private cg(Frame var1) {
      super(var1);
      this.setSize(600, 480);
      this.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
      this.setTitle("Item Details");
      this.setModal(true);
      JPanel var2 = new JPanel();
      var2.setLayout(new FormLayout(new ColumnSpec[]{FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("100px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("100px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("64px"), FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("default:grow"), FormFactory.LINE_GAP_ROWSPEC}));
      var2.add(new JLabel("Type:"), "2, 2, left, center");
      this.fn = new JTextField();
      this.fn.setEditable(false);
      var2.add(this.fn, "4, 2, fill, default");
      this.fo = new JLabel("");
      var2.add(this.fo, "6, 2, 1, 7, center, fill");
      var2.add(new JLabel("Category:"), "2, 4, left, center");
      this.fp = new JTextField();
      this.fp.setEditable(false);
      var2.add(this.fp, "4, 4, fill, default");
      var2.add(new JLabel("Name:"), "2, 6, left, center");
      this.fq = new JTextField();
      this.fq.setEditable(false);
      var2.add(this.fq, "4, 6, fill, default");
      var2.add(new JLabel("ID:"), "2, 8, left, center");
      JPanel var3 = new JPanel();
      var3.setLayout(new FormLayout(new ColumnSpec[]{ColumnSpec.decode("default:grow"), FormFactory.DEFAULT_COLSPEC, ColumnSpec.decode("100px")}, new RowSpec[]{FormFactory.DEFAULT_ROWSPEC}));
      this.fs = new JTextField();
      this.fs.setEditable(false);
      var3.add(this.fs, "1, 1");
      this.fr = new JLabel("#");
      var3.add(this.fr, "2, 1");
      this.ft = new ch(this);
      this.ft.setEditable(false);
      var3.add(this.ft, "3, 1");
      var2.add(var3, "4, 8, fill, default");
      this.fu = new JLabel("Quantity:");
      var2.add(this.fu, "2, 10, left, center");
      var3 = new JPanel();
      var3.setLayout(new FormLayout(new ColumnSpec[]{ColumnSpec.decode("100px"), FormFactory.DEFAULT_COLSPEC, ColumnSpec.decode("100px")}, new RowSpec[]{FormFactory.DEFAULT_ROWSPEC}));
      this.fv = new ci(this);
      this.fv.setEditable(false);
      var3.add(this.fv, "1, 1");
      var3.add(new JLabel("/"), "2, 1");
      this.fw = new JTextField();
      this.fw.setEditable(false);
      var3.add(this.fw, "3, 1");
      var2.add(var3, "4, 10, fill, default");
      var2.add(new JLabel("Subtitle:"), "2, 12, left, center");
      this.fx = new JTextField();
      this.fx.setEditable(false);
      var2.add(this.fx, "4, 12, 3, 1, fill, default");
      var2.add(new JLabel("Build Cost:"), "2, 14, left, top");
      JScrollPane var4 = new JScrollPane();
      var4.setBorder(this.fx.getBorder());
      var4.setBackground(this.fx.getBackground());
      this.fy = new JTextArea();
      this.fy.setEditable(false);
      this.fy.setBorder((Border)null);
      this.fy.setBackground((Color)null);
      this.fy.setFont(this.fx.getFont());
      var4.setViewportView(this.fy);
      var2.add(var4, "4, 14, 3, 1, fill, fill");
      var2.add(new JLabel("Description:"), "2, 16, left, top");
      this.fz = new JTextArea();
      this.fz.setEditable(false);
      this.fz.setWrapStyleWord(true);
      this.fz.setLineWrap(true);
      this.fz.setBorder(this.fx.getBorder());
      this.fz.setBackground(this.fx.getBackground());
      this.fz.setFont(this.fx.getFont());
      var2.add(this.fz, "4, 16, 3, 1, fill, fill");
      this.setContentPane(var2);
      this.getRootPane().registerKeyboardAction(new cj(this), KeyStroke.getKeyStroke(27, 0), 2);
      this.addWindowListener(new ck(this));
   }

   private void a(gQ var1) {
      this.fB = var1;
      Object var2 = var1.dz();
      this.fA = ey.d(var2);
      this.fC = null;
      this.fD = null;
      String var3 = this.fA == null ? var1.getType() : this.fA.ba().toString();
      this.fn.setText(var3);
      this.fo.setIcon(this.fA == null ? null : this.fA.N(2));
      String var4;
      if (this.fA != null && this.fA.bb()) {
         var4 = "";
         int var6;
         if (var2 instanceof fg) {
            fg var5 = (fg)var2;
            var6 = var5.indexOf(35);
            if (var6 >= 0) {
               var4 = var5.substring(var6 + 1);
            }
         } else {
            String var8 = var2.toString();
            var6 = var8.indexOf(35);
            if (var6 >= 0) {
               var4 = var8.substring(var6 + 1);
            }
         }

         this.fs.setText(this.fA.getID());
         this.ft.setText(var4);
         this.fr.setVisible(true);
         this.ft.setVisible(true);

         try {
            int var9 = hf.b(var4, 0, 99999);
            this.fC = new Integer(var9);
            this.ft.setEditable(true);
         } catch (RuntimeException var7) {
            hc.warn("Error detected in item id: " + var2);
            this.fC = null;
            this.ft.setEditable(false);
         }
      } else {
         this.fs.setText(var1.ei());
         this.ft.setText("");
         this.fr.setVisible(false);
         this.ft.setVisible(false);
      }

      if (var3.equals("Technology") && var1.dA() >= 0 && var1.dA() < var1.dB()) {
         this.fu.setText("Charge:");
         this.fD = var1.dA();
         this.fv.setText(Integer.toString(var1.dA()));
         this.fw.setText(Integer.toString(var1.dB()));
         this.fv.setEditable(true);
      } else if ((var3.equals("Product") || var3.equals("Substance")) && var1.dB() > 1) {
         this.fu.setText("Quantity:");
         this.fD = var1.dA();
         this.fv.setText(Integer.toString(var1.dA()));
         this.fw.setText(Integer.toString(var1.dB()));
         this.fv.setEditable(true);
      } else {
         this.fu.setText("Quantity:");
         this.fv.setText("1");
         this.fw.setText("1");
         this.fv.setEditable(false);
      }

      this.fq.setText(this.fA == null ? "[Unknown]" : this.fA.getName());
      this.fp.setText(this.fA == null ? "[Unknown]" : this.fA.bc().toString());
      this.fx.setText(this.fA == null ? "" : this.fA.bg());
      var4 = this.fA == null ? "" : (String)this.fA.bk().stream().map((var0) -> {
         ey var1 = ey.d(var0.getID());
         return var1 != null ? var1.getName() + " (x" + var0.bo() + ")" : var0.getID() + " (x" + var0.bo() + ")";
      }).collect(Collectors.joining("\n"));
      if (var4.length() == 0) {
         var4 = "N/A";
      }

      this.fy.setText(var4);
      this.fy.setCaretPosition(0);
      this.fz.setText(this.fA == null ? "" : this.fA.getDescription());
      this.setLocationRelativeTo(this.getParent());
      this.setVisible(true);
   }

   public static void a(Container var0, gQ var1) {
      if (fE == null) {
         Frame var2 = JOptionPane.getFrameForComponent(var0);
         fE = new cg(var2);
      }

      fE.a(var1);
   }

   // $FF: synthetic method
   static Integer a(cg var0) {
      return var0.fC;
   }

   // $FF: synthetic method
   static gQ b(cg var0) {
      return var0.fB;
   }

   // $FF: synthetic method
   static ey c(cg var0) {
      return var0.fA;
   }

   // $FF: synthetic method
   static void a(cg var0, Integer var1) {
      var0.fC = var1;
   }

   // $FF: synthetic method
   static Integer d(cg var0) {
      return var0.fD;
   }

   // $FF: synthetic method
   static void b(cg var0, Integer var1) {
      var0.fD = var1;
   }

   // $FF: synthetic method
   static G e(cg var0) {
      return var0.ft;
   }

   // $FF: synthetic method
   static G f(cg var0) {
      return var0.fv;
   }
}
