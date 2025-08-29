package nomanssave;

import javax.swing.ImageIcon;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

class q implements TableModel {
  q(p paramp) {}
  
  public void addTableModelListener(TableModelListener paramTableModelListener) {}
  
  public Class getColumnClass(int paramInt) {
    return (Class)((paramInt == 0) ? ImageIcon.class : String.class);
  }
  
  public int getColumnCount() {
    return 4;
  }
  
  public String getColumnName(int paramInt) {
    switch (paramInt) {
      case 0:
        return "";
      case 1:
        return "Name";
      case 2:
        return "Category";
      case 3:
        return "ID";
    } 
    return null;
  }
  
  public int getRowCount() {
    return (p.a(this.I) == null) ? 0 : p.a(this.I).size();
  }
  
  public Object getValueAt(int paramInt1, int paramInt2) {
    switch (paramInt2) {
      case 0:
        return ((ey)p.a(this.I).get(paramInt1)).N(3);
      case 1:
        return ((ey)p.a(this.I).get(paramInt1)).getName();
      case 2:
        return ((ey)p.a(this.I).get(paramInt1)).bc().toString();
      case 3:
        return ((ey)p.a(this.I).get(paramInt1)).getID();
    } 
    return null;
  }
  
  public boolean isCellEditable(int paramInt1, int paramInt2) {
    return false;
  }
  
  public void removeTableModelListener(TableModelListener paramTableModelListener) {}
  
  public void setValueAt(Object paramObject, int paramInt1, int paramInt2) {}
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\q.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */