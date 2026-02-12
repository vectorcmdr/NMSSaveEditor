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

public class aj : Form {
   public static List<object> bW = new List<object> {"Euclid", "Hilbert Size", "Calypso", "Hesperius Size", "Hyades", "Ickjamatew", "Budullangr", "Kikolgallr", "Eltiensleen", "Eissentam", "Elkupalos", "Aptarkaba", "Ontiniangp", "Odiwagiri", "Ogtialabi", "Muhacksonto", "Hitonskyer", "Rerasmutul", "Isdoraijung", "Doctinawyra", "Loychazinq", "Zukasizawa", "Ekwathore", "Yeberhahne", "Twerbetek", "Sivarates", "Eajerandal", "Aldukesci", "Wotyarogii", "Sudzerbal", "Maupenzhay", "Sugueziume", "Brogoweldian", "Ehbogdenbu", "Ijsenufryos", "Nipikulha", "Autsurabin", "Lusontrygiamh", "Rewmanawa", "Ethiophodhe", "Urastrykle", "Xobeurindj", "Oniijialdu", "Wucetosucc", "Ebyeloofdud", "Odyavanta", "Milekistri", "Waferganh", "Agnusopwit", "Teyaypilny", "Zalienkosm", "Ladgudiraf", "Mushonponte", "Amsentisz", "Fladiselm", "Laanawemb", "Ilkerloor", "Davanossi", "Ploehrliou", "Corpinyaya", "Leckandmeram", "Quulngais", "Nokokipsechl", "Rinblodesa", "Loydporpen", "Ibtrevskip", "Elkowaldb", "Heholhofsko", "Yebrilowisod", "Husalvangewi", "Ovna'uesed", "Bahibusey", "Nuybeliaure", "Doshawchuc", "Ruckinarkh", "Thorettac", "Nuponoparau", "Moglaschil", "Uiweupose", "Nasmilete", "Ekdaluskin", "Hakapanasy", "Dimonimba", "Cajaccari", "Olonerovo", "Umlanswick", "Henayliszm", "Utzenmate", "Umirpaiya", "Paholiang", "Iaereznika", "Yudukagath", "Boealalosnj", "Yaevarcko", "Coellosipp", "Wayndohalou", "Smoduraykl", "Apmaneessu", "Hicanpaav", "Akvasanta", "Tuychelisaor", "Rivskimbe", "Daksanquix", "Kissonlin", "Aediabiel", "Ulosaginyik", "Roclaytonycar", "Kichiaroa", "Irceauffey", "Nudquathsenfe", "Getaizakaal", "Hansolmien", "Bloytisagra", "Ladsenlay", "Luyugoslasr", "Ubredhatk", "Cidoniana", "Jasinessa", "Torweierf", "Saffneckm", "Thnistner", "Dotusingg", "Luleukous", "Jelmandan", "Otimanaso", "Enjaxusanto", "Sezviktorew", "Zikehpm", "Bephembah", "Broomerrai", "Meximicka", "Venessika", "Gaiteseling", "Zosakasiro", "Drajayanes", "Ooibekuar", "Urckiansi", "Dozivadido", "Emiekereks", "Meykinunukur", "Kimycuristh", "Roansfien", "Isgarmeso", "Daitibeli", "Gucuttarik", "Enlaythie", "Drewweste", "Akbulkabi", "Homskiw", "Zavainlani", "Jewijkmas", "Itlhotagra", "Podalicess", "Hiviusauer", "Halsebenk", "Puikitoac", "Gaybakuaria", "Grbodubhe", "Rycempler", "Indjalala", "Fontenikk", "Pasycihelwhee", "Ikbaksmit", "Telicianses", "Oyleyzhan", "Uagerosat", "Impoxectin", "Twoodmand", "Hilfsesorbs", "Ezdaranit", "Wiensanshe", "Ewheelonc", "Litzmantufa", "Emarmatosi", "Mufimbomacvi", "Wongquarum", "Hapirajua", "Igbinduina", "Wepaitvas", "Sthatigudi", "Yekathsebehn", "Ebedeagurst", "Nolisonia", "Ulexovitab", "Iodhinxois", "Irroswitzs", "Bifredait", "Beiraghedwe", "Yeonatlak", "Cugnatachh", "Nozoryenki", "Ebralduri", "Evcickcandj", "Ziybosswin", "Heperclait", "Sugiuniam", "Aaseertush", "Uglyestemaa", "Horeroedsh", "Drundemiso", "Ityanianat", "Purneyrine", "Dokiessmat", "Nupiacheh", "Dihewsonj", "Rudrailhik", "Tweretnort", "Snatreetze", "Iwunddaracos", "Digarlewena", "Erquagsta", "Logovoloin", "Boyaghosganh", "Kuolungau", "Pehneldept", "Yevettiiqidcon", "Sahliacabru", "Noggalterpor", "Chmageaki", "Veticueca", "Vittesbursul", "Nootanore", "Innebdjerah", "Kisvarcini", "Cuzcogipper", "Pamanhermonsu", "Brotoghek", "Mibittara", "Huruahili", "Raldwicarn", "Ezdartlic", "Badesclema", "Isenkeyan", "Iadoitesu", "Yagrovoisi", "Ewcomechio", "Inunnunnoda", "Dischiutun", "Yuwarugha", "Ialmendra", "Reponudrle", "Rinjanagrbo", "Zeziceloh", "Oeileutasc", "Zicniijinis", "Dugnowarilda", "Neuxoisan", "Ilmenhorn", "Rukwatsuku", "Nepitzaspru", "Chcehoemig", "Haffneyrin", "Uliciawai", "Tuhgrespod", "Iousongola", "Odyalutai", "Yilsrussimil"};
   public ComboBox bX;
   public TextBox bY;
   public TextBox bZ;
   public Label[] ca;
   public TextBox m;
   public Image[] cb;
   public hl cc = null;
   public bool cd = false;
   public static string ce = "0123456789ABCDEF";
   public static aj cf = null;

