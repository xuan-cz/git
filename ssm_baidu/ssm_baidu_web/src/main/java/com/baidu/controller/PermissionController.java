package com.baidu.controller;

import com.baidu.domain.Permission;
import com.baidu.domain.Product;
import com.baidu.service.IPermissionService;
import org.apache.ibatis.annotations.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
    @Secured("ROLE_ADMIN")
    public ModelAndView findAll() throws Exception {
        List<Permission> permissions = permissionService.findAll();
        ModelAndView mv = new ModelAndView();
        mv.addObject("permissions", permissions);
        mv.setViewName("permission-list");
        return mv;
    }

    @RequestMapping("/save.do")
    @Secured("ROLE_ADMIN")
    public String save(Permission permission)throws Exception{
        permissionService.save(permission);
        return "redirect:findAll.do";
    }

    /**
     * 回显
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/echo.do")
    @Secured("ROLE_ADMIN")
    public ModelAndView echo(String id)throws Exception{
        Permission permission = permissionService.findByid(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("permission",permission);
        mv.setViewName("permission-update");
        return mv;
    }
    /**
     * 修改
     * @param permission
     * @return
     * @throws Exception
     */
    @RequestMapping("/updateById.do")
    @Secured("ROLE_ADMIN")
    public String findById(Permission permission)throws Exception{
        permissionService.updateById(permission);
        return "redirect:findAll.do";
    }

    /**
     * 删除权限信息
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/deletePermission.do")
    @Secured("ROLE_ADMIN")
    public String deletePermission(String id)throws Exception{
        permissionService.deletePermission(id);
        return "redirect:findAll.do";
    }
    @RequestMapping("/deleteByIds.do")
    @Secured("ROLE_ADMIN")
    public String deleteByIds(String ... ids) throws Exception {
        permissionService.deleteByIds(ids);
        return "redirect:findAll.do";
    }
}
