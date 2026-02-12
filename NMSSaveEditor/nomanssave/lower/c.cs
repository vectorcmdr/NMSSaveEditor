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

public class c : Panel {
   public f c_field;
   public f d;
   public f e;

   public c(Application var1) {
      GridLayout var2 = new GridLayout(2, 3);
      this.SetLayout(var2);
      Panel var3 = new Panel();
      this.Add(var3);
      var3.SetLayout(new FormLayout(new ColumnSpec[]{FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("200px:grow"), FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC}));
      Label var4 = new Label("Season Rewards");
      var4.putClientProperty("FlatLaf.styleClass", "semibold");
      var3.Add(var4, "2, 2");
      Panel var5 = new Panel();
      var3.Add(var5, "2, 4, fill, fill");
      this.c_field = new f(this, var1, eI.bq, eI.P);
      var5.setViewportView(this.c_field);
      Panel var6 = new Panel();
      this.Add(var6);
      var6.SetLayout(new FormLayout(new ColumnSpec[]{FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("200px:grow"), FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC}));
      var4 = new Label("Twitch Rewards");
      var4.putClientProperty("FlatLaf.styleClass", "semibold");
      var6.Add(var4, "2, 2");
      Panel var7 = new Panel();
      var6.Add(var7, "2, 4, fill, fill");
      this.d = new f(this, var1, eI.br, eI.Q);
      var7.setViewportView(this.d);
      var4 = new Label("NOTE: To use twitch drops, you must go offline before you start the game.");
      var6.Add(var4, "2, 6, fill, fill");
      var4 = new Label("You can claim them at the Synthesis vendor in the Anomaly.");
      var6.Add(var4, "2, 7, fill, fill");
      Panel var8 = new Panel();
      this.Add(var8);
      var8.SetLayout(new FormLayout(new ColumnSpec[]{FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("200px:grow"), FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC}));
      var4 = new Label("Platform Rewards");
      var4.putClientProperty("FlatLaf.styleClass", "semibold");
      var8.Add(var4, "2, 2");
      Panel var9 = new Panel();
      var8.Add(var9, "2, 4, fill, fill");
      this.e = new f(this, var1, eI.bs, eI.R);
      var9.setViewportView(this.e);
      Panel var10 = new Panel();
      this.Add(var10);
      var10 = new Panel();
      this.Add(var10);
      var10 = new Panel();
      this.Add(var10);
   }

   public void a(eY var1) {
      if (var1 == null) {
         this.c_field.a((eV)null);
         this.d.a((eV)null);
         this.e.a((eV)null);
      } else {
         this.c_field.a(var1.d("UserSettingsData.UnlockedSeasonRewards"));
         this.d.a(var1.d("UserSettingsData.UnlockedTwitchRewards"));
         this.e.a(var1.d("UserSettingsData.UnlockedPlatformRewards"));
      }
       this.updateUI();
   }
}

}
