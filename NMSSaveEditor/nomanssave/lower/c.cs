using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class c : Panel {
   public f _c;
   public f d;
   public f e;

   public c(Application var1) {
      TableLayoutPanel var2 = new TableLayoutPanel();
      this.SuspendLayout(); // TODO: set layout var2);
      Panel var3 = new Panel();
      this.Add(var3);
      // TODO: var3.SuspendLayout(); // TODO: set layout /* FormLayout */ null, FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("200px:grow"), FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC}));
      Label var4 = new Label() { Text = "Season Rewards" };
      // TODO: var4.putClientProperty(...);
      var3.Controls.Add(var4);
      Panel var5 = new Panel();
      var3.Controls.Add(var5);
      this._c = new f(this, var1, eI.bq, eI.P);
      var5.setViewportView(this._c);
      Panel var6 = new Panel();
      this.Add(var6);
      // TODO: var6.SuspendLayout(); // TODO: set layout /* FormLayout */ null, FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("200px:grow"), FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC}));
      var4 = new Label() { Text = "Twitch Rewards" };
      // TODO: var4.putClientProperty(...);
      var6.Controls.Add(var4);
      Panel var7 = new Panel();
      var6.Controls.Add(var7);
      this.d = new f(this, var1, eI.br, eI.Q);
      var7.setViewportView(this.d);
      var4 = new Label() { Text = "NOTE: To use twitch drops, you must go offline before you start the game." };
      var6.Controls.Add(var4);
      var4 = new Label() { Text = "You can claim them at the Synthesis vendor in the Anomaly." };
      var6.Controls.Add(var4);
      Panel var8 = new Panel();
      this.Add(var8);
      // TODO: var8.SuspendLayout(); // TODO: set layout /* FormLayout */ null, FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("200px:grow"), FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC}));
      var4 = new Label() { Text = "Platform Rewards" };
      // TODO: var4.putClientProperty(...);
      var8.Controls.Add(var4);
      Panel var9 = new Panel();
      var8.Controls.Add(var9);
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
         this._c.a((eV)null);
         this.d.a((eV)null);
         this.e.a((eV)null);
      } else {
         this._c.a(var1.d("UserSettingsData.UnlockedSeasonRewards"));
         this.d.a(var1.d("UserSettingsData.UnlockedTwitchRewards"));
         this.e.a(var1.d("UserSettingsData.UnlockedPlatformRewards"));
      }

      this.Refresh();
   }
}


#else

public class c
{
   public c() { }
   public c(params object[] args) { }
   public f _c = default;
   public f d = default;
   public f e = default;
   public void a(eY var1) { }
}

#endif

}
