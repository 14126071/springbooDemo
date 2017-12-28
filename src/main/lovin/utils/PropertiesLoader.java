package lovin.utils;

import java.io.IOException;
import java.util.Properties;
import java.io.IOException;

/**
 * Created by bixin on 2017/12/19.
 */
public class PropertiesLoader {
    private static Properties PROPS = new Properties();

    public PropertiesLoader() {
    }

    public static String getString(String key) {
        return PROPS.getProperty(key);
    }

    public static int getInt(String key) {
        return Integer.parseInt(PROPS.getProperty(key));
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(PROPS.getProperty(key));
    }

    static {
        try {
            PROPS.load(Object.class.getResourceAsStream("/redis.conf"));
        } catch (IOException var1) {
            var1.printStackTrace();
        }

    }
}

