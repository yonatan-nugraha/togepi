package com.example.togepi.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Optional;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
@Order(1)
public class HttpFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(HttpFilter.class.getName());

    @Autowired
    private ObjectMapper objectMapper;

    private static final String REQUEST_FORMAT = "Request -> "
            + "method=%s; "
            + "uri=%s%s; "
            + "headers=%s; "
            + "body=%s";

    private static final String RESPONSE_FORMAT = "Response -> "
            + "httpStatus=%s; "
            + "headers=%s; "
            + "body=%s";

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        final ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        final ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        try {
            logger.info(getRequest(requestWrapper));
            filterChain.doFilter(requestWrapper, responseWrapper);
        } finally {
            logger.info(getResponse(responseWrapper));
            responseWrapper.copyBodyToResponse();
        }
    }

    private String getRequest(ContentCachingRequestWrapper requestWrapper) {
        final String method = Optional.ofNullable(requestWrapper.getMethod()).orElse("");
        final String uri = Optional.ofNullable(requestWrapper.getRequestURI()).orElse("");
        final String queryString = Optional.ofNullable(requestWrapper.getQueryString()).map(param -> new StringBuilder("?").append(param).toString()).orElse("");
        final String headers = Optional.ofNullable(getRequestHeaders(requestWrapper)).orElse("");
        final String body = Optional.ofNullable(getRequestBody(requestWrapper)).orElse("");

        return String.format(REQUEST_FORMAT, method, uri, queryString, headers, body);
    }

    private String getResponse(ContentCachingResponseWrapper responseWrapper) {
        final String httpStatus = Optional.ofNullable(String.valueOf(responseWrapper.getStatusCode())).orElse("");
        final String headers = Optional.ofNullable(getResponseHeaders(responseWrapper)).orElse("");
        final String body = Optional.ofNullable(getResponseBody(responseWrapper)).orElse("");

        return String.format(RESPONSE_FORMAT, httpStatus, headers, body);
    }

    private String getRequestHeaders(ContentCachingRequestWrapper requestWrapper) {
        final HttpHeaders requestHeaders = new HttpHeaders();
        final Enumeration headerNames = requestWrapper.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            final String headerName = (String) headerNames.nextElement();
            requestHeaders.add(headerName, requestWrapper.getHeader(headerName));
        }
        return requestHeaders.toString();
    }

    private String getRequestBody(ContentCachingRequestWrapper requestWrapper) {
        try {
            final String requestBody = IOUtils.toString(requestWrapper.getInputStream(), UTF_8);
            return objectMapper.readTree(requestBody).toString();
        } catch (Exception e) {
            logger.error("Error when reading request body {}", e);
            return "";
        }
    }

    private String getResponseHeaders(ContentCachingResponseWrapper responseWrapper) {
        final HttpHeaders responseHeaders = new HttpHeaders();
        for (String headerName : responseWrapper.getHeaderNames()) {
            responseHeaders.add(headerName, responseWrapper.getHeader(headerName));
        }
        return responseHeaders.toString();
    }

    private String getResponseBody(ContentCachingResponseWrapper responseWrapper) {
        try {
            final String responseBody = IOUtils.toString(responseWrapper.getContentInputStream(), UTF_8);
            return objectMapper.readTree(responseBody).toString();
        } catch (Exception e) {
            logger.error("Error when reading response body {}", e);
            return "";
        }
    }
}
