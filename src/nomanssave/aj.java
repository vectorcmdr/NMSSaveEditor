package nomanssave;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Dialog.ModalExclusionType;
import java.util.Arrays;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;

public class aj extends JDialog {
   private static final List bW = Arrays.asList("Euclid", "Hilbert Dimension", "Calypso", "Hesperius Dimension", "Hyades", "Ickjamatew", "Budullangr", "Kikolgallr", "Eltiensleen", "Eissentam", "Elkupalos", "Aptarkaba", "Ontiniangp", "Odiwagiri", "Ogtialabi", "Muhacksonto", "Hitonskyer", "Rerasmutul", "Isdoraijung", "Doctinawyra", "Loychazinq", "Zukasizawa", "Ekwathore", "Yeberhahne", "Twerbetek", "Sivarates", "Eajerandal", "Aldukesci", "Wotyarogii", "Sudzerbal", "Maupenzhay", "Sugueziume", "Brogoweldian", "Ehbogdenbu", "Ijsenufryos", "Nipikulha", "Autsurabin", "Lusontrygiamh", "Rewmanawa", "Ethiophodhe", "Urastrykle", "Xobeurindj", "Oniijialdu", "Wucetosucc", "Ebyeloofdud", "Odyavanta", "Milekistri", "Waferganh", "Agnusopwit", "Teyaypilny", "Zalienkosm", "Ladgudiraf", "Mushonponte", "Amsentisz", "Fladiselm", "Laanawemb", "Ilkerloor", "Davanossi", "Ploehrliou", "Corpinyaya", "Leckandmeram", "Quulngais", "Nokokipsechl", "Rinblodesa", "Loydporpen", "Ibtrevskip", "Elkowaldb", "Heholhofsko", "Yebrilowisod", "Husalvangewi", "Ovna'uesed", "Bahibusey", "Nuybeliaure", "Doshawchuc", "Ruckinarkh", "Thorettac", "Nuponoparau", "Moglaschil", "Uiweupose", "Nasmilete", "Ekdaluskin", "Hakapanasy", "Dimonimba", "Cajaccari", "Olonerovo", "Umlanswick", "Henayliszm", "Utzenmate", "Umirpaiya", "Paholiang", "Iaereznika", "Yudukagath", "Boealalosnj", "Yaevarcko", "Coellosipp", "Wayndohalou", "Smoduraykl", "Apmaneessu", "Hicanpaav", "Akvasanta", "Tuychelisaor", "Rivskimbe", "Daksanquix", "Kissonlin", "Aediabiel", "Ulosaginyik", "Roclaytonycar", "Kichiaroa", "Irceauffey", "Nudquathsenfe", "Getaizakaal", "Hansolmien", "Bloytisagra", "Ladsenlay", "Luyugoslasr", "Ubredhatk", "Cidoniana", "Jasinessa", "Torweierf", "Saffneckm", "Thnistner", "Dotusingg", "Luleukous", "Jelmandan", "Otimanaso", "Enjaxusanto", "Sezviktorew", "Zikehpm", "Bephembah", "Broomerrai", "Meximicka", "Venessika", "Gaiteseling", "Zosakasiro", "Drajayanes", "Ooibekuar", "Urckiansi", "Dozivadido", "Emiekereks", "Meykinunukur", "Kimycuristh", "Roansfien", "Isgarmeso", "Daitibeli", "Gucuttarik", "Enlaythie", "Drewweste", "Akbulkabi", "Homskiw", "Zavainlani", "Jewijkmas", "Itlhotagra", "Podalicess", "Hiviusauer", "Halsebenk", "Puikitoac", "Gaybakuaria", "Grbodubhe", "Rycempler", "Indjalala", "Fontenikk", "Pasycihelwhee", "Ikbaksmit", "Telicianses", "Oyleyzhan", "Uagerosat", "Impoxectin", "Twoodmand", "Hilfsesorbs", "Ezdaranit", "Wiensanshe", "Ewheelonc", "Litzmantufa", "Emarmatosi", "Mufimbomacvi", "Wongquarum", "Hapirajua", "Igbinduina", "Wepaitvas", "Sthatigudi", "Yekathsebehn", "Ebedeagurst", "Nolisonia", "Ulexovitab", "Iodhinxois", "Irroswitzs", "Bifredait", "Beiraghedwe", "Yeonatlak", "Cugnatachh", "Nozoryenki", "Ebralduri", "Evcickcandj", "Ziybosswin", "Heperclait", "Sugiuniam", "Aaseertush", "Uglyestemaa", "Horeroedsh", "Drundemiso", "Ityanianat", "Purneyrine", "Dokiessmat", "Nupiacheh", "Dihewsonj", "Rudrailhik", "Tweretnort", "Snatreetze", "Iwunddaracos", "Digarlewena", "Erquagsta", "Logovoloin", "Boyaghosganh", "Kuolungau", "Pehneldept", "Yevettiiqidcon", "Sahliacabru", "Noggalterpor", "Chmageaki", "Veticueca", "Vittesbursul", "Nootanore", "Innebdjerah", "Kisvarcini", "Cuzcogipper", "Pamanhermonsu", "Brotoghek", "Mibittara", "Huruahili", "Raldwicarn", "Ezdartlic", "Badesclema", "Isenkeyan", "Iadoitesu", "Yagrovoisi", "Ewcomechio", "Inunnunnoda", "Dischiutun", "Yuwarugha", "Ialmendra", "Reponudrle", "Rinjanagrbo", "Zeziceloh", "Oeileutasc", "Zicniijinis", "Dugnowarilda", "Neuxoisan", "Ilmenhorn", "Rukwatsuku", "Nepitzaspru", "Chcehoemig", "Haffneyrin", "Uliciawai", "Tuhgrespod", "Iousongola", "Odyalutai", "Yilsrussimil");
   private JComboBox bX;
   private JTextField bY;
   private JTextField bZ;
   private JLabel[] ca;
   private JTextField m;
   private ImageIcon[] cb;
   private hl cc = null;
   private boolean cd = false;
   private static final String ce = "0123456789ABCDEF";
   private static aj cf = null;

