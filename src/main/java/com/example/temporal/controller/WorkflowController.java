package com.example.temporal.controller;

import com.example.temporal.model.NextBestActionRequest;
import com.example.temporal.service.NextBestActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/workflow")
public class WorkflowController {

    @Autowired
    private NextBestActionService nextBestActionService;

    @PostMapping("/start")
    public void startWorkflow(@RequestBody NextBestActionRequest request) {
        nextBestActionService.startNextBestActionWorkflow(request);
    }
}
