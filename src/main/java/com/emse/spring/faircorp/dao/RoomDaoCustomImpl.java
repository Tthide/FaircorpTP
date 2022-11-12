package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Window;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class RoomDaoCustomImpl implements RoomDaoCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Window> FindAllWindowByRoomName(String name) {
        String jpql = "select w from Window w left join Room r on r.id=w.room.id where r.name= :name ";
        return em.createQuery(jpql, Window.class)
                .setParameter("name", name)
                .getResultList();
    }

    @Override
    public void deleteRoomById(Long id) {
        //deleting windows
        String jpql = "delete from Window w where room_id = :room_id ";
        //String jpql = "update Room set ";
        em.createQuery(jpql)
                .setParameter("room_id", id)
                .executeUpdate();

        jpql = "delete from Heater h where room_id = :room_id ";
        //String jpql = "update Room set ";
        em.createQuery(jpql)
                .setParameter("room_id", id)
                .executeUpdate();

        jpql = "delete from Room r where id = :id ";
        //String jpql = "update Room set ";
        em.createQuery(jpql)
                .setParameter("id", id)
                .executeUpdate();
    }
}




