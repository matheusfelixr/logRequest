package com.matheusfelixr.logRequest.repository;

import com.matheusfelixr.logRequest.domain.RequestTraceLog;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface RequestTraceLogRepository extends ReactiveMongoRepository<RequestTraceLog, String>{

}
