package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.HiDPIUtils;
import com.formdev.flatlaf.util.StringUtils;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Iterator;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JToolTip;
import javax.swing.SwingUtilities;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicToolTipUI;

public class FlatToolTipUI extends BasicToolTipUI implements PropertyChangeListener {
   public static ComponentUI createUI(JComponent c) {
      return FlatUIUtils.createSharedUI(FlatToolTipUI.class, FlatToolTipUI::new);
   }

   public void installUI(JComponent c) {
      super.installUI(c);
      FlatLabelUI.updateHTMLRenderer(c, ((JToolTip)c).getTipText(), false);
   }

   protected void installListeners(JComponent c) {
      super.installListeners(c);
      c.addPropertyChangeListener(this);
   }

   protected void uninstallListeners(JComponent c) {
      super.uninstallListeners(c);
      c.removePropertyChangeListener(this);
   }

   public void propertyChange(PropertyChangeEvent e) {
      String name = e.getPropertyName();
      if (name == "tiptext" || name == "font" || name == "foreground") {
         JToolTip toolTip = (JToolTip)e.getSource();
         FlatLabelUI.updateHTMLRenderer(toolTip, toolTip.getTipText(), false);
      }

   }

   public Dimension getPreferredSize(JComponent c) {
      String text = ((JToolTip)c).getTipText();
      if (text != null && !text.isEmpty()) {
         if (!this.isMultiLine(c)) {
            return super.getPreferredSize(c);
         } else {
            FontMetrics fm = c.getFontMetrics(c.getFont());
            Insets insets = c.getInsets();
            List<String> lines = StringUtils.split(((JToolTip)c).getTipText(), '\n');
            int width = 0;
            int height = fm.getHeight() * Math.max(lines.size(), 1);

            String line;
            for(Iterator var8 = lines.iterator(); var8.hasNext(); width = Math.max(width, SwingUtilities.computeStringWidth(fm, line))) {
               line = (String)var8.next();
            }

            return new Dimension(insets.left + width + insets.right + 6, insets.top + height + insets.bottom);
         }
      } else {
         return new Dimension();
      }
   }

   public void paint(Graphics g, JComponent c) {
      if (this.isMultiLine(c)) {
         FontMetrics fm = c.getFontMetrics(c.getFont());
         Insets insets = c.getInsets();
         g.setColor(c.getForeground());
         List<String> lines = StringUtils.split(((JToolTip)c).getTipText(), '\n');
         int x = insets.left + 3;
         int x2 = c.getWidth() - insets.right - 3;
         int y = insets.top - fm.getDescent();
         int lineHeight = fm.getHeight();
         JComponent comp = ((JToolTip)c).getComponent();
         boolean leftToRight = (comp != null ? comp : c).getComponentOrientation().isLeftToRight();
         Iterator var12 = lines.iterator();

         while(var12.hasNext()) {
            String line = (String)var12.next();
            y += lineHeight;
            FlatUIUtils.drawString(c, g, line, leftToRight ? x : x2 - SwingUtilities.computeStringWidth(fm, line), y);
         }
      } else {
         super.paint(HiDPIUtils.createGraphicsTextYCorrection((Graphics2D)g), c);
      }

   }

   private boolean isMultiLine(JComponent c) {
      String text = ((JToolTip)c).getTipText();
      return c.getClientProperty("html") == null && text != null && text.indexOf(10) >= 0;
   }
}
