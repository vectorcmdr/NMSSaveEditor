package nomanssave;

import javax.swing.ComboBoxModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListDataListener;

class cO implements ComboBoxModel {
   // $FF: synthetic field
   final cN gt;
   // $FF: synthetic field
   private final Class gu;

   cO(cN var1, Class var2) {
      this.gt = var1;
      this.gu = var2;
   }

   public int getSize() {
      return ((Enum[])this.gu.getEnumConstants()).length + cN.a(this.gt).size();
   }

   public Object getElementAt(int var1) {
      return var1 < ((Enum[])this.gu.getEnumConstants()).length ? ((Enum[])this.gu.getEnumConstants())[var1] : cN.a(this.gt).get(var1 - ((Enum[])this.gu.getEnumConstants()).length);
   }

   public void addListDataListener(ListDataListener var1) {
   }

   public void removeListDataListener(ListDataListener var1) {
   }

   public void setSelectedItem(Object var1) {
      Object var2 = cN.b(this.gt);
      cN.a(this.gt, var1);
      if (cN.c(this.gt) != null) {
         SwingUtilities.invokeLater(() -> {
            if (cN.b(this.gt) == null) {
               if (var2 != null) {
                  cN.c(this.gt).setSelectedValue((String)null);
               }
            } else if (var2 == null || !cN.b(this.gt).equals(var2)) {
               if (cN.d(this.gt)) {
                  cN.c(this.gt).setSelectedValue(((gD)cN.b(this.gt)).K());
               } else if (cN.b(this.gt) instanceof Enum) {
                  cN.c(this.gt).setSelectedValue(((Enum)cN.b(this.gt)).name());
               } else {
                  cN.c(this.gt).setSelectedValue(cN.b(this.gt).toString());
               }
            }

         });
      }

   }

   public Object getSelectedItem() {
      return cN.b(this.gt);
   }
}