   public aj(Form var1) {
      // base(var1);
      this.SetResizable(false);
      this.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
      this.SetTitle("Coordinate Viewer");
      this.SetModal(true);
      Panel var2 = new Panel();
      this.SetContentPane(var2);
      var2.SetLayout(new BorderLayout(0, 0));
      Panel var3 = new Panel();
      var3.SetLayout(new FormLayout(new ColumnSpec[]{FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("100px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("100px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, "default", FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("bottom:10px"), FormFactory.LINE_GAP_ROWSPEC, "default", FormFactory.LINE_GAP_ROWSPEC, "default", FormFactory.LINE_GAP_ROWSPEC, "default", FormFactory.LINE_GAP_ROWSPEC, "default", FormFactory.LINE_GAP_ROWSPEC, "default", FormFactory.LINE_GAP_ROWSPEC}));
      Label var4 = new Label() { Text = "Search:" };
      var4.putClientProperty("FlatLaf.styleClass", "semibold");
      var3.Add(var4, "2, 2, left, center");
      this.m = new TextBox();
      var3.Add(this.m, "4, 2, fill, default");
      Button var5 = new Button() { Text = "Search" };
      var5.AddActionListener(new ak(this));
      var3.Add(var5, "6, 2, fill, fill");
      Label var6 = new Label() { Text = "Coordinate Location:" };
      var6.putClientProperty("FlatLaf.styleClass", "semibold");
      var3.Add(var6, "2, 6, 5, 1, left, center");
      Label var7 = new Label() { Text = "Galaxy:" };
      var3.Add(var7, "2, 8, left, center");
      this.bX = new ComboBox();
      this.bX.SetModel(new al(this));
      var3.Add(this.bX, "4, 8, 3, 1, fill, default");
      Label var8 = new Label() { Text = "Galactic Addr:" };
      var3.Add(var8, "2, 10, left, center");
      this.bY = new TextBox();
      this.bY.setEditable(false);
      var3.Add(this.bY, "4, 10, 3, 1, fill, default");
      Label var9 = new Label() { Text = "Portal Addr:" };
      var3.Add(var9, "2, 12, left, center");
      this.bZ = new TextBox();
      this.bZ.setEditable(false);
      var3.Add(this.bZ, "4, 12, 3, 1, fill, default");
      Panel var10 = new Panel();
      var10.SetBackground(Color.Gray);
      var10.SetBorder(new LineBorder(Color.DarkGray));
      var10.SetLayout(new FlowLayoutPanel());
      var10.SetMinimumSize(new Size(449, 42));
      this.cb = new Image[16];
       int var11;
      for(var11 = 0; var11 < 16; ++var11) {
         this.cb[var11] = Application.a("UI-GLYPH" + (var11 + 1) + ".PNG");
      }
       this.ca = new Label[12];
       for(var11 = 0; var11 < 12; ++var11) {
         this.ca[var11] = new Label(this.cb[0]);
         var10.Add(this.ca[var11]);
      }
       var3.Add(var10, "2, 14, 5, 1, fill, fill");
      var2.Add(var3);
      Panel var14 = new Panel();
      var14.SetLayout(new FlowLayoutPanel());
      var2.Add(var14, "South");
      Button var12 = new Button() { Text = "Save / Warp" };
      var12.AddActionListener(new am(this));
      var14.Add(var12);
      this.GetRootPane().setDefaultButton(var12);
      Button var13 = new Button() { Text = "Cancel" };
      var13.AddActionListener(new an(this));
      var14.Add(var13);
      this.GetRootPane().registerKeyboardAction(new ao(this), null, 2);
      this.Pack();
   }

   public void P() {
      this.bX.SetSelectedIndex(this.cc.es() >= bW.Count ? -1 : this.cc.es());
      this.bX.updateUI();
      this.bY.SetText(this.cc.ez());
      string var1 = this.cc.ey();
      this.bZ.SetText(var1);

      for(int var2 = 0; var2 < 12; ++var2) {
         int var3 = "0123456789ABCDEF".IndexOf(var1[var2]);
         this.ca[var2].setIcon(var3 < 0 ? null : this.cb[var3]);
      }

   }

   public hl a(hl var1) {
      this.cc = var1;
      this.m.SetText("");
      this.P();
      this.cd = false;
      this.SetLocationRelativeTo(this.Parent);
      this.SetVisible(true);
      return this.cd ? this.cc : null;
   }

   public static hl a(Container var0, hl var1) {
      if (cf == null) {
         Form var2 = JOptionPane.getFrameForComponent(var0);
         cf = new aj(var2);
      }

      return cf.a(var1);
   }

   // $FF: synthetic method
   public static TextBox a(aj var0) {
      return var0.m;
   }

   // $FF: synthetic method
   public static ComboBox b(aj var0) {
      return var0.bX;
   }

   // $FF: synthetic method
   public static void a(aj var0, hl var1) {
      var0.cc = var1;
   }

   // $FF: synthetic method
   public static void c(aj var0) {
      var0.P();
   }

   // $FF: synthetic method
   public static List<object> Q() {
      return bW;
   }

   // $FF: synthetic method
   public static void a(aj var0, bool var1) {
      var0.cd = var1;
   }
}

}
