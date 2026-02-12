using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class aW : Form {
   public TextBox ds;
   public CheckBox dt;
   public CheckBox du;
   public RadioButton dv;
   public RadioButton dw;
   public static aW dx;

// PORT_TODO: public aW(cy var1) : base(var1) {
      // PORT_TODO: this.Size = new Size(400, 250);
      // PORT_TODO: this.FormBorderStyle = FormBorderStyle.FixedDialog; //(false);
      // setModalExclusionType not available in WinForms
      // PORT_TODO: this.Text = ("Find");
      // PORT_TODO: // PORT_TODO: this/* setModal */(true);
      // PORT_TODO: Panel var2 = new Panel();
      // PORT_TODO: this.setContentPane(var2);
      // PORT_TODO: var2.SuspendLayout(); // TODO: set layout new TableLayoutPanel());
      // PORT_TODO: Panel var3 = new Panel();
      // TODO: var3.SuspendLayout(); // TODO: set layout /* FormLayout */ null, FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("250px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC}));
      // PORT_TODO: Label var4 = new Label() { Text = "Find:" };
      // PORT_TODO: var3.Controls.Add(var4);
      // PORT_TODO: this.ds = new TextBox();
      // PORT_TODO: var3.Add(this.ds, "4, 2, fill, default");
      // PORT_TODO: var2.Add(var3);
      // PORT_TODO: Panel var5 = new Panel();
      // PORT_TODO: var5.SuspendLayout(); // TODO: set layout new TableLayoutPanel());
      // PORT_TODO: var5.Padding = new Padding(0); /* setBorder */ //(null /* CompoundBorder */);
      // PORT_TODO: this.dv = new RadioButton() { Text = "Forward" };
      // PORT_TODO: this.dv.Checked = (true);
      // PORT_TODO: var5.Add(this.dv);
      // PORT_TODO: this.dw = new RadioButton() { Text = "Backward" };
      // PORT_TODO: var5.Add(this.dw);
      // PORT_TODO: object var6 = new object();
      // PORT_TODO: var6.Add(this.dv);
      // PORT_TODO: var6.Add(this.dw);
      // PORT_TODO: var3.Controls.Add(var5);
      // PORT_TODO: Panel var7 = new Panel();
      // PORT_TODO: var7.SuspendLayout(); // TODO: set layout new TableLayoutPanel());
      // PORT_TODO: var7.Padding = new Padding(0); /* setBorder */ //(null /* CompoundBorder */);
      // PORT_TODO: this.dt = new CheckBox() { Text = "Case Sensitive" };
      // PORT_TODO: this.dt.Checked = (true);
      // PORT_TODO: var7.Add(this.dt);
      // PORT_TODO: this.du = new CheckBox() { Text = "Wrap Search" };
      // PORT_TODO: var7.Add(this.du);
      // PORT_TODO: var3.Controls.Add(var7);
      // PORT_TODO: Panel var8 = new Panel();
      // PORT_TODO: var8.SuspendLayout(); // TODO: set layout new FlowLayoutPanel(2));
      // PORT_TODO: var2.Controls.Add(var8);
      // PORT_TODO: Button var9 = new Button() { Text = "Find" };
      // var9.setMnemonic - not directly available in WinForms
      // PORT_TODO: var9.Click += (new aX(this, var1));
      // PORT_TODO: var8.Add(var9);
      // PORT_TODO: this.getRootPane().setDefaultButton(var9);
      // PORT_TODO: Button var10 = new Button() { Text = "Cancel" };
      // var10.setMnemonic - not directly available in WinForms
      // PORT_TODO: var10.Click += (new aY(this));
      // PORT_TODO: var8.Add(var10);
      // PORT_TODO: this.getRootPane().registerKeyboardAction(new aZ(this), /* KeyStroke */ Keys.None /* (27, 0) */, 2);
      // PORT_TODO: this.PerformLayout();
   // PORT_TODO: }

   public static void a(cy var0, string var1) {
      if (dx == null) {
         // PORT_TODO: dx = new aW(var0);
      }

      dx.StartPosition = FormStartPosition.CenterParent; //(var0);
      if (var1 != null) {
         dx.ds.Text = (var1);
      }

      dx.ds.setSelectionStart(0);
      dx.ds.setSelectionEnd(dx.ds.Text.Length);
      dx.ds.Focus();
      dx.Show();
   }
   static TextBox a(aW var0) {
      return var0.ds;
   }
   public static RadioButton b(aW var0) {
      return var0.dw;
   }
   public static CheckBox c(aW var0) {
      return var0.dt;
   }
   public static CheckBox d(aW var0) {
      return var0.du;
   }
}



}
