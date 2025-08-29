package nomanssave;

import java.util.function.Function;
import java.util.function.Supplier;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.TableRowSorter;

class f extends JTable {
  eV g = null;
  
  f(c paramc, Application paramApplication, Supplier paramSupplier, Function paramFunction) {
    g g = new g(this, paramSupplier, paramFunction);
    setCellSelectionEnabled(false);
    getColumnModel().setColumnMargin(2);
    setModel(g);
    TableRowSorter<g> tableRowSorter = new TableRowSorter<>(g);
    tableRowSorter.setSortable(2, false);
    setRowSorter((RowSorter)tableRowSorter);
    getTableHeader().getColumnModel().getColumn(0).setHeaderRenderer(new e(2));
    getTableHeader().getColumnModel().getColumn(1).setHeaderRenderer(new e(2));
    JCheckBox jCheckBox = new JCheckBox();
    jCheckBox.setHorizontalAlignment(0);
    getColumnModel().getColumn(2).setMaxWidth(80);
    getTableHeader().getColumnModel().getColumn(2).setHeaderRenderer(new e(0));
    getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(jCheckBox));
    getColumnModel().getColumn(2).setCellRenderer(new d(null));
  }
  
  void a(eV parameV) {
    this.g = parameV;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\f.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */