package com.baidu.controller;

import com.baidu.domain.Permission;
import com.baidu.domain.Product;
import com.baidu.service.IPermissionService;
import org.apache.ibatis.annotations.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        List<Permission> permissions = permissionService.findAll();
        ModelAndView mv = new ModelAndView();
        mv.addObject("permissions", permissions);
        mv.setViewName("permission-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Permission permission)throws Exception{
        permissionService.save(permission);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(String id)throws Exception{
        Permission permission = permissionService.findById(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("permission",permission);
        mv.setViewName("permission-show");
        return mv;
    }
    @RequestMapping("/deletePermission.do")
    public String deletePermission(String id)throws Exception{
        permissionService.deletePermission(id);
        return "redirect:findAll.do";
    }
}
