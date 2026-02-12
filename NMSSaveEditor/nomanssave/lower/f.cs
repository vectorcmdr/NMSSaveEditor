using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class f : DataGridView {
   public eV g;
   public c h;

   public f(c var1, Application var2, Supplier var3, Function var4) {
      this.h = var1;
      this.g = null;
      g var5 = new g(this, var3, var4);
      this.setCellSelectionEnabled(false);
      this.getColumnModel().setColumnMargin(2);
      this.DataSource = (var5);
      object var6 = ((object)(var5));
      var6.setSortable(2, false);
      this.setRowSorter(var6);
      this.getTableHeader().getColumnModel().getColumn(0).setHeaderRenderer(new e(2));
      this.getTableHeader().getColumnModel().getColumn(1).setHeaderRenderer(new e(2));
      CheckBox var7 = new CheckBox();
      var7.setHorizontalAlignment(0);
      this.getColumnModel().getColumn(2).setMaxWidth(80);
      this.getTableHeader().getColumnModel().getColumn(2).setHeaderRenderer(new e(0));
      this.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(var7));
      this.getColumnModel().getColumn(2).setCellRenderer(new d((d)null));
   }

   public void a(eV var1) {
      this.g = var1;
   }
}


#else

public class f
{
   public f() { }
   public f(params object[] args) { }
   public eV g = default;
   public c h = default;
   public void a(eV var1) { }
}

#endif

}
