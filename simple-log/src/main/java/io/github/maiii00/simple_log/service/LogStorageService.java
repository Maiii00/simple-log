package io.github.maiii00.simple_log.service;

import io.github.maiii00.simple_log.model.LogData;

public interface LogStorageService {
    void save(LogData logData);
}
