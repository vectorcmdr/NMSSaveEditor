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

public class f : DataGridView {
   eV g;
   // $FF: synthetic field
   c h;

   f(c var1, Application var2, Supplier var3, Function var4) {
      this.h = var1;
      this.g = null;
      g var5 = new g(this, var3, var4);
      this.setCellSelectionEnabled(false);
      this.GetColumnModel().setColumnMargin(2);
      this.SetModel(var5);
      TableRowSorter var6 = new TableRowSorter(var5);
      var6.setSortable(2, false);
      this.setRowSorter(var6);
      this.getTableHeader().GetColumnModel().getColumn(0).setHeaderRenderer(new e(2));
      this.getTableHeader().GetColumnModel().getColumn(1).setHeaderRenderer(new e(2));
      CheckBox var7 = new CheckBox();
      var7.setHorizontalAlignment(0);
      this.GetColumnModel().getColumn(2).setMaxWidth(80);
      this.getTableHeader().GetColumnModel().getColumn(2).setHeaderRenderer(new e(0));
      this.GetColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(var7));
      this.GetColumnModel().getColumn(2).setCellRenderer(new d((d)null));
   }

   void a(eV var1) {
      this.g = var1;
   }
}

}
