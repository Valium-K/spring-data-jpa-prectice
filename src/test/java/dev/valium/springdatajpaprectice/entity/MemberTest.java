package dev.valium.springdatajpaprectice.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberTest {

    @PersistenceContext
    private EntityManager em;

    @Test
    public void testEntity() {
        Team team1 = new Team("team1");
        Team team2 = new Team("team2");

        em.persist(team1);
        em.persist(team2);

        Member member1 = new Member("member1", 10, team1);
        Member member2 = new Member("member2", 20, team2);
        Member member3 = new Member("member3", 30, team1);
        Member member4 = new Member("member4", 40, team2);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        em.flush(); // 플러시
        em.clear(); // cache clear

        List<Member> members = em.createQuery(
                "select m from Member m", Member.class
        ).getResultList();

        for (Member member : members) {
            System.out.println("member: " + member);
            System.out.println("member.team: = " + member.getTeam());
        }
    }
}