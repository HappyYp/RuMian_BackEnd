package com.rumian.demo1.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RedisHashService {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void put(String key, String mapKey, Object object){
        HashOperations<String, String, Object> operations = redisTemplate.opsForHash();
        operations.put(key, mapKey, object);
    }

    public long increment(String key, String mapKey, long i){
        HashOperations<String, String, Object> operations = redisTemplate.opsForHash();
        return operations.increment(key, mapKey, i);
    }
}
