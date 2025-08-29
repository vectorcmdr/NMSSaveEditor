/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.util.ScaledImageIcon;
/*     */ import com.formdev.flatlaf.util.UIScale;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.Rectangle;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JInternalFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.LookAndFeel;
/*     */ import javax.swing.border.Border;
/*     */ import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
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
/*     */ public class FlatInternalFrameTitlePane
/*     */   extends BasicInternalFrameTitlePane
/*     */ {
/*     */   private JLabel titleLabel;
/*     */   private JPanel buttonPanel;
/*     */   
/*     */   public FlatInternalFrameTitlePane(JInternalFrame f) {
/*  52 */     super(f);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installDefaults() {
/*  57 */     super.installDefaults();
/*     */     
/*  59 */     LookAndFeel.installBorder(this, "InternalFrameTitlePane.border");
/*     */   }
/*     */ 
/*     */   
/*     */   protected PropertyChangeListener createPropertyChangeListener() {
/*  64 */     return new FlatPropertyChangeHandler();
/*     */   }
/*     */ 
/*     */   
/*     */   protected LayoutManager createLayout() {
/*  69 */     return new BorderLayout(UIScale.scale(4), 0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void createButtons() {
/*  74 */     super.createButtons();
/*     */     
/*  76 */     this.iconButton.setContentAreaFilled(false);
/*  77 */     this.maxButton.setContentAreaFilled(false);
/*  78 */     this.closeButton.setContentAreaFilled(false);
/*     */     
/*  80 */     Border emptyBorder = BorderFactory.createEmptyBorder();
/*  81 */     this.iconButton.setBorder(emptyBorder);
/*  82 */     this.maxButton.setBorder(emptyBorder);
/*  83 */     this.closeButton.setBorder(emptyBorder);
/*     */     
/*  85 */     updateButtonsVisibility();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void addSubComponents() {
/*  90 */     this.titleLabel = new JLabel(this.frame.getTitle());
/*  91 */     this.titleLabel.setFont(FlatUIUtils.nonUIResource(getFont()));
/*  92 */     this.titleLabel.setMinimumSize(new Dimension(UIScale.scale(32), 1));
/*  93 */     updateFrameIcon();
/*  94 */     updateColors();
/*     */     
/*  96 */     this.buttonPanel = new JPanel()
/*     */       {
/*     */         public Dimension getPreferredSize() {
/*  99 */           Dimension size = super.getPreferredSize();
/* 100 */           int height = size.height;
/*     */           
/* 102 */           if (!FlatInternalFrameTitlePane.this.iconButton.isVisible())
/* 103 */             height = Math.max(height, (FlatInternalFrameTitlePane.this.iconButton.getPreferredSize()).height); 
/* 104 */           if (!FlatInternalFrameTitlePane.this.maxButton.isVisible())
/* 105 */             height = Math.max(height, (FlatInternalFrameTitlePane.this.maxButton.getPreferredSize()).height); 
/* 106 */           if (!FlatInternalFrameTitlePane.this.closeButton.isVisible())
/* 107 */             height = Math.max(height, (FlatInternalFrameTitlePane.this.closeButton.getPreferredSize()).height); 
/* 108 */           return new Dimension(size.width, height);
/*     */         }
/*     */       };
/* 111 */     this.buttonPanel.setLayout(new BoxLayout(this.buttonPanel, 2));
/* 112 */     this.buttonPanel.setOpaque(false);
/*     */     
/* 114 */     this.buttonPanel.add(this.iconButton);
/* 115 */     this.buttonPanel.add(this.maxButton);
/* 116 */     this.buttonPanel.add(this.closeButton);
/*     */     
/* 118 */     add(this.titleLabel, "Center");
/* 119 */     add(this.buttonPanel, "After");
/*     */   }
/*     */   protected void updateFrameIcon() {
/*     */     ScaledImageIcon scaledImageIcon;
/* 123 */     Icon frameIcon = this.frame.getFrameIcon();
/* 124 */     if (frameIcon != null && (frameIcon.getIconWidth() == 0 || frameIcon.getIconHeight() == 0)) {
/* 125 */       frameIcon = null;
/* 126 */     } else if (frameIcon instanceof ImageIcon) {
/* 127 */       scaledImageIcon = new ScaledImageIcon((ImageIcon)frameIcon);
/* 128 */     }  this.titleLabel.setIcon((Icon)scaledImageIcon);
/*     */   }
/*     */   
/*     */   protected void updateColors() {
/* 132 */     Color background = FlatUIUtils.nonUIResource(this.frame.isSelected() ? this.selectedTitleColor : this.notSelectedTitleColor);
/* 133 */     Color foreground = FlatUIUtils.nonUIResource(this.frame.isSelected() ? this.selectedTextColor : this.notSelectedTextColor);
/*     */     
/* 135 */     this.titleLabel.setForeground(foreground);
/* 136 */     this.iconButton.setBackground(background);
/* 137 */     this.iconButton.setForeground(foreground);
/* 138 */     this.maxButton.setBackground(background);
/* 139 */     this.maxButton.setForeground(foreground);
/* 140 */     this.closeButton.setBackground(background);
/* 141 */     this.closeButton.setForeground(foreground);
/*     */   }
/*     */   
/*     */   protected void updateButtonsVisibility() {
/* 145 */     this.iconButton.setVisible(this.frame.isIconifiable());
/* 146 */     this.maxButton.setVisible(this.frame.isMaximizable());
/* 147 */     this.closeButton.setVisible(this.frame.isClosable());
/*     */   }
/*     */   
/*     */   Rectangle getFrameIconBounds() {
/* 151 */     Icon icon = this.titleLabel.getIcon();
/* 152 */     if (icon == null) {
/* 153 */       return null;
/*     */     }
/* 155 */     int iconWidth = icon.getIconWidth();
/* 156 */     int iconHeight = icon.getIconHeight();
/* 157 */     boolean leftToRight = this.titleLabel.getComponentOrientation().isLeftToRight();
/* 158 */     int x = this.titleLabel.getX() + (leftToRight ? 0 : (this.titleLabel.getWidth() - iconWidth));
/* 159 */     int y = this.titleLabel.getY() + (this.titleLabel.getHeight() - iconHeight) / 2;
/* 160 */     return new Rectangle(x, y, iconWidth, iconHeight);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void assembleSystemMenu() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void showSystemMenu() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void paintComponent(Graphics g) {
/* 179 */     paintTitleBackground(g);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class FlatPropertyChangeHandler
/*     */     extends BasicInternalFrameTitlePane.PropertyChangeHandler
/*     */   {
/*     */     public void propertyChange(PropertyChangeEvent e) {
/* 189 */       switch (e.getPropertyName()) {
/*     */         case "title":
/* 191 */           FlatInternalFrameTitlePane.this.titleLabel.setText(FlatInternalFrameTitlePane.this.frame.getTitle());
/*     */           break;
/*     */         
/*     */         case "frameIcon":
/* 195 */           FlatInternalFrameTitlePane.this.updateFrameIcon();
/*     */           break;
/*     */         
/*     */         case "selected":
/* 199 */           FlatInternalFrameTitlePane.this.updateColors();
/*     */           break;
/*     */         
/*     */         case "iconable":
/*     */         case "maximizable":
/*     */         case "closable":
/* 205 */           FlatInternalFrameTitlePane.this.updateButtonsVisibility();
/* 206 */           FlatInternalFrameTitlePane.this.enableActions();
/* 207 */           FlatInternalFrameTitlePane.this.revalidate();
/* 208 */           FlatInternalFrameTitlePane.this.repaint();
/*     */           return;
/*     */ 
/*     */ 
/*     */         
/*     */         case "componentOrientation":
/* 214 */           FlatInternalFrameTitlePane.this.applyComponentOrientation(FlatInternalFrameTitlePane.this.frame.getComponentOrientation());
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case "opaque":
/*     */           return;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 225 */       super.propertyChange(e);
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatInternalFrameTitlePane.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */