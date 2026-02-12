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
   private CheckBox @if;
   private ComboBox ig;
   private G bj;
   private ComboBox ih;
   private G hO;
   private G ii;
   // $FF: synthetic field
   eb ij;

   ec(eb var1, int var2) : base(aH.cH, aH.cH * 2) {
      this.ij = var1;
      this.index = var2;
      this.k("Wingman " + (var2 + 1));
      this.@if = new CheckBox();
      this.@if.Text = "Enabled";
      this.@if.addActionListener(new ed(this, var2));
      this.a((string)null, this.@if);
      this.setBorder(new LineBorder(Color.DarkGray));
      this.ig = new ComboBox();
      this.ig.setModel(new ee(this, var2));
      this.a("NPC Race", this.ig);
      this.bj = new ef(this, var2);
      this.a("NPC Seed", this.bj);
      this.ih = new ComboBox();
      this.ih.setModel(new eg(this, var2));
      this.a("Ship Type", this.ih);
      this.hO = new eh(this, var2);
      this.a("Ship Seed", this.hO);
      this.ii = new ei(this, var2);
      this.a("Pilot Rank", this.ii);
   }

   private void aQ() {
      this.@if.setSelected(eb.a(this.ij)[this.index].isEnabled());
      this.ig.setEnabled(eb.a(this.ij)[this.index].isEnabled());
      this.ig.setSelectedItem(eb.a(this.ij)[this.index].ed());
      this.bj.setEnabled(eb.a(this.ij)[this.index].isEnabled());
      this.bj.setText(eb.a(this.ij)[this.index].ee());
      this.ih.setEnabled(eb.a(this.ij)[this.index].isEnabled());
      this.ih.setSelectedItem(eb.a(this.ij)[this.index].ef());
      this.hO.setEnabled(eb.a(this.ij)[this.index].isEnabled());
      this.hO.setText(eb.a(this.ij)[this.index].eg());
      this.ii.setEnabled(eb.a(this.ij)[this.index].isEnabled());
      this.ii.setText(Convert.ToString(eb.a(this.ij)[this.index].eh()));
   }

   // $FF: synthetic method
   static CheckBox a(ec var0) {
      return var0.@if;
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
