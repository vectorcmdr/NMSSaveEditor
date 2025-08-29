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
/*     */ public final class RowSpec
/*     */   extends FormSpec
/*     */ {
/*  72 */   public static final FormSpec.DefaultAlignment TOP = FormSpec.TOP_ALIGN;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  77 */   public static final FormSpec.DefaultAlignment CENTER = FormSpec.CENTER_ALIGN;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   public static final FormSpec.DefaultAlignment BOTTOM = FormSpec.BOTTOM_ALIGN;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   public static final FormSpec.DefaultAlignment FILL = FormSpec.FILL_ALIGN;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  92 */   public static final FormSpec.DefaultAlignment DEFAULT = CENTER;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 100 */   private static final Map CACHE = new HashMap();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RowSpec(FormSpec.DefaultAlignment defaultAlignment, Size size, double resizeWeight) {
/* 122 */     super(defaultAlignment, size, resizeWeight);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RowSpec(Size size) {
/* 133 */     super(DEFAULT, size, 0.0D);
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
/*     */   public RowSpec(String encodedDescription) {
/* 151 */     super(DEFAULT, encodedDescription);
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
/*     */   public static RowSpec createGap(ConstantSize gapHeight) {
/* 169 */     return new RowSpec(DEFAULT, gapHeight, 0.0D);
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
/*     */   public static RowSpec decode(String encodedRowSpec) {
/* 189 */     return decode(encodedRowSpec, LayoutMap.getRoot());
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
/*     */   public static RowSpec decode(String encodedRowSpec, LayoutMap layoutMap) {
/* 210 */     FormUtils.assertNotBlank(encodedRowSpec, "encoded row specification");
/* 211 */     FormUtils.assertNotNull(layoutMap, "LayoutMap");
/* 212 */     String trimmed = encodedRowSpec.trim();
/* 213 */     String lower = trimmed.toLowerCase(Locale.ENGLISH);
/* 214 */     return decodeExpanded(layoutMap.expand(lower, false));
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
/*     */   static RowSpec decodeExpanded(String expandedTrimmedLowerCaseSpec) {
/* 228 */     RowSpec spec = (RowSpec)CACHE.get(expandedTrimmedLowerCaseSpec);
/* 229 */     if (spec == null) {
/* 230 */       spec = new RowSpec(expandedTrimmedLowerCaseSpec);
/* 231 */       CACHE.put(expandedTrimmedLowerCaseSpec, spec);
/*     */     } 
/* 233 */     return spec;
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
/*     */   public static RowSpec[] decodeSpecs(String encodedRowSpecs) {
/* 250 */     return decodeSpecs(encodedRowSpecs, LayoutMap.getRoot());
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
/*     */   public static RowSpec[] decodeSpecs(String encodedRowSpecs, LayoutMap layoutMap) {
/* 270 */     return FormSpecParser.parseRowSpecs(encodedRowSpecs, layoutMap);
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
/*     */   protected boolean isHorizontal() {
/* 283 */     return false;
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\jgoodies\forms\layout\RowSpec.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */