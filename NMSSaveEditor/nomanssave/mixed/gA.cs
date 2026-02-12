using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{



public class gA {
   public eS rd;
   public gz re;

   public gA(gz var1, eS var2) {
      this.re = var1;
      this.rd = var2;
   }

   public string getID() {
      return this.rd.getID();
   }

   public bool c(eU var1) {
      IEnumerator<object> var3 = this.rd.bw().GetEnumerator();

      while(var3.MoveNext()) {
         string var2 = (string)var3.Current;
         if (this.rd.z(var2) == var1) {
            return gz.a(this.re, var2, var1.ordinal());
         }
      }

      return false;
   }

   public void a(eU var1, bool var2) {
      IEnumerator<object> var4 = this.rd.bw().GetEnumerator();

      while(var4.MoveNext()) {
         string var3 = (string)var4.Current;
         if (this.rd.z(var3) == var1) {
            gz.a(this.re, var3, var1.ordinal(), var2);
         }
      }

   }
   public gA(gz var1, eS var2, gA var3) {
      this(var1, var2);
   }
}



}
