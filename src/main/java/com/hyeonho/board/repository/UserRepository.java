package com.hyeonho.board.repository;


import com.hyeonho.board.domain.User;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository implements UserRepInf {

    private final EntityManager em;

    public UserRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public User save(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = em.find(User.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<User> findAllUser() {
        List<User> result = em.createQuery("select m from User m",User.class).getResultList();
        User user = em.find(User.class, 1);
        System.out.println("더미로 만든 user는: " + user);
        return em.createQuery("select m from User m",User.class).getResultList();
    }
}
