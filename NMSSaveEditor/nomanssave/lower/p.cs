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

public class p : Form {
   public DataGridView D;
   public TableRowSorter E;
   public List<object> F;
   public List<object> G = null;
   public static p H = null;

   public p(Form var1) {
      // base(var1);
      this.SetSize(aH.cI * 2, aH.cI + aH.cH);
      this.SetResizable(false);
      this.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
      this.SetModal(true);
      Panel var2 = new Panel();
      this.SetContentPane(var2);
      var2.SetLayout(new BorderLayout(0, 0));
      Panel var3 = new Panel();
      this.D = new DataGridView();
      this.D.SetSelectionMode(2);
      this.D.SetModel(new q(this));
      this.D.GetColumnModel().getColumn(0).setMaxWidth(24);
      this.E = new TableRowSorter(this.D.GetModel());
      this.E.setSortable(0, false);
      this.D.setRowSorter(this.E);
      var3.setViewportView(this.D);
      var2.Add(var3);
      Panel var4 = new Panel();
      var4.SetLayout(new FlowLayout(2));
      var2.Add(var4, "South");
      Button var5 = new Button("Add");
      var5.AddActionListener(new r(this));
      var4.Add(var5);
      this.GetRootPane().setDefaultButton(var5);
      Button var6 = new Button("Cancel");
      var6.AddActionListener(new s(this));
      var4.Add(var6);
      this.GetRootPane().registerKeyboardAction(new t(this), null, 2);
   }

   public string[] d() {
      this.D.clearSelection();
      this.E.setSortKeys(new List<object>());
      this.E.sort();
      this.D.updateUI();
      this.G = null;
      this.SetLocationRelativeTo(this.Parent);
      this.SetVisible(true);
      return this.G == null ? new string[0] : (string[])this.G.ToArray(new string[0]);
   }

   public static string[] b(Container var0) {
      if (H == null) {
         Form var1 = JOptionPane.getFrameForComponent(var0);
         H = new p(var1);
      }
       H.F = ey.bl();
      H.SetTitle("Add Known Technologies");
      return H.d();
   }

   public static string[] c(Container var0) {
      if (H == null) {
         Form var1 = JOptionPane.getFrameForComponent(var0);
         H = new p(var1);
      }
       H.F = ey.bm();
      H.SetTitle("Add Known Products");
      return H.d();
   }

   // $FF: synthetic method
   public static List<object> a(p var0) {
      return var0.F;
   }

   // $FF: synthetic method
   public static DataGridView b(p var0) {
      return var0.D;
   }

   // $FF: synthetic method
   public static void a(p var0, List<object> var1) {
      var0.G = var1;
   }

   // $FF: synthetic method
   public static List<object> c(p var0) {
      return var0.G;
   }
}

}
