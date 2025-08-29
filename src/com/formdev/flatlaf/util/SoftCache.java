/*     */ package com.formdev.flatlaf.util;
/*     */ 
/*     */ import java.lang.ref.Reference;
/*     */ import java.lang.ref.ReferenceQueue;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.function.BiConsumer;
/*     */ import java.util.function.BiFunction;
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
/*     */ public class SoftCache<K, V>
/*     */   implements Map<K, V>
/*     */ {
/*     */   private final Map<K, CacheReference<K, V>> map;
/*  39 */   private final ReferenceQueue<V> queue = new ReferenceQueue<>();
/*     */   
/*     */   public SoftCache() {
/*  42 */     this.map = new HashMap<>();
/*     */   }
/*     */   
/*     */   public SoftCache(int initialCapacity) {
/*  46 */     this.map = new HashMap<>(initialCapacity);
/*     */   }
/*     */ 
/*     */   
/*     */   public int size() {
/*  51 */     expungeStaleEntries();
/*  52 */     return this.map.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/*  57 */     expungeStaleEntries();
/*  58 */     return this.map.isEmpty();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean containsKey(Object key) {
/*  63 */     expungeStaleEntries();
/*  64 */     return this.map.containsKey(key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsValue(Object value) {
/*  72 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   
/*     */   public V get(Object key) {
/*  77 */     expungeStaleEntries();
/*  78 */     return getRef(this.map.get(key));
/*     */   }
/*     */ 
/*     */   
/*     */   public V put(K key, V value) {
/*  83 */     expungeStaleEntries();
/*  84 */     return getRef(this.map.put(key, new CacheReference<>(key, value, this.queue)));
/*     */   }
/*     */ 
/*     */   
/*     */   public V remove(Object key) {
/*  89 */     expungeStaleEntries();
/*  90 */     return getRef(this.map.remove(key));
/*     */   }
/*     */   
/*     */   private V getRef(CacheReference<K, V> ref) {
/*  94 */     return (ref != null) ? ref.get() : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void putAll(Map<? extends K, ? extends V> m) {
/*  99 */     expungeStaleEntries();
/* 100 */     for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
/* 101 */       put(e.getKey(), e.getValue());
/*     */     }
/*     */   }
/*     */   
/*     */   public void clear() {
/* 106 */     this.map.clear();
/* 107 */     expungeStaleEntries();
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<K> keySet() {
/* 112 */     expungeStaleEntries();
/* 113 */     return this.map.keySet();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<V> values() {
/* 121 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<Map.Entry<K, V>> entrySet() {
/* 129 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void forEach(BiConsumer<? super K, ? super V> action) {
/* 137 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
/* 145 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   
/*     */   private void expungeStaleEntries() {
/*     */     Reference<? extends V> reference;
/* 151 */     while ((reference = this.queue.poll()) != null) {
/* 152 */       this.map.remove(((CacheReference)reference).key);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static class CacheReference<K, V>
/*     */     extends SoftReference<V>
/*     */   {
/*     */     final K key;
/*     */ 
/*     */     
/*     */     CacheReference(K key, V value, ReferenceQueue<? super V> queue) {
/* 164 */       super(value, queue);
/* 165 */       this.key = key;
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\util\SoftCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */