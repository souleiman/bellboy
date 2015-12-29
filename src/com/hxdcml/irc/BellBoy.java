package com.hxdcml.irc;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.apache.log4j.Logger;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.UtilSSLSocketFactory;
import org.pircbotx.exception.IrcException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

/**
 * Author: Soul Ayoub
 * Date: 12/20/2015
 */
public class BellBoy {
    private static final Logger logger = Logger.getLogger(BellBoy.class);

    public BellBoy(Map<String, String> config) throws IOException, IrcException {

        // String log4jConfPath = "log4j.properties";
        // PropertyConfigurator.configure(log4jConfPath);

        Configuration<PircBotX> configuration = new Configuration.Builder<>()
                .setSocketFactory(new UtilSSLSocketFactory().trustAllCertificates())
                .setServerHostname(config.get("address"))
                .setServerPort(Integer.valueOf(config.get("port")))
                .setServerPassword(config.get("password"))
                .addListener(new GateAdaptor(config.get("message")))
                .buildConfiguration();

        PircBotX bot = new PircBotX(configuration);
        bot.startBot();
    }

    public static void main(String[] args) throws IOException, IrcException {

        Gson gson = new Gson();

        File file = new File("config.json");
        if (!file.exists()) {
            logger.error("Failed to load resources: " + file.getName());
            return;
        }

        JsonReader reader = new JsonReader(new FileReader(file));
        Map<String, String> config = gson.fromJson(reader, Map.class);
        reader.close();

        new BellBoy(config);
    }
}


