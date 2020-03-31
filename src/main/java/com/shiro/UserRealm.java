package com.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.doain.UserDomain;
import com.service.UserService;

/**
 * 自定义realm
 * 
 * @author qupengfei
 *
 */

public class UserRealm extends AuthorizingRealm {

	@Autowired
	private UserService service;

	// 执行授权逻辑
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		System.out.println("执行授权逻辑");
		// 给资源进行授权
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		// 添加资源的授权字符串
		//info.addStringPermission("user:add");
		//到数据库查询当前登录用户的授权字符串
		//获取当前登录用户
		Subject subject=SecurityUtils.getSubject();
		UserDomain user=(UserDomain) subject.getPrincipal();
		UserDomain u=service.findById(user.getId());
		info.addStringPermission(u.getPerms());
		return info;
	}

	// 执行认证逻辑
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) {
		// TODO Auto-generated method stub
		System.out.println("执行认证逻辑");

		// 编写shiro判断逻辑，判断用户名和密码
		// 1.判断用户名
		UsernamePasswordToken token = (UsernamePasswordToken) arg0;
		UserDomain user = service.findName(token.getUsername());
		if (user == null) {
			// 用户名不存在
			return null;// shiro底层会抛出UnKnowAccountException
		}
		/**
		 * 2.判断密码 SimpleAuthenticationInfo为AuthenticationInfo的子类
		 * 报错会返回IncorrectCredentialsException异常表示密码错误
		 */

		return new SimpleAuthenticationInfo(user, user.getPassword(), "");
	}

}
