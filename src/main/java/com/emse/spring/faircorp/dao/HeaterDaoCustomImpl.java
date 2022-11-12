package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.Window;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class HeaterDaoCustomImpl implements HeaterDaoCustom {


    @PersistenceContext
    private EntityManager em;

    @Override
    public void deleteByRoom(long room_id) {
        String jpql = "delete from Heater where room_id = :room_id ";
        //String jpql = "update Room set ";
        em.createQuery(jpql)
                .setParameter("room_id", room_id)
                .executeUpdate();
    }

    @Override
    public List<Heater> findAllById(List<Long> roomIds) {
        String jpql = "select h from Heater h left join Room r on r.id=h.room.id where r.id in :roomIds ";
        return em.createQuery(jpql, Heater.class)
                .setParameter("roomIds", roomIds)
                .getResultList();
    }
}
