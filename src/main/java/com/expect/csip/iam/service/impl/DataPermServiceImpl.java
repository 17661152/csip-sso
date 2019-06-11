package com.expect.csip.iam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.expect.common.enums.PublicEnum;
import com.expect.csip.iam.constant.DataPermConst;
import com.expect.csip.iam.domain.DataPerm;
import com.expect.csip.iam.domain.Organ;
import com.expect.csip.iam.domain.RoleDataPermRel;
import com.expect.csip.iam.domain.User;
import com.expect.csip.iam.domain.condition.DataPermCondition;
import com.expect.csip.iam.domain.condition.DataPermValueCondition;
import com.expect.csip.iam.domain.DataPermValue;
import com.expect.csip.iam.mapper.DataPermMapper;
import com.expect.csip.iam.service.DataPermService;
import com.expect.csip.iam.service.DataPermValueService;
import com.expect.csip.iam.service.MenuDataPermRelService;
import com.expect.csip.iam.service.OrganService;
import com.expect.csip.iam.service.RoleDataPermRelService;
import com.expect.csip.iam.service.ServiceService;
import com.expect.csip.iam.service.UserService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 数据权限 服务实现类
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
@Service
public class DataPermServiceImpl extends ServiceImpl<DataPermMapper, DataPerm> implements DataPermService {

    @Autowired
    private RoleDataPermRelService roleDataPermRelService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrganService organService;

    @Autowired
    private DataPermValueService dataPermValueService;

    @Autowired
    private MenuDataPermRelService menuDataPermRelService;

    @Autowired
    private ServiceService serviceService;

    @Override
    public IPage<DataPerm> listDataPermPage(DataPermCondition condition) {
        IPage<DataPerm> page = condition.buildPage();
        page = baseMapper.listDataPermPage(page, condition);
        return page;
    }

    @Override
    public DataPerm getDataPermById(String dataPermId) {
        DataPerm dataPerm = getById(dataPermId);
        if (null != dataPerm) {
            DataPermValueCondition condition = new DataPermValueCondition();
            condition.setDataPermId(dataPermId);
            dataPerm.setDataPermValues(dataPermValueService.listDataPermValue(condition));

            com.expect.csip.iam.domain.Service service = serviceService.getServiceById(dataPermId);
            if (null != service) {
                dataPerm.setServiceName(service.getName());
            }
        }
        return dataPerm;
    }

    @Override
    public Map<String, DataPerm> listDataPermMapsByIds(List<String> dataPermIds) {
        Map<String, DataPerm> resultMap = Maps.newLinkedHashMap();
        Collection<DataPerm> domains = listByIds(dataPermIds);
        if (CollectionUtils.isEmpty(domains)) {
            return resultMap;
        }

        for (DataPerm dataPerm : domains) {
            resultMap.put(dataPerm.getDataPermId(), dataPerm);
        }
        return resultMap;
    }

    @Override
    @Transactional
    public Boolean deleteDataPermByIds(List<String> dataPermIds) {
        dataPermValueService.deleteDataPermValueByServiceIds(dataPermIds);
        menuDataPermRelService.deleteMenuDataPermRelByServiceIds(dataPermIds);
        roleDataPermRelService.deleteRoleDataPermRelByDataPermIds(dataPermIds);
        return removeByIds(dataPermIds);
    }

    @Override
    @Transactional
    public Boolean deleteDataPermById(String dataPermId) {
        List<String> dataPermIds = Lists.newArrayList();
        dataPermIds.add(dataPermId);
        return deleteDataPermByIds(dataPermIds);
    }

    @Override
    @Transactional
    public DataPerm saveDataPerm(DataPerm dataPerm) {
        dataPerm.setIsEnable(PublicEnum.ACTIVE.getStatus());
        save(dataPerm);
        List<DataPermValue> dataPermValues = dataPerm.getDataPermValues();
        if (CollectionUtils.isNotEmpty(dataPermValues)) {
            dataPermValues.forEach(dataPermValue -> {
                dataPermValue.setDataPermId(dataPerm.getDataPermId());
            });
            dataPermValueService.saveBatch(dataPermValues);
        }
        return dataPerm;
    }

