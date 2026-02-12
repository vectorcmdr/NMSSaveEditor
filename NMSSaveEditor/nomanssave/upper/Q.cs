using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class Q : Form {
   private W bw;
   private int bx;
   private int by;
   private W bz = null;
   private TextBox bA;
   private TextBox bB;
   private static Q bC;

   private Q(Frame var1) {
      base(var1);
      this.FormBorderStyle = FormBorderStyle.FixedDialog; //(false);
      this.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
      this.Text = ("Change Stack Sizes");
      this/* setModal */(true);
      Panel var2 = new Panel();
      this.setContentPane(var2);
      var2.SuspendLayout(); // TODO: set layout new TableLayoutPanel(0, 0));
      Panel var3 = new Panel();
      // TODO: var3.SuspendLayout(); // TODO: set layout /* FormLayout */ null, FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("250px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("20dlu"), FormFactory.LINE_GAP_ROWSPEC}));
      Label var4 = new Label("Substances:");
      var3.Controls.Add(var4);
      this.bA = new TextBox();
      this.bA.addFocusListener(new R(this));
      var3.Add(this.bA, "4, 2, fill, default");
      Label var5 = new Label("Products:");
      var3.Controls.Add(var5);
      this.bB = new TextBox();
      this.bB.addFocusListener(new S(this));
      var3.Add(this.bB, "4, 4, fill, default");
      Label var6 = new Label("<html><font color=\"red\"><b>Please Note: No Man's Sky sometimes reverts these settings back to default.</b></font></html>");
      var3.Controls.Add(var6);
      var2.Add(var3);
      Panel var7 = new Panel();
      var7.SuspendLayout(); // TODO: set layout new FlowLayoutPanel(2));
      var2.Controls.Add(var7);
      Button var8 = new Button("Save");
      var8.Click += (new T(this));
      var7.Add(var8);
      this.getRootPane().setDefaultButton(var8);
      Button var9 = new Button("Cancel");
      var9.Click += (new U(this));
      var7.Add(var9);
      this.getRootPane().registerKeyboardAction(new V(this), /* KeyStroke */ Keys.None; //(27, 0), 2);
      this.PerformLayout();
   }

   private W a(W var1, int var2, int var3) {
      this.bw = var1;
      this.bx = var2;
      this.by = var3;
      this.bA.Text = (Integer.toString(var1.bE));
      this.bB.Text = (Integer.toString(var1.bF));
      this.bz = null;
      this.StartPosition = FormStartPosition.CenterParent; //(this.DirectoryName);
      this.Show();
      return this.bz;
   }

   public static W a(Container var0, W var1, int var2, int var3) {
      if (bC == null) {
         Frame var4 = MessageBox.getFrameForComponent(var0);
         bC = new Q(var4);
      }

      return bC.a(var1, var2, var3);
   }
   static TextBox a(Q var0) {
      return var0.bA;
   }
   static int b(Q var0) {
      return var0.bx;
   }
   static W c(Q var0) {
      return var0.bw;
   }
   static TextBox d(Q var0) {
      return var0.bB;
   }
   static int e(Q var0) {
      return var0.by;
   }
   static void a(Q var0, W var1) {
      var0.bz = var1;
   }
}

}
