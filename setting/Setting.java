package meo.meoclient.cheat.setting;

public class Setting {

    public String name;
    public Object objType, objValue;

    public Setting(String name, Object objType, Object objValue) {
        this.name = name;
        this.objType = objType;
        this.objValue = objValue;
    }

    public String getValueAsString() {
        return String.valueOf(objValue);
    }

    public boolean getValueAsBoolean() {
        return (Boolean) objValue;
    }

    public Float getValueAsFloat() {
        return Float.valueOf(getValueAsString());
    }
}
