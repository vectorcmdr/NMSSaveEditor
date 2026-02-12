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

public class eQ : ey {
   public static string gc = "0123456789ABCDEFabcdef";
   public bool jY;
   public string name;
   public ex jZ;
   public bool special;
   public Integer ka;
   public string jM;
   public string kc;
   public int kd;
   public string description;
   public List<object> ke;
   public eR km;

   public eQ(XmlElement var1, bool var2) {
      // base(var1.GetAttribute("id"));
      this.jY = var2;
      this.name = var1.GetAttribute("name");
       try {
         if (var2) {
            this.jZ = ex.valueOf("PROC_" + var1.GetAttribute("category"));
         } else {
            this.jZ = ex.valueOf(var1.GetAttribute("category"));
         }
      } catch (ArgumentException var10) {
         throw new Exception("Error in tech: " + this.id, var10);
      }
       this.special = var1.HasAttribute("special") ? Boolean.valueOf(var1.GetAttribute("special")) : false;
      this.ka = var1.HasAttribute("chargeable") ? new Integer(var1.GetAttribute("chargeable")) : null;
      this.jM = var1.GetAttribute("subtitle");
      this.kc = var1.HasAttribute("icon") ? var1.GetAttribute("icon") : null;
      if (var1.HasAttribute("multiplier")) {
         this.kd = int.Parse(var1.GetAttribute("multiplier"));
      } else {
         this.kd = 0;
      }
       string var3 = null;
      List<object> var4 = new List<object>();
      eR var5 = null;
      XmlNodeList var6 = var1.ChildNodes;
       for(int var9 = 0; var9 < var6.Count; ++var9) {
         XmlNode var7 = var6.Item(var9);
         if (var7 is Element) {
            XmlElement var8 = (XmlElement)var7;
            if (var8.Name.Equals("description")) {
               var3 = a(var8);
            } else if (var8.Name.Equals("requirement")) {
               var4.Add(new ez(this, var8));
            } else if (var8.Name.Equals("techbox")) {
               var5 = new eR(this, var8);
            }
         }
      }
       this.description = var3;
      this.ke = new List<object>(var4);
      this.km = var5;
   }

   public eB ba() {
      return eB.jN;
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
      return false;
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

   public eR bv() {
      return this.km;
   }

   public string toString() {
      return this.name.Length == 0 ? this.id : this.name;
   }
}

}
