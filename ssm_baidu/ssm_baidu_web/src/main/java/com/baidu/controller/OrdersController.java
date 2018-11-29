package com.baidu.controller;

import com.baidu.domain.Orders;
import com.baidu.service.IOrdersService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;

    /**
     * 未分页
     * @return
     * @throws Exception
     @RequestMapping("/findAll.do") public ModelAndView findAll() throws Exception {
     List<Orders> orders = ordersService.findAll();
     ModelAndView mv = new ModelAndView();
     mv.addObject("orders", orders);
     mv.setViewName("orders-list");
     return mv;
     }
     */
    /**
     * 分页
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(
            @RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
            @RequestParam(name = "pageSize", required = true, defaultValue = "5") Integer pageSize
    ) throws Exception {
        List<Orders> orders = ordersService.findAll(page, pageSize);
        PageInfo<Orders> pageInfo = new PageInfo<>(orders);
        ModelAndView mv = new ModelAndView();
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("orders-list");
        return mv;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception {
        Orders orders = ordersService.findById(id);
        System.out.println(orders.getTravellers());
        ModelAndView mv = new ModelAndView();
        mv.addObject("orders", orders);
        mv.setViewName("orders-show");
        return mv;
    }
}
