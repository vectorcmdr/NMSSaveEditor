package nomanssave;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class c extends JPanel {
  private final f c;
  
  private final f d;
  
  private final f e;
  
  c(Application paramApplication) {
    GridLayout gridLayout = new GridLayout(2, 3);
    setLayout(gridLayout);
    JPanel jPanel1 = new JPanel();
    add(jPanel1);
    jPanel1.setLayout((LayoutManager)new FormLayout(new ColumnSpec[] { FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC }, new RowSpec[] { FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("200px:grow"), FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC }));
    JLabel jLabel = new JLabel("Season Rewards");
    jLabel.putClientProperty("FlatLaf.styleClass", "semibold");
    jPanel1.add(jLabel, "2, 2");
    JScrollPane jScrollPane1 = new JScrollPane();
    jPanel1.add(jScrollPane1, "2, 4, fill, fill");
    this.c = new f(this, paramApplication, eI::bq, eI::P);
    jScrollPane1.setViewportView(this.c);
    JPanel jPanel2 = new JPanel();
    add(jPanel2);
    jPanel2.setLayout((LayoutManager)new FormLayout(new ColumnSpec[] { FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC }, new RowSpec[] { FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("200px:grow"), FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC }));
    jLabel = new JLabel("Twitch Rewards");
    jLabel.putClientProperty("FlatLaf.styleClass", "semibold");
    jPanel2.add(jLabel, "2, 2");
    JScrollPane jScrollPane2 = new JScrollPane();
    jPanel2.add(jScrollPane2, "2, 4, fill, fill");
    this.d = new f(this, paramApplication, eI::br, eI::Q);
    jScrollPane2.setViewportView(this.d);
    jLabel = new JLabel("NOTE: To use twitch drops, you must go offline before you start the game.");
    jPanel2.add(jLabel, "2, 6, fill, fill");
    jLabel = new JLabel("You can claim them at the Synthesis vendor in the Anomaly.");
    jPanel2.add(jLabel, "2, 7, fill, fill");
    JPanel jPanel3 = new JPanel();
    add(jPanel3);
    jPanel3.setLayout((LayoutManager)new FormLayout(new ColumnSpec[] { FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC }, new RowSpec[] { FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("200px:grow"), FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC }));
    jLabel = new JLabel("Platform Rewards");
    jLabel.putClientProperty("FlatLaf.styleClass", "semibold");
    jPanel3.add(jLabel, "2, 2");
    JScrollPane jScrollPane3 = new JScrollPane();
    jPanel3.add(jScrollPane3, "2, 4, fill, fill");
    this.e = new f(this, paramApplication, eI::bs, eI::R);
    jScrollPane3.setViewportView(this.e);
    JPanel jPanel4 = new JPanel();
    add(jPanel4);
    jPanel4 = new JPanel();
    add(jPanel4);
    jPanel4 = new JPanel();
    add(jPanel4);
  }
  
  void a(eY parameY) {
    if (parameY == null) {
      this.c.a(null);
      this.d.a(null);
      this.e.a(null);
    } else {
      this.c.a(parameY.d("UserSettingsData.UnlockedSeasonRewards"));
      this.d.a(parameY.d("UserSettingsData.UnlockedTwitchRewards"));
      this.e.a(parameY.d("UserSettingsData.UnlockedPlatformRewards"));
    } 
    updateUI();
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */