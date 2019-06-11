package com.expect.csip.iam.authorization;

import cn.hutool.json.JSONUtil;
import com.baomidou.kisso.common.util.HttpUtil;
import com.baomidou.kisso.web.handler.SSOPermissionHandlerInterceptor;
import com.baomidou.mybatisplus.extension.api.IErrorCode;
import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * SSO Handler 拦截器接口
 * </p>
 *
 * @author jack.zeng
 * @since 2019-02-22
 */
@Service("ssoPermissionHandler")
public class SSOPermissionHandler implements SSOPermissionHandlerInterceptor {

    @Override
    public boolean prePermissionIsNullAjax(HttpServletRequest request, HttpServletResponse response) {
        R result = R.failed(new IErrorCode() {
            @Override
            public long getCode() {
                return -3;
            }
            @Override
            public String getMsg() {
                String url = request.getRequestURI();
                return "您所访问的链接[" + url + "]未授权，请联系管理员授权后再试.";
            }
        });
        HttpUtil.ajaxStatus(request, response, HttpStatus.OK.value(), JSONUtil.toJsonStr(result));
        return false;
    }

    @Override
    public boolean prePermissionIsNull(HttpServletRequest request, HttpServletResponse response, String illegalUrl) {
        return false;
    }
}
