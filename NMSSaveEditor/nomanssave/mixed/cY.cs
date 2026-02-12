using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class cY : Form {
   public ComboBox gM;
   public List<object> gN = new List<object>();
   public int gO = -1;
   public static cY gP = null;

   public cY(Frame var1) {
      base(var1);
      this.FormBorderStyle = FormBorderStyle.FixedDialog; //(false);
      this.setModalExclusionType(/* ModalExclusionType */ 0);
      this.Text = ("Move Base Computer");
      this/* setModal */(true);
      Panel var2 = new Panel();
      this.setContentPane(var2);
      var2.SuspendLayout(); // TODO: set layout new TableLayoutPanel());
      Panel var3 = new Panel();
      var3.SuspendLayout(); // TODO: set layout new FlowLayoutPanel(0));
      var3.Add(new Label() { Text = "Please select a base part to swap your base computer with." });
      var2.Controls.Add(var3);
      Panel var4 = new Panel();
      // TODO: var4.SuspendLayout(); // TODO: set layout /* FormLayout */ null, FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("250px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC}));
      Label var5 = new Label() { Text = "Base Part:" };
      var4.Controls.Add(var5);
      this.gM = new ComboBox();
      this.gM.DataSource = (new cZ(this));
      var4.Add(this.gM, "4, 2, fill, default");
      var2.Controls.Add(var4);
      Panel var6 = new Panel();
      var6.SuspendLayout(); // TODO: set layout new FlowLayoutPanel(2));
      var2.Controls.Add(var6);
      Button var7 = new Button() { Text = "Save" };
      var7.Click += (new da(this));
      var6.Add(var7);
      this.getRootPane().setDefaultButton(var7);
      Button var8 = new Button() { Text = "Cancel" };
      var8.Click += (new db(this));
      var6.Add(var8);
      this.getRootPane().registerKeyboardAction(new dc(this), /* KeyStroke */ Keys.None /* (27, 0) */, 2);
      this.PerformLayout();
   }

   public int b(List<object> var1) {
      this.gN = var1;
      this.StartPosition = FormStartPosition.CenterParent; //(this.DirectoryName);
      this.gM.SelectedIndex = (0);
      this.gM.Refresh();
      this.gO = -1;
      this.Show();
      return this.gO;
   }

   public static int a(Container var0, List<object> var1) {
      if (gP == null) {
         Frame var2 = null;
         gP = new cY(var2);
      }

      return gP.b(var1);
   }
   static List<object> a(cY var0) {
      return var0.gN;
   }
   static ComboBox b(cY var0) {
      return var0.gM;
   }
   static void a(cY var0, int var1) {
      var0.gO = var1;
   }
}


#else

public class cY
{
   public cY() { }
   public cY(params object[] args) { }
   public static cY gP = default;
   public ComboBox gM = default;
   public List<object> gN = default;
   public int gO = 0;
   public int b(List<object> var1) { return 0; }
   public static int a(Container var0, List<object> var1) { return 0; }
}

#endif

}
