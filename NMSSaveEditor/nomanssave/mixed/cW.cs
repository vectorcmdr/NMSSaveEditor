using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class cW : Panel, CaretListener {
   public static float gx = 0.0F;
   public static float gy = 0.5F;
   public static float gz = 1.0F;
   public static object gA;
   public static int HEIGHT = 2146483647;
   public JTextComponent gB;
   public bool gC;
   public int gD;
   public Color gE;
   public float gF;
   public int gG;
   public int gH;
   public int gI;
   public int gJ;
   public Dictionary<object, object> gK;

   static cW() {
      gA = new MatteBorder(0, 0, 0, 2, Color.GRAY);
   }

   public cW(JTextComponent var1) {
      this(var1, 3);
   }

   public cW(JTextComponent var1, int var2) {
      this.gB = var1;
      this.setFont(var1.getFont());
      this.y(5);
      this.a(Color.RED);
      this.a(1.0F);
      this.z(var2);
      var1.getDocument().addDocumentListener(this);
      var1.addCaretListener(this);
      var1.addPropertyChangeListener("font", this);
   }

   public bool aD() {
      return this.gC;
   }

   public void b(bool var1) {
      this.gC = var1;
   }

   public int aE() {
      return this.gD;
   }

   public void y(int var1) {
      this.gD = var1;
      EmptyBorder var2 = new EmptyBorder(0, var1, 0, var1);
      this.Padding = new Padding(0); /* setBorder */ //(new CompoundBorder(gA, var2));
      this.gH = 0;
      this.aI();
   }

   public Color aF() {
      return this.gE == null ? this.getForeground() : this.gE;
   }

   public void a(Color var1) {
      this.gE = var1;
   }

   public float aG() {
      return this.gF;
   }

   public void a(float var1) {
      this.gF = var1 > 1.0F ? 1.0F : (var1 < 0.0F ? -1.0F : var1);
   }

   public int aH() {
      return this.gG;
   }

   public void z(int var1) {
      this.gG = var1;
      this.aI();
   }

   public void aI() {
      Element var1 = this.gB.getDocument().getDefaultRootElement();
      int var2 = var1.getElementCount();
      int var3 = Math.Max(Convert.ToString(var2).length(), this.gG);
      if (this.gH != var3) {
         this.gH = var3;
         FontMetrics var4 = this.getFontMetrics(this.getFont());
         int var5 = var4.charWidth('0') * var3;
         Padding var6 = this.getInsets();
         int var7 = var6.left + var6.right + var5;
         Size var8 = this.PreferredSize;
         var8.Size = new Size(var7, 2146483647);
         this.Size = (var8);
         this.Size = new Size(var8);
      }

   }

   public void paintComponent(Graphics var1) {
      base.paintComponent(var1);
      FontMetrics var2 = this.gB.getFontMetrics(this.gB.getFont());
      Padding var3 = this.getInsets();
      int var4 = this.getSize().width - var3.left - var3.right;
      Rectangle var5 = var1.getClipBounds();
      int var6 = this.gB.viewToModel(new Point(0, var5.y));
      int var7 = this.gB.viewToModel(new Point(0, var5.y + var5.height));

      while(var6 <= var7) {
         try {
            if (this.A(var6)) {
               var1.setColor(this.aF());
            } else {
               var1.setColor(this.getForeground());
            }

            string var8 = this.B(var6);
            int var9 = var2.stringWidth(var8);
            int var10 = this.b(var4, var9) + var3.left;
            int var11 = this.a(var6, var2);
            var1.drawString(var8, var10, var11);
            var6 = Utilities.getRowEnd(this.gB, var6) + 1;
         } catch (Exception var12) {
            break;
         }
      }

   }

   public bool A(int var1) {
      int var2 = this.gB.getCaretPosition();
      Element var3 = this.gB.getDocument().getDefaultRootElement();
      return var3.getElementIndex(var1) == var3.getElementIndex(var2);
   }

   protected string B(int var1) {
      Element var2 = this.gB.getDocument().getDefaultRootElement();
      int var3 = var2.getElementIndex(var1);
      Element var4 = var2.getElement(var3);
      return var4.getStartOffset() == var1 ? Convert.ToString(var3 + 1) : "";
   }

   public int b(int var1, int var2) {
      return (int)((float)(var1 - var2) * this.gF);
   }

   public int a(int var1, FontMetrics var2) {
      Rectangle var3 = this.gB.modelToView(var1);
      int var4 = var2.Height;
      int var5 = var3.y + var3.height;
      int var6 = 0;
      if (var3.height == var4) {
         var6 = var2.getDescent();
      } else {
         if (this.gK == null) {
            this.gK = new Dictionary<object, object>();
         }

         Element var7 = this.gB.getDocument().getDefaultRootElement();
         int var8 = var7.getElementIndex(var1);
         Element var9 = var7.getElement(var8);

         for(int var10 = 0; var10 < var9.getElementCount(); ++var10) {
            Element var11 = var9.getElement(var10);
            AttributeSet var12 = var11.getAttributes();
            string var13 = (string)var12.getAttribute(StyleConstants.FontFamily);
            Integer var14 = (Integer)var12.getAttribute(StyleConstants.FontSize);
            string var15 = var13 + var14;
            FontMetrics var16 = (FontMetrics)this.gK[var15];
            if (var16 == null) {
               Font var17 = new Font(var13, 0, var14);
               var16 = this.gB.getFontMetrics(var17);
               this.gK.Put(var15, var16);
            }

            var6 = Math.Max(var6, var16.getDescent());
         }
      }

      return var5 - var6;
   }

   public void caretUpdate(CaretEvent var1) {
      int var2 = this.gB.getCaretPosition();
      Element var3 = this.gB.getDocument().getDefaultRootElement();
      int var4 = var3.getElementIndex(var2);
      if (this.gJ != var4) {
         this.Invalidate();
         this.gJ = var4;
      }

   }

   public void changedUpdate(EventArgs var1) {
      this.aJ();
   }

   public void insertUpdate(EventArgs var1) {
      this.aJ();
   }

   public void removeUpdate(EventArgs var1) {
      this.aJ();
   }

   public void aJ() {
      Control.invokeLater(new cX(this));
   }

   public void propertyChange(EventArgs var1) {
      if (var1.getNewValue() is Font) {
         if (this.gC) {
            Font var2 = (Font)var1.getNewValue();
            this.setFont(var2);
            this.gH = 0;
            this.aI();
         } else {
            this.Invalidate();
         }
      }

   }
   public static JTextComponent a(cW var0) {
      return var0.gB;
   }
   public static int b(cW var0) {
      return var0.gI;
   }
   public static void c(cW var0) {
      var0.aI();
   }
   public static void a(cW var0, int var1) {
      var0.gI = var1;
   }
}

}
