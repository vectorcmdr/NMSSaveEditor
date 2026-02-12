using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class bu : Runnable {
   public bt es;
   public Application bv;

   public bu(bt var1, Application var2) {
      this.es = var1;
      this.bv = var2;
   }

   public void run() {
      bl.a(bt.a(this.es), bl.e(bt.a(this.es)).getSelectedRow());
      int var1;
      if (bl.b(bt.a(this.es)) < 0) {
         bl.a(bt.a(this.es), new er[0]);
         bl.b(bt.a(this.es), new er[0]);
         bl.h(bt.a(this.es)).Hide();
         bl.i(bt.a(this.es)).Hide();
         bl.j(bt.a(this.es)).Text = ("");
         bl.k(bt.a(this.es)).SelectedIndex = (-1);
         bl.l(bt.a(this.es)).Text = ("");
         bl.m(bt.a(this.es)).SelectedIndex = (-1);
         bl.n(bt.a(this.es)).Text = ("");
         bl.o(bt.a(this.es)).Text = ("");

         for(var1 = 0; var1 < bl.d(bt.a(this.es)).Length; ++var1) {
            bl.d(bt.a(this.es))[var1].Text = ("");
         }

         bl.p(bt.a(this.es)).SelectedIndex = (-1);
         bl.q(bt.a(this.es)).SelectedIndex = (-1);
         bl.r(bt.a(this.es)).SelectedIndex = (-1);
         bl.s(bt.a(this.es)).SelectedIndex = (-1);
         bl.t(bt.a(this.es)).SelectedIndex = (-1);
         bl.u(bt.a(this.es)).Text = ("");
         bl.v(bt.a(this.es)).Text = ("");
         bl.w(bt.a(this.es)).Text = ("");
         bl.x(bt.a(this.es)).Text = ("");
         bl.h(bt.a(this.es)).Refresh();
         bl.i(bt.a(this.es)).Refresh();
         bl.y(bt.a(this.es)).Text = ("");
         bl.z(bt.a(this.es)).Hide();
         bl.A(bt.a(this.es)).Enabled = (false);
         bl.g(bt.a(this.es)).Enabled = (false);
      } else {
         bl.a(bt.a(this.es), er.a(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].da()));
         bl.b(bt.a(this.es), er.b(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].da()));
         bl.h(bt.a(this.es)).Show();
         bl.i(bt.a(this.es)).Show();
         bl.j(bt.a(this.es)).Text = (bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].Name);
         bl.k(bt.a(this.es)).SelectedItem = (bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].da());
         bl.l(bt.a(this.es)).Text = (bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].cW().ToString());
         bl.m(bt.a(this.es)).m(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].db());
         bl.n(bt.a(this.es)).Text = (bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].cU());
         bl.o(bt.a(this.es)).Text = (bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].cV());

         for(var1 = 0; var1 < bl.d(bt.a(this.es)).Length; ++var1) {
            bl.d(bt.a(this.es))[var1].Text = (Integer.toString(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].aq(var1)));
         }

         bl.p(bt.a(this.es)).SelectedItem = (bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].ar(0));
         bl.q(bt.a(this.es)).SelectedItem = (bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].ar(1));
         bl.r(bt.a(this.es)).SelectedItem = (bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].ar(2));
         bl.s(bt.a(this.es)).SelectedItem = (bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].ar(3));
         bl.t(bt.a(this.es)).SelectedItem = (bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].ar(4));
         bl.u(bt.a(this.es)).Text = (Integer.toString(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].dc()));
         bl.v(bt.a(this.es)).Text = (Integer.toString(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].dd()));
         bl.w(bt.a(this.es)).Text = (Integer.toString(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].de()));
         bl.x(bt.a(this.es)).Text = (Integer.toString(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].df()));
         if (this.bv.j(bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].getIndex())) {
            bl.y(bt.a(this.es)).Text = ("Status: On Mission");
            bl.y(bt.a(this.es)).setForeground(Color.Blue);
            bl.z(bt.a(this.es)).Hide();
         } else if (bl.c(bt.a(this.es))[bl.b(bt.a(this.es))].dh() > 0) {
            bl.y(bt.a(this.es)).Text = ("Status: Damaged!");
            bl.y(bt.a(this.es)).setForeground(Color.Red);
            bl.z(bt.a(this.es)).Show();
         } else {
            bl.y(bt.a(this.es)).Text = ("");
            bl.z(bt.a(this.es)).Hide();
         }

         bl.h(bt.a(this.es)).Refresh();
         bl.i(bt.a(this.es)).Refresh();
         bl.p(bt.a(this.es)).Refresh();
         bl.q(bt.a(this.es)).Refresh();
         bl.r(bt.a(this.es)).Refresh();
         bl.s(bt.a(this.es)).Refresh();
         bl.t(bt.a(this.es)).Refresh();
         bl.A(bt.a(this.es)).Enabled = (bl.c(bt.a(this.es)).Length > 1);
         bl.g(bt.a(this.es)).Enabled = (bl.c(bt.a(this.es)).Length < 30 || en.aS());
      }
   }
}


#else

public class bu
{
   public bu() { }
   public bu(params object[] args) { }
   public bt es = default;
   public Application bv = default;
   public void run() { }
}

#endif

}
