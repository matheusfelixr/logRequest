package com.matheusfelixr.logRequest.config.requestTraceLog;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.matheusfelixr.logRequest.domain.RequestTraceLog;
import com.matheusfelixr.logRequest.repository.RequestTraceLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.InMemoryTraceRepository;
import org.springframework.stereotype.Repository;



@Repository
public class CustomTraceRepository extends InMemoryTraceRepository {
	
	@Autowired
	private RequestTraceLogRepository requestTraceLogRepository;
	
    private static final Logger log = LoggerFactory.getLogger(CustomTraceRepository.class);

	
    public CustomTraceRepository() {
        super.setCapacity(200);
    }

    @Override
    public void add(Map<String, Object> map) {
        
    	try {
			if(!isCorsPreFlightRequest(map)) {
				super.add(map);
				this.requestTraceLogRepository.save(this.convertToEntity(map));
			}
		} catch (Exception e) {
			log.warn("Exception while saving log. "+e.toString());
		}
    	
    }
    
    @SuppressWarnings("unchecked")
	private RequestTraceLog convertToEntity(Map<String, Object> map) {
		
    	RequestTraceLog ret = new RequestTraceLog();
    	
    	Map<String, Object> headers = (Map<String, Object>) map.getOrDefault(ActuatorKey.HEADERS, new HashMap<>());
    	Map<String, Object> requestHeaders = (Map<String, Object>) headers.getOrDefault(ActuatorKey.REQUEST, new HashMap<>());
    	Map<String, Object> responseHeaders = (Map<String, Object>) headers.getOrDefault(ActuatorKey.RESPONSE, new HashMap<>());

    	ret.setApplication("API_APLICATIVO");
    	ret.setDate(new Date());
    	ret.setMethod((String) map.getOrDefault(ActuatorKey.METHOD, ""));
    	ret.setPath((String) map.getOrDefault(ActuatorKey.PATH, ""));
    	ret.setUserMail((String) map.getOrDefault(ActuatorKey.USER_PRINCIPAL, ""));
    	ret.setUserAddress((String) map.getOrDefault(ActuatorKey.REMOTE_ADDRESS, ""));
    	ret.setRequestTime( new Integer((String) map.getOrDefault(ActuatorKey.TIME_TAKEN, "0")));
    	ret.setResponseStatus( new Integer((String) responseHeaders.getOrDefault(ActuatorKey.RESPONSE_STATUS, "0")));
    	ret.setUserAgent((String) requestHeaders.getOrDefault(ActuatorKey.USER_AGENT, ""));
    	
    	
		return ret;
	}

	public static final String HEADERS ="headers";
	public static final String REQUEST ="request";
	public static final String USER_AGENT ="user-agent";
	public static final String ACCESS_CONTROL_REQUEST_METHOD ="access-control-request-method";
	public static final String ACCESS_CONTROL_REQUEST_HEADERS ="access-control-request-headers";
	public static final String TIME_TAKEN ="timeTaken";
    
	@SuppressWarnings("unchecked")
	private boolean isCorsPreFlightRequest(Map<String, Object> map) {
    	String requestMethod = (String) map.getOrDefault(ActuatorKey.METHOD, "");
    	Map<String, Object> headers = (Map<String, Object>) map.getOrDefault(ActuatorKey.HEADERS, new HashMap<>());
    	Map<String, Object> requestHeaders = (Map<String, Object>) headers.getOrDefault(ActuatorKey.REQUEST, new HashMap<>());
    	
    	return requestMethod.equalsIgnoreCase(HttpMethod.OPTIONS) 
    		   && requestHeaders.getOrDefault(ActuatorKey.ACCESS_CONTROL_REQUEST_METHOD, null) != null
    		   && requestHeaders.getOrDefault(ActuatorKey.ACCESS_CONTROL_REQUEST_HEADERS, null) != null;
    }
}