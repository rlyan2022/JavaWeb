package com.sanqing.service;

import com.sanqing.dao.IBasDictDAO;
import com.sanqing.po.BasDict;
import com.sanqing.util.PageResult;

import java.util.Map;

public class BasDictService {
    private IBasDictDAO bdoo;        //数据字典数据访问层

    public void setBdoo(IBasDictDAO bdoo) {
        this.bdoo = bdoo;            //注入数据字典数据访问层
    }

    public PageResult findAll(Map paramMap) {// 查询数据
        return bdoo.findAll(paramMap);
    }

    public Boolean findById(Long dictId) {//判断数据字典编号是否存在
        if (bdoo.findById(dictId) == null) {
            return false;
        } else {
            return true;
        }
    }

    public BasDict findBydictId(Long dictId) {//通过数据字典编号查找
        return bdoo.findById(dictId);
    }


    public void add(BasDict basdict) {// 新增数据字典信息
        bdoo.save(basdict);
    }


    public void update(BasDict basdict) {// 修改数据字典信息
        bdoo.merge(basdict);
    }


    public void delete(BasDict basdict) {// 删除数字典信息
        bdoo.delete(basdict);

    }

}
