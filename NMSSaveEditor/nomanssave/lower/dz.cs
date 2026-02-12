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

public class dz : Form {
   public ListBox hr;
   public ft[] hs;
   public int gU;
   public static dz ht = null;

   public dz(Form var1) {
      base(var1);
      this.SetSize(300, 400);
      this.SetResizable(false);
      this.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
      this.SetTitle("Save FileInfo As");
      this.SetModal(true);
      Panel var2 = new Panel();
      this.SetContentPane(var2);
      var2.SetLayout(new BorderLayout(0, 0));
      Panel var3 = new Panel();
      this.hr = new ListBox();
      this.hr.SetSelectionMode(0);
      this.hr.SetModel(new dA(this));
      var3.setViewportView(this.hr);
      var2.Add(var3);
      Panel var4 = new Panel();
      var4.SetLayout(new FlowLayout(2));
      var2.Add(var4, "South");
      Button var5 = new Button("Replace/Save");
      var5.AddActionListener(new dB(this));
      var4.Add(var5);
      this.GetRootPane().setDefaultButton(var5);
      Button var6 = new Button("Cancel");
      var6.AddActionListener(new dC(this));
      var4.Add(var6);
      this.GetRootPane().registerKeyboardAction(new dD(this), Keys.getKeyStroke(27, 0), 2);
   }

   public int a(ft[] var1, int var2) {
      this.hs = var1;
      this.hr.updateUI();
      this.hr.SetSelectedIndex(var2);
      this.gU = -1;
      this.SetLocationRelativeTo(this.Parent);
      this.SetVisible(true);
      return this.gU;
   }

   public static int a(Container var0, ft[] var1, int var2) {
      if (ht == null) {
         Form var3 = JOptionPane.getFrameForComponent(var0);
         ht = new dz(var3);
      }

      return ht.a(var1, var2);
   }

   // $FF: synthetic method
   static ft[] a(dz var0) {
      return var0.hs;
   }

   // $FF: synthetic method
   static ListBox b(dz var0) {
      return var0.hr;
   }

   // $FF: synthetic method
   static void a(dz var0, int var1) {
      var0.gU = var1;
   }
}

}
