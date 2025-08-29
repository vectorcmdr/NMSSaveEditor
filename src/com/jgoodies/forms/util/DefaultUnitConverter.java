/*     */ package com.jgoodies.forms.util;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.beans.PropertyChangeSupport;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.UIManager;
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
/*     */ public final class DefaultUnitConverter
/*     */   extends AbstractUnitConverter
/*     */ {
/*     */   public static final String PROPERTY_AVERAGE_CHARACTER_WIDTH_TEST_STRING = "averageCharacterWidthTestString";
/*     */   public static final String PROPERTY_DEFAULT_DIALOG_FONT = "defaultDialogFont";
/*  90 */   private static final Logger LOGGER = Logger.getLogger(DefaultUnitConverter.class.getName());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static DefaultUnitConverter instance;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 104 */   private String averageCharWidthTestString = "X";
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
/*     */   private Font defaultDialogFont;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final PropertyChangeSupport changeSupport;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 135 */   private DialogBaseUnits cachedGlobalDialogBaseUnits = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 141 */   private DialogBaseUnits cachedDialogBaseUnits = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 147 */   private FontMetrics cachedFontMetrics = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 156 */   private Font cachedDefaultDialogFont = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private DefaultUnitConverter() {
/* 166 */     this.changeSupport = new PropertyChangeSupport(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static DefaultUnitConverter getInstance() {
/* 176 */     if (instance == null) {
/* 177 */       instance = new DefaultUnitConverter();
/*     */     }
/* 179 */     return instance;
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
/*     */   public String getAverageCharacterWidthTestString() {
/* 192 */     return this.averageCharWidthTestString;
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
/*     */   public void setAverageCharacterWidthTestString(String newTestString) {
/* 210 */     if (newTestString == null) {
/* 211 */       throw new NullPointerException("The test string must not be null.");
/*     */     }
/* 213 */     if (newTestString.length() == 0) {
/* 214 */       throw new IllegalArgumentException("The test string must not be empty.");
/*     */     }
/*     */     
/* 217 */     String oldTestString = this.averageCharWidthTestString;
/* 218 */     this.averageCharWidthTestString = newTestString;
/* 219 */     this.changeSupport.firePropertyChange("averageCharacterWidthTestString", oldTestString, newTestString);
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
/*     */   public Font getDefaultDialogFont() {
/* 235 */     return (this.defaultDialogFont != null) ? this.defaultDialogFont : getCachedDefaultDialogFont();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDefaultDialogFont(Font newFont) {
/* 246 */     Font oldFont = this.defaultDialogFont;
/* 247 */     this.defaultDialogFont = newFont;
/* 248 */     clearCache();
/* 249 */     this.changeSupport.firePropertyChange("defaultDialogFont", oldFont, newFont);
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
/*     */   protected double getDialogBaseUnitsX(Component component) {
/* 262 */     return (getDialogBaseUnits(component)).x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected double getDialogBaseUnitsY(Component component) {
/* 273 */     return (getDialogBaseUnits(component)).y;
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
/*     */   private DialogBaseUnits getGlobalDialogBaseUnits() {
/* 286 */     if (this.cachedGlobalDialogBaseUnits == null) {
/* 287 */       this.cachedGlobalDialogBaseUnits = computeGlobalDialogBaseUnits();
/*     */     }
/* 289 */     return this.cachedGlobalDialogBaseUnits;
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
/*     */   private DialogBaseUnits getDialogBaseUnits(Component c) {
/* 305 */     FormUtils.ensureValidCache();
/* 306 */     if (c == null)
/*     */     {
/* 308 */       return getGlobalDialogBaseUnits();
/*     */     }
/* 310 */     FontMetrics fm = c.getFontMetrics(getDefaultDialogFont());
/* 311 */     if (fm.equals(this.cachedFontMetrics)) {
/* 312 */       return this.cachedDialogBaseUnits;
/*     */     }
/* 314 */     DialogBaseUnits dialogBaseUnits = computeDialogBaseUnits(fm);
/* 315 */     this.cachedFontMetrics = fm;
/* 316 */     this.cachedDialogBaseUnits = dialogBaseUnits;
/* 317 */     return dialogBaseUnits;
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
/*     */   private DialogBaseUnits computeDialogBaseUnits(FontMetrics metrics) {
/* 337 */     double averageCharWidth = computeAverageCharWidth(metrics, this.averageCharWidthTestString);
/*     */     
/* 339 */     int ascent = metrics.getAscent();
/* 340 */     double height = (ascent > 14) ? ascent : (ascent + (15 - ascent) / 3);
/* 341 */     DialogBaseUnits dialogBaseUnits = new DialogBaseUnits(averageCharWidth, height);
/*     */     
/* 343 */     if (LOGGER.isLoggable(Level.CONFIG)) {
/* 344 */       LOGGER.config("Computed dialog base units " + dialogBaseUnits + " for: " + metrics.getFont());
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 350 */     return dialogBaseUnits;
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
/*     */   private DialogBaseUnits computeGlobalDialogBaseUnits() {
/* 364 */     LOGGER.config("Computing global dialog base units...");
/* 365 */     Font dialogFont = getDefaultDialogFont();
/* 366 */     FontMetrics metrics = createDefaultGlobalComponent().getFontMetrics(dialogFont);
/* 367 */     DialogBaseUnits globalDialogBaseUnits = computeDialogBaseUnits(metrics);
/* 368 */     return globalDialogBaseUnits;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Font getCachedDefaultDialogFont() {
/* 379 */     FormUtils.ensureValidCache();
/* 380 */     if (this.cachedDefaultDialogFont == null) {
/* 381 */       this.cachedDefaultDialogFont = lookupDefaultDialogFont();
/*     */     }
/* 383 */     return this.cachedDefaultDialogFont;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Font lookupDefaultDialogFont() {
/* 394 */     Font buttonFont = UIManager.getFont("Button.font");
/* 395 */     return (buttonFont != null) ? buttonFont : (new JButton()).getFont();
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
/*     */   private Component createDefaultGlobalComponent() {
/* 413 */     return new JPanel(null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void clearCache() {
/* 423 */     this.cachedGlobalDialogBaseUnits = null;
/* 424 */     this.cachedFontMetrics = null;
/* 425 */     this.cachedDefaultDialogFont = null;
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
/*     */   public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
/* 445 */     this.changeSupport.addPropertyChangeListener(listener);
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
/*     */   public synchronized void removePropertyChangeListener(PropertyChangeListener listener) {
/* 464 */     this.changeSupport.removePropertyChangeListener(listener);
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
/*     */   public synchronized void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
/* 486 */     this.changeSupport.addPropertyChangeListener(propertyName, listener);
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
/*     */   public synchronized void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
/* 506 */     this.changeSupport.removePropertyChangeListener(propertyName, listener);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static final class DialogBaseUnits
/*     */   {
/*     */     final double x;
/*     */ 
/*     */     
/*     */     final double y;
/*     */ 
/*     */ 
/*     */     
/*     */     DialogBaseUnits(double dialogBaseUnitsX, double dialogBaseUnitsY) {
/* 521 */       this.x = dialogBaseUnitsX;
/* 522 */       this.y = dialogBaseUnitsY;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 526 */       return "DBU(x=" + this.x + "; y=" + this.y + ")";
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\jgoodies\form\\util\DefaultUnitConverter.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */