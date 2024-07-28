package com.example.temporal.service;

import com.example.temporal.model.NextBestActionRequest;
import com.example.temporal.workflow.NextBestActionWorkflow;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import org.springframework.stereotype.Service;

@Service
public class NextBestActionService {

    private final WorkflowClient workflowClient;

    public NextBestActionService(WorkflowClient workflowClient) {
        this.workflowClient = workflowClient;
    }

    public void startNextBestActionWorkflow(NextBestActionRequest request) {
        NextBestActionWorkflow workflow = workflowClient.newWorkflowStub(
            NextBestActionWorkflow.class,
            WorkflowOptions.newBuilder()
                .setTaskQueue("NEXT_BEST_ACTION_TASK_QUEUE")
                .build()
        );

        WorkflowClient.start(workflow::startWorkflow, request);
    }
}
