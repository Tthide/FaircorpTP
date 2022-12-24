package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.dto.WindowDto;
import com.emse.spring.faircorp.logs.TestLog4J;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;
import com.emse.spring.faircorp.model.WindowStatus;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController // (1)
@RequestMapping("/api/windows") // (2)
@Transactional // (3)
@CrossOrigin
public class WindowController {
    private static final Logger LOGGER =  LogManager.getLogger( TestLog4J.class );

    private final WindowDao windowDao;
    private final RoomDao roomDao;

    public WindowController(WindowDao windowDao, RoomDao roomDao) { // (4)
        this.windowDao = windowDao;
        this.roomDao = roomDao;
    }

    @GetMapping // (5)
    @Secured("ROLE_ADMIN") // (1)
    public List<WindowDto> findAll() {
        return windowDao.findAll().stream().map(WindowDto::new).collect(Collectors.toList());  // (6)
    }

    @GetMapping(path = "/{id}")
    @Secured("ROLE_ADMIN") // (1)
    public WindowDto findById(@PathVariable Long id) {

        return windowDao.findById(id).map(WindowDto::new).orElse(null); // (7)
    }

    @PutMapping(path = "/{id}/switch")
    @Secured("ROLE_ADMIN") // (1)
    public WindowDto switchStatus(@PathVariable Long id) {
        Window window = windowDao.findById(id).orElseThrow(IllegalArgumentException::new);
        window.setWindow_status(window.getWindow_status() == WindowStatus.OPEN ? WindowStatus.CLOSED : WindowStatus.OPEN);
        LOGGER.log( Level.INFO, "Window # "+Long.toString(id)+" now " + window.getWindow_status().toString());

        return new WindowDto(window);
    }

    @PostMapping // (8)
    @Secured("ROLE_ADMIN") // (1)
    public WindowDto create(@RequestBody WindowDto dto) {
        // WindowDto must always contain the window room
        Room room = roomDao.getReferenceById(dto.getRoomId());
        Window window = null;
        // On creation id is not defined
        if (dto.getId() == null) {
            window = windowDao.save(new Window(dto.getName(), dto.getWindowStatus(), room));
        } else {
            window = windowDao.getReferenceById(dto.getId());  // (9)
            window.setWindow_status(dto.getWindowStatus());
        }
        return new WindowDto(window);
    }

    @DeleteMapping(path = "/{id}")
    @Secured("ROLE_ADMIN") // (1)
    public void delete(@PathVariable Long id) {
        windowDao.deleteWindowById(id);
        LOGGER.log( Level.INFO, "Window # "+Long.toString(id)+" deleted" );

    }
}