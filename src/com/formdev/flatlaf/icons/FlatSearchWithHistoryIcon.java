package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Component;
import java.awt.Graphics2D;

public class FlatSearchWithHistoryIcon extends FlatSearchIcon {
   public FlatSearchWithHistoryIcon() {
      this(false);
   }

   public FlatSearchWithHistoryIcon(boolean ignoreButtonState) {
      super(ignoreButtonState);
   }

   protected void paintIcon(Component c, Graphics2D g) {
      g.translate(-2, 0);
      super.paintIcon(c, g);
      g.translate(2, 0);
      g.fill(FlatUIUtils.createPath(11.0D, 7.0D, 16.0D, 7.0D, 13.5D, 10.0D));
   }
}
