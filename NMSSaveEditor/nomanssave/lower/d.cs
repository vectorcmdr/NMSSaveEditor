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

public class d : CheckBox, TableCellRenderer {
   public d() {
   }

   public Component getTableCellRendererComponent(DataGridView var1, Object var2, bool var3, bool var4, int var5, int var6) {
      this.SetBackground(var1.BackColor);
      this.setHorizontalAlignment(0);
      this.setSelected(true == var2);
      return this;
   }

   // $FF: synthetic method
   public d(d var1) {
      // Constructor chain: base()
   }
   object TableCellRenderer.getTableCellRendererComponent(object table, object value, bool isSelected, bool hasFocus, int row, int column) { return getTableCellRendererComponent((DataGridView)table, value, isSelected, hasFocus, row, column); }
}

}