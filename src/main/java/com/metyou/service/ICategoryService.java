package com.metyou.service;

import com.metyou.common.ServerResponse;
import com.metyou.pojo.Category;

import java.util.List;

public interface ICategoryService {
    ServerResponse addCategory(String categoryName, Integer parentId);

    ServerResponse updateCategoryName(Integer categoryId, String categoryName);

    ServerResponse<List<Category>> getChildrenParallelCatrgory(Integer categoryId);

    ServerResponse<List<Integer>> selectCategoryAndChinldrenById(Integer catrgoryId);

}
