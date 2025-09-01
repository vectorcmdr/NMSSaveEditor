package com.formdev.flatlaf;

import com.formdev.flatlaf.ui.FlatEmptyBorder;
import com.formdev.flatlaf.ui.FlatLineBorder;
import com.formdev.flatlaf.util.ColorFunctions;
import com.formdev.flatlaf.util.DerivedColor;
import com.formdev.flatlaf.util.GrayFilter;
import com.formdev.flatlaf.util.HSLColor;
import com.formdev.flatlaf.util.LoggingFacade;
import com.formdev.flatlaf.util.SoftCache;
import com.formdev.flatlaf.util.StringUtils;
import com.formdev.flatlaf.util.SystemInfo;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.function.Function;
import javax.swing.Icon;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UIDefaults.ActiveValue;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.plaf.InsetsUIResource;

class UIDefaultsLoader {
   private static final String TYPE_PREFIX = "{";
   private static final String TYPE_PREFIX_END = "}";
   private static final String VARIABLE_PREFIX = "@";
   private static final String PROPERTY_PREFIX = "$";
   private static final String OPTIONAL_PREFIX = "?";
   private static final String WILDCARD_PREFIX = "*.";
   static final String KEY_VARIABLES = "FlatLaf.internal.variables";
   private static int parseColorDepth;
   private static Map<String, ColorUIResource> systemColorCache;
   private static final SoftCache<String, Object> fontCache = new SoftCache();
   private static final UIDefaultsLoader.ValueType[] tempResultValueType = new UIDefaultsLoader.ValueType[1];
   private static Map<Class<?>, UIDefaultsLoader.ValueType> javaValueTypes;
   private static Map<String, UIDefaultsLoader.ValueType> knownValueTypes;

   static void loadDefaultsFromProperties(Class<?> lookAndFeelClass, List<FlatDefaultsAddon> addons, Properties additionalDefaults, boolean dark, UIDefaults defaults) {
      ArrayList<Class<?>> lafClasses = new ArrayList();

      for(Class lafClass = lookAndFeelClass; FlatLaf.class.isAssignableFrom(lafClass); lafClass = lafClass.getSuperclass()) {
         lafClasses.add(0, lafClass);
      }

      loadDefaultsFromProperties((List)lafClasses, addons, additionalDefaults, dark, defaults);
   }

   static void loadDefaultsFromProperties(List<Class<?>> lafClasses, List<FlatDefaultsAddon> addons, Properties additionalDefaults, boolean dark, UIDefaults defaults) {
      try {
         systemColorCache = FlatLaf.getSystemColorGetter() != null ? new HashMap() : null;
         Properties properties = new Properties();
         Iterator var6 = lafClasses.iterator();

         while(var6.hasNext()) {
            Class<?> lafClass = (Class)var6.next();
            String propertiesName = '/' + lafClass.getName().replace('.', '/') + ".properties";
            InputStream in = lafClass.getResourceAsStream(propertiesName);

            try {
               if (in != null) {
                  properties.load(in);
               }
            } catch (Throwable var31) {
               if (in != null) {
                  try {
                     in.close();
                  } catch (Throwable var23) {
                     var31.addSuppressed(var23);
                  }
               }

               throw var31;
            }

            if (in != null) {
               in.close();
            }
         }

         var6 = addons.iterator();

         while(var6.hasNext()) {
            FlatDefaultsAddon addon = (FlatDefaultsAddon)var6.next();
            Iterator var37 = lafClasses.iterator();

            while(var37.hasNext()) {
               Class<?> lafClass = (Class)var37.next();
               InputStream in = addon.getDefaults(lafClass);

               try {
                  if (in != null) {
                     properties.load(in);
                  }
               } catch (Throwable var30) {
                  if (in != null) {
                     try {
                        in.close();
                     } catch (Throwable var22) {
                        var30.addSuppressed(var22);
                     }
                  }

                  throw var30;
               }

               if (in != null) {
                  in.close();
               }
            }
         }

         List<ClassLoader> addonClassLoaders = new ArrayList();
         Iterator var35 = addons.iterator();

         while(var35.hasNext()) {
            FlatDefaultsAddon addon = (FlatDefaultsAddon)var35.next();
            ClassLoader addonClassLoader = addon.getClass().getClassLoader();
            if (!addonClassLoaders.contains(addonClassLoader)) {
               addonClassLoaders.add(addonClassLoader);
            }
         }

         List<Object> customDefaultsSources = FlatLaf.getCustomDefaultsSources();
         int size = customDefaultsSources != null ? customDefaultsSources.size() : 0;

         Iterator var12;
         String platformPrefix;
         String wildcardKey;
         for(int i = 0; i < size; ++i) {
            Object source = customDefaultsSources.get(i);
            if (source instanceof String && i + 1 < size) {
               platformPrefix = (String)source;
               ++i;
               ClassLoader classLoader = (ClassLoader)customDefaultsSources.get(i);
               if (classLoader != null && !addonClassLoaders.contains(classLoader)) {
                  addonClassLoaders.add(classLoader);
               }

               platformPrefix = platformPrefix.replace('.', '/');
               if (classLoader == null) {
                  classLoader = FlatLaf.class.getClassLoader();
               }

               Iterator var54 = lafClasses.iterator();

               while(var54.hasNext()) {
                  Class<?> lafClass = (Class)var54.next();
                  wildcardKey = platformPrefix + '/' + lafClass.getSimpleName() + ".properties";
                  InputStream in = classLoader.getResourceAsStream(wildcardKey);

                  try {
                     if (in != null) {
                        properties.load(in);
                     }
                  } catch (Throwable var29) {
                     if (in != null) {
                        try {
                           in.close();
                        } catch (Throwable var21) {
                           var29.addSuppressed(var21);
                        }
                     }

                     throw var29;
                  }

                  if (in != null) {
                     in.close();
                  }
               }
            } else {
               Class lafClass;
               if (source instanceof URL) {
                  URL packageUrl = (URL)source;
                  var12 = lafClasses.iterator();

                  while(var12.hasNext()) {
                     lafClass = (Class)var12.next();
                     URL propertiesUrl = new URL(packageUrl + lafClass.getSimpleName() + ".properties");

                     try {
                        InputStream in = propertiesUrl.openStream();

                        try {
                           properties.load(in);
                        } catch (Throwable var27) {
                           if (in != null) {
                              try {
                                 in.close();
                              } catch (Throwable var26) {
                                 var27.addSuppressed(var26);
                              }
                           }

                           throw var27;
                        }

                        if (in != null) {
                           in.close();
                        }
                     } catch (FileNotFoundException var28) {
                     }
                  }
               } else if (source instanceof File) {
                  File folder = (File)source;
                  var12 = lafClasses.iterator();

                  while(var12.hasNext()) {
                     lafClass = (Class)var12.next();
                     File propertiesFile = new File(folder, lafClass.getSimpleName() + ".properties");
                     if (propertiesFile.isFile()) {
                        FileInputStream in = new FileInputStream(propertiesFile);

                        try {
                           properties.load(in);
                        } catch (Throwable var25) {
                           try {
                              in.close();
                           } catch (Throwable var20) {
                              var25.addSuppressed(var20);
                           }

                           throw var25;
                        }

                        in.close();
                     }
                  }
               }
            }
         }

         if (additionalDefaults != null) {
            properties.putAll(additionalDefaults);
         }

         ArrayList<String> platformSpecificKeys = new ArrayList();
         Iterator var44 = properties.keySet().iterator();

         while(true) {
            String key;
            do {
               do {
                  if (!var44.hasNext()) {
                     Iterator it;
                     String key;
                     if (!platformSpecificKeys.isEmpty()) {
                        String lightOrDarkPrefix = dark ? "[dark]" : "[light]";
                        it = platformSpecificKeys.iterator();

                        while(it.hasNext()) {
                           key = (String)it.next();
                           if (key.startsWith(lightOrDarkPrefix)) {
                              properties.put(key.substring(lightOrDarkPrefix.length()), properties.remove(key));
                           }
                        }

                        platformPrefix = SystemInfo.isWindows ? "[win]" : (SystemInfo.isMacOS ? "[mac]" : (SystemInfo.isLinux ? "[linux]" : "[unknown]"));
                        var12 = platformSpecificKeys.iterator();

                        while(var12.hasNext()) {
                           key = (String)var12.next();
                           Object value = properties.remove(key);
                           if (key.startsWith(platformPrefix)) {
                              properties.put(key.substring(platformPrefix.length()), value);
                           }
                        }
                     }

                     HashMap<String, String> wildcards = new HashMap();
                     it = properties.entrySet().iterator();

                     while(it.hasNext()) {
                        Entry<Object, Object> e = (Entry)it.next();
                        key = (String)e.getKey();
                        if (key.startsWith("*.")) {
                           wildcards.put(key.substring("*.".length()), (String)e.getValue());
                           it.remove();
                        }
                     }

                     var12 = defaults.keySet().iterator();

                     while(var12.hasNext()) {
                        Object key = var12.next();
                        int dot;
                        if (key instanceof String && !properties.containsKey(key) && (dot = ((String)key).lastIndexOf(46)) >= 0) {
                           wildcardKey = ((String)key).substring(dot + 1);
                           String wildcardValue = (String)wildcards.get(wildcardKey);
                           if (wildcardValue != null) {
                              properties.put(key, wildcardValue);
                           }
                        }
                     }

                     Function<String, String> propertiesGetter = (keyx) -> {
                        return properties.getProperty(keyx);
                     };
                     Function<String, String> resolver = (valuex) -> {
                        return resolveValue(valuex, propertiesGetter);
                     };
                     Map<String, String> variables = new HashMap(50);
                     Iterator var67 = properties.entrySet().iterator();

                     while(var67.hasNext()) {
                        Entry<Object, Object> e = (Entry)var67.next();
                        String key = (String)e.getKey();
                        if (key.startsWith("@")) {
                           variables.put(key, (String)e.getValue());
                        } else {
                           String value = resolveValue((String)e.getValue(), propertiesGetter);

                           try {
                              defaults.put(key, parseValue(key, value, (Class)null, (UIDefaultsLoader.ValueType[])null, resolver, addonClassLoaders));
                           } catch (RuntimeException var24) {
                              logParseError(key, value, var24, true);
                           }
                        }
                     }

                     defaults.put("FlatLaf.internal.variables", variables);
                     systemColorCache = null;
                     return;
                  }

                  Object okey = var44.next();
                  key = (String)okey;
               } while(!key.startsWith("["));
            } while(!key.startsWith("[win]") && !key.startsWith("[mac]") && !key.startsWith("[linux]") && !key.startsWith("[light]") && !key.startsWith("[dark]"));

            platformSpecificKeys.add(key);
         }
      } catch (IOException var32) {
         LoggingFacade.INSTANCE.logSevere("FlatLaf: Failed to load properties files.", var32);
      }
   }

