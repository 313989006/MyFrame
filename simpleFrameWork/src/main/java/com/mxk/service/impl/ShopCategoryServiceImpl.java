package com.mxk.service.impl;

import com.mxk.entity.ShopCategory;
import com.mxk.entity.dto.Result;
import com.mxk.service.ShopCategoryService;
import org.simpleframework.core.annotation.Service;

import java.util.List;

/**
 * @ClassName ShopCategoryServiceImpl
 * @Description TODO
 * @Author ma.kangkang
 * @Date 2020/11/1 12:22
 **/
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {

    @Override
    public Result<Boolean> insert(ShopCategory shopCategory) {
        return null;
    }

    @Override
    public Result<Boolean> update(ShopCategory shopCategory) {
        return null;
    }

    @Override
    public Result<Boolean> delete(Integer shopCategoryId) {
        return null;
    }

    @Override
    public Result<ShopCategory> queryById(Integer shopCategoryId) {
        return null;
    }

    @Override
    public Result<List<ShopCategory>> queryByPage(ShopCategory shopCategory, int pageNum, int pageSize) {
        return null;
    }
}
