package io.github.maiii00.simple_log.config;

import io.github.maiii00.simple_log.aspect.LogAspect;
import io.github.maiii00.simple_log.service.LogStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(AutoConfiguration.class);

    @Bean
    @ConditionalOnMissingBean
    public LogAspect logAspect(LogStorageService storageService) {
        return new LogAspect(storageService);
    }

    @Bean
    @ConditionalOnMissingBean(LogStorageService.class)
    public LogStorageService defaultLogStorage() {
        return (logData) -> {
            String status = logData.isSuccess() ? "SUCCESS" : "FAILED";
            Object argsMap = logData.getArgs();

            if (logData.isSuccess()) {
                log.info("[Simple-Log] [{}] Method: {} | Time: {}ms | Args: {}",
                        status, logData.getMethodName(), logData.getExecutionTime(), argsMap);
            } else {
                log.error("[Simple-Log] [{}] Method: {} | Time: {}ms | Error: {} | Args: {}",
                        status, logData.getMethodName(), logData.getExecutionTime(), logData.getErrorMsg(), argsMap);
            }
        };
    }
}
