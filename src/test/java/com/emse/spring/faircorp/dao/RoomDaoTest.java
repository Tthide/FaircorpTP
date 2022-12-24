package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Window;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.apache.logging.log4j.core.impl.Log4jContextFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class RoomDaoTest {

    @Autowired
    private RoomDao roomDao;



    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void shouldFindAllWindowFromRoom() {



        List<Window> windows_actual = roomDao.FindAllWindowByRoomName("Room1");
        List<Long> windows_actual_id = new ArrayList<>();
        for (int i = 0; i < windows_actual.size(); i++) {
            windows_actual_id.add(windows_actual.get(i).getId());
        }
        List<Long> windows_expected = Arrays.asList(-10L, -9L);
        List<Long> wrong_window = Arrays.asList(-8L, -7L);

        assertTrue(windows_actual_id.size() == windows_expected.size()
                && windows_actual_id.containsAll(windows_expected) && windows_expected.containsAll(windows_actual_id));

        assertFalse(windows_actual_id.size() == wrong_window.size()
                && windows_actual_id.containsAll(wrong_window) && wrong_window.containsAll(windows_actual_id));


    }
}