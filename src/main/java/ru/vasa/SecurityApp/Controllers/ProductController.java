package ru.vasa.SecurityApp.Controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vasa.SecurityApp.dao.ProductDAO;
import ru.vasa.SecurityApp.models.Product;
import ru.vasa.SecurityApp.util.ProductValidator;

@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductDAO productDAO;
    private final ProductValidator productValidator;


    @Autowired
    public ProductController(ProductValidator productValidator, ProductDAO productDAO) {
        this.productValidator = productValidator;
        this.productDAO = productDAO;
    }


    @GetMapping("/hello")
    public String makeBasePage() {

        return "hello";
    }
    @GetMapping("/")
    public String makeBasePage1() {

        return "hello";
    }

    @GetMapping({"/allproduct","/"})
    public String makeAdminPage(Model model) {

        model.addAttribute("products", productDAO.index());
        return "allproduct";
    }
    @GetMapping("/{id_product}/edit")//   product/1/edit
    public String edit(Model model, @PathVariable("id_product") int id) {

        model.addAttribute("product",productDAO.show(id));


        return "/edit";
    }


    @GetMapping("/{id_product}")
    public String update2(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult,
                         @PathVariable("id_product") int id) {

        productValidator.validate(product, bindingResult);
        if (bindingResult.hasErrors()) {
            System.out.println("Какие то ошибка");
            return "/hello";
        }

        productDAO.update(id,product);
        return "redirect:/products/allproduct";
    }

    @GetMapping("/new/newproduct")
    public String create(@ModelAttribute("product") @Valid Product product,
                         BindingResult bindingResult) {
        //System.out.println("ПЕРЕИНУЛ НА КРЕАТ");
        productValidator.validate(product, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/";
        }
        productDAO.save(product);
        return "redirect:/products/allproduct";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("product",new Product());
        System.out.println("ПЕРЕКИНУЛ НА НЕВ");
        return "/new";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        System.out.println("DELETE "+ id);
        productDAO.delete(id);
        return "redirect:/products/allproduct";
    }
}
