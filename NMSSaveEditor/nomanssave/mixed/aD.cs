using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class aD : Form {
   public ComboBox cw;
   public TextBox cx;
   public bool cy;
   public static aD cz = null;

// PORT_TODO: public aD(Frame var1) : base(var1) {
      // PORT_TODO: this.setMinimumSize(new Size(400, 10));
      // setModalExclusionType not available in WinForms
      // PORT_TODO: this.Text = ("Editor Settings");
      // PORT_TODO: // PORT_TODO: this/* setModal */(true);
      // PORT_TODO: ba var2 = new ba();
      // PORT_TODO: this.cw = new ComboBox();
      // PORT_TODO: this.cw.DataSource = (new aE(this));
      // PORT_TODO: var2.a("Look & Feel", (Control)this.cw);
      // PORT_TODO: this.cx = new TextBox();
      // PORT_TODO: var2.a("Inventory Scale", (Control)this.cx);
      // PORT_TODO: var2.Y();
      // PORT_TODO: Panel var3 = new Panel();
      // PORT_TODO: var2.a(var3);
      // PORT_TODO: Button var4 = new Button() { Text = "Apply" };
      // PORT_TODO: var4.Click += (new aF(this));
      // PORT_TODO: var3.Add(var4);
      // PORT_TODO: Button var5 = new Button() { Text = "Cancel" };
      // PORT_TODO: var5.Click += (new aG(this));
      // PORT_TODO: var3.Add(var5);
      // PORT_TODO: this.setContentPane(var2);
      // PORT_TODO: this.PerformLayout();
   // PORT_TODO: }

   public bool S() {
      string var1 = aH.getProperty("LookAndFeel");
      // PORT_TODO: aI var2 = (aI)Stream.of(aI.Values).filter((var1x) => {
         // PORT_TODO: return var1x.Name.Equals(var1);
      // PORT_TODO: }).findFirst().orElse(aI.cN);
      // PORT_TODO: this.cw.SelectedItem = (var2);
      // PORT_TODO: this.cx.Text = ((aH.a("InventoryScaling", 1.0D).ToString()));
      // PORT_TODO: this.StartPosition = FormStartPosition.CenterParent; //(this.DirectoryName);
      // PORT_TODO: this.cy = false;
      // PORT_TODO: this.Show();
      // PORT_TODO: return this.cy;
       return false; // PORT_TODO: stub return
    }

   public static bool d(Container var0) {
      if (cz == null) {
         Frame var1 = null;
         // PORT_TODO: cz = new aD(var1);
      }

      return cz.S();
   }
   public static ComboBox a(aD var0) {
      return var0.cw;
   }
   public static void a(aD var0, bool var1) {
      var0.cy = var1;
   }
   public static TextBox b(aD var0) {
      return var0.cx;
   }
}



}
