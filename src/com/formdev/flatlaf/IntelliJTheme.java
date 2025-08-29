/*     */ package com.formdev.flatlaf;
/*     */ 
/*     */ import com.formdev.flatlaf.json.Json;
/*     */ import com.formdev.flatlaf.json.ParseException;
/*     */ import com.formdev.flatlaf.util.ColorFunctions;
/*     */ import com.formdev.flatlaf.util.LoggingFacade;
/*     */ import com.formdev.flatlaf.util.StringUtils;
/*     */ import com.formdev.flatlaf.util.SystemInfo;
/*     */ import java.awt.Color;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.swing.UIDefaults;
/*     */ import javax.swing.plaf.ColorUIResource;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IntelliJTheme
/*     */ {
/*     */   public final String name;
/*     */   public final boolean dark;
/*     */   public final String author;
/*     */   private final boolean isMaterialUILite;
/*     */   private final Map<String, String> colors;
/*     */   private final Map<String, Object> ui;
/*     */   private final Map<String, Object> icons;
/*     */   private Map<String, ColorUIResource> namedColors;
/*     */   
/*     */   public static boolean setup(InputStream in) {
/*     */     try {
/*  81 */       return FlatLaf.setup(createLaf(in));
/*  82 */     } catch (Exception ex) {
/*  83 */       LoggingFacade.INSTANCE.logSevere("FlatLaf: Failed to load IntelliJ theme", ex);
/*  84 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static boolean install(InputStream in) {
/*  93 */     return setup(in);
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
/*     */   public static FlatLaf createLaf(InputStream in) throws IOException {
/* 106 */     return createLaf(new IntelliJTheme(in));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static FlatLaf createLaf(IntelliJTheme theme) {
/* 113 */     return new ThemeLaf(theme);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IntelliJTheme(InputStream in) throws IOException {
/*     */     Map<String, Object> json;
/*     */     this.namedColors = Collections.emptyMap();
/*     */     
/* 127 */     try { Reader reader = new InputStreamReader(in, StandardCharsets.UTF_8); 
/* 128 */       try { json = (Map<String, Object>)Json.parse(reader);
/* 129 */         reader.close(); } catch (Throwable throwable) { try { reader.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (ParseException ex)
/* 130 */     { throw new IOException(ex.getMessage(), ex); }
/*     */ 
/*     */     
/* 133 */     this.name = (String)json.get("name");
/* 134 */     this.dark = Boolean.parseBoolean((String)json.get("dark"));
/* 135 */     this.author = (String)json.get("author");
/*     */     
/* 137 */     this.isMaterialUILite = this.author.equals("Mallowigi");
/*     */     
/* 139 */     this.colors = (Map<String, String>)json.get("colors");
/* 140 */     this.ui = (Map<String, Object>)json.get("ui");
/* 141 */     this.icons = (Map<String, Object>)json.get("icons");
/*     */   }
/*     */   
/*     */   private void applyProperties(UIDefaults defaults) {
/* 145 */     if (this.ui == null) {
/*     */       return;
/*     */     }
/* 148 */     defaults.put("Component.isIntelliJTheme", Boolean.valueOf(true));
/*     */ 
/*     */     
/* 151 */     defaults.put("Button.paintShadow", Boolean.valueOf(true));
/* 152 */     defaults.put("Button.shadowWidth", Integer.valueOf(this.dark ? 2 : 1));
/*     */     
/* 154 */     Map<Object, Object> themeSpecificDefaults = removeThemeSpecificDefaults(defaults);
/*     */     
/* 156 */     loadNamedColors(defaults);
/*     */ 
/*     */     
/* 159 */     ArrayList<Object> defaultsKeysCache = new ArrayList();
/* 160 */     Set<String> uiKeys = new HashSet<>();
/* 161 */     for (Map.Entry<String, Object> e : this.ui.entrySet()) {
/* 162 */       apply(e.getKey(), e.getValue(), defaults, defaultsKeysCache, uiKeys);
/*     */     }
/* 164 */     applyColorPalette(defaults);
/* 165 */     applyCheckBoxColors(defaults);
/*     */ 
/*     */     
/* 168 */     for (Map.Entry<String, String> e : uiKeyCopying.entrySet()) {
/* 169 */       Object value = defaults.get(e.getValue());
/* 170 */       if (value != null) {
/* 171 */         defaults.put(e.getKey(), value);
/*     */       }
/*     */     } 
/*     */     
/* 175 */     Object panelBackground = defaults.get("Panel.background");
/* 176 */     defaults.put("Button.disabledBackground", panelBackground);
/* 177 */     defaults.put("ToggleButton.disabledBackground", panelBackground);
/*     */ 
/*     */     
/* 180 */     copyIfNotSet(defaults, "Button.focusedBorderColor", "Component.focusedBorderColor", uiKeys);
/* 181 */     defaults.put("Button.hoverBorderColor", defaults.get("Button.focusedBorderColor"));
/* 182 */     defaults.put("HelpButton.hoverBorderColor", defaults.get("Button.focusedBorderColor"));
/*     */ 
/*     */     
/* 185 */     Object helpButtonBackground = defaults.get("Button.startBackground");
/* 186 */     Object helpButtonBorderColor = defaults.get("Button.startBorderColor");
/* 187 */     if (helpButtonBackground == null)
/* 188 */       helpButtonBackground = defaults.get("Button.background"); 
/* 189 */     if (helpButtonBorderColor == null)
/* 190 */       helpButtonBorderColor = defaults.get("Button.borderColor"); 
/* 191 */     defaults.put("HelpButton.background", helpButtonBackground);
/* 192 */     defaults.put("HelpButton.borderColor", helpButtonBorderColor);
/* 193 */     defaults.put("HelpButton.disabledBackground", panelBackground);
/* 194 */     defaults.put("HelpButton.disabledBorderColor", defaults.get("Button.disabledBorderColor"));
/* 195 */     defaults.put("HelpButton.focusedBorderColor", defaults.get("Button.focusedBorderColor"));
/* 196 */     defaults.put("HelpButton.focusedBackground", defaults.get("Button.focusedBackground"));
/*     */ 
/*     */     
/* 199 */     defaults.put("ComboBox.editableBackground", defaults.get("TextField.background"));
/* 200 */     defaults.put("Spinner.background", defaults.get("TextField.background"));
/*     */ 
/*     */     
/* 203 */     defaults.put("Spinner.buttonBackground", defaults.get("ComboBox.buttonEditableBackground"));
/* 204 */     defaults.put("Spinner.buttonArrowColor", defaults.get("ComboBox.buttonArrowColor"));
/* 205 */     defaults.put("Spinner.buttonDisabledArrowColor", defaults.get("ComboBox.buttonDisabledArrowColor"));
/*     */ 
/*     */ 
/*     */     
/* 209 */     if (uiKeys.contains("TextField.background")) {
/* 210 */       Object textFieldBackground = defaults.get("TextField.background");
/* 211 */       if (!uiKeys.contains("FormattedTextField.background"))
/* 212 */         defaults.put("FormattedTextField.background", textFieldBackground); 
/* 213 */       if (!uiKeys.contains("PasswordField.background"))
/* 214 */         defaults.put("PasswordField.background", textFieldBackground); 
/* 215 */       if (!uiKeys.contains("EditorPane.background"))
/* 216 */         defaults.put("EditorPane.background", textFieldBackground); 
/* 217 */       if (!uiKeys.contains("TextArea.background"))
/* 218 */         defaults.put("TextArea.background", textFieldBackground); 
/* 219 */       if (!uiKeys.contains("TextPane.background"))
/* 220 */         defaults.put("TextPane.background", textFieldBackground); 
/* 221 */       if (!uiKeys.contains("Spinner.background")) {
/* 222 */         defaults.put("Spinner.background", textFieldBackground);
/*     */       }
/*     */     } 
/*     */     
/* 226 */     if (!uiKeys.contains("ToggleButton.startBackground") && !uiKeys.contains("*.startBackground"))
/* 227 */       defaults.put("ToggleButton.startBackground", defaults.get("Button.startBackground")); 
/* 228 */     if (!uiKeys.contains("ToggleButton.endBackground") && !uiKeys.contains("*.endBackground"))
/* 229 */       defaults.put("ToggleButton.endBackground", defaults.get("Button.endBackground")); 
/* 230 */     if (!uiKeys.contains("ToggleButton.foreground") && uiKeys.contains("Button.foreground")) {
/* 231 */       defaults.put("ToggleButton.foreground", defaults.get("Button.foreground"));
/*     */     }
/*     */     
/* 234 */     Color desktopBackgroundBase = defaults.getColor("Panel.background");
/* 235 */     Color desktopBackground = ColorFunctions.applyFunctions(desktopBackgroundBase, new ColorFunctions.ColorFunction[] { (ColorFunctions.ColorFunction)new ColorFunctions.HSLIncreaseDecrease(2, this.dark, 5.0F, false, true) });
/*     */     
/* 237 */     defaults.put("Desktop.background", new ColorUIResource(desktopBackground));
/*     */ 
/*     */     
/* 240 */     if (this.isMaterialUILite) {
/* 241 */       defaults.put("List.background", defaults.get("Tree.background"));
/* 242 */       defaults.put("Table.background", defaults.get("Tree.background"));
/*     */     } 
/*     */ 
/*     */     
/* 246 */     int rowHeight = defaults.getInt("Tree.rowHeight");
/* 247 */     if (rowHeight > 22) {
/* 248 */       defaults.put("Tree.rowHeight", Integer.valueOf(22));
/*     */     }
/*     */     
/* 251 */     for (Map.Entry<Object, Object> e : themeSpecificDefaults.entrySet()) {
/* 252 */       Object key = e.getKey();
/* 253 */       Object value = e.getValue();
/*     */ 
/*     */       
/* 256 */       if (key instanceof String && ((String)key).startsWith("[style]")) {
/* 257 */         Object oldValue = defaults.get(key);
/* 258 */         if (oldValue != null) {
/* 259 */           value = oldValue + "; " + value;
/*     */         }
/*     */       } 
/* 262 */       defaults.put(key, value);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private Map<Object, Object> removeThemeSpecificDefaults(UIDefaults defaults) {
/* 268 */     ArrayList<String> themeSpecificKeys = new ArrayList<>();
/* 269 */     for (Object key : defaults.keySet()) {
/* 270 */       if (key instanceof String && ((String)key).startsWith("[") && !((String)key).startsWith("[style]")) {
/* 271 */         themeSpecificKeys.add((String)key);
/*     */       }
/*     */     } 
/*     */     
/* 275 */     Map<Object, Object> themeSpecificDefaults = new HashMap<>();
/* 276 */     String currentThemePrefix = '[' + this.name.replace(' ', '_') + ']';
/* 277 */     String currentThemeAndAuthorPrefix = '[' + this.name.replace(' ', '_') + "---" + this.author.replace(' ', '_') + ']';
/* 278 */     String currentAuthorPrefix = "[author-" + this.author.replace(' ', '_') + ']';
/* 279 */     String allThemesPrefix = "[*]";
/* 280 */     String[] prefixes = { currentThemePrefix, currentThemeAndAuthorPrefix, currentAuthorPrefix, allThemesPrefix };
/* 281 */     for (String key : themeSpecificKeys) {
/* 282 */       Object value = defaults.remove(key);
/* 283 */       for (String prefix : prefixes) {
/* 284 */         if (key.startsWith(prefix)) {
/* 285 */           themeSpecificDefaults.put(key.substring(prefix.length()), value);
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 291 */     return themeSpecificDefaults;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void loadNamedColors(UIDefaults defaults) {
/* 298 */     if (this.colors == null) {
/*     */       return;
/*     */     }
/* 301 */     this.namedColors = new HashMap<>();
/*     */     
/* 303 */     for (Map.Entry<String, String> e : this.colors.entrySet()) {
/* 304 */       String value = e.getValue();
/* 305 */       ColorUIResource color = parseColor(value);
/* 306 */       if (color != null) {
/* 307 */         String key = e.getKey();
/* 308 */         this.namedColors.put(key, color);
/* 309 */         defaults.put("ColorPalette." + key, color);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void apply(String key, Object value, UIDefaults defaults, ArrayList<Object> defaultsKeysCache, Set<String> uiKeys) {
/* 319 */     if (value instanceof Map) {
/* 320 */       Map<String, Object> map = (Map<String, Object>)value;
/* 321 */       if (map.containsKey("os.default") || map.containsKey("os.windows") || map.containsKey("os.mac") || map.containsKey("os.linux"))
/*     */       
/*     */       { 
/* 324 */         String osKey = SystemInfo.isWindows ? "os.windows" : (SystemInfo.isMacOS ? "os.mac" : (SystemInfo.isLinux ? "os.linux" : null));
/* 325 */         if (osKey != null && map.containsKey(osKey)) {
/* 326 */           apply(key, map.get(osKey), defaults, defaultsKeysCache, uiKeys);
/* 327 */         } else if (map.containsKey("os.default")) {
/* 328 */           apply(key, map.get("os.default"), defaults, defaultsKeysCache, uiKeys);
/*     */         }  }
/* 330 */       else { for (Map.Entry<String, Object> e : map.entrySet())
/* 331 */           apply(key + '.' + (String)e.getKey(), e.getValue(), defaults, defaultsKeysCache, uiKeys);  }
/*     */     
/*     */     } else {
/* 334 */       if ("".equals(value)) {
/*     */         return;
/*     */       }
/* 337 */       uiKeys.add(key);
/*     */ 
/*     */       
/* 340 */       if (key.endsWith(".border") || key
/* 341 */         .endsWith(".rowHeight") || key
/* 342 */         .equals("ComboBox.padding") || key
/* 343 */         .equals("Spinner.padding") || key
/* 344 */         .equals("Tree.leftChildIndent") || key
/* 345 */         .equals("Tree.rightChildIndent")) {
/*     */         return;
/*     */       }
/*     */       
/* 349 */       key = uiKeyMapping.getOrDefault(key, key);
/* 350 */       if (key.isEmpty()) {
/*     */         return;
/*     */       }
/* 353 */       String valueStr = value.toString();
/*     */ 
/*     */       
/* 356 */       Object uiValue = this.namedColors.get(valueStr);
/*     */ 
/*     */       
/* 359 */       if (uiValue == null) {
/*     */         
/* 361 */         if (!valueStr.startsWith("#") && (key.endsWith("ground") || key.endsWith("Color"))) {
/* 362 */           valueStr = fixColorIfValid("#" + valueStr, valueStr);
/* 363 */         } else if (valueStr.startsWith("##")) {
/* 364 */           valueStr = fixColorIfValid(valueStr.substring(1), valueStr);
/* 365 */         } else if (key.endsWith(".border") || key.endsWith("Border")) {
/* 366 */           List<String> parts = StringUtils.split(valueStr, ',');
/* 367 */           if (parts.size() == 5 && !((String)parts.get(4)).startsWith("#")) {
/* 368 */             parts.set(4, "#" + (String)parts.get(4));
/* 369 */             valueStr = String.join(",", (Iterable)parts);
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/*     */         try {
/* 375 */           uiValue = UIDefaultsLoader.parseValue(key, valueStr, null);
/* 376 */         } catch (RuntimeException ex) {
/* 377 */           UIDefaultsLoader.logParseError(key, valueStr, ex, false);
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 382 */       if (key.startsWith("*.")) {
/*     */         
/* 384 */         String tail = key.substring(1);
/*     */ 
/*     */ 
/*     */         
/* 388 */         if (defaultsKeysCache.size() != defaults.size()) {
/* 389 */           defaultsKeysCache.clear();
/* 390 */           Enumeration<Object> e = defaults.keys();
/* 391 */           while (e.hasMoreElements()) {
/* 392 */             defaultsKeysCache.add(e.nextElement());
/*     */           }
/*     */         } 
/*     */         
/* 396 */         for (Object k : defaultsKeysCache) {
/* 397 */           if (k.equals("Desktop.background") || k
/* 398 */             .equals("DesktopIcon.background")) {
/*     */             continue;
/*     */           }
/* 401 */           if (k instanceof String) {
/*     */ 
/*     */ 
/*     */             
/* 405 */             String km = uiKeyInverseMapping.getOrDefault(k, (String)k);
/* 406 */             if (km.endsWith(tail) && !((String)k).startsWith("CheckBox.icon."))
/* 407 */               defaults.put(k, uiValue); 
/*     */           } 
/*     */         } 
/*     */       } else {
/* 411 */         defaults.put(key, uiValue);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private String fixColorIfValid(String newColorStr, String colorStr) {
/*     */     try {
/* 418 */       UIDefaultsLoader.parseColorRGBA(newColorStr);
/*     */       
/* 420 */       return newColorStr;
/* 421 */     } catch (IllegalArgumentException ex) {
/* 422 */       return colorStr;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void applyColorPalette(UIDefaults defaults) {
/* 427 */     if (this.icons == null) {
/*     */       return;
/*     */     }
/* 430 */     Object palette = this.icons.get("ColorPalette");
/* 431 */     if (!(palette instanceof Map)) {
/*     */       return;
/*     */     }
/*     */     
/* 435 */     Map<String, Object> colorPalette = (Map<String, Object>)palette;
/* 436 */     for (Map.Entry<String, Object> e : colorPalette.entrySet()) {
/* 437 */       String key = e.getKey();
/* 438 */       Object value = e.getValue();
/* 439 */       if (key.startsWith("Checkbox.") || !(value instanceof String)) {
/*     */         continue;
/*     */       }
/* 442 */       if (this.dark) {
/* 443 */         key = StringUtils.removeTrailing(key, ".Dark");
/*     */       }
/* 445 */       ColorUIResource color = toColor((String)value);
/* 446 */       if (color != null) {
/* 447 */         defaults.put(key, color);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private ColorUIResource toColor(String value) {
/* 453 */     ColorUIResource color = this.namedColors.get(value);
/*     */ 
/*     */     
/* 456 */     return (color != null) ? color : parseColor(value);
/*     */   }
/*     */   
/*     */   private ColorUIResource parseColor(String value) {
/*     */     try {
/* 461 */       return UIDefaultsLoader.parseColor(value);
/* 462 */     } catch (IllegalArgumentException ex) {
/* 463 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void applyCheckBoxColors(UIDefaults defaults) {
/* 473 */     if (this.icons == null) {
/*     */       return;
/*     */     }
/* 476 */     Object palette = this.icons.get("ColorPalette");
/* 477 */     if (!(palette instanceof Map)) {
/*     */       return;
/*     */     }
/* 480 */     boolean checkboxModified = false;
/*     */     
/* 482 */     Map<String, Object> colorPalette = (Map<String, Object>)palette;
/* 483 */     for (Map.Entry<String, Object> e : colorPalette.entrySet()) {
/* 484 */       String key = e.getKey();
/* 485 */       Object value = e.getValue();
/* 486 */       if (!key.startsWith("Checkbox.") || !(value instanceof String)) {
/*     */         continue;
/*     */       }
/* 489 */       if (key.equals("Checkbox.Background.Default") || key
/* 490 */         .equals("Checkbox.Foreground.Selected"))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 496 */         value = "#ffffff";
/*     */       }
/*     */       
/* 499 */       String key2 = checkboxDuplicateColors.get(key);
/*     */       
/* 501 */       if (this.dark) {
/* 502 */         key = StringUtils.removeTrailing(key, ".Dark");
/*     */       }
/* 504 */       String newKey = checkboxKeyMapping.get(key);
/* 505 */       if (newKey != null) {
/* 506 */         String checkBoxIconPrefix = "CheckBox.icon.";
/* 507 */         if (!this.dark && newKey.startsWith(checkBoxIconPrefix)) {
/* 508 */           newKey = "CheckBox.icon[filled].".concat(newKey.substring(checkBoxIconPrefix.length()));
/*     */         }
/* 510 */         ColorUIResource color = toColor((String)value);
/* 511 */         if (color != null) {
/* 512 */           defaults.put(newKey, color);
/*     */           
/* 514 */           if (key2 != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 527 */             if (this.dark) {
/* 528 */               key2 = StringUtils.removeTrailing(key2, ".Dark");
/*     */             }
/* 530 */             String newKey2 = checkboxKeyMapping.get(key2);
/* 531 */             if (newKey2 != null) {
/* 532 */               defaults.put(newKey2, color);
/*     */             }
/*     */           } 
/*     */         } 
/* 536 */         checkboxModified = true;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 541 */     if (checkboxModified) {
/*     */       
/* 543 */       defaults.remove("CheckBox.icon.focusWidth");
/* 544 */       defaults.put("CheckBox.icon.hoverBorderColor", defaults.get("CheckBox.icon.focusedBorderColor"));
/*     */ 
/*     */       
/* 547 */       defaults.remove("CheckBox.icon[filled].focusWidth");
/* 548 */       defaults.put("CheckBox.icon[filled].hoverBorderColor", defaults.get("CheckBox.icon[filled].focusedBorderColor"));
/* 549 */       defaults.put("CheckBox.icon[filled].focusedSelectedBackground", defaults.get("CheckBox.icon[filled].selectedBackground"));
/*     */       
/* 551 */       if (this.dark) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 556 */         String[] focusedBorderColorKeys = { "CheckBox.icon.focusedBorderColor", "CheckBox.icon.focusedSelectedBorderColor", "CheckBox.icon[filled].focusedBorderColor", "CheckBox.icon[filled].focusedSelectedBorderColor" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 562 */         for (String key : focusedBorderColorKeys) {
/* 563 */           Color color = defaults.getColor(key);
/* 564 */           if (color != null) {
/* 565 */             defaults.put(key, new ColorUIResource(new Color(color
/* 566 */                     .getRGB() & 0xFFFFFF | 0xA6000000, true)));
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void copyIfNotSet(UIDefaults defaults, String destKey, String srcKey, Set<String> uiKeys) {
/* 574 */     if (!uiKeys.contains(destKey)) {
/* 575 */       defaults.put(destKey, defaults.get(srcKey));
/*     */     }
/*     */   }
/*     */   
/* 579 */   private static final Map<String, String> uiKeyMapping = new HashMap<>();
/*     */   
/* 581 */   private static final Map<String, String> uiKeyCopying = new HashMap<>();
/* 582 */   private static final Map<String, String> uiKeyInverseMapping = new HashMap<>();
/* 583 */   private static final Map<String, String> checkboxKeyMapping = new HashMap<>();
/* 584 */   private static final Map<String, String> checkboxDuplicateColors = new HashMap<>();
/*     */ 
/*     */   
/*     */   static {
/* 588 */     uiKeyMapping.put("ComboBox.background", "");
/* 589 */     uiKeyMapping.put("ComboBox.nonEditableBackground", "ComboBox.background");
/* 590 */     uiKeyMapping.put("ComboBox.ArrowButton.background", "ComboBox.buttonEditableBackground");
/* 591 */     uiKeyMapping.put("ComboBox.ArrowButton.disabledIconColor", "ComboBox.buttonDisabledArrowColor");
/* 592 */     uiKeyMapping.put("ComboBox.ArrowButton.iconColor", "ComboBox.buttonArrowColor");
/* 593 */     uiKeyMapping.put("ComboBox.ArrowButton.nonEditableBackground", "ComboBox.buttonBackground");
/* 594 */     uiKeyCopying.put("ComboBox.buttonSeparatorColor", "Component.borderColor");
/* 595 */     uiKeyCopying.put("ComboBox.buttonDisabledSeparatorColor", "Component.disabledBorderColor");
/*     */ 
/*     */     
/* 598 */     uiKeyMapping.put("Component.inactiveErrorFocusColor", "Component.error.borderColor");
/* 599 */     uiKeyMapping.put("Component.errorFocusColor", "Component.error.focusedBorderColor");
/* 600 */     uiKeyMapping.put("Component.inactiveWarningFocusColor", "Component.warning.borderColor");
/* 601 */     uiKeyMapping.put("Component.warningFocusColor", "Component.warning.focusedBorderColor");
/*     */ 
/*     */     
/* 604 */     uiKeyMapping.put("Link.activeForeground", "Component.linkColor");
/*     */ 
/*     */     
/* 607 */     uiKeyMapping.put("Menu.border", "Menu.margin");
/* 608 */     uiKeyMapping.put("MenuItem.border", "MenuItem.margin");
/* 609 */     uiKeyCopying.put("CheckBoxMenuItem.margin", "MenuItem.margin");
/* 610 */     uiKeyCopying.put("RadioButtonMenuItem.margin", "MenuItem.margin");
/* 611 */     uiKeyMapping.put("PopupMenu.border", "PopupMenu.borderInsets");
/* 612 */     uiKeyCopying.put("MenuItem.underlineSelectionColor", "TabbedPane.underlineColor");
/*     */ 
/*     */     
/* 615 */     uiKeyCopying.put("Menu.selectionBackground", "List.selectionBackground");
/* 616 */     uiKeyCopying.put("MenuItem.selectionBackground", "List.selectionBackground");
/* 617 */     uiKeyCopying.put("CheckBoxMenuItem.selectionBackground", "List.selectionBackground");
/* 618 */     uiKeyCopying.put("RadioButtonMenuItem.selectionBackground", "List.selectionBackground");
/*     */ 
/*     */     
/* 621 */     uiKeyMapping.put("ProgressBar.background", "");
/* 622 */     uiKeyMapping.put("ProgressBar.foreground", "");
/* 623 */     uiKeyMapping.put("ProgressBar.trackColor", "ProgressBar.background");
/* 624 */     uiKeyMapping.put("ProgressBar.progressColor", "ProgressBar.foreground");
/* 625 */     uiKeyCopying.put("ProgressBar.selectionForeground", "ProgressBar.background");
/* 626 */     uiKeyCopying.put("ProgressBar.selectionBackground", "ProgressBar.foreground");
/*     */ 
/*     */     
/* 629 */     uiKeyMapping.put("ScrollBar.trackColor", "ScrollBar.track");
/* 630 */     uiKeyMapping.put("ScrollBar.thumbColor", "ScrollBar.thumb");
/*     */ 
/*     */     
/* 633 */     uiKeyMapping.put("Separator.separatorColor", "Separator.foreground");
/*     */ 
/*     */     
/* 636 */     uiKeyMapping.put("Slider.trackWidth", "");
/* 637 */     uiKeyCopying.put("Slider.trackValueColor", "ProgressBar.foreground");
/* 638 */     uiKeyCopying.put("Slider.thumbColor", "ProgressBar.foreground");
/* 639 */     uiKeyCopying.put("Slider.trackColor", "ProgressBar.background");
/*     */ 
/*     */     
/* 642 */     uiKeyCopying.put("Spinner.buttonSeparatorColor", "Component.borderColor");
/* 643 */     uiKeyCopying.put("Spinner.buttonDisabledSeparatorColor", "Component.disabledBorderColor");
/*     */ 
/*     */     
/* 646 */     uiKeyCopying.put("TabbedPane.selectedBackground", "DefaultTabs.underlinedTabBackground");
/* 647 */     uiKeyCopying.put("TabbedPane.selectedForeground", "DefaultTabs.underlinedTabForeground");
/* 648 */     uiKeyCopying.put("TabbedPane.inactiveUnderlineColor", "DefaultTabs.inactiveUnderlineColor");
/*     */ 
/*     */     
/* 651 */     uiKeyCopying.put("TitlePane.inactiveBackground", "TitlePane.background");
/* 652 */     uiKeyMapping.put("TitlePane.infoForeground", "TitlePane.foreground");
/* 653 */     uiKeyMapping.put("TitlePane.inactiveInfoForeground", "TitlePane.inactiveForeground");
/*     */     
/* 655 */     for (Map.Entry<String, String> e : uiKeyMapping.entrySet()) {
/* 656 */       uiKeyInverseMapping.put(e.getValue(), e.getKey());
/*     */     }
/* 658 */     uiKeyCopying.put("ToggleButton.tab.underlineColor", "TabbedPane.underlineColor");
/* 659 */     uiKeyCopying.put("ToggleButton.tab.disabledUnderlineColor", "TabbedPane.disabledUnderlineColor");
/* 660 */     uiKeyCopying.put("ToggleButton.tab.selectedBackground", "TabbedPane.selectedBackground");
/* 661 */     uiKeyCopying.put("ToggleButton.tab.hoverBackground", "TabbedPane.hoverColor");
/* 662 */     uiKeyCopying.put("ToggleButton.tab.focusBackground", "TabbedPane.focusColor");
/*     */     
/* 664 */     checkboxKeyMapping.put("Checkbox.Background.Default", "CheckBox.icon.background");
/* 665 */     checkboxKeyMapping.put("Checkbox.Background.Disabled", "CheckBox.icon.disabledBackground");
/* 666 */     checkboxKeyMapping.put("Checkbox.Border.Default", "CheckBox.icon.borderColor");
/* 667 */     checkboxKeyMapping.put("Checkbox.Border.Disabled", "CheckBox.icon.disabledBorderColor");
/* 668 */     checkboxKeyMapping.put("Checkbox.Focus.Thin.Default", "CheckBox.icon.focusedBorderColor");
/* 669 */     checkboxKeyMapping.put("Checkbox.Focus.Wide", "CheckBox.icon.focusColor");
/* 670 */     checkboxKeyMapping.put("Checkbox.Foreground.Disabled", "CheckBox.icon.disabledCheckmarkColor");
/* 671 */     checkboxKeyMapping.put("Checkbox.Background.Selected", "CheckBox.icon.selectedBackground");
/* 672 */     checkboxKeyMapping.put("Checkbox.Border.Selected", "CheckBox.icon.selectedBorderColor");
/* 673 */     checkboxKeyMapping.put("Checkbox.Foreground.Selected", "CheckBox.icon.checkmarkColor");
/* 674 */     checkboxKeyMapping.put("Checkbox.Focus.Thin.Selected", "CheckBox.icon.focusedSelectedBorderColor");
/*     */     
/* 676 */     checkboxDuplicateColors.put("Checkbox.Background.Default.Dark", "Checkbox.Background.Selected.Dark");
/* 677 */     checkboxDuplicateColors.put("Checkbox.Border.Default.Dark", "Checkbox.Border.Selected.Dark");
/* 678 */     checkboxDuplicateColors.put("Checkbox.Focus.Thin.Default.Dark", "Checkbox.Focus.Thin.Selected.Dark");
/*     */     
/* 680 */     Map.Entry[] arrayOfEntry = (Map.Entry[])checkboxDuplicateColors.entrySet().toArray((Object[])new Map.Entry[checkboxDuplicateColors.size()]);
/* 681 */     for (Map.Entry<String, String> e : arrayOfEntry) {
/* 682 */       checkboxDuplicateColors.put(e.getValue(), e.getKey());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static class ThemeLaf
/*     */     extends FlatLaf
/*     */   {
/*     */     private final IntelliJTheme theme;
/*     */     
/*     */     public ThemeLaf(IntelliJTheme theme) {
/* 693 */       this.theme = theme;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 698 */       return this.theme.name;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getDescription() {
/* 703 */       return getName();
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isDark() {
/* 708 */       return this.theme.dark;
/*     */     }
/*     */     
/*     */     public IntelliJTheme getTheme() {
/* 712 */       return this.theme;
/*     */     }
/*     */ 
/*     */     
/*     */     void applyAdditionalDefaults(UIDefaults defaults) {
/* 717 */       this.theme.applyProperties(defaults);
/*     */     }
/*     */ 
/*     */     
/*     */     protected ArrayList<Class<?>> getLafClassesForDefaultsLoading() {
/* 722 */       ArrayList<Class<?>> lafClasses = new ArrayList<>();
/* 723 */       lafClasses.add(FlatLaf.class);
/* 724 */       lafClasses.add(this.theme.dark ? FlatDarkLaf.class : FlatLightLaf.class);
/* 725 */       lafClasses.add(this.theme.dark ? FlatDarculaLaf.class : FlatIntelliJLaf.class);
/* 726 */       lafClasses.add(ThemeLaf.class);
/* 727 */       return lafClasses;
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\IntelliJTheme.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */