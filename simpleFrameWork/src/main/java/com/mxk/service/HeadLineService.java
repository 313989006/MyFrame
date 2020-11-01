package com.mxk.service;

import com.mxk.entity.HeadLine;
import com.mxk.entity.dto.Result;

import java.util.List;

public interface HeadLineService {

    Result<Boolean> insert(HeadLine headLine);

    Result<Boolean> update(HeadLine headLine);

    Result<Boolean> delete(HeadLine headLine);

    Result<HeadLine> queryById(String headLineId);

    Result<List<HeadLine>> queryByPage(HeadLine headLine,int pageNum,int pageSize);

}
