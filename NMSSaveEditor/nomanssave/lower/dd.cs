using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class dd : Form {
   public ListBox gS;
   public List<object> gT;
   public int gU;
   public static dd gV = null;

public dd(Frame var1) : base(var1) {
      this.Size = new Size(300, 300);
      this.FormBorderStyle = FormBorderStyle.FixedDialog; //(false);
      // setModalExclusionType not available in WinForms
      this.Text = ("Move Item");
      // PORT_TODO: // PORT_TODO: this/* setModal */(true);
      Panel var2 = new Panel();
      this.setContentPane(var2);
      var2.SuspendLayout(); // TODO: set layout new TableLayoutPanel());
      Panel var3 = new Panel();
      this.gS = new ListBox();
      this.gS.setSelectionMode(0);
      this.gS.DataSource = (new de(this));
      this.// gS.addMouseListener - use MouseClick event instead
      var3.setViewportView(this.gS);
      var2.Add(var3);
      Panel var4 = new Panel();
      var4.SuspendLayout(); // TODO: set layout new FlowLayoutPanel(2));
      var2.Controls.Add(var4);
      Button var5 = new Button() { Text = "Move" };
      var5.Click += (new dg(this));
      var4.Add(var5);
      this.getRootPane().setDefaultButton(var5);
      Button var6 = new Button() { Text = "Cancel" };
      var6.Click += (new dh(this));
      var4.Add(var6);
      this.getRootPane().registerKeyboardAction(new di(this), /* KeyStroke */ Keys.None /* (27, 0) */, 2);
   }

   public int a(List<object> var1, int var2) {
      this.gT = var1;
      this.gS.Refresh();
      this.gS.SelectedIndex = (this.gU);
      this.gU = var2;
      this.StartPosition = FormStartPosition.CenterParent; //(this.DirectoryName);
      this.Show();
      return this.gU;
   }

   public static int a(Container var0, List<object> var1, int var2) {
      if (gV == null) {
         Frame var3 = null;
         gV = new dd(var3);
      }

      return gV.a(var1, var2);
   }
   public static List<object> a(dd var0) {
      return var0.gT;
   }
   public static ListBox b(dd var0) {
      return var0.gS;
   }
   public static void a(dd var0, int var1) {
      var0.gU = var1;
   }
}



}
