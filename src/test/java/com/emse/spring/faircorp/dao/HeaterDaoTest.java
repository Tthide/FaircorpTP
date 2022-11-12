package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.Room;
import org.assertj.core.api.Assertions;
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
class HeaterDaoTest {

    @Autowired
    private HeaterDao heaterDao;
    @Autowired
    private RoomDao roomDao;

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void shouldDeleteWindowsRoom() {
        Room room = roomDao.getReferenceById(-10L);
        List<Long> roomIds = room.getHeaters().stream().map(Heater::getId).collect(Collectors.toList());
        Assertions.assertThat(roomIds.size()).isEqualTo(2);

        heaterDao.deleteByRoom(-10L);
        List<Heater> result = heaterDao.findAllById(roomIds);
        Assertions.assertThat(result.size()).isEqualTo(0); //changé par rapport à l'énoncé
        //en effet on supprime les fenetres de la salle -10 mais pas des autres,
        //il reste forcément des fenetres autre part

    }

}