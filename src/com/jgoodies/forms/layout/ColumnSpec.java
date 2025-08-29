/*     */ package com.jgoodies.forms.layout;
/*     */ 
/*     */ import com.jgoodies.forms.util.FormUtils;
/*     */ import java.util.HashMap;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ColumnSpec
/*     */   extends FormSpec
/*     */ {
/*  73 */   public static final FormSpec.DefaultAlignment LEFT = FormSpec.LEFT_ALIGN;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  78 */   public static final FormSpec.DefaultAlignment CENTER = FormSpec.CENTER_ALIGN;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   public static final FormSpec.DefaultAlignment MIDDLE = CENTER;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  88 */   public static final FormSpec.DefaultAlignment RIGHT = FormSpec.RIGHT_ALIGN;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  93 */   public static final FormSpec.DefaultAlignment FILL = FormSpec.FILL_ALIGN;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  98 */   public static final FormSpec.DefaultAlignment DEFAULT = FILL;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 106 */   private static final Map CACHE = new HashMap();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColumnSpec(FormSpec.DefaultAlignment defaultAlignment, Size size, double resizeWeight) {
/* 130 */     super(defaultAlignment, size, resizeWeight);
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
/*     */   public ColumnSpec(Size size) {
/* 142 */     super(DEFAULT, size, 0.0D);
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
/*     */   public ColumnSpec(String encodedDescription) {
/* 161 */     super(DEFAULT, encodedDescription);
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
/*     */   public static ColumnSpec createGap(ConstantSize gapWidth) {
/* 179 */     return new ColumnSpec(DEFAULT, gapWidth, 0.0D);
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
/*     */   public static ColumnSpec decode(String encodedColumnSpec) {
/* 199 */     return decode(encodedColumnSpec, LayoutMap.getRoot());
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
/*     */   public static ColumnSpec decode(String encodedColumnSpec, LayoutMap layoutMap) {
/* 220 */     FormUtils.assertNotBlank(encodedColumnSpec, "encoded column specification");
/* 221 */     FormUtils.assertNotNull(layoutMap, "LayoutMap");
/* 222 */     String trimmed = encodedColumnSpec.trim();
/* 223 */     String lower = trimmed.toLowerCase(Locale.ENGLISH);
/* 224 */     return decodeExpanded(layoutMap.expand(lower, true));
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
/*     */   static ColumnSpec decodeExpanded(String expandedTrimmedLowerCaseSpec) {
/* 238 */     ColumnSpec spec = (ColumnSpec)CACHE.get(expandedTrimmedLowerCaseSpec);
/* 239 */     if (spec == null) {
/* 240 */       spec = new ColumnSpec(expandedTrimmedLowerCaseSpec);
/* 241 */       CACHE.put(expandedTrimmedLowerCaseSpec, spec);
/*     */     } 
/* 243 */     return spec;
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
/*     */   public static ColumnSpec[] decodeSpecs(String encodedColumnSpecs) {
/* 260 */     return decodeSpecs(encodedColumnSpecs, LayoutMap.getRoot());
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
/*     */   public static ColumnSpec[] decodeSpecs(String encodedColumnSpecs, LayoutMap layoutMap) {
/* 280 */     return FormSpecParser.parseColumnSpecs(encodedColumnSpecs, layoutMap);
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
/*     */   protected boolean isHorizontal() {
/* 294 */     return true;
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\jgoodies\forms\layout\ColumnSpec.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */