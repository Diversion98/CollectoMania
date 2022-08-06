// Made with Blockbench 4.3.1
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


public class custom_model<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "custom_model"), "main");
	private final ModelPart elytra_L;
	private final ModelPart elytra_R;
	private final ModelPart bb_main;

	public custom_model(ModelPart root) {
		this.elytra_L = root.getChild("elytra_L");
		this.elytra_R = root.getChild("elytra_R");
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition elytra_L = partdefinition.addOrReplaceChild("elytra_L", CubeListBuilder.create().texOffs(0, 11).addBox(0.0F, -3.0F, -6.0F, 3.0F, 3.0F, 7.0F, new CubeDeformation(0.1F)), PartPose.offset(-3.1F, 24.0F, 0.0F));

		PartDefinition elytra_R = partdefinition.addOrReplaceChild("elytra_R", CubeListBuilder.create().texOffs(0, 11).mirror().addBox(0.0F, -3.0F, -6.0F, 3.0F, 3.0F, 7.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offset(0.1F, 24.0F, 0.0F));

		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(20, 0).addBox(-2.0F, -2.0F, 2.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.0F, -3.0F, -6.0F, 6.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(22, 4).addBox(0.5F, -1.5F, 3.25F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(22, 4).mirror().addBox(-2.5F, -1.5F, 3.25F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r1 = bb_main.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(-9, 23).mirror().addBox(-4.5F, 0.0F, -4.5F, 9.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.8003F, 0.5F, -1.0F, 0.0F, 0.7854F, -0.3927F));

		PartDefinition cube_r2 = bb_main.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(-9, 23).addBox(-4.5F, 0.0F, -4.5F, 9.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.8003F, 0.5F, -1.0F, 0.0F, -0.7854F, 0.3927F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		elytra_L.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		elytra_R.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}