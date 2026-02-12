using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class aQ : Form {
   public Size dk;
   public Size dl;
   public Size dm;
   // PORT_TODO: public Size dn = null;
   public TextBox @do;
   public TextBox dp;
   public static aQ dq;

public aQ(Frame var1) : base(var1) {
      this.FormBorderStyle = FormBorderStyle.FixedDialog; //(false);
      // setModalExclusionType not available in WinForms
      this.Text = ("Expand Inventory");
      // PORT_TODO: // PORT_TODO: this/* setModal */(true);
      Panel var2 = new Panel();
      this.setContentPane(var2);
      var2.SuspendLayout(); // TODO: set layout new TableLayoutPanel());
      Panel var3 = new Panel();
      // TODO: var3.SuspendLayout(); // TODO: set layout /* FormLayout */ null, FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("250px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC}));
      Label var4 = new Label() { Text = "Width:" };
      var3.Controls.Add(var4);
      this.@do = new TextBox();
      this.@do.addFocusListener(new aR(this));
      var3.Add(this.@do, "4, 2, fill, default");
      Label var5 = new Label() { Text = "Height:" };
      var3.Controls.Add(var5);
      this.dp = new TextBox();
      this.dp.addFocusListener(new aS(this));
      var3.Add(this.dp, "4, 4, fill, default");
      var2.Add(var3);
      Panel var6 = new Panel();
      var6.SuspendLayout(); // TODO: set layout new FlowLayoutPanel(2));
      var2.Controls.Add(var6);
      Button var7 = new Button() { Text = "Save" };
      // PORT_TODO: var7.Click += (new aT(this));
      var6.Add(var7);
      this.getRootPane().setDefaultButton(var7);
      Button var8 = new Button() { Text = "Cancel" };
      // PORT_TODO: var8.Click += (new aU(this));
      var6.Add(var8);
      // PORT_TODO: this.getRootPane().registerKeyboardAction(new aV(this), /* KeyStroke */ Keys.None /* (27, 0) */, 2);
      this.PerformLayout();
   }

   public Size a(Size var1, Size var2, Size var3) {
      this.dk = var1;
      this.dl = var2;
      this.dm = var3;
      // PORT_TODO: this.@do.Text = ((var1.width).ToString());
      // PORT_TODO: this.dp.Text = ((var1.height).ToString());
      // PORT_TODO: this.dn = null;
      this.StartPosition = FormStartPosition.CenterParent; //(this.DirectoryName);
      this.Show();
      // PORT_TODO: return this.dn;
   }

   public static Size a(Container var0, Size var1, Size var2, Size var3) {
      if (dq == null) {
         Frame var4 = null;
         dq = new aQ(var4);
      }

      return dq.a(var1, var2, var3);
   }
   static TextBox a(aQ var0) {
      return var0.@do;
   }
   public static Size b(aQ var0) {
      return var0.dk;
   }
   public static Size c(aQ var0) {
      return var0.dl;
   }
   public static Size d(aQ var0) {
      return var0.dm;
   }
   public static TextBox e(aQ var0) {
      return var0.dp;
   }
   static void a(aQ var0, Size var1) {
      // PORT_TODO: var0.dn = var1;
   }
}



}
