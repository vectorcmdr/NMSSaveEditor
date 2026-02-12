using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class aC : CheckBox, TableCellRenderer {
   public Label cv = new Label();

   public object getTableCellRendererComponent(object table, object value, bool isSelected, bool hasFocus, int row, int column) {
      DataGridView var1 = (DataGridView)table;
      int var5 = row;
      int var6 = column;
      var5 = var1.convertRowIndexToModel(var5);
      if (!var1.DataSource.isCellEditable(var5, var6)) {
         return this.cv;
      } else {
         this.setBackground(var1.getBackground());
         this.setHorizontalAlignment(0);
         this.Checked = (true == (bool?)value);
         return this;
      }
   }
}

}
