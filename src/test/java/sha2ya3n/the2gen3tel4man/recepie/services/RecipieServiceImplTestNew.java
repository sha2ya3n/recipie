package sha2ya3n.the2gen3tel4man.recepie.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sha2ya3n.the2gen3tel4man.recepie.converters.RecipeCommandToRecipe;
import sha2ya3n.the2gen3tel4man.recepie.converters.RecipeToRecipeCommand;
import sha2ya3n.the2gen3tel4man.recepie.model.Recipie;
import sha2ya3n.the2gen3tel4man.recepie.repository.RecipieRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RecipieServiceImplTestNew {

    RecipieServiceImpl recipeService;

    @Mock
    RecipieRepository recipeRepository;

    RecipeCommandToRecipe recipeCommandToRecipe;
    RecipeToRecipeCommand recipeToRecipeCommand;

    final Long idValue = 1L;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipieServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    public void getRecipies() {
        Recipie recipie1 = new Recipie();
        HashSet<Recipie> recipieSet = new HashSet<>();
        recipieSet.add(recipie1);

        when(recipeService.getRecipies()).thenReturn(recipieSet);

        Set<Recipie> recipies = recipeService.getRecipies();

        assertEquals(recipies.size(), 1);

        verify(recipeRepository, times(1)).findAll();
    }


    @Test
    public void findById() throws Exception{
        Recipie recipie = new Recipie();
        recipie.setId(idValue);
        Optional<Recipie> recipieOptional = Optional.of(recipie);

        when(recipeRepository.findById(anyLong())).thenReturn(recipieOptional);

        Recipie returnRecipies = recipeService.findById(idValue);
        assertNotNull("Not null", returnRecipies);

        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();

    }

}
