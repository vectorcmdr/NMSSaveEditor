using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class ec : ba {
   public int index;
   public CheckBox @if;
   public ComboBox ig;
   public G bj;
   public ComboBox ih;
   public G hO;
   public G ii;
   public eb ij;

public ec(eb var1, int var2) : base(aH.cH, aH.cH * 2) {
      this.ij = var1;
      this.index = var2;
      this.k("Wingman " + (var2 + 1));
      this.@if = new CheckBox() { Text = "Enabled" };
      // PORT_TODO: this.@if.Click += (new ed(this, var2));
      this.a(null, this.@if);
      this.Padding = new Padding(0); /* setBorder */ //(new LineBorder(Color.DARK_GRAY));
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

   public void aQ() {
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
      // PORT_TODO: this.ii.Text = ((eb.a(this.ij).ToString()[this.index].eh()));
   }
   public static CheckBox a(ec var0) {
      return var0.@if;
   }
   public static ComboBox b(ec var0) {
      return var0.ig;
   }
   public static G c(ec var0) {
      return var0.bj;
   }
   public static ComboBox d(ec var0) {
      return var0.ih;
   }
   public static G e(ec var0) {
      return var0.hO;
   }
   public static G f(ec var0) {
      return var0.ii;
   }
   public static void g(ec var0) {
      var0.aQ();
   }
   public static eb h(ec var0) {
      return var0.ij;
   }
}



}
