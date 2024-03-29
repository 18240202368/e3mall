package club.lyzmw.e3mall.content.service;

import java.util.List;

import club.lyzmw.e3mall.common.pojo.EasyUITreeNode;
import club.lyzmw.e3mall.common.utils.E3Result;



public interface ContentCategoryService {

	List<EasyUITreeNode> getContentCatList(long parentId);
	E3Result addContentCategory(long parentId, String name);
}
