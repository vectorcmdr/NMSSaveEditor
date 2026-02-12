using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class dI : object {
   public dE hE;

   public dI(dE var1) {
      this.hE = var1;
   }

   public void addTableModelListener(EventHandler var1) {
   }

   public Class getColumnClass(int var1) {
      return var1 == 1 ? typeof(eM) : typeof(string);
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
         return "Description";
      default:
         return null;
      }
   }

   public int getRowCount() {
      gE var1 = (gE)dE.a(this.hE).SelectedItem;
      return var1 == null ? 0 : var1.dW();
   }

   public object getValueAt(int var1, int var2) {
      gE var3 = (gE)dE.a(this.hE).SelectedItem;
      string var4 = var3 == null ? null : var3.aH(var1);
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

   public bool isCellEditable(int var1, int var2) {
      return var2 == 1;
   }

   public void removeTableModelListener(EventHandler var1) {
   }

   public void setValueAt(object var1, int var2, int var3) {
      gE var4 = (gE)dE.a(this.hE).SelectedItem;
      if (var4 != null && var3 == 1) {
         eM var5 = (eM)var1;
         string var6 = var4.aH(var2);
         if (var5.bb()) {
            int var7 = var6.IndexOf("#");
            if (var7 >= 0 && var6.Substring(0, var7).Equals(var5.getID())) {
               return;
            }

            string var8 = "#" + (int)Math.Floor(new Random().NextDouble() * 100000.0D);
            var4.c(var2, var5.getID() + var8);
         } else {
            if (var6.EndsWith(var5.getID())) {
               return;
            }

            var4.c(var2, var5.getID());
         }

      }
   }
}


#else

public class dI
{
   public dI() { }
   public dI(params object[] args) { }
   public dE hE = default;
   public void addTableModelListener(EventHandler var1) { }
   public Class getColumnClass(int var1) { return default; }
   public int getColumnCount() { return 0; }
   public string getColumnName(int var1) { return ""; }
   public int getRowCount() { return 0; }
   public object getValueAt(int var1, int var2) { return default; }
   public bool isCellEditable(int var1, int var2) { return false; }
   public void removeTableModelListener(EventHandler var1) { }
   public void setValueAt(object var1, int var2, int var3) { }
}

#endif

}
