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
   List<object> ke;

   eP(XmlElement var1) {
      base(var1.GetAttribute("id"));
      this.name = var1.GetAttribute("name");
      this.jZ = ex.valueOf(var1.GetAttribute("category"));
      this.special = var1.HasAttribute("special") ? Boolean.valueOf(var1.GetAttribute("special")) : false;
      this.ka = var1.HasAttribute("chargeable") ? new Integer(var1.GetAttribute("chargeable")) : null;
      this.jM = var1.GetAttribute("subtitle");
      this.kb = var1.HasAttribute("cooking") ? Boolean.valueOf(var1.GetAttribute("cooking")) : false;
      this.kc = var1.HasAttribute("icon") ? var1.GetAttribute("icon") : null;
      if (var1.HasAttribute("multiplier")) {
         this.kd = int.Parse(var1.GetAttribute("multiplier"));
      } else {
         this.kd = 0;
      }

      string var2 = null;
      XmlNodeList var3 = var1.ChildNodes;
      List<object> var5 = new List<object>();

      for(int var6 = 0; var6 < var3.Count; ++var6) {
         XmlNode var4 = var3.Item(var6);
         if (var4 is Element) {
            var1 = (System.Xml.XmlElement)var4;
            if (var1.Name.Equals("description")) {
               var2 = a(var1);
            } else if (var1.Name.Equals("requirement")) {
               var5.Add(new ez(this, var1));
            }
         }
      }

      this.description = var2;
      this.ke = JavaCollections.UnmodifiableList(var5);
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

   public List<object> bk() {
      return this.ke;
   }

   public string toString() {
      return this.name.Length == 0 ? this.id : this.name;
   }
}

}
