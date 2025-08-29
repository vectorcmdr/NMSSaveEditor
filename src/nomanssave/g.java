package nomanssave;

import java.util.function.Function;
import java.util.function.Supplier;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

class g implements TableModel {
  g(f paramf, Supplier paramSupplier, Function paramFunction) {}
  
  public int getRowCount() {
    return ((Integer)this.j.get()).intValue();
  }
  
  public int getColumnCount() {
    return 3;
  }
  
  public String getColumnName(int paramInt) {
    switch (paramInt) {
      case 0:
        return "ID";
      case 1:
        return "Name";
      case 2:
        return "Unlocked";
    } 
    return null;
  }
  
  public Class getColumnClass(int paramInt) {
    switch (paramInt) {
      case 0:
      case 1:
        return String.class;
    } 
    return Boolean.class;
  }
  
  public boolean isCellEditable(int paramInt1, int paramInt2) {
    return (paramInt2 == 2 && this.i.g != null);
  }
  
  public Object getValueAt(int paramInt1, int paramInt2) {
    eI eI = this.k.apply(Integer.valueOf(paramInt1));
    switch (paramInt2) {
      case 0:
        return (eI == null) ? "" : eI.getID();
      case 1:
        return (eI == null) ? "" : eI.getName();
      case 2:
        return Boolean.valueOf((eI == null || this.i.g == null) ? false : ((this.i.g.indexOf(eI.getID()) >= 0)));
    } 
    return null;
  }
  
  public void setValueAt(Object paramObject, int paramInt1, int paramInt2) {
    if (this.i.g == null)
      return; 
    eI eI = this.k.apply(Integer.valueOf(paramInt1));
    if (paramInt2 == 2) {
      int i = this.i.g.indexOf(eI.getID());
      if (Boolean.TRUE.equals(paramObject)) {
        if (i < 0)
          this.i.g.f(eI.getID()); 
      } else if (i >= 0) {
        this.i.g.ac(i);
      } 
    } 
  }
  
  public void addTableModelListener(TableModelListener paramTableModelListener) {}
  
  public void removeTableModelListener(TableModelListener paramTableModelListener) {}
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\g.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */