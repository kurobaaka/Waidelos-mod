package net.infugogr.barracuda.screenhandler;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.infugogr.barracuda.Barracuda;
import net.infugogr.barracuda.block.entity.SMESblockEntity;
import net.infugogr.barracuda.util.ScreenUtils;
import net.infugogr.barracuda.util.energy.EnergyCounter;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ToggleButtonWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class SMESScreen extends HandledScreen<SMESScreenHandler> {
    private static final Identifier TEXTURE = Barracuda.id("textures/gui/smes_gui.png");

    public SMESScreen(SMESScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        this.titleX = (this.backgroundWidth - this.textRenderer.getWidth(this.title)) / 2;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        ScreenUtils.drawTexture(context, TEXTURE, this.x, this.y, 0, 0, this.backgroundWidth, this.backgroundHeight);

        int energy = MathHelper.ceil(this.handler.getEnergyPercent() * 70);
        context.fill(this.x + 152, this.y + 8 + 70 - energy, this.x + 152 + 16, this.y + 8 + 70, 0xFFFF4040);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);

        int energy = MathHelper.ceil(this.handler.getEnergyPercent() * 66);
        if (isPointWithinBounds(152, 8, 16, 70, mouseX, mouseY)) {
            context.drawTooltip(this.textRenderer, Text.literal(EnergyCounter.CounterAh(this.handler.getEnergy()) + " / " + EnergyCounter.CounterAh(this.handler.getMaxEnergy())), mouseX, mouseY);
        }
    }
}
