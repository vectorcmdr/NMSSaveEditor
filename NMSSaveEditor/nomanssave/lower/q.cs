using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

public class q : object {
   p I;

   public q(p var1) {
      this.I = var1;
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
      return p.a(this.I) == null ? 0 : p.a(this.I).Count;
   }

   public object getValueAt(int var1, int var2) {
      switch(var2) {
      case 0:
         return ((ey)p.a(this.I)[var1]).N(3);
      case 1:
         return ((ey)p.a(this.I)[var1]).Name;
      case 2:
         return ((ey)p.a(this.I)[var1]).bc().ToString();
      case 3:
         return ((ey)p.a(this.I)[var1]).getID();
      default:
         return null;
      }
   }

   public bool isCellEditable(int var1, int var2) {
      return false;
   }

   public void removeTableModelListener(EventHandler var1) {
   }

   public void setValueAt(object var1, int var2, int var3) {
   }
}

}
