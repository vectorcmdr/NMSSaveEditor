package com.jgoodies.forms.util;

import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.LayoutManager;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;

public final class DefaultUnitConverter extends AbstractUnitConverter {
   public static final String PROPERTY_AVERAGE_CHARACTER_WIDTH_TEST_STRING = "averageCharacterWidthTestString";
   public static final String PROPERTY_DEFAULT_DIALOG_FONT = "defaultDialogFont";
   private static final Logger LOGGER;
   private static DefaultUnitConverter instance;
   private String averageCharWidthTestString = "X";
   private Font defaultDialogFont;
   private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
   private DefaultUnitConverter.DialogBaseUnits cachedGlobalDialogBaseUnits = null;
   private DefaultUnitConverter.DialogBaseUnits cachedDialogBaseUnits = null;
   private FontMetrics cachedFontMetrics = null;
   private Font cachedDefaultDialogFont = null;

   private DefaultUnitConverter() {
   }

   public static DefaultUnitConverter getInstance() {
      if (instance == null) {
         instance = new DefaultUnitConverter();
      }

      return instance;
   }

   public String getAverageCharacterWidthTestString() {
      return this.averageCharWidthTestString;
   }

   public void setAverageCharacterWidthTestString(String newTestString) {
      if (newTestString == null) {
         throw new NullPointerException("The test string must not be null.");
      } else if (newTestString.length() == 0) {
         throw new IllegalArgumentException("The test string must not be empty.");
      } else {
         String oldTestString = this.averageCharWidthTestString;
         this.averageCharWidthTestString = newTestString;
         this.changeSupport.firePropertyChange("averageCharacterWidthTestString", oldTestString, newTestString);
      }
   }

   public Font getDefaultDialogFont() {
      return this.defaultDialogFont != null ? this.defaultDialogFont : this.getCachedDefaultDialogFont();
   }

   public void setDefaultDialogFont(Font newFont) {
      Font oldFont = this.defaultDialogFont;
      this.defaultDialogFont = newFont;
      this.clearCache();
      this.changeSupport.firePropertyChange("defaultDialogFont", oldFont, newFont);
   }

   protected double getDialogBaseUnitsX(Component component) {
      return this.getDialogBaseUnits(component).x;
   }

   protected double getDialogBaseUnitsY(Component component) {
      return this.getDialogBaseUnits(component).y;
   }

   private DefaultUnitConverter.DialogBaseUnits getGlobalDialogBaseUnits() {
      if (this.cachedGlobalDialogBaseUnits == null) {
         this.cachedGlobalDialogBaseUnits = this.computeGlobalDialogBaseUnits();
      }

      return this.cachedGlobalDialogBaseUnits;
   }

   private DefaultUnitConverter.DialogBaseUnits getDialogBaseUnits(Component c) {
      FormUtils.ensureValidCache();
      if (c == null) {
         return this.getGlobalDialogBaseUnits();
      } else {
         FontMetrics fm = c.getFontMetrics(this.getDefaultDialogFont());
         if (fm.equals(this.cachedFontMetrics)) {
            return this.cachedDialogBaseUnits;
         } else {
            DefaultUnitConverter.DialogBaseUnits dialogBaseUnits = this.computeDialogBaseUnits(fm);
            this.cachedFontMetrics = fm;
            this.cachedDialogBaseUnits = dialogBaseUnits;
            return dialogBaseUnits;
         }
      }
   }

   private DefaultUnitConverter.DialogBaseUnits computeDialogBaseUnits(FontMetrics metrics) {
      double averageCharWidth = this.computeAverageCharWidth(metrics, this.averageCharWidthTestString);
      int ascent = metrics.getAscent();
      double height = ascent > 14 ? (double)ascent : (double)(ascent + (15 - ascent) / 3);
      DefaultUnitConverter.DialogBaseUnits dialogBaseUnits = new DefaultUnitConverter.DialogBaseUnits(averageCharWidth, height);
      if (LOGGER.isLoggable(Level.CONFIG)) {
         LOGGER.config("Computed dialog base units " + dialogBaseUnits + " for: " + metrics.getFont());
      }

      return dialogBaseUnits;
   }

   private DefaultUnitConverter.DialogBaseUnits computeGlobalDialogBaseUnits() {
      LOGGER.config("Computing global dialog base units...");
      Font dialogFont = this.getDefaultDialogFont();
      FontMetrics metrics = this.createDefaultGlobalComponent().getFontMetrics(dialogFont);
      DefaultUnitConverter.DialogBaseUnits globalDialogBaseUnits = this.computeDialogBaseUnits(metrics);
      return globalDialogBaseUnits;
   }

   private Font getCachedDefaultDialogFont() {
      FormUtils.ensureValidCache();
      if (this.cachedDefaultDialogFont == null) {
         this.cachedDefaultDialogFont = this.lookupDefaultDialogFont();
      }

      return this.cachedDefaultDialogFont;
   }

   private Font lookupDefaultDialogFont() {
      Font buttonFont = UIManager.getFont("Button.font");
      return buttonFont != null ? buttonFont : (new JButton()).getFont();
   }

   private Component createDefaultGlobalComponent() {
      return new JPanel((LayoutManager)null);
   }

   void clearCache() {
      this.cachedGlobalDialogBaseUnits = null;
      this.cachedFontMetrics = null;
      this.cachedDefaultDialogFont = null;
   }

   public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
      this.changeSupport.addPropertyChangeListener(listener);
   }

   public synchronized void removePropertyChangeListener(PropertyChangeListener listener) {
      this.changeSupport.removePropertyChangeListener(listener);
   }

   public synchronized void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
      this.changeSupport.addPropertyChangeListener(propertyName, listener);
   }

   public synchronized void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
      this.changeSupport.removePropertyChangeListener(propertyName, listener);
   }

   static {
      LOGGER = Logger.getLogger(DefaultUnitConverter.class.getName());
   }

   private static final class DialogBaseUnits {
      final double x;
      final double y;

      DialogBaseUnits(double dialogBaseUnitsX, double dialogBaseUnitsY) {
         this.x = dialogBaseUnitsX;
         this.y = dialogBaseUnitsY;
      }

      public String toString() {
         return "DBU(x=" + this.x + "; y=" + this.y + ")";
      }
   }
}