   private aj(Frame var1) {
      super(var1);
      this.setResizable(false);
      this.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
      this.setTitle("Coordinate Viewer");
      this.setModal(true);
      JPanel var2 = new JPanel();
      this.setContentPane(var2);
      var2.setLayout(new BorderLayout(0, 0));
      JPanel var3 = new JPanel();
      var3.setLayout(new FormLayout(new ColumnSpec[]{FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("100px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("100px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("bottom:10px"), FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC}));
      JLabel var4 = new JLabel("Search:");
      var4.putClientProperty("FlatLaf.styleClass", "semibold");
      var3.add(var4, "2, 2, left, center");
      this.m = new JTextField();
      var3.add(this.m, "4, 2, fill, default");
      JButton var5 = new JButton("Search");
      var5.addActionListener(new ak(this));
      var3.add(var5, "6, 2, fill, fill");
      JLabel var6 = new JLabel("Coordinate Location:");
      var6.putClientProperty("FlatLaf.styleClass", "semibold");
      var3.add(var6, "2, 6, 5, 1, left, center");
      JLabel var7 = new JLabel("Galaxy:");
      var3.add(var7, "2, 8, left, center");
      this.bX = new JComboBox();
      this.bX.setModel(new al(this));
      var3.add(this.bX, "4, 8, 3, 1, fill, default");
      JLabel var8 = new JLabel("Galactic Addr:");
      var3.add(var8, "2, 10, left, center");
      this.bY = new JTextField();
      this.bY.setEditable(false);
      var3.add(this.bY, "4, 10, 3, 1, fill, default");
      JLabel var9 = new JLabel("Portal Addr:");
      var3.add(var9, "2, 12, left, center");
      this.bZ = new JTextField();
      this.bZ.setEditable(false);
      var3.add(this.bZ, "4, 12, 3, 1, fill, default");
      JPanel var10 = new JPanel();
      var10.setBackground(Color.GRAY);
      var10.setBorder(new LineBorder(Color.DARK_GRAY));
      var10.setLayout(new FlowLayout(1, 5, 5));
      var10.setMinimumSize(new Dimension(449, 42));
      this.cb = new ImageIcon[16];

      int var11;
      for(var11 = 0; var11 < 16; ++var11) {
         this.cb[var11] = Application.a("UI-GLYPH" + (var11 + 1) + ".PNG");
      }

      this.ca = new JLabel[12];

      for(var11 = 0; var11 < 12; ++var11) {
         this.ca[var11] = new JLabel(this.cb[0]);
         var10.add(this.ca[var11]);
      }

      var3.add(var10, "2, 14, 5, 1, fill, fill");
      var2.add(var3);
      JPanel var14 = new JPanel();
      var14.setLayout(new FlowLayout(2));
      var2.add(var14, "South");
      JButton var12 = new JButton("Save / Warp");
      var12.addActionListener(new am(this));
      var14.add(var12);
      this.getRootPane().setDefaultButton(var12);
      JButton var13 = new JButton("Cancel");
      var13.addActionListener(new an(this));
      var14.add(var13);
      this.getRootPane().registerKeyboardAction(new ao(this), KeyStroke.getKeyStroke(27, 0), 2);
      this.pack();
   }

   private void P() {
      this.bX.setSelectedIndex(this.cc.es() >= bW.size() ? -1 : this.cc.es());
      this.bX.updateUI();
      this.bY.setText(this.cc.ez());
      String var1 = this.cc.ey();
      this.bZ.setText(var1);

      for(int var2 = 0; var2 < 12; ++var2) {
         int var3 = "0123456789ABCDEF".indexOf(var1.charAt(var2));
         this.ca[var2].setIcon(var3 < 0 ? null : this.cb[var3]);
      }

   }

   private hl a(hl var1) {
      this.cc = var1;
      this.m.setText("");
      this.P();
      this.cd = false;
      this.setLocationRelativeTo(this.getParent());
      this.setVisible(true);
      return this.cd ? this.cc : null;
   }

   public static hl a(Container var0, hl var1) {
      if (cf == null) {
         Frame var2 = JOptionPane.getFrameForComponent(var0);
         cf = new aj(var2);
      }

      return cf.a(var1);
   }

   // $FF: synthetic method
   static JTextField a(aj var0) {
      return var0.m;
   }

   // $FF: synthetic method
   static JComboBox b(aj var0) {
      return var0.bX;
   }

   // $FF: synthetic method
   static void a(aj var0, hl var1) {
      var0.cc = var1;
   }

   // $FF: synthetic method
   static void c(aj var0) {
      var0.P();
   }

   // $FF: synthetic method
   static List Q() {
      return bW;
   }

   // $FF: synthetic method
   static void a(aj var0, boolean var1) {
      var0.cd = var1;
   }
}
