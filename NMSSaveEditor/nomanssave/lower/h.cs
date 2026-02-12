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

// PORT_TODO: public h(Frame var1) : base(var1) {
      // PORT_TODO: this.FormBorderStyle = FormBorderStyle.FixedDialog; //(false);
      // setModalExclusionType not available in WinForms
      // PORT_TODO: this.Text = ("Add Item");
      // PORT_TODO: // PORT_TODO: this/* setModal */(true);
      // PORT_TODO: Panel var2 = new Panel();
      // PORT_TODO: this.setContentPane(var2);
      // PORT_TODO: var2.SuspendLayout(); // TODO: set layout new TableLayoutPanel());
      // PORT_TODO: Panel var3 = new Panel();
      // TODO: var3.SuspendLayout(); // TODO: set layout /* FormLayout */ null, FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("280px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC}));
      // PORT_TODO: Label var4 = new Label() { Text = "Search:" };
      // PORT_TODO: var3.Controls.Controls.Add(var4);
      // PORT_TODO: Panel var5 = new Panel();
      // PORT_TODO: var5.SuspendLayout(); // TODO: set layout new TableLayoutPanel());
      // PORT_TODO: this.m = new TextBox();
      // PORT_TODO: this.m.Text = ("");
      // PORT_TODO: var5.Add(this.m);
      // PORT_TODO: this.n = new Button() { Text = "Search" };
      // PORT_TODO: this.n.Click += (new i(this));
      // PORT_TODO: var5.Add(this.n, "East");
      // PORT_TODO: var3.Controls.Add(var5);
      // PORT_TODO: Label var6 = new Label() { Text = "Type:" };
      // PORT_TODO: var3.Controls.Add(var6);
      // PORT_TODO: this.o = new ComboBox();
      // PORT_TODO: this.o.DataSource = (new j(this));
      // PORT_TODO: var3.Add(this.o, "4, 4, fill, default");
      // PORT_TODO: Label var7 = new Label() { Text = "Category:" };
      // PORT_TODO: var3.Controls.Add(var7);
      // PORT_TODO: this.p = new ComboBox();
      // PORT_TODO: this.p.DataSource = (new k(this));
      // PORT_TODO: var3.Add(this.p, "4, 6, fill, default");
      // PORT_TODO: Label var8 = new Label() { Text = "Item:" };
      // PORT_TODO: var3.Controls.Add(var8);
      // PORT_TODO: this.q = new ComboBox();
      // PORT_TODO: this.q.DataSource = (new l(this));
      // PORT_TODO: var3.Add(this.q, "4, 8, fill, default");
      // PORT_TODO: var2.Controls.Add(var3);
      // PORT_TODO: Panel var9 = new Panel();
      // PORT_TODO: var9.SuspendLayout(); // TODO: set layout new FlowLayoutPanel(2));
      // PORT_TODO: var2.Controls.Add(var9);
      // PORT_TODO: Button var10 = new Button() { Text = "Save" };
      // PORT_TODO: var10.Click += (new m(this));
      // PORT_TODO: var9.Add(var10);
      // PORT_TODO: this.getRootPane().setDefaultButton(var10);
      // PORT_TODO: Button var11 = new Button() { Text = "Cancel" };
      // PORT_TODO: var11.Click += (new n(this));
      // PORT_TODO: var9.Add(var11);
      // PORT_TODO: this.getRootPane().registerKeyboardAction(new o(this), /* KeyStroke */ Keys.None /* (27, 0) */, 2);
      // PORT_TODO: this.PerformLayout();
   // PORT_TODO: }

   public void a() {
      // PORT_TODO: this.t = (List<object>)this.s.stream().map(ey.ba).distinct().sorted((var0, var1) => {
         // PORT_TODO: return var0.Name.CompareTo(var1.Name);
      // PORT_TODO: }).collect(Collectors.toList());
      // PORT_TODO: this.o.SelectedIndex = (this.t.Count == 1 ? 0 : -1);
      // PORT_TODO: this.o.Refresh();
      // PORT_TODO: this.b();
   }

   public void b() {
      eB var1 = (eB)this.o.SelectedItem;
      this.u = (List<object>)this.s.stream().filter((var1x) => {
         return var1x.ba() == var1;
      // PORT_TODO: }).map(ey.bc).distinct().sorted((var0, var1x) => {
         return var0.Name.CompareTo(var1x.Name);
      }).collect(Collectors.toList());
      this.p.SelectedIndex = (this.u.Count == 1 ? 0 : -1);
      this.p.Refresh();
      this.c();
   }

   public void c() {
      eB var1 = (eB)this.o.SelectedItem;
      ex var2 = (ex)this.p.SelectedItem;
      // PORT_TODO: this.v = (List<object>)this.s.stream().filter((var2x) => {
         // PORT_TODO: return var2x.ba() == var1 && var2x.bc() == var2 && (var2 != ex.iZ || !var2x.be());
      // PORT_TODO: }).sorted((var0, var1x) => {
         // PORT_TODO: return var0.Name.CompareTo(var1x.Name);
      // PORT_TODO: }).collect(Collectors.toList());
      // PORT_TODO: this.q.SelectedIndex = (this.v.Count == 1 ? 0 : -1);
      // PORT_TODO: this.q.Refresh();
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
