package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Window;

import java.util.List;

public interface RoomDaoCustom {
    List<Window> FindAllWindowByRoomName(String name);

    void deleteRoomById(Long id);
}
