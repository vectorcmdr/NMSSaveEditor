using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Windows.Forms;
using System.Globalization;

namespace NMSSaveEditor
{

public class ba : Panel {
   public FormLayout dA;

   public ba() {
      // Constructor chain: base(aH.cH, 0)
   }

   public ba(params int[] var1) {
      this.dA = new FormLayout(new ColumnSpec[]{FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC});

      for(int var2 = 0; var2 < var1.length; ++var2) {
         if (var1[var2] > 0) {
            this.dA.appendColumn(ColumnSpec.decode(var1[var2] + "px"));
         } else {
            this.dA.appendColumn(ColumnSpec.decode("default:grow"));
         }

         this.dA.appendColumn(FormFactory.LABEL_COMPONENT_GAP_COLSPEC);
      }

      this.SetLayout(this.dA);
   }

   public void k(string var1) {
      this.a(var1, (Image)null);
   }

   public void a(string var1, Image var2) {
      int var3;
      if (this.dA.RowCount == 1) {
         this.dA.appendRow(FormFactory.DEFAULT_ROWSPEC);
         this.dA.appendRow(FormFactory.LINE_GAP_ROWSPEC);
      } else {
         var3 = this.dA.RowCount;
         this.dA.insertRow(var3, FormFactory.DEFAULT_ROWSPEC);
         this.dA.insertRow(var3, RowSpec.decode("bottom:25px"));
      }

      var3 = this.dA.ColumnCount - 2;
      Label var4 = new Label(var1);
      var4.putClientProperty("FlatLaf.styleClass", "semibold");
      if (var2 == null) {
         this.Add(var4, "2, " + (this.dA.RowCount - 1) + ", " + var3 + ", 1, left, default");
      } else {
         Panel var5 = new Panel();
         var5.SetLayout(new FlowLayout(0, 0, 0));
         var5.Add(new Label(var2));
         var5.Add(var4);
         this.Add(var5, "2, " + (this.dA.RowCount - 1) + ", " + var3 + ", 1, left, default");
      }

   }

   public void addText(string var1) {
      int var2;
      if (this.dA.RowCount == 1) {
         this.dA.appendRow(FormFactory.DEFAULT_ROWSPEC);
         this.dA.appendRow(FormFactory.LINE_GAP_ROWSPEC);
      } else {
         var2 = this.dA.RowCount;
         this.dA.insertRow(var2, FormFactory.DEFAULT_ROWSPEC);
         this.dA.insertRow(var2, RowSpec.decode("bottom:25px"));
      }

      var2 = this.dA.ColumnCount - 2;
      Label var3 = new Label(var1);
      this.Add(var3, "2, " + (this.dA.RowCount - 1) + ", " + var2 + ", 1, left, default");
   }

   public void Y() {
      this.dA.appendRow(RowSpec.decode("bottom:10px"));
      this.dA.appendRow(FormFactory.LINE_GAP_ROWSPEC);
   }

   public void a(string var1, JComponent var2) {
      this.a(var1, false, var2, 1);
   }

   public void a(string var1, JComponent var2, int var3) {
      this.a(var1, false, var2, var3);
   }

   public void a(string var1, bool var2, JComponent var3) {
      this.a(var1, var2, var3, 1);
   }

   public void a(string var1, bool var2, JComponent var3, int var4) {
      var4 = var4 * 2 - 1;
      this.dA.appendRow(FormFactory.DEFAULT_ROWSPEC);
      this.dA.appendRow(FormFactory.LINE_GAP_ROWSPEC);
      int var5 = this.dA.RowCount - 1;
      if (var1 != null) {
         Label var6 = new Label(var1 + ":");
         if (var2) {
            var6.putClientProperty("FlatLaf.styleClass", "semibold");
         }

         this.Add(var6, "2, " + var5 + ", left, default");
      }

      this.Add(var3, "4, " + var5 + ", " + var4 + ", 1, fill, default");
   }

   public void a(string var1, G var2) {
      Panel var3 = new Panel();
      var3.SetLayout(new BorderLayout(0, 0));
      Panel var4 = new Panel();
      var4.SetLayout(new FlowLayout(2, 0, 0));
      Button var5 = new Button("Generate");
      var5.SetEnabled(var2.Enabled);
      var5.AddActionListener(new bb(this, var2));
      var2.addPropertyChangeListener("enabled", new bc(this, var5, var2));
      var4.Add(var5);
      var3.Add(var2, "Center");
      var3.Add(var4, "South");
      this.a(var1, (JComponent)var3);
   }

   public void a(JComponent var1) {
      this.dA.appendRow(FormFactory.DEFAULT_ROWSPEC);
      this.dA.appendRow(FormFactory.LINE_GAP_ROWSPEC);
      int var2 = this.dA.ColumnCount - 2;
      int var3 = this.dA.RowCount - 1;
      this.Add(var1, "2, " + var3 + ", " + var2 + ", 1, fill, default");
   }
}

}
