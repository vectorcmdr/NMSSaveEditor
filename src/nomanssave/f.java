package nomanssave;

import java.util.function.Function;
import java.util.function.Supplier;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;

class f extends JTable {
   eV g;
   // $FF: synthetic field
   final c h;

   f(c var1, Application var2, Supplier var3, Function var4) {
      this.h = var1;
      this.g = null;
      g var5 = new g(this, var3, var4);
      this.setCellSelectionEnabled(false);
      this.getColumnModel().setColumnMargin(2);
      this.setModel(var5);
      TableRowSorter var6 = new TableRowSorter(var5);
      var6.setSortable(2, false);
      this.setRowSorter(var6);
      this.getTableHeader().getColumnModel().getColumn(0).setHeaderRenderer(new e(2));
      this.getTableHeader().getColumnModel().getColumn(1).setHeaderRenderer(new e(2));
      JCheckBox var7 = new JCheckBox();
      var7.setHorizontalAlignment(0);
      this.getColumnModel().getColumn(2).setMaxWidth(80);
      this.getTableHeader().getColumnModel().getColumn(2).setHeaderRenderer(new e(0));
      this.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(var7));
      this.getColumnModel().getColumn(2).setCellRenderer(new d((d)null));
   }

   void a(eV var1) {
      this.g = var1;
   }
}