   static void logParseError(String key, String value, RuntimeException ex, boolean severe) {
      String message = "FlatLaf: Failed to parse: '" + key + '=' + value + '\'';
      if (severe) {
         LoggingFacade.INSTANCE.logSevere(message, ex);
      } else {
         LoggingFacade.INSTANCE.logConfig(message, ex);
      }

   }

   static String resolveValue(String value, Function<String, String> propertiesGetter) {
      value = value.trim();
      String value0 = value;
      if (value.startsWith("$")) {
         value = value.substring("$".length());
      } else if (!value.startsWith("@")) {
         return value;
      }

      boolean optional = false;
      if (value.startsWith("?")) {
         value = value.substring("?".length());
         optional = true;
      }

      String newValue = (String)propertiesGetter.apply(value);
      if (newValue == null) {
         if (optional) {
            return "null";
         } else {
            throw new IllegalArgumentException("variable or property '" + value + "' not found");
         }
      } else if (newValue.equals(value0)) {
         throw new IllegalArgumentException("endless recursion in variable or property '" + value + "'");
      } else {
         return resolveValue(newValue, propertiesGetter);
      }
   }

   static String resolveValueFromUIManager(String value) {
      if (value.startsWith("@")) {
         Map<String, String> variables = (Map)UIManager.get("FlatLaf.internal.variables");
         String newValue = variables != null ? (String)variables.get(value) : null;
         if (newValue == null) {
            throw new IllegalArgumentException("variable '" + value + "' not found");
         } else {
            return resolveValueFromUIManager(newValue);
         }
      } else if (!value.startsWith("$")) {
         return value;
      } else {
         String key = value.substring("$".length());
         Object newValue = UIManager.get(key);
         if (newValue == null) {
            throw new IllegalArgumentException("property '" + key + "' not found");
         } else if (newValue instanceof Color) {
            Color color = (Color)newValue;
            int alpha = color.getAlpha();
            return String.format(alpha != 255 ? "#%06x%02x" : "#%06x", color.getRGB() & 16777215, alpha);
         } else {
            throw new IllegalArgumentException("property value type '" + newValue.getClass().getName() + "' not supported in references");
         }
      }
   }

   static Object parseValue(String key, String value, Class<?> valueType) {
      return parseValue(key, value, valueType, (UIDefaultsLoader.ValueType[])null, (v) -> {
         return v;
      }, Collections.emptyList());
   }

