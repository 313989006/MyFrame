package com.mxk.service.impl;

import com.mxk.entity.HeadLine;
import com.mxk.entity.ShopCategory;
import com.mxk.entity.dto.MainPageInfoDto;
import com.mxk.entity.dto.Result;
import com.mxk.service.HeadLineService;
import com.mxk.service.HeadLineShppCategoryService;
import com.mxk.service.ShopCategoryService;
import org.simpleframework.core.annotation.Service;
import org.simpleframework.inject.annotation.Autowired;

import java.util.List;

/**
 * @ClassName HeadLineShppCategoryServiceImpl
 * @Description TODO
 * @Author ma.kangkang
 * @Date 2020/11/1 12:55
 **/
@Service
public class HeadLineShppCategoryServiceImpl implements HeadLineShppCategoryService {

    @Autowired(value = "HeadLineServiceImpl")
    private HeadLineService headLineService;

    @Autowired(value = "ShopCategoryServiceImpl")
    private ShopCategoryService shopCategoryService;

    @Override
    public Result<MainPageInfoDto> getMainPageInfo() {

        // 获取头条列表
        HeadLine headLineCondition = new HeadLine();
        headLineCondition.setEnableStatus(1);
        Result<List<HeadLine>> headLineResult = headLineService.queryByPage(headLineCondition, 1, 4);

        // 获取店铺类别列表
        ShopCategory shopCategoryCondition = new ShopCategory();
        Result<List<ShopCategory>> shopCategoryResult = shopCategoryService.queryByPage(shopCategoryCondition, 1, 100);

        // 合并并返回
        Result<MainPageInfoDto> result = mergeMainPageInfoResult(headLineResult,shopCategoryResult);
        return result;
    }

    private Result<MainPageInfoDto> mergeMainPageInfoResult(Result<List<HeadLine>> headLineResult, Result<List<ShopCategory>> shopCategoryResult) {
        return  null;
    }
}
