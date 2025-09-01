package com.jgoodies.forms.layout;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.util.LayoutStyle;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

public final class LayoutMap {
   private static final char VARIABLE_PREFIX_CHAR = '$';
   private static final Map COLUMN_ALIASES = new HashMap();
   private static final Map ROW_ALIASES = new HashMap();
   private static LayoutMap root = null;
   private final LayoutMap parent;
   private final Map columnMap;
   private final Map columnMapCache;
   private final Map rowMap;
   private final Map rowMapCache;

   public LayoutMap() {
      this(getRoot());
   }

   public LayoutMap(LayoutMap parent) {
      this.parent = parent;
      this.columnMap = new HashMap();
      this.rowMap = new HashMap();
      this.columnMapCache = new HashMap();
      this.rowMapCache = new HashMap();
   }

   public static LayoutMap getRoot() {
      if (root == null) {
         root = createRoot();
      }

      return root;
   }

   public boolean columnContainsKey(String key) {
      String resolvedKey = this.resolveColumnKey(key);
      return this.columnMap.containsKey(resolvedKey) || this.parent != null && this.parent.columnContainsKey(resolvedKey);
   }

   public String columnGet(String key) {
      String resolvedKey = this.resolveColumnKey(key);
      String cachedValue = (String)this.columnMapCache.get(resolvedKey);
      if (cachedValue != null) {
         return cachedValue;
      } else {
         String value = (String)this.columnMap.get(resolvedKey);
         if (value == null && this.parent != null) {
            value = this.parent.columnGet(resolvedKey);
         }

         if (value == null) {
            return null;
         } else {
            String expandedString = this.expand(value, true);
            this.columnMapCache.put(resolvedKey, expandedString);
            return expandedString;
         }
      }
   }

   public String columnPut(String key, String value) {
      String resolvedKey = this.resolveColumnKey(key);
      if (value == null) {
         throw new NullPointerException("The column expression value must not be null.");
      } else {
         this.columnMapCache.clear();
         return (String)this.columnMap.put(resolvedKey, value.toLowerCase(Locale.ENGLISH));
      }
   }

   public String columnPut(String key, ColumnSpec value) {
      return this.columnPut(key, value.encode());
   }

   public String columnPut(String key, Size value) {
      return this.columnPut(key, value.encode());
   }

   public String columnRemove(String key) {
      String resolvedKey = this.resolveColumnKey(key);
      this.columnMapCache.clear();
      return (String)this.columnMap.remove(resolvedKey);
   }

   public boolean rowContainsKey(String key) {
      String resolvedKey = this.resolveRowKey(key);
      return this.rowMap.containsKey(resolvedKey) || this.parent != null && this.parent.rowContainsKey(resolvedKey);
   }

   public String rowGet(String key) {
      String resolvedKey = this.resolveRowKey(key);
      String cachedValue = (String)this.rowMapCache.get(resolvedKey);
      if (cachedValue != null) {
         return cachedValue;
      } else {
         String value = (String)this.rowMap.get(resolvedKey);
         if (value == null && this.parent != null) {
            value = this.parent.rowGet(resolvedKey);
         }

         if (value == null) {
            return null;
         } else {
            String expandedString = this.expand(value, false);
            this.rowMapCache.put(resolvedKey, expandedString);
            return expandedString;
         }
      }
   }

   public String rowPut(String key, String value) {
      String resolvedKey = this.resolveRowKey(key);
      if (value == null) {
         throw new NullPointerException("The row expression value must not be null.");
      } else {
         this.rowMapCache.clear();
         return (String)this.rowMap.put(resolvedKey, value.toLowerCase(Locale.ENGLISH));
      }
   }

   public String rowPut(String key, RowSpec value) {
      return this.rowPut(key, value.encode());
   }

   public String rowPut(String key, Size value) {
      return this.rowPut(key, value.encode());
   }

   public String rowRemove(String key) {
      String resolvedKey = this.resolveRowKey(key);
      this.rowMapCache.clear();
      return (String)this.rowMap.remove(resolvedKey);
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer(super.toString());
      buffer.append("\n  Column associations:");
      Iterator iterator = this.columnMap.entrySet().iterator();

      Entry name;
      while(iterator.hasNext()) {
         name = (Entry)iterator.next();
         buffer.append("\n    ");
         buffer.append(name.getKey());
         buffer.append("->");
         buffer.append(name.getValue());
      }

      buffer.append("\n  Row associations:");
      iterator = this.rowMap.entrySet().iterator();

      while(iterator.hasNext()) {
         name = (Entry)iterator.next();
         buffer.append("\n    ");
         buffer.append(name.getKey());
         buffer.append("->");
         buffer.append(name.getValue());
      }

      return buffer.toString();
   }

   String expand(String expression, boolean horizontal) {
      int cursor = 0;
      int start = expression.indexOf(36, cursor);
      if (start == -1) {
         return expression;
      } else {
         StringBuffer buffer = new StringBuffer();

         do {
            buffer.append(expression.substring(cursor, start));
            String variableName = this.nextVariableName(expression, start);
            buffer.append(this.expansion(variableName, horizontal));
            cursor = start + variableName.length() + 1;
            start = expression.indexOf(36, cursor);
         } while(start != -1);

         buffer.append(expression.substring(cursor));
         return buffer.toString();
      }
   }

