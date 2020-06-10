package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
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
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    IngredientToIngredientCommand ingredientConverter;
    NotesToNotesCommand notesConverter;
    CategoryToCategoryCommand categoryConverter;

    public RecipeToRecipeCommand(IngredientToIngredientCommand ingredientConverter, NotesToNotesCommand notesConverter, CategoryToCategoryCommand categoryConverter) {
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe recipe) {
        if(Objects.isNull(recipe)){
            return null;
        }
        final RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(recipe.getId());
        recipeCommand.setDescription(recipe.getDescription());
        recipeCommand.setPrepTime(recipe.getPrepTime());
        recipeCommand.setCookTime(recipe.getCookTime());
        recipeCommand.setServings(recipe.getServings());
        recipeCommand.setSource(recipe.getSource());
        recipeCommand.setUrl(recipe.getUrl());
        recipeCommand.setImage(recipe.getImage());
        recipeCommand.setDirections(recipe.getDirections());

        Set<IngredientCommand> ingredientCommands = new HashSet<>();
        recipe.getIngredients().stream().forEach(ingredient -> {
            ingredientCommands.add(ingredientConverter.convert(ingredient));
        });
        recipeCommand.setIngredients(ingredientCommands);

        recipeCommand.setImage(recipe.getImage());
        recipeCommand.setDifficulty(recipe.getDifficulty());

        recipeCommand.setNotes(notesConverter.convert(recipe.getNotes()));

        Set<CategoryCommand> categoryCommands = new HashSet<>();
        recipe.getCategories().stream().forEach(category -> {
            categoryCommands.add(categoryConverter.convert(category));
        });
        recipeCommand.setCategories(categoryCommands);

        return recipeCommand;
    }
}
