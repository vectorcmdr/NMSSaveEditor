package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.MultiResolutionImageSupport;
import com.formdev.flatlaf.util.ScaledImageIcon;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.ImageIcon;

public class FlatTitlePaneIcon extends ScaledImageIcon {
   private final List<Image> images;

   public FlatTitlePaneIcon(List<Image> images, Dimension size) {
      super((ImageIcon)null, size.width, size.height);
      this.images = images;
   }

   protected Image getResolutionVariant(int destImageWidth, int destImageHeight) {
      List<Image> allImages = new ArrayList();
      Iterator var4 = this.images.iterator();

      Image image;
      while(var4.hasNext()) {
         image = (Image)var4.next();
         if (MultiResolutionImageSupport.isMultiResolutionImage(image)) {
            allImages.add(MultiResolutionImageSupport.getResolutionVariant(image, destImageWidth, destImageHeight));
         } else {
            allImages.add(image);
         }
      }

      if (allImages.size() == 1) {
         return (Image)allImages.get(0);
      } else {
         allImages.sort((image1, image2) -> {
            return image1.getWidth((ImageObserver)null) - image2.getWidth((ImageObserver)null);
         });
         var4 = allImages.iterator();

         do {
            if (!var4.hasNext()) {
               return (Image)allImages.get(allImages.size() - 1);
            }

            image = (Image)var4.next();
         } while(destImageWidth > image.getWidth((ImageObserver)null) || destImageHeight > image.getHeight((ImageObserver)null));

         return image;
      }
   }
}
