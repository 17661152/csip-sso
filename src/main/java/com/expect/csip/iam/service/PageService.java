package com.expect.csip.iam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.expect.csip.iam.domain.Page;
import com.expect.csip.iam.domain.condition.PageCondition;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * 页面 服务类
 * </p>
 *
 * @author jack.zeng
 * @since 2019-01-07
 */
public interface PageService extends IService<Page> {

    /**
     * 页面分页列表
     *
     * @param condition
     * @return
     */
    IPage<Page> listPagePage(PageCondition condition);

    /**
     * 主键查询页面
     *
     * @param pageId
     * @return
     */
    Page getPageById(String pageId);

    /**
     * 主键批量查询页面
     *
     * @param pageIds
     * @return
     */
    Map<String, Page> listPageMapsByIds(List<String> pageIds);

    /**
     * 主键批量删除页面
     *
     * @param pageIds
     * @return
     */
    Boolean deletePageByIds(List<String> pageIds);

    /**
     * 主键删除页面
     *
     * @param pageId
     * @return
     */
    Boolean deletePageById(String pageId);

    /**
     * 保存页面
     *
     * @param page
     * @return
     */
    Page savePage(Page page);

    /**
     * 修改页面
     *
     * @param page
     * @return
     */
    Boolean updatePage(Page page);

    /**
     * 查询菜单关联的页面列表
     * @param menuIds
     * @return
     */
    List<Page> listMenuPage(List<String> menuIds);

    /**
     * 禁用页面
     * @param pageId
     * @return
     */
    Boolean disablePage(String pageId);

    /**
     * 启用页面
     * @param pageId
     * @return
     */
    Boolean enablePage(String pageId);
}
