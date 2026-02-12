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

public class ap : Panel {
   public DataGridView ci;
   public TableRowSorter cj;
   public DataGridView ck;
   public TableRowSorter cl;
   public CheckBox[] cm;
   public DataGridView cn;
   public TableRowSorter co;
   public gz cp;
   public eV cq;
   public eV cr;
   public eV cs;
   public List<object> ct = new List<object>();

   public ap(Application var1) {
      GridLayout var2 = new GridLayout(2, 2);
      this.SetLayout(var2);
      Panel var3 = new Panel();
      this.Add(var3);
      var3.SetLayout(new FormLayout(new ColumnSpec[]{FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("200px:grow"), FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC}));
      Label var4 = new Label("Known Technology");
      var4.putClientProperty("FlatLaf.styleClass", "semibold");
      var3.Add(var4, "2, 2");
      Panel var5 = new Panel();
      var3.Add(var5, "2, 4, fill, fill");
      this.ci = new DataGridView();
      this.ci.SetSelectionMode(2);
      this.ci.SetModel(new aq(this));
      this.ci.GetColumnModel().getColumn(0).setMaxWidth(24);
      this.ci.GetColumnModel().getColumn(0).setCellRenderer(new aB(this, (aB)null));
      this.cj = new TableRowSorter(this.ci.GetModel());
      this.cj.setSortable(0, false);
      this.ci.setRowSorter(this.cj);
      var5.setViewportView(this.ci);
      Panel var6 = new Panel();
      var6.SetLayout(new FlowLayout());
      var3.Add(var6, "2, 6, fill, default");
      Button var7 = new Button();
      var7.SetText("Add Technology");
      var7.AddActionListener(new @as(this));
      var6.Add(var7);
      Button var8 = new Button();
      var8.SetText("Remove Selected");
      var8.AddActionListener(new at(this));
      var6.Add(var8);
      Panel var9 = new Panel();
      this.Add(var9);
      var9.SetLayout(new FormLayout(new ColumnSpec[]{FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("200px:grow"), FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC}));
      var4 = new Label("Known Products");
      var4.putClientProperty("FlatLaf.styleClass", "semibold");
      var9.Add(var4, "2, 2");
      Panel var10 = new Panel();
      var9.Add(var10, "2, 4, fill, fill");
      this.ck = new DataGridView();
      this.ck.SetSelectionMode(2);
      this.ck.SetModel(new au(this));
      this.ck.GetColumnModel().getColumn(0).setMaxWidth(24);
      this.ck.GetColumnModel().getColumn(0).setCellRenderer(new aB(this, (aB)null));
      this.cl = new TableRowSorter(this.ck.GetModel());
      this.cl.setSortable(0, false);
      this.ck.setRowSorter(this.cl);
      var10.setViewportView(this.ck);
      var6 = new Panel();
      var6.SetLayout(new FlowLayout());
      var9.Add(var6, "2, 6, fill, default");
      Button var11 = new Button();
      var11.SetText("Add Product");
      var11.AddActionListener(new av(this));
      var6.Add(var11);
      Button var12 = new Button();
      var12.SetText("Remove Selected");
      var12.AddActionListener(new aw(this));
      var6.Add(var12);
      Panel var13 = new Panel();
      this.Add(var13);
      var13.SetLayout(new FormLayout(new ColumnSpec[]{FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("200px:grow"), FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC}));
      var4 = new Label("Known Words");
      var4.putClientProperty("FlatLaf.styleClass", "semibold");
      var13.Add(var4, "2, 2");
      Panel var14 = new Panel();
      var13.Add(var14, "2, 4, fill, fill");
      this.cn = new DataGridView();
      this.cn.setCellSelectionEnabled(false);
      this.cn.GetColumnModel().setColumnMargin(2);
      this.cn.SetModel(new ax(this));
      this.cn.getTableHeader().GetColumnModel().getColumn(0).setHeaderRenderer(new aA(this.cn, 2));
      this.cn.getTableHeader().GetColumnModel().getColumn(1).setHeaderRenderer(new aA(this.cn, 2));
      CheckBox var15 = new CheckBox();
      var15.setHorizontalAlignment(0);

      int var16;
      for(var16 = 2; var16 < this.cn.ColumnCount; ++var16) {
         this.cn.GetColumnModel().getColumn(var16).setMaxWidth(80);
         this.cn.getTableHeader().GetColumnModel().getColumn(var16).setHeaderRenderer(new aA(this.cn, 0));
         this.cn.GetColumnModel().getColumn(var16).setCellEditor(new DefaultCellEditor(var15));
         this.cn.GetColumnModel().getColumn(var16).setCellRenderer(new aC());
      }

      this.co = new TableRowSorter(this.cn.GetModel());

      for(var16 = 2; var16 < this.cn.GetModel().ColumnCount; ++var16) {
         this.co.setSortable(var16, false);
      }

      this.cn.setRowSorter(this.co);
      var14.setViewportView(this.cn);
      var6 = new Panel();
      var6.SetLayout(new FlowLayout());
      var13.Add(var6, "2, 6, fill, default");
      Button var24 = new Button();
      var24.SetText("Learn All");
      var24.AddActionListener(new ay(this));
      var6.Add(var24);
      Button var17 = new Button();
      var17.SetText("Unlearn All");
      var17.AddActionListener(new az(this));
      var6.Add(var17);
      Panel var18 = new Panel();
      this.Add(var18);
      var18.SetLayout(new FormLayout(new ColumnSpec[]{FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC}));
      var4 = new Label("Known Glyphs");
      var4.putClientProperty("FlatLaf.styleClass", "semibold");
      var18.Add(var4, "2, 2");
      Panel var19 = new Panel();
      var19.putClientProperty("FlatLaf.styleClass", "glyphs");
      var18.Add(var19, "2, 4, fill, fill");
      var19.SetLayout(new FormLayout(new ColumnSpec[]{FormFactory.LABEL_COMPONENT_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.LABEL_COMPONENT_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.LABEL_COMPONENT_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.LABEL_COMPONENT_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.DEFAULT_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC}));
      this.cm = new CheckBox[16];

      for(int var20 = 0; var20 < 16; ++var20) {
         int var21 = var20 % 4 * 3 + 2;
         int var22 = var20 / 4 * 2 + 2;
         this.cm[var20] = new CheckBox();
         this.cm[var20].SetBackground(var19.BackColor);
         this.cm[var20].AddActionListener(new ar(this));
         Image var23 = Application.a("UI-GLYPH" + (var20 + 1) + ".PNG");
         if (var23 == null) {
            var4 = new Label(Convert.ToString(var20 + 1));
         } else {
            var4 = new Label(var23);
         }

         var19.Add(this.cm[var20], var21 + ", " + var22);
         var19.Add(var4, var21 + 1 + ", " + var22);
      }

   }

