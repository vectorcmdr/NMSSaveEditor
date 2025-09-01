package com.formdev.flatlaf.util;

import java.awt.Component;
import java.awt.Container;

public class SwingUtils {
   public static <T extends Component> T getComponentByName(Container parent, String name) {
      Component[] var2 = parent.getComponents();
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         Component child = var2[var4];
         if (name.equals(child.getName())) {
            return child;
         }

         if (child instanceof Container) {
            T c = getComponentByName((Container)child, name);
            if (c != null) {
               return c;
            }
         }
      }

      return null;
   }
}
