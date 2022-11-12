package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RoomDao extends JpaRepository<Room, Long>, RoomDaoCustom, CrudRepository<Room, Long> {
    Room getReferenceById(Long id);
}
