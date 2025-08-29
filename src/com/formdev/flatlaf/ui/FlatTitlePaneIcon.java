/*    */ package com.formdev.flatlaf.ui;
/*    */ 
/*    */ import com.formdev.flatlaf.util.MultiResolutionImageSupport;
/*    */ import com.formdev.flatlaf.util.ScaledImageIcon;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Image;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
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
/*    */ public class FlatTitlePaneIcon
/*    */   extends ScaledImageIcon
/*    */ {
/*    */   private final List<Image> images;
/*    */   
/*    */   public FlatTitlePaneIcon(List<Image> images, Dimension size) {
/* 36 */     super(null, size.width, size.height);
/* 37 */     this.images = images;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected Image getResolutionVariant(int destImageWidth, int destImageHeight) {
/* 43 */     List<Image> allImages = new ArrayList<>();
/* 44 */     for (Image image : this.images) {
/* 45 */       if (MultiResolutionImageSupport.isMultiResolutionImage(image)) {
/* 46 */         allImages.add(MultiResolutionImageSupport.getResolutionVariant(image, destImageWidth, destImageHeight)); continue;
/*    */       } 
/* 48 */       allImages.add(image);
/*    */     } 
/*    */     
/* 51 */     if (allImages.size() == 1) {
/* 52 */       return allImages.get(0);
/*    */     }
/*    */     
/* 55 */     allImages.sort((image1, image2) -> image1.getWidth(null) - image2.getWidth(null));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 60 */     for (Image image : allImages) {
/* 61 */       if (destImageWidth <= image.getWidth(null) && destImageHeight <= image
/* 62 */         .getHeight(null)) {
/* 63 */         return image;
/*    */       }
/*    */     } 
/*    */     
/* 67 */     return allImages.get(allImages.size() - 1);
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatTitlePaneIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */