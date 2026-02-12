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

public class k : ComboBoxModel {
   public ex B;
   // $FF: synthetic field
   public h z;

   public k(h var1) {
      this.z = var1;
      this.B = null;
   }

   public int getSize() {
      return h.g(this.z).Count;
   }

   public ex c(int var1) {
      return (ex)h.g(this.z).Get(var1);
   }

   public void addListDataListener(ListDataListener var1) {
   }

   public void removeListDataListener(ListDataListener var1) {
   }

   public void setSelectedItem(Object var1) {
      this.B = (ex)var1;
      h.h(this.z);
   }

   public Object getSelectedItem() {
      return this.B;
   }

   // $FF: synthetic method
   public Object getElementAt(int var1) {
      return this.c(var1);
   }

}


}