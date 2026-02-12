using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class ba : Panel {
   private FormLayout dA;

   ba() {
      this(aH.cH, 0);
   }

   ba(int... var1) {
      this.dA = new FormLayout(new ColumnSpec[]{FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC});

      for(int var2 = 0; var2 < var1.Length; ++var2) {
         if (var1[var2] > 0) {
            this.dA.appendColumn(ColumnSpec.decode(var1[var2] + "px"));
         } else {
            this.dA.appendColumn(ColumnSpec.decode("default:grow"));
         }

         this.dA.appendColumn(FormFactory.LABEL_COMPONENT_GAP_COLSPEC);
      }

      this.setLayout(this.dA);
   }

   void k(string var1) {
      this.a(var1, (Image)null);
   }

   void a(string var1, Image var2) {
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
      Label var4 = new Label(var1);
      var4.putClientProperty("FlatLaf.styleClass", "semibold");
      if (var2 == null) {
         this.Add(var4, "2, " + (this.dA.getRowCount() - 1) + ", " + var3 + ", 1, left, default");
      } else {
         Panel var5 = new Panel();
         var5.setLayout(new FlowLayoutPanel(0, 0, 0));
         var5.Add(new Label(var2));
         var5.Add(var4);
         this.Add(var5, "2, " + (this.dA.getRowCount() - 1) + ", " + var3 + ", 1, left, default");
      }

   }

   void addText(string var1) {
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
      Label var3 = new Label(var1);
      this.Add(var3, "2, " + (this.dA.getRowCount() - 1) + ", " + var2 + ", 1, left, default");
   }

   void Y() {
      this.dA.appendRow(RowSpec.decode("bottom:10px"));
      this.dA.appendRow(FormFactory.LINE_GAP_ROWSPEC);
   }

   void a(string var1, Control var2) {
      this.a(var1, false, var2, 1);
   }

   void a(string var1, Control var2, int var3) {
      this.a(var1, false, var2, var3);
   }

   void a(string var1, bool var2, Control var3) {
      this.a(var1, var2, var3, 1);
   }

   void a(string var1, bool var2, Control var3, int var4) {
      var4 = var4 * 2 - 1;
      this.dA.appendRow(FormFactory.DEFAULT_ROWSPEC);
      this.dA.appendRow(FormFactory.LINE_GAP_ROWSPEC);
      int var5 = this.dA.getRowCount() - 1;
      if (var1 != null) {
         Label var6 = new Label(var1 + ":");
         if (var2) {
            var6.putClientProperty("FlatLaf.styleClass", "semibold");
         }

         this.Add(var6, "2, " + var5 + ", left, default");
      }

      this.Add(var3, "4, " + var5 + ", " + var4 + ", 1, fill, default");
   }

   void a(string var1, G var2) {
      Panel var3 = new Panel();
      var3.setLayout(new TableLayoutPanel(0, 0));
      Panel var4 = new Panel();
      var4.setLayout(new FlowLayoutPanel(2, 0, 0));
      Button var5 = new Button("Generate");
      var5.setEnabled(var2.isEnabled());
      var5.addActionListener(new bb(this, var2));
      var2.addPropertyChangeListener("enabled", new bc(this, var5, var2));
      var4.Add(var5);
      var3.Add(var2, "Center");
      var3.Add(var4, "South");
      this.a(var1, (Control)var3);
   }

   void a(Control var1) {
      this.dA.appendRow(FormFactory.DEFAULT_ROWSPEC);
      this.dA.appendRow(FormFactory.LINE_GAP_ROWSPEC);
      int var2 = this.dA.getColumnCount() - 2;
      int var3 = this.dA.getRowCount() - 1;
      this.Add(var1, "2, " + var3 + ", " + var2 + ", 1, fill, default");
   }
}

}
