/*     */ package com.jgoodies.forms.layout;
/*     */ 
/*     */ import java.awt.Container;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class BoundedSize
/*     */   implements Size, Serializable
/*     */ {
/*     */   private final Size basis;
/*     */   private final Size lowerBound;
/*     */   private final Size upperBound;
/*     */   
/*     */   public BoundedSize(Size basis, Size lowerBound, Size upperBound) {
/*  83 */     if (basis == null)
/*  84 */       throw new NullPointerException("The basis of a bounded size must not be null."); 
/*  85 */     if (lowerBound == null && upperBound == null)
/*  86 */       throw new IllegalArgumentException("A bounded size must have a non-null lower or upper bound."); 
/*  87 */     this.basis = basis;
/*  88 */     this.lowerBound = lowerBound;
/*  89 */     this.upperBound = upperBound;
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
/*     */ 
/*     */   
/*     */   public Size getBasis() {
/* 103 */     return this.basis;
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
/*     */   public Size getLowerBound() {
/* 115 */     return this.lowerBound;
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
/*     */   public Size getUpperBound() {
/* 127 */     return this.upperBound;
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
/*     */   public int maximumSize(Container container, List components, FormLayout.Measure minMeasure, FormLayout.Measure prefMeasure, FormLayout.Measure defaultMeasure) {
/* 153 */     int size = this.basis.maximumSize(container, components, minMeasure, prefMeasure, defaultMeasure);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 158 */     if (this.lowerBound != null) {
/* 159 */       size = Math.max(size, this.lowerBound.maximumSize(container, components, minMeasure, prefMeasure, defaultMeasure));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 166 */     if (this.upperBound != null) {
/* 167 */       size = Math.min(size, this.upperBound.maximumSize(container, components, minMeasure, prefMeasure, defaultMeasure));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 174 */     return size;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean compressible() {
/* 190 */     return getBasis().compressible();
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 206 */     if (this == object)
/* 207 */       return true; 
/* 208 */     if (!(object instanceof BoundedSize))
/* 209 */       return false; 
/* 210 */     BoundedSize size = (BoundedSize)object;
/* 211 */     return (this.basis.equals(size.basis) && ((this.lowerBound == null && size.lowerBound == null) || (this.lowerBound != null && this.lowerBound.equals(size.lowerBound))) && ((this.upperBound == null && size.upperBound == null) || (this.upperBound != null && this.upperBound.equals(size.upperBound))));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 228 */     int hashValue = this.basis.hashCode();
/* 229 */     if (this.lowerBound != null) {
/* 230 */       hashValue = hashValue * 37 + this.lowerBound.hashCode();
/*     */     }
/* 232 */     if (this.upperBound != null) {
/* 233 */       hashValue = hashValue * 37 + this.upperBound.hashCode();
/*     */     }
/* 235 */     return hashValue;
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
/*     */   
/*     */   public String toString() {
/* 248 */     return encode();
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
/*     */   public String encode() {
/* 260 */     StringBuffer buffer = new StringBuffer("[");
/* 261 */     if (this.lowerBound != null) {
/* 262 */       buffer.append(this.lowerBound.encode());
/* 263 */       buffer.append(',');
/*     */     } 
/* 265 */     buffer.append(this.basis.encode());
/* 266 */     if (this.upperBound != null) {
/* 267 */       buffer.append(',');
/* 268 */       buffer.append(this.upperBound.encode());
/*     */     } 
/* 270 */     buffer.append(']');
/* 271 */     return buffer.toString();
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\jgoodies\forms\layout\BoundedSize.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */