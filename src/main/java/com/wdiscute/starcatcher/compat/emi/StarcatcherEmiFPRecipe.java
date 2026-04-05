package com.wdiscute.starcatcher.compat.emi;

import com.wdiscute.sellingbin.emi.HoverTextWidget;
import com.wdiscute.starcatcher.SCColors;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.registry.SCItems;
import com.wdiscute.starcatcher.registry.FishProperties;
import com.wdiscute.starcatcher.registry.fishrestrictions.AbstractFishRestriction;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class StarcatcherEmiFPRecipe implements EmiRecipe
{

    private final ResourceLocation id;
    private final List<EmiStack> output;
    private final EmiIngredient rod = EmiIngredient.of(Ingredient.of(SCItems.ROD));
    private final ItemStack is;
    private final FishProperties fp;

    List<Component> restrictions = new ArrayList<>();

    public StarcatcherEmiFPRecipe(ResourceLocation id, FishProperties fp)
    {
        this.output = List.of(EmiStack.of(fp.catchInfo().fish().value()));
        this.id = id;
        this.is = new ItemStack(fp.catchInfo().fish());
        this.fp = fp;

        //Aurora
        restrictions.add(fp.getDisplayName());

        //❌ Dimension
        //✅ Biome
        fp.restrictions().stream().filter(AbstractFishRestriction::isEnabled).forEach(o ->
                restrictions.addAll(
                        o.getIndexHover(
                                Minecraft.getInstance().level,
                                fp,
                                Minecraft.getInstance().player,
                                AbstractFishRestriction.Context.EMI)
                )
        );
    }

    @Override
    public EmiRecipeCategory getCategory()
    {
        return StarcatcherEmiPlugin.STARCATCHER_CATEGORY;
    }

    @Override
    public @Nullable ResourceLocation getId()
    {
        return Starcatcher.rl("/" + id.getPath());
    }

    @Override
    public List<EmiIngredient> getInputs()
    {
        return List.of();
    }

    @Override
    public List<EmiIngredient> getCatalysts()
    {
        return List.of(rod);
    }

    @Override
    public List<EmiStack> getOutputs()
    {
        return output;
    }

    @Override
    public int getDisplayWidth()
    {
        return 107;
    }

    @Override
    public int getDisplayHeight()
    {
        return 22;
    }


    @Override
    public void addWidgets(WidgetHolder widgets)
    {
        widgets.addSlot(rod, 5, 2);

        widgets.addTexture(EmiTexture.EMPTY_ARROW, 25, 2).tooltipText(restrictions);

        widgets.addSlot(EmiIngredient.of(Ingredient.of(is)), 53, 2).recipeContext(this);

        widgets.add(new StarcatcherShowInGuideEmiWidget(76, 1, fp, this));

        if (fp.catchInfo().alwaysSpawnEntity())
        {
            List<Component> components = List.of(Component.translatable("emi.starcatcher.entity_entry", fp.getDisplayName()));

            widgets.addText(Component.literal("[!]").withStyle(Style.EMPTY.withColor(SCColors.GUIDE_RED)), 97, 13, 0x000000, false);
            widgets.addTooltip(components.stream().map(Component::getVisualOrderText).map(ClientTooltipComponent::create).toList(),
                    97, 13, 9,9);
        }

    }
}