   public void R() {
      if (this.cp != null) {
         int var1 = 0;

         for(int var2 = 0; var2 < 16; ++var2) {
            if (this.cm[var2].isSelected()) {
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
      this.ci.updateUI();
      this.ck.clearSelection();
      this.cl.allRowsChanged();
      this.ck.updateUI();
      var2 = var1 == null ? 0 : var1.dP();

      for(int var5 = 0; var5 < 16; ++var5) {
         int var4 = 1 << var5;
         this.cm[var5].setSelected((var2 & var4) == var4);
      }

      this.co.allRowsChanged();
      this.cn.updateUI();
   }

   // $FF: synthetic method
   static eV a(ap var0) {
      return var0.cq;
   }

   // $FF: synthetic method
   static TableRowSorter b(ap var0) {
      return var0.cj;
   }

   // $FF: synthetic method
   static DataGridView c(ap var0) {
      return var0.ci;
   }

   // $FF: synthetic method
   static List<object> d(ap var0) {
      return var0.ct;
   }

   // $FF: synthetic method
   static eV e(ap var0) {
      return var0.cs;
   }

   // $FF: synthetic method
   static eV f(ap var0) {
      return var0.cr;
   }

   // $FF: synthetic method
   static TableRowSorter g(ap var0) {
      return var0.cl;
   }

   // $FF: synthetic method
   static DataGridView h(ap var0) {
      return var0.ck;
   }

   // $FF: synthetic method
   static gz i(ap var0) {
      return var0.cp;
   }

   // $FF: synthetic method
   static DataGridView j(ap var0) {
      return var0.cn;
   }

   // $FF: synthetic method
   static void k(ap var0) {
      var0.R();
   }
}

}
