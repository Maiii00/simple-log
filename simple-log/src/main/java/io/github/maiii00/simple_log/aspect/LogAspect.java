package io.github.maiii00.simple_log.aspect;

import io.github.maiii00.simple_log.annotation.AutoLog;
import io.github.maiii00.simple_log.model.LogData;
import io.github.maiii00.simple_log.service.LogStorageService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Aspect
@Slf4j
public class LogAspect {
    private final LogStorageService storageService;

    public LogAspect(LogStorageService storageService) {
        this.storageService = storageService;
    }

    @Around("@annotation(autoLog)")
    public Object around(ProceedingJoinPoint joinPoint, AutoLog autoLog) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result;
        boolean isSuccess = false;
        String errorMsg = null;

        try {
            result = joinPoint.proceed();
            isSuccess = true;
            return result;
        } catch (Throwable e) {
            errorMsg = e.getMessage();
            throw e;
        } finally {
            long executionTime = System.currentTimeMillis() - startTime;

            Map<String, Object> argMap = getArgsMap(joinPoint);
            String description = autoLog.value().isEmpty() ?
                    joinPoint.getSignature().getName() : autoLog.value();

            LogData logData = LogData.builder()
                    .description(description)
                    .methodName(joinPoint.getSignature().getName())
                    .executionTime(executionTime)
                    .args(argMap)
                    .createTime(LocalDateTime.now())
                    .success(isSuccess)
                    .errorMsg(errorMsg)
                    .build();

            String status = logData.isSuccess() ? "SUCCESS" : "FAILED";
            if (isSuccess) {
                log.info("[Simple-Log] [{}] Method: {} | Time: {}ms | Args: {}",
                        status, logData.getMethodName(), logData.getExecutionTime(), logData.getArgs());
            } else {
                log.error("[Simple-Log] [{}] Method: {} | Time: {}ms | Args: {}",
                        status, logData.getMethodName(), logData.getExecutionTime(), logData.getArgs());
            }

            try {
                storageService.save(logData);
            } catch (Exception e) {
                System.err.println("[Simple-Log] Critical Error: Failed to save log! " + e.getMessage());
            }
        }
    }

    private Map<String, Object> getArgsMap(ProceedingJoinPoint joinPoint) {
        Map<String, Object> map = new LinkedHashMap<>();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String[] parameterNames = signature.getParameterNames();
        Object[] args = joinPoint.getArgs();

        if (parameterNames != null && args != null) {
            for (int i = 0; i < parameterNames.length; i++) {
                map.put(parameterNames[i], args[i]);
            }
        }
        return map;
    }
}
