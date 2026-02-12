using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class aj : Form {
   public static List<object> bW = new List<object>(new object[]{"Euclid", "Hilbert Size", "Calypso", "Hesperius Size", "Hyades", "Ickjamatew", "Budullangr", "Kikolgallr", "Eltiensleen", "Eissentam", "Elkupalos", "Aptarkaba", "Ontiniangp", "Odiwagiri", "Ogtialabi", "Muhacksonto", "Hitonskyer", "Rerasmutul", "Isdoraijung", "Doctinawyra", "Loychazinq", "Zukasizawa", "Ekwathore", "Yeberhahne", "Twerbetek", "Sivarates", "Eajerandal", "Aldukesci", "Wotyarogii", "Sudzerbal", "Maupenzhay", "Sugueziume", "Brogoweldian", "Ehbogdenbu", "Ijsenufryos", "Nipikulha", "Autsurabin", "Lusontrygiamh", "Rewmanawa", "Ethiophodhe", "Urastrykle", "Xobeurindj", "Oniijialdu", "Wucetosucc", "Ebyeloofdud", "Odyavanta", "Milekistri", "Waferganh", "Agnusopwit", "Teyaypilny", "Zalienkosm", "Ladgudiraf", "Mushonponte", "Amsentisz", "Fladiselm", "Laanawemb", "Ilkerloor", "Davanossi", "Ploehrliou", "Corpinyaya", "Leckandmeram", "Quulngais", "Nokokipsechl", "Rinblodesa", "Loydporpen", "Ibtrevskip", "Elkowaldb", "Heholhofsko", "Yebrilowisod", "Husalvangewi", "Ovna'uesed", "Bahibusey", "Nuybeliaure", "Doshawchuc", "Ruckinarkh", "Thorettac", "Nuponoparau", "Moglaschil", "Uiweupose", "Nasmilete", "Ekdaluskin", "Hakapanasy", "Dimonimba", "Cajaccari", "Olonerovo", "Umlanswick", "Henayliszm", "Utzenmate", "Umirpaiya", "Paholiang", "Iaereznika", "Yudukagath", "Boealalosnj", "Yaevarcko", "Coellosipp", "Wayndohalou", "Smoduraykl", "Apmaneessu", "Hicanpaav", "Akvasanta", "Tuychelisaor", "Rivskimbe", "Daksanquix", "Kissonlin", "Aediabiel", "Ulosaginyik", "Roclaytonycar", "Kichiaroa", "Irceauffey", "Nudquathsenfe", "Getaizakaal", "Hansolmien", "Bloytisagra", "Ladsenlay", "Luyugoslasr", "Ubredhatk", "Cidoniana", "Jasinessa", "Torweierf", "Saffneckm", "Thnistner", "Dotusingg", "Luleukous", "Jelmandan", "Otimanaso", "Enjaxusanto", "Sezviktorew", "Zikehpm", "Bephembah", "Broomerrai", "Meximicka", "Venessika", "Gaiteseling", "Zosakasiro", "Drajayanes", "Ooibekuar", "Urckiansi", "Dozivadido", "Emiekereks", "Meykinunukur", "Kimycuristh", "Roansfien", "Isgarmeso", "Daitibeli", "Gucuttarik", "Enlaythie", "Drewweste", "Akbulkabi", "Homskiw", "Zavainlani", "Jewijkmas", "Itlhotagra", "Podalicess", "Hiviusauer", "Halsebenk", "Puikitoac", "Gaybakuaria", "Grbodubhe", "Rycempler", "Indjalala", "Fontenikk", "Pasycihelwhee", "Ikbaksmit", "Telicianses", "Oyleyzhan", "Uagerosat", "Impoxectin", "Twoodmand", "Hilfsesorbs", "Ezdaranit", "Wiensanshe", "Ewheelonc", "Litzmantufa", "Emarmatosi", "Mufimbomacvi", "Wongquarum", "Hapirajua", "Igbinduina", "Wepaitvas", "Sthatigudi", "Yekathsebehn", "Ebedeagurst", "Nolisonia", "Ulexovitab", "Iodhinxois", "Irroswitzs", "Bifredait", "Beiraghedwe", "Yeonatlak", "Cugnatachh", "Nozoryenki", "Ebralduri", "Evcickcandj", "Ziybosswin", "Heperclait", "Sugiuniam", "Aaseertush", "Uglyestemaa", "Horeroedsh", "Drundemiso", "Ityanianat", "Purneyrine", "Dokiessmat", "Nupiacheh", "Dihewsonj", "Rudrailhik", "Tweretnort", "Snatreetze", "Iwunddaracos", "Digarlewena", "Erquagsta", "Logovoloin", "Boyaghosganh", "Kuolungau", "Pehneldept", "Yevettiiqidcon", "Sahliacabru", "Noggalterpor", "Chmageaki", "Veticueca", "Vittesbursul", "Nootanore", "Innebdjerah", "Kisvarcini", "Cuzcogipper", "Pamanhermonsu", "Brotoghek", "Mibittara", "Huruahili", "Raldwicarn", "Ezdartlic", "Badesclema", "Isenkeyan", "Iadoitesu", "Yagrovoisi", "Ewcomechio", "Inunnunnoda", "Dischiutun", "Yuwarugha", "Ialmendra", "Reponudrle", "Rinjanagrbo", "Zeziceloh", "Oeileutasc", "Zicniijinis", "Dugnowarilda", "Neuxoisan", "Ilmenhorn", "Rukwatsuku", "Nepitzaspru", "Chcehoemig", "Haffneyrin", "Uliciawai", "Tuhgrespod", "Iousongola", "Odyalutai", "Yilsrussimil"});
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

// PORT_TODO: public aj(Frame var1) : base(var1) {
      // PORT_TODO: this.FormBorderStyle = FormBorderStyle.FixedDialog; //(false);
      // setModalExclusionType not available in WinForms
      // PORT_TODO: this.Text = ("Coordinate Viewer");
      // PORT_TODO: // PORT_TODO: this/* setModal */(true);
      // PORT_TODO: Panel var2 = new Panel();
      // PORT_TODO: this.setContentPane(var2);
      // PORT_TODO: var2.SuspendLayout(); // TODO: set layout new TableLayoutPanel());
      // PORT_TODO: Panel var3 = new Panel();
      // TODO: var3.SuspendLayout(); // TODO: set layout /* FormLayout */ null, FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("100px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("bottom:10px"), FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC}));
      // PORT_TODO: Label var4 = new Label() { Text = "Search:" };
      // TODO: var4.putClientProperty(...);
      // PORT_TODO: var3.Controls.Add(var4);
      // PORT_TODO: this.m = new TextBox();
      // PORT_TODO: var3.Add(this.m, "4, 2, fill, default");
      // PORT_TODO: Button var5 = new Button() { Text = "Search" };
      // PORT_TODO: var5.Click += (new ak(this));
      // PORT_TODO: var3.Controls.Add(var5);
      // PORT_TODO: Label var6 = new Label() { Text = "Coordinate Location:" };
      // TODO: var6.putClientProperty(...);
      // PORT_TODO: var3.Controls.Add(var6);
      // PORT_TODO: Label var7 = new Label() { Text = "Galaxy:" };
      // PORT_TODO: var3.Controls.Add(var7);
      // PORT_TODO: this.bX = new ComboBox();
      // PORT_TODO: this.bX.DataSource = (new al(this));
      // PORT_TODO: var3.Add(this.bX, "4, 8, 3, 1, fill, default");
      // PORT_TODO: Label var8 = new Label() { Text = "Galactic Addr:" };
      // PORT_TODO: var3.Controls.Add(var8);
      // PORT_TODO: this.bY = new TextBox();
      // PORT_TODO: this.bY.setEditable(false);
      // PORT_TODO: var3.Add(this.bY, "4, 10, 3, 1, fill, default");
      // PORT_TODO: Label var9 = new Label() { Text = "Portal Addr:" };
      // PORT_TODO: var3.Controls.Add(var9);
      // PORT_TODO: this.bZ = new TextBox();
      // PORT_TODO: this.bZ.setEditable(false);
      // PORT_TODO: var3.Add(this.bZ, "4, 12, 3, 1, fill, default");
      // PORT_TODO: Panel var10 = new Panel();
      // PORT_TODO: var10.setBackground(Color.Gray);
      // PORT_TODO: var10.Padding = new Padding(0); /* setBorder */ //(new LineBorder(Color.DARK_GRAY));
      // PORT_TODO: var10.SuspendLayout(); // TODO: set layout new FlowLayoutPanel(1, 5, 5));
      // PORT_TODO: var10.setMinimumSize(new Size(449, 42));
      // PORT_TODO: this.cb = new Image[16];

// PORT_TODO: 
      // PORT_TODO: int var11;
      // PORT_TODO: for(var11 = 0; var11 < 16; ++var11) {
         // PORT_TODO: this.cb[var11] = Application.a("UI-GLYPH" + (var11 + 1) + ".PNG");
      // PORT_TODO: }

// PORT_TODO: 
      // PORT_TODO: this.ca = new Label[12];

// PORT_TODO: 
      // PORT_TODO: for(var11 = 0; var11 < 12; ++var11) {
         // PORT_TODO: this.ca[var11] = new Label(this.cb[0]);
         // PORT_TODO: var10.Add(this.ca[var11]);
      // PORT_TODO: }

// PORT_TODO: 
      // PORT_TODO: var3.Controls.Add(var10);
      // PORT_TODO: var2.Add(var3);
      // PORT_TODO: Panel var14 = new Panel();
      // PORT_TODO: var14.SuspendLayout(); // TODO: set layout new FlowLayoutPanel(2));
      // PORT_TODO: var2.Controls.Add(var14);
      // PORT_TODO: Button var12 = new Button() { Text = "Save / Warp" };
      // PORT_TODO: var12.Click += (new am(this));
      // PORT_TODO: var14.Add(var12);
      // PORT_TODO: this.getRootPane().setDefaultButton(var12);
      // PORT_TODO: Button var13 = new Button() { Text = "Cancel" };
      // PORT_TODO: var13.Click += (new an(this));
      // PORT_TODO: var14.Add(var13);
      // PORT_TODO: this.getRootPane().registerKeyboardAction(new ao(this), /* KeyStroke */ Keys.None /* (27, 0) */, 2);
      // PORT_TODO: this.PerformLayout();
   // PORT_TODO: }

   public void P() {
      this.bX.SelectedIndex = (this.cc.es() >= bW.Count ? -1 : this.cc.es());
      this.bX.Refresh();
      this.bY.Text = (this.cc.ez());
      string var1 = this.cc.ey();
      this.bZ.Text = (var1);

      for(int var2 = 0; var2 < 12; ++var2) {
         int var3 = "0123456789ABCDEF".IndexOf(var1[var2]);
         this.ca[var2].setIcon(var3 < 0 ? null : this.cb[var3]);
      }

   }

   public hl a(hl var1) {
      this.cc = var1;
      this.m.Text = ("");
      this.P();
      this.cd = false;
      this.StartPosition = FormStartPosition.CenterParent; //(this.DirectoryName);
      this.Show();
      return this.cd ? this.cc : null;
   }

   public static hl a(Container var0, hl var1) {
      if (cf == null) {
         Frame var2 = null;
         cf = new aj(var2);
      }

      return cf.a(var1);
   }
   static TextBox a(aj var0) {
      return var0.m;
   }
   public static ComboBox b(aj var0) {
      return var0.bX;
   }
   static void a(aj var0, hl var1) {
      var0.cc = var1;
   }
   public static void c(aj var0) {
      var0.P();
   }
   public static List<object> Q() {
      return bW;
   }
   static void a(aj var0, bool var1) {
      var0.cd = var1;
   }
}



}
