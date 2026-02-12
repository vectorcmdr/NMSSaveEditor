using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml;

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

public eH(Element var1, bool var2) : base(var1.getAttribute("id")) {
      this.jY = var2;
      this.name = var1.getAttribute("name");
      if (var2) {
         this.jZ = ex.iY;
      } else {
         this.jZ = ex.valueOf(var1.getAttribute("category"));
      }

      this.special = var1.hasAttribute("special") ? Boolean.valueOf(var1.getAttribute("special")) : false;
      this.ka = var1.hasAttribute("chargeable") ? ((int)(var1.getAttribute("chargeable"))) : null;
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
            if (var1.getNodeName().Equals("description")) {
               var3 = a(var1);
            } else if (var1.getNodeName().Equals("requirement")) {
               var6.Add(new ez(this, var1));
            }
         }
      }

      this.description = var3;
      this.ke = new List<object>(var6);
   }

   public override eB ba() {
      return eB.jO;
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

   public override int? bf() {
      return this.ka;
   }

   public override string bg() {
      return this.jM;
   }

   public override bool bh() {
      return this.kb;
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

   public string toString() {
      return this.name.Length == 0 ? this.id : this.name;
   }
}



}
