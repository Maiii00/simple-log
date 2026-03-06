package io.github.maiii00.simple_log_example.consumer;

import io.github.maiii00.simple_log.model.LogData;
import io.github.maiii00.simple_log_example.model.LogEntity;
import io.github.maiii00.simple_log_example.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    @Autowired
    private LogRepository logRepository;

    @KafkaListener(topics = "logs", groupId = "log-group")
    public void listen(LogData logData) {
        LogEntity entity = new LogEntity();

        entity.setDescription(logData.getDescription());
        entity.setMethodName(logData.getMethodName());
        entity.setExecutionTime(logData.getExecutionTime());
        entity.setCreateTime(logData.getCreateTime());
        entity.setSuccess(logData.isSuccess());
        entity.setErrorMsg(logData.getErrorMsg());
        entity.setArgs(logData.getArgs());

        logRepository.save(entity);
    }
}
