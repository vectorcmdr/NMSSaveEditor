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
      // PORT_TODO: gA = new MatteBorder(0, 0, 0, 2, Color.Gray);
   }

   public cW(JTextComponent var1) {
      // PORT_TODO: // PORT_TODO: this(var1, 3);
   }

   public cW(JTextComponent var1, int var2) {
      this.gB = var1;
      this.setFont(var1.getFont());
      this.y(5);
      this.a(Color.Red);
      this.a(1.0F);
      this.z(var2);
      var1.getDocument().addDocumentListener(this);
      // PORT_TODO: var1.addCaretListener(this);
      // var1.addPropertyChangeListener - use property events instead
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
      int var2 = 0; // PORT_TODO: stub declaration
      // PORT_TODO: Element var1 = this.gB.getDocument().getDefaultRootElement();
      // PORT_TODO: int var2 = var1.getElementCount();
      int var3 = Math.Max(Convert.ToString(var2).length(), this.gG);
      if (this.gH != var3) {
         this.gH = var3;
         // PORT_TODO: FontMetrics var4 = Graphics.FromHwnd(IntPtr.Zero).MeasureString("M", this.getFont());
         // PORT_TODO: int var5 = var4.charWidth('0') * var3;
         Padding var6 = this.getInsets();
         // PORT_TODO: int var7 = var6.left + var6.right + var5;
         Size var8 = this.PreferredSize;
         // PORT_TODO: var8.Size = new Size(var7, 2146483647);
         this.Size = (var8);
         // PORT_TODO: this.Size = new Size(var8);
      }

   }

   public void paintComponent(Graphics var1) {
      // PORT_TODO: base.paintComponent(var1);
      // PORT_TODO: FontMetrics var2 = this.Graphics.FromHwnd(IntPtr.Zero).MeasureString("M", this.gB.getFont());
      Padding var3 = this.getInsets();
      // PORT_TODO: int var4 = this.getSize().width - var3.left - var3.right;
      Rectangle var5 = var1.getClipBounds();
      int var6 = this.gB.GetCharIndexFromPosition(new Point(0, var5.Y));
      int var7 = this.gB.GetCharIndexFromPosition(new Point(0, var5.Y + var5.Height));

      while(var6 <= var7) {
         try {
            if (this.A(var6)) {
               var1.setColor(this.aF());
            } else {
               var1.setColor(this.getForeground());
            }

            string var8 = this.B(var6);
            // PORT_TODO: int var9 = var2.stringWidth(var8);
            // PORT_TODO: int var10 = this.b(var4, var9) + var3.left;
            // PORT_TODO: int var11 = this.a(var6, var2);
            // PORT_TODO: var1.drawString(var8, var10, var11);
            var6 = 0; /* Utilities.getRowEnd(this.gB, var6) + 1 */;
         } catch (Exception var12) {
            break;
         }
      }

   }

   public bool A(int var1) {
      int var2 = this.gB.getCaretPosition();
      // PORT_TODO: Element var3 = this.gB.getDocument().getDefaultRootElement();
      // PORT_TODO: return var3.getElementIndex(var1) == var3.getElementIndex(var2);
   }

   public string B(int var1) {
      Element var3 = null; // PORT_TODO: stub declaration
      Element var4 = null; // PORT_TODO: stub declaration
      // PORT_TODO: Element var2 = this.gB.getDocument().getDefaultRootElement();
      // PORT_TODO: int var3 = var2.getElementIndex(var1);
      // PORT_TODO: Element var4 = var2.getElement(var3);
      // PORT_TODO: return var4.getStartOffset() == var1 ? Convert.ToString(var3 + 1) : "";
   }

   public int b(int var1, int var2) {
      return (int)((float)(var1 - var2) * this.gF);
   }

   public int a(int var1, FontMetrics var2) {
      Rectangle var3 = this.gB.modelToView(var1);
      // PORT_TODO: int var4 = var2.Height;
      // PORT_TODO: int var5 = var3.y + var3.height;
      int var6 = 0;
      if (true) { // PORT_TODO: original condition had errors
         var6 = var2.getDescent();
      } else {
         if (this.gK == null) {
            this.gK = new Dictionary<object, object>();
         }

         // PORT_TODO: Element var7 = this.gB.getDocument().getDefaultRootElement();
         // PORT_TODO: int var8 = var7.getElementIndex(var1);
         // PORT_TODO: Element var9 = var7.getElement(var8);

         // PORT_TODO: for(int var10 = 0; var10 < var9.getElementCount(); ++var10) {
      // PORT_TODO: string var15 = null; // PORT_TODO: stub declaration
      // PORT_TODO: Element var9 = null; // PORT_TODO: stub declaration
            // PORT_TODO: Element var11 = var9.getElement(var10);
            // PORT_TODO: AttributeSet var12 = var11.getAttributes();
            // PORT_TODO: string var13 = (string)var12.getAttribute(StyleConstants.FontFamily);
            // PORT_TODO: Integer var14 = (Integer)var12.getAttribute(StyleConstants.FontSize);
            // PORT_TODO: string var15 = var13 + var14;
            // PORT_TODO: FontMetrics var16 = (FontMetrics)this.gK[var15];
            // PORT_TODO: if (var16 == null) {
      // PORT_TODO: var15 = null; // PORT_TODO: stub declaration
               // PORT_TODO: Font var17 = new Font(var13, 0, var14);
               // PORT_TODO: var16 = this.Graphics.FromHwnd(IntPtr.Zero).MeasureString("M", var17);
               // PORT_TODO: this.gK.Put(var15, var16);
            // PORT_TODO: }

// PORT_TODO: 
            // PORT_TODO: var6 = Math.Max(var6, var16.getDescent());
         // PORT_TODO: }
      }

      // PORT_TODO: return var5 - var6;
   }

   public void caretUpdate(CaretEvent var1) {
      int var2 = this.gB.getCaretPosition();
      // PORT_TODO: Element var3 = this.gB.getDocument().getDefaultRootElement();
      // PORT_TODO: int var4 = var3.getElementIndex(var2);
      // PORT_TODO: if (this.gJ != var4) {
      // PORT_TODO: int var4 = 0; // PORT_TODO: stub declaration
         // PORT_TODO: this.Invalidate();
         // PORT_TODO: this.gJ = var4;
      // PORT_TODO: }

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
      // PORT_TODO: Control.invokeLater(new cX(this));
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
