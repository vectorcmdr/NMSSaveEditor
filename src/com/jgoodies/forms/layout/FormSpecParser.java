/*     */ package com.jgoodies.forms.layout;
/*     */ 
/*     */ import com.jgoodies.forms.util.FormUtils;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class FormSpecParser
/*     */ {
/*  55 */   private static final Pattern MULTIPLIER_PREFIX_PATTERN = Pattern.compile("\\d+\\s*\\*\\s*\\(");
/*     */ 
/*     */   
/*  58 */   private static final Pattern DIGIT_PATTERN = Pattern.compile("\\d+");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final String source;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final LayoutMap layoutMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private FormSpecParser(String source, String description, LayoutMap layoutMap, boolean horizontal) {
/*  89 */     FormUtils.assertNotNull(source, description);
/*  90 */     FormUtils.assertNotNull(layoutMap, "LayoutMap");
/*  91 */     this.layoutMap = layoutMap;
/*  92 */     this.source = this.layoutMap.expand(source, horizontal);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static ColumnSpec[] parseColumnSpecs(String encodedColumnSpecs, LayoutMap layoutMap) {
/* 101 */     FormSpecParser parser = new FormSpecParser(encodedColumnSpecs, "encoded column specifications", layoutMap, true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 106 */     return parser.parseColumnSpecs();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static RowSpec[] parseRowSpecs(String encodedRowSpecs, LayoutMap layoutMap) {
/* 113 */     FormSpecParser parser = new FormSpecParser(encodedRowSpecs, "encoded column specifications", layoutMap, false);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 118 */     return parser.parseRowSpecs();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ColumnSpec[] parseColumnSpecs() {
/* 125 */     List encodedColumnSpecs = split(this.source, 0);
/* 126 */     int columnCount = encodedColumnSpecs.size();
/* 127 */     ColumnSpec[] columnSpecs = new ColumnSpec[columnCount];
/* 128 */     for (int i = 0; i < columnCount; i++) {
/* 129 */       String encodedSpec = encodedColumnSpecs.get(i);
/* 130 */       columnSpecs[i] = ColumnSpec.decodeExpanded(encodedSpec);
/*     */     } 
/* 132 */     return columnSpecs;
/*     */   }
/*     */ 
/*     */   
/*     */   private RowSpec[] parseRowSpecs() {
/* 137 */     List encodedRowSpecs = split(this.source, 0);
/* 138 */     int rowCount = encodedRowSpecs.size();
/* 139 */     RowSpec[] rowSpecs = new RowSpec[rowCount];
/* 140 */     for (int i = 0; i < rowCount; i++) {
/* 141 */       String encodedSpec = encodedRowSpecs.get(i);
/* 142 */       rowSpecs[i] = RowSpec.decodeExpanded(encodedSpec);
/*     */     } 
/* 144 */     return rowSpecs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List split(String expression, int offset) {
/* 151 */     List encodedSpecs = new ArrayList();
/* 152 */     int parenthesisLevel = 0;
/* 153 */     int bracketLevel = 0;
/* 154 */     int quoteLevel = 0;
/* 155 */     int length = expression.length();
/* 156 */     int specStart = 0;
/*     */     
/* 158 */     boolean lead = true;
/* 159 */     for (int i = 0; i < length; i++) {
/* 160 */       char c = expression.charAt(i);
/* 161 */       if (lead && Character.isWhitespace(c)) {
/* 162 */         specStart++;
/*     */       } else {
/*     */         
/* 165 */         lead = false;
/* 166 */         if (c == ',' && parenthesisLevel == 0 && bracketLevel == 0 && quoteLevel == 0) {
/* 167 */           String token = expression.substring(specStart, i);
/* 168 */           addSpec(encodedSpecs, token, offset + specStart);
/* 169 */           specStart = i + 1;
/* 170 */           lead = true;
/* 171 */         } else if (c == '(') {
/* 172 */           if (bracketLevel > 0) {
/* 173 */             fail(offset + i, "illegal '(' in [...]");
/*     */           }
/* 175 */           parenthesisLevel++;
/* 176 */         } else if (c == ')') {
/* 177 */           if (bracketLevel > 0) {
/* 178 */             fail(offset + i, "illegal ')' in [...]");
/*     */           }
/* 180 */           parenthesisLevel--;
/* 181 */           if (parenthesisLevel < 0) {
/* 182 */             fail(offset + i, "missing '('");
/*     */           }
/* 184 */         } else if (c == '[') {
/* 185 */           if (bracketLevel > 0) {
/* 186 */             fail(offset + i, "too many '['");
/*     */           }
/* 188 */           bracketLevel++;
/* 189 */         } else if (c == ']') {
/* 190 */           bracketLevel--;
/* 191 */           if (bracketLevel < 0) {
/* 192 */             fail(offset + i, "missing '['");
/*     */           }
/* 194 */         } else if (c == '\'') {
/* 195 */           if (quoteLevel == 0) {
/* 196 */             quoteLevel++;
/* 197 */           } else if (quoteLevel == 1) {
/* 198 */             quoteLevel--;
/*     */           } 
/*     */         } 
/*     */       } 
/* 202 */     }  if (parenthesisLevel > 0) {
/* 203 */       fail(offset + length, "missing ')'");
/*     */     }
/* 205 */     if (bracketLevel > 0) {
/* 206 */       fail(offset + length, "missing ']");
/*     */     }
/* 208 */     if (specStart < length) {
/* 209 */       String token = expression.substring(specStart);
/* 210 */       addSpec(encodedSpecs, token, offset + specStart);
/*     */     } 
/* 212 */     return encodedSpecs;
/*     */   }
/*     */ 
/*     */   
/*     */   private void addSpec(List encodedSpecs, String expression, int offset) {
/* 217 */     String trimmedExpression = expression.trim();
/* 218 */     Multiplier multiplier = multiplier(trimmedExpression, offset);
/* 219 */     if (multiplier == null) {
/* 220 */       encodedSpecs.add(trimmedExpression);
/*     */       return;
/*     */     } 
/* 223 */     List subTokenList = split(multiplier.expression, offset + multiplier.offset);
/* 224 */     for (int i = 0; i < multiplier.multiplier; i++) {
/* 225 */       encodedSpecs.addAll(subTokenList);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private Multiplier multiplier(String expression, int offset) {
/* 231 */     Matcher matcher = MULTIPLIER_PREFIX_PATTERN.matcher(expression);
/* 232 */     if (!matcher.find()) {
/* 233 */       return null;
/*     */     }
/* 235 */     if (matcher.start() > 0) {
/* 236 */       fail(offset + matcher.start(), "illegal multiplier position");
/*     */     }
/* 238 */     Matcher digitMatcher = DIGIT_PATTERN.matcher(expression);
/* 239 */     if (!digitMatcher.find()) {
/* 240 */       return null;
/*     */     }
/* 242 */     String digitStr = expression.substring(0, digitMatcher.end());
/* 243 */     int number = 0;
/*     */     try {
/* 245 */       number = Integer.parseInt(digitStr);
/* 246 */     } catch (NumberFormatException e) {
/* 247 */       fail(offset, e);
/*     */     } 
/* 249 */     if (number <= 0) {
/* 250 */       fail(offset, "illegal 0 multiplier");
/*     */     }
/* 252 */     String subexpression = expression.substring(matcher.end(), expression.length() - 1);
/* 253 */     return new Multiplier(number, subexpression, matcher.end());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void fail(String source, int index, String description) {
/* 260 */     throw new FormLayoutParseException(message(source, index, description));
/*     */   }
/*     */ 
/*     */   
/*     */   private void fail(int index, String description) {
/* 265 */     throw new FormLayoutParseException(message(this.source, index, description));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void fail(int index, NumberFormatException cause) {
/* 271 */     throw new FormLayoutParseException(message(this.source, index, "Invalid multiplier"), cause);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String message(String source, int index, String description) {
/* 278 */     StringBuffer buffer = new StringBuffer(10);
/* 279 */     buffer.append('\n');
/* 280 */     buffer.append(source);
/* 281 */     buffer.append('\n');
/* 282 */     for (int i = 0; i < index; i++) {
/* 283 */       buffer.append(' ');
/*     */     }
/* 285 */     buffer.append('^');
/* 286 */     buffer.append(description);
/* 287 */     String message = buffer.toString();
/* 288 */     throw new FormLayoutParseException(message);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final class FormLayoutParseException
/*     */     extends RuntimeException
/*     */   {
/*     */     FormLayoutParseException(String message) {
/* 298 */       super(message);
/*     */     }
/*     */     
/*     */     FormLayoutParseException(String message, Throwable cause) {
/* 302 */       super(message, cause);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static final class Multiplier
/*     */   {
/*     */     final int multiplier;
/*     */ 
/*     */     
/*     */     final String expression;
/*     */ 
/*     */     
/*     */     final int offset;
/*     */ 
/*     */ 
/*     */     
/*     */     Multiplier(int multiplier, String expression, int offset) {
/* 321 */       this.multiplier = multiplier;
/* 322 */       this.expression = expression;
/* 323 */       this.offset = offset;
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\jgoodies\forms\layout\FormSpecParser.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */