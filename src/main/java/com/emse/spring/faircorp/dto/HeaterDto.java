package com.emse.spring.faircorp.dto;

import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.HeaterStatus;

public class HeaterDto {


    private Long id;
    private String name;
    private Long power;
    private HeaterStatus heater_status;
    private String roomName;
    private Long roomId;

    public HeaterDto() {
    }

    public HeaterDto(Heater heater) {
        this.id = heater.getId();
        this.name = heater.getName();
        this.power = heater.getPower();
        this.heater_status = heater.getHeater_status();
        this.roomName = heater.getRoom().getName();
        this.roomId = heater.getRoom().getId();
    }

    public HeaterDto(HeaterDto heaterDto) {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPower() {
        return power;
    }

    public void setPower(Long power) {
        this.power = power;
    }

    public HeaterStatus getHeater_status() {
        return heater_status;
    }

    public void setHeater_status(HeaterStatus heater_status) {
        this.heater_status = heater_status;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
}
