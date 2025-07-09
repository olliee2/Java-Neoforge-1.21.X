package net.olliee2.kricketotmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.olliee2.kricketotmod.KricketotMod;
import net.olliee2.kricketotmod.block.ModBlocks;
import net.olliee2.kricketotmod.item.ModItems;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static net.minecraft.data.recipes.ShapedRecipeBuilder.shaped;
import static net.minecraft.data.recipes.ShapelessRecipeBuilder.shapeless;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    // Constructor
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    // Helper method
    protected static void oreSmelting(@NotNull RecipeOutput recipeOutput, List<ItemLike> pIngredients, @NotNull RecipeCategory pCategory, @NotNull ItemLike pResult,
                                      float pExperience, int pCookingTIme, @NotNull String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    // Helper method
    protected static void oreBlasting(@NotNull RecipeOutput recipeOutput, List<ItemLike> pIngredients, @NotNull RecipeCategory pCategory, @NotNull ItemLike pResult,
                                      float pExperience, int pCookingTime, @NotNull String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    // Helper method
    protected static <T extends AbstractCookingRecipe> void oreCooking(@NotNull RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.@NotNull Factory<T> factory,
                                                                       List<ItemLike> pIngredients, @NotNull RecipeCategory pCategory, @NotNull ItemLike pResult, float pExperience, int pCookingTime, @NotNull String pGroup, String pRecipeName) {
        for (ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, KricketotMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }

    // Called externally to build all the recipes required
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {
        List<ItemLike> KRICKETOTIUM_SMELTABLES = List.of(ModItems.RAW_KRICKETOTIUM,
                ModBlocks.KRICKETOTIUM_ORE, ModBlocks.DEEPSLATE_KRICKETOTIUM_ORE);

        shaped(RecipeCategory.MISC, ModBlocks.KRICKETOTIUM_BLOCK.get())
                .pattern("KKK")
                .pattern("KKK")
                .pattern("KKK")
                .define('K', ModItems.KRICKETOTIUM.get())
                .unlockedBy("has_kricketotium", has(ModItems.KRICKETOTIUM.get())).save(recipeOutput);

        shaped(RecipeCategory.MISC, ModItems.BLAZING_KRICKETOT.get())
                .pattern("KKK")
                .pattern("KBK")
                .pattern("KKK")
                .define('K', ModItems.KRICKETOTIUM.get())
                .define('B', Items.BLAZE_POWDER)
                .unlockedBy("has_kricketotium", has(ModItems.KRICKETOTIUM.get())).save(recipeOutput);

        shaped(RecipeCategory.MISC, ModBlocks.KRICKETOTIUM_DRUM.get())
                .pattern("K")
                .pattern("L")
                .define('K', ModItems.KRICKETOTIUM.get())
                .define('L', Items.JUNGLE_LOG)
                .unlockedBy("has_kricketotium", has(ModItems.KRICKETOTIUM.get())).save(recipeOutput);

        shapeless(RecipeCategory.MISC, ModItems.KRICKETOTIUM.get(), 9)
                .requires(ModBlocks.KRICKETOTIUM_BLOCK)
                .unlockedBy("has_kricketotium_block", has(ModBlocks.KRICKETOTIUM_BLOCK)).save(recipeOutput);

        shapeless(RecipeCategory.MISC, ModBlocks.KRICKETOTIUM_ORE.get())
                .requires(Items.END_STONE, 1)
                .requires(Items.JUNGLE_LOG, 1)
                .unlockedBy("has_end_stone", has(Items.END_STONE)).save(recipeOutput);

        oreSmelting(recipeOutput, KRICKETOTIUM_SMELTABLES, RecipeCategory.MISC, ModItems.KRICKETOTIUM.get(), 0.25f, 200, "kricketotium");
        oreBlasting(recipeOutput, KRICKETOTIUM_SMELTABLES, RecipeCategory.MISC, ModItems.KRICKETOTIUM.get(), 0.25f, 100, "kricketotium");
    }
}