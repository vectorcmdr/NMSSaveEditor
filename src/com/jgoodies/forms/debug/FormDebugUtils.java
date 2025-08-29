/*     */ package com.jgoodies.forms.debug;
/*     */ 
/*     */ import com.jgoodies.forms.layout.CellConstraints;
/*     */ import com.jgoodies.forms.layout.ColumnSpec;
/*     */ import com.jgoodies.forms.layout.FormLayout;
/*     */ import com.jgoodies.forms.layout.RowSpec;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import javax.swing.JLabel;
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
/*     */ public final class FormDebugUtils
/*     */ {
/*     */   public static void dumpAll(Container container) {
/*  81 */     if (!(container.getLayout() instanceof FormLayout)) {
/*  82 */       System.out.println("The container's layout is not a FormLayout.");
/*     */       return;
/*     */     } 
/*  85 */     FormLayout layout = (FormLayout)container.getLayout();
/*  86 */     dumpColumnSpecs(layout);
/*  87 */     dumpRowSpecs(layout);
/*  88 */     System.out.println();
/*  89 */     dumpColumnGroups(layout);
/*  90 */     dumpRowGroups(layout);
/*  91 */     System.out.println();
/*  92 */     dumpConstraints(container);
/*  93 */     dumpGridBounds(container);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void dumpColumnSpecs(FormLayout layout) {
/* 103 */     System.out.print("COLUMN SPECS:");
/* 104 */     for (int col = 1; col <= layout.getColumnCount(); col++) {
/* 105 */       ColumnSpec colSpec = layout.getColumnSpec(col);
/* 106 */       System.out.print(colSpec.toShortString());
/* 107 */       if (col < layout.getColumnCount())
/* 108 */         System.out.print(", "); 
/*     */     } 
/* 110 */     System.out.println();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void dumpRowSpecs(FormLayout layout) {
/* 120 */     System.out.print("ROW SPECS:   ");
/* 121 */     for (int row = 1; row <= layout.getRowCount(); row++) {
/* 122 */       RowSpec rowSpec = layout.getRowSpec(row);
/* 123 */       System.out.print(rowSpec.toShortString());
/* 124 */       if (row < layout.getRowCount())
/* 125 */         System.out.print(", "); 
/*     */     } 
/* 127 */     System.out.println();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void dumpColumnGroups(FormLayout layout) {
/* 137 */     dumpGroups("COLUMN GROUPS: ", layout.getColumnGroups());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void dumpRowGroups(FormLayout layout) {
/* 147 */     dumpGroups("ROW GROUPS:    ", layout.getRowGroups());
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
/*     */   public static void dumpGridBounds(Container container) {
/* 159 */     System.out.println("GRID BOUNDS");
/* 160 */     dumpGridBounds(getLayoutInfo(container));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void dumpGridBounds(FormLayout.LayoutInfo layoutInfo) {
/* 170 */     System.out.print("COLUMN ORIGINS: ");
/* 171 */     for (int col = 0; col < layoutInfo.columnOrigins.length; col++) {
/* 172 */       System.out.print(layoutInfo.columnOrigins[col] + " ");
/*     */     }
/* 174 */     System.out.println();
/*     */     
/* 176 */     System.out.print("ROW ORIGINS:    ");
/* 177 */     for (int row = 0; row < layoutInfo.rowOrigins.length; row++) {
/* 178 */       System.out.print(layoutInfo.rowOrigins[row] + " ");
/*     */     }
/* 180 */     System.out.println();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void dumpConstraints(Container container) {
/* 190 */     System.out.println("COMPONENT CONSTRAINTS");
/* 191 */     if (!(container.getLayout() instanceof FormLayout)) {
/* 192 */       System.out.println("The container's layout is not a FormLayout.");
/*     */       return;
/*     */     } 
/* 195 */     FormLayout layout = (FormLayout)container.getLayout();
/* 196 */     int childCount = container.getComponentCount();
/* 197 */     for (int i = 0; i < childCount; i++) {
/* 198 */       Component child = container.getComponent(i);
/* 199 */       CellConstraints cc = layout.getConstraints(child);
/* 200 */       String ccString = (cc == null) ? "no constraints" : cc.toShortString(layout);
/*     */ 
/*     */       
/* 203 */       System.out.print(ccString);
/* 204 */       System.out.print("; ");
/* 205 */       String childType = child.getClass().getName();
/* 206 */       System.out.print(childType);
/* 207 */       if (child instanceof JLabel) {
/* 208 */         JLabel label = (JLabel)child;
/* 209 */         System.out.print("      \"" + label.getText() + "\"");
/*     */       } 
/* 211 */       if (child.getName() != null) {
/* 212 */         System.out.print("; name=");
/* 213 */         System.out.print(child.getName());
/*     */       } 
/* 215 */       System.out.println();
/*     */     } 
/* 217 */     System.out.println();
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
/*     */   private static void dumpGroups(String title, int[][] allGroups) {
/* 230 */     System.out.print(title + " {");
/* 231 */     for (int group = 0; group < allGroups.length; group++) {
/* 232 */       int[] groupIndices = allGroups[group];
/* 233 */       System.out.print(" {");
/* 234 */       for (int i = 0; i < groupIndices.length; i++) {
/* 235 */         System.out.print(groupIndices[i]);
/* 236 */         if (i < groupIndices.length - 1) {
/* 237 */           System.out.print(", ");
/*     */         }
/*     */       } 
/* 240 */       System.out.print("} ");
/* 241 */       if (group < allGroups.length - 1) {
/* 242 */         System.out.print(", ");
/*     */       }
/*     */     } 
/* 245 */     System.out.println("}");
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
/*     */   public static FormLayout.LayoutInfo getLayoutInfo(Container container) {
/* 257 */     if (!(container.getLayout() instanceof FormLayout)) {
/* 258 */       throw new IllegalArgumentException("The container must use an instance of FormLayout.");
/*     */     }
/* 260 */     FormLayout layout = (FormLayout)container.getLayout();
/* 261 */     return layout.getLayoutInfo(container);
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\jgoodies\forms\debug\FormDebugUtils.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */