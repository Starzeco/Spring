package guru.springframework.spring5recipeapp.converts;

import org.springframework.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import guru.springframework.spring5recipeapp.commands.CategoryCommand;
import guru.springframework.spring5recipeapp.domain.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand,Category> {



    @Override
    @Synchronized
    @Nullable
    public Category convert(CategoryCommand categoryCommand) {
        if(categoryCommand == null){
            return null;
        }

        final Category category=new Category();
        category.setId(categoryCommand.getId());
        category.setDescription(categoryCommand.getDescription());

        return category;





    }
}
