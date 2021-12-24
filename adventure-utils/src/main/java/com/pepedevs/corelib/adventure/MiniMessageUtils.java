package com.pepedevs.corelib.adventure;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.plain.PlainComponentSerializer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MiniMessageUtils {

    public static Component translate(String message) {
        return translate(MiniMessage.get(), message);
    }

    public static Component translate(MiniMessage miniMessage, String string) {
       return miniMessage.parse(string);
    }

    public static Component[] translate(String[] messages) {
        return translate(MiniMessage.get(), messages);
    }

    public static Component[] translate(MiniMessage miniMessage, String... strings) {
        Component[] components = new Component[strings.length];
        for (int i = 0; i < strings.length; i++) {
            components[i] = translate(miniMessage, strings[i]);
        }
        return components;
    }

    public static List<Component> translate(Collection<String> messages) {
        return translate(MiniMessage.get(), messages);
    }

    public static List<Component> translate(MiniMessage miniMessage, Collection<String> strings) {
        List<Component> components = new ArrayList<>(strings.size());
        for (String string : strings) {
            components.add(translate(miniMessage, string));
        }
        return components;
    }

    public static String untranslate(Component component) {
        return untranslate(MiniMessage.get(), component);
    }

    public static String untranslate(MiniMessage miniMessage, Component component) {
        return miniMessage.serialize(component);
    }

    public static String[] untranslate(Component... components) {
        return untranslate(MiniMessage.get(), components);
    }

    public static String[] untranslate(MiniMessage miniMessage, Component... components) {
        String[] strings = new String[components.length];
        for (int i = 0; i < components.length; i++) {
            strings[i] = untranslate(miniMessage, components[i]);
        }
        return strings;
    }

    public static List<String> untranslate(List<Component> components) {
        return untranslate(MiniMessage.get(), components);
    }

    public static List<String> untranslate(MiniMessage miniMessage, Collection<Component> components) {
        List<String> list = new ArrayList<>(components.size());
        for (Component component : components) {
            list.add(untranslate(miniMessage, component));
        }
        return list;
    }

    public static String strip(Component component) {
        return PlainComponentSerializer.plain().serialize(component);
    }

}