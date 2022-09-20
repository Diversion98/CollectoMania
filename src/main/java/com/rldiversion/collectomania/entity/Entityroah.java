// Made with Blockbench 4.4.1
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


public class custom_model extends ModelBase {
	private final ModelRenderer wing_L;
	private final ModelRenderer wing_R;
	private final ModelRenderer bone;
	private final ModelRenderer legs;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer bb_main;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;
	private final ModelRenderer cube_r7;
	private final ModelRenderer cube_r8;

	public custom_model() {
		textureWidth = 32;
		textureHeight = 32;

		wing_L = new ModelRenderer(this);
		wing_L.setRotationPoint(0.0F, 19.65F, 0.15F);
		wing_L.cubeList.add(new ModelBox(wing_L, 0, 11, -0.4F, 1.1F, -1.15F, 3, 2, 8, 0.1F, true));

		wing_R = new ModelRenderer(this);
		wing_R.setRotationPoint(0.0F, 19.65F, 0.15F);
		wing_R.cubeList.add(new ModelBox(wing_R, 0, 11, -2.6F, 1.1F, -1.15F, 3, 2, 8, 0.1F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(4.8003F, 24.5F, -1.0F);
		

		legs = new ModelRenderer(this);
		legs.setRotationPoint(0.0F, 24.5F, -1.0F);
		

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(3.8003F, -1.75F, 1.75F);
		legs.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, -0.7854F, 0.3927F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, -9, 23, -4.5F, 0.0F, -4.5F, 9, 0, 9, 0.0F, false));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(-3.8003F, -1.75F, 1.75F);
		legs.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.7854F, -0.3927F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, -9, 23, -4.5F, 0.0F, -4.5F, 9, 0, 9, 0.0F, true));

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -2.5F, -3.0F, -2.5F, 5, 2, 9, 0.0F, true));

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(0.0F, -1.5F, 7.5F);
		bb_main.addChild(cube_r3);
		setRotationAngle(cube_r3, -0.3927F, 0.0F, 0.0F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 24, 15, -1.5F, 0.5F, -1.5F, 3, 0, 2, 0.0F, true));

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(0.0F, -1.761F, -2.4172F);
		bb_main.addChild(cube_r4);
		setRotationAngle(cube_r4, -1.6668F, 0.0F, 0.0F);
		cube_r4.cubeList.add(new ModelBox(cube_r4, 0, 11, -1.0F, -1.25F, -1.45F, 2, 3, 2, 0.0F, true));

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(0.0F, -2.5F, -3.75F);
		bb_main.addChild(cube_r5);
		setRotationAngle(cube_r5, -1.4923F, 0.0F, 0.0F);
		cube_r5.cubeList.add(new ModelBox(cube_r5, 22, 11, -2.0F, -2.75F, -0.625F, 4, 3, 1, 0.0F, true));

		cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(1.0F, -1.0F, -5.25F);
		bb_main.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.0F, -0.3927F, 0.0F);
		cube_r6.cubeList.add(new ModelBox(cube_r6, 22, 4, -1.0F, -0.5F, -1.5F, 2, 1, 3, 0.0F, false));

		cube_r7 = new ModelRenderer(this);
		cube_r7.setRotationPoint(-1.0F, -1.0F, -5.25F);
		bb_main.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.0F, 0.3927F, 0.0F);
		cube_r7.cubeList.add(new ModelBox(cube_r7, 22, 4, -1.0F, -0.5F, -1.5F, 2, 1, 3, 0.0F, true));

		cube_r8 = new ModelRenderer(this);
		cube_r8.setRotationPoint(0.0F, -2.0F, -4.25F);
		bb_main.addChild(cube_r8);
		setRotationAngle(cube_r8, 0.3927F, 0.0F, 0.0F);
		cube_r8.cubeList.add(new ModelBox(cube_r8, 22, 0, -1.5F, -1.0F, -1.25F, 3, 2, 2, 0.0F, true));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		wing_L.render(f5);
		wing_R.render(f5);
		bone.render(f5);
		legs.render(f5);
		bb_main.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}