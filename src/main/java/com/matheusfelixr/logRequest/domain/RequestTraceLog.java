package com.matheusfelixr.logRequest.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Document
public class RequestTraceLog implements Serializable {

	private static final long serialVersionUID = 4166091231231451385L;

	@Id
	private Long id;


	private String application;
	

	private String method;


	private String path;


	private String userAgent;


	private String userAddress;


	private String userMail;


	private Integer requestTime;


	private Integer responseStatus;
	

	private Date date;
	
	public RequestTraceLog() {
	}
}