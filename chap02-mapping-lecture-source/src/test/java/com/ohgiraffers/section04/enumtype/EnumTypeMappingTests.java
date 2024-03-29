package com.ohgiraffers.section04.enumtype;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import static com.ohgiraffers.section04.enumtype.RoleType.MEMBER;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnumTypeMappingTests {
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

    @AfterAll
    public static void closeFactory() {
        entityManagerFactory.close();
    }

    @AfterEach
    public void closeManager() {
        entityManager.close();
    }

    @Test
    public void enum타입_매핑_테스트() {
        Member member = new Member();
        member.setMemberNo(1);
        member.setMemberId("user01");
        member.setMemberPwd("pass01");
        member.setNickname("홍길동");
        member.setPhone("010-1111-1111");
        member.setEmail("gildong@gmail.com");
        member.setAddress("서울특별시");
        member.setEnrollDate(new java.util.Date());

        /* 설명.
         *  테이블에 insert 할 때
         *   1. @Enumerated(EnumType.ORDINAL): 숫자
         *   2. @Enumerated(EnumType.STRING): 문자
        * */

        member.setMemberRole(MEMBER);
        member.setStatus("Y");

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(member);
        entityTransaction.commit();

        Member foundMember = entityManager.find(Member.class, member.getMemberNo());

        assertEquals(member.getMemberNo(), foundMember.getMemberNo());
        System.out.println("foundMember = " + foundMember);
    }
}
