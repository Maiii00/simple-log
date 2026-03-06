package io.github.maiii00.simple_log_example.service;

import io.github.maiii00.simple_log.model.LogData;
import io.github.maiii00.simple_log.service.LogStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaLogStorage implements LogStorageService {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void save(LogData logData) {
        kafkaTemplate.send("logs", logData.getMethodName(), logData);
    }
}
