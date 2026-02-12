using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Xml;

namespace NMSSaveEditor
{



public class eu {
   public static List<object> iH = new List<object>();

   static eu() {
      Stream var0 = typeof(Application).GetManifestResourceStream("db/inventory.xml");
      if (var0 != null) {
         try {
            Document var1 = DocumentBuilderFactory.newInstance(string var1) {
      IEnumerator<object> var3 = iH.GetEnumerator();

      while(true) {
         ev var2;
         do {
            if (!var3.MoveNext()) {
               return null;
            }

            var2 = (ev)var3.Current;
         } while(!var2.id.Equals(var0));

         IEnumerator<object> var5 = var2.GetEnumerator();

         while(var5.MoveNext()) {
            ew var4 = (ew)var5.Current;
            if (var4.iI.Equals(var1)) {
               return var4;
            }
         }
      }
   }
}



}
