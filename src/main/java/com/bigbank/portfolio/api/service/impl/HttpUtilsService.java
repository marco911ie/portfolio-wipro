package com.bigbank.portfolio.api.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.Arrays;

@Slf4j
@Component
public class HttpUtilsService {


    private RestTemplate restTemplate;

    public HttpUtilsService() {
        super();
    }

    @PostConstruct
    public void init() {

        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter
                .setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));

        restTemplate = new RestTemplate();

        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);

    }



    public <E> E makeRestCall(final String restApiUrl, final Class<E> responseType) throws Exception {

        final HttpMethod methodType = HttpMethod.GET;
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(restApiUrl);
        final URI uri = builder.build().encode().toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        LinkedMultiValueMap<String, String> multipartMap = new LinkedMultiValueMap<>();

        final HttpEntity<LinkedMultiValueMap<String, String>> request = new HttpEntity<>(multipartMap, headers);
        log.info("Calling endpoint {}", uri.toString());
        ResponseEntity<? extends E> httpResponse = restTemplate.exchange(uri, methodType, request, responseType);

        return httpResponse.getBody();

    }





}
