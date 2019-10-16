package com.test.xg.mapper;

import com.test.xg.bean.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface XgMapper {

    List<Xg> selectXgByCondition(XgParam xgParam);

    int importPersonalProblem(@Param("personalProblemList")List<PersonalProblem> personalProblemList);

    int importNotice(@Param("noticeList")List<Notice> noticeList);

    List<PersonalProblem> selectPersonalProblemByCondition(PersonalProblemDto personalProblemDto);

    List<Notice> selectNotice(NoticeDto noticeDto);
}