   static Object parseValue(String key, String value, Class<?> javaValueType, UIDefaultsLoader.ValueType[] resultValueType, Function<String, String> resolver, List<ClassLoader> addonClassLoaders) {
      if (resultValueType == null) {
         resultValueType = tempResultValueType;
      }

      if (key.startsWith("[style]")) {
         resultValueType[0] = UIDefaultsLoader.ValueType.STRING;
         return value;
      } else {
         value = value.trim();
         if (!value.equals("null") && !value.isEmpty()) {
            String typeStr;
            if (value.startsWith("if(") && value.endsWith(")")) {
               List<String> params = splitFunctionParams(value.substring(3, value.length() - 1), ',');
               if (params.size() != 3) {
                  throwMissingParametersException(value);
               }

               boolean ifCondition = parseCondition((String)params.get(0), resolver, addonClassLoaders);
               typeStr = (String)params.get(ifCondition ? 1 : 2);
               return parseValue(key, (String)resolver.apply(typeStr), javaValueType, resultValueType, resolver, addonClassLoaders);
            } else {
               UIDefaultsLoader.ValueType valueType = UIDefaultsLoader.ValueType.UNKNOWN;
               if (javaValueType != null) {
                  if (javaValueTypes == null) {
                     javaValueTypes = new HashMap();
                     javaValueTypes.put(String.class, UIDefaultsLoader.ValueType.STRING);
                     javaValueTypes.put(Boolean.TYPE, UIDefaultsLoader.ValueType.BOOLEAN);
                     javaValueTypes.put(Boolean.class, UIDefaultsLoader.ValueType.BOOLEAN);
                     javaValueTypes.put(Character.TYPE, UIDefaultsLoader.ValueType.CHARACTER);
                     javaValueTypes.put(Character.class, UIDefaultsLoader.ValueType.CHARACTER);
                     javaValueTypes.put(Integer.TYPE, UIDefaultsLoader.ValueType.INTEGER);
                     javaValueTypes.put(Integer.class, UIDefaultsLoader.ValueType.INTEGER);
                     javaValueTypes.put(Float.TYPE, UIDefaultsLoader.ValueType.FLOAT);
                     javaValueTypes.put(Float.class, UIDefaultsLoader.ValueType.FLOAT);
                     javaValueTypes.put(Border.class, UIDefaultsLoader.ValueType.BORDER);
                     javaValueTypes.put(Icon.class, UIDefaultsLoader.ValueType.ICON);
                     javaValueTypes.put(Insets.class, UIDefaultsLoader.ValueType.INSETS);
                     javaValueTypes.put(Dimension.class, UIDefaultsLoader.ValueType.DIMENSION);
                     javaValueTypes.put(Color.class, UIDefaultsLoader.ValueType.COLOR);
                     javaValueTypes.put(Font.class, UIDefaultsLoader.ValueType.FONT);
                  }

                  valueType = (UIDefaultsLoader.ValueType)javaValueTypes.get(javaValueType);
                  if (valueType == null) {
                     throw new IllegalArgumentException("unsupported value type '" + javaValueType.getName() + "'");
                  }

                  if (valueType == UIDefaultsLoader.ValueType.STRING && value.startsWith("\"") && value.endsWith("\"")) {
                     value = value.substring(1, value.length() - 1);
                  }
               } else {
                  byte var8 = -1;
                  switch(value.hashCode()) {
                  case 3569038:
                     if (value.equals("true")) {
                        var8 = 1;
                     }
                     break;
                  case 97196323:
                     if (value.equals("false")) {
                        var8 = 0;
                     }
                  }

                  switch(var8) {
                  case 0:
                     resultValueType[0] = UIDefaultsLoader.ValueType.BOOLEAN;
                     return false;
                  case 1:
                     resultValueType[0] = UIDefaultsLoader.ValueType.BOOLEAN;
                     return true;
                  }

                  if (value.startsWith("lazy(") && value.endsWith(")")) {
                     resultValueType[0] = UIDefaultsLoader.ValueType.LAZY;
                     String uiKey = StringUtils.substringTrimmed(value, 5, value.length() - 1);
                     return (t) -> {
                        return lazyUIManagerGet(uiKey);
                     };
                  }

                  if (value.startsWith("#")) {
                     valueType = UIDefaultsLoader.ValueType.COLOR;
                  } else if (value.startsWith("{")) {
                     int end = value.indexOf("}");
                     if (end != -1) {
                        try {
                           typeStr = value.substring("{".length(), end);
                           valueType = UIDefaultsLoader.ValueType.valueOf(typeStr.toUpperCase(Locale.ENGLISH));
                           value = value.substring(end + "}".length());
                        } catch (IllegalArgumentException var9) {
                        }
                     }
                  }

                  if (valueType == UIDefaultsLoader.ValueType.UNKNOWN) {
                     if (knownValueTypes == null) {
                        knownValueTypes = new HashMap();
                        knownValueTypes.put("activeCaptionBorder", UIDefaultsLoader.ValueType.COLOR);
                        knownValueTypes.put("inactiveCaptionBorder", UIDefaultsLoader.ValueType.COLOR);
                        knownValueTypes.put("windowBorder", UIDefaultsLoader.ValueType.COLOR);
                        knownValueTypes.put("SplitPane.dividerSize", UIDefaultsLoader.ValueType.INTEGER);
                        knownValueTypes.put("SplitPaneDivider.gripDotSize", UIDefaultsLoader.ValueType.INTEGER);
                        knownValueTypes.put("dividerSize", UIDefaultsLoader.ValueType.INTEGER);
                        knownValueTypes.put("gripDotSize", UIDefaultsLoader.ValueType.INTEGER);
                        knownValueTypes.put("TabbedPane.closeCrossPlainSize", UIDefaultsLoader.ValueType.FLOAT);
                        knownValueTypes.put("TabbedPane.closeCrossFilledSize", UIDefaultsLoader.ValueType.FLOAT);
                        knownValueTypes.put("closeCrossPlainSize", UIDefaultsLoader.ValueType.FLOAT);
                        knownValueTypes.put("closeCrossFilledSize", UIDefaultsLoader.ValueType.FLOAT);
                        knownValueTypes.put("Table.intercellSpacing", UIDefaultsLoader.ValueType.DIMENSION);
                        knownValueTypes.put("intercellSpacing", UIDefaultsLoader.ValueType.DIMENSION);
                     }

                     valueType = (UIDefaultsLoader.ValueType)knownValueTypes.getOrDefault(key, UIDefaultsLoader.ValueType.UNKNOWN);
                  }

                  if (valueType == UIDefaultsLoader.ValueType.UNKNOWN) {
                     if (key.endsWith("UI")) {
                        valueType = UIDefaultsLoader.ValueType.STRING;
                     } else if (!key.endsWith("Color") && (!key.endsWith("ground") || !key.endsWith(".background") && !key.endsWith("Background") && !key.equals("background") && !key.endsWith(".foreground") && !key.endsWith("Foreground") && !key.equals("foreground"))) {
                        if (!key.endsWith(".font") && !key.endsWith("Font") && !key.equals("font")) {
                           if (!key.endsWith(".border") && !key.endsWith("Border") && !key.equals("border")) {
                              if (!key.endsWith(".icon") && !key.endsWith("Icon") && !key.equals("icon")) {
                                 if (!key.endsWith(".margin") && !key.equals("margin") && !key.endsWith(".padding") && !key.equals("padding") && !key.endsWith("Margins") && !key.endsWith("Insets")) {
                                    if (key.endsWith("Size")) {
                                       valueType = UIDefaultsLoader.ValueType.DIMENSION;
                                    } else if (!key.endsWith("Width") && !key.endsWith("Height")) {
                                       if (key.endsWith("Char")) {
                                          valueType = UIDefaultsLoader.ValueType.CHARACTER;
                                       } else if (key.endsWith("grayFilter")) {
                                          valueType = UIDefaultsLoader.ValueType.GRAYFILTER;
                                       }
                                    } else {
                                       valueType = UIDefaultsLoader.ValueType.INTEGERORFLOAT;
                                    }
                                 } else {
                                    valueType = UIDefaultsLoader.ValueType.INSETS;
                                 }
                              } else {
                                 valueType = UIDefaultsLoader.ValueType.ICON;
                              }
                           } else {
                              valueType = UIDefaultsLoader.ValueType.BORDER;
                           }
                        } else {
                           valueType = UIDefaultsLoader.ValueType.FONT;
                        }
                     } else {
                        valueType = UIDefaultsLoader.ValueType.COLOR;
                     }
                  }
               }

               resultValueType[0] = valueType;
               switch(valueType) {
               case STRING:
                  return value;
               case BOOLEAN:
                  return parseBoolean(value);
               case CHARACTER:
                  return parseCharacter(value);
               case INTEGER:
                  return parseInteger(value);
               case INTEGERORFLOAT:
                  return parseIntegerOrFloat(value);
               case FLOAT:
                  return parseFloat(value);
               case BORDER:
                  return parseBorder(value, resolver, addonClassLoaders);
               case ICON:
                  return parseInstance(value, addonClassLoaders);
               case INSETS:
                  return parseInsets(value);
               case DIMENSION:
                  return parseDimension(value);
               case COLOR:
                  return parseColorOrFunction(value, resolver);
               case FONT:
                  return parseFont(value);
               case SCALEDINTEGER:
                  return parseScaledInteger(value);
               case SCALEDFLOAT:
                  return parseScaledFloat(value);
               case SCALEDINSETS:
                  return parseScaledInsets(value);
               case SCALEDDIMENSION:
                  return parseScaledDimension(value);
               case INSTANCE:
                  return parseInstance(value, addonClassLoaders);
               case CLASS:
                  return parseClass(value, addonClassLoaders);
               case GRAYFILTER:
                  return parseGrayFilter(value);
               case UNKNOWN:
               default:
                  if (value.startsWith("\"") && value.endsWith("\"")) {
                     resultValueType[0] = UIDefaultsLoader.ValueType.STRING;
                     return value.substring(1, value.length() - 1);
                  } else if (!value.startsWith("#") && !value.endsWith(")")) {
                     char firstChar = value.charAt(0);
                     if (firstChar >= '0' && firstChar <= '9' || firstChar == '-' || firstChar == '+' || firstChar == '.') {
                        try {
                           Integer integer = parseInteger(value);
                           resultValueType[0] = UIDefaultsLoader.ValueType.INTEGER;
                           return integer;
                        } catch (NumberFormatException var11) {
                           try {
                              Float f = parseFloat(value);
                              resultValueType[0] = UIDefaultsLoader.ValueType.FLOAT;
                              return f;
                           } catch (NumberFormatException var10) {
                           }
                        }
                     }

                     resultValueType[0] = UIDefaultsLoader.ValueType.STRING;
                     return value;
                  } else {
                     Object color = parseColorOrFunction(value, resolver);
                     resultValueType[0] = color != null ? UIDefaultsLoader.ValueType.COLOR : UIDefaultsLoader.ValueType.NULL;
                     return color;
                  }
               }
            }
         } else {
            resultValueType[0] = UIDefaultsLoader.ValueType.NULL;
            return null;
         }
      }
   }

   private static boolean parseCondition(String condition, Function<String, String> resolver, List<ClassLoader> addonClassLoaders) {
      try {
         Object conditionValue = parseValue("", (String)resolver.apply(condition), (Class)null, (UIDefaultsLoader.ValueType[])null, resolver, addonClassLoaders);
         return conditionValue != null && !conditionValue.equals(false) && !conditionValue.equals(0);
      } catch (IllegalArgumentException var4) {
         return false;
      }
   }

   private static Object parseBorder(String value, Function<String, String> resolver, List<ClassLoader> addonClassLoaders) {
      if (value.indexOf(44) >= 0) {
         List<String> parts = splitFunctionParams(value, ',');
         Insets insets = parseInsets(value);
         ColorUIResource lineColor = parts.size() >= 5 ? (ColorUIResource)parseColorOrFunction((String)resolver.apply((String)parts.get(4)), resolver) : null;
         float lineThickness = parts.size() >= 6 && !((String)parts.get(5)).isEmpty() ? parseFloat((String)parts.get(5)) : 1.0F;
         int arc = parts.size() >= 7 ? parseInteger((String)parts.get(6)) : 0;
         return (t) -> {
            return lineColor != null ? new FlatLineBorder(insets, lineColor, lineThickness, arc) : new FlatEmptyBorder(insets);
         };
      } else {
         return parseInstance(value, addonClassLoaders);
      }
   }

   private static Object parseInstance(String value, List<ClassLoader> addonClassLoaders) {
      return (t) -> {
         try {
            return findClass(value, addonClassLoaders).getDeclaredConstructor().newInstance();
         } catch (Exception var4) {
            LoggingFacade.INSTANCE.logSevere("FlatLaf: Failed to instantiate '" + value + "'.", var4);
            return null;
         }
      };
   }

   private static Object parseClass(String value, List<ClassLoader> addonClassLoaders) {
      return (t) -> {
         try {
            return findClass(value, addonClassLoaders);
         } catch (ClassNotFoundException var4) {
            LoggingFacade.INSTANCE.logSevere("FlatLaf: Failed to find class '" + value + "'.", var4);
            return null;
         }
      };
   }

   private static Class<?> findClass(String className, List<ClassLoader> addonClassLoaders) throws ClassNotFoundException {
      try {
         return Class.forName(className);
      } catch (ClassNotFoundException var7) {
         Iterator var3 = addonClassLoaders.iterator();

         while(var3.hasNext()) {
            ClassLoader addonClassLoader = (ClassLoader)var3.next();

            try {
               return addonClassLoader.loadClass(className);
            } catch (ClassNotFoundException var6) {
            }
         }

         throw var7;
      }
   }

