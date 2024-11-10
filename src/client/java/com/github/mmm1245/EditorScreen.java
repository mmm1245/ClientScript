package com.github.mmm1245;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.EditBoxWidget;
import net.minecraft.text.Text;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

@Environment(EnvType.CLIENT)
public class EditorScreen extends Screen {
    protected EditorScreen() {
        // The parameter is the title of the screen,
        // which will be narrated when you enter the screen.
        super(Text.literal("Editor screen"));
    }


    public EditBoxWidget editBox;
    public ButtonWidget button2;

    @Override
    protected void init() {

        editBox = new EditBoxWidget(textRenderer, 100, 100, 500, 500, Text.literal("aaa"), Text.literal("bbb"));
        button2 = ButtonWidget.builder(Text.literal("run"), button -> {
                    Context cx = Context.enter();
                    cx.setLanguageVersion(Context.VERSION_ES6);
                    try {
                        // Initialize the standard objects (Object, Function, etc.)
                        // This must be done before scripts can be executed. Returns
                        // a scope object that we use in later calls.
                        Scriptable scope = cx.initStandardObjects();
                        ScriptableObject.defineClass(scope, ScriptItemStack.class);
                        ScriptableObject.defineClass(scope, ScriptPlayer.class);

                        scope.put("player", scope, cx.newObject(scope, "Player"));

                        // Collect the arguments into a single string.
                        String s = editBox.getText();

                        // Now evaluate the string we've colected.
                        Object result = cx.evaluateString(scope, s, "<cmd>", 1, null);

                        // Convert the result to a string and print it.
                        System.out.println(Context.toString(result));

                    } catch(Exception e) {
                        e.printStackTrace();
                    }finally {
                        // Exit from the context.
                        Context.exit();
                    }
                    System.out.println("You clicked button2!");
                })
                .dimensions(width / 2 + 5, 20, 200, 20)
                .tooltip(Tooltip.of(Text.literal("Tooltip of button2")))
                .build();

        addDrawableChild(editBox);
        addDrawableChild(button2);
    }
}
