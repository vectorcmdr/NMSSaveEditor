/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.FlatClientProperties;
/*     */ import com.formdev.flatlaf.util.UIScale;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Graphics;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.util.Map;
/*     */ import javax.swing.AbstractButton;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JToggleButton;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.plaf.ComponentUI;
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
/*     */ public class FlatToggleButtonUI
/*     */   extends FlatButtonUI
/*     */ {
/*     */   @Styleable(dot = true)
/*     */   protected int tabUnderlineHeight;
/*     */   @Styleable(dot = true)
/*     */   protected Color tabUnderlineColor;
/*     */   @Styleable(dot = true)
/*     */   protected Color tabDisabledUnderlineColor;
/*     */   @Styleable(dot = true)
/*     */   protected Color tabSelectedBackground;
/*     */   @Styleable(dot = true)
/*     */   protected Color tabSelectedForeground;
/*     */   @Styleable(dot = true)
/*     */   protected Color tabHoverBackground;
/*     */   @Styleable(dot = true)
/*     */   protected Color tabHoverForeground;
/*     */   @Styleable(dot = true)
/*     */   protected Color tabFocusBackground;
/*     */   @Styleable(dot = true)
/*     */   protected Color tabFocusForeground;
/*     */   private boolean defaults_initialized = false;
/*     */   
/*     */   public static ComponentUI createUI(JComponent c) {
/* 106 */     return FlatUIUtils.canUseSharedUI(c) ? 
/* 107 */       FlatUIUtils.createSharedUI(FlatToggleButtonUI.class, () -> new FlatToggleButtonUI(true)) : 
/* 108 */       new FlatToggleButtonUI(false);
/*     */   }
/*     */   
/*     */   protected FlatToggleButtonUI(boolean shared) {
/* 112 */     super(shared);
/*     */   }
/*     */ 
/*     */   
/*     */   String getStyleType() {
/* 117 */     return "ToggleButton";
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getPropertyPrefix() {
/* 122 */     return "ToggleButton.";
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installDefaults(AbstractButton b) {
/* 127 */     super.installDefaults(b);
/*     */     
/* 129 */     if (!this.defaults_initialized) {
/* 130 */       this.tabUnderlineHeight = UIManager.getInt("ToggleButton.tab.underlineHeight");
/* 131 */       this.tabUnderlineColor = UIManager.getColor("ToggleButton.tab.underlineColor");
/* 132 */       this.tabDisabledUnderlineColor = UIManager.getColor("ToggleButton.tab.disabledUnderlineColor");
/* 133 */       this.tabSelectedBackground = UIManager.getColor("ToggleButton.tab.selectedBackground");
/* 134 */       this.tabSelectedForeground = UIManager.getColor("ToggleButton.tab.selectedForeground");
/* 135 */       this.tabHoverBackground = UIManager.getColor("ToggleButton.tab.hoverBackground");
/* 136 */       this.tabHoverForeground = UIManager.getColor("ToggleButton.tab.hoverForeground");
/* 137 */       this.tabFocusBackground = UIManager.getColor("ToggleButton.tab.focusBackground");
/* 138 */       this.tabFocusForeground = UIManager.getColor("ToggleButton.tab.focusForeground");
/*     */       
/* 140 */       this.defaults_initialized = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallDefaults(AbstractButton b) {
/* 146 */     super.uninstallDefaults(b);
/* 147 */     this.defaults_initialized = false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void propertyChange(AbstractButton b, PropertyChangeEvent e) {
/* 152 */     super.propertyChange(b, e);
/*     */     
/* 154 */     switch (e.getPropertyName()) {
/*     */       case "JButton.buttonType":
/* 156 */         if ("tab".equals(e.getOldValue()) || "tab".equals(e.getNewValue())) {
/* 157 */           MigLayoutVisualPadding.uninstall(b);
/* 158 */           MigLayoutVisualPadding.install(b);
/* 159 */           b.revalidate();
/*     */         } 
/*     */         
/* 162 */         b.repaint();
/*     */         break;
/*     */       
/*     */       case "JToggleButton.tab.underlinePlacement":
/*     */       case "JToggleButton.tab.underlineHeight":
/*     */       case "JToggleButton.tab.underlineColor":
/*     */       case "JToggleButton.tab.selectedBackground":
/* 169 */         b.repaint();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object applyStyleProperty(AbstractButton b, String key, Object value) {
/* 177 */     if (key.startsWith("help.")) {
/* 178 */       throw new FlatStylingSupport.UnknownStyleException(key);
/*     */     }
/* 180 */     return super.applyStyleProperty(b, key, value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
/* 186 */     Map<String, Class<?>> infos = super.getStyleableInfos(c);
/* 187 */     infos.keySet().removeIf(s -> s.startsWith("help."));
/* 188 */     return infos;
/*     */   }
/*     */   
/*     */   static boolean isTabButton(Component c) {
/* 192 */     return (c instanceof JToggleButton && "tab".equals(getButtonTypeStr((JToggleButton)c)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintBackground(Graphics g, JComponent c) {
/* 197 */     if (isTabButton(c)) {
/* 198 */       int height = c.getHeight();
/* 199 */       int width = c.getWidth();
/* 200 */       boolean selected = ((AbstractButton)c).isSelected();
/* 201 */       Color enabledColor = selected ? FlatClientProperties.clientPropertyColor(c, "JToggleButton.tab.selectedBackground", this.tabSelectedBackground) : null;
/*     */ 
/*     */       
/* 204 */       if (enabledColor == null) {
/* 205 */         Color bg = c.getBackground();
/* 206 */         if (isCustomBackground(bg)) {
/* 207 */           enabledColor = bg;
/*     */         }
/*     */       } 
/*     */       
/* 211 */       Color background = buttonStateColor(c, enabledColor, null, this.tabFocusBackground, this.tabHoverBackground, null);
/*     */       
/* 213 */       if (background != null) {
/* 214 */         g.setColor(background);
/* 215 */         g.fillRect(0, 0, width, height);
/*     */       } 
/*     */ 
/*     */       
/* 219 */       if (selected) {
/* 220 */         int underlineThickness = UIScale.scale(FlatClientProperties.clientPropertyInt(c, "JToggleButton.tab.underlineHeight", this.tabUnderlineHeight));
/* 221 */         g.setColor(c.isEnabled() ? 
/* 222 */             FlatClientProperties.clientPropertyColor(c, "JToggleButton.tab.underlineColor", this.tabUnderlineColor) : 
/* 223 */             this.tabDisabledUnderlineColor);
/* 224 */         int placement = FlatClientProperties.clientPropertyInt(c, "JToggleButton.tab.underlinePlacement", 3);
/* 225 */         switch (placement) {
/*     */           case 1:
/* 227 */             g.fillRect(0, 0, width, underlineThickness);
/*     */             return;
/*     */           case 2:
/* 230 */             g.fillRect(0, 0, underlineThickness, height);
/*     */             return;
/*     */           case 4:
/* 233 */             g.fillRect(width - underlineThickness, 0, underlineThickness, height);
/*     */             return;
/*     */         } 
/*     */         
/* 237 */         g.fillRect(0, height - underlineThickness, width, underlineThickness);
/*     */       } 
/*     */     } else {
/*     */       
/* 241 */       super.paintBackground(g, c);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Color getForeground(JComponent c) {
/* 246 */     if (isTabButton(c)) {
/* 247 */       if (!c.isEnabled()) {
/* 248 */         return this.disabledText;
/*     */       }
/* 250 */       if (this.tabSelectedForeground != null && ((AbstractButton)c).isSelected()) {
/* 251 */         return this.tabSelectedForeground;
/*     */       }
/* 253 */       return buttonStateColor(c, c.getForeground(), this.disabledText, this.tabFocusForeground, this.tabHoverForeground, null);
/*     */     } 
/*     */     
/* 256 */     return super.getForeground(c);
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatToggleButtonUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */