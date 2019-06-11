package com.expect.csip.iam.controller;


import com.expect.common.controller.BaseController;
import com.expect.common.domain.PageQueryResult;
import com.expect.csip.iam.domain.Redis;
import com.expect.csip.iam.domain.condition.RedisCondition;
import com.expect.csip.iam.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * <p>
 * redis 前端控制器
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@Api(tags = "缓存(redis)微服务")
@RestController
@RequestMapping("/iam/redis")
public class RedisController extends BaseController {

    @Autowired
    private RedisService redisService;

    @ApiOperation("删除所有键值缓存")
    @PostMapping("/flushdb")
    public Boolean flushdb() {
        return redisService.flushdb();
    }

    @ApiOperation("key查询键值缓存")
    @PostMapping("/getRedisByKey")
    public Redis getRedisByKey(@ApiParam(name = "key", value = "key", required = true) @RequestBody String key) {
        return redisService.getRedisByKey(key);
    }

    @ApiOperation("key删除键值缓存")
    @PostMapping("/deleteRedisByKey")
    public Boolean deleteRedisByKey(@ApiParam(name = "key", value = "key", required = true) @RequestBody String key) {
        return redisService.deleteRedis(key);
    }

    @ApiOperation("缓存键值列表")
    @ApiImplicitParam(name = "condition", value = "redis查询条件", required = true, dataType = "RedisCondition", paramType = "body")
    @PostMapping("/listRedisPage")
    public PageQueryResult<Redis> listRedisPage(@RequestBody RedisCondition condition) {
        List<Redis> redis = redisService.listRedis(condition);
        PageQueryResult<Redis> pageQueryResult = new PageQueryResult<>();
        pageQueryResult.setRecords(redis);
        pageQueryResult.setTotalRecord(redis.size());
        pageQueryResult.setPage(1);
        return pageQueryResult;
    }
}
