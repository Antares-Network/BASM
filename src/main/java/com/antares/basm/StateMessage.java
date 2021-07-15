package com.antares.basm;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;

enum State {
    SUCCESS, ERROR, INFO;
    public static ChatColor stateColor(State state) {
        ChatColor color = null;
        switch (state) {
            case SUCCESS:
                color = ChatColor.GREEN;
                break;
            case ERROR:
                color = ChatColor.RED;
                break;
            case INFO:
                color = ChatColor.GRAY;
                break;
        }
        return color;
    }
};

public class StateMessage {

    public State state;
    public String message;

    public StateMessage(State state, String message) {
        this.state = state;
        this.message = message;
    }

    public BaseComponent[] construct() {
        return StateMessage.construct(state, message);
    }

    public static ComponentBuilder prefix() {
        return new ComponentBuilder("BASM")
                .color(ChatColor.GOLD)
                .bold(true)
                .append(" » ")
                .color(ChatColor.DARK_GRAY)
                .bold(false);
    }

    public static BaseComponent[] construct(State state, String message) {
        ChatColor color = State.stateColor(state);
        BaseComponent[] component = prefix()
                .append(message)
                .color(color)
                .create();
        return component;
    }

    public static StateMessage[] toArray(StateMessage message) {
        StateMessage messages[] = {message};
        return messages;
    }

    public static StateMessage[] toArray(State state, String message) {
        StateMessage messages[] = { new StateMessage(state, message) };
        return messages;
    }

}
