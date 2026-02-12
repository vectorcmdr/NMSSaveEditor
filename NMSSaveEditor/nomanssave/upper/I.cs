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

public class I : em {
   public ComboBox bh = new ComboBox();
   public TextBox bi;
   public G bj;
   public ComboBox bk;
   public G bl;
   public TextBox bm;
   public Button bn;
   public Button bo;
   public Button bp;
   public bO bq;
   public ge br;

   public I(Application var1) {
      this.bh.SetModel(new J(this));
      this.a("Base NPC", true, this.bh);
      this.bi = new TextBox();
      this.bi.SetEnabled(false);
      this.a("Race", this.bi);
      this.bj = new K(this);
      this.bj.SetEnabled(false);
      this.a("Seed", this.bj);
      this.Y();
      this.bk = new ComboBox();
      this.bk.SetModel(new L(this));
      this.a("Base Info", true, this.bk);
      this.bl = new M(this);
      this.a("Name", this.bl);
      this.bm = new TextBox();
      this.bm.SetEnabled(false);
      this.a("Items", this.bm);
      Panel var2 = new Panel();
      this.bn = new Button() { Text = "Backup" };
      this.bn.AddActionListener(new N(this, var1));
      var2.Add(this.bn);
      this.bo = new Button() { Text = "Restore" };
      this.bo.AddActionListener(new O(this, var1));
      var2.Add(this.bo);
      this.bp = new Button() { Text = "Move Base Computer" };
      this.bp.AddActionListener(new P(this, var1));
      var2.Add(this.bp);
      this.a((Control)var2);
      this.bq = new bO(var1);
      this.b(this.bq);
   }

   public void w() {
      this.bq.w();
   }

   public void x() {
      this.bq.x();
   }

   public void y() {
      this.bq.y();
   }

   public void A() {
      this.bq.A();
   }

   public void a(gt var1) {
      this.bq.a(var1);
   }

   public ge O() {
      return this.br;
   }

   public void a(ge var1) {
      this.br = var1;
      List<object> var2;
      if (var1 == null) {
         var2 = new List<object>();
         this.bh.SetSelectedIndex(-1);
         this.bk.SetSelectedIndex(-1);
      } else {
         var2 = var1.cC();
         this.bh.SetSelectedIndex(var1.cD().Count == 0 ? -1 : 0);
         this.bk.SetSelectedIndex(var1.cE().Count == 0 ? -1 : 0);
      }

      this.bh.updateUI();
      this.bk.updateUI();
      this.bq.a(var2);
   }

   // $FF: synthetic method
   public static ge a(I var0) {
      return var0.br;
   }

   // $FF: synthetic method
   public static TextBox b(I var0) {
      return var0.bi;
   }

   // $FF: synthetic method
   public static G c(I var0) {
      return var0.bj;
   }

   // $FF: synthetic method
   public static ComboBox d(I var0) {
      return var0.bh;
   }

   // $FF: synthetic method
   public static TextBox e(I var0) {
      return var0.bm;
   }

   // $FF: synthetic method
   public static G f(I var0) {
      return var0.bl;
   }

   // $FF: synthetic method
   public static Button g(I var0) {
      return var0.bn;
   }

   // $FF: synthetic method
   public static Button h(I var0) {
      return var0.bo;
   }

   // $FF: synthetic method
   public static Button i(I var0) {
      return var0.bp;
   }

   // $FF: synthetic method
   public static ComboBox j(I var0) {
      return var0.bk;
   }
}

}
