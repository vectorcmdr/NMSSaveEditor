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

public class aD : Form {
   public ComboBox cw;
   public TextBox cx;
   public bool cy;
   public static aD cz = null;

   public aD(Form var1) {
      base(var1);
      this.SetMinimumSize(new Size(400, 10));
      this.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
      this.SetTitle("Editor Settings");
      this.SetModal(true);
      ba var2 = new ba();
      this.cw = new ComboBox();
      this.cw.SetModel(new aE(this));
      var2.a("Look & Feel", (JComponent)this.cw);
      this.cx = new TextBox();
      var2.a("Inventory Scale", (JComponent)this.cx);
      var2.Y();
      Panel var3 = new Panel();
      var2.a(var3);
      Button var4 = new Button("Apply");
      var4.AddActionListener(new aF(this));
      var3.Add(var4);
      Button var5 = new Button("Cancel");
      var5.AddActionListener(new aG(this));
      var3.Add(var5);
      this.SetContentPane(var2);
      this.Pack();
   }

   public bool S() {
      string var1 = aH.getProperty("LookAndFeel");
      aI var2 = (aI)Stream.of(aI.Values).filter((var1x) => {
         return var1x.ToString().Equals(var1);
      }).findFirst().orElse(aI.cN);
      this.cw.SetSelectedItem(var2);
      this.cx.SetText(Double.toString(aH.a("InventoryScaling", 1.0D)));
      this.SetLocationRelativeTo(this.Parent);
      this.cy = false;
      this.SetVisible(true);
      return this.cy;
   }

   public static bool d(Container var0) {
      if (cz == null) {
         Form var1 = JOptionPane.getFrameForComponent(var0);
         cz = new aD(var1);
      }

      return cz.S();
   }

   // $FF: synthetic method
   public static ComboBox a(aD var0) {
      return var0.cw;
   }

   // $FF: synthetic method
   public static void a(aD var0, bool var1) {
      var0.cy = var1;
   }

   // $FF: synthetic method
   public static TextBox b(aD var0) {
      return var0.cx;
   }
}

}
