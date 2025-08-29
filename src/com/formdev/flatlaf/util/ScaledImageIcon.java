/*     */ package com.formdev.flatlaf.util;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ImageObserver;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.ImageIcon;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ScaledImageIcon
/*     */   implements Icon
/*     */ {
/*     */   private final ImageIcon imageIcon;
/*     */   private final int iconWidth;
/*     */   private final int iconHeight;
/*     */   private double lastSystemScaleFactor;
/*     */   private float lastUserScaleFactor;
/*     */   private Image lastImage;
/*     */   
/*     */   public ScaledImageIcon(ImageIcon imageIcon) {
/*  49 */     this(imageIcon, imageIcon.getIconWidth(), imageIcon.getIconHeight());
/*     */   }
/*     */   
/*     */   public ScaledImageIcon(ImageIcon imageIcon, int iconWidth, int iconHeight) {
/*  53 */     this.imageIcon = imageIcon;
/*  54 */     this.iconWidth = iconWidth;
/*  55 */     this.iconHeight = iconHeight;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIconWidth() {
/*  60 */     return UIScale.scale(this.iconWidth);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIconHeight() {
/*  65 */     return UIScale.scale(this.iconHeight);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void paintIcon(Component c, Graphics g, int x, int y) {
/*  76 */     double systemScaleFactor = UIScale.getSystemScaleFactor((Graphics2D)g);
/*  77 */     float userScaleFactor = UIScale.getUserScaleFactor();
/*  78 */     double scaleFactor = systemScaleFactor * userScaleFactor;
/*     */ 
/*     */     
/*  81 */     if (scaleFactor == 1.0D && this.imageIcon != null && this.iconWidth == this.imageIcon.getIconWidth() && this.iconHeight == this.imageIcon.getIconHeight()) {
/*  82 */       this.imageIcon.paintIcon(c, g, x, y);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  87 */     if (systemScaleFactor == this.lastSystemScaleFactor && userScaleFactor == this.lastUserScaleFactor && this.lastImage != null) {
/*     */ 
/*     */ 
/*     */       
/*  91 */       paintLastImage(g, x, y);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  96 */     int destImageWidth = (int)Math.round(this.iconWidth * scaleFactor);
/*  97 */     int destImageHeight = (int)Math.round(this.iconHeight * scaleFactor);
/*     */ 
/*     */     
/* 100 */     Image image = getResolutionVariant(destImageWidth, destImageHeight);
/*     */ 
/*     */     
/* 103 */     int imageWidth = -1;
/* 104 */     int imageHeight = -1;
/*     */     
/* 106 */     if (image != null) {
/* 107 */       imageWidth = image.getWidth(null);
/* 108 */       imageHeight = image.getHeight(null);
/*     */     } 
/*     */ 
/*     */     
/* 112 */     if (imageWidth < 0 || imageHeight < 0) {
/* 113 */       g.setColor(Color.red);
/* 114 */       g.fillRect(x, y, getIconWidth(), getIconHeight());
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 119 */     if (imageWidth != destImageWidth || imageHeight != destImageHeight) {
/*     */       
/* 121 */       Object scalingInterpolation = RenderingHints.VALUE_INTERPOLATION_BICUBIC;
/* 122 */       float imageScaleFactor = destImageWidth / imageWidth;
/* 123 */       if ((int)imageScaleFactor == imageScaleFactor && imageScaleFactor > 1.0F && imageWidth <= 16 && imageHeight <= 16)
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 130 */         scalingInterpolation = RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR;
/*     */       }
/*     */ 
/*     */       
/* 134 */       BufferedImage bufferedImage = image2bufferedImage(image);
/* 135 */       image = scaleImage(bufferedImage, destImageWidth, destImageHeight, scalingInterpolation);
/*     */     } 
/*     */ 
/*     */     
/* 139 */     this.lastSystemScaleFactor = systemScaleFactor;
/* 140 */     this.lastUserScaleFactor = userScaleFactor;
/* 141 */     this.lastImage = image;
/*     */ 
/*     */     
/* 144 */     paintLastImage(g, x, y);
/*     */   }
/*     */   
/*     */   protected Image getResolutionVariant(int destImageWidth, int destImageHeight) {
/* 148 */     return MultiResolutionImageSupport.getResolutionVariant(this.imageIcon
/* 149 */         .getImage(), destImageWidth, destImageHeight);
/*     */   }
/*     */   
/*     */   private void paintLastImage(Graphics g, int x, int y) {
/* 153 */     if (this.lastSystemScaleFactor > 1.0D) {
/* 154 */       HiDPIUtils.paintAtScale1x((Graphics2D)g, x, y, 100, 100, (g2, x2, y2, width2, height2, scaleFactor2) -> g2.drawImage(this.lastImage, x2, y2, (ImageObserver)null));
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 159 */       g.drawImage(this.lastImage, x, y, null);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private BufferedImage scaleImage(BufferedImage image, int targetWidth, int targetHeight, Object scalingInterpolation) {
/* 171 */     BufferedImage bufferedImage = new BufferedImage(targetWidth, targetHeight, 2);
/* 172 */     Graphics2D g = bufferedImage.createGraphics();
/*     */     try {
/* 174 */       g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, scalingInterpolation);
/* 175 */       g.drawImage(image, 0, 0, targetWidth, targetHeight, null);
/*     */     } finally {
/* 177 */       g.dispose();
/*     */     } 
/* 179 */     return bufferedImage;
/*     */   }
/*     */ 
/*     */   
/*     */   private BufferedImage image2bufferedImage(Image image) {
/* 184 */     if (image instanceof BufferedImage) {
/* 185 */       return (BufferedImage)image;
/*     */     }
/*     */     
/* 188 */     BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), 2);
/* 189 */     Graphics2D g = bufferedImage.createGraphics();
/*     */     try {
/* 191 */       g.drawImage(image, 0, 0, (ImageObserver)null);
/*     */     } finally {
/* 193 */       g.dispose();
/*     */     } 
/* 195 */     return bufferedImage;
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\util\ScaledImageIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */