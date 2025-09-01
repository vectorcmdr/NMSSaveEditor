package nomanssave;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ba extends JPanel {
   private final FormLayout dA;

   ba() {
      this(aH.cH, 0);
   }

   ba(int... var1) {
      this.dA = new FormLayout(new ColumnSpec[]{FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC});

      for(int var2 = 0; var2 < var1.length; ++var2) {
         if (var1[var2] > 0) {
            this.dA.appendColumn(ColumnSpec.decode(var1[var2] + "px"));
         } else {
            this.dA.appendColumn(ColumnSpec.decode("default:grow"));
         }

         this.dA.appendColumn(FormFactory.LABEL_COMPONENT_GAP_COLSPEC);
      }

      this.setLayout(this.dA);
   }

   void k(String var1) {
      this.a(var1, (ImageIcon)null);
   }

   void a(String var1, ImageIcon var2) {
      int var3;
      if (this.dA.getRowCount() == 1) {
         this.dA.appendRow(FormFactory.DEFAULT_ROWSPEC);
         this.dA.appendRow(FormFactory.LINE_GAP_ROWSPEC);
      } else {
         var3 = this.dA.getRowCount();
         this.dA.insertRow(var3, FormFactory.DEFAULT_ROWSPEC);
         this.dA.insertRow(var3, RowSpec.decode("bottom:25px"));
      }

      var3 = this.dA.getColumnCount() - 2;
      JLabel var4 = new JLabel(var1);
      var4.putClientProperty("FlatLaf.styleClass", "semibold");
      if (var2 == null) {
         this.add(var4, "2, " + (this.dA.getRowCount() - 1) + ", " + var3 + ", 1, left, default");
      } else {
         JPanel var5 = new JPanel();
         var5.setLayout(new FlowLayout(0, 0, 0));
         var5.add(new JLabel(var2));
         var5.add(var4);
         this.add(var5, "2, " + (this.dA.getRowCount() - 1) + ", " + var3 + ", 1, left, default");
      }

   }

   void addText(String var1) {
      int var2;
      if (this.dA.getRowCount() == 1) {
         this.dA.appendRow(FormFactory.DEFAULT_ROWSPEC);
         this.dA.appendRow(FormFactory.LINE_GAP_ROWSPEC);
      } else {
         var2 = this.dA.getRowCount();
         this.dA.insertRow(var2, FormFactory.DEFAULT_ROWSPEC);
         this.dA.insertRow(var2, RowSpec.decode("bottom:25px"));
      }

      var2 = this.dA.getColumnCount() - 2;
      JLabel var3 = new JLabel(var1);
      this.add(var3, "2, " + (this.dA.getRowCount() - 1) + ", " + var2 + ", 1, left, default");
   }

   void Y() {
      this.dA.appendRow(RowSpec.decode("bottom:10px"));
      this.dA.appendRow(FormFactory.LINE_GAP_ROWSPEC);
   }

   void a(String var1, JComponent var2) {
      this.a(var1, false, var2, 1);
   }

   void a(String var1, JComponent var2, int var3) {
      this.a(var1, false, var2, var3);
   }

   void a(String var1, boolean var2, JComponent var3) {
      this.a(var1, var2, var3, 1);
   }

   void a(String var1, boolean var2, JComponent var3, int var4) {
      var4 = var4 * 2 - 1;
      this.dA.appendRow(FormFactory.DEFAULT_ROWSPEC);
      this.dA.appendRow(FormFactory.LINE_GAP_ROWSPEC);
      int var5 = this.dA.getRowCount() - 1;
      if (var1 != null) {
         JLabel var6 = new JLabel(var1 + ":");
         if (var2) {
            var6.putClientProperty("FlatLaf.styleClass", "semibold");
         }

         this.add(var6, "2, " + var5 + ", left, default");
      }

      this.add(var3, "4, " + var5 + ", " + var4 + ", 1, fill, default");
   }

   void a(String var1, G var2) {
      JPanel var3 = new JPanel();
      var3.setLayout(new BorderLayout(0, 0));
      JPanel var4 = new JPanel();
      var4.setLayout(new FlowLayout(2, 0, 0));
      JButton var5 = new JButton("Generate");
      var5.setEnabled(var2.isEnabled());
      var5.addActionListener(new bb(this, var2));
      var2.addPropertyChangeListener("enabled", new bc(this, var5, var2));
      var4.add(var5);
      var3.add(var2, "Center");
      var3.add(var4, "South");
      this.a(var1, (JComponent)var3);
   }

   void a(JComponent var1) {
      this.dA.appendRow(FormFactory.DEFAULT_ROWSPEC);
      this.dA.appendRow(FormFactory.LINE_GAP_ROWSPEC);
      int var2 = this.dA.getColumnCount() - 2;
      int var3 = this.dA.getRowCount() - 1;
      this.add(var1, "2, " + var3 + ", " + var2 + ", 1, fill, default");
   }
}
