package com.matheusfelixr.logRequest.controller;

import com.matheusfelixr.logRequest.domain.RequestTraceLog;
import com.matheusfelixr.logRequest.service.RequestTraceLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/request-trace-log")
public class RequestTraceLogController {


    @Autowired
    RequestTraceLogService requestTraceLogService;

    @PostMapping("/save")
    public Mono<RequestTraceLog> save(@RequestBody RequestTraceLog requestTraceLog){
        return requestTraceLogService.save(requestTraceLog);
    }

    @GetMapping("/find-all")
    public Flux<RequestTraceLog> findAll(){
        return requestTraceLogService.findAll();
    }
}
