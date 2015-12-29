package com.hxdcml.irc;

import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.JoinEvent;
import org.pircbotx.hooks.events.NickChangeEvent;

/**
 * Author: Soul Ayoub
 * Date: 12/20/2015
 */
public class GateAdaptor extends ListenerAdapter<PircBotX> {

    private String message;

    public GateAdaptor(String message) {
        setMessage(message);
    }

    public void onJoin(JoinEvent<PircBotX> event) throws Exception {
        String nick = event.getUser().getNick();
        if (event.getBot().getNick().equals(nick))
            return;

        event.respond(message);
    }

    @Override
    public void onNickChange(NickChangeEvent<PircBotX> event) throws Exception {

    }

    public void setMessage(String message) {
        this.message = message;
    }
}
