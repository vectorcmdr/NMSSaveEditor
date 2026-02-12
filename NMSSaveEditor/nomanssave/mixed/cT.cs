using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class cT : JFileChooser {
   public static cT gv = null;
   public static string name = "Ship Export FileInfo";
   public static Image fH = Application.a("UI-SHIPICON.PNG", 16, 16);
   public CheckBox fO;

   public static cT aC() {
      if (gv == null) {
         gv = new cT();
      }

      return gv;
   }

   public cT() {
      this.setFileSelectionMode(0);
      this.setAcceptAllFileFilterUsed(false);
      this.setFileView(new cU(this));
      this.setFileFilter(new cV(this));
      this.setDialogTitle("Choose Ship Export FileInfo");
      Panel var1 = new Panel();
      var1.SuspendLayout(); // TODO: set layout new BoxLayout(var1, 1));
      var1.Padding = new Padding(0); /* setBorder */ //(null /* EmptyBorder */);
      var1.Add(new Label() { Text = "Export Options:" });
      this.fO = new CheckBox() { Text = "Products/Substances" };
      var1.Add(this.fO);
      this.setAccessory(var1);
      /* TODO: port from Java - addPropertyChangeListener for lookAndFeel */
   }

   public bool aw() {
      return this.fO.Checked;
   }

   public int showSaveDialog(Component var1) {
      this.getAccessory().Show();
      return base.showSaveDialog(var1);
   }

   public int showOpenDialog(Component var1) {
      this.getAccessory().Hide();
      return base.showOpenDialog(var1);
   }
   public static Image @as() {
      return fH;
   }
}



}
