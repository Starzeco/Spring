package guru.springframework.spring5recipeapp.controllers;


import guru.springframework.spring5recipeapp.commands.IngredientCommand;
import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.commands.UnitOfMeasureCommand;
import guru.springframework.spring5recipeapp.services.IngredientService;
import guru.springframework.spring5recipeapp.services.RecipeService;
import guru.springframework.spring5recipeapp.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    @Autowired
    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService=recipeService;
        this.ingredientService=ingredientService;

        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredients")
    public String ListIngredients(@PathVariable String recipeId,Model model){
        log.debug("Getting list ingredients from recipe: " + recipeId);

        model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(recipeId)));

        return "recipe/ingredients/list";

    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredients/{id}/show")
    public String showRecipeIngredient(@PathVariable String recipeId,
                                       @PathVariable String id,Model model){
        log.debug("Getting ingredient: "+id +" from recipe :"+recipeId);

        model.addAttribute("ingredients",ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId),Long.valueOf(id)));

        return "recipe/ingredients/show";


    }
    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredients/{id}/update")
    public String updateRecipeIngredient(@PathVariable String recipeId,
                                         @PathVariable String id,Model model){

        model.addAttribute("ingredients",ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId),Long.valueOf(id)));

        model.addAttribute("uomList",unitOfMeasureService.listAllUoms());

        return "recipe/ingredients/ingredientform";

    }

    @PostMapping("/recipe/{recipeId}/ingredients")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command){
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        log.debug("saved receipe id:" + savedCommand.getRecipeId());
        log.debug("saved ingredient id:" + savedCommand.getId());

        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredients/" + savedCommand.getId() + "/show";
    }



    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredients/new")
    public String newIngredient(@PathVariable String recipeId, Model model){

        //make sure we have a good id value
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));
        //todo raise exception if null

        //need to return back parent id for hidden form property
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));
        model.addAttribute("ingredients", ingredientCommand);

        //init uom
        ingredientCommand.setUnitOfMeasure(new UnitOfMeasureCommand());

        model.addAttribute("uomList",  unitOfMeasureService.listAllUoms());

        return "recipe/ingredients/ingredientform";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredients/{id}/delete")
    public String deleteIngredient(@PathVariable String recipeId,
                                   @PathVariable String id){
            log.debug("deleting ingredient id: "+id);
            ingredientService.deleteById(Long.valueOf(recipeId),Long.valueOf(id));


        return "redirect:/recipe/"+recipeId+"/ingredients";
    }










}
