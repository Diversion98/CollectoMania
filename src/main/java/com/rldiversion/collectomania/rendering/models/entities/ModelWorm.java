package com.rldiversion.collectomania.rendering.models.entities;// Made with Blockbench 4.4.1
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import javax.annotation.Nonnull;

public class ModelWorm extends ModelBase {
	private final ModelRenderer root;

	public ModelWorm() {
		textureWidth = 32;
		textureHeight = 32;

		root = new ModelRenderer(this);
		root.setRotationPoint(0.0F, 21.0F, 0.0F);
		root.cubeList.add(new ModelBox(root, 0, 0, -1.0F, 0.5F, -7.0F, 2, 2, 13, 0.0F, true));
		root.cubeList.add(new ModelBox(root, 0, 0, -1.0F, 0.5F, -5.0F, 2, 2, 3, 0.25F, true));
	}

	@Override
	public void render(@Nonnull Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		root.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}