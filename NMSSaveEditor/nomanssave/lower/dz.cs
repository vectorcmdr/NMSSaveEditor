using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class dz : Form {
   public ListBox hr;
   public ft[] hs;
   public int gU;
   public static dz ht = null;

   public dz(Frame var1) {
      base(var1);
      this.Size = new Size(300, 400);
      this.FormBorderStyle = FormBorderStyle.FixedDialog; //(false);
      this.setModalExclusionType(/* ModalExclusionType */ 0);
      this.Text = ("Save FileInfo As");
      this/* setModal */(true);
      Panel var2 = new Panel();
      this.setContentPane(var2);
      var2.SuspendLayout(); // TODO: set layout new TableLayoutPanel());
      Panel var3 = new Panel();
      this.hr = new ListBox();
      this.hr.setSelectionMode(0);
      this.hr.DataSource = (new dA(this));
      var3.setViewportView(this.hr);
      var2.Add(var3);
      Panel var4 = new Panel();
      var4.SuspendLayout(); // TODO: set layout new FlowLayoutPanel(2));
      var2.Controls.Add(var4);
      Button var5 = new Button() { Text = "Replace/Save" };
      var5.Click += (new dB(this));
      var4.Add(var5);
      this.getRootPane().setDefaultButton(var5);
      Button var6 = new Button() { Text = "Cancel" };
      var6.Click += (new dC(this));
      var4.Add(var6);
      this.getRootPane().registerKeyboardAction(new dD(this), /* KeyStroke */ Keys.None /* (27, 0) */, 2);
   }

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


#else

public class dz
{
   public dz() { }
   public dz(params object[] args) { }
   public static dz ht = default;
   public ListBox hr = default;
   public ft[] hs = System.Array.Empty<ft>();
   public int gU = 0;
   public int a(ft[] var1, int var2) { return 0; }
   public static ListBox b(dz var0) { return default; }
}

#endif

}
