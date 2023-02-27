package com.poly.hangnt169.util;

import com.poly.hangnt169.entity.ChuyenNganh;
import com.poly.hangnt169.entity.Lop;
import com.poly.hangnt169.entity.SinhVien;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {

    private static final SessionFactory FACTORY;

    private static Session SESSION;

    static {
        Configuration conf = new Configuration();

        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        properties.put(Environment.URL, "jdbc:mysql://localhost:3306/PTPM_JAVA");
        properties.put(Environment.USER, "root");
        properties.put(Environment.PASS, "root");
        properties.put(Environment.SHOW_SQL, "true");

        conf.setProperties(properties);
        conf.addAnnotatedClass(ChuyenNganh.class);
        conf.addAnnotatedClass(Lop.class);
        conf.addAnnotatedClass(SinhVien.class);

        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);

    }

    public static SessionFactory getFACTORY() {
        return FACTORY;
    }
}
