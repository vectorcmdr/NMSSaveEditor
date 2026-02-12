using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


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
      this.bh.DataSource = (new J(this));
      this.a("Base NPC", true, this.bh);
      this.bi = new TextBox();
      this.bi.Enabled = (false);
      this.a("Race", this.bi);
      this.bj = new K(this);
      this.bj.Enabled = (false);
      this.a("Seed", this.bj);
      this.Y();
      this.bk = new ComboBox();
      this.bk.DataSource = (new L(this));
      this.a("Base Info", true, this.bk);
      this.bl = new M(this);
      this.a("Name", this.bl);
      this.bm = new TextBox();
      this.bm.Enabled = (false);
      this.a("Items", this.bm);
      Panel var2 = new Panel();
      this.bn = new Button() { Text = "Backup" };
      this.bn.Click += (new N(this, var1));
      var2.Add(this.bn);
      this.bo = new Button() { Text = "Restore" };
      this.bo.Click += (new O(this, var1));
      var2.Add(this.bo);
      this.bp = new Button() { Text = "Move Base Computer" };
      this.bp.Click += (new P(this, var1));
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
         this.bh.SelectedIndex = (-1);
         this.bk.SelectedIndex = (-1);
      } else {
         var2 = var1.cC();
         this.bh.SelectedIndex = (var1.cD().Count == 0 ? -1 : 0);
         this.bk.SelectedIndex = (var1.cE().Count == 0 ? -1 : 0);
      }

      this.bh.Refresh();
      this.bk.Refresh();
      this.bq.a(var2);
   }
   public static ge a(I var0) {
      return var0.br;
   }
   public static TextBox b(I var0) {
      return var0.bi;
   }
   public static G c(I var0) {
      return var0.bj;
   }
   public static ComboBox d(I var0) {
      return var0.bh;
   }
   public static TextBox e(I var0) {
      return var0.bm;
   }
   public static G f(I var0) {
      return var0.bl;
   }
   public static Button g(I var0) {
      return var0.bn;
   }
   public static Button h(I var0) {
      return var0.bo;
   }
   public static Button i(I var0) {
      return var0.bp;
   }
   public static ComboBox j(I var0) {
      return var0.bk;
   }
}


#else

public class I
{
   public I() { }
   public I(params object[] args) { }
   public ComboBox bh = default;
   public TextBox bi = default;
   public G bj = default;
   public ComboBox bk = default;
   public G bl = default;
   public TextBox bm = default;
   public Button bn = default;
   public Button bo = default;
   public Button bp = default;
   public bO bq = default;
   public ge br = default;
   public void w() { }
   public void x() { }
   public void y() { }
   public void A() { }
   public void a(gt var1) { }
   public ge O() { return default; }
   public static TextBox b(I var0) { return default; }
   public static G c(I var0) { return default; }
   public static ComboBox d(I var0) { return default; }
   public static TextBox e(I var0) { return default; }
   public static G f(I var0) { return default; }
   public static Button g(I var0) { return default; }
   public static Button h(I var0) { return default; }
   public static Button i(I var0) { return default; }
   public static ComboBox j(I var0) { return default; }
}

#endif

}
