using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class ap : Panel {
   private DataGridView ci;
   private object cj;
   private DataGridView ck;
   private object cl;
   private readonly CheckBox[] cm;
   private DataGridView cn;
   private object co;
   private gz cp;
   private eV cq;
   private eV cr;
   private eV cs;
   private List<object> ct = new List<object>();

   ap(Application var1) {
      TableLayoutPanel var2 = new TableLayoutPanel(2, 2);
      this.SuspendLayout(); // TODO: set layout var2);
      Panel var3 = new Panel();
      this.Add(var3);
      // TODO: var3.SuspendLayout(); // TODO: set layout /* FormLayout */ null, FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("200px:grow"), FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC}));
      Label var4 = new Label("Known Technology");
      // TODO: var4.putClientProperty(...);
      var3.Controls.Add(var4);
      Panel var5 = new Panel();
      var3.Controls.Add(var5);
      this.ci = new DataGridView();
      this.ci.setSelectionMode(2);
      this.ci.DataSource = (new aq(this));
      this.ci.getColumnModel().getColumn(0).setMaxWidth(24);
      this.ci.getColumnModel().getColumn(0).setCellRenderer(new aB(this, (aB)null));
      this.cj = new object(this.ci.DataSource);
      this.cj.setSortable(0, false);
      this.ci.setRowSorter(this.cj);
      var5.setViewportView(this.ci);
      Panel var6 = new Panel();
      var6.SuspendLayout(); // TODO: set layout new FlowLayoutPanel());
      var3.Controls.Add(var6);
      Button var7 = new Button();
      var7.Text = ("Add Technology");
      var7.Click += (new @as(this));
      var6.Add(var7);
      Button var8 = new Button();
      var8.Text = ("Remove Selected");
      var8.Click += (new at(this));
      var6.Add(var8);
      Panel var9 = new Panel();
      this.Add(var9);
      // TODO: var9.SuspendLayout(); // TODO: set layout /* FormLayout */ null, FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("200px:grow"), FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC}));
      var4 = new Label("Known Products");
      // TODO: var4.putClientProperty(...);
      var9.Controls.Add(var4);
      Panel var10 = new Panel();
      var9.Controls.Add(var10);
      this.ck = new DataGridView();
      this.ck.setSelectionMode(2);
      this.ck.DataSource = (new au(this));
      this.ck.getColumnModel().getColumn(0).setMaxWidth(24);
      this.ck.getColumnModel().getColumn(0).setCellRenderer(new aB(this, (aB)null));
      this.cl = new object(this.ck.DataSource);
      this.cl.setSortable(0, false);
      this.ck.setRowSorter(this.cl);
      var10.setViewportView(this.ck);
      var6 = new Panel();
      var6.SuspendLayout(); // TODO: set layout new FlowLayoutPanel());
      var9.Controls.Add(var6);
      Button var11 = new Button();
      var11.Text = ("Add Product");
      var11.Click += (new av(this));
      var6.Add(var11);
      Button var12 = new Button();
      var12.Text = ("Remove Selected");
      var12.Click += (new aw(this));
      var6.Add(var12);
      Panel var13 = new Panel();
      this.Add(var13);
      // TODO: var13.SuspendLayout(); // TODO: set layout /* FormLayout */ null, FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("200px:grow"), FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC}));
      var4 = new Label("Known Words");
      // TODO: var4.putClientProperty(...);
      var13.Controls.Add(var4);
      Panel var14 = new Panel();
      var13.Controls.Add(var14);
      this.cn = new DataGridView();
      this.cn.setCellSelectionEnabled(false);
      this.cn.getColumnModel().setColumnMargin(2);
      this.cn.DataSource = (new ax(this));
      this.cn.getTableHeader().getColumnModel().getColumn(0).setHeaderRenderer(new aA(this.cn, 2));
      this.cn.getTableHeader().getColumnModel().getColumn(1).setHeaderRenderer(new aA(this.cn, 2));
      CheckBox var15 = new CheckBox();
      var15.setHorizontalAlignment(0);

      int var16;
      for(var16 = 2; var16 < this.cn.getColumnCount(); ++var16) {
         this.cn.getColumnModel().getColumn(var16).setMaxWidth(80);
         this.cn.getTableHeader().getColumnModel().getColumn(var16).setHeaderRenderer(new aA(this.cn, 0));
         this.cn.getColumnModel().getColumn(var16).setCellEditor(new DefaultCellEditor(var15));
         this.cn.getColumnModel().getColumn(var16).setCellRenderer(new aC());
      }

      this.co = new object(this.cn.DataSource);

      for(var16 = 2; var16 < this.cn.DataSource.getColumnCount(); ++var16) {
         this.co.setSortable(var16, false);
      }

      this.cn.setRowSorter(this.co);
      var14.setViewportView(this.cn);
      var6 = new Panel();
      var6.SuspendLayout(); // TODO: set layout new FlowLayoutPanel());
      var13.Controls.Add(var6);
      Button var24 = new Button();
      var24.Text = ("Learn All");
      var24.Click += (new ay(this));
      var6.Add(var24);
      Button var17 = new Button();
      var17.Text = ("Unlearn All");
      var17.Click += (new az(this));
      var6.Add(var17);
      Panel var18 = new Panel();
      this.Add(var18);
      // TODO: var18.SuspendLayout(); // TODO: set layout /* FormLayout */ null, FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC}));
      var4 = new Label("Known Glyphs");
      // TODO: var4.putClientProperty(...);
      var18.Controls.Add(var4);
      Panel var19 = new Panel();
      // TODO: var19.putClientProperty(...);
      var18.Controls.Add(var19);
      // TODO: var19.SuspendLayout(); // TODO: set layout /* FormLayout */ null);
      this.cm = new CheckBox[16];

      for(int var20 = 0; var20 < 16; ++var20) {
         int var21 = var20 % 4 * 3 + 2;
         int var22 = var20 / 4 * 2 + 2;
         this.cm[var20] = new CheckBox();
         this.cm[var20].setBackground(var19.getBackground());
         this.cm[var20].Click += (new ar(this));
         Image var23 = Application.a("UI-GLYPH" + (var20 + 1) + ".PNG");
         if (var23 == null) {
            var4 = new Label(Integer.toString(var20 + 1));
         } else {
            var4 = new Label(var23);
         }

         var19.Add(this.cm[var20], var21 + ", " + var22);
         var19.Add(var4, var21 + 1 + ", " + var22);
      }

   }

   private void R() {
      if (this.cp != null) {
         int var1 = 0;

         for(int var2 = 0; var2 < 16; ++var2) {
            if (this.cm[var2].Checked) {
               var1 |= 1 << var2;
            }
         }

         this.cp.aE(var1);
      }
   }

   public void a(gz var1) {
      this.cp = var1;
      this.cq = var1 == null ? null : var1.dQ();
      this.cr = var1 == null ? null : var1.dR();
      this.cs = var1 == null ? null : var1.dS();
      this.ct = new List<object>();
      int var2;
      string var3;
      if (this.cr != null) {
         for(var2 = 0; var2 < this.cr.Count; ++var2) {
            var3 = this.cr.X(var2);
            if (!this.ct.Contains(var3)) {
               this.ct.Add(var3);
            }
         }
      }

      if (this.cs != null) {
         for(var2 = 0; var2 < this.cs.Count; ++var2) {
            var3 = this.cs.X(var2);
            if (!this.ct.Contains(var3)) {
               this.ct.Add(var3);
            }
         }
      }

      this.ci.clearSelection();
      this.cj.allRowsChanged();
      this.ci.Refresh();
      this.ck.clearSelection();
      this.cl.allRowsChanged();
      this.ck.Refresh();
      var2 = var1 == null ? 0 : var1.dP();

      for(int var5 = 0; var5 < 16; ++var5) {
         int var4 = 1 << var5;
         this.cm[var5].Checked = ((var2 & var4) == var4);
      }

      this.co.allRowsChanged();
      this.cn.Refresh();
   }
   static eV a(ap var0) {
      return var0.cq;
   }
   static object b(ap var0) {
      return var0.cj;
   }
   static DataGridView c(ap var0) {
      return var0.ci;
   }
   static List<object> d(ap var0) {
      return var0.ct;
   }
   static eV e(ap var0) {
      return var0.cs;
   }
   static eV f(ap var0) {
      return var0.cr;
   }
   static object g(ap var0) {
      return var0.cl;
   }
   static DataGridView h(ap var0) {
      return var0.ck;
   }
   static gz i(ap var0) {
      return var0.cp;
   }
   static DataGridView j(ap var0) {
      return var0.cn;
   }
   static void k(ap var0) {
      var0.R();
   }
}

}
