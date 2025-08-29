/*     */ package com.jgoodies.forms.layout;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
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
/*     */ public final class ConstantSize
/*     */   implements Size, Serializable
/*     */ {
/*  78 */   public static final Unit PIXEL = new Unit("Pixel", "px", null, true);
/*  79 */   public static final Unit POINT = new Unit("Point", "pt", null, true);
/*  80 */   public static final Unit DIALOG_UNITS_X = new Unit("Dialog units X", "dluX", "dlu", true);
/*  81 */   public static final Unit DIALOG_UNITS_Y = new Unit("Dialog units Y", "dluY", "dlu", true);
/*  82 */   public static final Unit MILLIMETER = new Unit("Millimeter", "mm", null, false);
/*  83 */   public static final Unit CENTIMETER = new Unit("Centimeter", "cm", null, false);
/*  84 */   public static final Unit INCH = new Unit("Inch", "in", null, false);
/*     */   
/*  86 */   public static final Unit PX = PIXEL;
/*  87 */   public static final Unit PT = POINT;
/*  88 */   public static final Unit DLUX = DIALOG_UNITS_X;
/*  89 */   public static final Unit DLUY = DIALOG_UNITS_Y;
/*  90 */   public static final Unit MM = MILLIMETER;
/*  91 */   public static final Unit CM = CENTIMETER;
/*  92 */   public static final Unit IN = INCH;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  98 */   private static final Unit[] VALUES = new Unit[] { PIXEL, POINT, DIALOG_UNITS_X, DIALOG_UNITS_Y, MILLIMETER, CENTIMETER, INCH };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final double value;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final Unit unit;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConstantSize(int value, Unit unit) {
/* 119 */     this.value = value;
/* 120 */     this.unit = unit;
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
/*     */   public ConstantSize(double value, Unit unit) {
/* 133 */     this.value = value;
/* 134 */     this.unit = unit;
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
/*     */   static ConstantSize valueOf(String encodedValueAndUnit, boolean horizontal) {
/* 151 */     String[] split = splitValueAndUnit(encodedValueAndUnit);
/* 152 */     String encodedValue = split[0];
/* 153 */     String encodedUnit = split[1];
/* 154 */     Unit unit = Unit.valueOf(encodedUnit, horizontal);
/* 155 */     double value = Double.parseDouble(encodedValue);
/* 156 */     if (unit.requiresIntegers && 
/* 157 */       value != (int)value) {
/* 158 */       throw new IllegalArgumentException(unit.toString() + " value " + encodedValue + " must be an integer.");
/*     */     }
/*     */     
/* 161 */     return new ConstantSize(value, unit);
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
/*     */   static ConstantSize dluX(int value) {
/* 173 */     return new ConstantSize(value, DLUX);
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
/*     */   static ConstantSize dluY(int value) {
/* 185 */     return new ConstantSize(value, DLUY);
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
/*     */   public double getValue() {
/* 199 */     return this.value;
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
/*     */   public Unit getUnit() {
/* 211 */     return this.unit;
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
/*     */   public int getPixelSize(Component component) {
/* 224 */     if (this.unit == PIXEL)
/* 225 */       return intValue(); 
/* 226 */     if (this.unit == POINT)
/* 227 */       return Sizes.pointAsPixel(intValue(), component); 
/* 228 */     if (this.unit == INCH)
/* 229 */       return Sizes.inchAsPixel(this.value, component); 
/* 230 */     if (this.unit == MILLIMETER)
/* 231 */       return Sizes.millimeterAsPixel(this.value, component); 
/* 232 */     if (this.unit == CENTIMETER)
/* 233 */       return Sizes.centimeterAsPixel(this.value, component); 
/* 234 */     if (this.unit == DIALOG_UNITS_X)
/* 235 */       return Sizes.dialogUnitXAsPixel(intValue(), component); 
/* 236 */     if (this.unit == DIALOG_UNITS_Y) {
/* 237 */       return Sizes.dialogUnitYAsPixel(intValue(), component);
/*     */     }
/* 239 */     throw new IllegalStateException("Invalid unit " + this.unit);
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
/*     */   public int maximumSize(Container container, List components, FormLayout.Measure minMeasure, FormLayout.Measure prefMeasure, FormLayout.Measure defaultMeasure) {
/* 264 */     return getPixelSize(container);
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
/*     */   public boolean compressible() {
/* 280 */     return false;
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
/*     */   public boolean equals(Object o) {
/* 297 */     if (this == o)
/* 298 */       return true; 
/* 299 */     if (!(o instanceof ConstantSize))
/* 300 */       return false; 
/* 301 */     ConstantSize size = (ConstantSize)o;
/* 302 */     return (this.value == size.value && this.unit == size.unit);
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
/*     */   public int hashCode() {
/* 318 */     return (new Double(this.value)).hashCode() + 37 * this.unit.hashCode();
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
/*     */   public String toString() {
/* 332 */     return (this.value == intValue()) ? (Integer.toString(intValue()) + this.unit.abbreviation()) : (Double.toString(this.value) + this.unit.abbreviation());
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
/*     */   public String encode() {
/* 346 */     return (this.value == intValue()) ? (Integer.toString(intValue()) + this.unit.encode()) : (Double.toString(this.value) + this.unit.encode());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int intValue() {
/* 355 */     return (int)Math.round(this.value);
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
/*     */   private static String[] splitValueAndUnit(String encodedValueAndUnit) {
/* 368 */     String[] result = new String[2];
/* 369 */     int len = encodedValueAndUnit.length();
/* 370 */     int firstLetterIndex = len;
/*     */     
/* 372 */     while (firstLetterIndex > 0 && Character.isLetter(encodedValueAndUnit.charAt(firstLetterIndex - 1))) {
/* 373 */       firstLetterIndex--;
/*     */     }
/* 375 */     result[0] = encodedValueAndUnit.substring(0, firstLetterIndex);
/* 376 */     result[1] = encodedValueAndUnit.substring(firstLetterIndex);
/* 377 */     return result;
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
/*     */   public static final class Unit
/*     */     implements Serializable
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
/*     */     private final transient String abbreviation;
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
/*     */     private final transient String parseAbbreviation;
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
/*     */     final transient boolean requiresIntegers;
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
/*     */     private Unit(String name, String abbreviation, String parseAbbreviation, boolean requiresIntegers) {
/* 479 */       this.ordinal = nextOrdinal++; this.name = name;
/*     */       this.abbreviation = abbreviation;
/*     */       this.parseAbbreviation = parseAbbreviation;
/* 482 */       this.requiresIntegers = requiresIntegers; } private Object readResolve() { return ConstantSize.VALUES[this.ordinal]; }
/*     */ 
/*     */     
/*     */     static Unit valueOf(String name, boolean horizontal) {
/*     */       if (name.length() == 0) {
/*     */         Unit defaultUnit = Sizes.getDefaultUnit();
/*     */         if (defaultUnit != null)
/*     */           return defaultUnit; 
/*     */         return horizontal ? ConstantSize.DIALOG_UNITS_X : ConstantSize.DIALOG_UNITS_Y;
/*     */       } 
/*     */       if (name.equals("px"))
/*     */         return ConstantSize.PIXEL; 
/*     */       if (name.equals("dlu"))
/*     */         return horizontal ? ConstantSize.DIALOG_UNITS_X : ConstantSize.DIALOG_UNITS_Y; 
/*     */       if (name.equals("pt"))
/*     */         return ConstantSize.POINT; 
/*     */       if (name.equals("in"))
/*     */         return ConstantSize.INCH; 
/*     */       if (name.equals("mm"))
/*     */         return ConstantSize.MILLIMETER; 
/*     */       if (name.equals("cm"))
/*     */         return ConstantSize.CENTIMETER; 
/*     */       throw new IllegalArgumentException("Invalid unit name '" + name + "'. Must be one of: " + "px, dlu, pt, mm, cm, in");
/*     */     }
/*     */     
/*     */     public String toString() {
/*     */       return this.name;
/*     */     }
/*     */     
/*     */     public String encode() {
/*     */       return (this.parseAbbreviation != null) ? this.parseAbbreviation : this.abbreviation;
/*     */     }
/*     */     
/*     */     public String abbreviation() {
/*     */       return this.abbreviation;
/*     */     }
/*     */     
/*     */     private static int nextOrdinal = 0;
/*     */     private final int ordinal;
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\jgoodies\forms\layout\ConstantSize.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */