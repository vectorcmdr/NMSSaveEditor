/*     */ package com.formdev.flatlaf;
/*     */ 
/*     */ import com.formdev.flatlaf.util.LoggingFacade;
/*     */ import com.formdev.flatlaf.util.StringUtils;
/*     */ import com.formdev.flatlaf.util.SystemInfo;
/*     */ import com.formdev.flatlaf.util.UIScale;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.GraphicsConfiguration;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.awt.Toolkit;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import javax.swing.text.StyleContext;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class LinuxFontPolicy
/*     */ {
/*     */   static Font getFont() {
/*  44 */     return SystemInfo.isKDE ? getKDEFont() : getGnomeFont();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Font getGnomeFont() {
/*  53 */     Object fontName = Toolkit.getDefaultToolkit().getDesktopProperty("gnome.Gtk/FontName");
/*  54 */     if (!(fontName instanceof String)) {
/*  55 */       fontName = "sans 10";
/*     */     }
/*  57 */     String family = "";
/*  58 */     int style = 0;
/*  59 */     double dsize = 10.0D;
/*     */ 
/*     */ 
/*     */     
/*  63 */     StringTokenizer st = new StringTokenizer((String)fontName);
/*  64 */     while (st.hasMoreTokens()) {
/*  65 */       String word = st.nextToken();
/*     */ 
/*     */       
/*  68 */       if (word.endsWith(",")) {
/*  69 */         word = word.substring(0, word.length() - 1).trim();
/*     */       }
/*  71 */       String lword = word.toLowerCase();
/*  72 */       if (lword.equals("italic") || lword.equals("oblique")) {
/*  73 */         style |= 0x2; continue;
/*  74 */       }  if (lword.equals("bold")) {
/*  75 */         style |= 0x1; continue;
/*  76 */       }  if (Character.isDigit(word.charAt(0))) {
/*     */         try {
/*  78 */           dsize = Double.parseDouble(word);
/*  79 */         } catch (NumberFormatException numberFormatException) {}
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/*  84 */       if (lword.startsWith("semi-") || lword.startsWith("demi-")) {
/*  85 */         word = word.substring(0, 4) + word.substring(5);
/*  86 */       } else if (lword.startsWith("extra-") || lword.startsWith("ultra-")) {
/*  87 */         word = word.substring(0, 5) + word.substring(6);
/*     */       } 
/*  89 */       family = family.isEmpty() ? word : (family + ' ' + word);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  95 */     if (family.startsWith("Ubuntu") && !SystemInfo.isJetBrainsJVM && 
/*     */       
/*  97 */       !FlatSystemProperties.getBoolean("flatlaf.useUbuntuFont", false)) {
/*  98 */       family = "Liberation Sans";
/*     */     }
/*     */     
/* 101 */     dsize *= getGnomeFontScale();
/* 102 */     int size = (int)(dsize + 0.5D);
/* 103 */     if (size < 1) {
/* 104 */       size = 1;
/*     */     }
/*     */     
/* 107 */     String logicalFamily = mapFcName(family.toLowerCase());
/* 108 */     if (logicalFamily != null) {
/* 109 */       family = logicalFamily;
/*     */     }
/* 111 */     return createFontEx(family, style, size, dsize);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Font createFontEx(String family, int style, int size, double dsize) {
/*     */     while (true) {
/* 123 */       Font font = createFont(family, style, size, dsize);
/*     */       
/* 125 */       if ("Dialog".equals(family)) {
/* 126 */         return font;
/*     */       }
/*     */       
/* 129 */       if (!"Dialog".equals(font.getFamily())) {
/*     */ 
/*     */ 
/*     */         
/* 133 */         FontMetrics fm = StyleContext.getDefaultStyleContext().getFontMetrics(font);
/* 134 */         if (fm.getHeight() > size * 2 || fm.stringWidth("a") == 0) {
/* 135 */           return createFont("Dialog", style, size, dsize);
/*     */         }
/* 137 */         return font;
/*     */       } 
/*     */ 
/*     */       
/* 141 */       int index = family.lastIndexOf(' ');
/* 142 */       if (index < 0) {
/* 143 */         return createFont("Dialog", style, size, dsize);
/*     */       }
/*     */       
/* 146 */       String lastWord = family.substring(index + 1).toLowerCase();
/* 147 */       if (lastWord.contains("bold") || lastWord.contains("heavy") || lastWord.contains("black")) {
/* 148 */         style |= 0x1;
/*     */       }
/*     */       
/* 151 */       family = family.substring(0, index);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static Font createFont(String family, int style, int size, double dsize) {
/* 156 */     Font font = FlatLaf.createCompositeFont(family, style, size);
/*     */ 
/*     */     
/* 159 */     font = font.deriveFont(style, (float)dsize);
/*     */     
/* 161 */     return font;
/*     */   }
/*     */ 
/*     */   
/*     */   private static double getGnomeFontScale() {
/* 166 */     if (isSystemScaling()) {
/* 167 */       return 1.3333333333333333D;
/*     */     }
/*     */ 
/*     */     
/* 171 */     Object value = Toolkit.getDefaultToolkit().getDesktopProperty("gnome.Xft/DPI");
/* 172 */     if (value instanceof Integer) {
/* 173 */       int dpi = ((Integer)value).intValue() / 1024;
/* 174 */       if (dpi == -1)
/* 175 */         dpi = 96; 
/* 176 */       if (dpi < 50)
/* 177 */         dpi = 50; 
/* 178 */       return dpi / 72.0D;
/*     */     } 
/* 180 */     return GraphicsEnvironment.getLocalGraphicsEnvironment()
/* 181 */       .getDefaultScreenDevice().getDefaultConfiguration()
/* 182 */       .getNormalizingTransform().getScaleY();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String mapFcName(String name) {
/* 190 */     switch (name) { case "sans":
/* 191 */         return "sansserif";
/* 192 */       case "sans-serif": return "sansserif";
/* 193 */       case "serif": return "serif";
/* 194 */       case "monospace": return "monospaced"; }
/*     */     
/* 196 */     return null;
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
/*     */   private static Font getKDEFont() {
/* 212 */     List<String> kdeglobals = readConfig("kdeglobals");
/* 213 */     List<String> kcmfonts = readConfig("kcmfonts");
/*     */     
/* 215 */     String generalFont = getConfigEntry(kdeglobals, "General", "font");
/* 216 */     String forceFontDPI = getConfigEntry(kcmfonts, "General", "forceFontDPI");
/*     */     
/* 218 */     String family = "sansserif";
/* 219 */     int style = 0;
/* 220 */     int size = 10;
/*     */     
/* 222 */     if (generalFont != null) {
/* 223 */       List<String> strs = StringUtils.split(generalFont, ',');
/*     */       try {
/* 225 */         family = strs.get(0);
/* 226 */         size = Integer.parseInt(strs.get(1));
/* 227 */         if ("75".equals(strs.get(4)))
/* 228 */           style |= 0x1; 
/* 229 */         if ("1".equals(strs.get(5)))
/* 230 */           style |= 0x2; 
/* 231 */       } catch (RuntimeException ex) {
/* 232 */         LoggingFacade.INSTANCE.logConfig("FlatLaf: Failed to parse 'font=" + generalFont + "'.", ex);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 237 */     int dpi = 96;
/* 238 */     if (forceFontDPI != null && !isSystemScaling()) {
/*     */       try {
/* 240 */         dpi = Integer.parseInt(forceFontDPI);
/* 241 */         if (dpi <= 0)
/* 242 */           dpi = 96; 
/* 243 */         if (dpi < 50)
/* 244 */           dpi = 50; 
/* 245 */       } catch (NumberFormatException ex) {
/* 246 */         LoggingFacade.INSTANCE.logConfig("FlatLaf: Failed to parse 'forceFontDPI=" + forceFontDPI + "'.", ex);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 251 */     double fontScale = dpi / 72.0D;
/* 252 */     double dsize = size * fontScale;
/* 253 */     size = (int)(dsize + 0.5D);
/* 254 */     if (size < 1) {
/* 255 */       size = 1;
/*     */     }
/* 257 */     return createFont(family, style, size, dsize);
/*     */   }
/*     */   
/*     */   private static List<String> readConfig(String filename) {
/* 261 */     File userHome = new File(System.getProperty("user.home"));
/*     */ 
/*     */     
/* 264 */     String[] configDirs = { ".config", ".kde4/share/config", ".kde/share/config" };
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 269 */     File file = null;
/* 270 */     for (String configDir : configDirs) {
/* 271 */       file = new File(userHome, configDir + "/" + filename);
/* 272 */       if (file.isFile())
/*     */         break; 
/*     */     } 
/* 275 */     if (!file.isFile()) {
/* 276 */       return Collections.emptyList();
/*     */     }
/*     */     
/* 279 */     ArrayList<String> lines = new ArrayList<>(200); 
/* 280 */     try { BufferedReader reader = new BufferedReader(new FileReader(file)); 
/*     */       try { String line;
/* 282 */         while ((line = reader.readLine()) != null)
/* 283 */           lines.add(line); 
/* 284 */         reader.close(); } catch (Throwable throwable) { try { reader.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (IOException ex)
/* 285 */     { LoggingFacade.INSTANCE.logConfig("FlatLaf: Failed to read '" + filename + "'.", ex); }
/*     */     
/* 287 */     return lines;
/*     */   }
/*     */   
/*     */   private static String getConfigEntry(List<String> config, String group, String key) {
/* 291 */     int groupLength = group.length();
/* 292 */     int keyLength = key.length();
/* 293 */     boolean inGroup = false;
/* 294 */     for (String line : config) {
/* 295 */       if (!inGroup) {
/* 296 */         if (line.length() >= groupLength + 2 && line
/* 297 */           .charAt(0) == '[' && line
/* 298 */           .charAt(groupLength + 1) == ']' && line
/* 299 */           .indexOf(group) == 1)
/*     */         {
/* 301 */           inGroup = true; } 
/*     */         continue;
/*     */       } 
/* 304 */       if (line.startsWith("[")) {
/* 305 */         return null;
/*     */       }
/* 307 */       if (line.length() >= keyLength + 2 && line
/* 308 */         .charAt(keyLength) == '=' && line
/* 309 */         .startsWith(key))
/*     */       {
/* 311 */         return line.substring(keyLength + 1);
/*     */       }
/*     */     } 
/*     */     
/* 315 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isSystemScaling() {
/* 324 */     if (GraphicsEnvironment.isHeadless()) {
/* 325 */       return true;
/*     */     }
/*     */     
/* 328 */     GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
/* 329 */     return (UIScale.getSystemScaleFactor(gc) > 1.0D);
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\LinuxFontPolicy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */