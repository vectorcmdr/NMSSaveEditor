package com.formdev.flatlaf.util;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.plaf.UIResource;
import javax.swing.text.StyleContext;

public class FontUtils {
   private static Map<String, Runnable> loadersMap;

   public static Font getCompositeFont(String family, int style, int size) {
      loadFontFamily(family);
      Font font = StyleContext.getDefaultStyleContext().getFont(family, style, size);
      if (font instanceof UIResource) {
         font = font.deriveFont(font.getStyle());
      }

      return font;
   }

   public static void registerFontFamilyLoader(String family, Runnable loader) {
      if (loadersMap == null) {
         loadersMap = new HashMap();
      }

      loadersMap.put(family, loader);
   }

   public static void loadFontFamily(String family) {
      if (hasLoaders()) {
         Runnable loader = (Runnable)loadersMap.remove(family);
         if (loader != null) {
            loader.run();
         }

         if (loadersMap.isEmpty()) {
            loadersMap = null;
         }

      }
   }

   public static boolean installFont(URL url) {
      try {
         InputStream in = url.openStream();

         boolean var3;
         try {
            Font font = Font.createFont(0, in);
            var3 = GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
         } catch (Throwable var5) {
            if (in != null) {
               try {
                  in.close();
               } catch (Throwable var4) {
                  var5.addSuppressed(var4);
               }
            }

            throw var5;
         }

         if (in != null) {
            in.close();
         }

         return var3;
      } catch (IOException | FontFormatException var6) {
         LoggingFacade.INSTANCE.logSevere("FlatLaf: Failed to install font " + url, var6);
         return false;
      }
   }

   public static String[] getAvailableFontFamilyNames() {
      String[] availableFontFamilyNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
      if (!hasLoaders()) {
         return availableFontFamilyNames;
      } else {
         ArrayList<String> result = new ArrayList(availableFontFamilyNames.length + loadersMap.size());
         String[] var2 = availableFontFamilyNames;
         int var3 = availableFontFamilyNames.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            String name = var2[var4];
            result.add(name);
         }

         Iterator var6 = loadersMap.keySet().iterator();

         while(var6.hasNext()) {
            String name = (String)var6.next();
            if (!result.contains(name)) {
               result.add(name);
            }
         }

         return (String[])result.toArray(new String[result.size()]);
      }
   }

   public static Font[] getAllFonts() {
      if (hasLoaders()) {
         String[] families = (String[])loadersMap.keySet().toArray(new String[loadersMap.size()]);
         String[] var1 = families;
         int var2 = families.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            String family = var1[var3];
            loadFontFamily(family);
         }
      }

      return GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
   }

   private static boolean hasLoaders() {
      return loadersMap != null && !loadersMap.isEmpty();
   }
}
