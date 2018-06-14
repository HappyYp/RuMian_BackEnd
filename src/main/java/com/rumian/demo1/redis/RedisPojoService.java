package com.rumian.demo1.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class RedisPojoService {
    @Resource
    private RedisTemplate<String,Object> redisTemplate;


//    public boolean set(final String key, final String value) {
//        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
//
//            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
//                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
//                connection.set(serializer.serialize(key), serializer.serialize(value));
//                return true;
//            }
//        });
//        return result;
//    }

    public void set(String key, Object object){
        ValueOperations<String, Object> operations=redisTemplate.opsForValue();
        operations.set(key, object);
    }

    public void set(String key, Object object, long seconds){
        ValueOperations<String, Object> operations=redisTemplate.opsForValue();
        operations.set(key, object, seconds, TimeUnit.SECONDS);
    }

//    public String get(final String key) {
//        String result = redisTemplate.execute(new RedisCallback<String>() {
//
//            public String doInRedis(RedisConnection connection) throws DataAccessException {
//                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
//                byte[] value = connection.get(serializer.serialize(key));
//                return serializer.deserialize(value);
//            }
//        });
//        return result;
//    }

    public Object get(String key){
        ValueOperations<String, Object> operations=redisTemplate.opsForValue();
        return operations.get(key);
    }
    public boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }


    public boolean expire(final String key, long expire) {
        return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }


    public boolean remove(final String key) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {

            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                connection.del(key.getBytes());
                return true;
            }
        });
        return result;
    }
    public void setExpire(String key, long seconds){
        redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }
    public long increment(String key, long i){
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        return operations.increment(key, i);
    }
    public void delete(String key){
        redisTemplate.delete(key);
    }
}
