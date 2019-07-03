package club.lyzmw.e3mall.service;

import java.util.List;

import club.lyzmw.e3mall.common.pojo.EasyUITreeNode;



public interface ItemCatService {

	List<EasyUITreeNode> getItemCatlist(long parentId);
}
