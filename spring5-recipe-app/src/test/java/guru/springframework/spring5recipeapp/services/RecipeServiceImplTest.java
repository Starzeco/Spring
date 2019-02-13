package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.converts.RecipeCommandToRecipe;
import guru.springframework.spring5recipeapp.converts.RecipeToRecipeCommand;
import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.exceptions.NotFoundException;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {
    RecipeServiceImpl recipeService;


    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService=new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    public void getRecipeByIdTest(){
        Recipe recipe=new Recipe();
        recipe.setId(1L);

        Optional<Recipe> recipeOptional=Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned=recipeService.findById(1L);

        assertNotNull("Null recipe returned",recipeReturned);
        verify(recipeRepository,times(1)).findById(anyLong());
        verify(recipeRepository,never()).findAll();



    }


    @Test
    public void getRecipes() {
        Recipe recipe=new Recipe();
        HashSet recipeData=new HashSet();
        recipeData.add(recipe);


        when(recipeService.getRecipes()).thenReturn(recipeData);

        Set<Recipe> recipes=recipeService.getRecipes();

        assertEquals(recipes.size(),1);

        verify(recipeRepository,times(1)).findAll();

    }

    @Test
    public void testDeleteById() throws Exception {

        Long idToDelete=Long.valueOf(2L);

        recipeService.deleteById(idToDelete);

        //no "when" since method has void type

        verify(recipeRepository,times(1)).deleteById(anyLong());

    }

    @Test(expected = NotFoundException.class)
    public void getRecipeByIdTestNotFound() throws Exception{
        Optional<Recipe> recipeOptional=Optional.empty();

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned=recipeService.findById(1L);
    }



}