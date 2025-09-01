package nomanssave;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Dialog.ModalExclusionType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class h extends JDialog {
   private ey l = null;
   private JTextField m;
   private JButton n;
   private JComboBox o;
   private JComboBox p;
   private JComboBox q;
   private int r;
   private List s = new ArrayList();
   private List t = new ArrayList();
   private List u = new ArrayList();
   private List v = new ArrayList();
   private static h w = null;

   private h(Frame var1) {
      super(var1);
      this.setResizable(false);
      this.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
      this.setTitle("Add Item");
      this.setModal(true);
      JPanel var2 = new JPanel();
      this.setContentPane(var2);
      var2.setLayout(new BorderLayout(0, 0));
      JPanel var3 = new JPanel();
      var3.setLayout(new FormLayout(new ColumnSpec[]{FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("100px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("280px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC}));
      JLabel var4 = new JLabel("Search:");
      var3.add(var4, "2, 2, left, center");
      JPanel var5 = new JPanel();
      var5.setLayout(new BorderLayout(0, 0));
      this.m = new JTextField();
      this.m.setText("");
      var5.add(this.m, "Center");
      this.n = new JButton("Search");
      this.n.addActionListener(new i(this));
      var5.add(this.n, "East");
      var3.add(var5, "4, 2, fill, default");
      JLabel var6 = new JLabel("Type:");
      var3.add(var6, "2, 4, left, center");
      this.o = new JComboBox();
      this.o.setModel(new j(this));
      var3.add(this.o, "4, 4, fill, default");
      JLabel var7 = new JLabel("Category:");
      var3.add(var7, "2, 6, left, center");
      this.p = new JComboBox();
      this.p.setModel(new k(this));
      var3.add(this.p, "4, 6, fill, default");
      JLabel var8 = new JLabel("Item:");
      var3.add(var8, "2, 8, left, center");
      this.q = new JComboBox();
      this.q.setModel(new l(this));
      var3.add(this.q, "4, 8, fill, default");
      var2.add(var3, "Center");
      JPanel var9 = new JPanel();
      var9.setLayout(new FlowLayout(2));
      var2.add(var9, "South");
      JButton var10 = new JButton("Save");
      var10.addActionListener(new m(this));
      var9.add(var10);
      this.getRootPane().setDefaultButton(var10);
      JButton var11 = new JButton("Cancel");
      var11.addActionListener(new n(this));
      var9.add(var11);
      this.getRootPane().registerKeyboardAction(new o(this), KeyStroke.getKeyStroke(27, 0), 2);
      this.pack();
   }

   private void a() {
      this.t = (List)this.s.stream().map(ey::ba).distinct().sorted((var0, var1) -> {
         return var0.name().compareTo(var1.name());
      }).collect(Collectors.toList());
      this.o.setSelectedIndex(this.t.size() == 1 ? 0 : -1);
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
      this.p.setSelectedIndex(this.u.size() == 1 ? 0 : -1);
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
      this.q.setSelectedIndex(this.v.size() == 1 ? 0 : -1);
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
         Frame var2 = JOptionPane.getFrameForComponent(var0);
         w = new h(var2);
      }

      return w.a(var1);
   }

   // $FF: synthetic method
   static JTextField a(h var0) {
      return var0.m;
   }

   // $FF: synthetic method
   static int b(h var0) {
      return var0.r;
   }

   // $FF: synthetic method
   static void a(h var0, List var1) {
      var0.s = var1;
   }

   // $FF: synthetic method
   static void c(h var0) {
      var0.a();
   }

   // $FF: synthetic method
   static List d(h var0) {
      return var0.s;
   }

   // $FF: synthetic method
   static List e(h var0) {
      return var0.t;
   }

   // $FF: synthetic method
   static void f(h var0) {
      var0.b();
   }

   // $FF: synthetic method
   static List g(h var0) {
      return var0.u;
   }

   // $FF: synthetic method
   static void h(h var0) {
      var0.c();
   }

   // $FF: synthetic method
   static List i(h var0) {
      return var0.v;
   }

   // $FF: synthetic method
   static JComboBox j(h var0) {
      return var0.q;
   }

   // $FF: synthetic method
   static void a(h var0, ey var1) {
      var0.l = var1;
   }
}
