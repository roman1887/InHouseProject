package com.example.temporal.workflow;

import com.example.temporal.model.ChildTask;
import io.temporal.workflow.Workflow;
import io.temporal.workflow.WorkflowMethod;

import java.time.Duration;

public class ChildTaskWorkflowImpl implements ChildTaskWorkflow {

    private int completedTaskCount = 0;
    private int totalPoints = 0;

    @Override
    public void startTask(ChildTask task) {
        while (completedTaskCount < task.getTaskCount()) {
            Workflow.sleep(Duration.ofSeconds(10));
            completedTaskCount++;
        }
        totalPoints += task.getPoints();
        Workflow.returnFromWorkflow(totalPoints);
    }
}
