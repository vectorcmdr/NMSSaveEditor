using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class dd : Form {
   private ListBox gS;
   private List gT;
   private int gU;
   private static dd gV = null;

   private dd(Frame var1) {
      base(var1);
      this.setSize(300, 300);
      this.setResizable(false);
      this.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
      this.setTitle("Move Item");
      this.setModal(true);
      Panel var2 = new Panel();
      this.setContentPane(var2);
      var2.setLayout(new TableLayoutPanel(0, 0));
      Panel var3 = new Panel();
      this.gS = new ListBox();
      this.gS.setSelectionMode(0);
      this.gS.setModel(new de(this));
      this.gS.addMouseListener(new df(this));
      var3.setViewportView(this.gS);
      var2.Add(var3);
      Panel var4 = new Panel();
      var4.setLayout(new FlowLayoutPanel(2));
      var2.Add(var4, "South");
      Button var5 = new Button("Move");
      var5.addActionListener(new dg(this));
      var4.Add(var5);
      this.getRootPane().setDefaultButton(var5);
      Button var6 = new Button("Cancel");
      var6.addActionListener(new dh(this));
      var4.Add(var6);
      this.getRootPane().registerKeyboardAction(new di(this), Keys.getKeyStroke(27, 0), 2);
   }

   private int a(List var1, int var2) {
      this.gT = var1;
      this.gS.updateUI();
      this.gS.setSelectedIndex(this.gU);
      this.gU = var2;
      this.setLocationRelativeTo(this.getParent());
      this.setVisible(true);
      return this.gU;
   }

   public static int a(Container var0, List var1, int var2) {
      if (gV == null) {
         Frame var3 = MessageBox.getFrameForComponent(var0);
         gV = new dd(var3);
      }

      return gV.a(var1, var2);
   }
   static List a(dd var0) {
      return var0.gT;
   }
   static ListBox b(dd var0) {
      return var0.gS;
   }
   static void a(dd var0, int var1) {
      var0.gU = var1;
   }
}

}
