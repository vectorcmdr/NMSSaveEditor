/*      */ package com.formdev.flatlaf;
/*      */ 
/*      */ import com.formdev.flatlaf.ui.FlatEmptyBorder;
/*      */ import com.formdev.flatlaf.ui.FlatLineBorder;
/*      */ import com.formdev.flatlaf.util.ColorFunctions;
/*      */ import com.formdev.flatlaf.util.DerivedColor;
/*      */ import com.formdev.flatlaf.util.GrayFilter;
/*      */ import com.formdev.flatlaf.util.HSLColor;
/*      */ import com.formdev.flatlaf.util.LoggingFacade;
/*      */ import com.formdev.flatlaf.util.SoftCache;
/*      */ import com.formdev.flatlaf.util.StringUtils;
/*      */ import com.formdev.flatlaf.util.SystemInfo;
/*      */ import com.formdev.flatlaf.util.UIScale;
/*      */ import java.awt.Color;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Font;
/*      */ import java.awt.Insets;
/*      */ import java.io.File;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.FileNotFoundException;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.StreamTokenizer;
/*      */ import java.io.StringReader;
/*      */ import java.net.URL;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.Properties;
/*      */ import java.util.function.Function;
/*      */ import javax.swing.Icon;
/*      */ import javax.swing.UIDefaults;
/*      */ import javax.swing.UIManager;
/*      */ import javax.swing.border.Border;
/*      */ import javax.swing.plaf.ColorUIResource;
/*      */ import javax.swing.plaf.DimensionUIResource;
/*      */ import javax.swing.plaf.InsetsUIResource;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class UIDefaultsLoader
/*      */ {
/*      */   private static final String TYPE_PREFIX = "{";
/*      */   private static final String TYPE_PREFIX_END = "}";
/*      */   private static final String VARIABLE_PREFIX = "@";
/*      */   private static final String PROPERTY_PREFIX = "$";
/*      */   private static final String OPTIONAL_PREFIX = "?";
/*      */   private static final String WILDCARD_PREFIX = "*.";
/*      */   static final String KEY_VARIABLES = "FlatLaf.internal.variables";
/*      */   private static int parseColorDepth;
/*      */   private static Map<String, ColorUIResource> systemColorCache;
/*   88 */   private static final SoftCache<String, Object> fontCache = new SoftCache();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static void loadDefaultsFromProperties(Class<?> lookAndFeelClass, List<FlatDefaultsAddon> addons, Properties additionalDefaults, boolean dark, UIDefaults defaults) {
/*   94 */     ArrayList<Class<?>> lafClasses = new ArrayList<>();
/*   95 */     Class<?> lafClass = lookAndFeelClass;
/*   96 */     for (; FlatLaf.class.isAssignableFrom(lafClass); 
/*   97 */       lafClass = lafClass.getSuperclass())
/*      */     {
/*   99 */       lafClasses.add(0, lafClass);
/*      */     }
/*      */     
/*  102 */     loadDefaultsFromProperties(lafClasses, addons, additionalDefaults, dark, defaults);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static void loadDefaultsFromProperties(List<Class<?>> lafClasses, List<FlatDefaultsAddon> addons, Properties additionalDefaults, boolean dark, UIDefaults defaults) {
/*      */     try {
/*  111 */       systemColorCache = (FlatLaf.getSystemColorGetter() != null) ? new HashMap<>() : null;
/*      */ 
/*      */       
/*  114 */       Properties properties = new Properties();
/*  115 */       for (Class<?> lafClass : lafClasses) {
/*  116 */         String propertiesName = '/' + lafClass.getName().replace('.', '/') + ".properties";
/*  117 */         InputStream in = lafClass.getResourceAsStream(propertiesName); 
/*  118 */         try { if (in != null)
/*  119 */             properties.load(in); 
/*  120 */           if (in != null) in.close();  } catch (Throwable throwable) { if (in != null)
/*      */             try { in.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }
/*      */               throw throwable; }
/*      */       
/*  124 */       }  for (FlatDefaultsAddon addon : addons) {
/*  125 */         for (Class<?> lafClass : lafClasses) {
/*  126 */           InputStream in = addon.getDefaults(lafClass); 
/*  127 */           try { if (in != null)
/*  128 */               properties.load(in); 
/*  129 */             if (in != null) in.close();  } catch (Throwable throwable) { if (in != null)
/*      */               try { in.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }
/*      */                 throw throwable; }
/*      */         
/*      */         } 
/*  134 */       }  List<ClassLoader> addonClassLoaders = new ArrayList<>();
/*  135 */       for (FlatDefaultsAddon addon : addons) {
/*  136 */         ClassLoader addonClassLoader = addon.getClass().getClassLoader();
/*  137 */         if (!addonClassLoaders.contains(addonClassLoader)) {
/*  138 */           addonClassLoaders.add(addonClassLoader);
/*      */         }
/*      */       } 
/*      */       
/*  142 */       List<Object> customDefaultsSources = FlatLaf.getCustomDefaultsSources();
/*  143 */       int size = (customDefaultsSources != null) ? customDefaultsSources.size() : 0;
/*  144 */       for (int i = 0; i < size; i++) {
/*  145 */         Object source = customDefaultsSources.get(i);
/*  146 */         if (source instanceof String && i + 1 < size) {
/*      */           
/*  148 */           String packageName = (String)source;
/*  149 */           ClassLoader classLoader = (ClassLoader)customDefaultsSources.get(++i);
/*      */ 
/*      */           
/*  152 */           if (classLoader != null && !addonClassLoaders.contains(classLoader)) {
/*  153 */             addonClassLoaders.add(classLoader);
/*      */           }
/*  155 */           packageName = packageName.replace('.', '/');
/*  156 */           if (classLoader == null) {
/*  157 */             classLoader = FlatLaf.class.getClassLoader();
/*      */           }
/*  159 */           for (Class<?> lafClass : lafClasses)
/*  160 */           { String propertiesName = packageName + '/' + lafClass.getSimpleName() + ".properties";
/*  161 */             InputStream in = classLoader.getResourceAsStream(propertiesName); 
/*  162 */             try { if (in != null)
/*  163 */                 properties.load(in); 
/*  164 */               if (in != null) in.close();  } catch (Throwable throwable) { if (in != null)
/*      */                 try { in.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } 
/*  166 */         } else if (source instanceof URL) {
/*      */           
/*  168 */           URL packageUrl = (URL)source;
/*  169 */           for (Class<?> lafClass : lafClasses) {
/*  170 */             URL propertiesUrl = new URL(packageUrl + lafClass.getSimpleName() + ".properties");
/*      */             
/*  172 */             try { InputStream in = propertiesUrl.openStream(); 
/*  173 */               try { properties.load(in);
/*  174 */                 if (in != null) in.close();  } catch (Throwable throwable) { if (in != null) try { in.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (FileNotFoundException fileNotFoundException) {}
/*      */           }
/*      */         
/*      */         }
/*  178 */         else if (source instanceof File) {
/*      */           
/*  180 */           File folder = (File)source;
/*  181 */           for (Class<?> lafClass : lafClasses) {
/*  182 */             File propertiesFile = new File(folder, lafClass.getSimpleName() + ".properties");
/*  183 */             if (!propertiesFile.isFile()) {
/*      */               continue;
/*      */             }
/*  186 */             InputStream in = new FileInputStream(propertiesFile); 
/*  187 */             try { properties.load(in);
/*  188 */               in.close(); } catch (Throwable throwable) { try { in.close(); }
/*      */               catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }
/*      */                throw throwable; }
/*      */           
/*      */           } 
/*      */         } 
/*  194 */       }  if (additionalDefaults != null) {
/*  195 */         properties.putAll(additionalDefaults);
/*      */       }
/*      */       
/*  198 */       ArrayList<String> platformSpecificKeys = new ArrayList<>();
/*  199 */       for (Object okey : properties.keySet()) {
/*  200 */         String key = (String)okey;
/*  201 */         if (key.startsWith("[") && (key
/*  202 */           .startsWith("[win]") || key
/*  203 */           .startsWith("[mac]") || key
/*  204 */           .startsWith("[linux]") || key
/*  205 */           .startsWith("[light]") || key
/*  206 */           .startsWith("[dark]"))) {
/*  207 */           platformSpecificKeys.add(key);
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*  212 */       if (!platformSpecificKeys.isEmpty()) {
/*      */         
/*  214 */         String lightOrDarkPrefix = dark ? "[dark]" : "[light]";
/*  215 */         for (String key : platformSpecificKeys) {
/*  216 */           if (key.startsWith(lightOrDarkPrefix)) {
/*  217 */             properties.put(key.substring(lightOrDarkPrefix.length()), properties.remove(key));
/*      */           }
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  224 */         String platformPrefix = SystemInfo.isWindows ? "[win]" : (SystemInfo.isMacOS ? "[mac]" : (SystemInfo.isLinux ? "[linux]" : "[unknown]"));
/*  225 */         for (String key : platformSpecificKeys) {
/*  226 */           Object value = properties.remove(key);
/*  227 */           if (key.startsWith(platformPrefix)) {
/*  228 */             properties.put(key.substring(platformPrefix.length()), value);
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/*  233 */       HashMap<String, String> wildcards = new HashMap<>();
/*  234 */       Iterator<Map.Entry<Object, Object>> it = properties.entrySet().iterator();
/*  235 */       while (it.hasNext()) {
/*  236 */         Map.Entry<Object, Object> e = it.next();
/*  237 */         String key = (String)e.getKey();
/*  238 */         if (key.startsWith("*.")) {
/*  239 */           wildcards.put(key.substring("*.".length()), (String)e.getValue());
/*  240 */           it.remove();
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  245 */       for (Object key : defaults.keySet()) {
/*      */         int dot;
/*  247 */         if (!(key instanceof String) || properties
/*  248 */           .containsKey(key) || (
/*  249 */           dot = ((String)key).lastIndexOf('.')) < 0) {
/*      */           continue;
/*      */         }
/*  252 */         String wildcardKey = ((String)key).substring(dot + 1);
/*  253 */         String wildcardValue = wildcards.get(wildcardKey);
/*  254 */         if (wildcardValue != null) {
/*  255 */           properties.put(key, wildcardValue);
/*      */         }
/*      */       } 
/*  258 */       Function<String, String> propertiesGetter = key -> properties.getProperty(key);
/*      */ 
/*      */       
/*  261 */       Function<String, String> resolver = value -> resolveValue(value, propertiesGetter);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  266 */       Map<String, String> variables = new HashMap<>(50);
/*  267 */       for (Map.Entry<Object, Object> e : properties.entrySet()) {
/*  268 */         String key = (String)e.getKey();
/*  269 */         if (key.startsWith("@")) {
/*  270 */           variables.put(key, (String)e.getValue());
/*      */           
/*      */           continue;
/*      */         } 
/*  274 */         String value = resolveValue((String)e.getValue(), propertiesGetter);
/*      */         try {
/*  276 */           defaults.put(key, parseValue(key, value, null, null, resolver, addonClassLoaders));
/*  277 */         } catch (RuntimeException ex) {
/*  278 */           logParseError(key, value, ex, true);
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  283 */       defaults.put("FlatLaf.internal.variables", variables);
/*      */ 
/*      */       
/*  286 */       systemColorCache = null;
/*  287 */     } catch (IOException ex) {
/*  288 */       LoggingFacade.INSTANCE.logSevere("FlatLaf: Failed to load properties files.", ex);
/*      */     } 
/*      */   }
/*      */   
/*      */   static void logParseError(String key, String value, RuntimeException ex, boolean severe) {
/*  293 */     String message = "FlatLaf: Failed to parse: '" + key + '=' + value + '\'';
/*  294 */     if (severe) {
/*  295 */       LoggingFacade.INSTANCE.logSevere(message, ex);
/*      */     } else {
/*  297 */       LoggingFacade.INSTANCE.logConfig(message, ex);
/*      */     } 
/*      */   }
/*      */   static String resolveValue(String value, Function<String, String> propertiesGetter) {
/*  301 */     value = value.trim();
/*  302 */     String value0 = value;
/*      */     
/*  304 */     if (value.startsWith("$")) {
/*  305 */       value = value.substring("$".length());
/*  306 */     } else if (!value.startsWith("@")) {
/*  307 */       return value;
/*      */     } 
/*  309 */     boolean optional = false;
/*  310 */     if (value.startsWith("?")) {
/*  311 */       value = value.substring("?".length());
/*  312 */       optional = true;
/*      */     } 
/*      */     
/*  315 */     String newValue = propertiesGetter.apply(value);
/*  316 */     if (newValue == null) {
/*  317 */       if (optional) {
/*  318 */         return "null";
/*      */       }
/*  320 */       throw new IllegalArgumentException("variable or property '" + value + "' not found");
/*      */     } 
/*      */     
/*  323 */     if (newValue.equals(value0)) {
/*  324 */       throw new IllegalArgumentException("endless recursion in variable or property '" + value + "'");
/*      */     }
/*  326 */     return resolveValue(newValue, propertiesGetter);
/*      */   }
/*      */   
/*      */   static String resolveValueFromUIManager(String value) {
/*  330 */     if (value.startsWith("@")) {
/*      */       
/*  332 */       Map<String, String> variables = (Map<String, String>)UIManager.get("FlatLaf.internal.variables");
/*  333 */       String str = (variables != null) ? variables.get(value) : null;
/*  334 */       if (str == null) {
/*  335 */         throw new IllegalArgumentException("variable '" + value + "' not found");
/*      */       }
/*  337 */       return resolveValueFromUIManager(str);
/*      */     } 
/*      */     
/*  340 */     if (!value.startsWith("$")) {
/*  341 */       return value;
/*      */     }
/*  343 */     String key = value.substring("$".length());
/*  344 */     Object newValue = UIManager.get(key);
/*  345 */     if (newValue == null) {
/*  346 */       throw new IllegalArgumentException("property '" + key + "' not found");
/*      */     }
/*      */     
/*  349 */     if (newValue instanceof Color) {
/*  350 */       Color color = (Color)newValue;
/*  351 */       int alpha = color.getAlpha();
/*  352 */       return String.format((alpha != 255) ? "#%06x%02x" : "#%06x", new Object[] { Integer.valueOf(color.getRGB() & 0xFFFFFF), Integer.valueOf(alpha) });
/*      */     } 
/*      */     
/*  355 */     throw new IllegalArgumentException("property value type '" + newValue.getClass().getName() + "' not supported in references");
/*      */   }
/*      */   
/*  358 */   enum ValueType { UNKNOWN, STRING, BOOLEAN, CHARACTER, INTEGER, INTEGERORFLOAT, FLOAT, BORDER, ICON, INSETS, DIMENSION, COLOR, FONT,
/*  359 */     SCALEDINTEGER, SCALEDFLOAT, SCALEDINSETS, SCALEDDIMENSION, INSTANCE, CLASS, GRAYFILTER, NULL, LAZY; }
/*      */   
/*  361 */   private static final ValueType[] tempResultValueType = new ValueType[1];
/*      */   private static Map<Class<?>, ValueType> javaValueTypes;
/*      */   private static Map<String, ValueType> knownValueTypes;
/*      */   
/*      */   static Object parseValue(String key, String value, Class<?> valueType) {
/*  366 */     return parseValue(key, value, valueType, null, v -> v, Collections.emptyList());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static Object parseValue(String key, String value, Class<?> javaValueType, ValueType[] resultValueType, Function<String, String> resolver, List<ClassLoader> addonClassLoaders) {
/*  372 */     if (resultValueType == null) {
/*  373 */       resultValueType = tempResultValueType;
/*      */     }
/*      */     
/*  376 */     if (key.startsWith("[style]")) {
/*  377 */       resultValueType[0] = ValueType.STRING;
/*  378 */       return value;
/*      */     } 
/*      */     
/*  381 */     value = value.trim();
/*      */ 
/*      */     
/*  384 */     if (value.equals("null") || value.isEmpty()) {
/*  385 */       resultValueType[0] = ValueType.NULL;
/*  386 */       return null;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  397 */     if (value.startsWith("if(") && value.endsWith(")")) {
/*  398 */       List<String> params = splitFunctionParams(value.substring(3, value.length() - 1), ',');
/*  399 */       if (params.size() != 3) {
/*  400 */         throwMissingParametersException(value);
/*      */       }
/*  402 */       boolean ifCondition = parseCondition(params.get(0), resolver, addonClassLoaders);
/*  403 */       String ifValue = params.get(ifCondition ? 1 : 2);
/*  404 */       return parseValue(key, resolver.apply(ifValue), javaValueType, resultValueType, resolver, addonClassLoaders);
/*      */     } 
/*      */     
/*  407 */     ValueType valueType = ValueType.UNKNOWN;
/*      */     
/*  409 */     if (javaValueType != null) {
/*  410 */       if (javaValueTypes == null) {
/*      */         
/*  412 */         javaValueTypes = new HashMap<>();
/*  413 */         javaValueTypes.put(String.class, ValueType.STRING);
/*  414 */         javaValueTypes.put(boolean.class, ValueType.BOOLEAN);
/*  415 */         javaValueTypes.put(Boolean.class, ValueType.BOOLEAN);
/*  416 */         javaValueTypes.put(char.class, ValueType.CHARACTER);
/*  417 */         javaValueTypes.put(Character.class, ValueType.CHARACTER);
/*  418 */         javaValueTypes.put(int.class, ValueType.INTEGER);
/*  419 */         javaValueTypes.put(Integer.class, ValueType.INTEGER);
/*  420 */         javaValueTypes.put(float.class, ValueType.FLOAT);
/*  421 */         javaValueTypes.put(Float.class, ValueType.FLOAT);
/*  422 */         javaValueTypes.put(Border.class, ValueType.BORDER);
/*  423 */         javaValueTypes.put(Icon.class, ValueType.ICON);
/*  424 */         javaValueTypes.put(Insets.class, ValueType.INSETS);
/*  425 */         javaValueTypes.put(Dimension.class, ValueType.DIMENSION);
/*  426 */         javaValueTypes.put(Color.class, ValueType.COLOR);
/*  427 */         javaValueTypes.put(Font.class, ValueType.FONT);
/*      */       } 
/*      */ 
/*      */       
/*  431 */       valueType = javaValueTypes.get(javaValueType);
/*  432 */       if (valueType == null) {
/*  433 */         throw new IllegalArgumentException("unsupported value type '" + javaValueType.getName() + "'");
/*      */       }
/*      */       
/*  436 */       if (valueType == ValueType.STRING && value.startsWith("\"") && value.endsWith("\"")) {
/*  437 */         value = value.substring(1, value.length() - 1);
/*      */       }
/*      */     } else {
/*  440 */       switch (value) { case "false":
/*  441 */           resultValueType[0] = ValueType.BOOLEAN; return Boolean.valueOf(false);
/*  442 */         case "true": resultValueType[0] = ValueType.BOOLEAN; return Boolean.valueOf(true); }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  447 */       if (value.startsWith("lazy(") && value.endsWith(")")) {
/*  448 */         resultValueType[0] = ValueType.LAZY;
/*  449 */         String uiKey = StringUtils.substringTrimmed(value, 5, value.length() - 1);
/*  450 */         return t -> lazyUIManagerGet(uiKey);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  456 */       if (value.startsWith("#")) {
/*  457 */         valueType = ValueType.COLOR;
/*  458 */       } else if (value.startsWith("{")) {
/*  459 */         int end = value.indexOf("}");
/*  460 */         if (end != -1) {
/*      */           try {
/*  462 */             String typeStr = value.substring("{".length(), end);
/*  463 */             valueType = ValueType.valueOf(typeStr.toUpperCase(Locale.ENGLISH));
/*      */ 
/*      */             
/*  466 */             value = value.substring(end + "}".length());
/*  467 */           } catch (IllegalArgumentException illegalArgumentException) {}
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  473 */       if (valueType == ValueType.UNKNOWN) {
/*  474 */         if (knownValueTypes == null) {
/*      */           
/*  476 */           knownValueTypes = new HashMap<>();
/*      */           
/*  478 */           knownValueTypes.put("activeCaptionBorder", ValueType.COLOR);
/*  479 */           knownValueTypes.put("inactiveCaptionBorder", ValueType.COLOR);
/*  480 */           knownValueTypes.put("windowBorder", ValueType.COLOR);
/*      */           
/*  482 */           knownValueTypes.put("SplitPane.dividerSize", ValueType.INTEGER);
/*  483 */           knownValueTypes.put("SplitPaneDivider.gripDotSize", ValueType.INTEGER);
/*  484 */           knownValueTypes.put("dividerSize", ValueType.INTEGER);
/*  485 */           knownValueTypes.put("gripDotSize", ValueType.INTEGER);
/*      */           
/*  487 */           knownValueTypes.put("TabbedPane.closeCrossPlainSize", ValueType.FLOAT);
/*  488 */           knownValueTypes.put("TabbedPane.closeCrossFilledSize", ValueType.FLOAT);
/*  489 */           knownValueTypes.put("closeCrossPlainSize", ValueType.FLOAT);
/*  490 */           knownValueTypes.put("closeCrossFilledSize", ValueType.FLOAT);
/*      */           
/*  492 */           knownValueTypes.put("Table.intercellSpacing", ValueType.DIMENSION);
/*  493 */           knownValueTypes.put("intercellSpacing", ValueType.DIMENSION);
/*      */         } 
/*      */         
/*  496 */         valueType = knownValueTypes.getOrDefault(key, ValueType.UNKNOWN);
/*      */       } 
/*      */ 
/*      */       
/*  500 */       if (valueType == ValueType.UNKNOWN) {
/*  501 */         if (key.endsWith("UI")) {
/*  502 */           valueType = ValueType.STRING;
/*  503 */         } else if (key.endsWith("Color") || (key
/*  504 */           .endsWith("ground") && (key
/*  505 */           .endsWith(".background") || key.endsWith("Background") || key.equals("background") || key
/*  506 */           .endsWith(".foreground") || key.endsWith("Foreground") || key.equals("foreground")))) {
/*  507 */           valueType = ValueType.COLOR;
/*  508 */         } else if (key.endsWith(".font") || key.endsWith("Font") || key.equals("font")) {
/*  509 */           valueType = ValueType.FONT;
/*  510 */         } else if (key.endsWith(".border") || key.endsWith("Border") || key.equals("border")) {
/*  511 */           valueType = ValueType.BORDER;
/*  512 */         } else if (key.endsWith(".icon") || key.endsWith("Icon") || key.equals("icon")) {
/*  513 */           valueType = ValueType.ICON;
/*  514 */         } else if (key.endsWith(".margin") || key.equals("margin") || key
/*  515 */           .endsWith(".padding") || key.equals("padding") || key
/*  516 */           .endsWith("Margins") || key.endsWith("Insets")) {
/*  517 */           valueType = ValueType.INSETS;
/*  518 */         } else if (key.endsWith("Size")) {
/*  519 */           valueType = ValueType.DIMENSION;
/*  520 */         } else if (key.endsWith("Width") || key.endsWith("Height")) {
/*  521 */           valueType = ValueType.INTEGERORFLOAT;
/*  522 */         } else if (key.endsWith("Char")) {
/*  523 */           valueType = ValueType.CHARACTER;
/*  524 */         } else if (key.endsWith("grayFilter")) {
/*  525 */           valueType = ValueType.GRAYFILTER;
/*      */         } 
/*      */       }
/*      */     } 
/*  529 */     resultValueType[0] = valueType;
/*      */ 
/*      */     
/*  532 */     switch (valueType) { case STRING:
/*  533 */         return value;
/*  534 */       case BOOLEAN: return parseBoolean(value);
/*  535 */       case CHARACTER: return parseCharacter(value);
/*  536 */       case INTEGER: return parseInteger(value);
/*  537 */       case INTEGERORFLOAT: return parseIntegerOrFloat(value);
/*  538 */       case FLOAT: return parseFloat(value);
/*  539 */       case BORDER: return parseBorder(value, resolver, addonClassLoaders);
/*  540 */       case ICON: return parseInstance(value, addonClassLoaders);
/*  541 */       case INSETS: return parseInsets(value);
/*  542 */       case DIMENSION: return parseDimension(value);
/*  543 */       case COLOR: return parseColorOrFunction(value, resolver);
/*  544 */       case FONT: return parseFont(value);
/*  545 */       case SCALEDINTEGER: return parseScaledInteger(value);
/*  546 */       case SCALEDFLOAT: return parseScaledFloat(value);
/*  547 */       case SCALEDINSETS: return parseScaledInsets(value);
/*  548 */       case SCALEDDIMENSION: return parseScaledDimension(value);
/*  549 */       case INSTANCE: return parseInstance(value, addonClassLoaders);
/*  550 */       case CLASS: return parseClass(value, addonClassLoaders);
/*  551 */       case GRAYFILTER: return parseGrayFilter(value); }
/*      */ 
/*      */ 
/*      */     
/*  555 */     if (value.startsWith("\"") && value.endsWith("\"")) {
/*  556 */       resultValueType[0] = ValueType.STRING;
/*  557 */       return value.substring(1, value.length() - 1);
/*      */     } 
/*      */ 
/*      */     
/*  561 */     if (value.startsWith("#") || value.endsWith(")")) {
/*  562 */       Object color = parseColorOrFunction(value, resolver);
/*  563 */       resultValueType[0] = (color != null) ? ValueType.COLOR : ValueType.NULL;
/*  564 */       return color;
/*      */     } 
/*      */ 
/*      */     
/*  568 */     char firstChar = value.charAt(0);
/*  569 */     if ((firstChar >= '0' && firstChar <= '9') || firstChar == '-' || firstChar == '+' || firstChar == '.') {
/*      */       
/*      */       try {
/*      */ 
/*      */         
/*  574 */         Integer integer = parseInteger(value);
/*  575 */         resultValueType[0] = ValueType.INTEGER;
/*  576 */         return integer;
/*  577 */       } catch (NumberFormatException numberFormatException) {
/*      */ 
/*      */         
/*      */         try {
/*      */ 
/*      */           
/*  583 */           Float f = parseFloat(value);
/*  584 */           resultValueType[0] = ValueType.FLOAT;
/*  585 */           return f;
/*  586 */         } catch (NumberFormatException numberFormatException1) {}
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  592 */     resultValueType[0] = ValueType.STRING;
/*  593 */     return value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean parseCondition(String condition, Function<String, String> resolver, List<ClassLoader> addonClassLoaders) {
/*      */     try {
/*  601 */       Object conditionValue = parseValue("", resolver.apply(condition), null, null, resolver, addonClassLoaders);
/*  602 */       return (conditionValue != null && 
/*  603 */         !conditionValue.equals(Boolean.valueOf(false)) && 
/*  604 */         !conditionValue.equals(Integer.valueOf(0)));
/*  605 */     } catch (IllegalArgumentException ex) {
/*      */       
/*  607 */       return false;
/*      */     } 
/*      */   }
/*      */   
/*      */   private static Object parseBorder(String value, Function<String, String> resolver, List<ClassLoader> addonClassLoaders) {
/*  612 */     if (value.indexOf(',') >= 0) {
/*      */       
/*  614 */       List<String> parts = splitFunctionParams(value, ',');
/*  615 */       Insets insets = parseInsets(value);
/*      */ 
/*      */       
/*  618 */       ColorUIResource lineColor = (parts.size() >= 5) ? (ColorUIResource)parseColorOrFunction(resolver.apply(parts.get(4)), resolver) : null;
/*  619 */       float lineThickness = (parts.size() >= 6 && !((String)parts.get(5)).isEmpty()) ? parseFloat(parts.get(5)).floatValue() : 1.0F;
/*  620 */       int arc = (parts.size() >= 7) ? parseInteger(parts.get(6)).intValue() : 0;
/*      */       
/*  622 */       return t -> (lineColor != null) ? new FlatLineBorder(insets, lineColor, lineThickness, arc) : new FlatEmptyBorder(insets);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  628 */     return parseInstance(value, addonClassLoaders);
/*      */   }
/*      */   
/*      */   private static Object parseInstance(String value, List<ClassLoader> addonClassLoaders) {
/*  632 */     return t -> {
/*      */         try {
/*      */           return findClass(value, addonClassLoaders).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
/*  635 */         } catch (Exception ex) {
/*      */           LoggingFacade.INSTANCE.logSevere("FlatLaf: Failed to instantiate '" + value + "'.", ex);
/*      */           return null;
/*      */         } 
/*      */       };
/*      */   }
/*      */   
/*      */   private static Object parseClass(String value, List<ClassLoader> addonClassLoaders) {
/*  643 */     return t -> {
/*      */         try {
/*      */           return findClass(value, addonClassLoaders);
/*  646 */         } catch (ClassNotFoundException ex) {
/*      */           LoggingFacade.INSTANCE.logSevere("FlatLaf: Failed to find class '" + value + "'.", ex);
/*      */           return null;
/*      */         } 
/*      */       };
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static Class<?> findClass(String className, List<ClassLoader> addonClassLoaders) throws ClassNotFoundException {
/*      */     try {
/*  657 */       return Class.forName(className);
/*  658 */     } catch (ClassNotFoundException ex) {
/*      */       
/*  660 */       for (ClassLoader addonClassLoader : addonClassLoaders) {
/*      */         try {
/*  662 */           return addonClassLoader.loadClass(className);
/*  663 */         } catch (ClassNotFoundException classNotFoundException) {}
/*      */       } 
/*      */ 
/*      */       
/*  667 */       throw ex;
/*      */     } 
/*      */   }
/*      */   
/*      */   private static Insets parseInsets(String value) {
/*  672 */     List<String> numbers = StringUtils.split(value, ',', true, false);
/*      */     try {
/*  674 */       return new InsetsUIResource(
/*  675 */           Integer.parseInt(numbers.get(0)), 
/*  676 */           Integer.parseInt(numbers.get(1)), 
/*  677 */           Integer.parseInt(numbers.get(2)), 
/*  678 */           Integer.parseInt(numbers.get(3)));
/*  679 */     } catch (NumberFormatException ex) {
/*  680 */       throw new IllegalArgumentException("invalid insets '" + value + "'");
/*      */     } 
/*      */   }
/*      */   
/*      */   private static Dimension parseDimension(String value) {
/*  685 */     List<String> numbers = StringUtils.split(value, ',', true, false);
/*      */     try {
/*  687 */       return new DimensionUIResource(
/*  688 */           Integer.parseInt(numbers.get(0)), 
/*  689 */           Integer.parseInt(numbers.get(1)));
/*  690 */     } catch (NumberFormatException ex) {
/*  691 */       throw new IllegalArgumentException("invalid size '" + value + "'");
/*      */     } 
/*      */   }
/*      */   
/*      */   private static Object parseColorOrFunction(String value, Function<String, String> resolver) {
/*  696 */     if (value.endsWith(")")) {
/*  697 */       return parseColorFunctions(value, resolver);
/*      */     }
/*  699 */     return parseColor(value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static ColorUIResource parseColor(String value) {
/*  709 */     int rgba = parseColorRGBA(value);
/*  710 */     return ((rgba & 0xFF000000) == -16777216) ? 
/*  711 */       new ColorUIResource(rgba) : 
/*  712 */       new ColorUIResource(new Color(rgba, true));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static int parseColorRGBA(String value) {
/*  723 */     int len = value.length();
/*  724 */     if ((len != 4 && len != 5 && len != 7 && len != 9) || value.charAt(0) != '#') {
/*  725 */       throw newInvalidColorException(value);
/*      */     }
/*      */     
/*  728 */     int n = 0;
/*  729 */     for (int i = 1; i < len; i++) {
/*  730 */       int digit; char ch = value.charAt(i);
/*      */ 
/*      */       
/*  733 */       if (ch >= '0' && ch <= '9') {
/*  734 */         digit = ch - 48;
/*  735 */       } else if (ch >= 'a' && ch <= 'f') {
/*  736 */         digit = ch - 97 + 10;
/*  737 */       } else if (ch >= 'A' && ch <= 'F') {
/*  738 */         digit = ch - 65 + 10;
/*      */       } else {
/*  740 */         throw newInvalidColorException(value);
/*      */       } 
/*  742 */       n = n << 4 | digit;
/*      */     } 
/*      */     
/*  745 */     if (len <= 5) {
/*      */       
/*  747 */       int n1 = n & 0xF000;
/*  748 */       int n2 = n & 0xF00;
/*  749 */       int n3 = n & 0xF0;
/*  750 */       int n4 = n & 0xF;
/*  751 */       n = n1 << 16 | n1 << 12 | n2 << 12 | n2 << 8 | n3 << 8 | n3 << 4 | n4 << 4 | n4;
/*      */     } 
/*      */     
/*  754 */     return (len == 4 || len == 7) ? (
/*  755 */       0xFF000000 | n) : (
/*  756 */       n >> 8 & 0xFFFFFF | (n & 0xFF) << 24);
/*      */   }
/*      */   
/*      */   private static IllegalArgumentException newInvalidColorException(String value) {
/*  760 */     return new IllegalArgumentException("invalid color '" + value + "'");
/*      */   }
/*      */   
/*      */   private static Object parseColorFunctions(String value, Function<String, String> resolver) {
/*  764 */     int paramsStart = value.indexOf('(');
/*  765 */     if (paramsStart < 0) {
/*  766 */       throw new IllegalArgumentException("missing opening parenthesis in function '" + value + "'");
/*      */     }
/*  768 */     String function = StringUtils.substringTrimmed(value, 0, paramsStart);
/*  769 */     List<String> params = splitFunctionParams(value.substring(paramsStart + 1, value.length() - 1), ',');
/*  770 */     if (params.isEmpty()) {
/*  771 */       throwMissingParametersException(value);
/*      */     }
/*  773 */     if (parseColorDepth > 100) {
/*  774 */       throw new IllegalArgumentException("endless recursion in color function '" + value + "'");
/*      */     }
/*  776 */     parseColorDepth++; try {
/*      */       Object object;
/*  778 */       switch (function) { case "if":
/*  779 */           object = parseColorIf(value, params, resolver); return object;
/*  780 */         case "systemColor": object = parseColorSystemColor(value, params, resolver); return object;
/*  781 */         case "rgb": object = parseColorRgbOrRgba(false, params, resolver); return object;
/*  782 */         case "rgba": object = parseColorRgbOrRgba(true, params, resolver); return object;
/*  783 */         case "hsl": object = parseColorHslOrHsla(false, params); return object;
/*  784 */         case "hsla": object = parseColorHslOrHsla(true, params); return object;
/*  785 */         case "lighten": object = parseColorHSLIncreaseDecrease(2, true, params, resolver); return object;
/*  786 */         case "darken": object = parseColorHSLIncreaseDecrease(2, false, params, resolver); return object;
/*  787 */         case "saturate": object = parseColorHSLIncreaseDecrease(1, true, params, resolver); return object;
/*  788 */         case "desaturate": object = parseColorHSLIncreaseDecrease(1, false, params, resolver); return object;
/*  789 */         case "fadein": object = parseColorHSLIncreaseDecrease(3, true, params, resolver); return object;
/*  790 */         case "fadeout": object = parseColorHSLIncreaseDecrease(3, false, params, resolver); return object;
/*  791 */         case "fade": object = parseColorFade(params, resolver); return object;
/*  792 */         case "spin": object = parseColorSpin(params, resolver); return object;
/*  793 */         case "changeHue": object = parseColorChange(0, params, resolver); return object;
/*  794 */         case "changeSaturation": object = parseColorChange(1, params, resolver); return object;
/*  795 */         case "changeLightness": object = parseColorChange(2, params, resolver); return object;
/*  796 */         case "changeAlpha": object = parseColorChange(3, params, resolver); return object;
/*  797 */         case "mix": object = parseColorMix(null, params, resolver); return object;
/*  798 */         case "tint": object = parseColorMix("#fff", params, resolver); return object;
/*  799 */         case "shade": object = parseColorMix("#000", params, resolver); return object;
/*  800 */         case "contrast": object = parseColorContrast(params, resolver); return object;
/*  801 */         case "over": object = parseColorOver(params, resolver); return object; }
/*      */     
/*      */     } finally {
/*  804 */       parseColorDepth--;
/*      */     } 
/*      */     
/*  807 */     throw new IllegalArgumentException("unknown color function '" + value + "'");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Object parseColorIf(String value, List<String> params, Function<String, String> resolver) {
/*  817 */     if (params.size() != 3) {
/*  818 */       throwMissingParametersException(value);
/*      */     }
/*  820 */     boolean ifCondition = parseCondition(params.get(0), resolver, Collections.emptyList());
/*  821 */     String ifValue = params.get(ifCondition ? 1 : 2);
/*  822 */     return parseColorOrFunction(resolver.apply(ifValue), resolver);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Object parseColorSystemColor(String value, List<String> params, Function<String, String> resolver) {
/*  831 */     if (params.size() < 1) {
/*  832 */       throwMissingParametersException(value);
/*      */     }
/*  834 */     ColorUIResource systemColor = getSystemColor(params.get(0));
/*  835 */     if (systemColor != null) {
/*  836 */       return systemColor;
/*      */     }
/*  838 */     String defaultValue = (params.size() > 1) ? params.get(1) : "";
/*  839 */     if (defaultValue.equals("null") || defaultValue.isEmpty()) {
/*  840 */       return null;
/*      */     }
/*  842 */     return parseColorOrFunction(resolver.apply(defaultValue), resolver);
/*      */   }
/*      */   
/*      */   private static ColorUIResource getSystemColor(String name) {
/*  846 */     Function<String, Color> systemColorGetter = FlatLaf.getSystemColorGetter();
/*  847 */     if (systemColorGetter == null) {
/*  848 */       return null;
/*      */     }
/*      */     
/*  851 */     if (systemColorCache != null && systemColorCache.containsKey(name)) {
/*  852 */       return systemColorCache.get(name);
/*      */     }
/*  854 */     Color color = systemColorGetter.apply(name);
/*  855 */     ColorUIResource uiColor = (color != null) ? new ColorUIResource(color) : null;
/*      */     
/*  857 */     if (systemColorCache != null) {
/*  858 */       systemColorCache.put(name, uiColor);
/*      */     }
/*  860 */     return uiColor;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static ColorUIResource parseColorRgbOrRgba(boolean hasAlpha, List<String> params, Function<String, String> resolver) {
/*  873 */     if (hasAlpha && params.size() == 2) {
/*      */ 
/*      */ 
/*      */       
/*  877 */       String colorStr = params.get(0);
/*  878 */       int i = parseInteger(params.get(1), 0, 255, true).intValue();
/*      */       
/*  880 */       ColorUIResource color = (ColorUIResource)parseColorOrFunction(resolver.apply(colorStr), resolver);
/*  881 */       return new ColorUIResource(new Color((i & 0xFF) << 24 | color.getRGB() & 0xFFFFFF, true));
/*      */     } 
/*      */     
/*  884 */     int red = parseInteger(params.get(0), 0, 255, true).intValue();
/*  885 */     int green = parseInteger(params.get(1), 0, 255, true).intValue();
/*  886 */     int blue = parseInteger(params.get(2), 0, 255, true).intValue();
/*  887 */     int alpha = hasAlpha ? parseInteger(params.get(3), 0, 255, true).intValue() : 255;
/*      */     
/*  889 */     return hasAlpha ? 
/*  890 */       new ColorUIResource(new Color(red, green, blue, alpha)) : 
/*  891 */       new ColorUIResource(red, green, blue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static ColorUIResource parseColorHslOrHsla(boolean hasAlpha, List<String> params) {
/*  902 */     int hue = parseInteger(params.get(0), 0, 360, false).intValue();
/*  903 */     int saturation = parsePercentage(params.get(1));
/*  904 */     int lightness = parsePercentage(params.get(2));
/*  905 */     int alpha = hasAlpha ? parsePercentage(params.get(3)) : 100;
/*      */     
/*  907 */     float[] hsl = { hue, saturation, lightness };
/*  908 */     return new ColorUIResource(HSLColor.toRGB(hsl, alpha / 100.0F));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Object parseColorHSLIncreaseDecrease(int hslIndex, boolean increase, List<String> params, Function<String, String> resolver) {
/*  922 */     String colorStr = params.get(0);
/*  923 */     int amount = parsePercentage(params.get(1));
/*  924 */     boolean relative = false;
/*  925 */     boolean autoInverse = false;
/*  926 */     boolean lazy = false;
/*  927 */     boolean derived = false;
/*      */     
/*  929 */     if (params.size() > 2) {
/*  930 */       String options = params.get(2);
/*  931 */       relative = options.contains("relative");
/*  932 */       autoInverse = options.contains("autoInverse");
/*  933 */       lazy = options.contains("lazy");
/*  934 */       derived = options.contains("derived");
/*      */ 
/*      */       
/*  937 */       if (derived && !options.contains("noAutoInverse")) {
/*  938 */         autoInverse = true;
/*      */       }
/*      */     } 
/*      */     
/*  942 */     ColorFunctions.HSLIncreaseDecrease hSLIncreaseDecrease = new ColorFunctions.HSLIncreaseDecrease(hslIndex, increase, amount, relative, autoInverse);
/*      */ 
/*      */     
/*  945 */     if (lazy) {
/*  946 */       return t -> {
/*      */           Object color = lazyUIManagerGet(colorStr);
/*      */ 
/*      */           
/*      */           return (color instanceof Color) ? new ColorUIResource(ColorFunctions.applyFunctions((Color)color, new ColorFunctions.ColorFunction[] { function })) : null;
/*      */         };
/*      */     }
/*      */ 
/*      */     
/*  955 */     return parseFunctionBaseColor(colorStr, (ColorFunctions.ColorFunction)hSLIncreaseDecrease, derived, resolver);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Object parseColorFade(List<String> params, Function<String, String> resolver) {
/*  965 */     String colorStr = params.get(0);
/*  966 */     int amount = parsePercentage(params.get(1));
/*  967 */     boolean derived = false;
/*  968 */     boolean lazy = false;
/*      */     
/*  970 */     if (params.size() > 2) {
/*  971 */       String options = params.get(2);
/*  972 */       derived = options.contains("derived");
/*  973 */       lazy = options.contains("lazy");
/*      */     } 
/*      */ 
/*      */     
/*  977 */     ColorFunctions.Fade fade = new ColorFunctions.Fade(amount);
/*      */     
/*  979 */     if (lazy) {
/*  980 */       return t -> {
/*      */           Object color = lazyUIManagerGet(colorStr);
/*      */ 
/*      */           
/*      */           return (color instanceof Color) ? new ColorUIResource(ColorFunctions.applyFunctions((Color)color, new ColorFunctions.ColorFunction[] { function })) : null;
/*      */         };
/*      */     }
/*      */ 
/*      */     
/*  989 */     return parseFunctionBaseColor(colorStr, (ColorFunctions.ColorFunction)fade, derived, resolver);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Object parseColorSpin(List<String> params, Function<String, String> resolver) {
/*  999 */     String colorStr = params.get(0);
/* 1000 */     int amount = parseInteger(params.get(1)).intValue();
/* 1001 */     boolean derived = false;
/*      */     
/* 1003 */     if (params.size() > 2) {
/* 1004 */       String options = params.get(2);
/* 1005 */       derived = options.contains("derived");
/*      */     } 
/*      */ 
/*      */     
/* 1009 */     ColorFunctions.HSLIncreaseDecrease hSLIncreaseDecrease = new ColorFunctions.HSLIncreaseDecrease(0, true, amount, false, false);
/*      */ 
/*      */     
/* 1012 */     return parseFunctionBaseColor(colorStr, (ColorFunctions.ColorFunction)hSLIncreaseDecrease, derived, resolver);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Object parseColorChange(int hslIndex, List<String> params, Function<String, String> resolver) {
/* 1027 */     String colorStr = params.get(0);
/*      */ 
/*      */     
/* 1030 */     int value = (hslIndex == 0) ? parseInteger(params.get(1)).intValue() : parsePercentage(params.get(1));
/* 1031 */     boolean derived = false;
/*      */     
/* 1033 */     if (params.size() > 2) {
/* 1034 */       String options = params.get(2);
/* 1035 */       derived = options.contains("derived");
/*      */     } 
/*      */ 
/*      */     
/* 1039 */     ColorFunctions.HSLChange hSLChange = new ColorFunctions.HSLChange(hslIndex, value);
/*      */ 
/*      */     
/* 1042 */     return parseFunctionBaseColor(colorStr, (ColorFunctions.ColorFunction)hSLChange, derived, resolver);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Object parseColorMix(String color1Str, List<String> params, Function<String, String> resolver) {
/* 1055 */     int i = 0;
/* 1056 */     if (color1Str == null)
/* 1057 */       color1Str = params.get(i++); 
/* 1058 */     String color2Str = params.get(i++);
/* 1059 */     int weight = (params.size() > i) ? parsePercentage(params.get(i)) : 50;
/*      */ 
/*      */     
/* 1062 */     ColorUIResource color2 = (ColorUIResource)parseColorOrFunction(resolver.apply(color2Str), resolver);
/* 1063 */     if (color2 == null) {
/* 1064 */       return null;
/*      */     }
/*      */     
/* 1067 */     ColorFunctions.Mix mix = new ColorFunctions.Mix(color2, weight);
/*      */ 
/*      */     
/* 1070 */     return parseFunctionBaseColor(color1Str, (ColorFunctions.ColorFunction)mix, false, resolver);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Object parseColorContrast(List<String> params, Function<String, String> resolver) {
/* 1082 */     String colorStr = params.get(0);
/* 1083 */     String darkStr = params.get(1);
/* 1084 */     String lightStr = params.get(2);
/* 1085 */     int threshold = (params.size() > 3) ? parsePercentage(params.get(3)) : 43;
/*      */ 
/*      */     
/* 1088 */     ColorUIResource color = (ColorUIResource)parseColorOrFunction(resolver.apply(colorStr), resolver);
/* 1089 */     if (color == null) {
/* 1090 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1095 */     String darkOrLightColor = (ColorFunctions.luma(color) * 100.0F < threshold) ? lightStr : darkStr;
/*      */ 
/*      */     
/* 1098 */     return parseColorOrFunction(resolver.apply(darkOrLightColor), resolver);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static ColorUIResource parseColorOver(List<String> params, Function<String, String> resolver) {
/* 1108 */     String foregroundStr = params.get(0);
/* 1109 */     String backgroundStr = params.get(1);
/*      */ 
/*      */     
/* 1112 */     ColorUIResource foreground = (ColorUIResource)parseColorOrFunction(resolver.apply(foregroundStr), resolver);
/* 1113 */     if (foreground == null || foreground.getAlpha() == 255) {
/* 1114 */       return foreground;
/*      */     }
/*      */     
/* 1117 */     ColorUIResource foreground2 = new ColorUIResource(foreground.getRGB());
/*      */ 
/*      */     
/* 1120 */     ColorUIResource background = (ColorUIResource)parseColorOrFunction(resolver.apply(backgroundStr), resolver);
/* 1121 */     if (background == null) {
/* 1122 */       return foreground2;
/*      */     }
/*      */     
/* 1125 */     float weight = foreground.getAlpha() / 255.0F;
/* 1126 */     return new ColorUIResource(ColorFunctions.mix(foreground2, background, weight));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Object parseFunctionBaseColor(String colorStr, ColorFunctions.ColorFunction function, boolean derived, Function<String, String> resolver) {
/* 1133 */     String resolvedColorStr = resolver.apply(colorStr);
/* 1134 */     ColorUIResource baseColor = (ColorUIResource)parseColorOrFunction(resolvedColorStr, resolver);
/* 1135 */     if (baseColor == null) {
/* 1136 */       return null;
/*      */     }
/*      */     
/* 1139 */     Color newColor = ColorFunctions.applyFunctions(baseColor, new ColorFunctions.ColorFunction[] { function });
/*      */     
/* 1141 */     if (derived) {
/*      */       ColorFunctions.ColorFunction[] functions;
/* 1143 */       if (baseColor instanceof DerivedColor && resolvedColorStr == colorStr) {
/*      */ 
/*      */         
/* 1146 */         ColorFunctions.ColorFunction[] baseFunctions = ((DerivedColor)baseColor).getFunctions();
/* 1147 */         functions = new ColorFunctions.ColorFunction[baseFunctions.length + 1];
/* 1148 */         System.arraycopy(baseFunctions, 0, functions, 0, baseFunctions.length);
/* 1149 */         functions[baseFunctions.length] = function;
/*      */       } else {
/* 1151 */         functions = new ColorFunctions.ColorFunction[] { function };
/*      */       } 
/* 1153 */       return new DerivedColor(newColor, functions);
/*      */     } 
/*      */     
/* 1156 */     return new ColorUIResource(newColor);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Object parseFont(String value) {
/* 1163 */     Object font = fontCache.get(value);
/* 1164 */     if (font != null) {
/* 1165 */       return font;
/*      */     }
/* 1167 */     int style = -1;
/* 1168 */     int styleChange = 0;
/* 1169 */     int absoluteSize = 0;
/* 1170 */     int relativeSize = 0;
/* 1171 */     float scaleSize = 0.0F;
/* 1172 */     List<String> families = null;
/* 1173 */     String baseFontKey = null;
/*      */ 
/*      */     
/* 1176 */     StreamTokenizer st = new StreamTokenizer(new StringReader(value));
/* 1177 */     st.resetSyntax();
/* 1178 */     st.wordChars(33, 255);
/* 1179 */     st.whitespaceChars(0, 32);
/* 1180 */     st.whitespaceChars(44, 44);
/* 1181 */     st.quoteChar(34);
/* 1182 */     st.quoteChar(39);
/*      */     
/*      */     try {
/* 1185 */       while (st.nextToken() != -1) {
/* 1186 */         String param = st.sval;
/* 1187 */         switch (param) {
/*      */           
/*      */           case "normal":
/* 1190 */             style = 0;
/*      */             continue;
/*      */           
/*      */           case "bold":
/* 1194 */             if (style == -1)
/* 1195 */               style = 0; 
/* 1196 */             style |= 0x1;
/*      */             continue;
/*      */           
/*      */           case "italic":
/* 1200 */             if (style == -1)
/* 1201 */               style = 0; 
/* 1202 */             style |= 0x2;
/*      */             continue;
/*      */           case "+bold":
/* 1205 */             styleChange |= 0x1; continue;
/* 1206 */           case "-bold": styleChange |= 0x10000; continue;
/* 1207 */           case "+italic": styleChange |= 0x2; continue;
/* 1208 */           case "-italic": styleChange |= 0x20000;
/*      */             continue;
/*      */         } 
/* 1211 */         char firstChar = param.charAt(0);
/* 1212 */         if (Character.isDigit(firstChar) || firstChar == '+' || firstChar == '-') {
/*      */           
/* 1214 */           if (absoluteSize != 0 || relativeSize != 0 || scaleSize != 0.0F) {
/* 1215 */             throw new IllegalArgumentException("size specified more than once in '" + value + "'");
/*      */           }
/* 1217 */           if (firstChar == '+' || firstChar == '-') {
/* 1218 */             relativeSize = parseInteger(param).intValue(); continue;
/* 1219 */           }  if (param.endsWith("%")) {
/* 1220 */             scaleSize = parseInteger(param.substring(0, param.length() - 1)).intValue() / 100.0F; continue;
/*      */           } 
/* 1222 */           absoluteSize = parseInteger(param).intValue(); continue;
/* 1223 */         }  if (firstChar == '$') {
/*      */           
/* 1225 */           if (baseFontKey != null) {
/* 1226 */             throw new IllegalArgumentException("baseFontKey specified more than once in '" + value + "'");
/*      */           }
/* 1228 */           baseFontKey = param.substring(1);
/*      */           continue;
/*      */         } 
/* 1231 */         if (families == null) {
/* 1232 */           families = Collections.singletonList(param); continue;
/*      */         } 
/* 1234 */         if (families.size() == 1)
/* 1235 */           families = new ArrayList<>(families); 
/* 1236 */         families.add(param);
/*      */       
/*      */       }
/*      */ 
/*      */     
/*      */     }
/* 1242 */     catch (IOException ex) {
/* 1243 */       throw new IllegalArgumentException(ex);
/*      */     } 
/*      */     
/* 1246 */     if (style != -1 && styleChange != 0)
/* 1247 */       throw new IllegalArgumentException("can not mix absolute style (e.g. 'bold') with derived style (e.g. '+italic') in '" + value + "'"); 
/* 1248 */     if (styleChange != 0) {
/* 1249 */       if ((styleChange & 0x1) != 0 && (styleChange & 0x10000) != 0)
/* 1250 */         throw new IllegalArgumentException("can not use '+bold' and '-bold' in '" + value + "'"); 
/* 1251 */       if ((styleChange & 0x2) != 0 && (styleChange & 0x20000) != 0) {
/* 1252 */         throw new IllegalArgumentException("can not use '+italic' and '-italic' in '" + value + "'");
/*      */       }
/*      */     } 
/* 1255 */     font = new FlatLaf.ActiveFont(baseFontKey, families, style, styleChange, absoluteSize, relativeSize, scaleSize);
/* 1256 */     fontCache.put(value, font);
/* 1257 */     return font;
/*      */   }
/*      */   private static int parsePercentage(String value) {
/*      */     int val;
/* 1261 */     if (!value.endsWith("%")) {
/* 1262 */       throw new NumberFormatException("invalid percentage '" + value + "'");
/*      */     }
/*      */     
/*      */     try {
/* 1266 */       val = Integer.parseInt(value.substring(0, value.length() - 1));
/* 1267 */     } catch (NumberFormatException ex) {
/* 1268 */       throw new NumberFormatException("invalid percentage '" + value + "'");
/*      */     } 
/*      */     
/* 1271 */     if (val < 0 || val > 100)
/* 1272 */       throw new IllegalArgumentException("percentage out of range (0-100%) '" + value + "'"); 
/* 1273 */     return val;
/*      */   }
/*      */   
/*      */   private static Boolean parseBoolean(String value) {
/* 1277 */     switch (value) { case "false":
/* 1278 */         return Boolean.valueOf(false);
/* 1279 */       case "true": return Boolean.valueOf(true); }
/*      */     
/* 1281 */     throw new IllegalArgumentException("invalid boolean '" + value + "'");
/*      */   }
/*      */   
/*      */   private static Character parseCharacter(String value) {
/* 1285 */     if (value.length() != 1)
/* 1286 */       throw new IllegalArgumentException("invalid character '" + value + "'"); 
/* 1287 */     return Character.valueOf(value.charAt(0));
/*      */   }
/*      */   
/*      */   private static Integer parseInteger(String value, int min, int max, boolean allowPercentage) {
/* 1291 */     if (allowPercentage && value.endsWith("%")) {
/* 1292 */       int percent = parsePercentage(value);
/* 1293 */       return Integer.valueOf(max * percent / 100);
/*      */     } 
/*      */     
/* 1296 */     Integer integer = parseInteger(value);
/* 1297 */     if (integer.intValue() < min || integer.intValue() > max)
/* 1298 */       throw new NumberFormatException("integer '" + value + "' out of range (" + min + '-' + max + ')'); 
/* 1299 */     return integer;
/*      */   }
/*      */   
/*      */   private static Integer parseInteger(String value) {
/*      */     try {
/* 1304 */       return Integer.valueOf(Integer.parseInt(value));
/* 1305 */     } catch (NumberFormatException ex) {
/* 1306 */       throw new NumberFormatException("invalid integer '" + value + "'");
/*      */     } 
/*      */   }
/*      */   
/*      */   private static Number parseIntegerOrFloat(String value) {
/*      */     try {
/* 1312 */       return Integer.valueOf(Integer.parseInt(value));
/* 1313 */     } catch (NumberFormatException ex) {
/*      */       try {
/* 1315 */         return Float.valueOf(Float.parseFloat(value));
/* 1316 */       } catch (NumberFormatException ex2) {
/* 1317 */         throw new NumberFormatException("invalid integer or float '" + value + "'");
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private static Float parseFloat(String value) {
/*      */     try {
/* 1324 */       return Float.valueOf(Float.parseFloat(value));
/* 1325 */     } catch (NumberFormatException ex) {
/* 1326 */       throw new NumberFormatException("invalid float '" + value + "'");
/*      */     } 
/*      */   }
/*      */   
/*      */   private static UIDefaults.ActiveValue parseScaledInteger(String value) {
/* 1331 */     int val = parseInteger(value).intValue();
/* 1332 */     return t -> Integer.valueOf(UIScale.scale(val));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static UIDefaults.ActiveValue parseScaledFloat(String value) {
/* 1338 */     float val = parseFloat(value).floatValue();
/* 1339 */     return t -> Float.valueOf(UIScale.scale(val));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static UIDefaults.ActiveValue parseScaledInsets(String value) {
/* 1345 */     Insets insets = parseInsets(value);
/* 1346 */     return t -> UIScale.scale(insets);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static UIDefaults.ActiveValue parseScaledDimension(String value) {
/* 1352 */     Dimension dimension = parseDimension(value);
/* 1353 */     return t -> UIScale.scale(dimension);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static Object parseGrayFilter(String value) {
/* 1359 */     List<String> numbers = StringUtils.split(value, ',', true, false);
/*      */     try {
/* 1361 */       int brightness = Integer.parseInt(numbers.get(0));
/* 1362 */       int contrast = Integer.parseInt(numbers.get(1));
/* 1363 */       int alpha = Integer.parseInt(numbers.get(2));
/*      */       
/* 1365 */       return t -> new GrayFilter(brightness, contrast, alpha);
/*      */     
/*      */     }
/* 1368 */     catch (NumberFormatException ex) {
/* 1369 */       throw new IllegalArgumentException("invalid gray filter '" + value + "'");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static List<String> splitFunctionParams(String str, char delim) {
/* 1378 */     ArrayList<String> strs = new ArrayList<>();
/* 1379 */     int nestLevel = 0;
/* 1380 */     int start = 0;
/* 1381 */     int strlen = str.length();
/* 1382 */     for (int i = 0; i < strlen; i++) {
/* 1383 */       char ch = str.charAt(i);
/* 1384 */       if (ch == '(') {
/* 1385 */         nestLevel++;
/* 1386 */       } else if (ch == ')') {
/* 1387 */         nestLevel--;
/* 1388 */       } else if (nestLevel == 0 && ch == delim) {
/* 1389 */         strs.add(StringUtils.substringTrimmed(str, start, i));
/* 1390 */         start = i + 1;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1395 */     String s = StringUtils.substringTrimmed(str, start);
/* 1396 */     if (!s.isEmpty() || !strs.isEmpty()) {
/* 1397 */       strs.add(s);
/*      */     }
/* 1399 */     return strs;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static Object lazyUIManagerGet(String uiKey) {
/* 1407 */     boolean optional = false;
/* 1408 */     if (uiKey.startsWith("?")) {
/* 1409 */       uiKey = uiKey.substring("?".length());
/* 1410 */       optional = true;
/*      */     } 
/*      */     
/* 1413 */     Object value = UIManager.get(uiKey);
/* 1414 */     if (value == null && !optional)
/* 1415 */       LoggingFacade.INSTANCE.logSevere("FlatLaf: '" + uiKey + "' not found in UI defaults.", null); 
/* 1416 */     return value;
/*      */   }
/*      */   
/*      */   private static void throwMissingParametersException(String value) {
/* 1420 */     throw new IllegalArgumentException("missing parameters in function '" + value + "'");
/*      */   }
/*      */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\UIDefaultsLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */