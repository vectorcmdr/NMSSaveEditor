package com.formdev.flatlaf.json;

public class Location {
   public final int offset;
   public final int line;
   public final int column;

   Location(int offset, int line, int column) {
      this.offset = offset;
      this.column = column;
      this.line = line;
   }

   public String toString() {
      return this.line + ":" + this.column;
   }

   public int hashCode() {
      return this.offset;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         Location other = (Location)obj;
         return this.offset == other.offset && this.column == other.column && this.line == other.line;
      }
   }
}
