package com.formdev.flatlaf.util;

import java.awt.Component;
import java.awt.Graphics;
import javax.swing.Icon;
import javax.swing.JComponent;

public interface AnimatedIcon extends Icon {
   default void paintIcon(Component c, Graphics g, int x, int y) {
      AnimatedIcon.AnimationSupport.paintIcon(this, c, g, x, y);
   }

   void paintIconAnimated(Component var1, Graphics var2, int var3, int var4, float var5);

   float getValue(Component var1);

   default boolean isAnimationEnabled() {
      return true;
   }

   default int getAnimationDuration() {
      return 150;
   }

   default int getAnimationResolution() {
      return 10;
   }

   default Animator.Interpolator getAnimationInterpolator() {
      return CubicBezierEasing.STANDARD_EASING;
   }

   default Object getClientPropertyKey() {
      return this.getClass();
   }

   public static class AnimationSupport {
      private float startValue;
      private float targetValue;
      private float animatedValue;
      private float fraction;
      private Animator animator;
      private int x;
      private int y;

      public static void paintIcon(AnimatedIcon icon, Component c, Graphics g, int x, int y) {
         if (!isAnimationEnabled(icon, c)) {
            paintIconImpl(icon, c, g, x, y, (AnimatedIcon.AnimationSupport)null);
         } else {
            JComponent jc = (JComponent)c;
            Object key = icon.getClientPropertyKey();
            AnimatedIcon.AnimationSupport as = (AnimatedIcon.AnimationSupport)jc.getClientProperty(key);
            if (as == null) {
               as = new AnimatedIcon.AnimationSupport();
               as.startValue = as.targetValue = as.animatedValue = icon.getValue(c);
               as.x = x;
               as.y = y;
               jc.putClientProperty(key, as);
            } else {
               float value = icon.getValue(c);
               if (value != as.targetValue) {
                  if (as.animator == null) {
                     as.animator = new Animator(icon.getAnimationDuration(), (fraction) -> {
                        if (!c.isDisplayable()) {
                           as.animator.stop();
                        } else {
                           as.animatedValue = as.startValue + (as.targetValue - as.startValue) * fraction;
                           as.fraction = fraction;
                           c.repaint(as.x, as.y, icon.getIconWidth(), icon.getIconHeight());
                        }
                     }, () -> {
                        as.startValue = as.animatedValue = as.targetValue;
                        as.animator = null;
                     });
                  }

                  if (as.animator.isRunning()) {
                     as.animator.cancel();
                     int duration2 = (int)((float)icon.getAnimationDuration() * as.fraction);
                     if (duration2 > 0) {
                        as.animator.setDuration(duration2);
                     }

                     as.startValue = as.animatedValue;
                  } else {
                     as.animator.setDuration(icon.getAnimationDuration());
                     as.animator.setResolution(icon.getAnimationResolution());
                     as.animator.setInterpolator(icon.getAnimationInterpolator());
                     as.animatedValue = as.startValue;
                  }

                  as.targetValue = value;
                  as.animator.start();
               }

               as.x = x;
               as.y = y;
            }

            paintIconImpl(icon, c, g, x, y, as);
         }
      }

      private static void paintIconImpl(AnimatedIcon icon, Component c, Graphics g, int x, int y, AnimatedIcon.AnimationSupport as) {
         float value = as != null ? as.animatedValue : icon.getValue(c);
         icon.paintIconAnimated(c, g, x, y, value);
      }

      private static boolean isAnimationEnabled(AnimatedIcon icon, Component c) {
         return Animator.useAnimation() && icon.isAnimationEnabled() && c instanceof JComponent;
      }

      public static void saveIconLocation(AnimatedIcon icon, Component c, int x, int y) {
         if (isAnimationEnabled(icon, c)) {
            AnimatedIcon.AnimationSupport as = (AnimatedIcon.AnimationSupport)((JComponent)c).getClientProperty(icon.getClientPropertyKey());
            if (as != null) {
               as.x = x;
               as.y = y;
            }

         }
      }
   }
}
