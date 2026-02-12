using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class p : Form {
   private DataGridView D;
   private object E;
   private List<object> F;
   private List<object> G = null;
   private static p H = null;

   private p(Frame var1) {
      base(var1);
      this.Size = new Size(aH.cI * 2, aH.cI + aH.cH);
      this.FormBorderStyle = FormBorderStyle.FixedDialog; //(false);
      this.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
      this/* setModal */(true);
      Panel var2 = new Panel();
      this.setContentPane(var2);
      var2.LayoutEnginenew TableLayoutPanel(0, 0));
      Panel var3 = new Panel();
      this.D = new DataGridView();
      this.D.setSelectionMode(2);
      this.D.DataSource = (new q(this));
      this.D.getColumnModel().getColumn(0).setMaxWidth(24);
      this.E = new object(this.D.DataSource);
      this.E.setSortable(0, false);
      this.D.setRowSorter(this.E);
      var3.setViewportView(this.D);
      var2.Add(var3);
      Panel var4 = new Panel();
      var4.LayoutEnginenew FlowLayoutPanel(2));
      var2.Add(var4, "South");
      Button var5 = new Button("Add");
      var5.Click += (new r(this));
      var4.Add(var5);
      this.getRootPane().setDefaultButton(var5);
      Button var6 = new Button("Cancel");
      var6.Click += (new s(this));
      var4.Add(var6);
      this.getRootPane().registerKeyboardAction(new t(this), Keys.getKeyStroke(27, 0), 2);
   }

   private string[] d() {
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
         Frame var1 = MessageBox.getFrameForComponent(var0);
         H = new p(var1);
      }

      H.F = ey.bl();
      H.Text = ("Add Known Technologies");
      return H.d();
   }

   public static string[] c(Container var0) {
      if (H == null) {
         Frame var1 = MessageBox.getFrameForComponent(var0);
         H = new p(var1);
      }

      H.F = ey.bm();
      H.Text = ("Add Known Products");
      return H.d();
   }
   static List<object> a(p var0) {
      return var0.F;
   }
   static DataGridView b(p var0) {
      return var0.D;
   }
   static void a(p var0, List<object> var1) {
      var0.G = var1;
   }
   static List<object> c(p var0) {
      return var0.G;
   }
}

}
