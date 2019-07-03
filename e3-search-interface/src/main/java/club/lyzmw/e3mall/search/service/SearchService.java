package club.lyzmw.e3mall.search.service;

import club.lyzmw.e3mall.common.pojo.SearchResult;

public interface SearchService {

	SearchResult search(String keyword, int page, int rows)  throws Exception;
}
