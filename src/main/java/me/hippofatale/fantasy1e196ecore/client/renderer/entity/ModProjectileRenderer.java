package me.hippofatale.fantasy1e196ecore.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import me.hippofatale.fantasy1e196ecore.entity.projectile.ModProjectile;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class ModProjectileRenderer extends EntityRenderer<ModProjectile> {

    public ModProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(ModProjectile entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.pushPose();
        poseStack.translate(0.0, 0.0, 0.0);

        // Simple line rendering for projectile
        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.lines());
        poseStack.scale(0.1F, 0.1F, 0.5F);
        this.renderLine(poseStack, vertexConsumer, packedLight);

        poseStack.popPose();
    }

    private void renderLine(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight) {
        float f = 0.0F;
        float f1 = 0.0F;
        float f2 = 0.0F;
        float f3 = 1.0F;
        vertexConsumer.addVertex(poseStack.last(), f, f1, f2).setColor(255, 255, 255, 255).setUv(0, 0).setOverlay(OverlayTexture.NO_OVERLAY).setLight(packedLight).setNormal(poseStack.last(), 0, 1, 0);
        vertexConsumer.addVertex(poseStack.last(), f, f1, f3).setColor(255, 255, 255, 255).setUv(0, 1).setOverlay(OverlayTexture.NO_OVERLAY).setLight(packedLight).setNormal(poseStack.last(), 0, 1, 0);
    }

    @Override
    public ResourceLocation getTextureLocation(ModProjectile entity) {
        return ResourceLocation.withDefaultNamespace("textures/entity/projectile/arrow.png");
    }
}
