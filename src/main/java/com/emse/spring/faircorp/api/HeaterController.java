package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dto.HeaterDto;
import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.Room;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController // (1)
@RequestMapping("/api/heaters") // (2)
@Transactional // (3)
@CrossOrigin
public class HeaterController {

    private final HeaterDao heaterDao;
    private final RoomDao roomDao;

    public HeaterController(HeaterDao heaterDao, RoomDao roomDao) { // (4)
        this.heaterDao = heaterDao;
        this.roomDao = roomDao;
    }

    @GetMapping // (5)
    @Secured("ROLE_ADMIN")
    public List<HeaterDto> findAll() {
        return heaterDao.findAll().stream().map(HeaterDto::new).collect(Collectors.toList());  // (6)
    }

    @GetMapping(path = "/{id}")
    @Secured("ROLE_ADMIN")
    public HeaterDto findById(@PathVariable Long id) {

        return heaterDao.findById(id).map(HeaterDto::new).orElse(null); // (7)
    }


    @PostMapping // (8)
    @Secured("ROLE_ADMIN") // (1)
    public HeaterDto create(@RequestBody HeaterDto dto) {
        // WindowDto must always contain the window room
        Room room = roomDao.getReferenceById(dto.getRoomId());
        Heater heater = null;
        // On creation id is not defined
        if (dto.getId() == null) {
            heater = heaterDao.save(new Heater(dto.getName(), room, dto.getHeater_status()));
        } else {
            heater = heaterDao.getReferenceById(dto.getId());  // (9)
            heater.setHeater_status(dto.getHeater_status());
        }
        return new HeaterDto(heater);
    }

    @DeleteMapping(path = "/{id}")
    @Secured("ROLE_ADMIN") // (1)
    public void delete(@PathVariable Long id) {
        heaterDao.deleteById(id);
    }
}