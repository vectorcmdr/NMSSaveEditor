using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{



public class ax : object {
   public ap cu;

   public ax(ap var1) {
      this.cu = var1;
   }

   public int getRowCount() {
      return eS.bx();
   }

   public int getColumnCount() {
      return 7;
   }

   public string getColumnName(int var1) {
      switch(var1) {
      case 0:
         return "Word";
      case 1:
         return "ID";
      case 2:
         return eU.kr.ToString();
      case 3:
         return eU.ks.ToString();
      case 4:
         return eU.kt.ToString();
      case 5:
         return eU.kv.ToString();
      case 6:
         return eU.kz.ToString();
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
      if (var2 >= 2 && var2 < this.getColumnCount()) {
         eS var3 = eS.T(var1);
         if (var3 == null) {
            return false;
         } else {
            switch(var2) {
            case 2:
               return var3.a(eU.kr);
            case 3:
               return var3.a(eU.ks);
            case 4:
               return var3.a(eU.kt);
            case 5:
               return var3.a(eU.kv);
            case 6:
               return var3.a(eU.kz);
            default:
               return false;
            }
         }
      } else {
         return false;
      }
   }

   public object getValueAt(int var1, int var2) {
      eS var3 = eS.T(var1);
      gA var4 = ap.i(this.cu).a(var3);
      switch(var2) {
      case 0:
         return var3 == null ? "" : var3.Text;
      case 1:
         return var4.getID();
      case 2:
         return var4.c(eU.kr);
      case 3:
         return var4.c(eU.ks);
      case 4:
         return var4.c(eU.kt);
      case 5:
         return var4.c(eU.kv);
      case 6:
         return var4.c(eU.kz);
      default:
         return null;
      }
   }

   public void setValueAt(object var1, int var2, int var3) {
      eS var4 = eS.T(var2);
      gA var5 = ap.i(this.cu).a(var4);
      switch(var3) {
      case 2:
         var5.a(eU.kr, Boolean.TRUE.Equals(var1));
         break;
      case 3:
         var5.a(eU.ks, Boolean.TRUE.Equals(var1));
         break;
      case 4:
         var5.a(eU.kt, Boolean.TRUE.Equals(var1));
         break;
      case 5:
         var5.a(eU.kv, Boolean.TRUE.Equals(var1));
         break;
      case 6:
         var5.a(eU.kz, Boolean.TRUE.Equals(var1));
      }

   }

   public void addTableModelListener(EventHandler var1) {
   }

   public void removeTableModelListener(EventHandler var1) {
   }
}



}
