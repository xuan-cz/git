package com.baidu.dao;


import com.baidu.domain.SysLog;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ISysLogDao {

    @Select("select * from syslog")
    List<SysLog> findAll()throws Exception;
}
