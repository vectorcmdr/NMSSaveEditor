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

public class eH : ey {
   public bool jY;
   public string name;
   public ex jZ;
   public bool special;
   public Integer ka;
   public string jM;
   public bool kb;
   public string kc;
   public int kd;
   public string description;
   public List<object> ke;

   public eH(XmlElement var1, bool var2) {
      // base(var1.GetAttribute("id"));
      this.jY = var2;
      this.name = var1.GetAttribute("name");
      if (var2) {
         this.jZ = ex.iY;
      } else {
         this.jZ = ex.valueOf(var1.GetAttribute("category"));
      }
       this.special = var1.HasAttribute("special") ? (var1.GetAttribute("special")) : false;
      this.ka = var1.HasAttribute("chargeable") ? new Integer(var1.GetAttribute("chargeable")) : null;
      this.jM = var1.GetAttribute("subtitle");
      this.kb = var1.HasAttribute("cooking") ? (var1.GetAttribute("cooking")) : false;
      this.kc = var1.HasAttribute("icon") ? var1.GetAttribute("icon") : null;
      if (var1.HasAttribute("multiplier")) {
         this.kd = int.Parse(var1.GetAttribute("multiplier"));
      } else {
         this.kd = 0;
      }
       string var3 = null;
      XmlNodeList var4 = var1.ChildNodes;
      List<object> var6 = new List<object>();
       for(int var7 = 0; var7 < var4.Count; ++var7) {
         XmlNode var5 = var4[var7];
         if (var5 is Element) {
            var1 = (XmlElement)var5;
            if (var1.Name.Equals("description")) {
               var3 = a(var1);
            } else if (var1.Name.Equals("requirement")) {
               var6.Add(new ez(this, var1));
            }
         }
      }
       this.description = var3;
      this.ke = new List<object>(var6);
   }

   public eB ba() {
      return eB.jO;
   }

   public bool bb() {
      return this.jY;
   }

   public string getName() {
      return this.name;
   }

   public ex bc() {
      return this.jZ;
   }

   public bool bd() {
      return !this.jY && this.jZ != ex.ja && this.jZ != ex.iZ;
   }

   public bool be() {
      return !this.jY && this.special;
   }

   public Integer bf() {
      return this.ka;
   }

   public string bg() {
      return this.jM;
   }

   public bool bh() {
      return this.kb;
   }

   public string bi() {
      return this.kc;
   }

   public int bj() {
      return this.kd;
   }

   public string getDescription() {
      return this.description;
   }

   public List<object> bk() {
      return this.ke;
   }

   public string toString() {
      return this.name.Length == 0 ? this.id : this.name;
   }
}

}
