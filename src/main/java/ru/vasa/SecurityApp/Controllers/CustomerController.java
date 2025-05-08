package ru.vasa.SecurityApp.Controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vasa.SecurityApp.dao.CategoryDAO;
import ru.vasa.SecurityApp.dao.CustomerDAO;
import ru.vasa.SecurityApp.models.Category;
import ru.vasa.SecurityApp.models.Customer;
import ru.vasa.SecurityApp.models.Product;
import ru.vasa.SecurityApp.util.CategoryValidator;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    private CustomerDAO customerDAO;

    @Autowired
    public CustomerController(CategoryValidator categoryValidator, CustomerDAO customerDAO) {

        this.customerDAO = customerDAO;
    }





//catrgories/allcategories


    @GetMapping({"/allcustomers","/"})
    public String makeAdminPage(Model model) {

        model.addAttribute("customers", customerDAO.index());
//        System.out.println(categoryDAO.index().get(0).getName());
        return "allcustomers";
    }
    @GetMapping("/{id_customer}/edit")//   product/1/edit
    public String edit(Model model, @PathVariable("id_customer") int id) {
        System.out.println("3");
        model.addAttribute("customer",customerDAO.show(id));
        return "/editcustomer";
    }


    @GetMapping("/{id_customer}")
    public String update2(@ModelAttribute("customer") @Valid Customer customer, BindingResult bindingResult,
                          @PathVariable("id_customer") int id) {
        //System.out.println("1");
        if (bindingResult.hasErrors()) {
            System.out.println("Какие то ошибка");
            return "/hello";
        }
        //System.out.println("2");
        //System.out.println(id+" "+category.getDescription()+" "+category.getName()+"|");
        customerDAO.update(id,customer);
        System.out.println("4");
        return "redirect:/customers/allcustomers";
    }

    @GetMapping("/new/newcustomer")
    public String create(@ModelAttribute("customer") @Valid Customer customer,
                         BindingResult bindingResult) {
        System.out.println("ПЕРЕИНУЛ НА КРЕАТ");

        customerDAO.save(customer);
        return "redirect:/customers/allcustomers";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("customer",new Customer());
        //System.out.println("ПЕРЕКИНУЛ НА НЕВ");
        return "/newcustomer";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        System.out.println("DELETE "+ id);
        customerDAO.delete(id);
        return "redirect:/customers/allcustomers";
    }
}
