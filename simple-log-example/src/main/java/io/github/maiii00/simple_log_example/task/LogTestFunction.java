package io.github.maiii00.simple_log_example.task;

import io.github.maiii00.simple_log.annotation.AutoLog;
import org.springframework.stereotype.Component;

@Component
public class LogTestFunction {
    @AutoLog
    public void processData(String id) throws InterruptedException {
        Thread.sleep(100);
    }
}
