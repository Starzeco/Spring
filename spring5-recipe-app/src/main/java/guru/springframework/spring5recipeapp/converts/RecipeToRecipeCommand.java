package guru.springframework.spring5recipeapp.converts;

import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.domain.Category;
import guru.springframework.spring5recipeapp.domain.Recipe;
import org.springframework.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe,RecipeCommand> {

    private final NotesToNotesCommand notesToNotesCommand;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final CategoryToCategoryCommand categoryToCategoryCommand;

    public RecipeToRecipeCommand(NotesToNotesCommand notesToNotesCommand,IngredientToIngredientCommand ingredientToIngredientCommand, CategoryToCategoryCommand categoryToCategoryCommand){
        this.notesToNotesCommand=notesToNotesCommand;
        this.ingredientToIngredientCommand=ingredientToIngredientCommand;
        this.categoryToCategoryCommand=categoryToCategoryCommand;
    }


    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe recipe) {
        if(recipe==null){
            return null;
        }

        RecipeCommand recipeCommand=new RecipeCommand();

        recipeCommand.setId(recipe.getId());
        recipeCommand.setCookTime(recipe.getCookTime());
        recipeCommand.setPrepTime(recipe.getPrepTime());
        recipeCommand.setDescription(recipe.getDescription());
        recipeCommand.setDifficulty(recipe.getDifficulty());
        recipeCommand.setDirections(recipe.getDirections());
        recipeCommand.setServings(recipe.getServings());
        recipeCommand.setSource(recipe.getSource());
        recipeCommand.setImage(recipe.getImage());
        recipeCommand.setUrl(recipe.getUrl());
        recipeCommand.setNotes(notesToNotesCommand.convert(recipe.getNotes()));

        if (recipe.getCategories() != null && recipe.getCategories().size() > 0){
            recipe.getCategories()
                    .forEach((Category category) -> recipeCommand.getCategories().add(categoryToCategoryCommand.convert(category)));
        }

        if (recipe.getIngredients() != null && recipe.getIngredients().size() > 0){
            recipe.getIngredients()
                    .forEach(ingredient -> recipeCommand.getIngredients().add(ingredientToIngredientCommand.convert(ingredient)));
        }
        return recipeCommand;


    }
}
