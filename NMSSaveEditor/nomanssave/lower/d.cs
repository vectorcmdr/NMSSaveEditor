using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class d : CheckBox, TableCellRenderer {
   private d() {
   }

   public Component getTableCellRendererComponent(DataGridView var1, Object var2, bool var3, bool var4, int var5, int var6) {
      this.setBackground(var1.getBackground());
      this.setHorizontalAlignment(0);
      this.Checked = (Boolean.TRUE == var2);
      return this;
   }
   d(d var1) {
      this();
   }
}

}
