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

public class ec : ba {
   private int index;
   private CheckBox if;
   private ComboBox ig;
   private G bj;
   private ComboBox ih;
   private G hO;
   private G ii;
   // $FF: synthetic field
   eb ij;

   ec(eb var1, int var2) {
      base(aH.cH, aH.cH * 2);
      this.ij = var1;
      this.index = var2;
      this.k("Wingman " + (var2 + 1));
      this.if = new CheckBox("Enabled");
      this.if.AddActionListener(new ed(this, var2));
      this.a((string)null, this.if);
      this.SetBorder(new LineBorder(Color.DarkGray));
      this.ig = new ComboBox();
      this.ig.SetModel(new ee(this, var2));
      this.a("NPC Race", this.ig);
      this.bj = new ef(this, var2);
      this.a("NPC Seed", this.bj);
      this.ih = new ComboBox();
      this.ih.SetModel(new eg(this, var2));
      this.a("Ship Type", this.ih);
      this.hO = new eh(this, var2);
      this.a("Ship Seed", this.hO);
      this.ii = new ei(this, var2);
      this.a("Pilot Rank", this.ii);
   }

   private void aQ() {
      this.if.setSelected(eb.a(this.ij)[this.index].Enabled);
      this.ig.SetEnabled(eb.a(this.ij)[this.index].Enabled);
      this.ig.SetSelectedItem(eb.a(this.ij)[this.index].ed());
      this.bj.SetEnabled(eb.a(this.ij)[this.index].Enabled);
      this.bj.SetText(eb.a(this.ij)[this.index].ee());
      this.ih.SetEnabled(eb.a(this.ij)[this.index].Enabled);
      this.ih.SetSelectedItem(eb.a(this.ij)[this.index].ef());
      this.hO.SetEnabled(eb.a(this.ij)[this.index].Enabled);
      this.hO.SetText(eb.a(this.ij)[this.index].eg());
      this.ii.SetEnabled(eb.a(this.ij)[this.index].Enabled);
      this.ii.SetText(Convert.ToString(eb.a(this.ij)[this.index].eh()));
   }

   // $FF: synthetic method
   static CheckBox a(ec var0) {
      return var0.if;
   }

   // $FF: synthetic method
   static ComboBox b(ec var0) {
      return var0.ig;
   }

   // $FF: synthetic method
   static G c(ec var0) {
      return var0.bj;
   }

   // $FF: synthetic method
   static ComboBox d(ec var0) {
      return var0.ih;
   }

   // $FF: synthetic method
   static G e(ec var0) {
      return var0.hO;
   }

   // $FF: synthetic method
   static G f(ec var0) {
      return var0.ii;
   }

   // $FF: synthetic method
   static void g(ec var0) {
      var0.aQ();
   }

   // $FF: synthetic method
   static eb h(ec var0) {
      return var0.ij;
   }
}

}
