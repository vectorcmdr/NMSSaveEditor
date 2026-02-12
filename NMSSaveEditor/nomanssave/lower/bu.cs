using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class bu : Runnable {
   bt es;
   private Application bv;

   bu(bt var1, Application var2) {
      this.es = var1;
      this.bv = var2;
   }

   public void run() {
      bl.a(bt.a(this.es), bl.e(bt.a(this.es)).getSelectedRow());
      int var1;
      if (bl.b(bt.a(this.es)) < 0) {
         bl.a(bt.a(this.es), new er[0]);
         bl.b(bt.a(this.es), new er[0]);
         bl.h(bt.a(this.es)).setVisible(false);
         bl.i(bt.a(this.es)).setVisible(false);
         bl.j(bt.a(this.es)).setText("");
         bl.k(bt.a(this.es)).setSelectedIndex(-1);
         bl.l(bt.a(this.es)).setText("");
         bl.m(bt.a(this.es)).setSelectedIndex(-1);
         bl.n(bt.a(this.es)).setText("");
         bl.o(bt.a(this.es)).setText("");

         for(var1 = 0; var1 < bl.d(bt.a(this.es)).Length; ++var1) {
            bl.d(bt.a(this.es))[var1].setText("");
         }

         bl.p(bt.a(this.es)).setSelectedIndex(-1);
         bl.q(bt.a(this.es)).setSelectedIndex(-1);
         bl.r(bt.a(this.es)).setSelectedIndex(-1);
         bl.s(bt.a(this.es)).setSelectedIndex(-1);
         bl.t(bt.a(this.es)).setSelectedIndex(-1);
         bl.u(bt.a(this.es)).setText("");
         bl.v(bt.a(this.es)).setText("");
         bl.w(bt.a(this.es)).setText("");
         bl.x(bt.a(this.es)).setText("");
         bl.h(bt.a(this.es)).updateUI();
         bl.i(bt.a(this.es)).updateUI();
         bl.y(bt.a(this.es)).setText("");
         bl.z(bt.a(this.es)).setVisible(false);
         bl.A(bt.a(this.es)).setEnabled(false);
         bl.g(bt.a(this.es)).setEnabled(false);
      } else {
         bl.a(bt.a(this.es), er.a(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].da()));
         bl.b(bt.a(this.es), er.b(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].da()));
         bl.h(bt.a(this.es)).setVisible(true);
         bl.i(bt.a(this.es)).setVisible(true);
         bl.j(bt.a(this.es)).setText(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].getName());
         bl.k(bt.a(this.es)).setSelectedItem(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].da());
         bl.l(bt.a(this.es)).setText(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].cW().ToString());
         bl.m(bt.a(this.es)).m(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].db());
         bl.n(bt.a(this.es)).setText(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].cU());
         bl.o(bt.a(this.es)).setText(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].cV());

         for(var1 = 0; var1 < bl.d(bt.a(this.es)).Length; ++var1) {
            bl.d(bt.a(this.es))[var1].setText(Integer.toString(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].aq(var1)));
         }

         bl.p(bt.a(this.es)).setSelectedItem(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].ar(0));
         bl.q(bt.a(this.es)).setSelectedItem(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].ar(1));
         bl.r(bt.a(this.es)).setSelectedItem(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].ar(2));
         bl.s(bt.a(this.es)).setSelectedItem(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].ar(3));
         bl.t(bt.a(this.es)).setSelectedItem(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].ar(4));
         bl.u(bt.a(this.es)).setText(Integer.toString(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].dc()));
         bl.v(bt.a(this.es)).setText(Integer.toString(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].dd()));
         bl.w(bt.a(this.es)).setText(Integer.toString(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].de()));
         bl.x(bt.a(this.es)).setText(Integer.toString(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].df()));
         if (this.bv.j(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].getIndex())) {
            bl.y(bt.a(this.es)).setText("Status: On Mission");
            bl.y(bt.a(this.es)).setForeground(Color.BLUE);
            bl.z(bt.a(this.es)).setVisible(false);
         } else if (bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].dh() > 0) {
            bl.y(bt.a(this.es)).setText("Status: Damaged!");
            bl.y(bt.a(this.es)).setForeground(Color.RED);
            bl.z(bt.a(this.es)).setVisible(true);
         } else {
            bl.y(bt.a(this.es)).setText("");
            bl.z(bt.a(this.es)).setVisible(false);
         }

         bl.h(bt.a(this.es)).updateUI();
         bl.i(bt.a(this.es)).updateUI();
         bl.p(bt.a(this.es)).updateUI();
         bl.q(bt.a(this.es)).updateUI();
         bl.r(bt.a(this.es)).updateUI();
         bl.s(bt.a(this.es)).updateUI();
         bl.t(bt.a(this.es)).updateUI();
         bl.A(bt.a(this.es)).setEnabled(bl.c(bt.a(this.es)).Length > 1);
         bl.g(bt.a(this.es)).setEnabled(bl.c(bt.a(this.es)).Length < 30 || en.aS());
      }
   }
}

}
