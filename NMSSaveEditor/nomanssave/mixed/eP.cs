using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml;

namespace NMSSaveEditor
{

public class eP : ey {
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

   eP(Element var1) {
      base(var1.getAttribute("id"));
      this.name = var1.getAttribute("name");
      this.jZ = ex.valueOf(var1.getAttribute("category"));
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

      string var2 = null;
      XmlNodeList var3 = var1.getChildNodes();
      List<object> var5 = new List<object>();

      for(int var6 = 0; var6 < var3.getLength(); ++var6) {
         Node var4 = var3.item(var6);
         if (var4 is Element) {
            var1 = (Element)var4;
            if (var1.getNodeName().equals("description")) {
               var2 = a(var1);
            } else if (var1.getNodeName().equals("requirement")) {
               var5.Add(new ez(this, var1));
            }
         }
      }

      this.description = var2;
      this.ke = new List<object>(var5);
   }

   public eB ba() {
      return eB.jP;
   }

   public bool bb() {
      return false;
   }

   public string getName() {
      return this.name;
   }

   public ex bc() {
      return this.jZ;
   }

   public bool bd() {
      return this.jZ != ex.ja && this.jZ != ex.iZ;
   }

   public bool be() {
      return this.special;
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
