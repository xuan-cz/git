package com.baidu.service;

import com.baidu.domain.SysLog;

import java.util.List;

public interface ISysLogService {

    List<SysLog> findAll()throws Exception;
}
