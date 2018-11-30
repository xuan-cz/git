package com.baidu.domain;

import lombok.Data;

import java.util.List;

@Data
public class Role {
    private String id;
    private String roleName;
    private String roleDesc;
    private List<Permission> permissions;
    private List<Users> users;

}
