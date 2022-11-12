package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Window;
import com.emse.spring.faircorp.model.WindowStatus;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class WindowDaoCustomImpl implements WindowDaoCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Window> findRoomOpenWindows(Long id) {
        String jpql = "select w from Window w where w.room.id = :id and w.window_status= :status";
        return em.createQuery(jpql, Window.class)
                .setParameter("id", id)
                .setParameter("status", WindowStatus.OPEN)
                .getResultList();
    }

    @Override
    public List<Window> findAllById(List<Long> roomIds) {
        String jpql = "select w from Window w left join Room r on r.id=w.room.id where r.id in :roomIds ";
        return em.createQuery(jpql, Window.class)
                .setParameter("roomIds", roomIds)
                .getResultList();
    }

    @Override
    public void deleteByRoom(Long room_id) {
        String jpql = "delete from Window w where room_id = :room_id ";
        //String jpql = "update Room set ";
        em.createQuery(jpql)
                .setParameter("room_id", room_id)
                .executeUpdate();


    }

    @Override
    public void deleteWindowById(Long id) {
        String jpql = "delete from Window w where id = :id ";
        //String jpql = "update Room set ";
        em.createQuery(jpql)
                .setParameter("id", id)
                .executeUpdate();
    }
}

