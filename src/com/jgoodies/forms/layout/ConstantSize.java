package com.jgoodies.forms.layout;

import java.awt.Component;
import java.awt.Container;
import java.io.Serializable;
import java.util.List;

public final class ConstantSize implements Size, Serializable {
   public static final ConstantSize.Unit PIXEL = new ConstantSize.Unit("Pixel", "px", (String)null, true);
   public static final ConstantSize.Unit POINT = new ConstantSize.Unit("Point", "pt", (String)null, true);
   public static final ConstantSize.Unit DIALOG_UNITS_X = new ConstantSize.Unit("Dialog units X", "dluX", "dlu", true);
   public static final ConstantSize.Unit DIALOG_UNITS_Y = new ConstantSize.Unit("Dialog units Y", "dluY", "dlu", true);
   public static final ConstantSize.Unit MILLIMETER = new ConstantSize.Unit("Millimeter", "mm", (String)null, false);
   public static final ConstantSize.Unit CENTIMETER = new ConstantSize.Unit("Centimeter", "cm", (String)null, false);
   public static final ConstantSize.Unit INCH = new ConstantSize.Unit("Inch", "in", (String)null, false);
   public static final ConstantSize.Unit PX;
   public static final ConstantSize.Unit PT;
   public static final ConstantSize.Unit DLUX;
   public static final ConstantSize.Unit DLUY;
   public static final ConstantSize.Unit MM;
   public static final ConstantSize.Unit CM;
   public static final ConstantSize.Unit IN;
   private static final ConstantSize.Unit[] VALUES;
   private final double value;
   private final ConstantSize.Unit unit;

   public ConstantSize(int value, ConstantSize.Unit unit) {
      this.value = (double)value;
      this.unit = unit;
   }

   public ConstantSize(double value, ConstantSize.Unit unit) {
      this.value = value;
      this.unit = unit;
   }

   static ConstantSize valueOf(String encodedValueAndUnit, boolean horizontal) {
      String[] split = splitValueAndUnit(encodedValueAndUnit);
      String encodedValue = split[0];
      String encodedUnit = split[1];
      ConstantSize.Unit unit = ConstantSize.Unit.valueOf(encodedUnit, horizontal);
      double value = Double.parseDouble(encodedValue);
      if (unit.requiresIntegers && value != (double)((int)value)) {
         throw new IllegalArgumentException(unit.toString() + " value " + encodedValue + " must be an integer.");
      } else {
         return new ConstantSize(value, unit);
      }
   }

   static ConstantSize dluX(int value) {
      return new ConstantSize(value, DLUX);
   }

   static ConstantSize dluY(int value) {
      return new ConstantSize(value, DLUY);
   }

   public double getValue() {
      return this.value;
   }

   public ConstantSize.Unit getUnit() {
      return this.unit;
   }

   public int getPixelSize(Component component) {
      if (this.unit == PIXEL) {
         return this.intValue();
      } else if (this.unit == POINT) {
         return Sizes.pointAsPixel(this.intValue(), component);
      } else if (this.unit == INCH) {
         return Sizes.inchAsPixel(this.value, component);
      } else if (this.unit == MILLIMETER) {
         return Sizes.millimeterAsPixel(this.value, component);
      } else if (this.unit == CENTIMETER) {
         return Sizes.centimeterAsPixel(this.value, component);
      } else if (this.unit == DIALOG_UNITS_X) {
         return Sizes.dialogUnitXAsPixel(this.intValue(), component);
      } else if (this.unit == DIALOG_UNITS_Y) {
         return Sizes.dialogUnitYAsPixel(this.intValue(), component);
      } else {
         throw new IllegalStateException("Invalid unit " + this.unit);
      }
   }

   public int maximumSize(Container container, List components, FormLayout.Measure minMeasure, FormLayout.Measure prefMeasure, FormLayout.Measure defaultMeasure) {
      return this.getPixelSize(container);
   }

