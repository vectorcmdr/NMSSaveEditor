using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class aD : Form {
   private ComboBox cw;
   private TextBox cx;
   private bool cy;
   public static aD cz = null;

   private aD(Frame var1) {
      base(var1);
      this.setMinimumSize(new Size(400, 10));
      this.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
      this.setTitle("Editor Settings");
      this.setModal(true);
      ba var2 = new ba();
      this.cw = new ComboBox();
      this.cw.setModel(new aE(this));
      var2.a("Look & Feel", (Control)this.cw);
      this.cx = new TextBox();
      var2.a("Inventory Scale", (Control)this.cx);
      var2.Y();
      Panel var3 = new Panel();
      var2.a(var3);
      Button var4 = new Button("Apply");
      var4.addActionListener(new aF(this));
      var3.Add(var4);
      Button var5 = new Button("Cancel");
      var5.addActionListener(new aG(this));
      var3.Add(var5);
      this.setContentPane(var2);
      this.pack();
   }

   private bool S() {
      string var1 = aH.getProperty("LookAndFeel");
      aI var2 = (aI)Stream.of(aI.Values).filter((var1x) -> {
         return var1x.name().equalsIgnoreCase(var1);
      }).findFirst().orElse(aI.cN);
      this.cw.setSelectedItem(var2);
      this.cx.setText(Double.toString(aH.a("InventoryScaling", 1.0D)));
      this.setLocationRelativeTo(this.getParent());
      this.cy = false;
      this.setVisible(true);
      return this.cy;
   }

   public static bool d(Container var0) {
      if (cz == null) {
         Frame var1 = MessageBox.getFrameForComponent(var0);
         cz = new aD(var1);
      }

      return cz.S();
   }
   static ComboBox a(aD var0) {
      return var0.cw;
   }
   static void a(aD var0, bool var1) {
      var0.cy = var1;
   }
   static TextBox b(aD var0) {
      return var0.cx;
   }
}

}
