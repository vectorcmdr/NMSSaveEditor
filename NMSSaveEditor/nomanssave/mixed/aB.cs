using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class aB : DefaultTableCellRenderer {
   public ap cu;

   public aB(ap var1) {
      this.cu = var1;
   }

   public override object getTableCellRendererComponent(object table, object value, bool isSelected, bool hasFocus, int row, int column) {
      DataGridView var1 = (DataGridView)table;
      Label var7 = (Label)this.getTableCellRendererComponent(/*base*/var1, "", isSelected, hasFocus, row, column);
      var7.setIcon((Image)value);
      return var7;
   }
   public aB(ap var1, aB var2) : this(var1) {
   }
}

}
