using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

public class F : fs {
   public string filename;
   public long bd;
   fn be;
   eY bf;
   public Application aZ;

   public F(Application var1, string var2, long var3, fn var5, eY var6) {
      this.aZ = var1;
      this.filename = var2;
      this.bd = var3;
      this.be = var5;
      this.bf = var6;
   }

   public string K() {
      return this.filename;
   }

   public fn L() {
      return this.be;
   }

   public long lastModified() {
      return this.bd;
   }

   public eY M() {
      return this.bf;
   }

   public string b(eY var1) {
      throw new IOException("Save not supported!");
   }

   public string toString() {
      return this.filename;
   }
}

}
