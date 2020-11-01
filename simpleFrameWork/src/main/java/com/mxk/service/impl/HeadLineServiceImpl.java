package com.mxk.service.impl;

import com.mxk.entity.HeadLine;
import com.mxk.entity.dto.Result;
import com.mxk.service.HeadLineService;

import java.util.List;

/**
 * @ClassName HeadLineService
 * @Description TODO
 * @Author ma.kangkang
 * @Date 2020/11/1 12:20
 **/
public class HeadLineServiceImpl implements HeadLineService {

    @Override
    public Result<Boolean> insert(HeadLine headLine) {
        return null;
    }

    @Override
    public Result<Boolean> update(HeadLine headLine) {
        return null;
    }

    @Override
    public Result<Boolean> delete(Integer headLineId) {
        return null;
    }

    @Override
    public Result<HeadLine> queryById(Integer headLineId) {
        return null;
    }

    @Override
    public Result<List<HeadLine>> queryByPage(HeadLine headLine, int pageNum, int pageSize) {
        return null;
    }
}
