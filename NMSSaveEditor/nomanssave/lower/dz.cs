using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class dz : Form {
   public ListBox hr;
   public ft[] hs;
   public int gU;
   public static dz ht = null;

// PORT_TODO: public dz(Frame var1) : base(var1) {
      // PORT_TODO: this.Size = new Size(300, 400);
      // PORT_TODO: this.FormBorderStyle = FormBorderStyle.FixedDialog; //(false);
      // setModalExclusionType not available in WinForms
      // PORT_TODO: this.Text = ("Save FileInfo As");
      // PORT_TODO: // PORT_TODO: this/* setModal */(true);
      // PORT_TODO: Panel var2 = new Panel();
      // PORT_TODO: this.setContentPane(var2);
      // PORT_TODO: var2.SuspendLayout(); // TODO: set layout new TableLayoutPanel());
      // PORT_TODO: Panel var3 = new Panel();
      // PORT_TODO: this.hr = new ListBox();
      // PORT_TODO: this.hr.setSelectionMode(0);
      // PORT_TODO: this.hr.DataSource = (new dA(this));
      // PORT_TODO: var3.setViewportView(this.hr);
      // PORT_TODO: var2.Add(var3);
      // PORT_TODO: Panel var4 = new Panel();
      // PORT_TODO: var4.SuspendLayout(); // TODO: set layout new FlowLayoutPanel(2));
      // PORT_TODO: var2.Controls.Add(var4);
      // PORT_TODO: Button var5 = new Button() { Text = "Replace/Save" };
      // PORT_TODO: var5.Click += (new dB(this));
      // PORT_TODO: var4.Add(var5);
      // PORT_TODO: this.getRootPane().setDefaultButton(var5);
      // PORT_TODO: Button var6 = new Button() { Text = "Cancel" };
      // PORT_TODO: var6.Click += (new dC(this));
      // PORT_TODO: var4.Add(var6);
      // PORT_TODO: this.getRootPane().registerKeyboardAction(new dD(this), /* KeyStroke */ Keys.None /* (27, 0) */, 2);
   // PORT_TODO: }

   public int a(ft[] var1, int var2) {
      this.hs = var1;
      this.hr.Refresh();
      this.hr.SelectedIndex = (var2);
      this.gU = -1;
      this.StartPosition = FormStartPosition.CenterParent; //(this.DirectoryName);
      this.Show();
      return this.gU;
   }

   public static int a(Container var0, ft[] var1, int var2) {
      if (ht == null) {
         Frame var3 = null;
         ht = new dz(var3);
      }

      return ht.a(var1, var2);
   }
   public static ft[] a(dz var0) {
      return var0.hs;
   }
   public static ListBox b(dz var0) {
      return var0.hr;
   }
   public static void a(dz var0, int var1) {
      var0.gU = var1;
   }
}



}
