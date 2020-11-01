package com.mxk.entity.dto;

import com.mxk.entity.HeadLine;
import com.mxk.entity.ShopCategory;
import lombok.Data;

import java.util.List;

/**
 * @ClassName MainPageInfoDto
 * @Description TODO
 * @Author ma.kangkang
 * @Date 2020/11/1 12:53
 **/
@Data
public class MainPageInfoDto {

    private List<HeadLine> headLineList;

    private List<ShopCategory> shopCategoryList;

}