   private static Insets parseInsets(String value) {
      List numbers = StringUtils.split(value, ',', true, false);

      try {
         return new InsetsUIResource(Integer.parseInt((String)numbers.get(0)), Integer.parseInt((String)numbers.get(1)), Integer.parseInt((String)numbers.get(2)), Integer.parseInt((String)numbers.get(3)));
      } catch (NumberFormatException var3) {
         throw new IllegalArgumentException("invalid insets '" + value + "'");
      }
   }

   private static Dimension parseDimension(String value) {
      List numbers = StringUtils.split(value, ',', true, false);

      try {
         return new DimensionUIResource(Integer.parseInt((String)numbers.get(0)), Integer.parseInt((String)numbers.get(1)));
      } catch (NumberFormatException var3) {
         throw new IllegalArgumentException("invalid size '" + value + "'");
      }
   }

   private static Object parseColorOrFunction(String value, Function<String, String> resolver) {
      return value.endsWith(")") ? parseColorFunctions(value, resolver) : parseColor(value);
   }

   static ColorUIResource parseColor(String value) {
      int rgba = parseColorRGBA(value);
      return (rgba & -16777216) == -16777216 ? new ColorUIResource(rgba) : new ColorUIResource(new Color(rgba, true));
   }

   static int parseColorRGBA(String value) {
      int len = value.length();
      if ((len == 4 || len == 5 || len == 7 || len == 9) && value.charAt(0) == '#') {
         int n = 0;

         int n1;
         int digit;
         for(n1 = 1; n1 < len; ++n1) {
            char ch = value.charAt(n1);
            if (ch >= '0' && ch <= '9') {
               digit = ch - 48;
            } else if (ch >= 'a' && ch <= 'f') {
               digit = ch - 97 + 10;
            } else {
               if (ch < 'A' || ch > 'F') {
                  throw newInvalidColorException(value);
               }

               digit = ch - 65 + 10;
            }

            n = n << 4 | digit;
         }

         if (len <= 5) {
            n1 = n & '\uf000';
            int n2 = n & 3840;
            digit = n & 240;
            int n4 = n & 15;
            n = n1 << 16 | n1 << 12 | n2 << 12 | n2 << 8 | digit << 8 | digit << 4 | n4 << 4 | n4;
         }

         return len != 4 && len != 7 ? n >> 8 & 16777215 | (n & 255) << 24 : -16777216 | n;
      } else {
         throw newInvalidColorException(value);
      }
   }

   private static IllegalArgumentException newInvalidColorException(String value) {
      return new IllegalArgumentException("invalid color '" + value + "'");
   }

   private static Object parseColorFunctions(String value, Function<String, String> resolver) {
      int paramsStart = value.indexOf(40);
      if (paramsStart < 0) {
         throw new IllegalArgumentException("missing opening parenthesis in function '" + value + "'");
      } else {
         String function = StringUtils.substringTrimmed(value, 0, paramsStart);
         List<String> params = splitFunctionParams(value.substring(paramsStart + 1, value.length() - 1), ',');
         if (params.isEmpty()) {
            throwMissingParametersException(value);
         }

         if (parseColorDepth > 100) {
            throw new IllegalArgumentException("endless recursion in color function '" + value + "'");
         } else {
            ++parseColorDepth;

            try {
               byte var6 = -1;
               switch(function.hashCode()) {
               case -2114203985:
                  if (function.equals("saturate")) {
                     var6 = 8;
                  }
                  break;
               case -1682408562:
                  if (function.equals("changeAlpha")) {
                     var6 = 17;
                  }
                  break;
               case -1561144716:
                  if (function.equals("systemColor")) {
                     var6 = 1;
                  }
                  break;
               case -1338968417:
                  if (function.equals("darken")) {
                     var6 = 7;
                  }
                  break;
               case -1282132831:
                  if (function.equals("fadein")) {
                     var6 = 10;
                  }
                  break;
               case -1091405998:
                  if (function.equals("fadeout")) {
                     var6 = 11;
                  }
                  break;
               case -566947070:
                  if (function.equals("contrast")) {
                     var6 = 21;
                  }
                  break;
               case 3357:
                  if (function.equals("if")) {
                     var6 = 0;
                  }
                  break;
               case 103617:
                  if (function.equals("hsl")) {
                     var6 = 4;
                  }
                  break;
               case 108124:
                  if (function.equals("mix")) {
                     var6 = 18;
                  }
                  break;
               case 112845:
                  if (function.equals("rgb")) {
                     var6 = 2;
                  }
                  break;
               case 3135100:
                  if (function.equals("fade")) {
                     var6 = 12;
                  }
                  break;
               case 3212224:
                  if (function.equals("hsla")) {
                     var6 = 5;
                  }
                  break;
               case 3423444:
                  if (function.equals("over")) {
                     var6 = 22;
                  }
                  break;
               case 3498292:
                  if (function.equals("rgba")) {
                     var6 = 3;
                  }
                  break;
               case 3536962:
                  if (function.equals("spin")) {
                     var6 = 13;
                  }
                  break;
               case 3560187:
                  if (function.equals("tint")) {
                     var6 = 19;
                  }
                  break;
               case 109399597:
                  if (function.equals("shade")) {
                     var6 = 20;
                  }
                  break;
               case 170546239:
                  if (function.equals("lighten")) {
                     var6 = 6;
                  }
                  break;
               case 229314466:
                  if (function.equals("changeSaturation")) {
                     var6 = 15;
                  }
                  break;
               case 424179357:
                  if (function.equals("changeLightness")) {
                     var6 = 16;
                  }
                  break;
               case 1455237928:
                  if (function.equals("changeHue")) {
                     var6 = 14;
                  }
                  break;
               case 1839974960:
                  if (function.equals("desaturate")) {
                     var6 = 9;
                  }
               }

               ColorUIResource var7;
               Object var11;
               switch(var6) {
               case 0:
                  var11 = parseColorIf(value, params, resolver);
                  return var11;
               case 1:
                  var11 = parseColorSystemColor(value, params, resolver);
                  return var11;
               case 2:
                  var7 = parseColorRgbOrRgba(false, params, resolver);
                  return var7;
               case 3:
                  var7 = parseColorRgbOrRgba(true, params, resolver);
                  return var7;
               case 4:
                  var7 = parseColorHslOrHsla(false, params);
                  return var7;
               case 5:
                  var7 = parseColorHslOrHsla(true, params);
                  return var7;
               case 6:
                  var11 = parseColorHSLIncreaseDecrease(2, true, params, resolver);
                  return var11;
               case 7:
                  var11 = parseColorHSLIncreaseDecrease(2, false, params, resolver);
                  return var11;
               case 8:
                  var11 = parseColorHSLIncreaseDecrease(1, true, params, resolver);
                  return var11;
               case 9:
                  var11 = parseColorHSLIncreaseDecrease(1, false, params, resolver);
                  return var11;
               case 10:
                  var11 = parseColorHSLIncreaseDecrease(3, true, params, resolver);
                  return var11;
               case 11:
                  var11 = parseColorHSLIncreaseDecrease(3, false, params, resolver);
                  return var11;
               case 12:
                  var11 = parseColorFade(params, resolver);
                  return var11;
               case 13:
                  var11 = parseColorSpin(params, resolver);
                  return var11;
               case 14:
                  var11 = parseColorChange(0, params, resolver);
                  return var11;
               case 15:
                  var11 = parseColorChange(1, params, resolver);
                  return var11;
               case 16:
                  var11 = parseColorChange(2, params, resolver);
                  return var11;
               case 17:
                  var11 = parseColorChange(3, params, resolver);
                  return var11;
               case 18:
                  var11 = parseColorMix((String)null, params, resolver);
                  return var11;
               case 19:
                  var11 = parseColorMix("#fff", params, resolver);
                  return var11;
               case 20:
                  var11 = parseColorMix("#000", params, resolver);
                  return var11;
               case 21:
                  var11 = parseColorContrast(params, resolver);
                  return var11;
               case 22:
                  var7 = parseColorOver(params, resolver);
                  return var7;
               default:
                  throw new IllegalArgumentException("unknown color function '" + value + "'");
               }
            } finally {
               --parseColorDepth;
            }
         }
      }
   }

