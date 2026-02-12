using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml;

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

   public eQ(Element var1, bool var2) {
      base(var1.getAttribute("id"));
      this.jY = var2;
      this.name = var1.getAttribute("name");

      try {
         if (var2) {
            this.jZ = ex.valueOf("PROC_" + var1.getAttribute("category"));
         } else {
            this.jZ = ex.valueOf(var1.getAttribute("category"));
         }
      } catch (ArgumentException var10) {
         throw new Exception("Error in tech: " + this.id, var10);
      }

      this.special = var1.hasAttribute("special") ? Boolean.valueOf(var1.getAttribute("special")) : false;
      this.ka = var1.hasAttribute("chargeable") ? ((int)(var1.getAttribute("chargeable"))) : null;
      this.jM = var1.getAttribute("subtitle");
      this.kc = var1.hasAttribute("icon") ? var1.getAttribute("icon") : null;
      if (var1.hasAttribute("multiplier")) {
         this.kd = int.Parse(var1.getAttribute("multiplier"));
      } else {
         this.kd = 0;
      }

      string var3 = null;
      List<object> var4 = new List<object>();
      eR var5 = null;
      XmlNodeList var6 = var1.getChildNodes();

      for(int var9 = 0; var9 < var6.getLength(); ++var9) {
         Node var7 = var6.item(var9);
         if (var7 is Element) {
            Element var8 = (Element)var7;
            if (var8.getNodeName().Equals("description")) {
               var3 = a(var8);
            } else if (var8.getNodeName().Equals("requirement")) {
               var4.Add(new ez(this, var8));
            } else if (var8.getNodeName().Equals("techbox")) {
               var5 = new eR(this, var8);
            }
         }
      }

      this.description = var3;
      this.ke = new List<object>(var4);
      this.km = var5;
   }

   public override eB ba() {
      return eB.jN;
   }

   public override bool bb() {
      return this.jY;
   }

   public override string getName() {
      return this.name;
   }

   public override ex bc() {
      return this.jZ;
   }

   public override bool bd() {
      return !this.jY && this.jZ != ex.ja && this.jZ != ex.iZ;
   }

   public override bool be() {
      return !this.jY && this.special;
   }

   public override Integer bf() {
      return this.ka;
   }

   public override string bg() {
      return this.jM;
   }

   public override bool bh() {
      return false;
   }

   public override string bi() {
      return this.kc;
   }

   public override int bj() {
      return this.kd;
   }

   public override string getDescription() {
      return this.description;
   }

   public override List<object> bk() {
      return this.ke;
   }

   public eR bv() {
      return this.km;
   }

   public string toString() {
      return this.name.length() == 0 ? this.id : this.name;
   }
}

}
