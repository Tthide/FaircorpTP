package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;
import com.emse.spring.faircorp.model.WindowStatus;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class WindowDaoTest {
    @Autowired
    private WindowDao windowDao;
    @Autowired
    private RoomDao roomDao;

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void shouldFindAWindow() {
        Window window = windowDao.getReferenceById(-10L);
        Assertions.assertThat(window.getName()).isEqualTo("Window 1");
        Assertions.assertThat(window.getWindow_status()).isEqualTo(WindowStatus.CLOSED);
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void shouldFindRoomOpenWindows() {
        List<Window> result = windowDao.findRoomOpenWindows(-9L);
        Assertions.assertThat(result)
                .hasSize(1)
                .extracting("id", "window_status")
                .containsExactly(Tuple.tuple(-8L, WindowStatus.OPEN));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void shouldNotFindRoomOpenWindows() {
        List<Window> result = windowDao.findRoomOpenWindows(-10L);
        Assertions.assertThat(result).isEmpty();
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void shouldDeleteWindowsRoom() {
        Room room = roomDao.getReferenceById(-10L);
        List<Long> roomIds = room.getWindows().stream().map(Window::getId).collect(Collectors.toList());
        Assertions.assertThat(roomIds.size()).isEqualTo(2);

        windowDao.deleteByRoom(-10L);
        List<Window> result = windowDao.findAllById(roomIds);
        Assertions.assertThat(result.size()).isEqualTo(2); //changé par rapport à l'énoncé
        //en effet on supprime les fenetres de la salle -10 mais pas des autres,
        //il reste forcément des fenetres autre part

    }
}