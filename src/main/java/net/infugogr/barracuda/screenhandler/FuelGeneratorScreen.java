package net.infugogr.barracuda.screenhandler;

import net.infugogr.barracuda.Barracuda;
import net.infugogr.barracuda.util.ScreenUtils;
import net.infugogr.barracuda.util.energy.EnergyCounter;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;


public class FuelGeneratorScreen extends HandledScreen<FuelGeneratorScreenHandler> {
    private static final Identifier TEXTURE = Barracuda.id("textures/gui/fuel_generator_gui.png");

    public FuelGeneratorScreen(FuelGeneratorScreenHandler handler, PlayerInventory inventory, Text title) {
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

        long energy = this.handler.getEnergy();
        long maxEnergy = this.handler.getMaxEnergy();
        int energySize = MathHelper.ceil((float) energy / maxEnergy * 70);
        context.fill(this.x + 152, this.y + 8 + 70 - energySize, this.x + 152 + 16, this.y + 8 + 70, 0xFFFF4040);
        Text text =  Text.literal("Output: "+ EnergyCounter.CounterWh(this.handler.getEnergyPerTick()));
        context.drawText(this.textRenderer, text, this.x + 31,this.y + 32,0xFF3e3e3e, false);

        int burnTime = this.handler.getBurnTime();
        int fuelTime = this.handler.getFuelTime();
        float burnTimePercentage = (float) burnTime / fuelTime;
        int burnTimeSize = Math.round(burnTimePercentage * 14);
        ScreenUtils.drawTexture(context, TEXTURE, this.x + 9, this.y + 12 + 14 - burnTimeSize, 176, 14 - burnTimeSize, 14, burnTimeSize);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);

        if (isPointWithinBounds(152, 8, 16, 70, mouseX, mouseY)) {
            long energy = this.handler.getEnergy();
            long maxEnergy = this.handler.getMaxEnergy();
            context.drawTooltip(this.textRenderer, Text.literal(EnergyCounter.CounterAh(energy) + " / " + EnergyCounter.CounterAh(maxEnergy)), mouseX, mouseY);
        }
    }
}