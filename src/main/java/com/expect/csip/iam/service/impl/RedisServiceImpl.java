package com.expect.csip.iam.service.impl;

import com.expect.csip.iam.domain.Redis;
import com.expect.csip.iam.domain.condition.RedisCondition;
import com.expect.csip.iam.service.RedisService;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RBucket;
import org.redisson.api.RKeys;
import org.redisson.api.RList;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public List<Redis> listRedis(RedisCondition condition) {
        List<Redis> rediss = Lists.newArrayList();
        RKeys rKeys = redissonClient.getKeys();
        String keyPattern = null;
        if (StringUtils.isNotBlank(condition.getKey())) {
            keyPattern = condition.getKey() + "*";
        } else {
            keyPattern = "*";
        }
        Iterable<String> keys = rKeys.getKeysByPattern(keyPattern);
        keys.forEach(key -> {
            rediss.add(new Redis(key));
        });
        return rediss;
    }

    @Override
    public Redis getRedisByKey(String key) {
        RMap<String, RList<RMap>> map = redissonClient.getMap(key);
        List<Redis> values = Lists.newArrayList();
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String rlist = iterator.next();
            values.add(new Redis(rlist, map.get(rlist)));
        }
        return new Redis(key, values);
    }

    @Override
    public Boolean deleteRedis(String key) {
        RBucket keyObject = redissonClient.getBucket(key);
        return keyObject.delete();
    }

    @Override
    public Boolean flushdb() {
        try {
            redissonClient.getKeys().flushdb();
            return true;
        } catch (Throwable e) {
            return false;
        }
    }
}
