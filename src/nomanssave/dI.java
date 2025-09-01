package nomanssave;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

class dI implements TableModel {
   // $FF: synthetic field
   final dE hE;

   dI(dE var1) {
      this.hE = var1;
   }

   public void addTableModelListener(TableModelListener var1) {
   }

   public Class getColumnClass(int var1) {
      return var1 == 1 ? eM.class : String.class;
   }

   public int getColumnCount() {
      return 3;
   }

   public String getColumnName(int var1) {
      switch(var1) {
      case 0:
         return "ID";
      case 1:
         return "Name";
      case 2:
         return "Description";
      default:
         return null;
      }
   }

   public int getRowCount() {
      gE var1 = (gE)dE.a(this.hE).getSelectedItem();
      return var1 == null ? 0 : var1.dW();
   }

   public Object getValueAt(int var1, int var2) {
      gE var3 = (gE)dE.a(this.hE).getSelectedItem();
      String var4 = var3 == null ? null : var3.aH(var1);
      eM var5 = eM.x(var4);
      switch(var2) {
      case 0:
         return var4;
      case 1:
         return var5;
      case 2:
         return var5 == null ? "" : var5.getDescription();
      default:
         return null;
      }
   }

   public boolean isCellEditable(int var1, int var2) {
      return var2 == 1;
   }

   public void removeTableModelListener(TableModelListener var1) {
   }

   public void setValueAt(Object var1, int var2, int var3) {
      gE var4 = (gE)dE.a(this.hE).getSelectedItem();
      if (var4 != null && var3 == 1) {
         eM var5 = (eM)var1;
         String var6 = var4.aH(var2);
         if (var5.bb()) {
            int var7 = var6.indexOf("#");
            if (var7 >= 0 && var6.substring(0, var7).equals(var5.getID())) {
               return;
            }

            String var8 = "#" + (int)Math.floor(Math.random() * 100000.0D);
            var4.c(var2, var5.getID() + var8);
         } else {
            if (var6.endsWith(var5.getID())) {
               return;
            }

            var4.c(var2, var5.getID());
         }

      }
   }
}
