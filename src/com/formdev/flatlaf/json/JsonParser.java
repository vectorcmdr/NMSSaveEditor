/*     */ package com.formdev.flatlaf.json;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;
/*     */ import java.io.StringReader;
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
/*     */ class JsonParser
/*     */ {
/*     */   private static final int MAX_NESTING_LEVEL = 1000;
/*     */   private static final int MIN_BUFFER_SIZE = 10;
/*     */   private static final int DEFAULT_BUFFER_SIZE = 1024;
/*     */   private final JsonHandler<Object, Object> handler;
/*     */   private Reader reader;
/*     */   private char[] buffer;
/*     */   private int bufferOffset;
/*     */   private int index;
/*     */   private int fill;
/*     */   private int line;
/*     */   private int lineOffset;
/*     */   private int current;
/*     */   private StringBuilder captureBuffer;
/*     */   private int captureStart;
/*     */   private int nestingLevel;
/*     */   
/*     */   public JsonParser(JsonHandler<?, ?> handler) {
/*  72 */     if (handler == null) {
/*  73 */       throw new NullPointerException("handler is null");
/*     */     }
/*  75 */     this.handler = (JsonHandler)handler;
/*  76 */     handler.parser = this;
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
/*     */   public void parse(String string) {
/*  89 */     if (string == null) {
/*  90 */       throw new NullPointerException("string is null");
/*     */     }
/*  92 */     int bufferSize = Math.max(10, Math.min(1024, string.length()));
/*     */     try {
/*  94 */       parse(new StringReader(string), bufferSize);
/*  95 */     } catch (IOException exception) {
/*     */       
/*  97 */       throw new RuntimeException(exception);
/*     */     } 
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
/*     */   public void parse(Reader reader) throws IOException {
/* 117 */     parse(reader, 1024);
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
/*     */   public void parse(Reader reader, int buffersize) throws IOException {
/* 138 */     if (reader == null) {
/* 139 */       throw new NullPointerException("reader is null");
/*     */     }
/* 141 */     if (buffersize <= 0) {
/* 142 */       throw new IllegalArgumentException("buffersize is zero or negative");
/*     */     }
/* 144 */     this.reader = reader;
/* 145 */     this.buffer = new char[buffersize];
/* 146 */     this.bufferOffset = 0;
/* 147 */     this.index = 0;
/* 148 */     this.fill = 0;
/* 149 */     this.line = 1;
/* 150 */     this.lineOffset = 0;
/* 151 */     this.current = 0;
/* 152 */     this.captureStart = -1;
/* 153 */     read();
/* 154 */     skipWhiteSpace();
/* 155 */     readValue();
/* 156 */     skipWhiteSpace();
/* 157 */     if (!isEndOfText()) {
/* 158 */       throw error("Unexpected character");
/*     */     }
/*     */   }
/*     */   
/*     */   private void readValue() throws IOException {
/* 163 */     switch (this.current) {
/*     */       case 110:
/* 165 */         readNull();
/*     */         return;
/*     */       case 116:
/* 168 */         readTrue();
/*     */         return;
/*     */       case 102:
/* 171 */         readFalse();
/*     */         return;
/*     */       case 34:
/* 174 */         readString();
/*     */         return;
/*     */       case 91:
/* 177 */         readArray();
/*     */         return;
/*     */       case 123:
/* 180 */         readObject();
/*     */         return;
/*     */       case 45:
/*     */       case 48:
/*     */       case 49:
/*     */       case 50:
/*     */       case 51:
/*     */       case 52:
/*     */       case 53:
/*     */       case 54:
/*     */       case 55:
/*     */       case 56:
/*     */       case 57:
/* 193 */         readNumber();
/*     */         return;
/*     */     } 
/* 196 */     throw expected("value");
/*     */   }
/*     */ 
/*     */   
/*     */   private void readArray() throws IOException {
/* 201 */     Object array = this.handler.startArray();
/* 202 */     read();
/* 203 */     if (++this.nestingLevel > 1000) {
/* 204 */       throw error("Nesting too deep");
/*     */     }
/* 206 */     skipWhiteSpace();
/* 207 */     if (readChar(']')) {
/* 208 */       this.nestingLevel--;
/* 209 */       this.handler.endArray(array);
/*     */       return;
/*     */     } 
/*     */     while (true) {
/* 213 */       skipWhiteSpace();
/* 214 */       this.handler.startArrayValue(array);
/* 215 */       readValue();
/* 216 */       this.handler.endArrayValue(array);
/* 217 */       skipWhiteSpace();
/* 218 */       if (!readChar(',')) {
/* 219 */         if (!readChar(']')) {
/* 220 */           throw expected("',' or ']'");
/*     */         }
/* 222 */         this.nestingLevel--;
/* 223 */         this.handler.endArray(array);
/*     */         return;
/*     */       } 
/*     */     }  }
/* 227 */   private void readObject() throws IOException { Object object = this.handler.startObject();
/* 228 */     read();
/* 229 */     if (++this.nestingLevel > 1000) {
/* 230 */       throw error("Nesting too deep");
/*     */     }
/* 232 */     skipWhiteSpace();
/* 233 */     if (readChar('}')) {
/* 234 */       this.nestingLevel--;
/* 235 */       this.handler.endObject(object);
/*     */       return;
/*     */     } 
/*     */     while (true) {
/* 239 */       skipWhiteSpace();
/* 240 */       this.handler.startObjectName(object);
/* 241 */       String name = readName();
/* 242 */       this.handler.endObjectName(object, name);
/* 243 */       skipWhiteSpace();
/* 244 */       if (!readChar(':')) {
/* 245 */         throw expected("':'");
/*     */       }
/* 247 */       skipWhiteSpace();
/* 248 */       this.handler.startObjectValue(object, name);
/* 249 */       readValue();
/* 250 */       this.handler.endObjectValue(object, name);
/* 251 */       skipWhiteSpace();
/* 252 */       if (!readChar(',')) {
/* 253 */         if (!readChar('}')) {
/* 254 */           throw expected("',' or '}'");
/*     */         }
/* 256 */         this.nestingLevel--;
/* 257 */         this.handler.endObject(object);
/*     */         return;
/*     */       } 
/*     */     }  } private String readName() throws IOException {
/* 261 */     if (this.current != 34) {
/* 262 */       throw expected("name");
/*     */     }
/* 264 */     return readStringInternal();
/*     */   }
/*     */   
/*     */   private void readNull() throws IOException {
/* 268 */     this.handler.startNull();
/* 269 */     read();
/* 270 */     readRequiredChar('u');
/* 271 */     readRequiredChar('l');
/* 272 */     readRequiredChar('l');
/* 273 */     this.handler.endNull();
/*     */   }
/*     */   
/*     */   private void readTrue() throws IOException {
/* 277 */     this.handler.startBoolean();
/* 278 */     read();
/* 279 */     readRequiredChar('r');
/* 280 */     readRequiredChar('u');
/* 281 */     readRequiredChar('e');
/* 282 */     this.handler.endBoolean(true);
/*     */   }
/*     */   
/*     */   private void readFalse() throws IOException {
/* 286 */     this.handler.startBoolean();
/* 287 */     read();
/* 288 */     readRequiredChar('a');
/* 289 */     readRequiredChar('l');
/* 290 */     readRequiredChar('s');
/* 291 */     readRequiredChar('e');
/* 292 */     this.handler.endBoolean(false);
/*     */   }
/*     */   
/*     */   private void readRequiredChar(char ch) throws IOException {
/* 296 */     if (!readChar(ch)) {
/* 297 */       throw expected("'" + ch + "'");
/*     */     }
/*     */   }
/*     */   
/*     */   private void readString() throws IOException {
/* 302 */     this.handler.startString();
/* 303 */     this.handler.endString(readStringInternal());
/*     */   }
/*     */   
/*     */   private String readStringInternal() throws IOException {
/* 307 */     read();
/* 308 */     startCapture();
/* 309 */     while (this.current != 34) {
/* 310 */       if (this.current == 92) {
/* 311 */         pauseCapture();
/* 312 */         readEscape();
/* 313 */         startCapture(); continue;
/* 314 */       }  if (this.current < 32) {
/* 315 */         throw expected("valid string character");
/*     */       }
/* 317 */       read();
/*     */     } 
/*     */     
/* 320 */     String string = endCapture();
/* 321 */     read();
/* 322 */     return string;
/*     */   } private void readEscape() throws IOException {
/*     */     char[] hexChars;
/*     */     int i;
/* 326 */     read();
/* 327 */     switch (this.current) {
/*     */       case 34:
/*     */       case 47:
/*     */       case 92:
/* 331 */         this.captureBuffer.append((char)this.current);
/*     */         break;
/*     */       case 98:
/* 334 */         this.captureBuffer.append('\b');
/*     */         break;
/*     */       case 102:
/* 337 */         this.captureBuffer.append('\f');
/*     */         break;
/*     */       case 110:
/* 340 */         this.captureBuffer.append('\n');
/*     */         break;
/*     */       case 114:
/* 343 */         this.captureBuffer.append('\r');
/*     */         break;
/*     */       case 116:
/* 346 */         this.captureBuffer.append('\t');
/*     */         break;
/*     */       case 117:
/* 349 */         hexChars = new char[4];
/* 350 */         for (i = 0; i < 4; i++) {
/* 351 */           read();
/* 352 */           if (!isHexDigit()) {
/* 353 */             throw expected("hexadecimal digit");
/*     */           }
/* 355 */           hexChars[i] = (char)this.current;
/*     */         } 
/* 357 */         this.captureBuffer.append((char)Integer.parseInt(new String(hexChars), 16));
/*     */         break;
/*     */       default:
/* 360 */         throw expected("valid escape sequence");
/*     */     } 
/* 362 */     read();
/*     */   }
/*     */   
/*     */   private void readNumber() throws IOException {
/* 366 */     this.handler.startNumber();
/* 367 */     startCapture();
/* 368 */     readChar('-');
/* 369 */     int firstDigit = this.current;
/* 370 */     if (!readDigit()) {
/* 371 */       throw expected("digit");
/*     */     }
/* 373 */     if (firstDigit != 48) {
/* 374 */       while (readDigit());
/*     */     }
/*     */     
/* 377 */     readFraction();
/* 378 */     readExponent();
/* 379 */     this.handler.endNumber(endCapture());
/*     */   }
/*     */   
/*     */   private boolean readFraction() throws IOException {
/* 383 */     if (!readChar('.')) {
/* 384 */       return false;
/*     */     }
/* 386 */     if (!readDigit()) {
/* 387 */       throw expected("digit");
/*     */     }
/* 389 */     while (readDigit());
/*     */     
/* 391 */     return true;
/*     */   }
/*     */   
/*     */   private boolean readExponent() throws IOException {
/* 395 */     if (!readChar('e') && !readChar('E')) {
/* 396 */       return false;
/*     */     }
/* 398 */     if (!readChar('+')) {
/* 399 */       readChar('-');
/*     */     }
/* 401 */     if (!readDigit()) {
/* 402 */       throw expected("digit");
/*     */     }
/* 404 */     while (readDigit());
/*     */     
/* 406 */     return true;
/*     */   }
/*     */   
/*     */   private boolean readChar(char ch) throws IOException {
/* 410 */     if (this.current != ch) {
/* 411 */       return false;
/*     */     }
/* 413 */     read();
/* 414 */     return true;
/*     */   }
/*     */   
/*     */   private boolean readDigit() throws IOException {
/* 418 */     if (!isDigit()) {
/* 419 */       return false;
/*     */     }
/* 421 */     read();
/* 422 */     return true;
/*     */   }
/*     */   
/*     */   private void skipWhiteSpace() throws IOException {
/* 426 */     while (isWhiteSpace()) {
/* 427 */       read();
/*     */     }
/*     */   }
/*     */   
/*     */   private void read() throws IOException {
/* 432 */     if (this.index == this.fill) {
/* 433 */       if (this.captureStart != -1) {
/* 434 */         this.captureBuffer.append(this.buffer, this.captureStart, this.fill - this.captureStart);
/* 435 */         this.captureStart = 0;
/*     */       } 
/* 437 */       this.bufferOffset += this.fill;
/* 438 */       this.fill = this.reader.read(this.buffer, 0, this.buffer.length);
/* 439 */       this.index = 0;
/* 440 */       if (this.fill == -1) {
/* 441 */         this.current = -1;
/* 442 */         this.index++;
/*     */         return;
/*     */       } 
/*     */     } 
/* 446 */     if (this.current == 10) {
/* 447 */       this.line++;
/* 448 */       this.lineOffset = this.bufferOffset + this.index;
/*     */     } 
/* 450 */     this.current = this.buffer[this.index++];
/*     */   }
/*     */   
/*     */   private void startCapture() {
/* 454 */     if (this.captureBuffer == null) {
/* 455 */       this.captureBuffer = new StringBuilder();
/*     */     }
/* 457 */     this.captureStart = this.index - 1;
/*     */   }
/*     */   
/*     */   private void pauseCapture() {
/* 461 */     int end = (this.current == -1) ? this.index : (this.index - 1);
/* 462 */     this.captureBuffer.append(this.buffer, this.captureStart, end - this.captureStart);
/* 463 */     this.captureStart = -1;
/*     */   }
/*     */   
/*     */   private String endCapture() {
/* 467 */     int start = this.captureStart;
/* 468 */     int end = this.index - 1;
/* 469 */     this.captureStart = -1;
/* 470 */     if (this.captureBuffer.length() > 0) {
/* 471 */       this.captureBuffer.append(this.buffer, start, end - start);
/* 472 */       String captured = this.captureBuffer.toString();
/* 473 */       this.captureBuffer.setLength(0);
/* 474 */       return captured;
/*     */     } 
/* 476 */     return new String(this.buffer, start, end - start);
/*     */   }
/*     */   
/*     */   Location getLocation() {
/* 480 */     int offset = this.bufferOffset + this.index - 1;
/* 481 */     int column = offset - this.lineOffset + 1;
/* 482 */     return new Location(offset, this.line, column);
/*     */   }
/*     */   
/*     */   private ParseException expected(String expected) {
/* 486 */     if (isEndOfText()) {
/* 487 */       return error("Unexpected end of input");
/*     */     }
/* 489 */     return error("Expected " + expected);
/*     */   }
/*     */   
/*     */   private ParseException error(String message) {
/* 493 */     return new ParseException(message, getLocation());
/*     */   }
/*     */   
/*     */   private boolean isWhiteSpace() {
/* 497 */     return (this.current == 32 || this.current == 9 || this.current == 10 || this.current == 13);
/*     */   }
/*     */   
/*     */   private boolean isDigit() {
/* 501 */     return (this.current >= 48 && this.current <= 57);
/*     */   }
/*     */   
/*     */   private boolean isHexDigit() {
/* 505 */     return ((this.current >= 48 && this.current <= 57) || (this.current >= 97 && this.current <= 102) || (this.current >= 65 && this.current <= 70));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isEndOfText() {
/* 511 */     return (this.current == -1);
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\json\JsonParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */