/*    */ package com.formdev.flatlaf;
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
/*    */ 
/*    */ 
/*    */ public enum FlatIconColors
/*    */ {
/* 46 */   ACTIONS_RED(14375008, "Actions.Red", true, false),
/* 47 */   ACTIONS_RED_DARK(13063248, "Actions.Red", false, true),
/* 48 */   ACTIONS_YELLOW(15573504, "Actions.Yellow", true, false),
/* 49 */   ACTIONS_YELLOW_DARK(15771442, "Actions.Yellow", false, true),
/* 50 */   ACTIONS_GREEN(5875817, "Actions.Green", true, false),
/* 51 */   ACTIONS_GREEN_DARK(4824148, "Actions.Green", false, true),
/* 52 */   ACTIONS_BLUE(3710934, "Actions.Blue", true, false),
/* 53 */   ACTIONS_BLUE_DARK(3510980, "Actions.Blue", false, true),
/* 54 */   ACTIONS_GREY(7237230, "Actions.Grey", true, false),
/* 55 */   ACTIONS_GREY_DARK(11514291, "Actions.Grey", false, true),
/* 56 */   ACTIONS_GREYINLINE(8358801, "Actions.GreyInline", true, false),
/* 57 */   ACTIONS_GREYINLINE_DARK(8358801, "Actions.GreyInline", false, true),
/*    */ 
/*    */ 
/*    */   
/* 61 */   OBJECTS_GREY(10135472, "Objects.Grey"),
/* 62 */   OBJECTS_BLUE(4241120, "Objects.Blue"),
/* 63 */   OBJECTS_GREEN(6468931, "Objects.Green"),
/* 64 */   OBJECTS_YELLOW(16035645, "Objects.Yellow"),
/* 65 */   OBJECTS_YELLOW_DARK(14263107, "Objects.YellowDark"),
/* 66 */   OBJECTS_PURPLE(12164088, "Objects.Purple"),
/* 67 */   OBJECTS_PINK(16354206, "Objects.Pink"),
/* 68 */   OBJECTS_RED(15885602, "Objects.Red"),
/* 69 */   OBJECTS_RED_STATUS(14701909, "Objects.RedStatus"),
/* 70 */   OBJECTS_GREEN_ANDROID(10798649, "Objects.GreenAndroid"),
/* 71 */   OBJECTS_BLACK_TEXT(2301728, "Objects.BlackText");
/*    */ 
/*    */   
/*    */   public final int rgb;
/*    */   
/*    */   public final String key;
/*    */   
/*    */   public final boolean light;
/*    */   
/*    */   public final boolean dark;
/*    */ 
/*    */   
/*    */   FlatIconColors(int rgb, String key, boolean light, boolean dark) {
/* 84 */     this.rgb = rgb;
/* 85 */     this.key = key;
/* 86 */     this.light = light;
/* 87 */     this.dark = dark;
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\FlatIconColors.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */