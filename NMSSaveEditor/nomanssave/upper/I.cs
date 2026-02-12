using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class I : em {
   private ComboBox bh = new ComboBox();
   private TextBox bi;
   private G bj;
   private ComboBox bk;
   private G bl;
   private TextBox bm;
   private Button bn;
   private Button bo;
   private Button bp;
   private bO bq;
   private ge br;

   I(Application var1) {
      this.bh.setModel(new J(this));
      this.a("Base NPC", true, this.bh);
      this.bi = new TextBox();
      this.bi.setEnabled(false);
      this.a("Race", this.bi);
      this.bj = new K(this);
      this.bj.setEnabled(false);
      this.a("Seed", this.bj);
      this.Y();
      this.bk = new ComboBox();
      this.bk.setModel(new L(this));
      this.a("Base Info", true, this.bk);
      this.bl = new M(this);
      this.a("Name", this.bl);
      this.bm = new TextBox();
      this.bm.setEnabled(false);
      this.a("Items", this.bm);
      Panel var2 = new Panel();
      this.bn = new Button("Backup");
      this.bn.addActionListener(new N(this, var1));
      var2.Add(this.bn);
      this.bo = new Button("Restore");
      this.bo.addActionListener(new O(this, var1));
      var2.Add(this.bo);
      this.bp = new Button("Move Base Computer");
      this.bp.addActionListener(new P(this, var1));
      var2.Add(this.bp);
      this.a((Control)var2);
      this.bq = new bO(var1);
      this.b(this.bq);
   }

   void w() {
      this.bq.w();
   }

   void x() {
      this.bq.x();
   }

   void y() {
      this.bq.y();
   }

   void A() {
      this.bq.A();
   }

   void a(gt var1) {
      this.bq.a(var1);
   }

   ge O() {
      return this.br;
   }

   void a(ge var1) {
      this.br = var1;
      List var2;
      if (var1 == null) {
         var2 = new List<object>();
         this.bh.setSelectedIndex(-1);
         this.bk.setSelectedIndex(-1);
      } else {
         var2 = var1.cC();
         this.bh.setSelectedIndex(var1.cD().Count == 0 ? -1 : 0);
         this.bk.setSelectedIndex(var1.cE().Count == 0 ? -1 : 0);
      }

      this.bh.updateUI();
      this.bk.updateUI();
      this.bq.a(var2);
   }
   static ge a(I var0) {
      return var0.br;
   }
   static TextBox b(I var0) {
      return var0.bi;
   }
   static G c(I var0) {
      return var0.bj;
   }
   static ComboBox d(I var0) {
      return var0.bh;
   }
   static TextBox e(I var0) {
      return var0.bm;
   }
   static G f(I var0) {
      return var0.bl;
   }
   static Button g(I var0) {
      return var0.bn;
   }
   static Button h(I var0) {
      return var0.bo;
   }
   static Button i(I var0) {
      return var0.bp;
   }
   static ComboBox j(I var0) {
      return var0.bk;
   }
}

}
