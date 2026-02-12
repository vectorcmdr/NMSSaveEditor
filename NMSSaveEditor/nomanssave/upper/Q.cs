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

public class Q : Form {
   public W bw;
   public int bx;
   public int by;
   public W bz = null;
   public TextBox bA;
   public TextBox bB;
   public static Q bC;

   public Q(Form var1) {
      base(var1);
      this.SetResizable(false);
      this.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
      this.SetTitle("Change Stack Sizes");
      this.SetModal(true);
      Panel var2 = new Panel();
      this.SetContentPane(var2);
      var2.SetLayout(new BorderLayout(0, 0));
      Panel var3 = new Panel();
      var3.SetLayout(new FormLayout(new ColumnSpec[]{FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("100px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("250px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("20dlu"), FormFactory.LINE_GAP_ROWSPEC}));
      Label var4 = new Label("Substances:");
      var3.Add(var4, "2, 2, left, center");
      this.bA = new TextBox();
      this.bA.addFocusListener(new R(this));
      var3.Add(this.bA, "4, 2, fill, default");
      Label var5 = new Label("Products:");
      var3.Add(var5, "2, 4, left, center");
      this.bB = new TextBox();
      this.bB.addFocusListener(new S(this));
      var3.Add(this.bB, "4, 4, fill, default");
      Label var6 = new Label("<html><font color=\"red\"><b>Please Note: No Man's Sky sometimes reverts these settings back to default.</b></font></html>");
      var3.Add(var6, "2, 6, 3, 1, fill, center");
      var2.Add(var3);
      Panel var7 = new Panel();
      var7.SetLayout(new FlowLayout(2));
      var2.Add(var7, "South");
      Button var8 = new Button("Save");
      var8.AddActionListener(new T(this));
      var7.Add(var8);
      this.GetRootPane().setDefaultButton(var8);
      Button var9 = new Button("Cancel");
      var9.AddActionListener(new U(this));
      var7.Add(var9);
      this.GetRootPane().registerKeyboardAction(new V(this), Keys.getKeyStroke(27, 0), 2);
      this.Pack();
   }

   public W a(W var1, int var2, int var3) {
      this.bw = var1;
      this.bx = var2;
      this.by = var3;
      this.bA.SetText(Convert.ToString(var1.bE));
      this.bB.SetText(Convert.ToString(var1.bF));
      this.bz = null;
      this.SetLocationRelativeTo(this.Parent);
      this.SetVisible(true);
      return this.bz;
   }

   public static W a(Container var0, W var1, int var2, int var3) {
      if (bC == null) {
         Form var4 = JOptionPane.getFrameForComponent(var0);
         bC = new Q(var4);
      }

      return bC.a(var1, var2, var3);
   }

   // $FF: synthetic method
   static TextBox a(Q var0) {
      return var0.bA;
   }

   // $FF: synthetic method
   static int b(Q var0) {
      return var0.bx;
   }

   // $FF: synthetic method
   static W c(Q var0) {
      return var0.bw;
   }

   // $FF: synthetic method
   static TextBox d(Q var0) {
      return var0.bB;
   }

   // $FF: synthetic method
   static int e(Q var0) {
      return var0.by;
   }

   // $FF: synthetic method
   static void a(Q var0, W var1) {
      var0.bz = var1;
   }
}

}
