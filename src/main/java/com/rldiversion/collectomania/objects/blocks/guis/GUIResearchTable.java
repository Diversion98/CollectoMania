package com.rldiversion.collectomania.objects.blocks.guis;

import com.rldiversion.collectomania.objects.blocks.container.ContainerResearchTable;
import com.rldiversion.collectomania.objects.blocks.tileentities.TileEntityResearchTable;
import com.rldiversion.collectomania.util.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import java.util.Objects;

@SideOnly(Side.CLIENT)
public class GUIResearchTable extends GuiContainer
{
    private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/gui/gui_research_table.png");
    private final InventoryPlayer playerInventory;
    private final IInventory tileResearchTable;

    public GUIResearchTable(InventoryPlayer playerInv, IInventory researchInv)
    {
        super(new ContainerResearchTable(playerInv, researchInv));
        this.playerInventory = playerInv;
        this.tileResearchTable = researchInv;
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String tileName = Objects.requireNonNull(this.tileResearchTable.getDisplayName()).getUnformattedText();
        this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2) -5, 6, 4210752);
        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 7, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(TEXTURES);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

        int l = this.getResearchProgressScaled();
        this.drawTexturedModalRect(this.guiLeft + 80, this.guiTop + 35, 176, 0, l, 15);//original code from harry talks: (this.guiLeft + 63, this.guiTop + 36, 176, 14, l + 1, 16)
    }

    private int getResearchProgressScaled()
    {
        int i = this.tileResearchTable.getField(0);
        return i != 0 ? i * 24 / 100 : 0;
    }
}
