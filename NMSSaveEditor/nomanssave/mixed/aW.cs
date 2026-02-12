using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class aW : Form {
   private TextBox ds;
   private CheckBox dt;
   private CheckBox du;
   private RadioButton dv;
   private RadioButton dw;
   private static aW dx;

   private aW(cy var1) {
      base(var1);
      this.Size = new Size(400, 250);
      this.FormBorderStyle = FormBorderStyle.FixedDialog; //(false);
      this.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
      this.Text = ("Find");
      this/* setModal */(true);
      Panel var2 = new Panel();
      this.setContentPane(var2);
      var2.SuspendLayout(); // TODO: set layout new TableLayoutPanel(0, 0));
      Panel var3 = new Panel();
      // TODO: var3.SuspendLayout(); // TODO: set layout /* FormLayout */ null, FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("250px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC}));
      Label var4 = new Label("Find:");
      var3.Controls.Add(var4);
      this.ds = new TextBox();
      var3.Add(this.ds, "4, 2, fill, default");
      var2.Add(var3);
      Panel var5 = new Panel();
      var5.SuspendLayout(); // TODO: set layout new TableLayoutPanel(1, 2));
      var5.Padding = new Padding(0); /* setBorder */ //(null /* CompoundBorder */);
      this.dv = new RadioButton("Forward");
      this.dv.Checked = (true);
      var5.Add(this.dv);
      this.dw = new RadioButton("Backward");
      var5.Add(this.dw);
      object var6 = new object();
      var6.Add(this.dv);
      var6.Add(this.dw);
      var3.Controls.Add(var5);
      Panel var7 = new Panel();
      var7.SuspendLayout(); // TODO: set layout new TableLayoutPanel(1, 2));
      var7.Padding = new Padding(0); /* setBorder */ //(null /* CompoundBorder */);
      this.dt = new CheckBox("Case Sensitive");
      this.dt.Checked = (true);
      var7.Add(this.dt);
      this.du = new CheckBox("Wrap Search");
      var7.Add(this.du);
      var3.Controls.Add(var7);
      Panel var8 = new Panel();
      var8.SuspendLayout(); // TODO: set layout new FlowLayoutPanel(2));
      var2.Controls.Add(var8);
      Button var9 = new Button("Find");
      var9.setMnemonic(10);
      var9.Click += (new aX(this, var1));
      var8.Add(var9);
      this.getRootPane().setDefaultButton(var9);
      Button var10 = new Button("Cancel");
      var10.setMnemonic(27);
      var10.Click += (new aY(this));
      var8.Add(var10);
      this.getRootPane().registerKeyboardAction(new aZ(this), /* KeyStroke */ Keys.None /* (27, 0) */, 2);
      this.PerformLayout();
   }

   public static void a(cy var0, string var1) {
      if (dx == null) {
         dx = new aW(var0);
      }

      dx.StartPosition = FormStartPosition.CenterParent; //(var0);
      if (var1 != null) {
         dx.ds.Text = (var1);
      }

      dx.ds.setSelectionStart(0);
      dx.ds.setSelectionEnd(dx.ds.Text.length());
      dx.ds.Focus();
      dx.Show();
   }
   static TextBox a(aW var0) {
      return var0.ds;
   }
   static RadioButton b(aW var0) {
      return var0.dw;
   }
   static CheckBox c(aW var0) {
      return var0.dt;
   }
   static CheckBox d(aW var0) {
      return var0.du;
   }
}

}
