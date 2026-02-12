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

public class h : Form {
   public ey l = null;
   public TextBox m;
   public Button n;
   public ComboBox o;
   public ComboBox p;
   public ComboBox q;
   public int r;
   public List<object> s = new List<object>();
   public List<object> t = new List<object>();
   public List<object> u = new List<object>();
   public List<object> v = new List<object>();
   public static h w = null;

   public h(Form var1) {
      base(var1);
      this.SetResizable(false);
      this.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
      this.SetTitle("Add Item");
      this.SetModal(true);
      Panel var2 = new Panel();
      this.SetContentPane(var2);
      var2.SetLayout(new BorderLayout(0, 0));
      Panel var3 = new Panel();
      var3.SetLayout(new FormLayout(new ColumnSpec[]{FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("100px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("280px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC}));
      Label var4 = new Label("Search:");
      var3.Add(var4, "2, 2, left, center");
      Panel var5 = new Panel();
      var5.SetLayout(new BorderLayout(0, 0));
      this.m = new TextBox();
      this.m.SetText("");
      var5.Add(this.m, "Center");
      this.n = new Button("Search");
      this.n.AddActionListener(new i(this));
      var5.Add(this.n, "East");
      var3.Add(var5, "4, 2, fill, default");
      Label var6 = new Label("Type:");
      var3.Add(var6, "2, 4, left, center");
      this.o = new ComboBox();
      this.o.SetModel(new j(this));
      var3.Add(this.o, "4, 4, fill, default");
      Label var7 = new Label("Category:");
      var3.Add(var7, "2, 6, left, center");
      this.p = new ComboBox();
      this.p.SetModel(new k(this));
      var3.Add(this.p, "4, 6, fill, default");
      Label var8 = new Label("Item:");
      var3.Add(var8, "2, 8, left, center");
      this.q = new ComboBox();
      this.q.SetModel(new l(this));
      var3.Add(this.q, "4, 8, fill, default");
      var2.Add(var3, "Center");
      Panel var9 = new Panel();
      var9.SetLayout(new FlowLayout(2));
      var2.Add(var9, "South");
      Button var10 = new Button("Save");
      var10.AddActionListener(new m(this));
      var9.Add(var10);
      this.GetRootPane().setDefaultButton(var10);
      Button var11 = new Button("Cancel");
      var11.AddActionListener(new n(this));
      var9.Add(var11);
      this.GetRootPane().registerKeyboardAction(new o(this), Keys.getKeyStroke(27, 0), 2);
      this.Pack();
   }

   public void a() {
      this.t = (List)this.s.map(ey.ba).distinct().sorted((var0, var1) => {
         return var0.ToString().CompareTo(var1.ToString());
      }).ToList();
      this.o.SetSelectedIndex(this.t.Count == 1 ? 0 : -1);
      this.o.updateUI();
      this.b();
   }

   public void b() {
      eB var1 = (eB)this.o.SelectedItem;
      this.u = (List)this.s.filter((var1x) => {
         return var1x.ba() == var1;
      }).map(ey.bc).distinct().sorted((var0, var1x) => {
         return var0.ToString().CompareTo(var1x.ToString());
      }).ToList();
      this.p.SetSelectedIndex(this.u.Count == 1 ? 0 : -1);
      this.p.updateUI();
      this.c();
   }

   public void c() {
      eB var1 = (eB)this.o.SelectedItem;
      ex var2 = (ex)this.p.SelectedItem;
      this.v = (List)this.s.filter((var2x) => {
         return var2x.ba() == var1 && var2x.bc() == var2 && (var2 != ex.iZ || !var2x.be());
      }).sorted((var0, var1x) => {
         return var0.Name.CompareTo(var1x.Name);
      }).ToList();
      this.q.SetSelectedIndex(this.v.Count == 1 ? 0 : -1);
      this.q.updateUI();
   }

   public ey a(int var1) {
      this.r = var1;
      this.s = ey.b(var1, this.m.GetText());
      this.a();
      this.l = null;
      this.SetLocationRelativeTo(this.Parent);
      this.SetVisible(true);
      return this.l;
   }

   public static ey a(Container var0, int var1) {
      if (w == null) {
         Form var2 = JOptionPane.getFrameForComponent(var0);
         w = new h(var2);
      }

      return w.a(var1);
   }

   // $FF: synthetic method
   static TextBox a(h var0) {
      return var0.m;
   }

   // $FF: synthetic method
   static int b(h var0) {
      return var0.r;
   }

   // $FF: synthetic method
   static void a(h var0, List<object> var1) {
      var0.s = var1;
   }

   // $FF: synthetic method
   static void c(h var0) {
      var0.a();
   }

   // $FF: synthetic method
   static List<object> d(h var0) {
      return var0.s;
   }

   // $FF: synthetic method
   static List<object> e(h var0) {
      return var0.t;
   }

   // $FF: synthetic method
   static void f(h var0) {
      var0.b();
   }

   // $FF: synthetic method
   static List<object> g(h var0) {
      return var0.u;
   }

   // $FF: synthetic method
   static void h_static(h var0) {
      var0.c();
   }

   // $FF: synthetic method
   static List<object> i(h var0) {
      return var0.v;
   }

   // $FF: synthetic method
   static ComboBox j(h var0) {
      return var0.q;
   }

   // $FF: synthetic method
   static void a(h var0, ey var1) {
      var0.l = var1;
   }
}

}
