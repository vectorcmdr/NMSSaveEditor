/*     */ package com.jgoodies.forms.layout;
/*     */ 
/*     */ import com.jgoodies.forms.util.DefaultUnitConverter;
/*     */ import com.jgoodies.forms.util.UnitConverter;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Sizes
/*     */ {
/*  64 */   public static final ConstantSize ZERO = pixel(0);
/*     */   
/*  66 */   public static final ConstantSize DLUX1 = dluX(1);
/*  67 */   public static final ConstantSize DLUX2 = dluX(2);
/*  68 */   public static final ConstantSize DLUX3 = dluX(3);
/*  69 */   public static final ConstantSize DLUX4 = dluX(4);
/*  70 */   public static final ConstantSize DLUX5 = dluX(5);
/*  71 */   public static final ConstantSize DLUX6 = dluX(6);
/*  72 */   public static final ConstantSize DLUX7 = dluX(7);
/*  73 */   public static final ConstantSize DLUX8 = dluX(8);
/*  74 */   public static final ConstantSize DLUX9 = dluX(9);
/*  75 */   public static final ConstantSize DLUX11 = dluX(11);
/*  76 */   public static final ConstantSize DLUX14 = dluX(14);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   public static final ConstantSize DLUX21 = dluX(21);
/*     */   
/*  83 */   public static final ConstantSize DLUY1 = dluY(1);
/*  84 */   public static final ConstantSize DLUY2 = dluY(2);
/*  85 */   public static final ConstantSize DLUY3 = dluY(3);
/*  86 */   public static final ConstantSize DLUY4 = dluY(4);
/*  87 */   public static final ConstantSize DLUY5 = dluY(5);
/*  88 */   public static final ConstantSize DLUY6 = dluY(6);
/*  89 */   public static final ConstantSize DLUY7 = dluY(7);
/*  90 */   public static final ConstantSize DLUY8 = dluY(8);
/*  91 */   public static final ConstantSize DLUY9 = dluY(9);
/*  92 */   public static final ConstantSize DLUY11 = dluY(11);
/*  93 */   public static final ConstantSize DLUY14 = dluY(14);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  98 */   public static final ConstantSize DLUY21 = dluY(21);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 106 */   public static final ComponentSize MINIMUM = new ComponentSize("minimum");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 111 */   public static final ComponentSize PREFERRED = new ComponentSize("preferred");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 118 */   public static final ComponentSize DEFAULT = new ComponentSize("default");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 124 */   private static final ComponentSize[] VALUES = new ComponentSize[] { MINIMUM, PREFERRED, DEFAULT };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static UnitConverter unitConverter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 144 */   private static ConstantSize.Unit defaultUnit = ConstantSize.PIXEL;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ConstantSize constant(String encodedValueAndUnit, boolean horizontal) {
/* 166 */     String lowerCase = encodedValueAndUnit.toLowerCase(Locale.ENGLISH);
/* 167 */     String trimmed = lowerCase.trim();
/* 168 */     return ConstantSize.valueOf(trimmed, horizontal);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ConstantSize dluX(int value) {
/* 179 */     return ConstantSize.dluX(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ConstantSize dluY(int value) {
/* 190 */     return ConstantSize.dluY(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ConstantSize pixel(int value) {
/* 201 */     return new ConstantSize(value, ConstantSize.PIXEL);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Size bounded(Size basis, Size lowerBound, Size upperBound) {
/* 216 */     return new BoundedSize(basis, lowerBound, upperBound);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int inchAsPixel(double in, Component component) {
/* 230 */     return (in == 0.0D) ? 0 : getUnitConverter().inchAsPixel(in, component);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int millimeterAsPixel(double mm, Component component) {
/* 244 */     return (mm == 0.0D) ? 0 : getUnitConverter().millimeterAsPixel(mm, component);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int centimeterAsPixel(double cm, Component component) {
/* 258 */     return (cm == 0.0D) ? 0 : getUnitConverter().centimeterAsPixel(cm, component);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int pointAsPixel(int pt, Component component) {
/* 272 */     return (pt == 0) ? 0 : getUnitConverter().pointAsPixel(pt, component);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int dialogUnitXAsPixel(int dluX, Component component) {
/* 286 */     return (dluX == 0) ? 0 : getUnitConverter().dialogUnitXAsPixel(dluX, component);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int dialogUnitYAsPixel(int dluY, Component component) {
/* 300 */     return (dluY == 0) ? 0 : getUnitConverter().dialogUnitYAsPixel(dluY, component);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static UnitConverter getUnitConverter() {
/* 315 */     if (unitConverter == null) {
/* 316 */       unitConverter = (UnitConverter)DefaultUnitConverter.getInstance();
/*     */     }
/* 318 */     return unitConverter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setUnitConverter(UnitConverter newUnitConverter) {
/* 328 */     unitConverter = newUnitConverter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ConstantSize.Unit getDefaultUnit() {
/* 343 */     return defaultUnit;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setDefaultUnit(ConstantSize.Unit unit) {
/* 359 */     if (unit == ConstantSize.DLUX || unit == ConstantSize.DLUY) {
/* 360 */       throw new IllegalArgumentException("The unit must not be DLUX or DLUY. To use DLU as default unit, invoke this method with null.");
/*     */     }
/*     */ 
/*     */     
/* 364 */     defaultUnit = unit;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static final class ComponentSize
/*     */     implements Size, Serializable
/*     */   {
/*     */     private final transient String name;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private ComponentSize(String name) {
/* 469 */       this.ordinal = nextOrdinal++;
/*     */       this.name = name;
/*     */     } private Object readResolve() {
/* 472 */       return Sizes.VALUES[this.ordinal];
/*     */     }
/*     */     
/*     */     static ComponentSize valueOf(String str) {
/*     */       if (str.equals("m") || str.equals("min"))
/*     */         return Sizes.MINIMUM; 
/*     */       if (str.equals("p") || str.equals("pref"))
/*     */         return Sizes.PREFERRED; 
/*     */       if (str.equals("d") || str.equals("default"))
/*     */         return Sizes.DEFAULT; 
/*     */       return null;
/*     */     }
/*     */     
/*     */     public int maximumSize(Container container, List components, FormLayout.Measure minMeasure, FormLayout.Measure prefMeasure, FormLayout.Measure defaultMeasure) {
/*     */       FormLayout.Measure measure = (this == Sizes.MINIMUM) ? minMeasure : ((this == Sizes.PREFERRED) ? prefMeasure : defaultMeasure);
/*     */       int maximum = 0;
/*     */       for (Iterator i = components.iterator(); i.hasNext(); ) {
/*     */         Component c = i.next();
/*     */         maximum = Math.max(maximum, measure.sizeOf(c));
/*     */       } 
/*     */       return maximum;
/*     */     }
/*     */     
/*     */     public boolean compressible() {
/*     */       return (this == Sizes.DEFAULT);
/*     */     }
/*     */     
/*     */     public String toString() {
/*     */       return encode();
/*     */     }
/*     */     
/*     */     public String encode() {
/*     */       return this.name.substring(0, 1);
/*     */     }
/*     */     
/*     */     private static int nextOrdinal = 0;
/*     */     private final int ordinal;
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\jgoodies\forms\layout\Sizes.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */