package com.pipiqi.project_mycar.realm;

import com.pipiqi.project_mycar.pojo.SysUser;
import com.pipiqi.project_mycar.service.MenuService;
import com.pipiqi.project_mycar.service.RoleService;
import com.pipiqi.project_mycar.service.SysUserService;
import com.pipiqi.project_mycar.utils.R;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private SysUserService sysUserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        /*
        * 用户的授权
        * */
        SysUser sysUser = (SysUser)principalCollection.getPrimaryPrincipal();
        Long userId = sysUser.getUserId();
        // 用户的权限信息
        List<String> permsByUserId = menuService.findPermsByUserId(userId);
        // 用户的角色信息
        List<String> rolesByUserId = roleService.findRolesByUserId(userId);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(rolesByUserId);
        info.addStringPermissions(permsByUserId);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        /*
        * 用户的认证
        *
        * */

        // 1,得到用户名和密码
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        String username = token.getUsername();
        // 拿到的password是char类型的  所以进行封装转换
        String password = new String(token.getPassword());
        // 根据用户名 拿到用户信息
        SysUser userInfo = sysUserService.findUserInfo(username);
        if (userInfo == null) {
            throw new  UnknownAccountException("用户不存在");
        }
        if (userInfo.getPassword().equals(password)) {
            throw new IncorrectCredentialsException("密码错误");
        }
        if (userInfo.getStatus() == 0) {
            throw new LockedAccountException("账户被冻结");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userInfo, password, this.getName());
        return info;
    }
}
