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
import ru.vasa.SecurityApp.dao.SuppliersDAO;
import ru.vasa.SecurityApp.models.*;
import ru.vasa.SecurityApp.util.CategoryValidator;

@Controller
@RequestMapping("/suppliers")
public class SuppliersController {
    private SuppliersDAO suppliersDAO;

    @Autowired
    public SuppliersController(SuppliersDAO suppliersDAO) {

        this.suppliersDAO = suppliersDAO;
    }



//catrgories/allcategories


    @GetMapping({"/allsuppliers"})
    public String makeAdminPage(Model model) {

        model.addAttribute("suppliers", suppliersDAO.index());
//        System.out.println(categoryDAO.index().get(0).getName());
        return "allsuppliers";
    }
    @GetMapping("/{id_supplier}/edit")//   product/1/edit
    public String edit(Model model, @PathVariable("id_supplier") int id) {

        model.addAttribute("supplier",suppliersDAO.show(id));
        System.out.println("3");
        return "/editsupplier";
    }


    @GetMapping("/{id_supplier}")
    public String update2(@ModelAttribute("supplier") @Valid Supplier supplier, BindingResult bindingResult,
                          @PathVariable("id_supplier") int id) {
        System.out.println("1");
        if (bindingResult.hasErrors()) {
            //System.out.println("Какие то ошибка");
            return "/hello";
        }
        System.out.println("2");

        suppliersDAO.update(id,supplier);
        System.out.println("4");
        return "redirect:/suppliers/allsuppliers";
    }

    @GetMapping("/new/newsupplier")
    public String create(@ModelAttribute("supplier") @Valid Supplier supplier,
                         BindingResult bindingResult) {
        System.out.println("ПЕРЕИНУЛ НА КРЕАТ");
        suppliersDAO.save(supplier);
        return "redirect:/suppliers/allsuppliers";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("supplier",new Supplier());
        //System.out.println("ПЕРЕКИНУЛ НА НЕВ");
        return "/newsupplier";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        System.out.println("DELETE "+ id);
        suppliersDAO.delete(id);
        return "redirect:/suppliers/allsuppliers";
    }
}
