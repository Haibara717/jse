package org.jsecurity.cache;

import java.util.Hashtable;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;

public class MemoryConstrainedCacheManager implements CacheManager {

    public Cache getCache(String name) throws CacheException {
        return new HashtableCache();
    }

    private static class HashtableCache implements Cache {
        private final Map map = new Hashtable();

        public Object get(Object key) throws CacheException { return map.get(key); }
        public void put(Object key, Object value) throws CacheException { map.put(key, value); }
        public void remove(Object key) throws CacheException { map.remove(key); }
        public void clear() throws CacheException { map.clear(); }
        public int size() { return map.size(); }

        // JSecurity 0.9 接口定义的可能是 keys() 而非 keySet()
        public Set keys() {
            return map.keySet();
        }

        // 确保 values 也返回 Set
        public Set values() {
            return new HashSet(map.values());
        }
    }
}