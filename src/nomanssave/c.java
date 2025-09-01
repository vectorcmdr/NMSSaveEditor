package nomanssave;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class c extends JPanel {
   private final f c;
   private final f d;
   private final f e;

   c(Application var1) {
      GridLayout var2 = new GridLayout(2, 3);
      this.setLayout(var2);
      JPanel var3 = new JPanel();
      this.add(var3);
      var3.setLayout(new FormLayout(new ColumnSpec[]{FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("200px:grow"), FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC}));
      JLabel var4 = new JLabel("Season Rewards");
      var4.putClientProperty("FlatLaf.styleClass", "semibold");
      var3.add(var4, "2, 2");
      JScrollPane var5 = new JScrollPane();
      var3.add(var5, "2, 4, fill, fill");
      this.c = new f(this, var1, eI::bq, eI::P);
      var5.setViewportView(this.c);
      JPanel var6 = new JPanel();
      this.add(var6);
      var6.setLayout(new FormLayout(new ColumnSpec[]{FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("200px:grow"), FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC}));
      var4 = new JLabel("Twitch Rewards");
      var4.putClientProperty("FlatLaf.styleClass", "semibold");
      var6.add(var4, "2, 2");
      JScrollPane var7 = new JScrollPane();
      var6.add(var7, "2, 4, fill, fill");
      this.d = new f(this, var1, eI::br, eI::Q);
      var7.setViewportView(this.d);
      var4 = new JLabel("NOTE: To use twitch drops, you must go offline before you start the game.");
      var6.add(var4, "2, 6, fill, fill");
      var4 = new JLabel("You can claim them at the Synthesis vendor in the Anomaly.");
      var6.add(var4, "2, 7, fill, fill");
      JPanel var8 = new JPanel();
      this.add(var8);
      var8.setLayout(new FormLayout(new ColumnSpec[]{FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("200px:grow"), FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC}));
      var4 = new JLabel("Platform Rewards");
      var4.putClientProperty("FlatLaf.styleClass", "semibold");
      var8.add(var4, "2, 2");
      JScrollPane var9 = new JScrollPane();
      var8.add(var9, "2, 4, fill, fill");
      this.e = new f(this, var1, eI::bs, eI::R);
      var9.setViewportView(this.e);
      JPanel var10 = new JPanel();
      this.add(var10);
      var10 = new JPanel();
      this.add(var10);
      var10 = new JPanel();
      this.add(var10);
   }

   void a(eY var1) {
      if (var1 == null) {
         this.c.a((eV)null);
         this.d.a((eV)null);
         this.e.a((eV)null);
      } else {
         this.c.a(var1.d("UserSettingsData.UnlockedSeasonRewards"));
         this.d.a(var1.d("UserSettingsData.UnlockedTwitchRewards"));
         this.e.a(var1.d("UserSettingsData.UnlockedPlatformRewards"));
      }

      this.updateUI();
   }
}
