package net.infugogr.barracuda.screenhandler;

import com.mojang.blaze3d.systems.RenderSystem;
import net.infugogr.barracuda.Barracuda;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class FishingNetScreen extends HandledScreen<FishingNetScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(Barracuda.MOD_ID, "textures/gui/fishingnet.png");

    public FishingNetScreen(FishingNetScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        titleY = 1000;
        playerInventoryTitleY = 1000;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        renderProgressArrow(context, x, y);
    }

    private void renderProgressArrow(DrawContext context, int x, int y) {
        if(handler.isCrafting()) {
            context.drawTexture(TEXTURE, x + 85, y + 30, 176, 0, 8, handler.getScaledProgress());
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
// package net.infugogr.barracuda.screenhandler;

// import com.mojang.blaze3d.systems.RenderSystem;

// import net.infugogr.barracuda.Barracuda;
// import net.minecraft.client.gui.DrawContext;
// import net.minecraft.client.gui.screen.ingame.HandledScreen;
// import net.minecraft.client.render.GameRenderer;
// import net.minecraft.entity.player.PlayerInventory;
// import net.minecraft.text.Text;
// import net.minecraft.util.Identifier;

// public class FishingNetScreen extends HandledScreen<FishingNetScreenHandler> {
//     private static final Identifier TEXTURE = Barracuda.id("textures/gui/fishingnet.png");

//     public FishingNetScreen(FishingNetScreenHandler handler, PlayerInventory inventory, Text title) {
//         super(handler, inventory, title);
//     }

//     @Override
//     protected void init() {
//         super.init();
//         // Центрируем заголовок
//         titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
//     }

//     @Override
//     public void render(DrawContext context, int mouseX, int mouseY, float delta) {
//         renderBackground(context, mouseX, mouseY, delta);
//         super.render(context, mouseX, mouseY, delta);
//         drawMouseoverTooltip(context, mouseX, mouseY);
//     }

//     @Override
//     protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
//         RenderSystem.setShader(GameRenderer::getPositionTexProgram);
//         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
//         RenderSystem.setShaderTexture(0, TEXTURE);

//         int x = (width - backgroundWidth) / 2;
//         int y = (height - backgroundHeight) / 2;
//         context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
//     }
// }
