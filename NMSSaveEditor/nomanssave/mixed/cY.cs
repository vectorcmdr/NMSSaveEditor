using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class cY : Form {
   private ComboBox gM;
   private List gN = new List<object>();
   private int gO = -1;
   private static cY gP = null;

   private cY(Frame var1) {
      base(var1);
      this.setResizable(false);
      this.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
      this.setTitle("Move Base Computer");
      this.setModal(true);
      Panel var2 = new Panel();
      this.setContentPane(var2);
      var2.setLayout(new TableLayoutPanel(0, 0));
      Panel var3 = new Panel();
      var3.setLayout(new FlowLayoutPanel(0));
      var3.Add(new Label("Please select a base part to swap your base computer with."));
      var2.Add(var3, "North");
      Panel var4 = new Panel();
      var4.setLayout(new FormLayout(new ColumnSpec[]{FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("100px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("250px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC}));
      Label var5 = new Label("Base Part:");
      var4.Add(var5, "2, 2, left, center");
      this.gM = new ComboBox();
      this.gM.setModel(new cZ(this));
      var4.Add(this.gM, "4, 2, fill, default");
      var2.Add(var4, "Center");
      Panel var6 = new Panel();
      var6.setLayout(new FlowLayoutPanel(2));
      var2.Add(var6, "South");
      Button var7 = new Button("Save");
      var7.addActionListener(new da(this));
      var6.Add(var7);
      this.getRootPane().setDefaultButton(var7);
      Button var8 = new Button("Cancel");
      var8.addActionListener(new db(this));
      var6.Add(var8);
      this.getRootPane().registerKeyboardAction(new dc(this), Keys.getKeyStroke(27, 0), 2);
      this.pack();
   }

   private int b(List var1) {
      this.gN = var1;
      this.setLocationRelativeTo(this.getParent());
      this.gM.setSelectedIndex(0);
      this.gM.updateUI();
      this.gO = -1;
      this.setVisible(true);
      return this.gO;
   }

   public static int a(Container var0, List var1) {
      if (gP == null) {
         Frame var2 = MessageBox.getFrameForComponent(var0);
         gP = new cY(var2);
      }

      return gP.b(var1);
   }
   static List a(cY var0) {
      return var0.gN;
   }
   static ComboBox b(cY var0) {
      return var0.gM;
   }
   static void a(cY var0, int var1) {
      var0.gO = var1;
   }
}

}
