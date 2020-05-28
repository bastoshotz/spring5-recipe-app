package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Category;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    IngredientCommandToIngredient ingredientConverter;
    NotesCommandToNotes notesConverter;
    CategoryCommandToCategory categoryConverter;

    public RecipeCommandToRecipe(IngredientCommandToIngredient ingredientConverter, NotesCommandToNotes notesConverter, CategoryCommandToCategory categoryConverter) {
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand recipeCommand) {
        if(Objects.isNull(recipeCommand)){
            return null;
        }
        final Recipe recipe = new Recipe();
        recipe.setId(recipeCommand.getId());
        recipe.setDescription(recipeCommand.getDescription());
        recipe.setPrepTime(recipeCommand.getPrepTime());
        recipe.setCookTime(recipeCommand.getCookTime());
        recipe.setServings(recipeCommand.getServings());
        recipe.setSource(recipeCommand.getSource());
        recipe.setUrl(recipeCommand.getUrl());
        recipe.setDirections(recipeCommand.getDirections());

        Set<Ingredient> ingredients = new HashSet<>();
        recipeCommand.getIngredientes().stream().forEach( ingredientCommand -> {
            ingredients.add(ingredientConverter.convert(ingredientCommand));
        });
        recipe.setIngredients(ingredients);

        recipe.setImage(recipeCommand.getImage());
        recipe.setDifficulty(recipeCommand.getDifficulty());

        recipe.setNotes(notesConverter.convert(recipeCommand.getNotes()));

        Set<Category> categories = new HashSet<>();
        recipeCommand.getCategories().stream().forEach(categoryCommand -> {
            categories.add(categoryConverter.convert(categoryCommand));
        });
        recipe.setCategories(categories);

        return recipe;
    }
}
