package club.lyzmw.e3mall.sso.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import club.lyzmw.e3mall.common.utils.E3Result;
import club.lyzmw.e3mall.mapper.TbUserMapper;
import club.lyzmw.e3mall.pojo.TbUser;
import club.lyzmw.e3mall.pojo.TbUserExample;
import club.lyzmw.e3mall.pojo.TbUserExample.Criteria;
import club.lyzmw.e3mall.sso.service.RegisterService;
@Service
public class RegisterServiceImpl implements RegisterService {
	@Autowired
	private TbUserMapper userMapper;
	
	public E3Result checkData(String param,Integer type) {
		//根据不同的type生成不同的查询条件
		TbUserExample userExample = new TbUserExample();
		Criteria criteria = userExample.createCriteria();
		//1：用户名 2：手机号 3：邮箱
		if(type == 1) {
			criteria.andUsernameEqualTo(param);
		}else if (type == 2) {
			criteria.andPhoneEqualTo(param);
		}else if (type == 3) {
			criteria.andEmailEqualTo(param);
		}else {
			return E3Result.build(400, "数据类型错误");
		}
		//执行查询
		List<TbUser> list = userMapper.selectByExample(userExample);
		if(list.size()>0 && list != null) {
			//如果有数据则返回false
			E3Result.ok(false);
		}
		return E3Result.ok(true);
	} 
	@Override
	public E3Result register(TbUser user) {
		//校验数据完整性
		if(StringUtils.isBlank(user.getUsername())||StringUtils.isBlank(user.getPassword())
									||StringUtils.isBlank(user.getPhone())) {
			return E3Result.build(400, "用户数据不完整", "注册失败");
		}
		//1：用户名 2：手机号 3：邮箱
		E3Result checkData = checkData(user.getUsername(), 1);
		if (!(boolean)checkData.getData()) {
			return E3Result.build(400, "此用户名已经被占用");
		}
		E3Result checkData2 = checkData(user.getPhone(), 2);
		if (!(boolean)checkData2.getData()) {
			return E3Result.build(400, "此手机号已经注册过账号");
		}
		//补全pojo属性
		user.setCreated(new Date());
		user.setUpdated(new Date());
		//对密码进行MD5加密
		String md5pw = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		user.setPassword(md5pw);
		//把用户数据插入到数据库中
		userMapper.insert(user);
		//返回添加成功
		return E3Result.ok();
	}

}
