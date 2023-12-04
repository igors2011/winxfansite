package com.winxfansite.fansite.rest;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class RestConnection {
    public static String getRestURL() {
        String result = "localhost:8081";
        try {
            Properties props = new Properties();
            InputStream in = Files.newInputStream(Paths.get("user-mvc/src/main/resources/settings/rest.properties"));
            props.load(in);
            return props.getProperty("url");
        }
        catch (IOException e)
        {
            return result;
        }
    }
}
