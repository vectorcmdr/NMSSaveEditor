using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class cs : OpenFileDialog {
   private static cs fN = null;
   private static string name = "Freighter Backup File";
   private static Image fH = Application.a("UI-FREIGHTERICON.PNG", 16, 16);
   private CheckBox fO;

   public static cs av() {
      if (fN == null) {
         fN = new cs();
      }

      return fN;
   }

   private cs() {
      this.setFileSelectionMode(0);
      this.setAcceptAllFileFilterUsed(false);
      this.setFileView(new ct(this));
      this.setFileFilter(new cu(this));
      this.setDialogTitle("Choose Backup File");
      Panel var1 = new Panel();
      var1.SuspendLayout(); // TODO: set layout new BoxLayout(var1, 1));
      var1.Padding = new Padding(0); /* setBorder */ //(null /* EmptyBorder */);
      var1.Add(new Label("Export Options:"));
      this.fO = new CheckBox("Products/Substances");
      var1.Add(this.fO);
      this.setAccessory(var1);
      SystemInformation.addPropertyChangeListener((var1x) => {
         if ("lookAndFeel".Equals(var1x.getPropertyName())) {
            Control.updateComponentTreeUI(this);
         }

      });
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
   static Image as() {
      return fH;
   }
}

}
