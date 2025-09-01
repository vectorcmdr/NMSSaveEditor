package nomanssave;

import java.util.function.Function;
import java.util.function.Supplier;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

class g implements TableModel {
   // $FF: synthetic field
   final f i;
   // $FF: synthetic field
   private final Supplier j;
   // $FF: synthetic field
   private final Function k;

   g(f var1, Supplier var2, Function var3) {
      this.i = var1;
      this.j = var2;
      this.k = var3;
   }

   public int getRowCount() {
      return (Integer)this.j.get();
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
         return "Unlocked";
      default:
         return null;
      }
   }

   public Class getColumnClass(int var1) {
      switch(var1) {
      case 0:
      case 1:
         return String.class;
      default:
         return Boolean.class;
      }
   }

   public boolean isCellEditable(int var1, int var2) {
      return var2 == 2 && this.i.g != null;
   }

   public Object getValueAt(int var1, int var2) {
      eI var3 = (eI)this.k.apply(var1);
      switch(var2) {
      case 0:
         return var3 == null ? "" : var3.getID();
      case 1:
         return var3 == null ? "" : var3.getName();
      case 2:
         return var3 != null && this.i.g != null ? this.i.g.indexOf(var3.getID()) >= 0 : false;
      default:
         return null;
      }
   }

   public void setValueAt(Object var1, int var2, int var3) {
      if (this.i.g != null) {
         eI var4 = (eI)this.k.apply(var2);
         if (var3 == 2) {
            int var5 = this.i.g.indexOf(var4.getID());
            if (Boolean.TRUE.equals(var1)) {
               if (var5 < 0) {
                  this.i.g.f(var4.getID());
               }
            } else if (var5 >= 0) {
               this.i.g.ac(var5);
            }
         }

      }
   }

   public void addTableModelListener(TableModelListener var1) {
   }

   public void removeTableModelListener(TableModelListener var1) {
   }
}
