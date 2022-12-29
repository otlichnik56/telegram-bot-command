package pro.sky.telegrambot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.entity.Report;
import pro.sky.telegrambot.service.ReportService;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("report")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }


    @Operation(
            summary = "Вывести весь список отчётов контакта",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Найденные отчёты контакта",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = Report.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Ничего не найдено"
                    )
            },
            tags = "Reports"
    )
    @GetMapping("username")
    public List<Report> getAllReportsPerson(@Parameter(description = "Введите имя пользователя контакта", example = "ivan") @RequestParam String username) {
        return reportService.getAllReportsPerson(username);
    }


    @Operation(
            summary = "Вывести весь список отчетов за определённую дату",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Найденные отчёты за дату",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = Report.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Ничего не найдено"
                    )
            },
            tags = "Reports"
    )
    @GetMapping("date")
    public List<Report> getAllReportsDaily(@Parameter(description = "Введите дату, за которую посмотреть отчёты", example = "2022-12-30") @RequestParam LocalDate date) {
        return reportService.getAllReportsDaily(date);
    }

}
