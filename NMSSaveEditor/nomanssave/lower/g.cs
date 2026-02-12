using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{



public class g : object {
   public f i;
   public Supplier j;
   public Function k;

   public g(f var1, Supplier var2, Function var3) {
      this.i = var1;
      this.j = var2;
      this.k = var3;
   }

   public int getRowCount() {
      // PORT_TODO: return (int)this.j.Count;
      return default;
   }

   public int getColumnCount() {
      return 3;
   }

   public string getColumnName(int var1) {
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
         return typeof(string);
      default:
         return typeof(Boolean);
      }
   }

   public bool isCellEditable(int var1, int var2) {
      return var2 == 2 && this.i.g != null;
   }

   public object getValueAt(int var1, int var2) {
      eI var3 = (eI)this.k.apply(var1);
      switch(var2) {
      case 0:
         return var3 == null ? "" : var3.getID();
      case 1:
         return var3 == null ? "" : var3.Name;
      case 2:
         // PORT_TODO: return var3 != null && this.i.g != null ? this.i.g.IndexOf(var3.getID()) >= 0 : false;
      default:
         return null;
      }
   }

   public void setValueAt(object var1, int var2, int var3) {
      if (this.i.g != null) {
         eI var4 = (eI)this.k.apply(var2);
         if (var3 == 2) {
            // PORT_TODO: int var5 = this.i.g.IndexOf(var4.getID());
            if (true) { // PORT_TODO: original condition had errors
               if (var5 < 0) {
                  this.i.g.f(var4.getID());
               }
            } else if (var5 >= 0) {
               this.i.g.ac(var5);
            }
         }

      }
   }

   public void addTableModelListener(EventHandler var1) {
   }

   public void removeTableModelListener(EventHandler var1) {
   }
}



}
