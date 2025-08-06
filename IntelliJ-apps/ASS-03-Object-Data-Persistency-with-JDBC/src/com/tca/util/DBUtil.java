package com.tca.util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {

    private static final String DB_URL;
    private static final String DB_USER;
    private static final String DB_PWD;

    static{
        Properties properties = new Properties();

        try(FileInputStream fis = new FileInputStream("config.properties")){

            //load configurations from config.properties file
            properties.load(fis);

            //Register Driver
            Class.forName(properties.getProperty("DB_DRIVER"));

            DB_URL = properties.getProperty("DB_URL");
            DB_USER = properties.getProperty("DB_USER");
            DB_PWD = properties.getProperty("DB_PWD");

        }catch (ClassNotFoundException ce){
            throw new RuntimeException("Failed to register Driver.", ce);
        }
        catch (Exception e){
            throw new RuntimeException("Failed to load configurations.", e);
        }
        finally {
            properties.clear();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
    }


}
