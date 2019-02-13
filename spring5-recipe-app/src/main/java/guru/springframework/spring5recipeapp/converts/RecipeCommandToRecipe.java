package guru.springframework.spring5recipeapp.converts;

import guru.springframework.spring5recipeapp.commands.CategoryCommand;
import guru.springframework.spring5recipeapp.commands.IngredientCommand;
import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.domain.Category;
import guru.springframework.spring5recipeapp.domain.Recipe;
import org.springframework.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand,Recipe> {

    private final NotesCommandToNotes notesCommandToNotes;
    private final CategoryCommandToCategory categoryCommandToCategory;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;


    public RecipeCommandToRecipe(NotesCommandToNotes notesCommandToNotes,CategoryCommandToCategory categoryCommandToCategory, IngredientCommandToIngredient ingredientCommandToIngredient){
        this.categoryCommandToCategory=categoryCommandToCategory;
        this.ingredientCommandToIngredient=ingredientCommandToIngredient;
        this.notesCommandToNotes=notesCommandToNotes;
    }


    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand recipeCommand) {
        if(recipeCommand==null){
            return null;
        }

        final Recipe recipe=new Recipe();

        recipe.setId(recipeCommand.getId());
        recipe.setCookTime(recipeCommand.getCookTime());
        recipe.setPrepTime(recipeCommand.getPrepTime());
        recipe.setDescription(recipeCommand.getDescription());
        recipe.setDifficulty(recipeCommand.getDifficulty());
        recipe.setDirections(recipeCommand.getDirections());
        recipe.setServings(recipeCommand.getServings());
        recipe.setSource(recipeCommand.getSource());
        recipe.setImage(recipeCommand.getImage());
        recipe.setUrl(recipeCommand.getUrl());


        recipe.setNotes(notesCommandToNotes.convert(recipeCommand.getNotes()));

        if(recipeCommand.getCategories()!=null && recipeCommand.getCategories().size()>0){
            for(CategoryCommand categoryCommand:recipeCommand.getCategories()){
                recipe.getCategories().add(categoryCommandToCategory.convert(categoryCommand));

            }
        }
        if(recipeCommand.getIngredients()!=null && recipeCommand.getIngredients().size()>0){
            for(IngredientCommand ingredientCommand:recipeCommand.getIngredients()){
                recipe.getIngredients().add(ingredientCommandToIngredient.convert(ingredientCommand));
            }
        }
        return recipe;

    }
}
