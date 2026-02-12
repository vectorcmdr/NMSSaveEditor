using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class ec : ba {
   private int index;
   private CheckBox @if;
   private ComboBox ig;
   private G bj;
   private ComboBox ih;
   private G hO;
   private G ii;
   eb ij;

   ec(eb var1, int var2) {
      base(aH.cH, aH.cH * 2);
      this.ij = var1;
      this.index = var2;
      this.k("Wingman " + (var2 + 1));
      this.@if = new CheckBox("Enabled");
      this.@if.Click += (new ed(this, var2));
      this.a((string)null, this.@if);
      this.setBorder(new LineBorder(Color.DARK_GRAY));
      this.ig = new ComboBox();
      this.ig.DataSource = (new ee(this, var2));
      this.a("NPC Race", this.ig);
      this.bj = new ef(this, var2);
      this.a("NPC Seed", this.bj);
      this.ih = new ComboBox();
      this.ih.DataSource = (new eg(this, var2));
      this.a("Ship Type", this.ih);
      this.hO = new eh(this, var2);
      this.a("Ship Seed", this.hO);
      this.ii = new ei(this, var2);
      this.a("Pilot Rank", this.ii);
   }

   private void aQ() {
      this.@if.Checked = (eb.a(this.ij)[this.index].Enabled);
      this.ig.Enabled = (eb.a(this.ij)[this.index].Enabled);
      this.ig.SelectedItem = (eb.a(this.ij)[this.index].ed());
      this.bj.Enabled = (eb.a(this.ij)[this.index].Enabled);
      this.bj.Text = (eb.a(this.ij)[this.index].ee());
      this.ih.Enabled = (eb.a(this.ij)[this.index].Enabled);
      this.ih.SelectedItem = (eb.a(this.ij)[this.index].ef());
      this.hO.Enabled = (eb.a(this.ij)[this.index].Enabled);
      this.hO.Text = (eb.a(this.ij)[this.index].eg());
      this.ii.Enabled = (eb.a(this.ij)[this.index].Enabled);
      this.ii.Text = (Integer.toString(eb.a(this.ij)[this.index].eh()));
   }
   static CheckBox a(ec var0) {
      return var0.if;
   }
   static ComboBox b(ec var0) {
      return var0.ig;
   }
   static G c(ec var0) {
      return var0.bj;
   }
   static ComboBox d(ec var0) {
      return var0.ih;
   }
   static G e(ec var0) {
      return var0.hO;
   }
   static G f(ec var0) {
      return var0.ii;
   }
   static void g(ec var0) {
      var0.aQ();
   }
   static eb h(ec var0) {
      return var0.ij;
   }
}

}
