package club.lyzmw.e3mall.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import club.lyzmw.e3mall.item.pojo.Item;
import club.lyzmw.e3mall.pojo.TbItem;
import club.lyzmw.e3mall.pojo.TbItemDesc;
import club.lyzmw.e3mall.service.ItemService;

/**
 * 商品详情页
 * @author Administrator
 *
 */
@Controller
public class ItemController {
	@Autowired
	ItemService itemService;

	@RequestMapping("/item/{itemId}")
	public String showItemInfo(@PathVariable Long itemId,Model model) {
		TbItem tbItem = itemService.getItemById(itemId);
		Item item = new Item(tbItem);
		TbItemDesc itemDesc = itemService.getItemDescById(itemId);
		model.addAttribute("item", item);
		model.addAttribute("itemDesc", itemDesc);
		return "item";
	}
}
