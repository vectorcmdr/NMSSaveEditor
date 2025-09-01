package nomanssave;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;

public class ap extends JPanel {
   private final JTable ci;
   private final TableRowSorter cj;
   private final JTable ck;
   private final TableRowSorter cl;
   private final JCheckBox[] cm;
   private final JTable cn;
   private final TableRowSorter co;
   private gz cp;
   private eV cq;
   private eV cr;
   private eV cs;
   private ArrayList ct = new ArrayList();

   ap(Application var1) {
      GridLayout var2 = new GridLayout(2, 2);
      this.setLayout(var2);
      JPanel var3 = new JPanel();
      this.add(var3);
      var3.setLayout(new FormLayout(new ColumnSpec[]{FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("200px:grow"), FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC}));
      JLabel var4 = new JLabel("Known Technology");
      var4.putClientProperty("FlatLaf.styleClass", "semibold");
      var3.add(var4, "2, 2");
      JScrollPane var5 = new JScrollPane();
      var3.add(var5, "2, 4, fill, fill");
      this.ci = new JTable();
      this.ci.setSelectionMode(2);
      this.ci.setModel(new aq(this));
      this.ci.getColumnModel().getColumn(0).setMaxWidth(24);
      this.ci.getColumnModel().getColumn(0).setCellRenderer(new aB(this, (aB)null));
      this.cj = new TableRowSorter(this.ci.getModel());
      this.cj.setSortable(0, false);
      this.ci.setRowSorter(this.cj);
      var5.setViewportView(this.ci);
      JPanel var6 = new JPanel();
      var6.setLayout(new FlowLayout());
      var3.add(var6, "2, 6, fill, default");
      JButton var7 = new JButton();
      var7.setText("Add Technology");
      var7.addActionListener(new as(this));
      var6.add(var7);
      JButton var8 = new JButton();
      var8.setText("Remove Selected");
      var8.addActionListener(new at(this));
      var6.add(var8);
      JPanel var9 = new JPanel();
      this.add(var9);
      var9.setLayout(new FormLayout(new ColumnSpec[]{FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("200px:grow"), FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC}));
      var4 = new JLabel("Known Products");
      var4.putClientProperty("FlatLaf.styleClass", "semibold");
      var9.add(var4, "2, 2");
      JScrollPane var10 = new JScrollPane();
      var9.add(var10, "2, 4, fill, fill");
      this.ck = new JTable();
      this.ck.setSelectionMode(2);
      this.ck.setModel(new au(this));
      this.ck.getColumnModel().getColumn(0).setMaxWidth(24);
      this.ck.getColumnModel().getColumn(0).setCellRenderer(new aB(this, (aB)null));
      this.cl = new TableRowSorter(this.ck.getModel());
      this.cl.setSortable(0, false);
      this.ck.setRowSorter(this.cl);
      var10.setViewportView(this.ck);
      var6 = new JPanel();
      var6.setLayout(new FlowLayout());
      var9.add(var6, "2, 6, fill, default");
      JButton var11 = new JButton();
      var11.setText("Add Product");
      var11.addActionListener(new av(this));
      var6.add(var11);
      JButton var12 = new JButton();
      var12.setText("Remove Selected");
      var12.addActionListener(new aw(this));
      var6.add(var12);
      JPanel var13 = new JPanel();
      this.add(var13);
      var13.setLayout(new FormLayout(new ColumnSpec[]{FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("200px:grow"), FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC}));
      var4 = new JLabel("Known Words");
      var4.putClientProperty("FlatLaf.styleClass", "semibold");
      var13.add(var4, "2, 2");
      JScrollPane var14 = new JScrollPane();
      var13.add(var14, "2, 4, fill, fill");
      this.cn = new JTable();
      this.cn.setCellSelectionEnabled(false);
      this.cn.getColumnModel().setColumnMargin(2);
      this.cn.setModel(new ax(this));
      this.cn.getTableHeader().getColumnModel().getColumn(0).setHeaderRenderer(new aA(this.cn, 2));
      this.cn.getTableHeader().getColumnModel().getColumn(1).setHeaderRenderer(new aA(this.cn, 2));
      JCheckBox var15 = new JCheckBox();
      var15.setHorizontalAlignment(0);

      int var16;
      for(var16 = 2; var16 < this.cn.getColumnCount(); ++var16) {
         this.cn.getColumnModel().getColumn(var16).setMaxWidth(80);
         this.cn.getTableHeader().getColumnModel().getColumn(var16).setHeaderRenderer(new aA(this.cn, 0));
         this.cn.getColumnModel().getColumn(var16).setCellEditor(new DefaultCellEditor(var15));
         this.cn.getColumnModel().getColumn(var16).setCellRenderer(new aC());
      }

      this.co = new TableRowSorter(this.cn.getModel());

      for(var16 = 2; var16 < this.cn.getModel().getColumnCount(); ++var16) {
         this.co.setSortable(var16, false);
      }

      this.cn.setRowSorter(this.co);
      var14.setViewportView(this.cn);
      var6 = new JPanel();
      var6.setLayout(new FlowLayout());
      var13.add(var6, "2, 6, fill, default");
      JButton var24 = new JButton();
      var24.setText("Learn All");
      var24.addActionListener(new ay(this));
      var6.add(var24);
      JButton var17 = new JButton();
      var17.setText("Unlearn All");
      var17.addActionListener(new az(this));
      var6.add(var17);
      JPanel var18 = new JPanel();
      this.add(var18);
      var18.setLayout(new FormLayout(new ColumnSpec[]{FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC}));
      var4 = new JLabel("Known Glyphs");
      var4.putClientProperty("FlatLaf.styleClass", "semibold");
      var18.add(var4, "2, 2");
      JPanel var19 = new JPanel();
      var19.putClientProperty("FlatLaf.styleClass", "glyphs");
      var18.add(var19, "2, 4, fill, fill");
      var19.setLayout(new FormLayout(new ColumnSpec[]{FormFactory.LABEL_COMPONENT_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.LABEL_COMPONENT_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.LABEL_COMPONENT_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.LABEL_COMPONENT_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.DEFAULT_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC}));
      this.cm = new JCheckBox[16];

      for(int var20 = 0; var20 < 16; ++var20) {
         int var21 = var20 % 4 * 3 + 2;
         int var22 = var20 / 4 * 2 + 2;
         this.cm[var20] = new JCheckBox();
         this.cm[var20].setBackground(var19.getBackground());
         this.cm[var20].addActionListener(new ar(this));
         ImageIcon var23 = Application.a("UI-GLYPH" + (var20 + 1) + ".PNG");
         if (var23 == null) {
            var4 = new JLabel(Integer.toString(var20 + 1));
         } else {
            var4 = new JLabel(var23);
         }

         var19.add(this.cm[var20], var21 + ", " + var22);
         var19.add(var4, var21 + 1 + ", " + var22);
      }

   }

   private void R() {
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
      this.ct = new ArrayList();
      int var2;
      String var3;
      if (this.cr != null) {
         for(var2 = 0; var2 < this.cr.size(); ++var2) {
            var3 = this.cr.X(var2);
            if (!this.ct.contains(var3)) {
               this.ct.add(var3);
            }
         }
      }

      if (this.cs != null) {
         for(var2 = 0; var2 < this.cs.size(); ++var2) {
            var3 = this.cs.X(var2);
            if (!this.ct.contains(var3)) {
               this.ct.add(var3);
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
   static JTable c(ap var0) {
      return var0.ci;
   }

   // $FF: synthetic method
   static ArrayList d(ap var0) {
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
   static JTable h(ap var0) {
      return var0.ck;
   }

   // $FF: synthetic method
   static gz i(ap var0) {
      return var0.cp;
   }

   // $FF: synthetic method
   static JTable j(ap var0) {
      return var0.cn;
   }

   // $FF: synthetic method
   static void k(ap var0) {
      var0.R();
   }
}
