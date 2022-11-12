package com.emse.spring.faircorp.api;


import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.dto.RoomDto;
import com.emse.spring.faircorp.model.*;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController // (1)
@RequestMapping("/api/rooms") // (2)
@Transactional // (3)
@CrossOrigin
public class RoomController {
    private final WindowDao windowDao;
    private final RoomDao roomDao;
    private final HeaterDao heaterDao;

    public RoomController(WindowDao windowDao, RoomDao roomDao, HeaterDao heaterDao) { // (4)
        this.windowDao = windowDao;
        this.roomDao = roomDao;
        this.heaterDao = heaterDao;
    }

    // /api/rooms (GET) send room list
    @GetMapping // (5)
    @Secured("ROLE_ADMIN") // (1)
    public List<RoomDto> findAll() {
        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList());  // (6)
    }

    // /api/rooms/{room_id} (GET) read a room
    @GetMapping(path = "/{id}")
    @Secured("ROLE_ADMIN") // (1)
    public RoomDto findById(@PathVariable Long id) {

        return roomDao.findById(id).map(RoomDto::new).orElse(null); // (7)
    }


    @PostMapping // (8)
    @Secured("ROLE_ADMIN") // (1)
    public RoomDto create(@RequestBody RoomDto dto) {

        Room room = null;
        // On creation id is not defined

        if (dto.getId() == null) {
            room = roomDao.save(new Room(dto.getFloor(), dto.getName()));
        } else {
            room = roomDao.getReferenceById(dto.getId());
            room.setFloor(dto.getFloor());// (9)

        }
        return new RoomDto(room);
    }

    @DeleteMapping(path = "/{id}")
    @Secured("ROLE_ADMIN") // (1)
    public void delete(@PathVariable Long id) {
        Room room = roomDao.getReferenceById(id);
        roomDao.deleteRoomById(id);
    }


    // /api/rooms/{room_id}/switchWindow switch the room windows (OPEN to CLOSED or inverse)
    @PutMapping(path = "/{id}/switchWindow")
    @Secured("ROLE_ADMIN") // (1)
    public void switchStatusWindoww(@PathVariable Long id) {
        Room room = roomDao.getReferenceById(id);
        List<Window> windows = room.getWindows();
        Window window = null;
        for (int i = 0; i < windows.size(); i++) {

            window = windowDao.findById(windows.
                    get(i).getId()).orElseThrow(IllegalArgumentException::new);
            window.setWindow_status(window
                    .getWindow_status() == WindowStatus.OPEN ?
                    WindowStatus.CLOSED : WindowStatus.OPEN);
        }

    }

    //api/rooms/{room_id}/switchHeaters switch the room heaters (ON to OFF or inverse)
    @PutMapping(path = "/{id}/switchHeater")
    @Secured("ROLE_ADMIN") // (1)
    public void switchStatusHeaters(@PathVariable Long id) {
        Room room = roomDao.getReferenceById(id);
        List<Heater> heaters = room.getHeaters();
        Heater heater = null;
        for (int i = 0; i < heaters.size(); i++) {

            heater = heaterDao.
                    findById(heaters.get(i).getId()).
                    orElseThrow(IllegalArgumentException::new);
            heater.setHeater_status(heater.
                    getHeater_status() == HeaterStatus.ON ? HeaterStatus.OFF :
                    HeaterStatus.ON);

        }

    }
}
