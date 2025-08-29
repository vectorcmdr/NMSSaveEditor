/*    */ package com.formdev.flatlaf.json;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.Reader;
/*    */ import java.util.ArrayList;
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
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
/*    */ public class Json
/*    */ {
/*    */   public static Object parse(Reader reader) throws IOException, ParseException {
/* 34 */     DefaultHandler handler = new DefaultHandler();
/* 35 */     (new JsonParser(handler)).parse(reader);
/* 36 */     return handler.getValue();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   static class DefaultHandler
/*    */     extends JsonHandler<List<Object>, Map<String, Object>>
/*    */   {
/*    */     private Object value;
/*    */ 
/*    */     
/*    */     public List<Object> startArray() {
/* 48 */       return new ArrayList();
/*    */     }
/*    */ 
/*    */     
/*    */     public Map<String, Object> startObject() {
/* 53 */       return new LinkedHashMap<>();
/*    */     }
/*    */ 
/*    */     
/*    */     public void endNull() {
/* 58 */       this.value = "null";
/*    */     }
/*    */ 
/*    */     
/*    */     public void endBoolean(boolean bool) {
/* 63 */       this.value = bool ? "true" : "false";
/*    */     }
/*    */ 
/*    */     
/*    */     public void endString(String string) {
/* 68 */       this.value = string;
/*    */     }
/*    */ 
/*    */     
/*    */     public void endNumber(String string) {
/* 73 */       this.value = string;
/*    */     }
/*    */ 
/*    */     
/*    */     public void endArray(List<Object> array) {
/* 78 */       this.value = array;
/*    */     }
/*    */ 
/*    */     
/*    */     public void endObject(Map<String, Object> object) {
/* 83 */       this.value = object;
/*    */     }
/*    */ 
/*    */     
/*    */     public void endArrayValue(List<Object> array) {
/* 88 */       array.add(this.value);
/*    */     }
/*    */ 
/*    */     
/*    */     public void endObjectValue(Map<String, Object> object, String name) {
/* 93 */       object.put(name, this.value);
/*    */     }
/*    */     
/*    */     Object getValue() {
/* 97 */       return this.value;
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\json\Json.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */