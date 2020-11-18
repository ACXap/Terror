package Service;

import Data.DbConnectProperty;
import Data.InternetConnectProperty;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class PropertyService {

    //region PrivateField
    private static final String _fileIni = "settings.ini";

    //endregion PrivateField

    //region PublicProperty

    public static String ApiKey;
    public static String UrlService;
    public static String DbSeparator;
    public static DbConnectProperty DbConnectProperty;
    //public static InternetConnectProperty InternetConnectProperty;
    public static boolean CanSaveTempFile;
    public static String PathTempFile;

    //endregion PublicProperty

    //region PublicMethod
    public static void Initialization() throws Exception {
        Properties props = new Properties();

        try (FileInputStream fs = new FileInputStream(new File(_fileIni));
             InputStreamReader sr = new InputStreamReader(fs, StandardCharsets.UTF_8)) {
            props.load(sr);
        }

        //ApiKey = props.getProperty("ApiKey");
        //UrlService = props.getProperty("UrlService");
        DbSeparator = props.getProperty("DbSeparator");
        DbConnectProperty = new DbConnectProperty(props.getProperty("DbType"),
                props.getProperty("DbServer"),
                ParseInt(props.getProperty("DbPort"), 5432),
                props.getProperty("DbName"),
                props.getProperty("DbUser"),
                props.getProperty("DbPassword"),
                props.getProperty("DbSchema"));
        CanSaveTempFile = Boolean.parseBoolean(props.getProperty("SaveTempFile"));
        PathTempFile = props.getProperty("PathTempFile");

        //InternetConnectProperty = new InternetConnectProperty(props.getProperty("ProxyServer"), ParseInt(props.getProperty("ProxyPort"), 8080));
    }
    //endregion PublicMethod

    //region PrivateMethod
    private static int ParseInt(String content, int defValue) {
        try {
            return Integer.parseInt(content);
        } catch (Exception ex) {
            ex.printStackTrace();
            return defValue;
        }
    }
    //endregion PrivateMethod
}