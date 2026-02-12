using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class cY : Form {
   public ComboBox gM;
   public List<object> gN = new List<object>();
   public int gO = -1;
   public static cY gP = null;

// PORT_TODO: public cY(Frame var1) : base(var1) {
      // PORT_TODO: this.FormBorderStyle = FormBorderStyle.FixedDialog; //(false);
      // setModalExclusionType not available in WinForms
      // PORT_TODO: this.Text = ("Move Base Computer");
      // PORT_TODO: // PORT_TODO: this/* setModal */(true);
      // PORT_TODO: Panel var2 = new Panel();
      // PORT_TODO: this.setContentPane(var2);
      // PORT_TODO: var2.SuspendLayout(); // TODO: set layout new TableLayoutPanel());
      // PORT_TODO: Panel var3 = new Panel();
      // PORT_TODO: var3.SuspendLayout(); // TODO: set layout new FlowLayoutPanel(0));
      // PORT_TODO: var3.Add(new Label() { Text = "Please select a base part to swap your base computer with." });
      // PORT_TODO: var2.Controls.Add(var3);
      // PORT_TODO: Panel var4 = new Panel();
      // TODO: var4.SuspendLayout(); // TODO: set layout /* FormLayout */ null, FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("250px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC}));
      // PORT_TODO: Label var5 = new Label() { Text = "Base Part:" };
      // PORT_TODO: var4.Controls.Add(var5);
      // PORT_TODO: this.gM = new ComboBox();
      // PORT_TODO: this.gM.DataSource = (new cZ(this));
      // PORT_TODO: var4.Add(this.gM, "4, 2, fill, default");
      // PORT_TODO: var2.Controls.Add(var4);
      // PORT_TODO: Panel var6 = new Panel();
      // PORT_TODO: var6.SuspendLayout(); // TODO: set layout new FlowLayoutPanel(2));
      // PORT_TODO: var2.Controls.Add(var6);
      // PORT_TODO: Button var7 = new Button() { Text = "Save" };
      // PORT_TODO: var7.Click += (new da(this));
      // PORT_TODO: var6.Add(var7);
      // PORT_TODO: this.getRootPane().setDefaultButton(var7);
      // PORT_TODO: Button var8 = new Button() { Text = "Cancel" };
      // PORT_TODO: var8.Click += (new db(this));
      // PORT_TODO: var6.Add(var8);
      // PORT_TODO: this.getRootPane().registerKeyboardAction(new dc(this), /* KeyStroke */ Keys.None /* (27, 0) */, 2);
      // PORT_TODO: this.PerformLayout();
   // PORT_TODO: }

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



}
