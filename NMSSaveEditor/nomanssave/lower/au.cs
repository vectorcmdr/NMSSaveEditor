using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class au : object {
   ap cu;

   au(ap var1) {
      this.cu = var1;
   }

   public void addTableModelListener(EventHandler var1) {
   }

   public Class getColumnClass(int var1) {
      return var1 == 0 ? typeof(Image) : typeof(string);
   }

   public int getColumnCount() {
      return 4;
   }

   public string getColumnName(int var1) {
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
      return ap.d(this.cu) == null ? 0 : ap.d(this.cu).Count;
   }

   public Object getValueAt(int var1, int var2) {
      string var3 = (string)ap.d(this.cu)[(var1);
      ey var4 = ey.d(var3);
      switch(var2) {
      case 0:
         return var4 == null ? null : var4.N(3);
      case 1:
         return var4 == null ? "" : var4.Name;
      case 2:
         return var4 == null ? "" : var4.bc().ToString();
      case 3:
         return var3;
      default:
         return null;
      }
   }

   public bool isCellEditable(int var1, int var2) {
      return false;
   }

   public void removeTableModelListener(EventHandler var1) {
   }

   public void setValueAt(Object var1, int var2, int var3) {
   }
}

}