   public boolean compressible() {
      return false;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof ConstantSize)) {
         return false;
      } else {
         ConstantSize size = (ConstantSize)o;
         return this.value == size.value && this.unit == size.unit;
      }
   }

   public int hashCode() {
      return (new Double(this.value)).hashCode() + 37 * this.unit.hashCode();
   }

   public String toString() {
      return this.value == (double)this.intValue() ? Integer.toString(this.intValue()) + this.unit.abbreviation() : Double.toString(this.value) + this.unit.abbreviation();
   }

   public String encode() {
      return this.value == (double)this.intValue() ? Integer.toString(this.intValue()) + this.unit.encode() : Double.toString(this.value) + this.unit.encode();
   }

   private int intValue() {
      return (int)Math.round(this.value);
   }

   private static String[] splitValueAndUnit(String encodedValueAndUnit) {
      String[] result = new String[2];
      int len = encodedValueAndUnit.length();

      int firstLetterIndex;
      for(firstLetterIndex = len; firstLetterIndex > 0 && Character.isLetter(encodedValueAndUnit.charAt(firstLetterIndex - 1)); --firstLetterIndex) {
      }

      result[0] = encodedValueAndUnit.substring(0, firstLetterIndex);
      result[1] = encodedValueAndUnit.substring(firstLetterIndex);
      return result;
   }

   static {
      PX = PIXEL;
      PT = POINT;
      DLUX = DIALOG_UNITS_X;
      DLUY = DIALOG_UNITS_Y;
      MM = MILLIMETER;
      CM = CENTIMETER;
      IN = INCH;
      VALUES = new ConstantSize.Unit[]{PIXEL, POINT, DIALOG_UNITS_X, DIALOG_UNITS_Y, MILLIMETER, CENTIMETER, INCH};
   }

   public static final class Unit implements Serializable {
      private final transient String name;
      private final transient String abbreviation;
      private final transient String parseAbbreviation;
      final transient boolean requiresIntegers;
      private static int nextOrdinal = 0;
      private final int ordinal;

      private Unit(String name, String abbreviation, String parseAbbreviation, boolean requiresIntegers) {
         this.ordinal = nextOrdinal++;
         this.name = name;
         this.abbreviation = abbreviation;
         this.parseAbbreviation = parseAbbreviation;
         this.requiresIntegers = requiresIntegers;
      }

      static ConstantSize.Unit valueOf(String name, boolean horizontal) {
         if (name.length() == 0) {
            ConstantSize.Unit defaultUnit = Sizes.getDefaultUnit();
            if (defaultUnit != null) {
               return defaultUnit;
            } else {
               return horizontal ? ConstantSize.DIALOG_UNITS_X : ConstantSize.DIALOG_UNITS_Y;
            }
         } else if (name.equals("px")) {
            return ConstantSize.PIXEL;
         } else if (name.equals("dlu")) {
            return horizontal ? ConstantSize.DIALOG_UNITS_X : ConstantSize.DIALOG_UNITS_Y;
         } else if (name.equals("pt")) {
            return ConstantSize.POINT;
         } else if (name.equals("in")) {
            return ConstantSize.INCH;
         } else if (name.equals("mm")) {
            return ConstantSize.MILLIMETER;
         } else if (name.equals("cm")) {
            return ConstantSize.CENTIMETER;
         } else {
            throw new IllegalArgumentException("Invalid unit name '" + name + "'. Must be one of: " + "px, dlu, pt, mm, cm, in");
         }
      }

      public String toString() {
         return this.name;
      }

      public String encode() {
         return this.parseAbbreviation != null ? this.parseAbbreviation : this.abbreviation;
      }

      public String abbreviation() {
         return this.abbreviation;
      }

      private Object readResolve() {
         return ConstantSize.VALUES[this.ordinal];
      }

      // $FF: synthetic method
      Unit(String x0, String x1, String x2, boolean x3, Object x4) {
         this(x0, x1, x2, x3);
      }
   }
}
