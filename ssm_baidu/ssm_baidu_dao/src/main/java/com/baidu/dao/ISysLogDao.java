package com.baidu.dao;


import com.baidu.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ISysLogDao {

    @Select("select * from syslog")
    List<SysLog> findAll()throws Exception;

    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) values(" +
            "#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void save(SysLog log) throws Exception;
}
