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

public class cT  {
   public OpenFileDialog dialog = new OpenFileDialog();
   public static cT gv = null;
   public static string name = "Ship Export File";
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
      this.setDialogTitle("Choose Ship Export File");
      Panel var1 = new Panel();
      var1.SetLayout(new BoxLayout(var1, 1));
      var1.SetBorder(BorderFactory.createEmptyBorder(2, 5, 2, 2));
      var1.Add(new Label("Export Options:"));
      this.fO = new CheckBox("Products/Substances");
      var1.Add(this.fO);
      this.setAccessory(var1);
      UIManager.addPropertyChangeListener((var1x) => {
         if ("lookAndFeel".Equals(var1x.getPropertyName())) {
            SwingUtilities.updateComponentTreeUI(this);
         }
       });
   }

   public bool aw() {
      return this.fO.isSelected();
   }

   public int showSaveDialog(Component var1) {
      this.getAccessory().SetVisible(true);
      return base.showSaveDialog(var1);
   }

   public int showOpenDialog(Component var1) {
      this.getAccessory().SetVisible(false);
      return base.showOpenDialog(var1);
   }

   // $FF: synthetic method
   public static Image @as() {
      return fH;
   }
}

}
