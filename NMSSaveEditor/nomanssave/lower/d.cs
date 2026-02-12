using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class d : CheckBox, TableCellRenderer {
   public d() {
   }

   public object getTableCellRendererComponent(object table, object value, bool isSelected, bool hasFocus, int row, int column) {
      DataGridView var1 = (DataGridView)table;
      this.setBackground(var1.getBackground());
      this.setHorizontalAlignment(0);
      this.Checked = (true == (bool?)value);
      return this;
   }
   public d(d var1) : this() {
   }
}

}
