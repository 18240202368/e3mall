package club.lyzmw.e3mall.sso.service;

import club.lyzmw.e3mall.common.utils.E3Result;

public interface LoginService {
	public E3Result userLogin(String username,String password);
}
