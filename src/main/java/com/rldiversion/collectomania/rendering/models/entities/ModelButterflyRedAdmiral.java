package com.rldiversion.collectomania.rendering.models.entities;


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import javax.annotation.Nonnull;

public class ModelButterflyRedAdmiral extends ModelBase {
	private final ModelRenderer root;

	public ModelButterflyRedAdmiral() {
		textureWidth = 32;
		textureHeight = 32;

		root = new ModelRenderer(this);
		root.setRotationPoint(0.0F, 21.0F, 0.0F);
		root.cubeList.add(new ModelBox(root, 20, 0, -1.0F, -1.0F, -4.0F, 2, 2, 4, 0.0F, true));
		root.cubeList.add(new ModelBox(root, 0, 4, -1.0F, -1.0F, -2.25F, 2, 2, 2, 0.35F, true));

		ModelRenderer cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(-1.0F, -1.0F, -4.0F);
		root.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.2081F, 0.5108F, 1.1535F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, -4, 0, -2.0F, 0.0F, -4.0F, 2, 0, 4, 0.0F, true));

		ModelRenderer cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, 0.75F, -5.0F);
		root.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.7854F, 0.0F, 0.0F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 4, -2, 0.0F, 0.0F, -0.5F, 0, 2, 2, 0.0F, true));

		ModelRenderer cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(0.0F, 1.0F, 0.0F);
		root.addChild(cube_r3);
		setRotationAngle(cube_r3, -0.0524F, 0.0F, 0.0F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 0, 0, -1.0F, -2.0F, 0.0F, 2, 2, 8, 0.0F, true));

		ModelRenderer cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(1.0F, -1.0F, -4.0F);
		root.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.2081F, -0.5108F, -1.1535F);
		cube_r4.cubeList.add(new ModelBox(cube_r4, -4, 0, 0.0F, 0.0F, -4.0F, 2, 0, 4, 0.0F, false));

		ModelRenderer wing_L = new ModelRenderer(this);
		wing_L.setRotationPoint(1.0F, -0.1F, 1.0F);
		root.addChild(wing_L);


		ModelRenderer wing_r1 = new ModelRenderer(this);
		wing_r1.setRotationPoint(0.0F, -0.25F, -2.5F);
		wing_L.addChild(wing_r1);
		setRotationAngle(wing_r1, -3.1416F, 0.0F, -3.1416F);
		wing_r1.cubeList.add(new ModelBox(wing_r1, -20, 12, -11.0F, 0.0F, -14.0F, 12, 0, 20, 0.0F, true));

		ModelRenderer wing_R = new ModelRenderer(this);
		wing_R.setRotationPoint(-1.0F, -0.1F, 1.0F);
		root.addChild(wing_R);
		setRotationAngle(wing_R, 0.0F, 0.0F, 1.1781F);


		ModelRenderer wing_r2 = new ModelRenderer(this);
		wing_r2.setRotationPoint(0.0F, -0.25F, -1.0F);
		wing_R.addChild(wing_r2);
		setRotationAngle(wing_r2, -3.1416F, 0.0F, 1.9635F);
		wing_r2.cubeList.add(new ModelBox(wing_r2, -20, 12, -0.75F, 0.0F, -12.5F, 12, 0, 20, 0.0F, false));

		ModelRenderer legs = new ModelRenderer(this);
		legs.setRotationPoint(0.0F, 0.5F, -2.0F);
		root.addChild(legs);


		ModelRenderer cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(-1.897F, 2.2294F, 1.0F);
		legs.addChild(cube_r5);
		setRotationAngle(cube_r5, -3.1416F, 0.7854F, 1.9635F);
		cube_r5.cubeList.add(new ModelBox(cube_r5, 14, 6, -3.0F, 0.0F, -3.0F, 6, 0, 6, 0.0F, false));

		ModelRenderer cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(1.897F, 2.2294F, 1.0F);
		legs.addChild(cube_r6);
		setRotationAngle(cube_r6, -3.1416F, -0.7854F, -1.9635F);
		cube_r6.cubeList.add(new ModelBox(cube_r6, 14, 6, -3.0F, 0.0F, -3.0F, 6, 0, 6, 0.0F, true));
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