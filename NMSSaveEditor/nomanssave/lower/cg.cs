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

   public cg(Form var1) {
      // base(var1);
      this.SetSize(600, 480);
      this.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
      this.SetTitle("Item Details");
      this.SetModal(true);
      Panel var2 = new Panel();
      var2.SetLayout(new FormLayout(new ColumnSpec[]{FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("100px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("100px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC}, new RowSpec[]{FormFactory.LINE_GAP_ROWSPEC, "default", FormFactory.LINE_GAP_ROWSPEC, "default", FormFactory.LINE_GAP_ROWSPEC, "default", FormFactory.LINE_GAP_ROWSPEC, "default", FormFactory.LINE_GAP_ROWSPEC, "default", FormFactory.LINE_GAP_ROWSPEC, "default", FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("64px"), FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("default:grow"), FormFactory.LINE_GAP_ROWSPEC}));
      var2.Add(new Label("Type:"), "2, 2, left, center");
      this.fn = new TextBox();
      this.fn.setEditable(false);
      var2.Add(this.fn, "4, 2, fill, default");
      this.fo = new Label("");
      var2.Add(this.fo, "6, 2, 1, 7, center, fill");
      var2.Add(new Label("Category:"), "2, 4, left, center");
      this.fp = new TextBox();
      this.fp.setEditable(false);
      var2.Add(this.fp, "4, 4, fill, default");
      var2.Add(new Label("Name:"), "2, 6, left, center");
      this.fq = new TextBox();
      this.fq.setEditable(false);
      var2.Add(this.fq, "4, 6, fill, default");
      var2.Add(new Label("ID:"), "2, 8, left, center");
      Panel var3 = new Panel();
      var3.SetLayout(new FormLayout(new ColumnSpec[]{ColumnSpec.decode("default:grow"), "default", ColumnSpec.decode("100px")}, new RowSpec[]{"default"}));
      this.fs = new TextBox();
      this.fs.setEditable(false);
      var3.Add(this.fs, "1, 1");
      this.fr = new Label("#");
      var3.Add(this.fr, "2, 1");
      this.ft = new ch(this);
      this.ft.setEditable(false);
      var3.Add(this.ft, "3, 1");
      var2.Add(var3, "4, 8, fill, default");
      this.fu = new Label("Quantity:");
      var2.Add(this.fu, "2, 10, left, center");
      var3 = new Panel();
      var3.SetLayout(new FormLayout(new ColumnSpec[]{ColumnSpec.decode("100px"), "default", ColumnSpec.decode("100px")}, new RowSpec[]{"default"}));
      this.fv = new ci(this);
      this.fv.setEditable(false);
      var3.Add(this.fv, "1, 1");
      var3.Add(new Label("/"), "2, 1");
      this.fw = new TextBox();
      this.fw.setEditable(false);
      var3.Add(this.fw, "3, 1");
      var2.Add(var3, "4, 10, fill, default");
      var2.Add(new Label("Subtitle:"), "2, 12, left, center");
      this.fx = new TextBox();
      this.fx.setEditable(false);
      var2.Add(this.fx, "4, 12, 3, 1, fill, default");
      var2.Add(new Label("Build Cost:"), "2, 14, left, top");
      Panel var4 = new Panel();
      var4.SetBorder(this.fx.getBorder());
      var4.SetBackground(this.fx.BackColor);
      this.fy = new TextBox();
      this.fy.setEditable(false);
      this.fy.SetBorder((Border)null);
      this.fy.SetBackground((Color)null);
      this.fy.SetFont(this.fx.Font);
      var4.setViewportView(this.fy);
      var2.Add(var4, "4, 14, 3, 1, fill, fill");
      var2.Add(new Label("Description:"), "2, 16, left, top");
      this.fz = new TextBox();
      this.fz.setEditable(false);
      this.fz.setWrapStyleWord(true);
      this.fz.setLineWrap(true);
      this.fz.SetBorder(this.fx.getBorder());
      this.fz.SetBackground(this.fx.BackColor);
      this.fz.SetFont(this.fx.Font);
      var2.Add(this.fz, "4, 16, 3, 1, fill, fill");
      this.SetContentPane(var2);
      this.GetRootPane().registerKeyboardAction(new cj(this), null, 2);
      this.AddWindowListener(new ck(this));
   }

   public void a(gQ var1) {
      this.fB = var1;
      Object var2 = var1.dz();
      this.fA = ey.d(var2);
      this.fC = null;
      this.fD = null;
      string var3 = this.fA == null ? var1.getType() : this.fA.ba().ToString();
      this.fn.SetText(var3);
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
          this.fs.SetText(this.fA.getID());
         this.ft.SetText(var4);
         this.fr.SetVisible(true);
         this.ft.SetVisible(true);
          try {
            int var9 = hf.b(var4, 0, 99999);
            this.fC = new Integer(var9);
            this.ft.setEditable(true);
         } catch (Exception var7) {
            hc.warn("Error detected in item id: " + var2);
            this.fC = null;
            this.ft.setEditable(false);
         }
      } else {
         this.fs.SetText(var1.ei());
         this.ft.SetText("");
         this.fr.SetVisible(false);
         this.ft.SetVisible(false);
      }
       if (var3.Equals("Technology") && var1.dA() >= 0 && var1.dA() < var1.dB()) {
         this.fu.SetText("Charge:");
         this.fD = var1.dA();
         this.fv.SetText(Convert.ToString(var1.dA()));
         this.fw.SetText(Convert.ToString(var1.dB()));
         this.fv.setEditable(true);
      } else if ((var3.Equals("Product") || var3.Equals("Substance")) && var1.dB() > 1) {
         this.fu.SetText("Quantity:");
         this.fD = var1.dA();
         this.fv.SetText(Convert.ToString(var1.dA()));
         this.fw.SetText(Convert.ToString(var1.dB()));
         this.fv.setEditable(true);
      } else {
         this.fu.SetText("Quantity:");
         this.fv.SetText("1");
         this.fw.SetText("1");
         this.fv.setEditable(false);
      }
       this.fq.SetText(this.fA == null ? "[Unknown]" : this.fA.Name);
      this.fp.SetText(this.fA == null ? "[Unknown]" : this.fA.bc().ToString());
      this.fx.SetText(this.fA == null ? "" : this.fA.bg());
      if (this.fA != null) {
         var items = this.fA.bk().map((var0) => {
            ey var1x = ey.d(var0.getID());
            return var1x != null ? var1x.Name + " (x" + var0.bo() + ")" : var0.getID() + " (x" + var0.bo() + ")";
         });
         var4 = string.Join("\n", items);
      } else {
         var4 = "";
      }
      if (var4.Length == 0) {
         var4 = "N/A";
      }
       this.fy.SetText(var4);
      this.fy.setCaretPosition(0);
      this.fz.SetText(this.fA == null ? "" : this.fA.getDescription());
      this.SetLocationRelativeTo(this.Parent);
      this.SetVisible(true);
   }

   public static void a(Container var0, gQ var1) {
      if (fE == null) {
         Form var2 = JOptionPane.getFrameForComponent(var0);
         fE = new cg(var2);
      }

      fE.a(var1);
   }

   // $FF: synthetic method
   public static Integer a(cg var0) {
      return var0.fC;
   }

   // $FF: synthetic method
   public static gQ b(cg var0) {
      return var0.fB;
   }

   // $FF: synthetic method
   public static ey c(cg var0) {
      return var0.fA;
   }

   // $FF: synthetic method
   public static void a(cg var0, Integer var1) {
      var0.fC = var1;
   }

   // $FF: synthetic method
   public static Integer d(cg var0) {
      return var0.fD;
   }

   // $FF: synthetic method
   public static void b(cg var0, Integer var1) {
      var0.fD = var1;
   }

   // $FF: synthetic method
   public static G e(cg var0) {
      return var0.ft;
   }

   // $FF: synthetic method
   public static G f(cg var0) {
      return var0.fv;
   }
}

}
