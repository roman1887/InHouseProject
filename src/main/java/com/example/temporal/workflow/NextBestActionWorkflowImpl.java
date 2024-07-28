package com.example.temporal.workflow;

import com.example.temporal.model.NextBestActionRequest;
import com.example.temporal.model.ChildTask;
import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;
import io.temporal.workflow.WorkflowMethod;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NextBestActionWorkflowImpl implements NextBestActionWorkflow {

    private final List<ChildTaskWorkflow> childWorkflows = new ArrayList<>();

    @Override
    public void startWorkflow(NextBestActionRequest request) {
        for (ChildTask task : request.getChildTasks()) {
            ChildTaskWorkflow childWorkflow = Workflow.newChildWorkflowStub(ChildTaskWorkflow.class);
            childWorkflows.add(childWorkflow);
            childWorkflow.startTask(task);
        }

        Workflow.await(() -> {
            LocalDate now = LocalDate.now();
            return now.isAfter(request.getEndDate()) || childWorkflows.stream().allMatch(Workflow::isWorkflowCompleted);
        });

        if (LocalDate.now().isAfter(request.getEndDate())) {
            for (ChildTaskWorkflow childWorkflow : childWorkflows) {
                Workflow.newUntypedWorkflowStub(childWorkflow.getWorkflowId()).terminate("Parent workflow end date reached");
            }
        }
    }
}
