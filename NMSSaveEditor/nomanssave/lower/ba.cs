using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class ba : Panel {
   // TODO: private FormLayout dA;

   public ba() {
      this(aH.cH, 0);
   }

   public ba(params int[] var1) {
      // TODO: this.dA = /* FormLayout */ null;

      for(int var2 = 0; var2 < var1.Length; ++var2) {
         if (var1[var2] > 0) {
            // TODO: this.dA.appendColumn(ColumnSpec.decode(var1[var2] + "px"));
         } else {
            // TODO: this.dA.appendColumn(ColumnSpec.decode("default:grow"));
         }

         // TODO: this.dA.appendColumn(FormFactory.LABEL_COMPONENT_GAP_COLSPEC);
      }

      this.SuspendLayout(); // TODO: set layout this.dA);
   }

   public void k(string var1) {
      this.a(var1, (Image)null);
   }

   public void a(string var1, Image var2) {
      int var3;
      if (this.dA.getRowCount() == 1) {
         // TODO: this.dA.appendRow(FormFactory.DEFAULT_ROWSPEC);
         // TODO: this.dA.appendRow(FormFactory.LINE_GAP_ROWSPEC);
      } else {
         var3 = this.dA.getRowCount();
         // TODO: this.dA.insertRow(var3, FormFactory.DEFAULT_ROWSPEC);
         // TODO: this.dA.insertRow(var3, RowSpec.decode("bottom:25px"));
      }

      var3 = this.dA.getColumnCount() - 2;
      Label var4 = new Label(var1);
      // TODO: var4.putClientProperty(...);
      if (var2 == null) {
         this.Add(var4, "2, " + (this.dA.getRowCount() - 1) + ", " + var3 + ", 1, left, default");
      } else {
         Panel var5 = new Panel();
         var5.SuspendLayout(); // TODO: set layout new FlowLayoutPanel(0, 0, 0));
         var5.Add(new Label(var2));
         var5.Add(var4);
         this.Add(var5, "2, " + (this.dA.getRowCount() - 1) + ", " + var3 + ", 1, left, default");
      }

   }

   public void addText(string var1) {
      int var2;
      if (this.dA.getRowCount() == 1) {
         // TODO: this.dA.appendRow(FormFactory.DEFAULT_ROWSPEC);
         // TODO: this.dA.appendRow(FormFactory.LINE_GAP_ROWSPEC);
      } else {
         var2 = this.dA.getRowCount();
         // TODO: this.dA.insertRow(var2, FormFactory.DEFAULT_ROWSPEC);
         // TODO: this.dA.insertRow(var2, RowSpec.decode("bottom:25px"));
      }

      var2 = this.dA.getColumnCount() - 2;
      Label var3 = new Label(var1);
      this.Add(var3, "2, " + (this.dA.getRowCount() - 1) + ", " + var2 + ", 1, left, default");
   }

   public void Y() {
      // TODO: this.dA.appendRow(RowSpec.decode("bottom:10px"));
      // TODO: this.dA.appendRow(FormFactory.LINE_GAP_ROWSPEC);
   }

   public void a(string var1, Control var2) {
      this.a(var1, false, var2, 1);
   }

   public void a(string var1, Control var2, int var3) {
      this.a(var1, false, var2, var3);
   }

   public void a(string var1, bool var2, Control var3) {
      this.a(var1, var2, var3, 1);
   }

   public void a(string var1, bool var2, Control var3, int var4) {
      var4 = var4 * 2 - 1;
      // TODO: this.dA.appendRow(FormFactory.DEFAULT_ROWSPEC);
      // TODO: this.dA.appendRow(FormFactory.LINE_GAP_ROWSPEC);
      int var5 = this.dA.getRowCount() - 1;
      if (var1 != null) {
         Label var6 = new Label(var1 + ":");
         if (var2) {
            // TODO: var6.putClientProperty(...);
         }

         this.Add(var6, "2, " + var5 + ", left, default");
      }

      this.Add(var3, "4, " + var5 + ", " + var4 + ", 1, fill, default");
   }

   public void a(string var1, G var2) {
      Panel var3 = new Panel();
      var3.SuspendLayout(); // TODO: set layout new TableLayoutPanel(0, 0));
      Panel var4 = new Panel();
      var4.SuspendLayout(); // TODO: set layout new FlowLayoutPanel(2, 0, 0));
      Button var5 = new Button("Generate");
      var5.Enabled = (var2.Enabled);
      var5.Click += (new bb(this, var2));
      var2.addPropertyChangeListener("enabled", new bc(this, var5, var2));
      var4.Add(var5);
      var3.Controls.Add(var2);
      var3.Controls.Add(var4);
      this.a(var1, (Control)var3);
   }

   public void a(Control var1) {
      // TODO: this.dA.appendRow(FormFactory.DEFAULT_ROWSPEC);
      // TODO: this.dA.appendRow(FormFactory.LINE_GAP_ROWSPEC);
      int var2 = this.dA.getColumnCount() - 2;
      int var3 = this.dA.getRowCount() - 1;
      this.Add(var1, "2, " + var3 + ", " + var2 + ", 1, fill, default");
   }
}

}
