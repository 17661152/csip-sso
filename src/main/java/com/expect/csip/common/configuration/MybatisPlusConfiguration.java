package com.expect.csip.common.configuration;

import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.security.token.SSOToken;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.expect.common.utils.RequestUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Configuration
@MapperScan("com.expect.csip.**.mapper")
public class MybatisPlusConfiguration {

    /**
     * mybatis-plus分页插件<br>
     * 文档：http://mp.baomidou.com<br>
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }

    /**
     * mybatis-plus分析sql插件<br>
     * 文档：http://mp.baomidou.com<br>
     */
    @Bean
    @Profile("dev")
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setFormat(true);
        performanceInterceptor.setMaxTime(500);
        performanceInterceptor.setWriteInLog(true);
        return performanceInterceptor;
    }

    /**
     * mybatis-plus逻辑删除插件<br>
     * 文档：http://mp.baomidou.com<br>
     */
    @Bean
    public LogicSqlInjector logicSqlInjector() {
        return new LogicSqlInjector();
    }

    /**
     * mybatis-plus自动填充公共字段插件<br>
     * 文档：http://mp.baomidou.com<br>
     */
    @Component
    public class MetaObjectHandlerConfiguration implements MetaObjectHandler {

        @Override
        public void insertFill(MetaObject metaObject) {
            HttpServletRequest request = RequestUtil.getRequest();
            if (null == request) {
                return;
            }
            SSOToken ssoToken = SSOHelper.attrToken(request);
            if (null != ssoToken) {
                setFieldValByName("createPerson", ssoToken.getId(), metaObject);
                setFieldValByName("lastUpdatePerson", ssoToken.getId(), metaObject);
            }
            Date curentDate = new Date();
            setFieldValByName("createTime", curentDate, metaObject);
            setFieldValByName("lastUpdateTime", curentDate, metaObject);
        }

        @Override
        public void updateFill(MetaObject metaObject) {
            HttpServletRequest request = RequestUtil.getRequest();
            if (null == request) {
                return;
            }
            SSOToken ssoToken = SSOHelper.attrToken(request);
            if (null != ssoToken) {
                setFieldValByName("lastUpdatePerson", ssoToken.getId(), metaObject);
            }
            setFieldValByName("lastUpdateTime", new Date(), metaObject);
        }
    }
}