   private String nextVariableName(String expression, int start) {
      int length = expression.length();
      if (length <= start) {
         FormSpecParser.fail(expression, start, "Missing variable name after variable char '$'.");
      }

      int end;
      if (expression.charAt(start + 1) == '{') {
         end = expression.indexOf(125, start + 1);
         if (end == -1) {
            FormSpecParser.fail(expression, start, "Missing closing brace '}' for variable.");
         }

         return expression.substring(start + 1, end + 1);
      } else {
         for(end = start + 1; end < length && Character.isUnicodeIdentifierPart(expression.charAt(end)); ++end) {
         }

         return expression.substring(start + 1, end);
      }
   }

   private String expansion(String variableName, boolean horizontal) {
      String key = stripBraces(variableName);
      String expansion = horizontal ? this.columnGet(key) : this.rowGet(key);
      if (expansion == null) {
         String orientation = horizontal ? "column" : "row";
         throw new IllegalArgumentException("Unknown " + orientation + " layout variable \"" + key + "\"");
      } else {
         return expansion;
      }
   }

   private static String stripBraces(String variableName) {
      return variableName.charAt(0) == '{' ? variableName.substring(1, variableName.length() - 1) : variableName;
   }

   private String resolveColumnKey(String key) {
      if (key == null) {
         throw new NullPointerException("The key must not be null.");
      } else {
         String lowercaseKey = key.toLowerCase(Locale.ENGLISH);
         String defaultKey = (String)COLUMN_ALIASES.get(lowercaseKey);
         return defaultKey == null ? lowercaseKey : defaultKey;
      }
   }

   private String resolveRowKey(String key) {
      if (key == null) {
         throw new NullPointerException("The key must not be null.");
      } else {
         String lowercaseKey = key.toLowerCase(Locale.ENGLISH);
         String defaultKey = (String)ROW_ALIASES.get(lowercaseKey);
         return defaultKey == null ? lowercaseKey : defaultKey;
      }
   }

   private static LayoutMap createRoot() {
      LayoutMap map = new LayoutMap((LayoutMap)null);
      map.columnPut("label-component-gap", new String[]{"lcg", "lcgap"}, FormFactory.LABEL_COMPONENT_GAP_COLSPEC);
      map.columnPut("related-gap", new String[]{"rg", "rgap"}, FormFactory.RELATED_GAP_COLSPEC);
      map.columnPut("unrelated-gap", new String[]{"ug", "ugap"}, FormFactory.UNRELATED_GAP_COLSPEC);
      map.columnPut("button", new String[]{"b"}, FormFactory.BUTTON_COLSPEC);
      map.columnPut("growing-button", new String[]{"gb"}, FormFactory.GROWING_BUTTON_COLSPEC);
      map.columnPut("dialog-margin", new String[]{"dm", "dmargin"}, ColumnSpec.createGap(LayoutStyle.getCurrent().getDialogMarginX()));
      map.columnPut("tabbed-dialog-margin", new String[]{"tdm", "tdmargin"}, ColumnSpec.createGap(LayoutStyle.getCurrent().getTabbedDialogMarginX()));
      map.columnPut("glue", FormFactory.GLUE_COLSPEC.toShortString());
      map.rowPut("related-gap", new String[]{"rg", "rgap"}, FormFactory.RELATED_GAP_ROWSPEC);
      map.rowPut("unrelated-gap", new String[]{"ug", "ugap"}, FormFactory.UNRELATED_GAP_ROWSPEC);
      map.rowPut("narrow-line-gap", new String[]{"nlg", "nlgap"}, FormFactory.NARROW_LINE_GAP_ROWSPEC);
      map.rowPut("line-gap", new String[]{"lg", "lgap"}, FormFactory.LINE_GAP_ROWSPEC);
      map.rowPut("paragraph-gap", new String[]{"pg", "pgap"}, FormFactory.PARAGRAPH_GAP_ROWSPEC);
      map.rowPut("dialog-margin", new String[]{"dm", "dmargin"}, RowSpec.createGap(LayoutStyle.getCurrent().getDialogMarginY()));
      map.rowPut("tabbed-dialog-margin", new String[]{"tdm", "tdmargin"}, RowSpec.createGap(LayoutStyle.getCurrent().getTabbedDialogMarginY()));
      map.rowPut("button", new String[]{"b"}, FormFactory.BUTTON_ROWSPEC);
      map.rowPut("glue", FormFactory.GLUE_ROWSPEC);
      return map;
   }

   private void columnPut(String key, String[] aliases, ColumnSpec value) {
      this.ensureLowerCase(key);
      this.columnPut(key, value);

      for(int i = 0; i < aliases.length; ++i) {
         this.ensureLowerCase(aliases[i]);
         COLUMN_ALIASES.put(aliases[i], key);
      }

   }

   private void rowPut(String key, String[] aliases, RowSpec value) {
      this.ensureLowerCase(key);
      this.rowPut(key, value);

      for(int i = 0; i < aliases.length; ++i) {
         this.ensureLowerCase(aliases[i]);
         ROW_ALIASES.put(aliases[i], key);
      }

   }

   private void ensureLowerCase(String str) {
      String lowerCase = str.toLowerCase(Locale.ENGLISH);
      if (!lowerCase.equals(str)) {
         throw new IllegalArgumentException("The string \"" + str + "\" should be lower case.");
      }
   }
}
