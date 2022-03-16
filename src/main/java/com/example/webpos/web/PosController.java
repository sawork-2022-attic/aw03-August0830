package com.example.webpos.web;

import com.example.webpos.biz.PosService;
import com.example.webpos.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PosController {

    private PosService posService;

    @Autowired
    public void setPosService(PosService posService) {
        this.posService = posService;
    }

    @GetMapping("/")
    public String pos(Model model) {
        model.addAttribute("products", posService.products());
        model.addAttribute("cart", posService.getCart());
        return "index";
    }

    @GetMapping("/add")
    public String add(@RequestParam(name = "pid") String pid,Model model){
        posService.add(pid, 1);
        model.addAttribute("products", posService.products());
        model.addAttribute("cart", posService.getCart());
        return "index";
    }

    @GetMapping("/modify")
    public String modify(@RequestParam(name = "pid") String pid,
    @RequestParam(name = "quantity") int quantity, Model model){
        posService.modify(pid, quantity);
        model.addAttribute("products", posService.products());
        model.addAttribute("cart", posService.getCart());
        return "index";
    }

    @GetMapping("/remove")
    public String remove(@RequestParam(name = "pid") String pid,Model model){
        posService.remove(pid);
        model.addAttribute("products", posService.products());
        model.addAttribute("cart", posService.getCart());
        return "index";
    }

    @GetMapping("/empty")
    public String empty(Model model){
        posService.empty();
        model.addAttribute("products", posService.products());
        model.addAttribute("cart", posService.getCart());
        return "index";
    }

    @GetMapping("/checkout")
    public String checkout(Model model){
        model.addAttribute("checkoutAmount", posService.checkout(posService.getCart()));
        posService.checkout(posService.getCart());
        return "paid";
    }
}
