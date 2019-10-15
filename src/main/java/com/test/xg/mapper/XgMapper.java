package com.test.xg.mapper;

import com.test.xg.bean.Xg;
import com.test.xg.bean.XgParam;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface XgMapper {

    List<Xg> selectXgByCondition(XgParam xgParam);

}
