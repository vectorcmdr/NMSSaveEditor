/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.FlatLaf;
/*     */ import com.formdev.flatlaf.util.HiDPIUtils;
/*     */ import com.formdev.flatlaf.util.LoggingFacade;
/*     */ import com.formdev.flatlaf.util.UIScale;
/*     */ import java.awt.Color;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Rectangle;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.basic.BasicHTML;
/*     */ import javax.swing.plaf.basic.BasicLabelUI;
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
/*     */ public class FlatLabelUI
/*     */   extends BasicLabelUI
/*     */   implements FlatStylingSupport.StyleableUI
/*     */ {
/*     */   @Styleable
/*     */   protected Color disabledForeground;
/*     */   private final boolean shared;
/*     */   private boolean defaults_initialized = false;
/*     */   private Map<String, Object> oldStyleValues;
/*     */   private static Set<String> tagsUseFontSizeSet;
/*     */   
/*     */   public static ComponentUI createUI(JComponent c) {
/*  71 */     return FlatUIUtils.canUseSharedUI(c) ? 
/*  72 */       FlatUIUtils.createSharedUI(FlatLabelUI.class, () -> new FlatLabelUI(true)) : 
/*  73 */       new FlatLabelUI(false);
/*     */   }
/*     */ 
/*     */   
/*     */   protected FlatLabelUI(boolean shared) {
/*  78 */     this.shared = shared;
/*     */   }
/*     */ 
/*     */   
/*     */   public void installUI(JComponent c) {
/*  83 */     super.installUI(c);
/*     */     
/*  85 */     installStyle((JLabel)c);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installDefaults(JLabel c) {
/*  90 */     super.installDefaults(c);
/*     */     
/*  92 */     if (!this.defaults_initialized) {
/*  93 */       this.disabledForeground = UIManager.getColor("Label.disabledForeground");
/*     */       
/*  95 */       this.defaults_initialized = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallDefaults(JLabel c) {
/* 101 */     super.uninstallDefaults(c);
/*     */     
/* 103 */     this.defaults_initialized = false;
/* 104 */     this.oldStyleValues = null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installComponents(JLabel c) {
/* 109 */     super.installComponents(c);
/*     */ 
/*     */     
/* 112 */     updateHTMLRenderer(c, c.getText(), false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void propertyChange(PropertyChangeEvent e) {
/* 117 */     String name = e.getPropertyName();
/* 118 */     if (name == "text" || name == "font" || name == "foreground") {
/* 119 */       JLabel label = (JLabel)e.getSource();
/* 120 */       updateHTMLRenderer(label, label.getText(), true);
/* 121 */     } else if (name.equals("FlatLaf.style") || name.equals("FlatLaf.styleClass")) {
/* 122 */       JLabel label = (JLabel)e.getSource();
/* 123 */       if (this.shared && FlatStylingSupport.hasStyleProperty(label)) {
/*     */ 
/*     */         
/* 126 */         label.updateUI();
/*     */       } else {
/* 128 */         installStyle(label);
/* 129 */       }  label.revalidate();
/* 130 */       label.repaint();
/*     */     } else {
/* 132 */       super.propertyChange(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void installStyle(JLabel c) {
/*     */     try {
/* 138 */       applyStyle(c, FlatStylingSupport.getResolvedStyle(c, "Label"));
/* 139 */     } catch (RuntimeException ex) {
/* 140 */       LoggingFacade.INSTANCE.logSevere(null, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyStyle(JLabel c, Object style) {
/* 146 */     this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, (key, value) -> applyStyleProperty(c, key, value));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object applyStyleProperty(JLabel c, String key, Object value) {
/* 152 */     return FlatStylingSupport.applyToAnnotatedObjectOrComponent(this, c, key, value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
/* 158 */     return FlatStylingSupport.getAnnotatedStyleableInfos(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getStyleableValue(JComponent c, String key) {
/* 164 */     return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void updateHTMLRenderer(JComponent c, String text, boolean always) {
/* 175 */     if (BasicHTML.isHTMLString(text) && c
/* 176 */       .getClientProperty("html.disable") != Boolean.TRUE && 
/* 177 */       needsFontBaseSize(text)) {
/*     */       int insertIndex;
/*     */       
/* 180 */       String style = "<style>BASE_SIZE " + c.getFont().getSize() + "</style>";
/*     */       
/* 182 */       String lowerText = text.toLowerCase();
/*     */ 
/*     */       
/*     */       int headIndex;
/*     */       
/* 187 */       if ((headIndex = lowerText.indexOf("<head>")) >= 0)
/*     */       
/* 189 */       { insertIndex = headIndex + "<head>".length(); }
/* 190 */       else { int styleIndex; if ((styleIndex = lowerText.indexOf("<style>")) >= 0) {
/*     */           
/* 192 */           insertIndex = styleIndex;
/*     */         } else {
/*     */           
/* 195 */           style = "<head>" + style + "</head>";
/* 196 */           insertIndex = "<html>".length();
/*     */         }  }
/*     */ 
/*     */ 
/*     */       
/* 201 */       text = text.substring(0, insertIndex) + style + text.substring(insertIndex);
/* 202 */     } else if (!always) {
/*     */       return;
/*     */     } 
/* 205 */     BasicHTML.updateRenderer(c, text);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean needsFontBaseSize(String text) {
/* 211 */     if (tagsUseFontSizeSet == null)
/*     */     {
/* 213 */       tagsUseFontSizeSet = new HashSet<>(Arrays.asList(new String[] { "h1", "h2", "h3", "h4", "h5", "h6", "code", "kbd", "big", "small", "samp" }));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 218 */     int textLength = text.length();
/* 219 */     for (int i = 6; i < textLength - 1; i++) {
/* 220 */       if (text.charAt(i) == '<') {
/* 221 */         int tagBegin; switch (text.charAt(i + 1)) { case 'B': case 'C': case 'H': case 'K':
/*     */           case 'S':
/*     */           case 'b':
/*     */           case 'c':
/*     */           case 'h':
/*     */           case 'k':
/*     */           case 's':
/* 228 */             tagBegin = i + 1;
/* 229 */             for (i += 2; i < textLength; i++) {
/* 230 */               if (!Character.isLetterOrDigit(text.charAt(i))) {
/* 231 */                 String tag = text.substring(tagBegin, i).toLowerCase();
/* 232 */                 if (tagsUseFontSizeSet.contains(tag)) {
/* 233 */                   return true;
/*     */                 }
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */             break; }
/*     */ 
/*     */       
/*     */       } 
/*     */     } 
/* 243 */     return false;
/*     */   }
/*     */   
/*     */   static Graphics createGraphicsHTMLTextYCorrection(Graphics g, JComponent c) {
/* 247 */     return (c.getClientProperty("html") != null) ? 
/* 248 */       HiDPIUtils.createGraphicsTextYCorrection((Graphics2D)g) : 
/* 249 */       g;
/*     */   }
/*     */ 
/*     */   
/*     */   public void paint(Graphics g, JComponent c) {
/* 254 */     super.paint(createGraphicsHTMLTextYCorrection(g, c), c);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintEnabledText(JLabel l, Graphics g, String s, int textX, int textY) {
/* 259 */     int mnemIndex = FlatLaf.isShowMnemonics() ? l.getDisplayedMnemonicIndex() : -1;
/* 260 */     g.setColor(l.getForeground());
/* 261 */     FlatUIUtils.drawStringUnderlineCharAt(l, g, s, mnemIndex, textX, textY);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintDisabledText(JLabel l, Graphics g, String s, int textX, int textY) {
/* 266 */     int mnemIndex = FlatLaf.isShowMnemonics() ? l.getDisplayedMnemonicIndex() : -1;
/* 267 */     g.setColor(this.disabledForeground);
/* 268 */     FlatUIUtils.drawStringUnderlineCharAt(l, g, s, mnemIndex, textX, textY);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String layoutCL(JLabel label, FontMetrics fontMetrics, String text, Icon icon, Rectangle viewR, Rectangle iconR, Rectangle textR) {
/* 278 */     return SwingUtilities.layoutCompoundLabel(label, fontMetrics, text, icon, label
/* 279 */         .getVerticalAlignment(), label.getHorizontalAlignment(), label
/* 280 */         .getVerticalTextPosition(), label.getHorizontalTextPosition(), viewR, iconR, textR, 
/*     */         
/* 282 */         UIScale.scale(label.getIconTextGap()));
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatLabelUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */