package com.expect.csip.iam.authorization;

import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.security.token.SSOToken;
import com.expect.common.constant.PublicConstant;
import com.expect.common.domain.condition.AuthDataCondition;
import com.expect.common.utils.RequestUtil;
import com.expect.csip.iam.service.DataPermService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 数据权限参数解析
 * </p>
 *
 * @author jack.zeng
 * @since 2018-03-25
 */
public class SSODataPermissionArgumentResolver implements HandlerMethodArgumentResolver  {

	private static final Logger logger = LoggerFactory.getLogger(SSODataPermissionArgumentResolver.class);

	@Autowired
	private DataPermService dataPermService;


	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterType().getSuperclass() == AuthDataCondition.class;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		String name = parameter.getParameterName();
		logger.debug("进入参数解析 parameterName = {}", name);
	    // 查找是否已有名为name的参数值的映射，如果没有则创建一个
	    Object attribute = mavContainer.containsAttribute(name)
	        ? mavContainer.getModel().get(name)
	        : this.createAttribute(name, parameter, binderFactory, webRequest);
	    if (binderFactory != null) {
	        WebDataBinder binder = binderFactory.createBinder(webRequest, attribute, name);
	        if (binder.getTarget() != null) {
	            // 进行参数绑定
	            this.bindRequestParameters(parameter, binder, webRequest);
	        }
	        // 将参数转到预期类型，第一个参数为解析后的值，第二个参数为绑定Controller参数的类型，第三个参数为绑定的Controller参数
	        attribute = binder.convertIfNecessary(binder.getTarget(), parameter.getParameterType(), parameter);
	    }
	    return attribute;
	}

	/**
	 * Extension point to bind the request to the target object.
	 * @param binder the data binder instance to use for the binding
	 * @param request the current request
	 * @throws UnsupportedEncodingException
	 */
	protected void bindRequestParameters(MethodParameter parameter, WebDataBinder binder, NativeWebRequest request) throws UnsupportedEncodingException {
		 // 将key-value封装为map，传给bind方法进行参数值绑定
	    Map<String, Object> map = new HashMap<String, Object>();
	    Map<String, String[]> params = request.getParameterMap();

	    for (Map.Entry<String, String[]> entry : params.entrySet()) {
	        String name = entry.getKey();
	        // 执行urldecode
	        String value = URLDecoder.decode(entry.getValue()[0], "UTF-8");
	        map.put(name, value);
			logger.debug("绑定请求参数 name = {} value = {}", name, value);
	    }
		HttpServletRequest servletRequest = RequestUtil.getRequest();
	    SSOToken ssoToken = SSOHelper.attrToken(servletRequest);
		if (ssoToken.getId().equals(PublicConstant.UNAUTHORIZED_USER_ID)) {
			logger.debug("免授权用户，不做数据权限效验 SSOToken = {}  permission = {}", ssoToken.getId(), servletRequest.getRequestURI());
		} else {
			String conditionExpression = dataPermService.getConditionExpression(ssoToken.getId(), servletRequest.getRequestURI());
			logger.debug("绑定数据权限表达式 ssoToken = {} conditionExpression = {}", ssoToken.getId(), conditionExpression);
			map.put("conditionExpression", conditionExpression);
		}
	    PropertyValues propertyValues = new MutablePropertyValues(map);
	    // 将K-V绑定到binder.target属性上
	    binder.bind(propertyValues);
	}


	/**
	 * Extension point to create the model attribute if not found in the model.
	 * The default implementation uses the default constructor.
	 * @param attributeName the name of the attribute (never {@code null})
	 * @param parameter the method parameter
	 * @param binderFactory for creating WebDataBinder instance
	 * @param webRequest the current request
	 * @return the created model attribute (never {@code null})
	 */
	protected Object createAttribute(String attributeName, MethodParameter parameter,
			WebDataBinderFactory binderFactory, NativeWebRequest webRequest) throws Exception {

		return BeanUtils.instantiateClass(parameter.getParameterType());
	}
}
