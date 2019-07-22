package club.lyzmw.e3mall.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import club.lyzmw.e3mall.common.utils.E3Result;
import club.lyzmw.e3mall.pojo.TbUser;
import club.lyzmw.e3mall.sso.service.RegisterService;

/**
 * 登陆页面
 * @author Administrator
 *
 */
@Controller
public class RegisterController {
	@Autowired
	private RegisterService registerService;
	
	@RequestMapping("/page/register")
	public String showRegitster() {		
		return  "register";
	}
	@RequestMapping("/user/check/{param}/{type}")
	@ResponseBody
	public E3Result checkData(@PathVariable String param, @PathVariable Integer type) {
		E3Result e3Result = registerService.checkData(param, type);
		return e3Result;
	}
	
	@RequestMapping(value="/user/register", method=RequestMethod.POST)
	@ResponseBody
	public E3Result register(TbUser user) {
		E3Result e3Result = registerService.register(user);
		return e3Result;
	}
}
