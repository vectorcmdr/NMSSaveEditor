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

public class en {
   public static bool iu = false;
   public static List<object> iv = new List<object>();

   public static void a(eo var0) {
      iv.Add(var0);
   }

   public static bool aS() {
      return iu;
   }

   public static void c(bool var0) {
      iu = var0;
      IEnumerator<object> var2 = iv.GetEnumerator();

      while(var2.MoveNext()) {
         eo var1 = (eo)var2.Current;
         var1.a(var0);
      }

   }
}

}
