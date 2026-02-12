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

// PORT_TODO: public dd(Frame var1) : base(var1) {
      // PORT_TODO: this.Size = new Size(300, 300);
      // PORT_TODO: this.FormBorderStyle = FormBorderStyle.FixedDialog; //(false);
      // setModalExclusionType not available in WinForms
      // PORT_TODO: this.Text = ("Move Item");
      // PORT_TODO: // PORT_TODO: this/* setModal */(true);
      // PORT_TODO: Panel var2 = new Panel();
      // PORT_TODO: this.setContentPane(var2);
      // PORT_TODO: var2.SuspendLayout(); // TODO: set layout new TableLayoutPanel());
      // PORT_TODO: Panel var3 = new Panel();
      // PORT_TODO: this.gS = new ListBox();
      // PORT_TODO: this.gS.setSelectionMode(0);
      // PORT_TODO: this.gS.DataSource = (new de(this));
      // this.gS.addMouseListener - use MouseClick event instead
      // PORT_TODO: var3.setViewportView(this.gS);
      // PORT_TODO: var2.Add(var3);
      // PORT_TODO: Panel var4 = new Panel();
      // PORT_TODO: var4.SuspendLayout(); // TODO: set layout new FlowLayoutPanel(2));
      // PORT_TODO: var2.Controls.Add(var4);
      // PORT_TODO: Button var5 = new Button() { Text = "Move" };
      // PORT_TODO: var5.Click += (new dg(this));
      // PORT_TODO: var4.Add(var5);
      // PORT_TODO: this.getRootPane().setDefaultButton(var5);
      // PORT_TODO: Button var6 = new Button() { Text = "Cancel" };
      // PORT_TODO: var6.Click += (new dh(this));
      // PORT_TODO: var4.Add(var6);
      // PORT_TODO: this.getRootPane().registerKeyboardAction(new di(this), /* KeyStroke */ Keys.None /* (27, 0) */, 2);
   // PORT_TODO: }

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
