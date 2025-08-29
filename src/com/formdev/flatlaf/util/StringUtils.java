/*     */ package com.formdev.flatlaf.util;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
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
/*     */ public class StringUtils
/*     */ {
/*     */   public static boolean isEmpty(String string) {
/*  34 */     return (string == null || string.isEmpty());
/*     */   }
/*     */   
/*     */   public static String removeLeading(String string, String leading) {
/*  38 */     return string.startsWith(leading) ? 
/*  39 */       string.substring(leading.length()) : 
/*  40 */       string;
/*     */   }
/*     */   
/*     */   public static String removeTrailing(String string, String trailing) {
/*  44 */     return string.endsWith(trailing) ? 
/*  45 */       string.substring(0, string.length() - trailing.length()) : 
/*  46 */       string;
/*     */   }
/*     */   
/*     */   public static List<String> split(String str, char delim) {
/*  50 */     return split(str, delim, false, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<String> split(String str, char delim, boolean trim, boolean excludeEmpty) {
/*  61 */     int delimIndex = str.indexOf(delim);
/*  62 */     if (delimIndex < 0) {
/*  63 */       if (trim)
/*  64 */         str = str.trim(); 
/*  65 */       return (!excludeEmpty || !str.isEmpty()) ? 
/*  66 */         Collections.<String>singletonList(str) : 
/*  67 */         Collections.<String>emptyList();
/*     */     } 
/*     */     
/*  70 */     ArrayList<String> strs = new ArrayList<>();
/*  71 */     int index = 0;
/*  72 */     while (delimIndex >= 0) {
/*  73 */       add(strs, str, index, delimIndex, trim, excludeEmpty);
/*  74 */       index = delimIndex + 1;
/*  75 */       delimIndex = str.indexOf(delim, index);
/*     */     } 
/*  77 */     add(strs, str, index, str.length(), trim, excludeEmpty);
/*     */     
/*  79 */     return strs;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void add(List<String> strs, String str, int beginIndex, int endIndex, boolean trim, boolean excludeEmpty) {
/*  85 */     if (trim) {
/*  86 */       beginIndex = trimBegin(str, beginIndex, endIndex);
/*  87 */       endIndex = trimEnd(str, beginIndex, endIndex);
/*     */     } 
/*     */     
/*  90 */     if (!excludeEmpty || endIndex > beginIndex) {
/*  91 */       strs.add(str.substring(beginIndex, endIndex));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String substringTrimmed(String str, int beginIndex) {
/* 102 */     return substringTrimmed(str, beginIndex, str.length());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String substringTrimmed(String str, int beginIndex, int endIndex) {
/* 113 */     beginIndex = trimBegin(str, beginIndex, endIndex);
/* 114 */     endIndex = trimEnd(str, beginIndex, endIndex);
/* 115 */     return (endIndex > beginIndex) ? str.substring(beginIndex, endIndex) : "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isTrimmedEmpty(String str) {
/* 125 */     int length = str.length();
/* 126 */     int beginIndex = trimBegin(str, 0, length);
/* 127 */     int endIndex = trimEnd(str, beginIndex, length);
/* 128 */     return (beginIndex >= endIndex);
/*     */   }
/*     */ 
/*     */   
/*     */   private static int trimBegin(String str, int beginIndex, int endIndex) {
/* 133 */     while (beginIndex < endIndex && str.charAt(beginIndex) <= ' ')
/* 134 */       beginIndex++; 
/* 135 */     return beginIndex;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int trimEnd(String str, int beginIndex, int endIndex) {
/* 140 */     while (beginIndex < endIndex && str.charAt(endIndex - 1) <= ' ')
/* 141 */       endIndex--; 
/* 142 */     return endIndex;
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\util\StringUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */