package io.github.maiii00.simple_log_example.repository;

import io.github.maiii00.simple_log_example.model.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<LogEntity, Long> {

}
