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

public class aQ : Form {
   public Size dk;
   public Size dl;
   public Size dm;
   public Size? dn = null;
   public TextBox @do;
   public TextBox dp;
   public static aQ dq;

   public aQ(Form var1) {
      this.setResizable(false);
      this.setTitle("Expand Inventory");
      this.setModal(true);
      Panel var2 = new Panel();
      this.setContentPane(var2);
      var2.setLayout(new BorderLayout(0, 0));
      Panel var3 = new Panel();
      var3.setLayout(new FormLayout(new ColumnSpec[]{FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("100px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("250px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, "default", FormFactory.LINE_GAP_ROWSPEC, "default", FormFactory.LINE_GAP_ROWSPEC}));
      Label var4 = new Label();
      var4.Text = "Width:";
      var3.add(var4, "2, 2, left, center");
      this.@do = new TextBox();
      this.@do.addFocusListener(new aR(this));
      var3.add(this.@do, "4, 2, fill, default");
      Label var5 = new Label();
      var5.Text = "Height:";
      var3.add(var5, "2, 4, left, center");
      this.dp = new TextBox();
      this.dp.addFocusListener(new aS(this));
      var3.add(this.dp, "4, 4, fill, default");
      var2.add(var3);
      Panel var6 = new Panel();
      var6.setLayout(new FlowLayout(2));
      var2.add(var6, "South");
      Button var7 = new Button();
      var7.Text = "Save";
      var7.addActionListener(new aT(this));
      var6.add(var7);
      Button var8 = new Button();
      var8.Text = "Cancel";
      var8.addActionListener(new aU(this));
      var6.add(var8);
      this.pack();
   }

   public Size? a(Size var1, Size var2, Size var3) {
      this.dk = var1;
      this.dl = var2;
      this.dm = var3;
      this.@do.setText(Convert.ToString(var1.Width));
      this.dp.setText(Convert.ToString(var1.Height));
      this.dn = null;
      this.setLocationRelativeTo(this.Parent);
      this.setVisible(true);
      return this.dn;
   }

   public static Size? a(Control var0, Size var1, Size var2, Size var3) {
      if (dq == null) {
         Form var4 = JOptionPane.getFrameForComponent(var0);
         dq = new aQ(var4);
      }

      return dq.a(var1, var2, var3);
   }

   // $FF: synthetic method
   public static TextBox a(aQ var0) {
      return var0.@do;
   }

   // $FF: synthetic method
   public static Size b(aQ var0) {
      return var0.dk;
   }

   // $FF: synthetic method
   public static Size c(aQ var0) {
      return var0.dl;
   }

   // $FF: synthetic method
   public static Size d(aQ var0) {
      return var0.dm;
   }

   // $FF: synthetic method
   public static TextBox e(aQ var0) {
      return var0.dp;
   }

   // $FF: synthetic method
   public static void a(aQ var0, Size? var1) {
      var0.dn = var1;
   }
}

}
