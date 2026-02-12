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

public class bu : Runnable {
   // $FF: synthetic field
   public bt es;
   // $FF: synthetic field
   public Application bv;

   public bu(bt var1, Application var2) {
      this.es = var1;
      this.bv = var2;
   }

   public void run() {
      bl.a(bt.a(this.es), bl.e(bt.a(this.es)).GetSelectedRow());
      int var1;
      if (bl.b(bt.a(this.es)) < 0) {
         bl.a(bt.a(this.es), new er[0]);
         bl.b(bt.a(this.es), new er[0]);
         bl.h(bt.a(this.es)).SetVisible(false);
         bl.i(bt.a(this.es)).SetVisible(false);
         bl.j(bt.a(this.es)).SetText("");
         bl.k(bt.a(this.es)).SetSelectedIndex(-1);
         bl.l(bt.a(this.es)).SetText("");
         bl.m(bt.a(this.es)).SetSelectedIndex(-1);
         bl.n(bt.a(this.es)).SetText("");
         bl.o(bt.a(this.es)).SetText("");

         for(var1 = 0; var1 < bl.d(bt.a(this.es)).Length; ++var1) {
            bl.d(bt.a(this.es))[var1].SetText("");
         }

         bl.p(bt.a(this.es)).SetSelectedIndex(-1);
         bl.q(bt.a(this.es)).SetSelectedIndex(-1);
         bl.r(bt.a(this.es)).SetSelectedIndex(-1);
         bl.s(bt.a(this.es)).SetSelectedIndex(-1);
         bl.t(bt.a(this.es)).SetSelectedIndex(-1);
         bl.u(bt.a(this.es)).SetText("");
         bl.v(bt.a(this.es)).SetText("");
         bl.w(bt.a(this.es)).SetText("");
         bl.x(bt.a(this.es)).SetText("");
         bl.h(bt.a(this.es)).updateUI();
         bl.i(bt.a(this.es)).updateUI();
         bl.y(bt.a(this.es)).SetText("");
         bl.z(bt.a(this.es)).SetVisible(false);
         bl.A(bt.a(this.es)).SetEnabled(false);
         bl.g(bt.a(this.es)).SetEnabled(false);
      } else {
         bl.a(bt.a(this.es), er.a(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].da()));
         bl.b(bt.a(this.es), er.b(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].da()));
         bl.h(bt.a(this.es)).SetVisible(true);
         bl.i(bt.a(this.es)).SetVisible(true);
         bl.j(bt.a(this.es)).SetText(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].Name);
         bl.k(bt.a(this.es)).SetSelectedItem(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].da());
         bl.l(bt.a(this.es)).SetText(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].cW().ToString());
         bl.m(bt.a(this.es)).m(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].db());
         bl.n(bt.a(this.es)).SetText(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].cU());
         bl.o(bt.a(this.es)).SetText(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].cV());

         for(var1 = 0; var1 < bl.d(bt.a(this.es)).Length; ++var1) {
            bl.d(bt.a(this.es))[var1].SetText(Convert.ToString(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].aq(var1)));
         }

         bl.p(bt.a(this.es)).SetSelectedItem(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].ar(0));
         bl.q(bt.a(this.es)).SetSelectedItem(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].ar(1));
         bl.r(bt.a(this.es)).SetSelectedItem(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].ar(2));
         bl.s(bt.a(this.es)).SetSelectedItem(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].ar(3));
         bl.t(bt.a(this.es)).SetSelectedItem(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].ar(4));
         bl.u(bt.a(this.es)).SetText(Convert.ToString(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].dc()));
         bl.v(bt.a(this.es)).SetText(Convert.ToString(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].dd()));
         bl.w(bt.a(this.es)).SetText(Convert.ToString(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].de()));
         bl.x(bt.a(this.es)).SetText(Convert.ToString(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].df()));
         if (this.bv.j(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].getIndex())) {
            bl.y(bt.a(this.es)).SetText("Status: On Mission");
            bl.y(bt.a(this.es)).SetForeground(Color.Blue);
            bl.z(bt.a(this.es)).SetVisible(false);
         } else if (bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].dh() > 0) {
            bl.y(bt.a(this.es)).SetText("Status: Damaged!");
            bl.y(bt.a(this.es)).SetForeground(Color.Red);
            bl.z(bt.a(this.es)).SetVisible(true);
         } else {
            bl.y(bt.a(this.es)).SetText("");
            bl.z(bt.a(this.es)).SetVisible(false);
         }

         bl.h(bt.a(this.es)).updateUI();
         bl.i(bt.a(this.es)).updateUI();
         bl.p(bt.a(this.es)).updateUI();
         bl.q(bt.a(this.es)).updateUI();
         bl.r(bt.a(this.es)).updateUI();
         bl.s(bt.a(this.es)).updateUI();
         bl.t(bt.a(this.es)).updateUI();
         bl.A(bt.a(this.es)).SetEnabled(bl.c(bt.a(this.es)).Length > 1);
         bl.g(bt.a(this.es)).SetEnabled(bl.c(bt.a(this.es)).Length < 30 || en.aS());
      }
   }
}

}
