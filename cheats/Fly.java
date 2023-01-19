package meo.meoclient.cheat.cheats;

import meo.meoclient.cheat.*;
import meo.meoclient.cheat.setting.Setting;
import org.lwjgl.glfw.GLFW;


@ACheat(name = "Fly", key = GLFW.GLFW_KEY_X, category = Category.MOVEMENT)
public class Fly extends Cheat {

    @CheatMethod(type = CheatMethodType.REGISTER)
    public void onRegister() {
        addSetting("TestBool", Boolean.class, false);
    }

    @CheatMethod(type = CheatMethodType.TICK)
    public void onTick() {
        if (!isInMenu()) {

        }
    }

    @CheatMethod(type = CheatMethodType.STATE_CHANGED)
    public void onStateChanged() {
        Setting setting = getSetting("TestBool");
        logger.info(setting.name + " : " + setting.getValueAsString());
        changeValueBySetting(setting, !setting.getValueAsBoolean());
    }

}
