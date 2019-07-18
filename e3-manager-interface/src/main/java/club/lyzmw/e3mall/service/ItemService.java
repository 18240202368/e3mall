package club.lyzmw.e3mall.service;

import club.lyzmw.e3mall.common.pojo.EasyUIDataGridResult;
import club.lyzmw.e3mall.common.utils.E3Result;
import club.lyzmw.e3mall.pojo.TbItem;
import club.lyzmw.e3mall.pojo.TbItemDesc;

public interface ItemService {

	TbItem getItemById(long itemId);
	EasyUIDataGridResult getItemList(int page, int rows);
	E3Result addItem(TbItem item, String desc);
	TbItemDesc getItemDescById(long itemId);
}
