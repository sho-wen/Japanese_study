package org.jiangwen.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jiangwen.common.lang.ApiRestResponse;
import org.jiangwen.common.resvo.FrontMenu;
import org.jiangwen.entity.FrontMenuTable;
import org.jiangwen.entity.RoleMenuTable;
import org.jiangwen.entity.UserInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author name：JiangWen
 * @since 2021-10-03
 */
@RestController
@RequestMapping("/menu")
public class FrontMenuTableController extends BaseController {

    @GetMapping("/nav")
    public ApiRestResponse nav(Principal principal) {
        UserInfo userInfo = userInfoService.getByUsername(principal.getName());

        // 获取权限信息
        String authorityInfo = userInfoService.getUserAuthorityInfo(userInfo.getUserId());
        String[] authorityInfoArray = StringUtils.tokenizeToStringArray(authorityInfo, ",");

        Map<Object, Object> navMap = frontMenuTableService.getCurrentUserNav();
        navMap.put("authoritys", authorityInfoArray);

        return ApiRestResponse.success(navMap);

    }

    @GetMapping("/info/{menuId}")
    @PreAuthorize("hasAuthority('sys:menu:list')")
    public ApiRestResponse menuInfo(@PathVariable(name = "menuId") Long menuId) {
        return ApiRestResponse.success(frontMenuTableService.getById(menuId));
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('sys:menu:list')")
    public ApiRestResponse list() {
        List<FrontMenu> menus = frontMenuTableService.tree();
        return ApiRestResponse.success(menus);
    }

    @Transactional
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('sys:menu:save')")
    public ApiRestResponse save(@Validated @RequestBody FrontMenuTable frontMenuTable, Principal principal) {

        int count = frontMenuTableService.count(new QueryWrapper<FrontMenuTable>().eq("perms", frontMenuTable.getPerms()));
        if (count > 0) {
            return ApiRestResponse.error("该权限编码已经存在");
        }

        frontMenuTable.setCreater(principal.getName());
        frontMenuTable.setCreateTime(LocalDateTime.now());
        frontMenuTableService.save(frontMenuTable);
        return ApiRestResponse.success(frontMenuTable);
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('sys:menu:update')")
    public ApiRestResponse update(@Validated @RequestBody FrontMenuTable frontMenuTable, Principal principal) {

        List<FrontMenuTable> menuList = frontMenuTableService.permsNoUnique(frontMenuTable);
        if (menuList.size() > 0) {
            return ApiRestResponse.error("该权限编码已经存在");
        }

        frontMenuTable.setUpdater(principal.getName());
        frontMenuTable.setUpdateTime(LocalDateTime.now());
        frontMenuTableService.updateById(frontMenuTable);

        // 清除所有与该菜单相关的权限缓存
        userInfoService.clearUserAuthorityInfoByMenuId(frontMenuTable.getFrontMenuId());
        return ApiRestResponse.success(frontMenuTable);
    }

    @Transactional
    @PostMapping("/delete/{menuId}")
    @PreAuthorize("hasAuthority('sys:menu:delete')")
    public ApiRestResponse delete(@PathVariable("menuId") Long menuId) {
        FrontMenuTable frontMenuTable = frontMenuTableService.getById(menuId);
        // 判断菜单是否有子菜单
        int count = frontMenuTableService.count(new QueryWrapper<FrontMenuTable>().eq("parent_id", menuId));
        if (count > 0) {
            return ApiRestResponse.error("该菜单存在子菜单");
        }

        // 同步删除菜单角色中间关联表
        roleMenuTableService.remove(new QueryWrapper<RoleMenuTable>().eq("front_menu_id", menuId));
        // 删除菜单
        frontMenuTableService.removeById(menuId);
        // 清除缓存
        userInfoService.clearUserAuthorityInfoByMenuId(menuId);

        return ApiRestResponse.success(frontMenuTable);
    }

}
