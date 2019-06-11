package com.expect.csip.common.configuration;

import com.baomidou.kisso.SSOAuthorization;
import com.baomidou.kisso.web.interceptor.SSOPermissionInterceptor;
import com.baomidou.kisso.web.interceptor.SSOSpringInterceptor;
import com.expect.csip.iam.authorization.SSODataPermissionArgumentResolver;
import com.expect.csip.iam.authorization.SSOPermissionHandler;
import com.expect.csip.iam.authorization.SSOTokenHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author jack.zeng
 * @version 创建时间：2017年7月3日 下午3:13:01
 */
@Configuration
public class SSOAuthorConfiguration implements WebMvcConfigurer {

    @Autowired
    private SSOAuthorization ssoAuthorization;

    @Autowired
    private SSOPermissionHandler ssoPermissionHandler;

    @Autowired
    private SSOTokenHandler ssoTokenHandler;

    /**
     * 配置拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //访问权限拦截
        SSOSpringInterceptor ssoSpringInterceptor = new SSOSpringInterceptor();
        ssoSpringInterceptor.setTokenHandlerInterceptor(ssoTokenHandler);

        //操作权限拦截
        SSOPermissionInterceptor ssoPermissionInterceptor = new SSOPermissionInterceptor();
        ssoPermissionInterceptor.setAuthorization(ssoAuthorization);
        ssoPermissionInterceptor.setNothingAnnotationPass(true);
        ssoPermissionInterceptor.setPermissionHandlerInterceptor(ssoPermissionHandler);

        //注册登录拦截
        registry.addInterceptor(ssoSpringInterceptor).addPathPatterns("/**");
        //注册权限拦截
        registry.addInterceptor(ssoPermissionInterceptor).addPathPatterns("/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        //注册请求参数解析器
        argumentResolvers.add(new SSODataPermissionArgumentResolver());
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedOrigin("*"); // 1允许任何域名使用
        corsConfiguration.addAllowedHeader("*"); // 2允许任何头
        corsConfiguration.addAllowedMethod("*"); // 3允许任何方法（post、get等）
        corsConfiguration.setMaxAge(1728000l);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration); // 4 对接口配置跨域设置
        return new CorsFilter(source);
    }
}
