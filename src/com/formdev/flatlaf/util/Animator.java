package com.formdev.flatlaf.util;

import com.formdev.flatlaf.FlatSystemProperties;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.Timer;

public class Animator {
   private int duration;
   private int resolution;
   private Animator.Interpolator interpolator;
   private final ArrayList<Animator.TimingTarget> targets;
   private final Runnable endRunnable;
   private boolean running;
   private boolean hasBegun;
   private boolean timeToStop;
   private long startTime;
   private Timer timer;

   public static boolean useAnimation() {
      return FlatSystemProperties.getBoolean("flatlaf.animation", true);
   }

   public Animator(int duration) {
      this(duration, (Animator.TimingTarget)null, (Runnable)null);
   }

   public Animator(int duration, Animator.TimingTarget target) {
      this(duration, target, (Runnable)null);
   }

   public Animator(int duration, Animator.TimingTarget target, Runnable endRunnable) {
      this.resolution = 10;
      this.targets = new ArrayList();
      this.setDuration(duration);
      this.addTarget(target);
      this.endRunnable = endRunnable;
   }

   public int getDuration() {
      return this.duration;
   }

   public void setDuration(int duration) {
      this.throwExceptionIfRunning();
      if (duration <= 0) {
         throw new IllegalArgumentException();
      } else {
         this.duration = duration;
      }
   }

   public int getResolution() {
      return this.resolution;
   }

   public void setResolution(int resolution) {
      this.throwExceptionIfRunning();
      if (resolution <= 0) {
         throw new IllegalArgumentException();
      } else {
         this.resolution = resolution;
      }
   }

   public Animator.Interpolator getInterpolator() {
      return this.interpolator;
   }

   public void setInterpolator(Animator.Interpolator interpolator) {
      this.throwExceptionIfRunning();
      this.interpolator = interpolator;
   }

   public void addTarget(Animator.TimingTarget target) {
      if (target != null) {
         synchronized(this.targets) {
            if (!this.targets.contains(target)) {
               this.targets.add(target);
            }

         }
      }
   }

   public void removeTarget(Animator.TimingTarget target) {
      synchronized(this.targets) {
         this.targets.remove(target);
      }
   }

   public void start() {
      this.throwExceptionIfRunning();
      this.running = true;
      this.hasBegun = false;
      this.timeToStop = false;
      this.startTime = System.nanoTime() / 1000000L;
      if (this.timer == null) {
         this.timer = new Timer(this.resolution, (e) -> {
            if (!this.hasBegun) {
               this.begin();
               this.hasBegun = true;
            }

            this.timingEvent(this.getTimingFraction());
         });
      } else {
         this.timer.setDelay(this.resolution);
      }

      this.timer.setInitialDelay(0);
      this.timer.start();
   }

   public void stop() {
      this.stop(false);
   }

   public void cancel() {
      this.stop(true);
   }

   private void stop(boolean cancel) {
      if (this.running) {
         if (this.timer != null) {
            this.timer.stop();
         }

         if (!cancel) {
            this.end();
         }

         this.running = false;
         this.timeToStop = false;
      }
   }

   public void restart() {
      this.cancel();
      this.start();
   }

   public boolean isRunning() {
      return this.running;
   }

   private float getTimingFraction() {
      long currentTime = System.nanoTime() / 1000000L;
      long elapsedTime = currentTime - this.startTime;
      this.timeToStop = elapsedTime >= (long)this.duration;
      float fraction = this.clampFraction((float)elapsedTime / (float)this.duration);
      if (this.interpolator != null) {
         fraction = this.clampFraction(this.interpolator.interpolate(fraction));
      }

      return fraction;
   }

   private float clampFraction(float fraction) {
      if (fraction < 0.0F) {
         return 0.0F;
      } else {
         return fraction > 1.0F ? 1.0F : fraction;
      }
   }

   private void timingEvent(float fraction) {
      synchronized(this.targets) {
         Iterator var3 = this.targets.iterator();

         while(true) {
            if (!var3.hasNext()) {
               break;
            }

            Animator.TimingTarget target = (Animator.TimingTarget)var3.next();
            target.timingEvent(fraction);
         }
      }

      if (this.timeToStop) {
         this.stop();
      }

   }

   private void begin() {
      synchronized(this.targets) {
         Iterator var2 = this.targets.iterator();

         while(var2.hasNext()) {
            Animator.TimingTarget target = (Animator.TimingTarget)var2.next();
            target.begin();
         }

      }
   }

   private void end() {
      synchronized(this.targets) {
         Iterator var2 = this.targets.iterator();

         while(true) {
            if (!var2.hasNext()) {
               break;
            }

            Animator.TimingTarget target = (Animator.TimingTarget)var2.next();
            target.end();
         }
      }

      if (this.endRunnable != null) {
         this.endRunnable.run();
      }

   }

   private void throwExceptionIfRunning() {
      if (this.isRunning()) {
         throw new IllegalStateException();
      }
   }

   @FunctionalInterface
   public interface Interpolator {
      float interpolate(float var1);
   }

   @FunctionalInterface
   public interface TimingTarget {
      void timingEvent(float var1);

      default void begin() {
      }

      default void end() {
      }
   }
}
