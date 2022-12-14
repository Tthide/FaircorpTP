package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Window;

import java.util.List;

public interface WindowDaoCustom {
    List<Window> findRoomOpenWindows(Long id);

    List<Window> findAllById(List<Long> roomIds);

    void deleteByRoom(Long id);

    void deleteWindowById(Long id);
}
