package cn.mcfun.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class C3P0Utils {
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

    public C3P0Utils() {
    }

    public static Connection getConnection() {
        Properties props = new Properties();

        try {
            props.load(new FileInputStream("config.properties"));
        } catch (IOException var3) {
            var3.printStackTrace();
        }

        try {
            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        } catch (PropertyVetoException var2) {
            var2.printStackTrace();
        }

        dataSource.setJdbcUrl("jdbc:mysql://" + props.getProperty("url") + ":" + props.getProperty("port") + "/jp_gacha?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&serverTimezone=UTC");
        dataSource.setUser(props.getProperty("user"));
        dataSource.setPassword(props.getProperty("password"));
        dataSource.setInitialPoolSize(20);
        dataSource.setMaxPoolSize(1000);
        dataSource.setCheckoutTimeout(60000);

        try {
            return dataSource.getConnection();
        } catch (SQLException var4) {
            if (!var4.toString().contains("you can no longer use it")) {
                var4.printStackTrace();
            }

            return null;
        }
    }
}
