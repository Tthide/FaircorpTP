package com.emse.spring.faircorp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ROOM")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name = "floor")
    private int floor;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(name = "current_temperature")
    private Double current_temperature;

    @Column(name = "target_temperature")
    private Double target_temperature;

    @JsonIgnore
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL
            , fetch = FetchType.LAZY)
    private List<Heater> heaters;
    @JsonIgnore
    @OneToMany(mappedBy = "room", cascade = CascadeType.PERSIST
            , fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Window> windows;


    public Room() {
    }

    public Room(int floor, String name) {
        this.floor = floor;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCurrent_temperature() {
        return current_temperature;
    }

    public void setCurrent_temperature(Double current_temperature) {
        this.current_temperature = current_temperature;
    }

    public Double getTarget_temperature() {
        return target_temperature;
    }

    public void setTarget_temperature(Double target_temperature) {
        this.target_temperature = target_temperature;
    }


    public List<Heater> getHeaters() {
        return heaters;
    }

    public void setHeaters(List<Heater> heaters) {
        this.heaters = heaters;
    }

    public List<Window> getWindows() {
        return windows;
    }

    public void setWindows(List<Window> windows) {
        this.windows = windows;
    }

    public void removeWindow(Window Window) {
        windows.remove(Window);
    }

    public void removeHeater(Heater heater) {
        heaters.remove(heater);
    }
}

