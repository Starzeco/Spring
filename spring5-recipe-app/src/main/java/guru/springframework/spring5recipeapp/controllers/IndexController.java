package guru.springframework.spring5recipeapp.controllers;


import guru.springframework.spring5recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Slf4j
@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    @RequestMapping({"","/","/index"})
    public String getIndexPage(Model model){
        System.out.println("Some message to say.... 12345555555ssss 55555");

        log.debug("I am in controller :)");

        model.addAttribute("recipes", recipeService.getRecipes());

        return "index";
    }
}
