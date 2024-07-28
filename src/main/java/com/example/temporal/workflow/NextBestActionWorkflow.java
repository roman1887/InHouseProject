package com.example.temporal.workflow;

import com.example.temporal.model.NextBestActionRequest;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface NextBestActionWorkflow {
    @WorkflowMethod
    void startWorkflow(NextBestActionRequest request);
}
