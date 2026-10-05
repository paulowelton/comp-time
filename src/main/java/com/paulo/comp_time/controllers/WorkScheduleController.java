package com.paulo.comp_time.controllers;

import com.paulo.comp_time.services.WorkScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/work-schedules")
@RequiredArgsConstructor
public class WorkScheduleController {

    private final WorkScheduleService workScheduleService;


}
