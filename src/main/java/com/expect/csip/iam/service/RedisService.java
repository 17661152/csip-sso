package com.expect.csip.iam.service;

import com.expect.csip.iam.domain.Redis;
import com.expect.csip.iam.domain.condition.RedisCondition;

import java.util.List;

/**
 * 可自行扩展
 *
 * @author jie
 * @date 2018-12-10
 */
public interface RedisService {

    /**
     * listByKey
     *
     * @param condition
     * @return
     */
    List<Redis> listRedis(RedisCondition condition);

    /**
     * getRedisByKey
     *
     * @param key
     * @return
     */
    Redis getRedisByKey(String key);

    /**
     * delete
     *
     * @param key
     */
    Boolean deleteRedis(String key);

    /**
     * 清空所有缓存
     */
    Boolean flushdb();
}
