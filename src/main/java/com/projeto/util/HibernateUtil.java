package com.projeto.util;


import java.util.Properties;

import javax.persistence.EntityManager;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                // settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/projeto");
                settings.put(Environment.USER, "projeto");
                settings.put(Environment.PASS, "password");

                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(settings);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
    public static EntityManager entityManager() {
        return getSessionFactory().createEntityManager();
    }

}

// SEGUNDA FORMA

// META-INF/persistence.xml

// <persistence xmlns="http://java.sun.com/xml/ns/persistence"
//          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
//          xsi:schemaLocation="http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd"
//          version="2.0">
// <persistence-unit name="projeto">

//   <properties>
//      <!-- <property name="hibernate.connection.driver_class">com.mysql.jdbc.Driver</property> -->
//      <property name="hibernate.dialect" value="org.hibernate.dialect.MySQL8Dialect" />
//     <property name="hibernate.hikari.dataSource.url" value="${quarkus.datasource.jdbc.url}" />
//     <property name="hibernate.hikari.dataSource.user" value="${quarkus.datasource.username}" />
//     <property name="hibernate.hikari.dataSource.password" value="${quarkus.datasource.password}" />

//    </properties>
//   </persistence-unit>
// </persistence>

// HibernateUtil.java

// import javax.persistence.EntityManagerFactory;
// import javax.persistence.Persistence;
// static private EntityManagerFactory emf;
// static private String PersirstenceUnit = "projeto";
// private static void init() {
//     HibernateUtil.emf = Persistence.createEntityManagerFactory(PersirstenceUnit);
// }

// public static EntityManager entityManager(){
//     if(HibernateUtil.emf == null) HibernateUtil.init();
//    return HibernateUtil.emf.createEntityManager();
// }