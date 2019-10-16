package com.test.xg.mapper;

import com.test.xg.bean.Notice;
import com.test.xg.bean.PersonalProblem;
import com.test.xg.bean.Xg;
import com.test.xg.bean.XgParam;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface XgMapper {

    List<Xg> selectXgByCondition(XgParam xgParam);

    int importPersonalProblem(List<PersonalProblem> personalProblemList);

    int importNotice(List<Notice> noticeList);
}
