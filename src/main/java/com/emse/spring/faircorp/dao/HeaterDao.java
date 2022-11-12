package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HeaterDao extends JpaRepository<Heater, Long>, HeaterDaoCustom, CrudRepository<Heater, Long> {

    Heater getReferenceById(Long id);
}
