package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.converts.RecipeCommandToRecipe;
import guru.springframework.spring5recipeapp.converts.RecipeToRecipeCommand;
import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.exceptions.NotFoundException;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeToRecipeCommand recipeToRecipeCommand;
    private final RecipeCommandToRecipe recipeCommandToRecipe;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeCommandToRecipe=recipeCommandToRecipe;
        this.recipeToRecipeCommand=recipeToRecipeCommand;

        this.recipeRepository=recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipeSet=new HashSet<>();

        log.debug("I am in the service" );

        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add );


        return recipeSet;
    }

    @Override
    public Recipe findById(Long l){
        Optional<Recipe> recipeOptional=recipeRepository.findById(l);

        if (!recipeOptional.isPresent()) {

            throw new NotFoundException("Recipe Not Found <3 Złe id :(  -->" +l.toString() );

        }

        return recipeOptional.get();
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detachedRecipe=recipeCommandToRecipe.convert(command);

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved RecipeId: "+ savedRecipe.getId());

        return recipeToRecipeCommand.convert(savedRecipe);


    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long l) {

        return recipeToRecipeCommand.convert(findById(l));


    }

    @Override
    public void deleteById(Long l) {
        recipeRepository.deleteById(l);
    }


}
