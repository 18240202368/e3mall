package club.lyzmw.e3mall.sso.service;

import club.lyzmw.e3mall.common.utils.E3Result;

public interface TokenService {
	public E3Result getUserByToken(String token);
}
