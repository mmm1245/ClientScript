package com.github.mmm1245;

import net.minecraft.item.ItemStack;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.annotations.JSConstructor;
import org.mozilla.javascript.annotations.JSGetter;

public class ScriptItemStack extends ScriptableObject {
    public ItemStack item;
    @JSConstructor
    public ScriptItemStack(){
        this.item = ItemStack.EMPTY;
    }
    @JSConstructor
    public ScriptItemStack(ItemStack is){
        this.item = is;
    }
    @JSGetter
    public String id(){
        return item.getItem().toString();
    }
    @JSGetter
    public int count(){
        return item.getCount();
    }
    @Override
    public String getClassName() {
        return "ItemStack";
    }
    @JSGetter
    @Override
    public String toString() {
        return item.toString();
    }
}
