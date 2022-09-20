// Made with Blockbench 4.4.1
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


public class custom_model extends ModelBase {
	private final ModelRenderer legs;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer wing_R;
	private final ModelRenderer wing_r1;
	private final ModelRenderer wing_L;
	private final ModelRenderer wing_r2;
	private final ModelRenderer bone;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer bb_main;
	private final ModelRenderer cube_r5;

	public custom_model() {
		textureWidth = 32;
		textureHeight = 32;

		legs = new ModelRenderer(this);
		legs.setRotationPoint(0.0F, 21.5F, 0.0F);
		

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(-1.0F, 0.25F, 0.75F);
		legs.addChild(cube_r1);
		setRotationAngle(cube_r1, -0.3655F, 0.147F, 0.3655F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 23, 0.0F, 0.0F, -2.0F, 0, 4, 5, 0.0F, true));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(1.0F, 0.25F, 0.75F);
		legs.addChild(cube_r2);
		setRotationAngle(cube_r2, -0.3655F, -0.147F, -0.3655F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 0, 23, 0.0F, 0.0F, -2.0F, 0, 4, 5, 0.0F, true));

		wing_R = new ModelRenderer(this);
		wing_R.setRotationPoint(0.0F, 19.25F, 0.0F);
		

		wing_r1 = new ModelRenderer(this);
		wing_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		wing_R.addChild(wing_r1);
		setRotationAngle(wing_r1, 0.1309F, -0.3927F, 0.0F);
		wing_r1.cubeList.add(new ModelBox(wing_r1, 14, 24, -5.0F, 0.0F, 0.0F, 5, 0, 8, 0.0F, true));

		wing_L = new ModelRenderer(this);
		wing_L.setRotationPoint(0.0F, 19.25F, 0.0F);
		

		wing_r2 = new ModelRenderer(this);
		wing_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
		wing_L.addChild(wing_r2);
		setRotationAngle(wing_r2, 0.1309F, 0.3927F, 0.0F);
		wing_r2.cubeList.add(new ModelBox(wing_r2, 14, 24, 0.0F, 0.0F, 0.0F, 5, 0, 8, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 20.25F, -1.0F);
		bone.cubeList.add(new ModelBox(bone, 0, 0, -2.0F, -1.0F, -2.0F, 4, 2, 2, 0.0F, true));

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(0.0F, 1.5287F, -1.4646F);
		bone.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.5236F, -1.5708F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 0, 11, -0.3F, -1.5F, -0.3F, 2, 3, 1, -0.1F, false));

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(0.0F, 1.3337F, -1.1209F);
		bone.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 1.309F, -1.5708F);
		cube_r4.cubeList.add(new ModelBox(cube_r4, 12, 9, 0.0F, -0.5F, -1.8F, 1, 1, 2, -0.1F, false));

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 4, -1.5F, -4.75F, -1.75F, 3, 3, 4, -0.2F, true));

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(0.0F, -2.75F, 1.0F);
		bb_main.addChild(cube_r5);
		setRotationAngle(cube_r5, -0.1309F, 0.0F, 0.0F);
		cube_r5.cubeList.add(new ModelBox(cube_r5, 14, 12, -2.0F, -2.0F, 0.0F, 4, 3, 5, 0.3F, true));
		cube_r5.cubeList.add(new ModelBox(cube_r5, 14, 0, -2.0F, -2.0F, 0.0F, 4, 3, 5, 0.1F, true));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		legs.render(f5);
		wing_R.render(f5);
		wing_L.render(f5);
		bone.render(f5);
		bb_main.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}