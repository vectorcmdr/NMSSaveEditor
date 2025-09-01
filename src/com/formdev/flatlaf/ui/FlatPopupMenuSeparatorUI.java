package com.formdev.flatlaf.ui;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;

public class FlatPopupMenuSeparatorUI extends FlatSeparatorUI {
   public static ComponentUI createUI(JComponent c) {
      return (ComponentUI)(FlatUIUtils.canUseSharedUI(c) ? FlatUIUtils.createSharedUI(FlatPopupMenuSeparatorUI.class, () -> {
         return new FlatPopupMenuSeparatorUI(true);
      }) : new FlatPopupMenuSeparatorUI(false));
   }

   protected FlatPopupMenuSeparatorUI(boolean shared) {
      super(shared);
   }

   protected String getPropertyPrefix() {
      return "PopupMenuSeparator";
   }

   String getStyleType() {
      return "PopupMenuSeparator";
   }
}
