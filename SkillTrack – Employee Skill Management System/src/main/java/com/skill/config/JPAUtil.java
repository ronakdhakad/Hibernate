package com.skill.config;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

    private static final EntityManagerFactory EMF =Persistence.createEntityManagerFactory("mypunit");

    public static EntityManagerFactory getFactory() {
        return EMF;
    }

    public static void shutdown() {
        EMF.close();
    }
}
