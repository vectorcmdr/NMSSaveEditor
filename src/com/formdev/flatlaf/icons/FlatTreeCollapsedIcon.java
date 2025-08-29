/*     */ package com.formdev.flatlaf.icons;
/*     */ 
/*     */ import com.formdev.flatlaf.ui.FlatTreeUI;
/*     */ import com.formdev.flatlaf.ui.FlatUIUtils;
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Path2D;
/*     */ import java.util.function.Function;
/*     */ import javax.swing.JTree;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.plaf.TreeUI;
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
/*     */ public class FlatTreeCollapsedIcon
/*     */   extends FlatAbstractIcon
/*     */ {
/*     */   private final boolean chevron;
/*     */   private Path2D path;
/*     */   
/*     */   public FlatTreeCollapsedIcon() {
/*  47 */     this(UIManager.getColor("Tree.icon.collapsedColor"));
/*     */   }
/*     */   
/*     */   FlatTreeCollapsedIcon(Color color) {
/*  51 */     super(11, 11, color);
/*  52 */     this.chevron = FlatUIUtils.isChevron(UIManager.getString("Component.arrowType"));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintIcon(Component c, Graphics2D g) {
/*  57 */     setStyleColorFromTreeUI(c, g);
/*  58 */     rotate(c, g);
/*     */     
/*  60 */     String arrowType = getStyleFromTreeUI(c, ui -> ui.iconArrowType);
/*  61 */     boolean chevron = (arrowType != null) ? FlatUIUtils.isChevron(arrowType) : this.chevron;
/*     */     
/*  63 */     if (chevron) {
/*     */       
/*  65 */       g.setStroke(new BasicStroke(1.0F, 1, 0));
/*  66 */       if (this.path == null)
/*  67 */         this.path = FlatUIUtils.createPath(false, new double[] { 3.5D, 1.5D, 7.5D, 5.5D, 3.5D, 9.5D }); 
/*  68 */       g.draw(this.path);
/*     */     } else {
/*     */       
/*  71 */       if (this.path == null)
/*  72 */         this.path = FlatUIUtils.createPath(new double[] { 2.0D, 1.0D, 2.0D, 10.0D, 10.0D, 5.5D }); 
/*  73 */       g.fill(this.path);
/*     */     } 
/*     */   }
/*     */   
/*     */   void setStyleColorFromTreeUI(Component c, Graphics2D g) {
/*  78 */     setStyleColorFromTreeUI(c, g, ui -> ui.iconCollapsedColor);
/*     */   }
/*     */   
/*     */   void rotate(Component c, Graphics2D g) {
/*  82 */     if (!c.getComponentOrientation().isLeftToRight()) {
/*  83 */       g.rotate(Math.toRadians(180.0D), this.width / 2.0D, this.height / 2.0D);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static <T> T getStyleFromTreeUI(Component c, Function<FlatTreeUI, T> f) {
/*  93 */     JTree tree = (c instanceof JTree) ? (JTree)c : (JTree)SwingUtilities.getAncestorOfClass(JTree.class, c);
/*  94 */     if (tree != null) {
/*  95 */       TreeUI ui = tree.getUI();
/*  96 */       if (ui instanceof FlatTreeUI)
/*  97 */         return f.apply((FlatTreeUI)ui); 
/*     */     } 
/*  99 */     return null;
/*     */   }
/*     */   
/*     */   static void setStyleColorFromTreeUI(Component c, Graphics2D g, Function<FlatTreeUI, Color> f) {
/* 103 */     Color color = getStyleFromTreeUI(c, f);
/* 104 */     if (color != null)
/* 105 */       g.setColor(color); 
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\icons\FlatTreeCollapsedIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */