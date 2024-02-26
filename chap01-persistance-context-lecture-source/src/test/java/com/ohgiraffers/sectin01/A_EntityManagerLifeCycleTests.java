package com.ohgiraffers.sectin01;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;


public class A_EntityManagerLifeCycleTests {
    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @BeforeAll
    public static void initFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");
    }

    @BeforeEach
    public void initManager() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Test
    public void 엔터티_매니저_팩토리와_엔터티_매니저_생명주기_확인1(){
        System.out.println("entityManagerFactory.hashCode() = " + entityManagerFactory.hashCode());
        System.out.println("entityManager.hashCode() = " + entityManager.hashCode());
    }
    @Test
    public void 엔터티_매니저_팩토리와_엔터티_매니저_생명주기_확인2(){
        System.out.println("entityManagerFactory.hashCode() = " + entityManagerFactory.hashCode());
        System.out.println("entityManager.hashCode() = " + entityManager.hashCode());
    }

    @AfterAll
    public static void closeFactory() {
        entityManagerFactory.close();
    }

    @AfterEach
    public void closeManager() {
        entityManager.close();
    }

}