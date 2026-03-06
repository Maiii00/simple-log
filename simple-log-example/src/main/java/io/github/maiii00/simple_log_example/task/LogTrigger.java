package io.github.maiii00.simple_log_example.task;

import io.github.maiii00.simple_log.annotation.AutoLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class LogTrigger {
    @Autowired
    private LogTestFunction logTestFunction;

    @AutoLog
    @Scheduled(fixedRate = 60000) // per 1 minute
    public void runTask() throws InterruptedException {
        logTestFunction.processData(String.valueOf(Math.random()));
    }
}
