package com.expect.csip.iam.authorization;

import cn.hutool.core.util.ReflectUtil;
import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.security.token.SSOToken;
import com.expect.common.constant.PublicConstant;
import com.expect.common.domain.condition.AuthDataCondition;
import com.expect.common.utils.RequestUtil;
import com.expect.csip.iam.service.DataPermService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 * <b>统一处理请求值/请求体</b><br>
 *
 * @author jack.zeng
 */
@RestControllerAdvice
public class SSODataPermissionRequestBodyAdvice implements RequestBodyAdvice {

    private static final Logger logger = LoggerFactory.getLogger(SSODataPermissionRequestBodyAdvice.class);

    @Autowired
    private DataPermService dataPermService;

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
                                Class<? extends HttpMessageConverter<?>> converterType) {
        HttpServletRequest request = RequestUtil.getRequest();
        SSOToken ssoToken = SSOHelper.attrToken(request);
        if (ssoToken.getId().equals(PublicConstant.UNAUTHORIZED_USER_ID)) {
            logger.debug("免授权用户，不做数据权限效验 SSOToken = {}  permission = {}", ssoToken.getId(), request.getRequestURI());
            return body;
        }
        String conditionExpression = dataPermService.getConditionExpression(ssoToken.getId(), request.getRequestURI());
        logger.debug("绑定数据权限表达式 ssoToken = {} conditionExpression = {}", ssoToken.getId(), conditionExpression);
        ReflectUtil.setFieldValue(body, "conditionExpression", conditionExpression);
        return body;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
                                           Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        return inputMessage;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
                                  Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public boolean supports(MethodParameter parameter, Type targetType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        return parameter.getParameterType().getSuperclass() == AuthDataCondition.class;
    }
}
