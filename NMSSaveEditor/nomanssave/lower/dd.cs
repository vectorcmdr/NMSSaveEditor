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

public class dd : Form {
   private ListBox gS;
   private List<object> gT;
   private int gU;
   private static dd gV = null;

   private dd(Form var1) {
      base(var1);
      this.SetSize(300, 300);
      this.SetResizable(false);
      this.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
      this.SetTitle("Move Item");
      this.SetModal(true);
      Panel var2 = new Panel();
      this.SetContentPane(var2);
      var2.SetLayout(new BorderLayout(0, 0));
      Panel var3 = new Panel();
      this.gS = new ListBox();
      this.gS.SetSelectionMode(0);
      this.gS.SetModel(new de(this));
      this.gS.AddMouseListener(new df(this));
      var3.setViewportView(this.gS);
      var2.Add(var3);
      Panel var4 = new Panel();
      var4.SetLayout(new FlowLayout(2));
      var2.Add(var4, "South");
      Button var5 = new Button("Move");
      var5.AddActionListener(new dg(this));
      var4.Add(var5);
      this.GetRootPane().setDefaultButton(var5);
      Button var6 = new Button("Cancel");
      var6.AddActionListener(new dh(this));
      var4.Add(var6);
      this.GetRootPane().registerKeyboardAction(new di(this), Keys.getKeyStroke(27, 0), 2);
   }

   private int a(List<object> var1, int var2) {
      this.gT = var1;
      this.gS.updateUI();
      this.gS.SetSelectedIndex(this.gU);
      this.gU = var2;
      this.SetLocationRelativeTo(this.Parent);
      this.SetVisible(true);
      return this.gU;
   }

   public static int a(Container var0, List<object> var1, int var2) {
      if (gV == null) {
         Form var3 = JOptionPane.getFrameForComponent(var0);
         gV = new dd(var3);
      }

      return gV.a(var1, var2);
   }

   // $FF: synthetic method
   static List<object> a(dd var0) {
      return var0.gT;
   }

   // $FF: synthetic method
   static ListBox b(dd var0) {
      return var0.gS;
   }

   // $FF: synthetic method
   static void a(dd var0, int var1) {
      var0.gU = var1;
   }
}

}
