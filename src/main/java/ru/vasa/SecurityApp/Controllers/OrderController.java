package ru.vasa.SecurityApp.Controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vasa.SecurityApp.dao.CategoryDAO;
import ru.vasa.SecurityApp.dao.CustomerDAO;
import ru.vasa.SecurityApp.dao.OrderDAO;
import ru.vasa.SecurityApp.models.Category;
import ru.vasa.SecurityApp.models.Customer;
import ru.vasa.SecurityApp.models.Order;
import ru.vasa.SecurityApp.models.Product;
import ru.vasa.SecurityApp.util.CategoryValidator;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private OrderDAO orderDAO;

    @Autowired
    public OrderController(CategoryValidator categoryValidator, OrderDAO orderDAO) {

        this.orderDAO = orderDAO;
    }



//catrgories/allcategories


    @GetMapping({"/allorders","/"})
    public String makeAdminPage(Model model) {

        model.addAttribute("orders", orderDAO.index());
//        System.out.println(categoryDAO.index().get(0).getName());
        return "allorders";
    }
    @GetMapping("/{id_order}/edit")//   product/1/edit
    public String edit(Model model, @PathVariable("id_order") int id) {

        model.addAttribute("order",orderDAO.show(id));
        System.out.println("3");
        return "/editorder";
    }


    @GetMapping("/{id_order}")
    public String update2(@ModelAttribute("order") @Valid Order order, BindingResult bindingResult,
                          @PathVariable("id_order") int id) {
        System.out.println("1");
        if (bindingResult.hasErrors()) {
            //System.out.println("Какие то ошибка");
            return "/hello";
        }
        System.out.println("2");

        orderDAO.update(id,order);
        System.out.println("4");
        return "redirect:/orders/allorders";
    }

    @GetMapping("/new/neworder")
    public String create(@ModelAttribute("order") @Valid Order order,
                         BindingResult bindingResult) {
        System.out.println("ПЕРЕИНУЛ НА КРЕАТ");
        orderDAO.save(order);
        return "redirect:/orders/allorders";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("order",new Order());
        //System.out.println("ПЕРЕКИНУЛ НА НЕВ");
        return "/neworder";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        System.out.println("DELETE "+ id);
        orderDAO.delete(id);
        return "redirect:/orders/allorders";
    }
}
