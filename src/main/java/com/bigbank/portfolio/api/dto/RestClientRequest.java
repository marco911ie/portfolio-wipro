package com.bigbank.portfolio.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestClientRequest<T> {

    private String uri;
    private Map<String, String> headers;
    private Map<String, String> requestParams;
    private HttpMethod methodType;
    private T entity;
    private String restAPIUrl;
    private int reponseCode = 200;
    private String responseValue;
    private MediaType mediaType;
    private int timeOutMilliSecs;

}
