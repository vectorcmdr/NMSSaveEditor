using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml;

namespace NMSSaveEditor
{

public class eH : ey {
   bool jY;
   string name;
   ex jZ;
   bool special;
   Integer ka;
   string jM;
   bool kb;
   string kc;
   int kd;
   string description;
   List ke;

   eH(Element var1, bool var2) {
      base(var1.getAttribute("id"));
      this.jY = var2;
      this.name = var1.getAttribute("name");
      if (var2) {
         this.jZ = ex.iY;
      } else {
         this.jZ = ex.valueOf(var1.getAttribute("category"));
      }

      this.special = var1.hasAttribute("special") ? Boolean.valueOf(var1.getAttribute("special")) : false;
      this.ka = var1.hasAttribute("chargeable") ? new Integer(var1.getAttribute("chargeable")) : null;
      this.jM = var1.getAttribute("subtitle");
      this.kb = var1.hasAttribute("cooking") ? Boolean.valueOf(var1.getAttribute("cooking")) : false;
      this.kc = var1.hasAttribute("icon") ? var1.getAttribute("icon") : null;
      if (var1.hasAttribute("multiplier")) {
         this.kd = int.Parse(var1.getAttribute("multiplier"));
      } else {
         this.kd = 0;
      }

      string var3 = null;
      XmlNodeList var4 = var1.getChildNodes();
      List<object> var6 = new List<object>();

      for(int var7 = 0; var7 < var4.getLength(); ++var7) {
         Node var5 = var4.item(var7);
         if (var5 is Element) {
            var1 = (Element)var5;
            if (var1.getNodeName().equals("description")) {
               var3 = a(var1);
            } else if (var1.getNodeName().equals("requirement")) {
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

   public List bk() {
      return this.ke;
   }

   public string toString() {
      return this.name.length() == 0 ? this.id : this.name;
   }
}

}
