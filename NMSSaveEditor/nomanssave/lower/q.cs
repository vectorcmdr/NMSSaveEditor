using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Windows.Forms;
using System.Globalization;

namespace NMSSaveEditor
{

public class q : TableModel {
   // $FF: synthetic field
   p I;

   q(p var1) {
      this.I = var1;
   }

   public void addTableModelListener(TableModelListener var1) {
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

   public Object getValueAt(int var1, int var2) {
      switch(var2) {
      case 0:
         return ((ey)p.a(this.I).Get(var1)).N(3);
      case 1:
         return ((ey)p.a(this.I).Get(var1)).Name;
      case 2:
         return ((ey)p.a(this.I).Get(var1)).bc().ToString();
      case 3:
         return ((ey)p.a(this.I).Get(var1)).getID();
      default:
         return null;
      }
   }

   public bool isCellEditable(int var1, int var2) {
      return false;
   }

   public void removeTableModelListener(TableModelListener var1) {
   }

   public void setValueAt(Object var1, int var2, int var3) {
   }
}

}
