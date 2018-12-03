package com.baidu.controller;

import com.baidu.domain.Role;
import com.baidu.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/findAll.do")
    @Secured("ROLE_ADMIN")
    public ModelAndView findAl() throws Exception {
        List<Role> roles = roleService.findAll();
        ModelAndView mv = new ModelAndView();
        mv.addObject("roles",roles);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/findById.do")
    @Secured("ROLE_ADMIN")
    public ModelAndView findById(String id) throws Exception {
        Role role = roleService.findById(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("role",role);
        mv.setViewName("role-show");
        return mv;
    }

    /**
     * 新建角色信息
     * @param role
     * @return
     * @throws Exception
     */
    @RequestMapping("/save.do")
    @Secured("ROLE_ADMIN")
    public String save(Role role)throws Exception{
        roleService.save(role);
        return "redirect:findAll.do";
    }


    @RequestMapping("/findRoleByIdAndAllPermission.do")
    @Secured("ROLE_ADMIN")
    public ModelAndView findRoleByIdAndAllPermission(String id) throws Exception {
        Role role = roleService.findRoleByIdAndAllPermission(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("role",role);
        mv.setViewName("role-permission-add");
        return mv;
    }

    @RequestMapping("/addPermissionToRole.do")
    @Secured("ROLE_ADMIN")
    public String addPermissionToRole(String roleId,String ... ids) throws Exception {
        roleService.addPermissionToRole(roleId,ids);
        return "redirect:findAll.do";
    }
}

