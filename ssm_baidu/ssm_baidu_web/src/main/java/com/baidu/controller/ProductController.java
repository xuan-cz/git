package com.baidu.controller;

import com.baidu.domain.Product;
import com.baidu.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @RequestMapping("/findAll")
    public ModelAndView findAll() throws Exception {
        List<Product> products = productService.findAll();
        ModelAndView mv = new ModelAndView();
        mv.addObject("products", products);
        mv.setViewName("product-list");
        return mv;
    }
    @RequestMapping("save.do")
    public String save(Product product){
        productService.save(product);
        return "redirect:findAll.do";
    }
}
