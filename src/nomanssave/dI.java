package nomanssave;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

class dI implements TableModel {
  dI(dE paramdE) {}
  
  public void addTableModelListener(TableModelListener paramTableModelListener) {}
  
  public Class getColumnClass(int paramInt) {
    return (Class)((paramInt == 1) ? eM.class : String.class);
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
        return "Description";
    } 
    return null;
  }
  
  public int getRowCount() {
    gE gE = (gE)dE.a(this.hE).getSelectedItem();
    return (gE == null) ? 0 : gE.dW();
  }
  
  public Object getValueAt(int paramInt1, int paramInt2) {
    gE gE = (gE)dE.a(this.hE).getSelectedItem();
    String str = (gE == null) ? null : gE.aH(paramInt1);
    eM eM = eM.x(str);
    switch (paramInt2) {
      case 0:
        return str;
      case 1:
        return eM;
      case 2:
        return (eM == null) ? "" : eM.getDescription();
    } 
    return null;
  }
  
  public boolean isCellEditable(int paramInt1, int paramInt2) {
    return (paramInt2 == 1);
  }
  
  public void removeTableModelListener(TableModelListener paramTableModelListener) {}
  
  public void setValueAt(Object paramObject, int paramInt1, int paramInt2) {
    gE gE = (gE)dE.a(this.hE).getSelectedItem();
    if (gE == null || paramInt2 != 1)
      return; 
    eM eM = (eM)paramObject;
    String str = gE.aH(paramInt1);
    if (eM.bb()) {
      int i = str.indexOf("#");
      if (i >= 0 && str.substring(0, i).equals(eM.getID()))
        return; 
      String str1 = "#" + (int)Math.floor(Math.random() * 100000.0D);
      gE.c(paramInt1, String.valueOf(eM.getID()) + str1);
    } else {
      if (str.endsWith(eM.getID()))
        return; 
      gE.c(paramInt1, eM.getID());
    } 
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\dI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */