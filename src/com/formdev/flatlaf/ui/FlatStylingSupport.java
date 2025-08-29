/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.FlatLaf;
/*     */ import com.formdev.flatlaf.util.StringUtils;
/*     */ import com.formdev.flatlaf.util.SystemInfo;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.lang.annotation.ElementType;
/*     */ import java.lang.annotation.Repeatable;
/*     */ import java.lang.annotation.Retention;
/*     */ import java.lang.annotation.RetentionPolicy;
/*     */ import java.lang.annotation.Target;
/*     */ import java.lang.invoke.MethodHandles;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import java.util.function.BiFunction;
/*     */ import java.util.function.Predicate;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.border.Border;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FlatStylingSupport
/*     */ {
/*     */   public static Object getStyle(JComponent c) {
/* 124 */     return c.getClientProperty("FlatLaf.style");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Object getStyleClass(JComponent c) {
/* 131 */     return c.getClientProperty("FlatLaf.styleClass");
/*     */   }
/*     */   
/*     */   static boolean hasStyleProperty(JComponent c) {
/* 135 */     return (getStyle(c) != null || getStyleClass(c) != null);
/*     */   }
/*     */   
/*     */   public static Object getResolvedStyle(JComponent c, String type) {
/* 139 */     Object style = getStyle(c);
/* 140 */     Object styleClass = getStyleClass(c);
/* 141 */     Object styleForClasses = getStyleForClasses(styleClass, type);
/* 142 */     return joinStyles(styleForClasses, style);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Object getStyleForClasses(Object styleClass, String type) {
/* 179 */     if (styleClass == null) {
/* 180 */       return null;
/*     */     }
/* 182 */     if (styleClass instanceof String && ((String)styleClass).indexOf(' ') >= 0) {
/* 183 */       styleClass = StringUtils.split((String)styleClass, ' ', true, true);
/*     */     }
/* 185 */     if (styleClass instanceof String)
/* 186 */       return getStyleForClass(((String)styleClass).trim(), type); 
/* 187 */     if (styleClass instanceof String[]) {
/* 188 */       Object style = null;
/* 189 */       for (String cls : (String[])styleClass)
/* 190 */         style = joinStyles(style, getStyleForClass(cls, type)); 
/* 191 */       return style;
/* 192 */     }  if (styleClass instanceof java.util.List) {
/* 193 */       Object style = null;
/* 194 */       for (Object cls : styleClass)
/* 195 */         style = joinStyles(style, getStyleForClass((String)cls, type)); 
/* 196 */       return style;
/*     */     } 
/* 198 */     return null;
/*     */   }
/*     */   
/*     */   private static Object getStyleForClass(String styleClass, String type) {
/* 202 */     return joinStyles(
/* 203 */         UIManager.get("[style]." + styleClass), 
/* 204 */         UIManager.get("[style]" + type + '.' + styleClass));
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
/*     */   public static Object joinStyles(Object style1, Object style2) {
/* 222 */     if (style1 == null)
/* 223 */       return style2; 
/* 224 */     if (style2 == null) {
/* 225 */       return style1;
/*     */     }
/*     */     
/* 228 */     if (style1 instanceof String && style2 instanceof String) {
/* 229 */       return style1 + "; " + style2;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 234 */     Map<String, Object> map1 = (style1 instanceof String) ? parse((String)style1) : (Map<String, Object>)style1;
/* 235 */     if (map1 == null) {
/* 236 */       return style2;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 241 */     Map<String, Object> map2 = (style2 instanceof String) ? parse((String)style2) : (Map<String, Object>)style2;
/* 242 */     if (map2 == null) {
/* 243 */       return style1;
/*     */     }
/*     */     
/* 246 */     Map<String, Object> map = new HashMap<>(map1);
/* 247 */     map.putAll(map2);
/* 248 */     return map;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String concatStyles(String style1, String style2) {
/* 259 */     if (style1 == null)
/* 260 */       return style2; 
/* 261 */     if (style2 == null)
/* 262 */       return style1; 
/* 263 */     return style1 + "; " + style2;
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
/*     */   public static Map<String, Object> parseAndApply(Map<String, Object> oldStyleValues, Object style, BiFunction<String, Object, Object> applyProperty) throws UnknownStyleException, IllegalArgumentException {
/* 286 */     if (oldStyleValues != null) {
/* 287 */       for (Map.Entry<String, Object> e : oldStyleValues.entrySet()) {
/* 288 */         applyProperty.apply(e.getKey(), e.getValue());
/*     */       }
/*     */     }
/*     */     
/* 292 */     if (style == null) {
/* 293 */       return null;
/*     */     }
/* 295 */     if (style instanceof String) {
/*     */       
/* 297 */       String str = (String)style;
/* 298 */       if (StringUtils.isTrimmedEmpty(str)) {
/* 299 */         return null;
/*     */       }
/* 301 */       return applyStyle(parse(str), applyProperty);
/* 302 */     }  if (style instanceof Map) {
/*     */ 
/*     */       
/* 305 */       Map<String, Object> map = (Map<String, Object>)style;
/* 306 */       return applyStyle(map, applyProperty);
/*     */     } 
/* 308 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static Map<String, Object> applyStyle(Map<String, Object> style, BiFunction<String, Object, Object> applyProperty) {
/* 314 */     if (style.isEmpty()) {
/* 315 */       return null;
/*     */     }
/* 317 */     Map<String, Object> oldValues = new HashMap<>();
/* 318 */     for (Map.Entry<String, Object> e : style.entrySet()) {
/* 319 */       String key = e.getKey();
/* 320 */       Object newValue = e.getValue();
/*     */ 
/*     */       
/* 323 */       if (key.startsWith("[")) {
/* 324 */         if ((SystemInfo.isWindows && key.startsWith("[win]")) || (SystemInfo.isMacOS && key
/* 325 */           .startsWith("[mac]")) || (SystemInfo.isLinux && key
/* 326 */           .startsWith("[linux]")) || (key
/* 327 */           .startsWith("[light]") && !FlatLaf.isLafDark()) || (key
/* 328 */           .startsWith("[dark]") && FlatLaf.isLafDark())) {
/*     */ 
/*     */           
/* 331 */           key = key.substring(key.indexOf(']') + 1);
/*     */         } else {
/*     */           continue;
/*     */         } 
/*     */       }
/* 336 */       Object oldValue = applyProperty.apply(key, newValue);
/* 337 */       oldValues.put(key, oldValue);
/*     */     } 
/* 339 */     return oldValues;
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
/*     */   public static Map<String, Object> parse(String style) throws IllegalArgumentException {
/* 353 */     if (style == null || StringUtils.isTrimmedEmpty(style)) {
/* 354 */       return null;
/*     */     }
/* 356 */     Map<String, Object> map = null;
/*     */ 
/*     */     
/* 359 */     for (String part : StringUtils.split(style, ';', true, true)) {
/*     */       
/* 361 */       int sepIndex = part.indexOf(':');
/* 362 */       if (sepIndex < 0) {
/* 363 */         throw new IllegalArgumentException("missing colon in '" + part + "'");
/*     */       }
/*     */       
/* 366 */       String key = StringUtils.substringTrimmed(part, 0, sepIndex);
/* 367 */       String value = StringUtils.substringTrimmed(part, sepIndex + 1);
/* 368 */       if (key.isEmpty())
/* 369 */         throw new IllegalArgumentException("missing key in '" + part + "'"); 
/* 370 */       if (value.isEmpty()) {
/* 371 */         throw new IllegalArgumentException("missing value in '" + part + "'");
/*     */       }
/*     */       
/* 374 */       if (map == null)
/* 375 */         map = new LinkedHashMap<>(); 
/* 376 */       map.put(key, parseValue(key, value));
/*     */     } 
/*     */     
/* 379 */     return map;
/*     */   }
/*     */ 
/*     */   
/*     */   private static Object parseValue(String key, String value) {
/* 384 */     if (value.startsWith("$")) {
/* 385 */       return UIManager.get(value.substring(1));
/*     */     }
/*     */ 
/*     */     
/* 389 */     if (key.startsWith("[")) {
/* 390 */       key = key.substring(key.indexOf(']') + 1);
/*     */     }
/*     */     
/* 393 */     return FlatLaf.parseDefaultsValue(key, value, null);
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
/*     */   public static Object applyToAnnotatedObject(Object obj, String key, Object value) throws UnknownStyleException, IllegalArgumentException {
/* 410 */     String fieldName = keyToFieldName(key);
/*     */     
/* 412 */     return applyToField(obj, fieldName, key, value, field -> {
/*     */           Styleable styleable = field.<Styleable>getAnnotation(Styleable.class);
/* 414 */           return (styleable != null && styleable.dot() == ((fieldName != key)));
/*     */         });
/*     */   }
/*     */   
/*     */   private static String keyToFieldName(String key) {
/* 419 */     int dotIndex = key.indexOf('.');
/* 420 */     if (dotIndex < 0) {
/* 421 */       return key;
/*     */     }
/*     */     
/* 424 */     return key.substring(0, dotIndex) + 
/* 425 */       Character.toUpperCase(key.charAt(dotIndex + 1)) + key
/* 426 */       .substring(dotIndex + 2);
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
/*     */   static Object applyToField(Object obj, String fieldName, String key, Object value) throws UnknownStyleException, IllegalArgumentException {
/* 443 */     return applyToField(obj, fieldName, key, value, null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static Object applyToField(Object obj, String fieldName, String key, Object value, Predicate<Field> predicate) throws UnknownStyleException, IllegalArgumentException {
/* 449 */     Class<?> cls = obj.getClass();
/*     */     
/*     */     while (true)
/*     */     { try {
/* 453 */         Field f = cls.getDeclaredField(fieldName);
/* 454 */         if (predicate == null || predicate.test(f))
/* 455 */           return applyToField(f, obj, value, false); 
/* 456 */       } catch (NoSuchFieldException noSuchFieldException) {}
/*     */ 
/*     */ 
/*     */       
/* 460 */       for (StyleableField styleableField : (StyleableField[])cls.<StyleableField>getAnnotationsByType(StyleableField.class)) {
/* 461 */         if (key.equals(styleableField.key())) {
/* 462 */           return applyToField(getStyleableField(styleableField), obj, value, true);
/*     */         }
/*     */       } 
/* 465 */       cls = cls.getSuperclass();
/* 466 */       if (cls == null) {
/* 467 */         throw new UnknownStyleException(key);
/*     */       }
/* 469 */       if (predicate != null)
/* 470 */       { String superclassName = cls.getName();
/* 471 */         if (superclassName.startsWith("java.") || superclassName.startsWith("javax."))
/* 472 */           break;  }  }  throw new UnknownStyleException(key);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static Object applyToField(Field f, Object obj, Object value, boolean useMethodHandles) {
/* 478 */     checkValidField(f);
/*     */     
/* 480 */     if (useMethodHandles && obj instanceof StyleableLookupProvider) {
/*     */       
/*     */       try {
/* 483 */         MethodHandles.Lookup lookup = ((StyleableLookupProvider)obj).getLookupForStyling();
/*     */ 
/*     */         
/* 486 */         Object oldValue = lookup.unreflectGetter(f).invoke(obj);
/* 487 */         lookup.unreflectSetter(f).invoke(obj, convertToEnum(value, f.getType()));
/* 488 */         return oldValue;
/* 489 */       } catch (Throwable ex) {
/* 490 */         throw newFieldAccessFailed(f, ex);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 496 */       f.setAccessible(true);
/*     */ 
/*     */       
/* 499 */       Object oldValue = f.get(obj);
/* 500 */       f.set(obj, convertToEnum(value, f.getType()));
/* 501 */       return oldValue;
/* 502 */     } catch (IllegalAccessException ex) {
/* 503 */       throw newFieldAccessFailed(f, ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static Object getFieldValue(Field f, Object obj, boolean useMethodHandles) {
/* 508 */     checkValidField(f);
/*     */     
/* 510 */     if (useMethodHandles && obj instanceof StyleableLookupProvider) {
/*     */       
/*     */       try {
/* 513 */         MethodHandles.Lookup lookup = ((StyleableLookupProvider)obj).getLookupForStyling();
/* 514 */         return lookup.unreflectGetter(f).invoke(obj);
/* 515 */       } catch (Throwable ex) {
/* 516 */         throw newFieldAccessFailed(f, ex);
/*     */       } 
/*     */     }
/*     */     
/*     */     try {
/* 521 */       f.setAccessible(true);
/* 522 */       return f.get(obj);
/* 523 */     } catch (IllegalAccessException ex) {
/* 524 */       throw newFieldAccessFailed(f, ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static IllegalArgumentException newFieldAccessFailed(Field f, Throwable ex) {
/* 529 */     return new IllegalArgumentException("failed to access field '" + f.getDeclaringClass().getName() + "." + f.getName() + "'", ex);
/*     */   }
/*     */   
/*     */   private static void checkValidField(Field f) {
/* 533 */     if (!isValidField(f))
/* 534 */       throw new IllegalArgumentException("field '" + f.getDeclaringClass().getName() + "." + f.getName() + "' is final or static"); 
/*     */   }
/*     */   
/*     */   private static boolean isValidField(Field f) {
/* 538 */     int modifiers = f.getModifiers();
/* 539 */     return ((modifiers & 0x18) == 0 && !f.isSynthetic());
/*     */   }
/*     */   
/*     */   private static Field getStyleableField(StyleableField styleableField) {
/* 543 */     String fieldName = styleableField.fieldName();
/* 544 */     if (fieldName.isEmpty()) {
/* 545 */       fieldName = styleableField.key();
/*     */     }
/*     */     try {
/* 548 */       return styleableField.cls().getDeclaredField(fieldName);
/* 549 */     } catch (NoSuchFieldException ex) {
/* 550 */       throw new IllegalArgumentException("field '" + styleableField.cls().getName() + "." + fieldName + "' not found", ex);
/*     */     } 
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
/*     */   private static Object applyToProperty(Object obj, String name, Object value) throws UnknownStyleException, IllegalArgumentException {
/* 570 */     Class<?> cls = obj.getClass();
/* 571 */     String getterName = buildMethodName("get", name);
/* 572 */     String setterName = buildMethodName("set", name);
/*     */     
/*     */     try {
/*     */       Method getter;
/*     */       try {
/* 577 */         getter = cls.getMethod(getterName, new Class[0]);
/* 578 */       } catch (NoSuchMethodException ex) {
/* 579 */         getter = cls.getMethod(buildMethodName("is", name), new Class[0]);
/*     */       } 
/* 581 */       Method setter = cls.getMethod(setterName, new Class[] { getter.getReturnType() });
/* 582 */       Object oldValue = getter.invoke(obj, new Object[0]);
/* 583 */       setter.invoke(obj, new Object[] { convertToEnum(value, getter.getReturnType()) });
/* 584 */       return oldValue;
/* 585 */     } catch (NoSuchMethodException ex) {
/* 586 */       throw new UnknownStyleException(name);
/* 587 */     } catch (Exception ex) {
/* 588 */       throw new IllegalArgumentException("failed to invoke property methods '" + cls.getName() + "." + getterName + "()' or '" + setterName + "(...)'", ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static String buildMethodName(String prefix, String name) {
/* 594 */     int prefixLength = prefix.length();
/* 595 */     int nameLength = name.length();
/* 596 */     char[] chars = new char[prefixLength + nameLength];
/* 597 */     prefix.getChars(0, prefixLength, chars, 0);
/* 598 */     name.getChars(0, nameLength, chars, prefixLength);
/* 599 */     chars[prefixLength] = Character.toUpperCase(chars[prefixLength]);
/* 600 */     return new String(chars);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Object convertToEnum(Object value, Class<?> type) throws IllegalArgumentException {
/* 608 */     if (Enum.class.isAssignableFrom(type) && value instanceof String) {
/*     */       try {
/* 610 */         value = Enum.valueOf(type, (String)value);
/* 611 */       } catch (IllegalArgumentException ex) {
/* 612 */         throw new IllegalArgumentException("unknown enum value '" + value + "' in enum '" + type.getName() + "'", ex);
/*     */       } 
/*     */     }
/* 615 */     return value;
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
/*     */   public static Object applyToAnnotatedObjectOrComponent(Object obj, Object comp, String key, Object value) throws UnknownStyleException, IllegalArgumentException {
/*     */     try {
/* 636 */       return applyToAnnotatedObject(obj, key, value);
/* 637 */     } catch (UnknownStyleException ex) {
/*     */       try {
/* 639 */         if (comp != null)
/* 640 */           return applyToProperty(comp, key, value); 
/* 641 */       } catch (UnknownStyleException unknownStyleException) {}
/*     */ 
/*     */       
/* 644 */       throw ex;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static Object applyToAnnotatedObjectOrBorder(Object obj, String key, Object value, JComponent c, AtomicBoolean borderShared) {
/*     */     try {
/* 652 */       return applyToAnnotatedObject(obj, key, value);
/* 653 */     } catch (UnknownStyleException ex) {
/*     */       
/* 655 */       Border border = c.getBorder();
/* 656 */       if (border instanceof StyleableBorder) {
/* 657 */         if (borderShared.get()) {
/* 658 */           border = cloneBorder(border);
/* 659 */           c.setBorder(border);
/* 660 */           borderShared.set(false);
/*     */         } 
/*     */         
/*     */         try {
/* 664 */           return ((StyleableBorder)border).applyStyleProperty(key, value);
/* 665 */         } catch (UnknownStyleException unknownStyleException) {}
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 672 */         return applyToProperty(c, key, value);
/* 673 */       } catch (UnknownStyleException unknownStyleException) {
/*     */ 
/*     */         
/* 676 */         throw ex;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   static PropertyChangeListener createPropertyChangeListener(JComponent c, Runnable installStyle, PropertyChangeListener superListener) {
/* 683 */     return e -> {
/*     */         if (superListener != null) {
/*     */           superListener.propertyChange(e);
/*     */         }
/*     */         switch (e.getPropertyName()) {
/*     */           case "FlatLaf.style":
/*     */           case "FlatLaf.styleClass":
/*     */             installStyle.run();
/*     */             c.revalidate();
/*     */             c.repaint();
/*     */             break;
/*     */         } 
/*     */       };
/*     */   }
/*     */   
/*     */   static Border cloneBorder(Border border) {
/* 699 */     Class<? extends Border> borderClass = (Class)border.getClass();
/*     */     try {
/* 701 */       return borderClass.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
/* 702 */     } catch (Exception ex) {
/* 703 */       throw new IllegalArgumentException("failed to clone border '" + borderClass.getName() + "'", ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   static Icon cloneIcon(Icon icon) {
/* 708 */     Class<? extends Icon> iconClass = (Class)icon.getClass();
/*     */     try {
/* 710 */       return iconClass.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
/* 711 */     } catch (Exception ex) {
/* 712 */       throw new IllegalArgumentException("failed to clone icon '" + iconClass.getName() + "'", ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Map<String, Class<?>> getAnnotatedStyleableInfos(Object obj) {
/* 721 */     return getAnnotatedStyleableInfos(obj, null);
/*     */   }
/*     */   
/*     */   public static Map<String, Class<?>> getAnnotatedStyleableInfos(Object obj, Border border) {
/* 725 */     Map<String, Class<?>> infos = new StyleableInfosMap<>();
/* 726 */     collectAnnotatedStyleableInfos(obj, infos);
/* 727 */     collectStyleableInfos(border, infos);
/* 728 */     return infos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void collectAnnotatedStyleableInfos(Object obj, Map<String, Class<?>> infos) {
/*     */     String superclassName;
/* 736 */     HashSet<String> processedFields = new HashSet<>();
/* 737 */     Class<?> cls = obj.getClass();
/*     */ 
/*     */     
/*     */     do {
/* 741 */       for (Field f : cls.getDeclaredFields()) {
/* 742 */         if (isValidField(f)) {
/*     */ 
/*     */           
/* 745 */           Styleable styleable = f.<Styleable>getAnnotation(Styleable.class);
/* 746 */           if (styleable != null) {
/*     */ 
/*     */             
/* 749 */             String name = f.getName();
/* 750 */             Class<?> type = f.getType();
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 755 */             if (!processedFields.contains(name)) {
/*     */               
/* 757 */               processedFields.add(name);
/*     */ 
/*     */               
/* 760 */               if (styleable.dot()) {
/* 761 */                 int len = name.length();
/* 762 */                 for (int i = 0; i < len; i++) {
/* 763 */                   if (Character.isUpperCase(name.charAt(i))) {
/*     */ 
/*     */                     
/* 766 */                     name = name.substring(0, i) + '.' + Character.toLowerCase(name.charAt(i)) + name.substring(i + 1);
/*     */                     
/*     */                     break;
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */               
/* 773 */               if (styleable.type() != Void.class) {
/* 774 */                 type = styleable.type();
/*     */               }
/* 776 */               infos.put(name, type);
/*     */             } 
/*     */           } 
/*     */         } 
/* 780 */       }  for (StyleableField styleableField : (StyleableField[])cls.<StyleableField>getAnnotationsByType(StyleableField.class)) {
/* 781 */         String name = styleableField.key();
/*     */ 
/*     */ 
/*     */         
/* 785 */         if (!processedFields.contains(name)) {
/*     */           
/* 787 */           processedFields.add(name);
/*     */           
/* 789 */           Field f = getStyleableField(styleableField);
/* 790 */           infos.put(name, f.getType());
/*     */         } 
/*     */       } 
/* 793 */       cls = cls.getSuperclass();
/* 794 */       if (cls == null) {
/*     */         return;
/*     */       }
/* 797 */       superclassName = cls.getName();
/* 798 */     } while (!superclassName.startsWith("java.") && !superclassName.startsWith("javax."));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void collectStyleableInfos(Border border, Map<String, Class<?>> infos) {
/* 804 */     if (border instanceof StyleableBorder)
/* 805 */       infos.putAll(((StyleableBorder)border).getStyleableInfos()); 
/*     */   }
/*     */   
/*     */   public static void putAllPrefixKey(Map<String, Class<?>> infos, String keyPrefix, Map<String, Class<?>> infos2) {
/* 809 */     for (Map.Entry<String, Class<?>> e : infos2.entrySet())
/* 810 */       infos.put(keyPrefix.concat(e.getKey()), e.getValue()); 
/*     */   } @Target({ElementType.FIELD}) @Retention(RetentionPolicy.RUNTIME) public static @interface Styleable {
/*     */     boolean dot() default false; Class<?> type() default Void.class; } @Target({ElementType.TYPE}) @Retention(RetentionPolicy.RUNTIME)
/*     */   @Repeatable(StyleableFields.class)
/* 814 */   public static @interface StyleableField { Class<?> cls(); String key(); String fieldName() default ""; } public static Object getAnnotatedStyleableValue(Object obj, String key) { String superclassName, fieldName = keyToFieldName(key);
/* 815 */     Class<?> cls = obj.getClass();
/*     */ 
/*     */     
/*     */     do {
/*     */       try {
/* 820 */         Field f = cls.getDeclaredField(fieldName);
/* 821 */         Styleable styleable = f.<Styleable>getAnnotation(Styleable.class);
/* 822 */         if (styleable != null) {
/* 823 */           if (styleable.dot() != ((fieldName != key)))
/* 824 */             throw new IllegalArgumentException("'Styleable.dot' on field '" + fieldName + "' does not match key '" + key + "'"); 
/* 825 */           if (styleable.type() != Void.class) {
/* 826 */             throw new IllegalArgumentException("'Styleable.type' on field '" + fieldName + "' not supported");
/*     */           }
/* 828 */           return getFieldValue(f, obj, false);
/*     */         } 
/* 830 */       } catch (NoSuchFieldException noSuchFieldException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 835 */       for (StyleableField styleableField : (StyleableField[])cls.<StyleableField>getAnnotationsByType(StyleableField.class)) {
/* 836 */         if (key.equals(styleableField.key())) {
/* 837 */           return getFieldValue(getStyleableField(styleableField), obj, true);
/*     */         }
/*     */       } 
/* 840 */       cls = cls.getSuperclass();
/* 841 */       if (cls == null) {
/* 842 */         return null;
/*     */       }
/* 844 */       superclassName = cls.getName();
/* 845 */     } while (!superclassName.startsWith("java.") && !superclassName.startsWith("javax."));
/* 846 */     return null; }
/*     */    @Target({ElementType.TYPE})
/*     */   @Retention(RetentionPolicy.RUNTIME)
/*     */   public static @interface StyleableFields {
/*     */     FlatStylingSupport.StyleableField[] value(); } public static Object getAnnotatedStyleableValue(Object obj, Border border, String key) {
/* 851 */     if (border instanceof StyleableBorder) {
/* 852 */       Object value = ((StyleableBorder)border).getStyleableValue(key);
/* 853 */       if (value != null)
/* 854 */         return value; 
/*     */     } 
/* 856 */     return getAnnotatedStyleableValue(obj, key);
/*     */   } public static interface StyleableUI {
/*     */     Map<String, Class<?>> getStyleableInfos(JComponent param1JComponent); Object getStyleableValue(JComponent param1JComponent, String param1String); } public static interface StyleableBorder {
/*     */     Object applyStyleProperty(String param1String, Object param1Object);
/*     */     Map<String, Class<?>> getStyleableInfos();
/*     */     Object getStyleableValue(String param1String); }
/*     */   public static interface StyleableLookupProvider {
/*     */     MethodHandles.Lookup getLookupForStyling(); }
/*     */   public static class UnknownStyleException extends IllegalArgumentException { public UnknownStyleException(String key) {
/* 865 */       super(key);
/*     */     }
/*     */ 
/*     */     
/*     */     public String getMessage() {
/* 870 */       return "unknown style '" + super.getMessage() + "'";
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class StyleableInfosMap<K, V>
/*     */     extends LinkedHashMap<K, V>
/*     */   {
/*     */     public V put(K key, V value) {
/* 881 */       V oldValue = super.put(key, value);
/* 882 */       if (oldValue != null)
/* 883 */         throw new IllegalArgumentException("duplicate key '" + key + "'"); 
/* 884 */       return oldValue;
/*     */     }
/*     */ 
/*     */     
/*     */     public void putAll(Map<? extends K, ? extends V> m) {
/* 889 */       for (Map.Entry<? extends K, ? extends V> e : m.entrySet())
/* 890 */         put(e.getKey(), e.getValue()); 
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatStylingSupport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */