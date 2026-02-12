using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

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

public h(Frame var1) : base(var1) {
      this.FormBorderStyle = FormBorderStyle.FixedDialog; //(false);
      // setModalExclusionType not available in WinForms
      this.Text = ("Add Item");
      this/* setModal */(true);
      Panel var2 = new Panel();
      this.setContentPane(var2);
      var2.SuspendLayout(); // TODO: set layout new TableLayoutPanel());
      Panel var3 = new Panel();
      // TODO: var3.SuspendLayout(); // TODO: set layout /* FormLayout */ null, FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("280px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC}));
      Label var4 = new Label() { Text = "Search:" };
      var3.Controls.Controls.Add(var4);
      Panel var5 = new Panel();
      var5.SuspendLayout(); // TODO: set layout new TableLayoutPanel());
      this.m = new TextBox();
      this.m.Text = ("");
      var5.Add(this.m);
      this.n = new Button() { Text = "Search" };
      this.n.Click += (new i(this));
      var5.Add(this.n, "East");
      var3.Controls.Add(var5);
      Label var6 = new Label() { Text = "Type:" };
      var3.Controls.Add(var6);
      this.o = new ComboBox();
      this.o.DataSource = (new j(this));
      var3.Add(this.o, "4, 4, fill, default");
      Label var7 = new Label() { Text = "Category:" };
      var3.Controls.Add(var7);
      this.p = new ComboBox();
      this.p.DataSource = (new k(this));
      var3.Add(this.p, "4, 6, fill, default");
      Label var8 = new Label() { Text = "Item:" };
      var3.Controls.Add(var8);
      this.q = new ComboBox();
      this.q.DataSource = (new l(this));
      var3.Add(this.q, "4, 8, fill, default");
      var2.Controls.Add(var3);
      Panel var9 = new Panel();
      var9.SuspendLayout(); // TODO: set layout new FlowLayoutPanel(2));
      var2.Controls.Add(var9);
      Button var10 = new Button() { Text = "Save" };
      var10.Click += (new m(this));
      var9.Add(var10);
      this.getRootPane().setDefaultButton(var10);
      Button var11 = new Button() { Text = "Cancel" };
      var11.Click += (new n(this));
      var9.Add(var11);
      this.getRootPane().registerKeyboardAction(new o(this), /* KeyStroke */ Keys.None /* (27, 0) */, 2);
      this.PerformLayout();
   }

   public void a() {
      this.t = (List<object>)this.s.stream().map(ey.ba).distinct().sorted((var0, var1) => {
         return var0.Name.CompareTo(var1.Name);
      }).collect(Collectors.toList());
      this.o.SelectedIndex = (this.t.Count == 1 ? 0 : -1);
      this.o.Refresh();
      this.b();
   }

   public void b() {
      eB var1 = (eB)this.o.SelectedItem;
      this.u = (List<object>)this.s.stream().filter((var1x) => {
         return var1x.ba() == var1;
      }).map(ey.bc).distinct().sorted((var0, var1x) => {
         return var0.Name.CompareTo(var1x.Name);
      }).collect(Collectors.toList());
      this.p.SelectedIndex = (this.u.Count == 1 ? 0 : -1);
      this.p.Refresh();
      this.c();
   }

   public void c() {
      eB var1 = (eB)this.o.SelectedItem;
      ex var2 = (ex)this.p.SelectedItem;
      this.v = (List<object>)this.s.stream().filter((var2x) => {
         return var2x.ba() == var1 && var2x.bc() == var2 && (var2 != ex.iZ || !var2x.be());
      }).sorted((var0, var1x) => {
         return var0.Name.CompareTo(var1x.Name);
      }).collect(Collectors.toList());
      this.q.SelectedIndex = (this.v.Count == 1 ? 0 : -1);
      this.q.Refresh();
   }

   public ey a(int var1) {
      this.r = var1;
      this.s = ey.b(var1, this.m.Text);
      this.a();
      this.l = null;
      this.StartPosition = FormStartPosition.CenterParent; //(this.DirectoryName);
      this.Show();
      return this.l;
   }

   public static ey a(Container var0, int var1) {
      if (w == null) {
         Frame var2 = null;
         w = new h(var2);
      }

      return w.a(var1);
   }
   static TextBox a(h var0) {
      return var0.m;
   }
   static int b(h var0) {
      return var0.r;
   }
   static void a(h var0, List<object> var1) {
      var0.s = var1;
   }
   static void c(h var0) {
      var0.a();
   }
   public static List<object> d(h var0) {
      return var0.s;
   }
   public static List<object> e(h var0) {
      return var0.t;
   }
   public static void f(h var0) {
      var0.b();
   }
   public static List<object> g(h var0) {
      return var0.u;
   }
   static void h_impl(h var0) {
      var0.c();
   }
   public static List<object> i(h var0) {
      return var0.v;
   }
   public static ComboBox j(h var0) {
      return var0.q;
   }
   static void a(h var0, ey var1) {
      var0.l = var1;
   }
}



}
