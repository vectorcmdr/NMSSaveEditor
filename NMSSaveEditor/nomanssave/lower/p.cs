using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class p : Form {
   public DataGridView D;
   public object E;
   public List<object> F;
   public List<object> G = null;
   public static p H = null;

   public p(Form var1) {
      this.Owner = var1;
      this.Size = new Size(aH.cI * 2, aH.cI + aH.cH);
      this.FormBorderStyle = FormBorderStyle.FixedDialog;
      this.StartPosition = FormStartPosition.CenterParent;

      Panel var2 = new Panel();
      var2.Dock = DockStyle.Fill;
      this.Controls.Add(var2);

      Panel var3 = new Panel();
      this.D = new DataGridView();
      this.D.MultiSelect = true;
      this.D.DataSource = new q(this);
      this.E = this.D.DataSource;
      var3.Controls.Add(this.D);
      var2.Controls.Add(var3);

      Panel var4 = new Panel();
      var4.Dock = DockStyle.Bottom;
      var2.Controls.Add(var4);

      Button var5 = new Button() { Text = "Add" };
      var5.Click += new EventHandler((s, e) => new r(this).actionPerformed(null));
      var4.Controls.Add(var5);
      this.AcceptButton = var5;

      Button var6 = new Button() { Text = "Cancel" };
      var6.Click += new EventHandler((s, e) => new s(this).actionPerformed(null));
      var4.Controls.Add(var6);

      this.KeyPreview = true;
      this.KeyDown += (s, e) => {
         if (e.KeyCode == Keys.Escape) {
            new t(this).actionPerformed(null);
         }
      };
   }

   public string[] d() {
      this.D.ClearSelection();
      this.D.Refresh();
      this.G = null;
      this.StartPosition = FormStartPosition.CenterParent;
      this.ShowDialog();
      return this.G == null ? new string[0] : this.G.Cast<string>().ToArray();
   }

   public static string[] b(Control var0) {
      if (H == null) {
         Form var1 = var0.FindForm();
         H = new p(var1);
      }

      H.F = ey.bl();
      H.Text = "Add Known Technologies";
      return H.d();
   }

   public static string[] c(Control var0) {
      if (H == null) {
         Form var1 = var0.FindForm();
         H = new p(var1);
      }

      H.F = ey.bm();
      H.Text = "Add Known Products";
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



}
