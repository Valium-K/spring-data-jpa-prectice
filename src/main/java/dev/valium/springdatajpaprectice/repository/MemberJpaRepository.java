package dev.valium.springdatajpaprectice.repository;

import dev.valium.springdatajpaprectice.entity.Member;
import dev.valium.springdatajpaprectice.entity.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberJpaRepository {

    private final EntityManager em;

    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery(
                "select m from Member m", Member.class
        ).getResultList();
    }

    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(find(id));
    }

    public void delete(Member member) {
        em.remove(member);
    }
    public long count() {
        return em.createQuery(
                "select count(m) from Member m", Long.class)
                .getSingleResult();
    }
}
