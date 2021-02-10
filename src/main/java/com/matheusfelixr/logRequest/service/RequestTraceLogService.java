package com.matheusfelixr.logRequest.service;

import com.matheusfelixr.logRequest.domain.Person;
import com.matheusfelixr.logRequest.domain.RequestTraceLog;
import com.matheusfelixr.logRequest.repository.PersonRepository;
import com.matheusfelixr.logRequest.repository.RequestTraceLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RequestTraceLogService {
	
	@Autowired
	private RequestTraceLogRepository requestTraceLogRepository;

	public Flux<RequestTraceLog> findAll() {
		return requestTraceLogRepository.findAll();
	}

	public Mono<RequestTraceLog> findById(String id) {
		return requestTraceLogRepository.findById(id);
	}

	public Mono<RequestTraceLog>  save(RequestTraceLog requestTraceLog) {
		return requestTraceLogRepository.save(requestTraceLog);
	}

}
