package io.github.maiii00.simple_log.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogData implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String description;
    private String methodName;
    private Long executionTime;
    private Map<String, Object> args;
    private LocalDateTime createTime;

    private boolean success;
    private String errorMsg;
}
