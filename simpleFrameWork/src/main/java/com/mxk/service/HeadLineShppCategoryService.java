package com.mxk.service;

import com.mxk.entity.dto.MainPageInfoDto;
import com.mxk.entity.dto.Result;

public interface HeadLineShppCategoryService {

    Result<MainPageInfoDto> getMainPageInfo();
}
