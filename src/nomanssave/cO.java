package nomanssave;

import javax.swing.ComboBoxModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListDataListener;

class cO implements ComboBoxModel {
  cO(cN paramcN, Class paramClass) {}
  
  public int getSize() {
    return ((Enum[])this.gu.getEnumConstants()).length + cN.a(this.gt).size();
  }
  
  public Object getElementAt(int paramInt) {
    return (paramInt < ((Enum[])this.gu.getEnumConstants()).length) ? ((Enum[])this.gu.getEnumConstants())[paramInt] : cN.a(this.gt).get(paramInt - ((Enum[])this.gu.getEnumConstants()).length);
  }
  
  public void addListDataListener(ListDataListener paramListDataListener) {}
  
  public void removeListDataListener(ListDataListener paramListDataListener) {}
  
  public void setSelectedItem(Object paramObject) {
    Object object = cN.b(this.gt);
    cN.a(this.gt, paramObject);
    if (cN.c(this.gt) != null)
      SwingUtilities.invokeLater(() -> {
            if (cN.b(this.gt) == null) {
              if (paramObject != null)
                cN.c(this.gt).setSelectedValue(null); 
            } else if (paramObject == null || !cN.b(this.gt).equals(paramObject)) {
              if (cN.d(this.gt)) {
                cN.c(this.gt).setSelectedValue(((gD)cN.b(this.gt)).K());
              } else if (cN.b(this.gt) instanceof Enum) {
                cN.c(this.gt).setSelectedValue(((Enum)cN.b(this.gt)).name());
              } else {
                cN.c(this.gt).setSelectedValue(cN.b(this.gt).toString());
              } 
            } 
          }); 
  }
  
  public Object getSelectedItem() {
    return cN.b(this.gt);
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\cO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */