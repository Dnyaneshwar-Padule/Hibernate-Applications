package com.tca.config;

import java.io.FileInputStream;
import java.util.Properties;

public class DatabaseConfigurations {

    public static final String DB_USER;
    public static final String DB_PWD;
    public static final String DB_URL;
    public static final String DB_DRIVER;

    static{
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties"))
        {
            properties.load(fis);

            DB_USER = properties.getProperty("DB_USER");
            DB_PWD = properties.getProperty("DB_PWD");
            DB_DRIVER = properties.getProperty("DB_DRIVER");
            DB_URL = properties.getProperty("DB_URL");
        }
        catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Failed to load Database configurations", e);
        }
        finally {
            properties.clear();
        }
    }
}
