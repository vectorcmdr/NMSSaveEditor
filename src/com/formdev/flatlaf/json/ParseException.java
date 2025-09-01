package com.formdev.flatlaf.json;

public class ParseException extends RuntimeException {
   private final Location location;

   ParseException(String message, Location location) {
      super(message + " at " + location);
      this.location = location;
   }

   public Location getLocation() {
      return this.location;
   }

   /** @deprecated */
   @Deprecated
   public int getOffset() {
      return this.location.offset;
   }

   /** @deprecated */
   @Deprecated
   public int getLine() {
      return this.location.line;
   }

   /** @deprecated */
   @Deprecated
   public int getColumn() {
      return this.location.column;
   }
}
