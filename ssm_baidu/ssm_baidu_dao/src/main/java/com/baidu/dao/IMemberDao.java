package com.baidu.dao;

import com.baidu.domain.Member;
import org.apache.ibatis.annotations.Select;

public interface IMemberDao {

    @Select("select * from member where id = #{memberId}")
    Member findById(String memberId);
}
