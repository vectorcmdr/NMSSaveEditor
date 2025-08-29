/*    */ package com.formdev.flatlaf.json;
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
/*    */ public class Location
/*    */ {
/*    */   public final int offset;
/*    */   public final int line;
/*    */   public final int column;
/*    */   
/*    */   Location(int offset, int line, int column) {
/* 49 */     this.offset = offset;
/* 50 */     this.column = column;
/* 51 */     this.line = line;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 56 */     return this.line + ":" + this.column;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 61 */     return this.offset;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 66 */     if (this == obj) {
/* 67 */       return true;
/*    */     }
/* 69 */     if (obj == null) {
/* 70 */       return false;
/*    */     }
/* 72 */     if (getClass() != obj.getClass()) {
/* 73 */       return false;
/*    */     }
/* 75 */     Location other = (Location)obj;
/* 76 */     return (this.offset == other.offset && this.column == other.column && this.line == other.line);
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\json\Location.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */