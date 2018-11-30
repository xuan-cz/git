package com.baidu.controller;

import com.baidu.domain.Users;
import com.baidu.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUsersService usersService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        List<Users> users = usersService.findAll();
        ModelAndView mv = new ModelAndView();
        mv.addObject("users", users);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception {
        Users user = usersService.findById(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", user);
        mv.setViewName("user-show");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Users users) throws Exception {
        usersService.save(users);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(String id)throws Exception {
        Users user = usersService.findUserByIdAndAllRole(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", user);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(String userId,String ...ids)throws Exception{
        System.out.println(userId+"----"+ids.toString());
        usersService.addRoleToUser(userId,ids);
        return "redirect:findAll.do";
    }
}
