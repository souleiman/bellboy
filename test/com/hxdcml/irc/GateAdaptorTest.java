package com.hxdcml.irc;

import org.junit.Before;
import org.junit.Test;
import org.pircbotx.PircBotX;
import org.pircbotx.User;
import org.pircbotx.hooks.events.JoinEvent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Author: Soul Ayoub
 * Date: 12/23/2015
 */
public class GateAdaptorTest {
    private static final String MESSAGE = "HELLO WORLD!";

    private GateAdaptor adaptor;
    private JoinEvent<PircBotX> event;

    @SuppressWarnings(value = "unchecked")
    @Before
    public void setUp() {
        adaptor = new GateAdaptor(MESSAGE);

        event = (JoinEvent<PircBotX>) mock(JoinEvent.class);

        PircBotX fakeBot = mock(PircBotX.class);

        when(fakeBot.getNick()).thenReturn("com/hxdcml/gate");
        when(event.getBot()).thenReturn(fakeBot);

        doAnswer(invocation -> {
            Object message = invocation.getArguments()[0];

            assertNotNull(message);
            assertEquals(MESSAGE, message);

            return true;
        }).when(event).respond(anyString());
    }

    @Test
    public void testOnJoin() throws Exception {
        User fakeUser = mock(User.class);

        when(fakeUser.getNick()).thenReturn("not_gate");
        when(event.getUser()).thenReturn(fakeUser);

        adaptor.onJoin(event);
        verify(event, times(1)).respond(anyString());
    }

    @Test
    public void testOnJoinBotUser() throws Exception {
        User fakeUser = mock(User.class);

        when(fakeUser.getNick()).thenReturn("com/hxdcml/gate");
        when(event.getUser()).thenReturn(fakeUser);

        adaptor.onJoin(event);
        verify(event, never()).respond(anyString());
    }
}