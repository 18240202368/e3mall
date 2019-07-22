package club.lyzmw.e3mall.sso.service;

import club.lyzmw.e3mall.common.utils.E3Result;
import club.lyzmw.e3mall.pojo.TbUser;

public interface RegisterService {
	public E3Result register(TbUser user);

	public E3Result checkData(String param, int type);
}
