package com.baldwin.mapper;

import com.baldwin.bean.RstBeanTest;
import com.common.bean.Criteria;

import java.util.ArrayList;

public interface BeanTest1Mapper {
    ArrayList<RstBeanTest> getInfoList(Criteria criteria);
}
