package com.github.mmm1245;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.annotations.JSConstructor;
import org.mozilla.javascript.annotations.JSFunction;
import org.mozilla.javascript.annotations.JSGetter;

public class ScriptPlayer extends ScriptableObject {
    @JSConstructor
    public ScriptPlayer(){}
    @Override
    public String getClassName() {
        return "Player";
    }
    @JSFunction
    public void log(String text){
        MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(Text.literal("[SCRIPT]" + text));
    }
    @JSFunction
    public void setSlot(int slot){
        if(PlayerInventory.isValidHotbarIndex(slot))
            MinecraftClient.getInstance().player.getInventory().setSelectedSlot(slot);
    }
    @JSFunction
    public void clickBlock(double x, double y, double z){
        BlockHitResult blockHitResult = new BlockHitResult(new Vec3d(x, y, z), Direction.UP, new BlockPos((int) x, (int) y, (int) z), false, false);
        MinecraftClient.getInstance().interactionManager.interactBlock(MinecraftClient.getInstance().player, Hand.MAIN_HAND, blockHitResult);
    }
    @JSFunction
    public Scriptable getItem(int slot){
        PlayerInventory inventory = MinecraftClient.getInstance().player.getInventory();
        System.out.println(inventory.getStack(slot).copy());
        return Context.getCurrentContext().newObject(this, "ItemStack", new Object[]{inventory.getStack(slot).copy()});
    }
}
