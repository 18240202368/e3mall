package club.lyzmw.e3mall.sso.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import club.lyzmw.e3mall.common.jedis.JedisClient;
import club.lyzmw.e3mall.common.utils.E3Result;
import club.lyzmw.e3mall.common.utils.JsonUtils;
import club.lyzmw.e3mall.pojo.TbUser;
import club.lyzmw.e3mall.sso.service.TokenService;


@Service
public class TokenServiceImpl implements TokenService {
	
	@Autowired
	private JedisClient jedisClient;
	@Value("${SESSION_EXPIRE}")
	private Integer SESSION_EXPIRE;
	@Override
	public E3Result getUserByToken(String token) {
		//根据token取用户信息
		String josn = jedisClient.get("SESSION:"+token);
		//如果取不到信息
		if (StringUtils.isBlank(josn)) {
			return E3Result.build(201, "登录已过期");
		}
		//取到用户信息则更新登录时间
		jedisClient.expire("SESSION:"+token, SESSION_EXPIRE);
		//返回结果，E3Result中包含TbUser对象
		TbUser tbUser = JsonUtils.jsonToPojo(josn, TbUser.class);
		return E3Result.ok(tbUser);
	}

}
