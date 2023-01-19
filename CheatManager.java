package meo.meoclient.cheat;

import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class CheatManager {

    public final List<Cheat> cheats = new ArrayList<>();

    public CheatManager() {
        final Reflections reflections = new Reflections("meo.meoclient.cheat.cheats");
        reflections.getTypesAnnotatedWith(ACheat.class).forEach(clazz -> {
            try {
                Cheat cheat = (Cheat) clazz.newInstance();
                callMethod(cheat, CheatMethodType.REGISTER);
                cheats.add(cheat);
            } catch (IllegalAccessException | InstantiationException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void callMethodForAll(CheatMethodType methodType) {
        cheats.forEach(cheat -> callMethod(cheat, methodType));
    }

    public void callMethod(Cheat cheat, CheatMethodType methodType) {
        for (Method method : cheat.getClass().getMethods()) {
            if (method.isAnnotationPresent(CheatMethod.class)) {
                CheatMethod annotation = method.getAnnotation(CheatMethod.class);
                if (annotation.type() == methodType) {
                    try {
                        method.invoke(cheat);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
