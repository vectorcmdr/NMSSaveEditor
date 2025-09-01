package nomanssave;

import javax.swing.ImageIcon;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

class au implements TableModel {
   // $FF: synthetic field
   final ap cu;

   au(ap var1) {
      this.cu = var1;
   }

   public void addTableModelListener(TableModelListener var1) {
   }

   public Class getColumnClass(int var1) {
      return var1 == 0 ? ImageIcon.class : String.class;
   }

   public int getColumnCount() {
      return 4;
   }

   public String getColumnName(int var1) {
      switch(var1) {
      case 0:
         return "";
      case 1:
         return "Name";
      case 2:
         return "Category";
      case 3:
         return "ID";
      default:
         return null;
      }
   }

   public int getRowCount() {
      return ap.d(this.cu) == null ? 0 : ap.d(this.cu).size();
   }

   public Object getValueAt(int var1, int var2) {
      String var3 = (String)ap.d(this.cu).get(var1);
      ey var4 = ey.d(var3);
      switch(var2) {
      case 0:
         return var4 == null ? null : var4.N(3);
      case 1:
         return var4 == null ? "" : var4.getName();
      case 2:
         return var4 == null ? "" : var4.bc().toString();
      case 3:
         return var3;
      default:
         return null;
      }
   }

   public boolean isCellEditable(int var1, int var2) {
      return false;
   }

   public void removeTableModelListener(TableModelListener var1) {
   }

   public void setValueAt(Object var1, int var2, int var3) {
   }
}
