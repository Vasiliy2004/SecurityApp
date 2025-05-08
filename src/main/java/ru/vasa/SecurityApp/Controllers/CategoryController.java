package ru.vasa.SecurityApp.Controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vasa.SecurityApp.dao.CategoryDAO;
import ru.vasa.SecurityApp.models.Category;
import ru.vasa.SecurityApp.models.Product;
import ru.vasa.SecurityApp.util.CategoryValidator;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    private CategoryDAO categoryDAO;
    private CategoryValidator categoryValidator;

    @Autowired
    public CategoryController(CategoryValidator categoryValidator, CategoryDAO categoryDAO) {
        this.categoryValidator = categoryValidator;
        this.categoryDAO = categoryDAO;
    }





//catrgories/allcategories


    @GetMapping({"/allcategories","/"})
    public String makeAdminPage(Model model) {
        model.addAttribute("categories", categoryDAO.index());
//        System.out.println(categoryDAO.index().get(0).getName());
        return "allcategories";
    }
    @GetMapping("/{id_category}/edit")//   product/1/edit
    public String edit(Model model, @PathVariable("id_category") int id) {
        System.out.println("3");
        model.addAttribute("category",categoryDAO.show(id));
        return "/editcategory";
    }


    @GetMapping("/{id_category}")
    public String update2(@ModelAttribute("category") @Valid Category category, BindingResult bindingResult,
                          @PathVariable("id_category") int id) {
        System.out.println("1");
        if (bindingResult.hasErrors()) {
            System.out.println("Какие то ошибка");
            return "/hello";
        }
        System.out.println("2");
        System.out.println(id+" "+category.getDescription()+" "+category.getName()+"|");
        categoryDAO.update(id,category);
        System.out.println("4");
        return "redirect:/categories/allcategories";
    }

    @GetMapping("/new/newcategory")
    public String create(@ModelAttribute("catergory") @Valid Category category,
                         BindingResult bindingResult) {
        System.out.println("ПЕРЕИНУЛ НА КРЕАТ");
        categoryValidator.validate(category, bindingResult);
        if (bindingResult.hasErrors()) {
            System.out.println("Какие то ошибка"+bindingResult.getAllErrors());
            System.out.println();
            //return "/hello";
            return "redirect:/categories/new"; ////+-+-+-+
            //return "/newcategory";

            //http://localhost:8080/categories/new?
            //http://localhost:8080/categories/new/newcategory?name=d&description=
        }
        categoryDAO.save(category);
        return "redirect:/categories/allcategories";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("category",new Category());
        //System.out.println("ПЕРЕКИНУЛ НА НЕВ");
        return "/newcategory";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        System.out.println("DELETE "+ id);
        categoryDAO.delete(id);
        return "redirect:/categories/allcategories";
    }
}
