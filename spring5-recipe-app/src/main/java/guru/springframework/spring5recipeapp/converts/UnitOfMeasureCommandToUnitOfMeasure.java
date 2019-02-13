package guru.springframework.spring5recipeapp.converts;

import guru.springframework.spring5recipeapp.commands.UnitOfMeasureCommand;
import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;
import org.springframework.lang.Nullable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand,UnitOfMeasure> {


    @Nullable
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand unitOfMeasureCommand) {
        if(unitOfMeasureCommand==null){
            return null;

        }

        UnitOfMeasure unitOfMeasure=new UnitOfMeasure();
        unitOfMeasure.setDescription(unitOfMeasureCommand.getDescription());
        unitOfMeasure.setId(unitOfMeasureCommand.getId());
        return unitOfMeasure;
    }
}
