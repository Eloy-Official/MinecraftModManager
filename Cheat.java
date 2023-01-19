package meo.meoclient.cheat;

import meo.meoclient.Meo;
import meo.meoclient.cheat.setting.Setting;
import meo.meoclient.utils.ClassUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cheat implements ClassUtility {

    public ACheat annotation = this.getClass().getAnnotation(ACheat.class);

    public String name;
    public int key;
    public Category category;

    public boolean state;

    public List<Setting> settings = new ArrayList<>();

    public Setting getSetting(String name) {
        return settings.stream().filter(objectSetting -> Objects.equals(objectSetting.name, name)).findFirst().get();
    }

    public Setting addSetting(String name, Object type, Object defaultValue) {
        Setting setting = new Setting(name, type, defaultValue);
        settings.add(setting);
        return setting;
    }

    public void changeValueByName(String name, Object newVaue) {
        getSetting(name).objValue = newVaue;
    }

    public void changeValueBySetting(Setting setting, Object newValue) {
        getSetting(setting.name).objValue = newValue;
    }

    public void toggleState() {
        state = !state;
        Meo.getMeo().cheatManager.callMethod(this, CheatMethodType.STATE_CHANGED);
    }

    public boolean isInMenu() {
        return playerEntity == null;
    }

    public Cheat() {
        if (annotation != null) {
            this.name = annotation.name();
            this.key = annotation.key();
            this.category = annotation.category();
        }
    }

}
