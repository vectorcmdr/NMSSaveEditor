using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml;

namespace NMSSaveEditor
{



public class eP : ey {
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

public eP(Element var1) : base(var1.getAttribute("id")) {
      this.name = var1.getAttribute("name");
      this.jZ = ex.valueOf(var1.getAttribute("category"));
      // PORT_TODO: this.special = var1.hasAttribute("special") ? Boolean.valueOf(var1.getAttribute("special")) : false;
      // PORT_TODO: // PORT_TODO: this.ka = var1.hasAttribute("chargeable") ? ((int)(var1.getAttribute("chargeable"))) : null;
      this.jM = var1.getAttribute("subtitle");
      // PORT_TODO: this.kb = var1.hasAttribute("cooking") ? Boolean.valueOf(var1.getAttribute("cooking")) : false;
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
            if (var1.getNodeName().Equals("description")) {
               // PORT_TODO: var2 = a(var1);
            } else if (var1.getNodeName().Equals("requirement")) {
               var5.Add(new ez(this, var1));
            }
         }
      }

      this.description = var2;
      this.ke = new List<object>(var5);
   }

   public override eB ba() {
      return eB.jP;
   }

   public override bool bb() {
      return false;
   }

   public override string getName() {
      return this.name;
   }

   public override ex bc() {
      return this.jZ;
   }

   public override bool bd() {
      return this.jZ != ex.ja && this.jZ != ex.iZ;
   }

   public override bool be() {
      return this.special;
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
