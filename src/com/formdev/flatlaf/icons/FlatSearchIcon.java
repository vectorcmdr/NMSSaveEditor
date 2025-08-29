/*    */ package com.formdev.flatlaf.icons;
/*    */ 
/*    */ import com.formdev.flatlaf.ui.FlatButtonUI;
/*    */ import com.formdev.flatlaf.ui.FlatStylingSupport;
/*    */ import com.formdev.flatlaf.ui.FlatStylingSupport.Styleable;
/*    */ import com.formdev.flatlaf.ui.FlatUIUtils;
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.geom.Area;
/*    */ import java.awt.geom.Ellipse2D;
/*    */ import java.util.Map;
/*    */ import javax.swing.UIManager;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FlatSearchIcon
/*    */   extends FlatAbstractIcon
/*    */ {
/*    */   @Styleable
/* 44 */   protected Color searchIconColor = UIManager.getColor("SearchField.searchIconColor"); @Styleable
/* 45 */   protected Color searchIconHoverColor = UIManager.getColor("SearchField.searchIconHoverColor"); @Styleable
/* 46 */   protected Color searchIconPressedColor = UIManager.getColor("SearchField.searchIconPressedColor");
/*    */   
/*    */   private final boolean ignoreButtonState;
/*    */   private Area area;
/*    */   
/*    */   public FlatSearchIcon() {
/* 52 */     this(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public FlatSearchIcon(boolean ignoreButtonState) {
/* 57 */     super(16, 16, null);
/* 58 */     this.ignoreButtonState = ignoreButtonState;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object applyStyleProperty(String key, Object value) {
/* 63 */     return FlatStylingSupport.applyToAnnotatedObject(this, key, value);
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, Class<?>> getStyleableInfos() {
/* 68 */     return FlatStylingSupport.getAnnotatedStyleableInfos(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getStyleableValue(String key) {
/* 73 */     return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void paintIcon(Component c, Graphics2D g) {
/* 87 */     g.setColor(this.ignoreButtonState ? 
/* 88 */         this.searchIconColor : 
/* 89 */         FlatButtonUI.buttonStateColor(c, this.searchIconColor, this.searchIconColor, null, this.searchIconHoverColor, this.searchIconPressedColor));
/*    */ 
/*    */ 
/*    */     
/* 93 */     if (this.area == null) {
/* 94 */       this.area = new Area(new Ellipse2D.Float(2.0F, 2.0F, 10.0F, 10.0F));
/* 95 */       this.area.subtract(new Area(new Ellipse2D.Float(3.0F, 3.0F, 8.0F, 8.0F)));
/* 96 */       this.area.add(new Area(FlatUIUtils.createPath(new double[] { 10.813D, 9.75D, 14.0D, 12.938D, 12.938D, 14.0D, 9.75D, 10.813D })));
/*    */     } 
/* 98 */     g.fill(this.area);
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\icons\FlatSearchIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */