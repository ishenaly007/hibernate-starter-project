package com.abit8.hibernate_starter.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    public static SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration().configure();
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        configuration.configure();
        return configuration.buildSessionFactory();
    }
}