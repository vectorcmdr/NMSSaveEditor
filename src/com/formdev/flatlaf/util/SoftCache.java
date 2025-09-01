package com.formdev.flatlaf.util;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class SoftCache<K, V> implements Map<K, V> {
   private final Map<K, SoftCache.CacheReference<K, V>> map;
   private final ReferenceQueue<V> queue = new ReferenceQueue();

   public SoftCache() {
      this.map = new HashMap();
   }

   public SoftCache(int initialCapacity) {
      this.map = new HashMap(initialCapacity);
   }

   public int size() {
      this.expungeStaleEntries();
      return this.map.size();
   }

   public boolean isEmpty() {
      this.expungeStaleEntries();
      return this.map.isEmpty();
   }

   public boolean containsKey(Object key) {
      this.expungeStaleEntries();
      return this.map.containsKey(key);
   }

   public boolean containsValue(Object value) {
      throw new UnsupportedOperationException();
   }

   public V get(Object key) {
      this.expungeStaleEntries();
      return this.getRef((SoftCache.CacheReference)this.map.get(key));
   }

   public V put(K key, V value) {
      this.expungeStaleEntries();
      return this.getRef((SoftCache.CacheReference)this.map.put(key, new SoftCache.CacheReference(key, value, this.queue)));
   }

   public V remove(Object key) {
      this.expungeStaleEntries();
      return this.getRef((SoftCache.CacheReference)this.map.remove(key));
   }

   private V getRef(SoftCache.CacheReference<K, V> ref) {
      return ref != null ? ref.get() : null;
   }

   public void putAll(Map<? extends K, ? extends V> m) {
      this.expungeStaleEntries();
      Iterator var2 = m.entrySet().iterator();

      while(var2.hasNext()) {
         Entry<? extends K, ? extends V> e = (Entry)var2.next();
         this.put(e.getKey(), e.getValue());
      }

   }

   public void clear() {
      this.map.clear();
      this.expungeStaleEntries();
   }

   public Set<K> keySet() {
      this.expungeStaleEntries();
      return this.map.keySet();
   }

   public Collection<V> values() {
      throw new UnsupportedOperationException();
   }

   public Set<Entry<K, V>> entrySet() {
      throw new UnsupportedOperationException();
   }

   public void forEach(BiConsumer<? super K, ? super V> action) {
      throw new UnsupportedOperationException();
   }

   public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
      throw new UnsupportedOperationException();
   }

   private void expungeStaleEntries() {
      Reference reference;
      while((reference = this.queue.poll()) != null) {
         this.map.remove(((SoftCache.CacheReference)reference).key);
      }

   }

   private static class CacheReference<K, V> extends SoftReference<V> {
      final K key;

      CacheReference(K key, V value, ReferenceQueue<? super V> queue) {
         super(value, queue);
         this.key = key;
      }
   }
}
