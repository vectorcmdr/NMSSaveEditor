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

public class cY : Form {
   public ComboBox gM;
   public List<object> gN = new List<object>();
   public int gO = -1;
   public static cY gP = null;

   public cY(Form var1) {
      base(var1);
      this.SetResizable(false);
      this.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
      this.SetTitle("Move Base Computer");
      this.SetModal(true);
      Panel var2 = new Panel();
      this.SetContentPane(var2);
      var2.SetLayout(new BorderLayout(0, 0));
      Panel var3 = new Panel();
      var3.SetLayout(new FlowLayout(0));
      var3.Add(new Label("Please select a base part to swap your base computer with."));
      var2.Add(var3, "North");
      Panel var4 = new Panel();
      var4.SetLayout(new FormLayout(new ColumnSpec[]{FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("100px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("250px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC}));
      Label var5 = new Label("Base Part:");
      var4.Add(var5, "2, 2, left, center");
      this.gM = new ComboBox();
      this.gM.SetModel(new cZ(this));
      var4.Add(this.gM, "4, 2, fill, default");
      var2.Add(var4, "Center");
      Panel var6 = new Panel();
      var6.SetLayout(new FlowLayout(2));
      var2.Add(var6, "South");
      Button var7 = new Button("Save");
      var7.AddActionListener(new da(this));
      var6.Add(var7);
      this.GetRootPane().setDefaultButton(var7);
      Button var8 = new Button("Cancel");
      var8.AddActionListener(new db(this));
      var6.Add(var8);
      this.GetRootPane().registerKeyboardAction(new dc(this), Keys.getKeyStroke(27, 0), 2);
      this.Pack();
   }

   public int b(List<object> var1) {
      this.gN = var1;
      this.SetLocationRelativeTo(this.Parent);
      this.gM.SetSelectedIndex(0);
      this.gM.updateUI();
      this.gO = -1;
      this.SetVisible(true);
      return this.gO;
   }

   public static int a(Container var0, List<object> var1) {
      if (gP == null) {
         Form var2 = JOptionPane.getFrameForComponent(var0);
         gP = new cY(var2);
      }

      return gP.b(var1);
   }

   // $FF: synthetic method
   static List<object> a(cY var0) {
      return var0.gN;
   }

   // $FF: synthetic method
   static ComboBox b(cY var0) {
      return var0.gM;
   }

   // $FF: synthetic method
   static void a(cY var0, int var1) {
      var0.gO = var1;
   }
}

}
