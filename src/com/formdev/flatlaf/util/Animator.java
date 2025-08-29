/*     */ package com.formdev.flatlaf.util;
/*     */ 
/*     */ import com.formdev.flatlaf.FlatSystemProperties;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.Timer;
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
/*     */ public class Animator
/*     */ {
/*     */   private int duration;
/*  32 */   private int resolution = 10;
/*     */   private Interpolator interpolator;
/*  34 */   private final ArrayList<TimingTarget> targets = new ArrayList<>();
/*     */   
/*     */   private final Runnable endRunnable;
/*     */   
/*     */   private boolean running;
/*     */   
/*     */   private boolean hasBegun;
/*     */   
/*     */   private boolean timeToStop;
/*     */   
/*     */   private long startTime;
/*     */   
/*     */   private Timer timer;
/*     */   
/*     */   public static boolean useAnimation() {
/*  49 */     return FlatSystemProperties.getBoolean("flatlaf.animation", true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Animator(int duration) {
/*  60 */     this(duration, null, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Animator(int duration, TimingTarget target) {
/*  71 */     this(duration, target, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Animator(int duration, TimingTarget target, Runnable endRunnable) {
/*  83 */     setDuration(duration);
/*  84 */     addTarget(target);
/*  85 */     this.endRunnable = endRunnable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDuration() {
/*  92 */     return this.duration;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDuration(int duration) {
/* 102 */     throwExceptionIfRunning();
/* 103 */     if (duration <= 0)
/* 104 */       throw new IllegalArgumentException(); 
/* 105 */     this.duration = duration;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getResolution() {
/* 113 */     return this.resolution;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setResolution(int resolution) {
/* 124 */     throwExceptionIfRunning();
/* 125 */     if (resolution <= 0)
/* 126 */       throw new IllegalArgumentException(); 
/* 127 */     this.resolution = resolution;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Interpolator getInterpolator() {
/* 135 */     return this.interpolator;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInterpolator(Interpolator interpolator) {
/* 144 */     throwExceptionIfRunning();
/* 145 */     this.interpolator = interpolator;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addTarget(TimingTarget target) {
/* 154 */     if (target == null) {
/*     */       return;
/*     */     }
/* 157 */     synchronized (this.targets) {
/* 158 */       if (!this.targets.contains(target)) {
/* 159 */         this.targets.add(target);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeTarget(TimingTarget target) {
/* 169 */     synchronized (this.targets) {
/* 170 */       this.targets.remove(target);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void start() {
/* 180 */     throwExceptionIfRunning();
/*     */     
/* 182 */     this.running = true;
/* 183 */     this.hasBegun = false;
/* 184 */     this.timeToStop = false;
/* 185 */     this.startTime = System.nanoTime() / 1000000L;
/*     */     
/* 187 */     if (this.timer == null) {
/* 188 */       this.timer = new Timer(this.resolution, e -> {
/*     */             if (!this.hasBegun) {
/*     */               begin();
/*     */               
/*     */               this.hasBegun = true;
/*     */             } 
/*     */             timingEvent(getTimingFraction());
/*     */           });
/*     */     } else {
/* 197 */       this.timer.setDelay(this.resolution);
/* 198 */     }  this.timer.setInitialDelay(0);
/* 199 */     this.timer.start();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void stop() {
/* 207 */     stop(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void cancel() {
/* 215 */     stop(true);
/*     */   }
/*     */   
/*     */   private void stop(boolean cancel) {
/* 219 */     if (!this.running) {
/*     */       return;
/*     */     }
/* 222 */     if (this.timer != null) {
/* 223 */       this.timer.stop();
/*     */     }
/* 225 */     if (!cancel) {
/* 226 */       end();
/*     */     }
/* 228 */     this.running = false;
/* 229 */     this.timeToStop = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void restart() {
/* 237 */     cancel();
/* 238 */     start();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRunning() {
/* 245 */     return this.running;
/*     */   }
/*     */   
/*     */   private float getTimingFraction() {
/* 249 */     long currentTime = System.nanoTime() / 1000000L;
/* 250 */     long elapsedTime = currentTime - this.startTime;
/* 251 */     this.timeToStop = (elapsedTime >= this.duration);
/*     */     
/* 253 */     float fraction = clampFraction((float)elapsedTime / this.duration);
/* 254 */     if (this.interpolator != null)
/* 255 */       fraction = clampFraction(this.interpolator.interpolate(fraction)); 
/* 256 */     return fraction;
/*     */   }
/*     */   
/*     */   private float clampFraction(float fraction) {
/* 260 */     if (fraction < 0.0F)
/* 261 */       return 0.0F; 
/* 262 */     if (fraction > 1.0F)
/* 263 */       return 1.0F; 
/* 264 */     return fraction;
/*     */   }
/*     */   
/*     */   private void timingEvent(float fraction) {
/* 268 */     synchronized (this.targets) {
/* 269 */       for (TimingTarget target : this.targets) {
/* 270 */         target.timingEvent(fraction);
/*     */       }
/*     */     } 
/* 273 */     if (this.timeToStop)
/* 274 */       stop(); 
/*     */   }
/*     */   
/*     */   private void begin() {
/* 278 */     synchronized (this.targets) {
/* 279 */       for (TimingTarget target : this.targets)
/* 280 */         target.begin(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void end() {
/* 285 */     synchronized (this.targets) {
/* 286 */       for (TimingTarget target : this.targets) {
/* 287 */         target.end();
/*     */       }
/*     */     } 
/* 290 */     if (this.endRunnable != null)
/* 291 */       this.endRunnable.run(); 
/*     */   }
/*     */   
/*     */   private void throwExceptionIfRunning() {
/* 295 */     if (isRunning())
/* 296 */       throw new IllegalStateException(); 
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface Interpolator {
/*     */     float interpolate(float param1Float);
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface TimingTarget {
/*     */     void timingEvent(float param1Float);
/*     */     
/*     */     default void begin() {}
/*     */     
/*     */     default void end() {}
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\util\Animator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */