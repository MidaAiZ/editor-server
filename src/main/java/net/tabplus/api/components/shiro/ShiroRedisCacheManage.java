package net.tabplus.api.components.shiro;

import net.tabplus.api.modules.pojo.Admin;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Set;

@Component
public class ShiroRedisCacheManage implements CacheManager {

    private String cacheKeyPrefix = "shiro:";

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return new ShiroRedisCache<K, V>(cacheKeyPrefix + name);
    }

    /**
     * 为shiro量身定做的一个redis cache,为Authorization cache做了特别优化
     */
    public class ShiroRedisCache<K, V> implements Cache<K, V> {

        private String cacheKey;

        public ShiroRedisCache(String cacheKey) {
            this.cacheKey = cacheKey;
        }

        @Override
        public V get(K key) throws CacheException {
            BoundHashOperations<String, K, V> hash = redisTemplate.boundHashOps(cacheKey);
            Object k = hashKey(key);
            return hash.get(k);
        }

        @Override
        public V put(K key, V value) throws CacheException {
            BoundHashOperations<String, K, V> hash = redisTemplate.boundHashOps(cacheKey);
            Object k = hashKey(key);
            hash.put((K) k, value);
            return value;
        }

        @Override
        public V remove(K key) throws CacheException {
            BoundHashOperations<String, K, V> hash = redisTemplate.boundHashOps(cacheKey);
            Object k = hashKey(key);
            V value = hash.get(k);
            hash.delete(k);
            return value;
        }

        @Override
        public void clear() throws CacheException {
            redisTemplate.delete(cacheKey);
        }

        @Override
        public int size() {
            BoundHashOperations<String, K, V> hash = redisTemplate.boundHashOps(cacheKey);
            return hash.size().intValue();
        }

        @Override
        public Set<K> keys() {
            BoundHashOperations<String, K, V> hash = redisTemplate.boundHashOps(cacheKey);
            return hash.keys();
        }

        @Override
        public Collection<V> values() {
            BoundHashOperations<String, K, V> hash = redisTemplate.boundHashOps(cacheKey);
            return hash.values();
        }

        protected Object hashKey(K key) {
            /**
             * 如果key是登录凭证,那么这是访问用户的授权缓存;
             * 将登录凭证转为admin对象,返回admin的id属性做为hash key,否则会以admin对象做为hash key,这样就不好清除指定用户的缓存了
             */
            if (key instanceof PrincipalCollection) {
                PrincipalCollection pc = (PrincipalCollection) key;
                Admin admin = (Admin) pc.getPrimaryPrincipal();
                return admin.getAid();
            }
            return key;
        }
    }

}
