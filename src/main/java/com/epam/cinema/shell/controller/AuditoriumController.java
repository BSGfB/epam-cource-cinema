package com.epam.cinema.shell.controller;

import com.epam.cinema.model.Auditorium;
import com.epam.cinema.service.AuditoriumService;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
public class AuditoriumController implements CommandMarker {

    private AuditoriumService auditoriumService;

    public AuditoriumController(AuditoriumService auditoriumService) {
        this.auditoriumService = auditoriumService;
    }

    @CliCommand(value = {"auditoriums"})
    public String getAuditoriums(@CliOption(key = "all", mandatory = false) String all,
                           @CliOption(key = "id", mandatory = false) Long id,
                           @CliOption(key = "name", mandatory = false) String name) {
        if (!isNull(id))
            return auditoriumService.getById(id).toString();
        if (!isNull(name))
            return auditoriumService.getByName(name).toString();

        return auditoriumService.getAll().stream().map(Auditorium::toString).collect(Collectors.joining("\n"));
    }

    @CliCommand(value = {"rm-auditorium"})
    public void removeAuditoriums(@CliOption(key = "id", mandatory = false) Long id) {
        auditoriumService.remove(id);
    }

    @CliCommand(value = {"create-auditorium"})
    public String createAuditoriums(@CliOption(key = "name", mandatory = true) String name,
                             @CliOption(key = "seats", mandatory = true) Long seats,
                             @CliOption(key = "vip", mandatory = true, help = "Set numbers of vip seats. Example (--vip 1,2,3,4,5)") String vipSeats) {
        Auditorium auditorium = new Auditorium();
        auditorium.setName(name);
        auditorium.setNumberOfSeats(seats);
        auditorium.setVipSeats(Arrays.stream(vipSeats.split(",")).map(Long::parseLong).collect(Collectors.toSet()));

        return "Added new auditorium with id: " + auditoriumService.save(auditorium);
    }
}
