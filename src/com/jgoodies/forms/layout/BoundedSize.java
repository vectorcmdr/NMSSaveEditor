package com.jgoodies.forms.layout;

import java.awt.Container;
import java.io.Serializable;
import java.util.List;

public final class BoundedSize implements Size, Serializable {
   private final Size basis;
   private final Size lowerBound;
   private final Size upperBound;

   public BoundedSize(Size basis, Size lowerBound, Size upperBound) {
      if (basis == null) {
         throw new NullPointerException("The basis of a bounded size must not be null.");
      } else if (lowerBound == null && upperBound == null) {
         throw new IllegalArgumentException("A bounded size must have a non-null lower or upper bound.");
      } else {
         this.basis = basis;
         this.lowerBound = lowerBound;
         this.upperBound = upperBound;
      }
   }

   public Size getBasis() {
      return this.basis;
   }

   public Size getLowerBound() {
      return this.lowerBound;
   }

   public Size getUpperBound() {
      return this.upperBound;
   }

   public int maximumSize(Container container, List components, FormLayout.Measure minMeasure, FormLayout.Measure prefMeasure, FormLayout.Measure defaultMeasure) {
      int size = this.basis.maximumSize(container, components, minMeasure, prefMeasure, defaultMeasure);
      if (this.lowerBound != null) {
         size = Math.max(size, this.lowerBound.maximumSize(container, components, minMeasure, prefMeasure, defaultMeasure));
      }

      if (this.upperBound != null) {
         size = Math.min(size, this.upperBound.maximumSize(container, components, minMeasure, prefMeasure, defaultMeasure));
      }

      return size;
   }

   public boolean compressible() {
      return this.getBasis().compressible();
   }

   public boolean equals(Object object) {
      if (this == object) {
         return true;
      } else if (!(object instanceof BoundedSize)) {
         return false;
      } else {
         BoundedSize size = (BoundedSize)object;
         return this.basis.equals(size.basis) && (this.lowerBound == null && size.lowerBound == null || this.lowerBound != null && this.lowerBound.equals(size.lowerBound)) && (this.upperBound == null && size.upperBound == null || this.upperBound != null && this.upperBound.equals(size.upperBound));
      }
   }

   public int hashCode() {
      int hashValue = this.basis.hashCode();
      if (this.lowerBound != null) {
         hashValue = hashValue * 37 + this.lowerBound.hashCode();
      }

      if (this.upperBound != null) {
         hashValue = hashValue * 37 + this.upperBound.hashCode();
      }

      return hashValue;
   }

   public String toString() {
      return this.encode();
   }

   public String encode() {
      StringBuffer buffer = new StringBuffer("[");
      if (this.lowerBound != null) {
         buffer.append(this.lowerBound.encode());
         buffer.append(',');
      }

      buffer.append(this.basis.encode());
      if (this.upperBound != null) {
         buffer.append(',');
         buffer.append(this.upperBound.encode());
      }

      buffer.append(']');
      return buffer.toString();
   }
}
