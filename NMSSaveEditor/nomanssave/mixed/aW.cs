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

public class aW : Form {
   public TextBox ds;
   public CheckBox dt;
   public CheckBox du;
   public RadioButton dv;
   public RadioButton dw;
   public static aW dx;

   public aW(cy var1) {
      // base(var1);
      this.SetSize(400, 250);
      this.SetResizable(false);
      this.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
      this.SetTitle("Find");
      this.SetModal(true);
      Panel var2 = new Panel();
      this.SetContentPane(var2);
      var2.SetLayout(new BorderLayout(0, 0));
      Panel var3 = new Panel();
      var3.SetLayout(new FormLayout(new ColumnSpec[]{FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("100px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("250px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC}));
      Label var4 = new Label("Find:");
      var3.Add(var4, "2, 2, left, center");
      this.ds = new TextBox();
      var3.Add(this.ds, "4, 2, fill, default");
      var2.Add(var3);
      Panel var5 = new Panel();
      var5.SetLayout(new GridLayout(1, 2));
      var5.SetBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Direction"), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
      this.dv = new RadioButton("Forward");
      this.dv.setSelected(true);
      var5.Add(this.dv);
      this.dw = new RadioButton("Backward");
      var5.Add(this.dw);
      ButtonGroup var6 = new ButtonGroup();
      var6.Add(this.dv);
      var6.Add(this.dw);
      var3.Add(var5, "2, 4, 3, 1");
      Panel var7 = new Panel();
      var7.SetLayout(new GridLayout(1, 2));
      var7.SetBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Options"), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
      this.dt = new CheckBox("Case Sensitive");
      this.dt.setSelected(true);
      var7.Add(this.dt);
      this.du = new CheckBox("Wrap Search");
      var7.Add(this.du);
      var3.Add(var7, "2, 6, 3, 1");
      Panel var8 = new Panel();
      var8.SetLayout(new FlowLayout(2));
      var2.Add(var8, "South");
      Button var9 = new Button("Find");
      var9.SetMnemonic(10);
      var9.AddActionListener(new aX(this, var1));
      var8.Add(var9);
      this.GetRootPane().setDefaultButton(var9);
      Button var10 = new Button("Cancel");
      var10.SetMnemonic(27);
      var10.AddActionListener(new aY(this));
      var8.Add(var10);
      this.GetRootPane().registerKeyboardAction(new aZ(this), Keys.getKeyStroke(27, 0), 2);
      this.Pack();
   }

   public static void a(cy var0, string var1) {
      if (dx == null) {
         dx = new aW(var0);
      }
       dx.SetLocationRelativeTo(var0);
      if (var1 != null) {
         dx.ds.SetText(var1);
      }
       dx.ds.setSelectionStart(0);
      dx.ds.setSelectionEnd(dx.ds.GetText().Length);
      dx.ds.Focus();
      dx.SetVisible(true);
   }

   // $FF: synthetic method
   public static TextBox a(aW var0) {
      return var0.ds;
   }

   // $FF: synthetic method
   public static RadioButton b(aW var0) {
      return var0.dw;
   }

   // $FF: synthetic method
   public static CheckBox c(aW var0) {
      return var0.dt;
   }

   // $FF: synthetic method
   public static CheckBox d(aW var0) {
      return var0.du;
   }
}

}
