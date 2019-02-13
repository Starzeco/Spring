package guru.springframework.spring5recipeapp.converts;

import guru.springframework.spring5recipeapp.commands.IngredientCommand;
import guru.springframework.spring5recipeapp.domain.Ingredient;
import org.springframework.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand,Ingredient> {

    UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure){
        this.unitOfMeasureCommandToUnitOfMeasure=unitOfMeasureCommandToUnitOfMeasure;
    }


    @Nullable
    @Override
    public Ingredient convert(IngredientCommand ingredientCommand) {
            if(ingredientCommand==null){
                return null;
            }
            final Ingredient ingredient=new Ingredient();

            ingredient.setAmount(ingredientCommand.getAmount());
            ingredient.setDescription(ingredientCommand.getDescription());
            ingredient.setId(ingredientCommand.getId());
            ingredient.setUnitOfMeasure(unitOfMeasureCommandToUnitOfMeasure.convert(ingredientCommand.getUnitOfMeasure()));

            return ingredient;




    }
}
