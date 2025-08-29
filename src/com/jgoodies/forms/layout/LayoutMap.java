/*     */ package com.jgoodies.forms.layout;
/*     */ 
/*     */ import com.jgoodies.forms.factories.FormFactory;
/*     */ import com.jgoodies.forms.util.LayoutStyle;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class LayoutMap
/*     */ {
/*     */   private static final char VARIABLE_PREFIX_CHAR = '$';
/* 131 */   private static final Map COLUMN_ALIASES = new HashMap();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 139 */   private static final Map ROW_ALIASES = new HashMap();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 146 */   private static LayoutMap root = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final LayoutMap parent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final Map columnMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final Map columnMapCache;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final Map rowMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final Map rowMapCache;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LayoutMap() {
/* 189 */     this(getRoot());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LayoutMap(LayoutMap parent) {
/* 199 */     this.parent = parent;
/* 200 */     this.columnMap = new HashMap();
/* 201 */     this.rowMap = new HashMap();
/* 202 */     this.columnMapCache = new HashMap();
/* 203 */     this.rowMapCache = new HashMap();
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
/*     */   public static LayoutMap getRoot() {
/* 216 */     if (root == null) {
/* 217 */       root = createRoot();
/*     */     }
/* 219 */     return root;
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
/*     */   public boolean columnContainsKey(String key) {
/* 238 */     String resolvedKey = resolveColumnKey(key);
/* 239 */     return (this.columnMap.containsKey(resolvedKey) || (this.parent != null && this.parent.columnContainsKey(resolvedKey)));
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
/*     */   public String columnGet(String key) {
/* 259 */     String resolvedKey = resolveColumnKey(key);
/* 260 */     String cachedValue = (String)this.columnMapCache.get(resolvedKey);
/* 261 */     if (cachedValue != null) {
/* 262 */       return cachedValue;
/*     */     }
/* 264 */     String value = (String)this.columnMap.get(resolvedKey);
/* 265 */     if (value == null && this.parent != null) {
/* 266 */       value = this.parent.columnGet(resolvedKey);
/*     */     }
/* 268 */     if (value == null) {
/* 269 */       return null;
/*     */     }
/* 271 */     String expandedString = expand(value, true);
/* 272 */     this.columnMapCache.put(resolvedKey, expandedString);
/* 273 */     return expandedString;
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
/*     */   public String columnPut(String key, String value) {
/* 298 */     String resolvedKey = resolveColumnKey(key);
/* 299 */     if (value == null) {
/* 300 */       throw new NullPointerException("The column expression value must not be null.");
/*     */     }
/* 302 */     this.columnMapCache.clear();
/* 303 */     return this.columnMap.put(resolvedKey, value.toLowerCase(Locale.ENGLISH));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String columnPut(String key, ColumnSpec value) {
/* 310 */     return columnPut(key, value.encode());
/*     */   }
/*     */ 
/*     */   
/*     */   public String columnPut(String key, Size value) {
/* 315 */     return columnPut(key, value.encode());
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
/*     */   public String columnRemove(String key) {
/* 337 */     String resolvedKey = resolveColumnKey(key);
/* 338 */     this.columnMapCache.clear();
/* 339 */     return (String)this.columnMap.remove(resolvedKey);
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
/*     */   public boolean rowContainsKey(String key) {
/* 358 */     String resolvedKey = resolveRowKey(key);
/* 359 */     return (this.rowMap.containsKey(resolvedKey) || (this.parent != null && this.parent.rowContainsKey(resolvedKey)));
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
/*     */   public String rowGet(String key) {
/* 379 */     String resolvedKey = resolveRowKey(key);
/* 380 */     String cachedValue = (String)this.rowMapCache.get(resolvedKey);
/* 381 */     if (cachedValue != null) {
/* 382 */       return cachedValue;
/*     */     }
/* 384 */     String value = (String)this.rowMap.get(resolvedKey);
/* 385 */     if (value == null && this.parent != null) {
/* 386 */       value = this.parent.rowGet(resolvedKey);
/*     */     }
/* 388 */     if (value == null) {
/* 389 */       return null;
/*     */     }
/* 391 */     String expandedString = expand(value, false);
/* 392 */     this.rowMapCache.put(resolvedKey, expandedString);
/* 393 */     return expandedString;
/*     */   }
/*     */ 
/*     */   
/*     */   public String rowPut(String key, String value) {
/* 398 */     String resolvedKey = resolveRowKey(key);
/* 399 */     if (value == null) {
/* 400 */       throw new NullPointerException("The row expression value must not be null.");
/*     */     }
/* 402 */     this.rowMapCache.clear();
/* 403 */     return this.rowMap.put(resolvedKey, value.toLowerCase(Locale.ENGLISH));
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
/*     */   public String rowPut(String key, RowSpec value) {
/* 429 */     return rowPut(key, value.encode());
/*     */   }
/*     */ 
/*     */   
/*     */   public String rowPut(String key, Size value) {
/* 434 */     return rowPut(key, value.encode());
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
/*     */   public String rowRemove(String key) {
/* 456 */     String resolvedKey = resolveRowKey(key);
/* 457 */     this.rowMapCache.clear();
/* 458 */     return (String)this.rowMap.remove(resolvedKey);
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
/*     */   public String toString() {
/* 471 */     StringBuffer buffer = new StringBuffer(super.toString());
/* 472 */     buffer.append("\n  Column associations:");
/* 473 */     for (Iterator iterator1 = this.columnMap.entrySet().iterator(); iterator1.hasNext(); ) {
/* 474 */       Map.Entry name = iterator1.next();
/* 475 */       buffer.append("\n    ");
/* 476 */       buffer.append(name.getKey());
/* 477 */       buffer.append("->");
/* 478 */       buffer.append(name.getValue());
/*     */     } 
/* 480 */     buffer.append("\n  Row associations:");
/* 481 */     for (Iterator iterator = this.rowMap.entrySet().iterator(); iterator.hasNext(); ) {
/* 482 */       Map.Entry name = iterator.next();
/* 483 */       buffer.append("\n    ");
/* 484 */       buffer.append(name.getKey());
/* 485 */       buffer.append("->");
/* 486 */       buffer.append(name.getValue());
/*     */     } 
/* 488 */     return buffer.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   String expand(String expression, boolean horizontal) {
/* 495 */     int cursor = 0;
/* 496 */     int start = expression.indexOf('$', cursor);
/* 497 */     if (start == -1) {
/* 498 */       return expression;
/*     */     }
/* 500 */     StringBuffer buffer = new StringBuffer();
/*     */     while (true) {
/* 502 */       buffer.append(expression.substring(cursor, start));
/* 503 */       String variableName = nextVariableName(expression, start);
/* 504 */       buffer.append(expansion(variableName, horizontal));
/* 505 */       cursor = start + variableName.length() + 1;
/* 506 */       start = expression.indexOf('$', cursor);
/* 507 */       if (start == -1) {
/* 508 */         buffer.append(expression.substring(cursor));
/* 509 */         return buffer.toString();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   private String nextVariableName(String expression, int start) {
/* 514 */     int length = expression.length();
/* 515 */     if (length <= start) {
/* 516 */       FormSpecParser.fail(expression, start, "Missing variable name after variable char '$'.");
/*     */     }
/* 518 */     if (expression.charAt(start + 1) == '{') {
/* 519 */       int i = expression.indexOf('}', start + 1);
/* 520 */       if (i == -1) {
/* 521 */         FormSpecParser.fail(expression, start, "Missing closing brace '}' for variable.");
/*     */       }
/* 523 */       return expression.substring(start + 1, i + 1);
/*     */     } 
/* 525 */     int end = start + 1;
/*     */     
/* 527 */     while (end < length && Character.isUnicodeIdentifierPart(expression.charAt(end))) {
/* 528 */       end++;
/*     */     }
/* 530 */     return expression.substring(start + 1, end);
/*     */   }
/*     */ 
/*     */   
/*     */   private String expansion(String variableName, boolean horizontal) {
/* 535 */     String key = stripBraces(variableName);
/* 536 */     String expansion = horizontal ? columnGet(key) : rowGet(key);
/* 537 */     if (expansion == null) {
/* 538 */       String orientation = horizontal ? "column" : "row";
/* 539 */       throw new IllegalArgumentException("Unknown " + orientation + " layout variable \"" + key + "\"");
/*     */     } 
/* 541 */     return expansion;
/*     */   }
/*     */ 
/*     */   
/*     */   private static String stripBraces(String variableName) {
/* 546 */     return (variableName.charAt(0) == '{') ? variableName.substring(1, variableName.length() - 1) : variableName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String resolveColumnKey(String key) {
/* 555 */     if (key == null) {
/* 556 */       throw new NullPointerException("The key must not be null.");
/*     */     }
/* 558 */     String lowercaseKey = key.toLowerCase(Locale.ENGLISH);
/* 559 */     String defaultKey = (String)COLUMN_ALIASES.get(lowercaseKey);
/* 560 */     return (defaultKey == null) ? lowercaseKey : defaultKey;
/*     */   }
/*     */ 
/*     */   
/*     */   private String resolveRowKey(String key) {
/* 565 */     if (key == null) {
/* 566 */       throw new NullPointerException("The key must not be null.");
/*     */     }
/* 568 */     String lowercaseKey = key.toLowerCase(Locale.ENGLISH);
/* 569 */     String defaultKey = (String)ROW_ALIASES.get(lowercaseKey);
/* 570 */     return (defaultKey == null) ? lowercaseKey : defaultKey;
/*     */   }
/*     */ 
/*     */   
/*     */   private static LayoutMap createRoot() {
/* 575 */     LayoutMap map = new LayoutMap(null);
/*     */ 
/*     */     
/* 578 */     map.columnPut("label-component-gap", new String[] { "lcg", "lcgap" }, FormFactory.LABEL_COMPONENT_GAP_COLSPEC);
/*     */ 
/*     */ 
/*     */     
/* 582 */     map.columnPut("related-gap", new String[] { "rg", "rgap" }, FormFactory.RELATED_GAP_COLSPEC);
/*     */ 
/*     */ 
/*     */     
/* 586 */     map.columnPut("unrelated-gap", new String[] { "ug", "ugap" }, FormFactory.UNRELATED_GAP_COLSPEC);
/*     */ 
/*     */ 
/*     */     
/* 590 */     map.columnPut("button", new String[] { "b" }, FormFactory.BUTTON_COLSPEC);
/*     */ 
/*     */ 
/*     */     
/* 594 */     map.columnPut("growing-button", new String[] { "gb" }, FormFactory.GROWING_BUTTON_COLSPEC);
/*     */ 
/*     */ 
/*     */     
/* 598 */     map.columnPut("dialog-margin", new String[] { "dm", "dmargin" }, ColumnSpec.createGap(LayoutStyle.getCurrent().getDialogMarginX()));
/*     */ 
/*     */ 
/*     */     
/* 602 */     map.columnPut("tabbed-dialog-margin", new String[] { "tdm", "tdmargin" }, ColumnSpec.createGap(LayoutStyle.getCurrent().getTabbedDialogMarginX()));
/*     */ 
/*     */ 
/*     */     
/* 606 */     map.columnPut("glue", FormFactory.GLUE_COLSPEC.toShortString());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 611 */     map.rowPut("related-gap", new String[] { "rg", "rgap" }, FormFactory.RELATED_GAP_ROWSPEC);
/*     */ 
/*     */ 
/*     */     
/* 615 */     map.rowPut("unrelated-gap", new String[] { "ug", "ugap" }, FormFactory.UNRELATED_GAP_ROWSPEC);
/*     */ 
/*     */ 
/*     */     
/* 619 */     map.rowPut("narrow-line-gap", new String[] { "nlg", "nlgap" }, FormFactory.NARROW_LINE_GAP_ROWSPEC);
/*     */ 
/*     */ 
/*     */     
/* 623 */     map.rowPut("line-gap", new String[] { "lg", "lgap" }, FormFactory.LINE_GAP_ROWSPEC);
/*     */ 
/*     */ 
/*     */     
/* 627 */     map.rowPut("paragraph-gap", new String[] { "pg", "pgap" }, FormFactory.PARAGRAPH_GAP_ROWSPEC);
/*     */ 
/*     */ 
/*     */     
/* 631 */     map.rowPut("dialog-margin", new String[] { "dm", "dmargin" }, RowSpec.createGap(LayoutStyle.getCurrent().getDialogMarginY()));
/*     */ 
/*     */ 
/*     */     
/* 635 */     map.rowPut("tabbed-dialog-margin", new String[] { "tdm", "tdmargin" }, RowSpec.createGap(LayoutStyle.getCurrent().getTabbedDialogMarginY()));
/*     */ 
/*     */ 
/*     */     
/* 639 */     map.rowPut("button", new String[] { "b" }, FormFactory.BUTTON_ROWSPEC);
/*     */ 
/*     */ 
/*     */     
/* 643 */     map.rowPut("glue", FormFactory.GLUE_ROWSPEC);
/*     */ 
/*     */ 
/*     */     
/* 647 */     return map;
/*     */   }
/*     */ 
/*     */   
/*     */   private void columnPut(String key, String[] aliases, ColumnSpec value) {
/* 652 */     ensureLowerCase(key);
/* 653 */     columnPut(key, value);
/* 654 */     for (int i = 0; i < aliases.length; i++) {
/* 655 */       ensureLowerCase(aliases[i]);
/* 656 */       COLUMN_ALIASES.put(aliases[i], key);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void rowPut(String key, String[] aliases, RowSpec value) {
/* 662 */     ensureLowerCase(key);
/* 663 */     rowPut(key, value);
/* 664 */     for (int i = 0; i < aliases.length; i++) {
/* 665 */       ensureLowerCase(aliases[i]);
/* 666 */       ROW_ALIASES.put(aliases[i], key);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void ensureLowerCase(String str) {
/* 672 */     String lowerCase = str.toLowerCase(Locale.ENGLISH);
/* 673 */     if (!lowerCase.equals(str))
/* 674 */       throw new IllegalArgumentException("The string \"" + str + "\" should be lower case."); 
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\jgoodies\forms\layout\LayoutMap.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */