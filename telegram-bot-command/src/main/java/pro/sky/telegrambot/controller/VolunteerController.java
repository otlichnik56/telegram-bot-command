package pro.sky.telegrambot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.entity.Volunteer;
import pro.sky.telegrambot.service.VolunteerService;

import java.util.List;

public class VolunteerController {

    private final VolunteerService volunteerService;

    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }


    @Operation(
            summary = "Вывести весь список волонтёров",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Найденные волонтёры",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = Volunteer.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Ничего не найдено"
                    )
            },
            tags = "Volunteers"
    )
    @GetMapping
    public List<Volunteer> getAllVolunteer() {
        return volunteerService.getAllVolunteer();
    }


    @Operation(
            summary = "Найти волонтёра по ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Найденный волонтёр",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Volunteer.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Ничего не найдено"
                    )
            },
            tags = "Volunteers"
    )
    @GetMapping("id")
    public Volunteer findVolunteerById(@Parameter(description = "Введите ID волонтёра", example = "15") @RequestParam Long id) {
        return volunteerService.getVolunteer(id);
    }


    @Operation(
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Создание новой записи волонтёра",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Volunteer.class)
                    )
            ),
            tags = "Volunteers"
    )
    @PostMapping
    public Volunteer createVolunteer(@RequestBody Volunteer volunteer) {
        return volunteerService.createVolunteer(volunteer);
    }


    @Operation(
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Редактирование записи волонтёра",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Volunteer.class)
                    )
            ),
            tags = "Volunteers"
    )
    @PutMapping
    public Volunteer editVolunteer(@RequestBody Volunteer volunteer) {
        return volunteerService.editVolunteer(volunteer);
    }


    @Operation(
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Удаление записи волонтёра",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Volunteer.class)
                    )
            ),
            tags = "Volunteers"
    )
    @DeleteMapping("id")
    public ResponseEntity deleteVolunteer(@RequestParam Long id) {
        volunteerService.deleteVolunteer(id);
        return ResponseEntity.ok().build();
    }

}
