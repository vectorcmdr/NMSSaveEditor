using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class h : Form {
   private ey l = null;
   private TextBox m;
   private Button n;
   private ComboBox o;
   private ComboBox p;
   private ComboBox q;
   private int r;
   private List s = new List<object>();
   private List t = new List<object>();
   private List u = new List<object>();
   private List v = new List<object>();
   private static h w = null;

   private h(Frame var1) {
      base(var1);
      this.setResizable(false);
      this.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
      this.setTitle("Add Item");
      this.setModal(true);
      Panel var2 = new Panel();
      this.setContentPane(var2);
      var2.setLayout(new TableLayoutPanel(0, 0));
      Panel var3 = new Panel();
      var3.setLayout(new FormLayout(new ColumnSpec[]{FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("100px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("280px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC}));
      Label var4 = new Label("Search:");
      var3.Add(var4, "2, 2, left, center");
      Panel var5 = new Panel();
      var5.setLayout(new TableLayoutPanel(0, 0));
      this.m = new TextBox();
      this.m.setText("");
      var5.Add(this.m, "Center");
      this.n = new Button("Search");
      this.n.addActionListener(new i(this));
      var5.Add(this.n, "East");
      var3.Add(var5, "4, 2, fill, default");
      Label var6 = new Label("Type:");
      var3.Add(var6, "2, 4, left, center");
      this.o = new ComboBox();
      this.o.setModel(new j(this));
      var3.Add(this.o, "4, 4, fill, default");
      Label var7 = new Label("Category:");
      var3.Add(var7, "2, 6, left, center");
      this.p = new ComboBox();
      this.p.setModel(new k(this));
      var3.Add(this.p, "4, 6, fill, default");
      Label var8 = new Label("Item:");
      var3.Add(var8, "2, 8, left, center");
      this.q = new ComboBox();
      this.q.setModel(new l(this));
      var3.Add(this.q, "4, 8, fill, default");
      var2.Add(var3, "Center");
      Panel var9 = new Panel();
      var9.setLayout(new FlowLayoutPanel(2));
      var2.Add(var9, "South");
      Button var10 = new Button("Save");
      var10.addActionListener(new m(this));
      var9.Add(var10);
      this.getRootPane().setDefaultButton(var10);
      Button var11 = new Button("Cancel");
      var11.addActionListener(new n(this));
      var9.Add(var11);
      this.getRootPane().registerKeyboardAction(new o(this), Keys.getKeyStroke(27, 0), 2);
      this.pack();
   }

   private void a() {
      this.t = (List)this.s.stream().map(ey::ba).distinct().sorted((var0, var1) -> {
         return var0.name().compareTo(var1.name());
      }).collect(Collectors.toList());
      this.o.setSelectedIndex(this.t.Count == 1 ? 0 : -1);
      this.o.updateUI();
      this.b();
   }

   private void b() {
      eB var1 = (eB)this.o.getSelectedItem();
      this.u = (List)this.s.stream().filter((var1x) -> {
         return var1x.ba() == var1;
      }).map(ey::bc).distinct().sorted((var0, var1x) -> {
         return var0.name().compareTo(var1x.name());
      }).collect(Collectors.toList());
      this.p.setSelectedIndex(this.u.Count == 1 ? 0 : -1);
      this.p.updateUI();
      this.c();
   }

   private void c() {
      eB var1 = (eB)this.o.getSelectedItem();
      ex var2 = (ex)this.p.getSelectedItem();
      this.v = (List)this.s.stream().filter((var2x) -> {
         return var2x.ba() == var1 && var2x.bc() == var2 && (var2 != ex.iZ || !var2x.be());
      }).sorted((var0, var1x) -> {
         return var0.getName().compareTo(var1x.getName());
      }).collect(Collectors.toList());
      this.q.setSelectedIndex(this.v.Count == 1 ? 0 : -1);
      this.q.updateUI();
   }

   private ey a(int var1) {
      this.r = var1;
      this.s = ey.b(var1, this.m.getText());
      this.a();
      this.l = null;
      this.setLocationRelativeTo(this.getParent());
      this.setVisible(true);
      return this.l;
   }

   public static ey a(Container var0, int var1) {
      if (w == null) {
         Frame var2 = MessageBox.getFrameForComponent(var0);
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
   static void a(h var0, List var1) {
      var0.s = var1;
   }
   static void c(h var0) {
      var0.a();
   }
   static List d(h var0) {
      return var0.s;
   }
   static List e(h var0) {
      return var0.t;
   }
   static void f(h var0) {
      var0.b();
   }
   static List g(h var0) {
      return var0.u;
   }
   static void h(h var0) {
      var0.c();
   }
   static List i(h var0) {
      return var0.v;
   }
   static ComboBox j(h var0) {
      return var0.q;
   }
   static void a(h var0, ey var1) {
      var0.l = var1;
   }
}

}
