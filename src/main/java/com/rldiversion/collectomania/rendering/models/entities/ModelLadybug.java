package com.rldiversion.collectomania.rendering.models.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLadybug extends ModelBase {
	private final ModelRenderer elytra_L;
	private final ModelRenderer elytra_R;
	private final ModelRenderer bone;
	private final ModelRenderer legs;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer wing_R;
	private final ModelRenderer wing_L;
	private final ModelRenderer bb_main;

	public ModelLadybug() {
		textureWidth = 32;
		textureHeight = 32;

		elytra_L = new ModelRenderer(this);
		elytra_L.setRotationPoint(1.9F, 20.0F, 0.0F);
		elytra_L.cubeList.add(new ModelBox(elytra_L, 0, 11, -1.8F, -2.0F, -1.0F, 4, 4, 7, 0.1F, true));

		elytra_R = new ModelRenderer(this);
		elytra_R.setRotationPoint(-1.9F, 20.0F, -1.0F);
		elytra_R.cubeList.add(new ModelBox(elytra_R, 0, 11, -2.2F, -2.0F, 0.0F, 4, 4, 7, 0.1F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(4.8003F, 24.5F, -1.0F);
		

		legs = new ModelRenderer(this);
		legs.setRotationPoint(0.0F, 24.5F, -1.0F);
		

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(4.8003F, -2.0F, 2.0F);
		legs.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, -0.7854F, 0.3927F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, -9, 23, -4.5F, 0.0F, -4.5F, 9, 0, 9, 0.0F, false));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(-4.8003F, -2.0F, 2.0F);
		legs.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.7854F, -0.3927F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, -9, 23, -4.5F, 0.0F, -4.5F, 9, 0, 9, 0.0F, true));

		wing_R = new ModelRenderer(this);
		wing_R.setRotationPoint(0.0F, 20.9F, 0.0F);
		wing_R.cubeList.add(new ModelBox(wing_R, 18, 26, -3.0F, -1.0F, 0.0F, 4, 0, 6, 0.0F, true));

		wing_L = new ModelRenderer(this);
		wing_L.setRotationPoint(0.0F, 20.9F, 0.0F);
		wing_L.cubeList.add(new ModelBox(wing_L, 18, 26, -1.0F, -1.0F, 0.0F, 4, 0, 6, 0.0F, false));

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 20, 0, -2.0F, -4.0F, -4.0F, 4, 2, 2, 0.0F, true));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -3.0F, -5.0F, -2.0F, 6, 3, 8, 0.0F, true));
		bb_main.cubeList.add(new ModelBox(bb_main, 22, 4, -2.5F, -3.5F, -6.0F, 2, 1, 3, 0.0F, true));
		bb_main.cubeList.add(new ModelBox(bb_main, 22, 4, 0.5F, -3.5F, -6.0F, 2, 1, 3, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		elytra_L.render(f5);
		elytra_R.render(f5);
		bone.render(f5);
		legs.render(f5);
		wing_R.render(f5);
		wing_L.render(f5);
		bb_main.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}