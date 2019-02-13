package guru.springframework.spring5recipeapp.converts;

import guru.springframework.spring5recipeapp.commands.IngredientCommand;
import guru.springframework.spring5recipeapp.domain.Ingredient;
import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;
import org.springframework.lang.Nullable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient,IngredientCommand> {

    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand){
        this.unitOfMeasureToUnitOfMeasureCommand=unitOfMeasureToUnitOfMeasureCommand;
    }

    @Nullable
    @Override
    public IngredientCommand convert(Ingredient ingredient) {
        if(ingredient==null){
            return null;
        }


        IngredientCommand ingredientCommand=new IngredientCommand();

        ingredientCommand.setAmount(ingredient.getAmount());
        ingredientCommand.setDescription(ingredient.getDescription());
        if(ingredient.getRecipe()!=null){
            ingredientCommand.setRecipeId(ingredient.getRecipe().getId());
        }
        ingredientCommand.setId(ingredient.getId());
        ingredientCommand.setUnitOfMeasure(unitOfMeasureToUnitOfMeasureCommand.convert(ingredient.getUnitOfMeasure()));

        return ingredientCommand;

    }
}
