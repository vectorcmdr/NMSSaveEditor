using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class aC : CheckBox, TableCellRenderer {
   Label cv = new Label();

   public Component getTableCellRendererComponent(DataGridView var1, Object var2, bool var3, bool var4, int var5, int var6) {
      var5 = var1.convertRowIndexToModel(var5);
      if (!var1.getModel().isCellEditable(var5, var6)) {
         return this.cv;
      } else {
         this.setBackground(var1.getBackground());
         this.setHorizontalAlignment(0);
         this.setSelected(Boolean.TRUE == var2);
         return this;
      }
   }
}

}