   private static Object parseColorIf(String value, List<String> params, Function<String, String> resolver) {
      if (params.size() != 3) {
         throwMissingParametersException(value);
      }

      boolean ifCondition = parseCondition((String)params.get(0), resolver, Collections.emptyList());
      String ifValue = (String)params.get(ifCondition ? 1 : 2);
      return parseColorOrFunction((String)resolver.apply(ifValue), resolver);
   }

   private static Object parseColorSystemColor(String value, List<String> params, Function<String, String> resolver) {
      if (params.size() < 1) {
         throwMissingParametersException(value);
      }

      ColorUIResource systemColor = getSystemColor((String)params.get(0));
      if (systemColor != null) {
         return systemColor;
      } else {
         String defaultValue = params.size() > 1 ? (String)params.get(1) : "";
         return !defaultValue.equals("null") && !defaultValue.isEmpty() ? parseColorOrFunction((String)resolver.apply(defaultValue), resolver) : null;
      }
   }

   private static ColorUIResource getSystemColor(String name) {
      Function<String, Color> systemColorGetter = FlatLaf.getSystemColorGetter();
      if (systemColorGetter == null) {
         return null;
      } else if (systemColorCache != null && systemColorCache.containsKey(name)) {
         return (ColorUIResource)systemColorCache.get(name);
      } else {
         Color color = (Color)systemColorGetter.apply(name);
         ColorUIResource uiColor = color != null ? new ColorUIResource(color) : null;
         if (systemColorCache != null) {
            systemColorCache.put(name, uiColor);
         }

         return uiColor;
      }
   }

   private static ColorUIResource parseColorRgbOrRgba(boolean hasAlpha, List<String> params, Function<String, String> resolver) {
      int green;
      if (hasAlpha && params.size() == 2) {
         String colorStr = (String)params.get(0);
         green = parseInteger((String)params.get(1), 0, 255, true);
         ColorUIResource color = (ColorUIResource)parseColorOrFunction((String)resolver.apply(colorStr), resolver);
         return new ColorUIResource(new Color((green & 255) << 24 | color.getRGB() & 16777215, true));
      } else {
         int red = parseInteger((String)params.get(0), 0, 255, true);
         green = parseInteger((String)params.get(1), 0, 255, true);
         int blue = parseInteger((String)params.get(2), 0, 255, true);
         int alpha = hasAlpha ? parseInteger((String)params.get(3), 0, 255, true) : 255;
         return hasAlpha ? new ColorUIResource(new Color(red, green, blue, alpha)) : new ColorUIResource(red, green, blue);
      }
   }

   private static ColorUIResource parseColorHslOrHsla(boolean hasAlpha, List<String> params) {
      int hue = parseInteger((String)params.get(0), 0, 360, false);
      int saturation = parsePercentage((String)params.get(1));
      int lightness = parsePercentage((String)params.get(2));
      int alpha = hasAlpha ? parsePercentage((String)params.get(3)) : 100;
      float[] hsl = new float[]{(float)hue, (float)saturation, (float)lightness};
      return new ColorUIResource(HSLColor.toRGB(hsl, (float)alpha / 100.0F));
   }

   private static Object parseColorHSLIncreaseDecrease(int hslIndex, boolean increase, List<String> params, Function<String, String> resolver) {
      String colorStr = (String)params.get(0);
      int amount = parsePercentage((String)params.get(1));
      boolean relative = false;
      boolean autoInverse = false;
      boolean lazy = false;
      boolean derived = false;
      if (params.size() > 2) {
         String options = (String)params.get(2);
         relative = options.contains("relative");
         autoInverse = options.contains("autoInverse");
         lazy = options.contains("lazy");
         derived = options.contains("derived");
         if (derived && !options.contains("noAutoInverse")) {
            autoInverse = true;
         }
      }

      ColorFunctions.ColorFunction function = new ColorFunctions.HSLIncreaseDecrease(hslIndex, increase, (float)amount, relative, autoInverse);
      return lazy ? (t) -> {
         Object color = lazyUIManagerGet(colorStr);
         return color instanceof Color ? new ColorUIResource(ColorFunctions.applyFunctions((Color)color, function)) : null;
      } : parseFunctionBaseColor(colorStr, function, derived, resolver);
   }

   private static Object parseColorFade(List<String> params, Function<String, String> resolver) {
      String colorStr = (String)params.get(0);
      int amount = parsePercentage((String)params.get(1));
      boolean derived = false;
      boolean lazy = false;
      if (params.size() > 2) {
         String options = (String)params.get(2);
         derived = options.contains("derived");
         lazy = options.contains("lazy");
      }

      ColorFunctions.ColorFunction function = new ColorFunctions.Fade((float)amount);
      return lazy ? (t) -> {
         Object color = lazyUIManagerGet(colorStr);
         return color instanceof Color ? new ColorUIResource(ColorFunctions.applyFunctions((Color)color, function)) : null;
      } : parseFunctionBaseColor(colorStr, function, derived, resolver);
   }

   private static Object parseColorSpin(List<String> params, Function<String, String> resolver) {
      String colorStr = (String)params.get(0);
      int amount = parseInteger((String)params.get(1));
      boolean derived = false;
      if (params.size() > 2) {
         String options = (String)params.get(2);
         derived = options.contains("derived");
      }

      ColorFunctions.ColorFunction function = new ColorFunctions.HSLIncreaseDecrease(0, true, (float)amount, false, false);
      return parseFunctionBaseColor(colorStr, function, derived, resolver);
   }

