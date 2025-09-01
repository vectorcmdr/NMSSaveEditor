package com.jgoodies.forms.layout;

import com.jgoodies.forms.util.FormUtils;
import java.awt.Container;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public abstract class FormSpec implements Serializable {
   static final FormSpec.DefaultAlignment LEFT_ALIGN = new FormSpec.DefaultAlignment("left");
   static final FormSpec.DefaultAlignment RIGHT_ALIGN = new FormSpec.DefaultAlignment("right");
   static final FormSpec.DefaultAlignment TOP_ALIGN = new FormSpec.DefaultAlignment("top");
   static final FormSpec.DefaultAlignment BOTTOM_ALIGN = new FormSpec.DefaultAlignment("bottom");
   static final FormSpec.DefaultAlignment CENTER_ALIGN = new FormSpec.DefaultAlignment("center");
   static final FormSpec.DefaultAlignment FILL_ALIGN = new FormSpec.DefaultAlignment("fill");
   private static final FormSpec.DefaultAlignment[] VALUES;
   public static final double NO_GROW = 0.0D;
   public static final double DEFAULT_GROW = 1.0D;
   private static final Pattern TOKEN_SEPARATOR_PATTERN;
   private static final Pattern BOUNDS_SEPARATOR_PATTERN;
   private FormSpec.DefaultAlignment defaultAlignment;
   private Size size;
   private double resizeWeight;

   protected FormSpec(FormSpec.DefaultAlignment defaultAlignment, Size size, double resizeWeight) {
      if (size == null) {
         throw new NullPointerException("The size must not be null.");
      } else {
         this.defaultAlignment = defaultAlignment;
         this.size = size;
         this.resizeWeight = resizeWeight;
         if (resizeWeight < 0.0D) {
            throw new IllegalArgumentException("The resize weight must be non-negative.");
         }
      }
   }

   protected FormSpec(FormSpec.DefaultAlignment defaultAlignment, String encodedDescription) {
      this(defaultAlignment, Sizes.DEFAULT, 0.0D);
      this.parseAndInitValues(encodedDescription.toLowerCase(Locale.ENGLISH));
   }

   public final FormSpec.DefaultAlignment getDefaultAlignment() {
      return this.defaultAlignment;
   }

   public final Size getSize() {
      return this.size;
   }

   public final double getResizeWeight() {
      return this.resizeWeight;
   }

   final boolean canGrow() {
      return this.getResizeWeight() != 0.0D;
   }

   abstract boolean isHorizontal();

   void setDefaultAlignment(FormSpec.DefaultAlignment defaultAlignment) {
      this.defaultAlignment = defaultAlignment;
   }

   void setSize(Size size) {
      this.size = size;
   }

   void setResizeWeight(double resizeWeight) {
      this.resizeWeight = resizeWeight;
   }

   private void parseAndInitValues(String encodedDescription) {
      FormUtils.assertNotBlank(encodedDescription, "encoded form specification");
      String[] token = TOKEN_SEPARATOR_PATTERN.split(encodedDescription);
      if (token.length == 0) {
         throw new IllegalArgumentException("The form spec must not be empty.");
      } else {
         int nextIndex = 0;
         int nextIndex = nextIndex + 1;
         String next = token[nextIndex];
         FormSpec.DefaultAlignment alignment = FormSpec.DefaultAlignment.valueOf(next, this.isHorizontal());
         if (alignment != null) {
            this.setDefaultAlignment(alignment);
            if (token.length == 1) {
               throw new IllegalArgumentException("The form spec must provide a size.");
            }

            next = token[nextIndex++];
         }

         this.setSize(this.parseSize(next));
         if (nextIndex < token.length) {
            this.setResizeWeight(parseResizeWeight(token[nextIndex]));
         }

      }
   }

   private Size parseSize(String token) {
      if (token.startsWith("[") && token.endsWith("]")) {
         return this.parseBoundedSize(token);
      } else if (token.startsWith("max(") && token.endsWith(")")) {
         return this.parseOldBoundedSize(token, false);
      } else {
         return token.startsWith("min(") && token.endsWith(")") ? this.parseOldBoundedSize(token, true) : this.parseAtomicSize(token);
      }
   }

   private Size parseBoundedSize(String token) {
      String content = token.substring(1, token.length() - 1);
      String[] subtoken = BOUNDS_SEPARATOR_PATTERN.split(content);
      Size basis = null;
      Size lower = null;
      Size upper = null;
      if (subtoken.length == 2) {
         Size size1 = this.parseAtomicSize(subtoken[0]);
         Size size2 = this.parseAtomicSize(subtoken[1]);
         if (isConstant(size1)) {
            if (isConstant(size2)) {
               lower = size1;
               basis = size2;
               upper = size2;
            } else {
               lower = size1;
               basis = size2;
            }
         } else {
            basis = size1;
            upper = size2;
         }
      } else if (subtoken.length == 3) {
         lower = this.parseAtomicSize(subtoken[0]);
         basis = this.parseAtomicSize(subtoken[1]);
         upper = this.parseAtomicSize(subtoken[2]);
      }

      if (lower != null && !isConstant(lower) || upper != null && !isConstant(upper)) {
         throw new IllegalArgumentException("Illegal bounded size '" + token + "'. Must be one of:" + "\n[<constant size>,<logical size>]                 // lower bound" + "\n[<logical size>,<constant size>]                 // upper bound" + "\n[<constant size>,<logical size>,<constant size>] // lower and upper bound." + "\nExamples:" + "\n[50dlu,pref]                                     // lower bound" + "\n[pref,200dlu]                                    // upper bound" + "\n[50dlu,pref,200dlu]                              // lower and upper bound.");
      } else {
         return new BoundedSize(basis, lower, upper);
      }
   }

   private Size parseOldBoundedSize(String token, boolean setMax) {
      int semicolonIndex = token.indexOf(59);
      String sizeToken1 = token.substring(4, semicolonIndex);
      String sizeToken2 = token.substring(semicolonIndex + 1, token.length() - 1);
      Size size1 = this.parseAtomicSize(sizeToken1);
      Size size2 = this.parseAtomicSize(sizeToken2);
      if (isConstant(size1)) {
         if (size2 instanceof Sizes.ComponentSize) {
            return new BoundedSize(size2, setMax ? null : size1, setMax ? size1 : null);
         } else {
            throw new IllegalArgumentException("Bounded sizes must not be both constants.");
         }
      } else if (isConstant(size2)) {
         return new BoundedSize(size1, setMax ? null : size2, setMax ? size2 : null);
      } else {
         throw new IllegalArgumentException("Bounded sizes must not be both logical.");
      }
   }

   private Size parseAtomicSize(String token) {
      String trimmedToken = token.trim();
      if (trimmedToken.startsWith("'") && trimmedToken.endsWith("'")) {
         int length = trimmedToken.length();
         if (length < 2) {
            throw new IllegalArgumentException("Missing closing \"'\" for prototype.");
         } else {
            return new PrototypeSize(trimmedToken.substring(1, length - 1));
         }
      } else {
         Sizes.ComponentSize componentSize = Sizes.ComponentSize.valueOf(trimmedToken);
         return (Size)(componentSize != null ? componentSize : ConstantSize.valueOf(trimmedToken, this.isHorizontal()));
      }
   }

   private static double parseResizeWeight(String token) {
      if (!token.equals("g") && !token.equals("grow")) {
         if (!token.equals("n") && !token.equals("nogrow") && !token.equals("none")) {
            if ((token.startsWith("grow(") || token.startsWith("g(")) && token.endsWith(")")) {
               int leftParen = token.indexOf(40);
               int rightParen = token.indexOf(41);
               String substring = token.substring(leftParen + 1, rightParen);
               return Double.parseDouble(substring);
            } else {
               throw new IllegalArgumentException("The resize argument '" + token + "' is invalid. " + " Must be one of: grow, g, none, n, grow(<double>), g(<double>)");
            }
         } else {
            return 0.0D;
         }
      } else {
         return 1.0D;
      }
   }

   private static boolean isConstant(Size aSize) {
      return aSize instanceof ConstantSize || aSize instanceof PrototypeSize;
   }

   public final String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append(this.defaultAlignment);
      buffer.append(":");
      buffer.append(this.size.toString());
      buffer.append(':');
      if (this.resizeWeight == 0.0D) {
         buffer.append("noGrow");
      } else if (this.resizeWeight == 1.0D) {
         buffer.append("grow");
      } else {
         buffer.append("grow(");
         buffer.append(this.resizeWeight);
         buffer.append(')');
      }

      return buffer.toString();
   }

   public final String toShortString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append(this.defaultAlignment.abbreviation());
      buffer.append(":");
      buffer.append(this.size.toString());
      buffer.append(':');
      if (this.resizeWeight == 0.0D) {
         buffer.append("n");
      } else if (this.resizeWeight == 1.0D) {
         buffer.append("g");
      } else {
         buffer.append("g(");
         buffer.append(this.resizeWeight);
         buffer.append(')');
      }

      return buffer.toString();
   }

   public final String encode() {
      StringBuffer buffer = new StringBuffer();
      FormSpec.DefaultAlignment alignmentDefault = this.isHorizontal() ? ColumnSpec.DEFAULT : RowSpec.DEFAULT;
      if (!alignmentDefault.equals(this.defaultAlignment)) {
         buffer.append(this.defaultAlignment.abbreviation());
         buffer.append(":");
      }

      buffer.append(this.size.encode());
      if (this.resizeWeight != 0.0D) {
         if (this.resizeWeight == 1.0D) {
            buffer.append(':');
            buffer.append("g");
         } else {
            buffer.append(':');
            buffer.append("g(");
            buffer.append(this.resizeWeight);
            buffer.append(')');
         }
      }

      return buffer.toString();
   }

   final int maximumSize(Container container, List components, FormLayout.Measure minMeasure, FormLayout.Measure prefMeasure, FormLayout.Measure defaultMeasure) {
      return this.size.maximumSize(container, components, minMeasure, prefMeasure, defaultMeasure);
   }

   static {
      VALUES = new FormSpec.DefaultAlignment[]{LEFT_ALIGN, RIGHT_ALIGN, TOP_ALIGN, BOTTOM_ALIGN, CENTER_ALIGN, FILL_ALIGN};
      TOKEN_SEPARATOR_PATTERN = Pattern.compile(":");
      BOUNDS_SEPARATOR_PATTERN = Pattern.compile("\\s*,\\s*");
   }

   public static final class DefaultAlignment implements Serializable {
      private final transient String name;
      private static int nextOrdinal = 0;
      private final int ordinal;

      private DefaultAlignment(String name) {
         this.ordinal = nextOrdinal++;
         this.name = name;
      }

      private static FormSpec.DefaultAlignment valueOf(String str, boolean isHorizontal) {
         if (!str.equals("f") && !str.equals("fill")) {
            if (!str.equals("c") && !str.equals("center")) {
               if (isHorizontal) {
                  if (!str.equals("r") && !str.equals("right")) {
                     return !str.equals("l") && !str.equals("left") ? null : FormSpec.LEFT_ALIGN;
                  } else {
                     return FormSpec.RIGHT_ALIGN;
                  }
               } else if (!str.equals("t") && !str.equals("top")) {
                  return !str.equals("b") && !str.equals("bottom") ? null : FormSpec.BOTTOM_ALIGN;
               } else {
                  return FormSpec.TOP_ALIGN;
               }
            } else {
               return FormSpec.CENTER_ALIGN;
            }
         } else {
            return FormSpec.FILL_ALIGN;
         }
      }

      public String toString() {
         return this.name;
      }

      public char abbreviation() {
         return this.name.charAt(0);
      }

      private Object readResolve() {
         return FormSpec.VALUES[this.ordinal];
      }

      // $FF: synthetic method
      DefaultAlignment(String x0, Object x1) {
         this(x0);
      }
   }
}
