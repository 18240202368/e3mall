package club.lyzmw.e3mall.service;

import club.lyzmw.e3mall.common.pojo.EasyUIDataGridResult;
import club.lyzmw.e3mall.common.utils.E3Result;
import club.lyzmw.e3mall.pojo.TbItem;

public interface ItemService {

	TbItem getItemById(long itemId);
	EasyUIDataGridResult getItemList(int page, int rows);
	E3Result addItem(TbItem item, String desc);
}
