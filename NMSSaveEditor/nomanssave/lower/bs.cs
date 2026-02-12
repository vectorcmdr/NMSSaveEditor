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

public class bs : TableModel {
   // $FF: synthetic field
   bl er;

   bs(bl var1) {
      this.er = var1;
   }

   public void addTableModelListener(TableModelListener var1) {
   }

   public Class getColumnClass(int var1) {
      return typeof(string);
   }

   public int getColumnCount() {
      return 3;
   }

   public string getColumnName(int var1) {
      switch(var1) {
      case 0:
         return "Name";
      case 1:
         return "Type";
      case 2:
         return "Class";
      default:
         return null;
      }
   }

   public int getRowCount() {
      return bl.c(this.er) == null ? 0 : bl.c(this.er).length;
   }

   public Object getValueAt(int var1, int var2) {
      switch(var2) {
      case 0:
         return bl.c(this.er) == null ? null : bl.c(this.er)[var1].ToString();
      case 1:
         gr var3 = bl.c(this.er) == null ? null : bl.c(this.er)[var1].da();
         return var3 == null ? "Unknown" : var3.ToString();
      case 2:
         return bl.c(this.er) == null ? null : bl.c(this.er)[var1].cW().ToString();
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
