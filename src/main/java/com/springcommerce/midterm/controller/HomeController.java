package com.springcommerce.midterm.controller;

import com.springcommerce.midterm.config.CustomerDetails;
import com.springcommerce.midterm.dto.*;
import com.springcommerce.midterm.model.Customer;
import com.springcommerce.midterm.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private IProductService productService;

    @Autowired
    private IGradeService gradeService;

    @Autowired
    private ICartService cartService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IOrderService orderService;

    @GetMapping
    public String homePage(Model model, Principal principal) {
        List<ProductDTO> productDTOList = productService.getAll();
        if (principal != null) {
            String email = ((CustomerDetails) ((Authentication) principal).getPrincipal()).getUsername();
            model.addAttribute("cartQuantity", customerService.sumCartQuantity(email));
            model.addAttribute("email", email);
        }

        model.addAttribute("products", productDTOList);
        model.addAttribute("title", "SpringCommerce");
        return "contents/home";
    }

    @GetMapping("/{id}")
    public String productDetail(Model model, Principal principal, @PathVariable("id") Long id) {
        ProductDTO productDTO = productService.getOne(id);
        GradeDTO gradeDTO = gradeService.getOne(productDTO.getGrade());
        if (principal != null) {
            String email = ((CustomerDetails) ((Authentication) principal).getPrincipal()).getUsername();
            model.addAttribute("cartQuantity", customerService.sumCartQuantity(email));
            model.addAttribute("email", email);
        }
        model.addAttribute("product", productDTO);
        model.addAttribute("grade", gradeDTO);
        model.addAttribute("title", "Mô hình "+productDTO.getName());
        return "contents/detail";
    }

    @GetMapping("/cart")
    public String cartPage(Model model, Principal principal) {
//        if (principal == null) {
//            return "redirect:/login";
//        }
        String email = ((CustomerDetails) ((Authentication) principal).getPrincipal()).getUsername();
        List<CartResDTO> cartResDTOList = cartService.getAllByCustomer(email);
        double total = 0;
        for (CartResDTO cartResDTO: cartResDTOList) {
            total += (cartResDTO.getQuantity() * cartResDTO.getProductPrice());
        }
        model.addAttribute("cartQuantity", customerService.sumCartQuantity(email));
        model.addAttribute("email", email);
        model.addAttribute("title", "Giỏ hàng");
        model.addAttribute("carts", cartResDTOList);
        model.addAttribute("total", total);
        return "contents/cart";
    }

    @GetMapping("/orders")
    public String ordersPage(Model model, Principal principal) {
//        if (principal == null) {
//            return "redirect:/login";
//        }
        String email = ((CustomerDetails) ((Authentication) principal).getPrincipal()).getUsername();
        List<OrderResDTO> orderDTOList = orderService.getAll(email);
        model.addAttribute("cartQuantity", customerService.sumCartQuantity(email));
        model.addAttribute("email", email);
        model.addAttribute("orders", orderDTOList);
        model.addAttribute("title", "Đơn hàng");
        return "contents/order";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("customerDTO", new CustomerDTO());
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("customerDTO", new CustomerDTO());
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@ModelAttribute CustomerDTO customerDTO, Model model) {
        Customer existingCustomer = customerService.getOne(customerDTO.getEmail());
        if (existingCustomer != null) {
            model.addAttribute("emailErr", "Email này đã được đăng ký");
            return "register";
        }

        if (!customerDTO.getPassword().equals(customerDTO.getRepeatPassword())) {
            model.addAttribute("passwordErr", "Mật khẩu nhập lại không đúng");
            return "register";
        }

        customerDTO.setPassword(bCryptPasswordEncoder.encode(customerDTO.getPassword()));
        customerService.register(customerDTO);
        return "redirect:/login";
    }
}
