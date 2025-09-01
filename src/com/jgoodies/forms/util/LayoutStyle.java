package com.jgoodies.forms.util;

import com.jgoodies.forms.layout.ConstantSize;
import com.jgoodies.forms.layout.Size;
import java.util.logging.Logger;

public abstract class LayoutStyle {
   private static LayoutStyle current = initialLayoutStyle();

   private static LayoutStyle initialLayoutStyle() {
      return (LayoutStyle)(isOSMac() ? MacLayoutStyle.INSTANCE : WindowsLayoutStyle.INSTANCE);
   }

   private static boolean isOSMac() {
      return getSystemProperty("os.name").startsWith("Mac");
   }

   private static String getSystemProperty(String key) {
      try {
         return System.getProperty(key);
      } catch (SecurityException var2) {
         Logger.getLogger(LayoutStyle.class.getName()).warning("Can't read the System property " + key + ".");
         return "";
      }
   }

   public static LayoutStyle getCurrent() {
      return current;
   }

   public static void setCurrent(LayoutStyle newLayoutStyle) {
      current = newLayoutStyle;
   }

   public abstract Size getDefaultButtonWidth();

   public abstract Size getDefaultButtonHeight();

   public abstract ConstantSize getDialogMarginX();

   public abstract ConstantSize getDialogMarginY();

   public abstract ConstantSize getTabbedDialogMarginX();

   public abstract ConstantSize getTabbedDialogMarginY();

   public abstract ConstantSize getLabelComponentPadX();

   public abstract ConstantSize getRelatedComponentsPadX();

   public abstract ConstantSize getRelatedComponentsPadY();

   public abstract ConstantSize getUnrelatedComponentsPadX();

   public abstract ConstantSize getUnrelatedComponentsPadY();

   public abstract ConstantSize getNarrowLinePad();

   public abstract ConstantSize getLinePad();

   public abstract ConstantSize getParagraphPad();

   public abstract ConstantSize getButtonBarPad();

   public abstract boolean isLeftToRightButtonOrder();
}