   private static Object parseColorChange(int hslIndex, List<String> params, Function<String, String> resolver) {
      String colorStr = (String)params.get(0);
      int value = hslIndex == 0 ? parseInteger((String)params.get(1)) : parsePercentage((String)params.get(1));
      boolean derived = false;
      if (params.size() > 2) {
         String options = (String)params.get(2);
         derived = options.contains("derived");
      }

      ColorFunctions.ColorFunction function = new ColorFunctions.HSLChange(hslIndex, (float)value);
      return parseFunctionBaseColor(colorStr, function, derived, resolver);
   }

   private static Object parseColorMix(String color1Str, List<String> params, Function<String, String> resolver) {
      int i = 0;
      if (color1Str == null) {
         color1Str = (String)params.get(i++);
      }

      String color2Str = (String)params.get(i++);
      int weight = params.size() > i ? parsePercentage((String)params.get(i)) : 50;
      ColorUIResource color2 = (ColorUIResource)parseColorOrFunction((String)resolver.apply(color2Str), resolver);
      if (color2 == null) {
         return null;
      } else {
         ColorFunctions.ColorFunction function = new ColorFunctions.Mix(color2, (float)weight);
         return parseFunctionBaseColor(color1Str, function, false, resolver);
      }
   }

   private static Object parseColorContrast(List<String> params, Function<String, String> resolver) {
      String colorStr = (String)params.get(0);
      String darkStr = (String)params.get(1);
      String lightStr = (String)params.get(2);
      int threshold = params.size() > 3 ? parsePercentage((String)params.get(3)) : 43;
      ColorUIResource color = (ColorUIResource)parseColorOrFunction((String)resolver.apply(colorStr), resolver);
      if (color == null) {
         return null;
      } else {
         String darkOrLightColor = ColorFunctions.luma(color) * 100.0F < (float)threshold ? lightStr : darkStr;
         return parseColorOrFunction((String)resolver.apply(darkOrLightColor), resolver);
      }
   }

   private static ColorUIResource parseColorOver(List<String> params, Function<String, String> resolver) {
      String foregroundStr = (String)params.get(0);
      String backgroundStr = (String)params.get(1);
      ColorUIResource foreground = (ColorUIResource)parseColorOrFunction((String)resolver.apply(foregroundStr), resolver);
      if (foreground != null && foreground.getAlpha() != 255) {
         ColorUIResource foreground2 = new ColorUIResource(foreground.getRGB());
         ColorUIResource background = (ColorUIResource)parseColorOrFunction((String)resolver.apply(backgroundStr), resolver);
         if (background == null) {
            return foreground2;
         } else {
            float weight = (float)foreground.getAlpha() / 255.0F;
            return new ColorUIResource(ColorFunctions.mix(foreground2, background, weight));
         }
      } else {
         return foreground;
      }
   }

   private static Object parseFunctionBaseColor(String colorStr, ColorFunctions.ColorFunction function, boolean derived, Function<String, String> resolver) {
      String resolvedColorStr = (String)resolver.apply(colorStr);
      ColorUIResource baseColor = (ColorUIResource)parseColorOrFunction(resolvedColorStr, resolver);
      if (baseColor == null) {
         return null;
      } else {
         Color newColor = ColorFunctions.applyFunctions(baseColor, function);
         if (!derived) {
            return new ColorUIResource(newColor);
         } else {
            ColorFunctions.ColorFunction[] functions;
            if (baseColor instanceof DerivedColor && resolvedColorStr == colorStr) {
               ColorFunctions.ColorFunction[] baseFunctions = ((DerivedColor)baseColor).getFunctions();
               functions = new ColorFunctions.ColorFunction[baseFunctions.length + 1];
               System.arraycopy(baseFunctions, 0, functions, 0, baseFunctions.length);
               functions[baseFunctions.length] = function;
            } else {
               functions = new ColorFunctions.ColorFunction[]{function};
            }

            return new DerivedColor(newColor, functions);
         }
      }
   }

   private static Object parseFont(String value) {
      Object font = fontCache.get(value);
      if (font != null) {
         return font;
      } else {
         int style = -1;
         int styleChange = 0;
         int absoluteSize = 0;
         int relativeSize = 0;
         float scaleSize = 0.0F;
         List<String> families = null;
         String baseFontKey = null;
         StreamTokenizer st = new StreamTokenizer(new StringReader(value));
         st.resetSyntax();
         st.wordChars(33, 255);
         st.whitespaceChars(0, 32);
         st.whitespaceChars(44, 44);
         st.quoteChar(34);
         st.quoteChar(39);

         try {
            while(st.nextToken() != -1) {
               String param = st.sval;
               byte var12 = -1;
               switch(param.hashCode()) {
               case -1670828517:
                  if (param.equals("+italic")) {
                     var12 = 5;
                  }
                  break;
               case -1178781136:
                  if (param.equals("italic")) {
                     var12 = 2;
                  }
                  break;
               case -1039745817:
                  if (param.equals("normal")) {
                     var12 = 0;
                  }
                  break;
               case 3029637:
                  if (param.equals("bold")) {
                     var12 = 1;
                  }
                  break;
               case 42741040:
                  if (param.equals("+bold")) {
                     var12 = 3;
                  }
                  break;
               case 44588082:
                  if (param.equals("-bold")) {
                     var12 = 4;
                  }
                  break;
               case 104178845:
                  if (param.equals("-italic")) {
                     var12 = 6;
                  }
               }

               switch(var12) {
               case 0:
                  style = 0;
                  break;
               case 1:
                  if (style == -1) {
                     style = 0;
                  }

                  style |= 1;
                  break;
               case 2:
                  if (style == -1) {
                     style = 0;
                  }

                  style |= 2;
                  break;
               case 3:
                  styleChange |= 1;
                  break;
               case 4:
                  styleChange |= 65536;
                  break;
               case 5:
                  styleChange |= 2;
                  break;
               case 6:
                  styleChange |= 131072;
                  break;
               default:
                  char firstChar = param.charAt(0);
                  if (!Character.isDigit(firstChar) && firstChar != '+' && firstChar != '-') {
                     if (firstChar == '$') {
                        if (baseFontKey != null) {
                           throw new IllegalArgumentException("baseFontKey specified more than once in '" + value + "'");
                        }

                        baseFontKey = param.substring(1);
                     } else if (families == null) {
                        families = Collections.singletonList(param);
                     } else {
                        if (((List)families).size() == 1) {
                           families = new ArrayList((Collection)families);
                        }

                        ((List)families).add(param);
                     }
                  } else {
                     if (absoluteSize != 0 || relativeSize != 0 || scaleSize != 0.0F) {
                        throw new IllegalArgumentException("size specified more than once in '" + value + "'");
                     }

                     if (firstChar != '+' && firstChar != '-') {
                        if (param.endsWith("%")) {
                           scaleSize = (float)parseInteger(param.substring(0, param.length() - 1)) / 100.0F;
                        } else {
                           absoluteSize = parseInteger(param);
                        }
                     } else {
                        relativeSize = parseInteger(param);
                     }
                  }
               }
            }
         } catch (IOException var14) {
            throw new IllegalArgumentException(var14);
         }

         if (style != -1 && styleChange != 0) {
            throw new IllegalArgumentException("can not mix absolute style (e.g. 'bold') with derived style (e.g. '+italic') in '" + value + "'");
         } else {
            if (styleChange != 0) {
               if ((styleChange & 1) != 0 && (styleChange & 65536) != 0) {
                  throw new IllegalArgumentException("can not use '+bold' and '-bold' in '" + value + "'");
               }

               if ((styleChange & 2) != 0 && (styleChange & 131072) != 0) {
                  throw new IllegalArgumentException("can not use '+italic' and '-italic' in '" + value + "'");
               }
            }

            Object font = new FlatLaf.ActiveFont(baseFontKey, (List)families, style, styleChange, absoluteSize, relativeSize, scaleSize);
            fontCache.put(value, font);
            return font;
         }
      }
   }

   private static int parsePercentage(String value) {
      if (!value.endsWith("%")) {
         throw new NumberFormatException("invalid percentage '" + value + "'");
      } else {
         int val;
         try {
            val = Integer.parseInt(value.substring(0, value.length() - 1));
         } catch (NumberFormatException var3) {
            throw new NumberFormatException("invalid percentage '" + value + "'");
         }

         if (val >= 0 && val <= 100) {
            return val;
         } else {
            throw new IllegalArgumentException("percentage out of range (0-100%) '" + value + "'");
         }
      }
   }

   private static Boolean parseBoolean(String value) {
      byte var2 = -1;
      switch(value.hashCode()) {
      case 3569038:
         if (value.equals("true")) {
            var2 = 1;
         }
         break;
      case 97196323:
         if (value.equals("false")) {
            var2 = 0;
         }
      }

      switch(var2) {
      case 0:
         return false;
      case 1:
         return true;
      default:
         throw new IllegalArgumentException("invalid boolean '" + value + "'");
      }
   }

   private static Character parseCharacter(String value) {
      if (value.length() != 1) {
         throw new IllegalArgumentException("invalid character '" + value + "'");
      } else {
         return value.charAt(0);
      }
   }

   private static Integer parseInteger(String value, int min, int max, boolean allowPercentage) {
      if (allowPercentage && value.endsWith("%")) {
         int percent = parsePercentage(value);
         return max * percent / 100;
      } else {
         Integer integer = parseInteger(value);
         if (integer >= min && integer <= max) {
            return integer;
         } else {
            throw new NumberFormatException("integer '" + value + "' out of range (" + min + '-' + max + ')');
         }
      }
   }

   private static Integer parseInteger(String value) {
      try {
         return Integer.parseInt(value);
      } catch (NumberFormatException var2) {
         throw new NumberFormatException("invalid integer '" + value + "'");
      }
   }

   private static Number parseIntegerOrFloat(String value) {
      try {
         return Integer.parseInt(value);
      } catch (NumberFormatException var4) {
         try {
            return Float.parseFloat(value);
         } catch (NumberFormatException var3) {
            throw new NumberFormatException("invalid integer or float '" + value + "'");
         }
      }
   }

   private static Float parseFloat(String value) {
      try {
         return Float.parseFloat(value);
      } catch (NumberFormatException var2) {
         throw new NumberFormatException("invalid float '" + value + "'");
      }
   }

   private static ActiveValue parseScaledInteger(String value) {
      int val = parseInteger(value);
      return (t) -> {
         return UIScale.scale(val);
      };
   }

   private static ActiveValue parseScaledFloat(String value) {
      float val = parseFloat(value);
      return (t) -> {
         return UIScale.scale(val);
      };
   }

   private static ActiveValue parseScaledInsets(String value) {
      Insets insets = parseInsets(value);
      return (t) -> {
         return UIScale.scale(insets);
      };
   }

   private static ActiveValue parseScaledDimension(String value) {
      Dimension dimension = parseDimension(value);
      return (t) -> {
         return UIScale.scale(dimension);
      };
   }

   private static Object parseGrayFilter(String value) {
      List numbers = StringUtils.split(value, ',', true, false);

      try {
         int brightness = Integer.parseInt((String)numbers.get(0));
         int contrast = Integer.parseInt((String)numbers.get(1));
         int alpha = Integer.parseInt((String)numbers.get(2));
         return (t) -> {
            return new GrayFilter(brightness, contrast, alpha);
         };
      } catch (NumberFormatException var5) {
         throw new IllegalArgumentException("invalid gray filter '" + value + "'");
      }
   }

   private static List<String> splitFunctionParams(String str, char delim) {
      ArrayList<String> strs = new ArrayList();
      int nestLevel = 0;
      int start = 0;
      int strlen = str.length();

      for(int i = 0; i < strlen; ++i) {
         char ch = str.charAt(i);
         if (ch == '(') {
            ++nestLevel;
         } else if (ch == ')') {
            --nestLevel;
         } else if (nestLevel == 0 && ch == delim) {
            strs.add(StringUtils.substringTrimmed(str, start, i));
            start = i + 1;
         }
      }

      String s = StringUtils.substringTrimmed(str, start);
      if (!s.isEmpty() || !strs.isEmpty()) {
         strs.add(s);
      }

      return strs;
   }

   static Object lazyUIManagerGet(String uiKey) {
      boolean optional = false;
      if (uiKey.startsWith("?")) {
         uiKey = uiKey.substring("?".length());
         optional = true;
      }

      Object value = UIManager.get(uiKey);
      if (value == null && !optional) {
         LoggingFacade.INSTANCE.logSevere("FlatLaf: '" + uiKey + "' not found in UI defaults.", (Throwable)null);
      }

      return value;
   }

   private static void throwMissingParametersException(String value) {
      throw new IllegalArgumentException("missing parameters in function '" + value + "'");
   }

   static enum ValueType {
      UNKNOWN,
      STRING,
      BOOLEAN,
      CHARACTER,
      INTEGER,
      INTEGERORFLOAT,
      FLOAT,
      BORDER,
      ICON,
      INSETS,
      DIMENSION,
      COLOR,
      FONT,
      SCALEDINTEGER,
      SCALEDFLOAT,
      SCALEDINSETS,
      SCALEDDIMENSION,
      INSTANCE,
      CLASS,
      GRAYFILTER,
      NULL,
      LAZY;
   }
}
