package com.example.temporal.workflow;

import com.example.temporal.model.ChildTask;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface ChildTaskWorkflow {
    @WorkflowMethod
    void startTask(ChildTask task);
}
