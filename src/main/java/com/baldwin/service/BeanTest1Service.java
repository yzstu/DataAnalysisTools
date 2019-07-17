package com.baldwin.service;

import com.baldwin.bean.RstBeanTest;
import com.baldwin.mapper.BeanTest1Mapper;
import com.common.bean.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class BeanTest1Service implements BeanTest1Mapper {
    @Autowired
    BeanTest1Mapper beanTest1Mapper;
    public ArrayList<RstBeanTest> getInfoList(Criteria criteria) {
        ArrayList<RstBeanTest> rstBeanTests = beanTest1Mapper.getInfoList(criteria);
        return rstBeanTests;
    }
}
