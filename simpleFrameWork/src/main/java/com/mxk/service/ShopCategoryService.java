package com.mxk.service;

import com.mxk.entity.HeadLine;
import com.mxk.entity.ShopCategory;
import com.mxk.entity.dto.Result;

import java.util.List;

public interface ShopCategoryService {

    Result<Boolean> insert(ShopCategory shopCategory);

    Result<Boolean> update(ShopCategory shopCategory);

    Result<Boolean> delete(Integer shopCategoryId);

    Result<ShopCategory> queryById(Integer shopCategoryId);

    Result<List<ShopCategory>> queryByPage(ShopCategory shopCategory, int pageNum, int pageSize);
}
