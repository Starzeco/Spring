package guru.springframework.spring5recipeapp.converts;

import guru.springframework.spring5recipeapp.commands.CategoryCommand;
import guru.springframework.spring5recipeapp.domain.Category;
import org.springframework.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand implements Converter<Category,CategoryCommand> {

    @Synchronized
    @Nullable
    @Override
    public CategoryCommand convert(Category category) {
        if(category==null){
            return null;
        }

        final CategoryCommand categoryCommand=new CategoryCommand();

        categoryCommand.setId(category.getId());
        categoryCommand.setDescription(category.getDescription());

        return  categoryCommand;
    }
}