    @Override
    @Transactional
    public Boolean updateDataPerm(DataPerm dataPerm) {
        List<DataPermValue> dataPermValues = dataPerm.getDataPermValues();
        if (CollectionUtils.isNotEmpty(dataPermValues)) {
            dataPermValues.forEach(dataPermValue -> {
                dataPermValue.setDataPermId(dataPerm.getDataPermId());
            });
        }
        dataPermValueService.updateDataPermValue(dataPerm.getDataPermId(), dataPermValues);
        return updateById(dataPerm);
    }

    @Override
    @Transactional
    public Boolean deleteDataPermByServiceIds(List<String> serviceIds) {
        QueryWrapper<DataPerm> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(DataPerm::getDataPermId, serviceIds);
        return remove(queryWrapper);
    }

    @Override
    @Transactional
    public Boolean disableDataPerm(String dataPermId) {
        List<String> dataPermIds = Lists.newArrayList();
        dataPermIds.add(dataPermId);
        UpdateWrapper<DataPerm> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().set(DataPerm::getIsEnable, PublicEnum.UNACTIVE.getStatus())
                .in(DataPerm::getDataPermId, dataPermIds);
        return update(updateWrapper);
    }

    @Override
    @Transactional
    public Boolean enableDataPerm(String dataPermId) {
        List<String> dataPermIds = Lists.newArrayList();
        dataPermIds.add(dataPermId);
        UpdateWrapper<DataPerm> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().set(DataPerm::getIsEnable, PublicEnum.ACTIVE.getStatus())
                .in(DataPerm::getDataPermId, dataPermIds);
        return update(updateWrapper);
    }

    @Override
    public String getConditionExpression(String userId, String accessAddress) {
        StringBuffer expression = new StringBuffer();
        List<RoleDataPermRel> roleDataPermRels = roleDataPermRelService.listUserDataPerms(userId, accessAddress);
        if (CollectionUtils.isEmpty(roleDataPermRels)) {
            //默认没有权限
            expression.append(" 1 <> 1");
            return expression.toString();
        }
        User user = userService.getById(userId);
        roleDataPermRels.forEach(roleDataPermRel -> {
            String conditionExpression = roleDataPermRel.getConditionExpression().toUpperCase();
            if (StringUtils.indexOf(conditionExpression, DataPermConst.CURRENT_ORGAN_ID_VARIABLE) != -1) {
                conditionExpression = conditionExpression.replace(DataPermConst.CURRENT_ORGAN_ID_VARIABLE, "'" + user.getOrganId() + "'");
            } else if (StringUtils.indexOf(conditionExpression, DataPermConst.CURRENT_USER_ID_VARIABLE) != -1) {
                conditionExpression = conditionExpression.replace(DataPermConst.CURRENT_USER_ID_VARIABLE, "'" + user.getUserId() + "'");
            } else if (StringUtils.indexOf(conditionExpression, DataPermConst.CURRENT_ORGAN_CHILDS_ID_VARIABLE) != -1) {
                //List<Organ> organs = organService.listOrganOracleTree(user.getOrganId());
                List<Organ> organs = Lists.newArrayList();
                conditionExpression = conditionExpression.replace(DataPermConst.CURRENT_ORGAN_CHILDS_ID_VARIABLE, getOrganSql(organs));
            }
            expression.append(conditionExpression);
            expression.append(" ");
        });
        return expression.toString();
    }

    private String getOrganSql(List<Organ> organs) {
        StringBuffer sqlBuffer = new StringBuffer();
        if (CollectionUtils.isEmpty(organs)) {
            sqlBuffer.append("('noConditionExpression')");
            return sqlBuffer.toString();
        }
        sqlBuffer.append("(");
        int size = organs.size();
        for (int i = 0; i < size; i++) {
            sqlBuffer.append("'");
            sqlBuffer.append(organs.get(i).getOrganId());
            sqlBuffer.append("'");
            if (i != size - 1) {
                sqlBuffer.append(",");
            }
        }
        sqlBuffer.append(")");
        return sqlBuffer.toString();
    }
}
