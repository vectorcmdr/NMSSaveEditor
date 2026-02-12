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

public class aC : CheckBox, TableCellRenderer {
   Label cv = new Label();

   public Component getTableCellRendererComponent(DataGridView var1, Object var2, bool var3, bool var4, int var5, int var6) {
      var5 = var1.convertRowIndexToModel(var5);
      if (!var1.GetModel().isCellEditable(var5, var6)) {
         return this.cv;
      } else {
         this.SetBackground(var1.BackColor);
         this.setHorizontalAlignment(0);
         this.setSelected(Boolean.TRUE == var2);
         return this;
      }
   }
}

}
