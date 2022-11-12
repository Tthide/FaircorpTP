package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Window;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface WindowDao extends JpaRepository<Window, Long> , WindowDaoCustom, CrudRepository<Window, Long> {
    Window getReferenceById(Long id);

}
