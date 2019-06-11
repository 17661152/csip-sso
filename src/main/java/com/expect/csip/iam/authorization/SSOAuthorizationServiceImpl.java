package com.expect.csip.iam.authorization;

import com.baomidou.kisso.SSOAuthorization;
import com.baomidou.kisso.security.token.SSOToken;
import com.expect.common.constant.PublicConstant;
import com.expect.common.enums.PublicEnum;
import com.expect.common.utils.RequestUtil;
import com.expect.csip.iam.constant.DataPermConst;
import com.expect.csip.iam.service.ServiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: SSOAuthorizationServiceImpl
 * @Description: 服务权限验证处理类
 * @author: jack.zeng
 * @date:2017年7月29日 下午10:19:23
 */
@Service("ssoAuthorization")
public class SSOAuthorizationServiceImpl implements SSOAuthorization {

    private final static Logger logger = LoggerFactory.getLogger(SSOAuthorizationServiceImpl.class);

    @Autowired
    private ServiceService serviceService;

    @Override
    public boolean isPermitted(SSOToken token, String permission) {
        if (token.getId().equals(PublicConstant.UNAUTHORIZED_USER_ID)) {
            logger.debug("免授权用户，不做权限效验 SSOToken = {}  permission = {}", token.getId(), permission);
            return true;
        }

        com.expect.csip.iam.domain.Service service = serviceService.getServiceByAccessAddress(permission);
        if (service.getIsWhite().equals(PublicEnum.YES.getStatus())) {
            logger.debug("白名单服务，不做权限效验 SSOToken = {}  permission = {}", token.getId(), permission);
            return true;
        }
        String currentRoute = RequestUtil.getRequest().getHeader(DataPermConst.CURRENT_ROUTE_HEADER);
        logger.debug("当前的路由标识 currentRoute = {}", currentRoute);
        boolean checkPerm = serviceService.checkServicePerm(token.getId(), permission, currentRoute);
        logger.debug("服务鉴权状态 SSOToken = {}  permission = {}  checkPerm = {}", token.getId(), permission, checkPerm);
        return checkPerm;
    }
}
