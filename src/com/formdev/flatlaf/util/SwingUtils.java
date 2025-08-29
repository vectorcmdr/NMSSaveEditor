/*    */ package com.formdev.flatlaf.util;
/*    */ 
/*    */ import java.awt.Component;
/*    */ import java.awt.Container;
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
/*    */ public class SwingUtils
/*    */ {
/*    */   public static <T extends Component> T getComponentByName(Container parent, String name) {
/* 37 */     for (Component child : parent.getComponents()) {
/* 38 */       if (name.equals(child.getName())) {
/* 39 */         return (T)child;
/*    */       }
/* 41 */       if (child instanceof Container) {
/* 42 */         T c = getComponentByName((Container)child, name);
/* 43 */         if (c != null) {
/* 44 */           return c;
/*    */         }
/*    */       } 
/*    */     } 
/* 48 */     return null;
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\util\SwingUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */