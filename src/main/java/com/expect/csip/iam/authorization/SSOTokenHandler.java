package com.expect.csip.iam.authorization;

import cn.hutool.json.JSONUtil;
import com.baomidou.kisso.common.util.HttpUtil;
import com.baomidou.kisso.web.handler.SSOTokenHandlerInterceptor;
import com.baomidou.mybatisplus.extension.api.IErrorCode;
import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service("ssoTokenHandler")
public class SSOTokenHandler implements SSOTokenHandlerInterceptor {

    @Override
    public boolean preTokenIsNullAjax(HttpServletRequest request, HttpServletResponse response) {
        R result = R.failed(new IErrorCode() {
            @Override
            public long getCode() {
                return -2;
            }
            @Override
            public String getMsg() {
                return "您的令牌非法，请重新登录.";
            }
        });
        HttpUtil.ajaxStatus(request, response, HttpStatus.OK.value(), JSONUtil.toJsonStr(result));
        return false;
    }

    @Override
    public boolean preTokenIsNull(HttpServletRequest request, HttpServletResponse response) {
        return false;
    }
}
