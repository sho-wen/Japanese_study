package org.jiangwen.service;

import org.jiangwen.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author name：JiangWen
 * @since 2021-10-03
 */
public interface UserInfoService extends IService<UserInfo> {

    UserInfo getByUsername(String username);

    String getUserAuthorityInfo(long userId);

    // 清除缓存
    void clearUserAuthorityInfo(long userId);

    void clearUserAuthorityInfoByRoleId(long roleId);

    void clearUserAuthorityInfoByMenuId(long menuId);

    int usernameNum(String username);
}
