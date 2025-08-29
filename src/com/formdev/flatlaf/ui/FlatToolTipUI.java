/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.util.HiDPIUtils;
/*     */ import com.formdev.flatlaf.util.StringUtils;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Insets;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.util.List;
/*     */ import java.util.function.Supplier;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JToolTip;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.basic.BasicToolTipUI;
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
/*     */ public class FlatToolTipUI
/*     */   extends BasicToolTipUI
/*     */   implements PropertyChangeListener
/*     */ {
/*     */   public static ComponentUI createUI(JComponent c) {
/*  56 */     return FlatUIUtils.createSharedUI(FlatToolTipUI.class, FlatToolTipUI::new);
/*     */   }
/*     */ 
/*     */   
/*     */   public void installUI(JComponent c) {
/*  61 */     super.installUI(c);
/*     */ 
/*     */     
/*  64 */     FlatLabelUI.updateHTMLRenderer(c, ((JToolTip)c).getTipText(), false);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installListeners(JComponent c) {
/*  69 */     super.installListeners(c);
/*     */     
/*  71 */     c.addPropertyChangeListener(this);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallListeners(JComponent c) {
/*  76 */     super.uninstallListeners(c);
/*     */     
/*  78 */     c.removePropertyChangeListener(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void propertyChange(PropertyChangeEvent e) {
/*  84 */     String name = e.getPropertyName();
/*  85 */     if (name == "tiptext" || name == "font" || name == "foreground") {
/*  86 */       JToolTip toolTip = (JToolTip)e.getSource();
/*  87 */       FlatLabelUI.updateHTMLRenderer(toolTip, toolTip.getTipText(), false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize(JComponent c) {
/*  94 */     String text = ((JToolTip)c).getTipText();
/*  95 */     if (text == null || text.isEmpty()) {
/*  96 */       return new Dimension();
/*     */     }
/*  98 */     if (isMultiLine(c)) {
/*  99 */       FontMetrics fm = c.getFontMetrics(c.getFont());
/* 100 */       Insets insets = c.getInsets();
/*     */       
/* 102 */       List<String> lines = StringUtils.split(((JToolTip)c).getTipText(), '\n');
/* 103 */       int width = 0;
/* 104 */       int height = fm.getHeight() * Math.max(lines.size(), 1);
/* 105 */       for (String line : lines) {
/* 106 */         width = Math.max(width, SwingUtilities.computeStringWidth(fm, line));
/*     */       }
/* 108 */       return new Dimension(insets.left + width + insets.right + 6, insets.top + height + insets.bottom);
/*     */     } 
/* 110 */     return super.getPreferredSize(c);
/*     */   }
/*     */ 
/*     */   
/*     */   public void paint(Graphics g, JComponent c) {
/* 115 */     if (isMultiLine(c)) {
/* 116 */       FontMetrics fm = c.getFontMetrics(c.getFont());
/* 117 */       Insets insets = c.getInsets();
/*     */       
/* 119 */       g.setColor(c.getForeground());
/*     */       
/* 121 */       List<String> lines = StringUtils.split(((JToolTip)c).getTipText(), '\n');
/*     */       
/* 123 */       int x = insets.left + 3;
/* 124 */       int x2 = c.getWidth() - insets.right - 3;
/* 125 */       int y = insets.top - fm.getDescent();
/* 126 */       int lineHeight = fm.getHeight();
/* 127 */       JComponent comp = ((JToolTip)c).getComponent();
/* 128 */       boolean leftToRight = ((comp != null) ? comp : c).getComponentOrientation().isLeftToRight();
/* 129 */       for (String line : lines) {
/* 130 */         y += lineHeight;
/* 131 */         FlatUIUtils.drawString(c, g, line, leftToRight ? x : (x2 - SwingUtilities.computeStringWidth(fm, line)), y);
/*     */       } 
/*     */     } else {
/* 134 */       super.paint(HiDPIUtils.createGraphicsTextYCorrection((Graphics2D)g), c);
/*     */     } 
/*     */   }
/*     */   private boolean isMultiLine(JComponent c) {
/* 138 */     String text = ((JToolTip)c).getTipText();
/* 139 */     return (c.getClientProperty("html") == null && text != null && text.indexOf('\n') >= 0);
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatToolTipUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */