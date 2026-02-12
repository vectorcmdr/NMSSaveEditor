using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class p : Form {
   public DataGridView D;
   public object E;
   public List<object> F;
   public List<object> G = null;
   public static p H = null;

   public p(Frame var1) {
      base(var1);
      this.Size = new Size(aH.cI * 2, aH.cI + aH.cH);
      this.FormBorderStyle = FormBorderStyle.FixedDialog; //(false);
      this.setModalExclusionType(/* ModalExclusionType */ 0);
      this/* setModal */(true);
      Panel var2 = new Panel();
      this.setContentPane(var2);
      var2.SuspendLayout(); // TODO: set layout new TableLayoutPanel());
      Panel var3 = new Panel();
      this.D = new DataGridView();
      this.D.setSelectionMode(2);
      this.D.DataSource = (new q(this));
      this.D.getColumnModel().getColumn(0).setMaxWidth(24);
      this.E = ((object)(this.D.DataSource));
      this.E.setSortable(0, false);
      this.D.setRowSorter(this.E);
      var3.setViewportView(this.D);
      var2.Add(var3);
      Panel var4 = new Panel();
      var4.SuspendLayout(); // TODO: set layout new FlowLayoutPanel(2));
      var2.Controls.Add(var4);
      Button var5 = new Button() { Text = "Add" };
      var5.Click += (new r(this));
      var4.Add(var5);
      this.getRootPane().setDefaultButton(var5);
      Button var6 = new Button() { Text = "Cancel" };
      var6.Click += (new s(this));
      var4.Add(var6);
      this.getRootPane().registerKeyboardAction(new t(this), /* KeyStroke */ Keys.None /* (27, 0) */, 2);
   }

   public string[] d() {
      this.D.clearSelection();
      this.E.setSortKeys(new List<object>());
      this.E.sort();
      this.D.Refresh();
      this.G = null;
      this.StartPosition = FormStartPosition.CenterParent; //(this.DirectoryName);
      this.Show();
      return this.G == null ? new string[0] : (string[])this.G.ToArray(new string[0]);
   }

   public static string[] b(Container var0) {
      if (H == null) {
         Frame var1 = null;
         H = new p(var1);
      }

      H.F = ey.bl();
      H.Text = ("Add Known Technologies");
      return H.d();
   }

   public static string[] c(Container var0) {
      if (H == null) {
         Frame var1 = null;
         H = new p(var1);
      }

      H.F = ey.bm();
      H.Text = ("Add Known Products");
      return H.d();
   }
   public static List<object> a(p var0) {
      return var0.F;
   }
   public static DataGridView b(p var0) {
      return var0.D;
   }
   public static void a(p var0, List<object> var1) {
      var0.G = var1;
   }
   public static List<object> c(p var0) {
      return var0.G;
   }
}


#else

public class p
{
   public p() { }
   public p(params object[] args) { }
   public static p H = default;
   public DataGridView D = default;
   public object E = default;
   public List<object> F = default;
   public List<object> G = default;
   public string[] d() { return System.Array.Empty<string>(); }
   public static string[] b(Container var0) { return System.Array.Empty<string>(); }
   public static string[] c(Container var0) { return System.Array.Empty<string>(); }
   public static List<object> a(p var0) { return default; }
}

#endif

}
