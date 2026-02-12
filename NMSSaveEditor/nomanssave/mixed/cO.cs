using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class cO : object {
   cN gt;
   private Class gu;

   cO(cN var1, Class var2) {
      this.gt = var1;
      this.gu = var2;
   }

   public int getSize() {
      return ((Enum[])this.gu.getEnumConstants()).Length + cN.a(this.gt).Count;
   }

   public Object getElementAt(int var1) {
      return var1 < ((Enum[])this.gu.getEnumConstants()).Length ? ((Enum[])this.gu.getEnumConstants())[var1] : cN.a(this.gt).get(var1 - ((Enum[])this.gu.getEnumConstants()).Length);
   }

   public void addListDataListener(EventHandler var1) {
   }

   public void removeListDataListener(EventHandler var1) {
   }

   public void setSelectedItem(Object var1) {
      Object var2 = cN.b(this.gt);
      cN.a(this.gt, var1);
      if (cN.c(this.gt) != null) {
         Control.invokeLater(() -> {
            if (cN.b(this.gt) == null) {
               if (var2 != null) {
                  cN.c(this.gt).setSelectedValue((string)null);
               }
            } else if (var2 == null || !cN.b(this.gt).equals(var2)) {
               if (cN.d(this.gt)) {
                  cN.c(this.gt).setSelectedValue(((gD)cN.b(this.gt)).K());
               } else if (cN.b(this.gt) is Enum) {
                  cN.c(this.gt).setSelectedValue(((Enum)cN.b(this.gt)).name());
               } else {
                  cN.c(this.gt).setSelectedValue(cN.b(this.gt).ToString());
               }
            }

         });
      }

   }

   public Object getSelectedItem() {
      return cN.b(this.gt);
   }
}

}
