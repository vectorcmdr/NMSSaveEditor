using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class aQ : Form {
   private Size dk;
   private Size dl;
   private Size dm;
   private Size dn = null;
   private TextBox @do;
   private TextBox dp;
   private static aQ dq;

   private aQ(Frame var1) {
      base(var1);
      this.FormBorderStyle = FormBorderStyle.FixedDialog; //(false);
      this.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
      this.Text = ("Expand Inventory");
      this/* setModal */(true);
      Panel var2 = new Panel();
      this.setContentPane(var2);
      var2.LayoutEnginenew TableLayoutPanel(0, 0));
      Panel var3 = new Panel();
      var3.LayoutEnginenew FormLayout(new ColumnSpec[]{FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("100px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("250px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC}));
      Label var4 = new Label("Width:");
      var3.Add(var4, "2, 2, left, center");
      this.@do = new TextBox();
      this.@do.addFocusListener(new aR(this));
      var3.Add(this.@do, "4, 2, fill, default");
      Label var5 = new Label("Height:");
      var3.Add(var5, "2, 4, left, center");
      this.dp = new TextBox();
      this.dp.addFocusListener(new aS(this));
      var3.Add(this.dp, "4, 4, fill, default");
      var2.Add(var3);
      Panel var6 = new Panel();
      var6.LayoutEnginenew FlowLayoutPanel(2));
      var2.Add(var6, "South");
      Button var7 = new Button("Save");
      var7.Click += (new aT(this));
      var6.Add(var7);
      this.getRootPane().setDefaultButton(var7);
      Button var8 = new Button("Cancel");
      var8.Click += (new aU(this));
      var6.Add(var8);
      this.getRootPane().registerKeyboardAction(new aV(this), Keys.getKeyStroke(27, 0), 2);
      this.PerformLayout();
   }

   private Size a(Size var1, Size var2, Size var3) {
      this.dk = var1;
      this.dl = var2;
      this.dm = var3;
      this.@do.Text = (Integer.toString(var1.width));
      this.dp.Text = (Integer.toString(var1.height));
      this.dn = null;
      this.StartPosition = FormStartPosition.CenterParent; //(this.DirectoryName);
      this.Show();
      return this.dn;
   }

   public static Size a(Container var0, Size var1, Size var2, Size var3) {
      if (dq == null) {
         Frame var4 = MessageBox.getFrameForComponent(var0);
         dq = new aQ(var4);
      }

      return dq.a(var1, var2, var3);
   }
   static TextBox a(aQ var0) {
      return var0.@do;
   }
   static Size b(aQ var0) {
      return var0.dk;
   }
   static Size c(aQ var0) {
      return var0.dl;
   }
   static Size d(aQ var0) {
      return var0.dm;
   }
   static TextBox e(aQ var0) {
      return var0.dp;
   }
   static void a(aQ var0, Size var1) {
      var0.dn = var1;
   }
}

}
