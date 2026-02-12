using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class cg : Form {
   public TextBox fn;
   public Label fo;
   public TextBox fp;
   public TextBox fq;
   public Label fr;
   public TextBox fs;
   public G ft;
   public Label fu;
   public G fv;
   public TextBox fw;
   public TextBox fx;
   public TextBox fy;
   public TextBox fz;
   public ey fA;
   public gQ fB;
   public Integer fC;
   public Integer fD;
   public static cg fE = null;

public cg(Frame var1) : base(var1) {
      this.Size = new Size(600, 480);
      this.setModalExclusionType(/* ModalExclusionType */ 0);
      this.Text = ("Item Details");
      this/* setModal */(true);
      Panel var2 = new Panel();
      // TODO: var2.SuspendLayout(); // TODO: set layout /* FormLayout */ null, FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("100px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("64px"), FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("default:grow"), FormFactory.LINE_GAP_ROWSPEC}));
      var2.Add(new Label() { Text = "Type:" }, "2, 2, left, center");
      this.fn = new TextBox();
      this.fn.setEditable(false);
      var2.Add(this.fn, "4, 2, fill, default");
      this.fo = new Label() { Text = "" };
      var2.Add(this.fo, "6, 2, 1, 7, center, fill");
      var2.Add(new Label() { Text = "Category:" }, "2, 4, left, center");
      this.fp = new TextBox();
      this.fp.setEditable(false);
      var2.Add(this.fp, "4, 4, fill, default");
      var2.Add(new Label() { Text = "Name:" }, "2, 6, left, center");
      this.fq = new TextBox();
      this.fq.setEditable(false);
      var2.Add(this.fq, "4, 6, fill, default");
      var2.Add(new Label() { Text = "ID:" }, "2, 8, left, center");
      Panel var3 = new Panel();
      // TODO: var3.SuspendLayout(); // TODO: set layout /* FormLayout */ null, FormFactory.DEFAULT_COLSPEC, ColumnSpec.decode("100px")}, new RowSpec[]{FormFactory.DEFAULT_ROWSPEC}));
      this.fs = new TextBox();
      this.fs.setEditable(false);
      var3.Add(this.fs, "1, 1");
      this.fr = new Label() { Text = "#" };
      var3.Add(this.fr, "2, 1");
      this.ft = new ch(this);
      this.ft.setEditable(false);
      var3.Add(this.ft, "3, 1");
      var2.Controls.Add(var3);
      this.fu = new Label() { Text = "Quantity:" };
      var2.Add(this.fu, "2, 10, left, center");
      var3 = new Panel();
      // TODO: var3.SuspendLayout(); // TODO: set layout /* FormLayout */ null, FormFactory.DEFAULT_COLSPEC, ColumnSpec.decode("100px")}, new RowSpec[]{FormFactory.DEFAULT_ROWSPEC}));
      this.fv = new ci(this);
      this.fv.setEditable(false);
      var3.Add(this.fv, "1, 1");
      var3.Add(new Label() { Text = "/" }, "2, 1");
      this.fw = new TextBox();
      this.fw.setEditable(false);
      var3.Add(this.fw, "3, 1");
      var2.Controls.Add(var3);
      var2.Add(new Label() { Text = "Subtitle:" }, "2, 12, left, center");
      this.fx = new TextBox();
      this.fx.setEditable(false);
      var2.Add(this.fx, "4, 12, 3, 1, fill, default");
      var2.Add(new Label() { Text = "Build Cost:" }, "2, 14, left, top");
      Panel var4 = new Panel();
      var4.Padding = new Padding(0); /* setBorder */ //(this.fx.getBorder());
      var4.setBackground(this.fx.getBackground());
      this.fy = new TextBox();
      this.fy.setEditable(false);
      this.fy.Padding = new Padding(0); /* setBorder */ //((Border)null);
      this.fy.setBackground((Color)null);
      this.fy.setFont(this.fx.getFont());
      var4.setViewportView(this.fy);
      var2.Controls.Add(var4);
      var2.Add(new Label() { Text = "Description:" }, "2, 16, left, top");
      this.fz = new TextBox();
      this.fz.setEditable(false);
      this.fz.setWrapStyleWord(true);
      this.fz.setLineWrap(true);
      this.fz.Padding = new Padding(0); /* setBorder */ //(this.fx.getBorder());
      this.fz.setBackground(this.fx.getBackground());
      this.fz.setFont(this.fx.getFont());
      var2.Add(this.fz, "4, 16, 3, 1, fill, fill");
      this.setContentPane(var2);
      this.getRootPane().registerKeyboardAction(new cj(this), /* KeyStroke */ Keys.None /* (27, 0) */, 2);
      this.addWindowListener(new ck(this));
   }

   public void a(gQ var1) {
      this.fB = var1;
      object var2 = var1.dz();
      this.fA = ey.d(var2);
      this.fC = null;
      this.fD = null;
      string var3 = this.fA == null ? var1.getType() : this.fA.ba().ToString();
      this.fn.Text = (var3);
      this.fo.setIcon(this.fA == null ? null : this.fA.N(2));
      string var4;
      if (this.fA != null && this.fA.bb()) {
         var4 = "";
         int var6;
         if (var2 is fg) {
            fg var5 = (fg)var2;
            var6 = var5.IndexOf(35);
            if (var6 >= 0) {
               var4 = var5.Substring(var6 + 1);
            }
         } else {
            string var8 = var2.ToString();
            var6 = var8.IndexOf(35);
            if (var6 >= 0) {
               var4 = var8.Substring(var6 + 1);
            }
         }

         this.fs.Text = (this.fA.getID());
         this.ft.Text = (var4);
         this.fr.Show();
         this.ft.Show();

         try {
            int var9 = hf.b(var4, 0, 99999);
            this.fC = ((int)(var9));
            this.ft.setEditable(true);
         } catch (Exception var7) {
            hc.warn("Error detected in item id: " + var2);
            this.fC = null;
            this.ft.setEditable(false);
         }
      } else {
         this.fs.Text = (var1.ei());
         this.ft.Text = ("");
         this.fr.Hide();
         this.ft.Hide();
      }

      if (var3.Equals("Technology") && var1.dA() >= 0 && var1.dA() < var1.dB()) {
         this.fu.Text = ("Charge:");
         this.fD = var1.dA();
         this.fv.Text = (Integer.toString(var1.dA()));
         this.fw.Text = (Integer.toString(var1.dB()));
         this.fv.setEditable(true);
      } else if ((var3.Equals("Product") || var3.Equals("Substance")) && var1.dB() > 1) {
         this.fu.Text = ("Quantity:");
         this.fD = var1.dA();
         this.fv.Text = (Integer.toString(var1.dA()));
         this.fw.Text = (Integer.toString(var1.dB()));
         this.fv.setEditable(true);
      } else {
         this.fu.Text = ("Quantity:");
         this.fv.Text = ("1");
         this.fw.Text = ("1");
         this.fv.setEditable(false);
      }

      this.fq.Text = (this.fA == null ? "[Unknown]" : this.fA.Name);
      this.fp.Text = (this.fA == null ? "[Unknown]" : this.fA.bc().ToString());
      this.fx.Text = (this.fA == null ? "" : this.fA.bg());
      var4 = this.fA == null ? "" : (string)this.fA.bk().stream().map((var0) => {
         ey var1 = ey.d(var0.getID());
         return var1 != null ? var1.Name + " (x" + var0.bo() + ")" : var0.getID() + " (x" + var0.bo() + ")";
      }).collect(Collectors.joining("\n"));
      if (var4.length() == 0) {
         var4 = "N/A";
      }

      this.fy.Text = (var4);
      this.fy.setCaretPosition(0);
      this.fz.Text = (this.fA == null ? "" : this.fA.getDescription());
      this.StartPosition = FormStartPosition.CenterParent; //(this.DirectoryName);
      this.Show();
   }

   public static void a(Container var0, gQ var1) {
      if (fE == null) {
         Frame var2 = null;
         fE = new cg(var2);
      }

      fE.a(var1);
   }
   static Integer a(cg var0) {
      return var0.fC;
   }
   public static gQ b(cg var0) {
      return var0.fB;
   }
   public static ey c(cg var0) {
      return var0.fA;
   }
   static void a(cg var0, Integer var1) {
      var0.fC = var1;
   }
   public static Integer d(cg var0) {
      return var0.fD;
   }
   public static void b(cg var0, Integer var1) {
      var0.fD = var1;
   }
   public static G e(cg var0) {
      return var0.ft;
   }
   public static G f(cg var0) {
      return var0.fv;
   }
}



}
