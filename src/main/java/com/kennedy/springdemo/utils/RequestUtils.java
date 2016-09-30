package com.kennedy.springdemo.utils;

import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public final class RequestUtils {
    public static String getRelativeRequestURLWithQueryString(HttpServletRequest request) {
        StringBuilder builder = new StringBuilder(getRelativeRequestURL(request));
        addQueryString(builder, request);
        return builder.toString();
    }

    public static String getRelativeRequestURL(HttpServletRequest request) {
        String forwardPath = (String) request.getAttribute("javax.servlet.forward.path_info");
        if (forwardPath != null)
            return forwardPath;
        return request.getPathInfo();
    }

    public static String getRequestURLWithQueryString(HttpServletRequest request) {
        StringBuilder builder = new StringBuilder(request.getRequestURL());
        addQueryString(builder, request);
        return builder.toString();
    }

    private static String getQueryString(HttpServletRequest request) {
        String forwardQueryString = (String) request.getAttribute("javax.servlet.forward.query_string");
        if (forwardQueryString != null)
            return forwardQueryString;
        return request.getQueryString();
    }

    private static void addQueryString(StringBuilder builder, HttpServletRequest request) {
        String queryString = getQueryString(request);
        if (StringUtils.isBlank(queryString))
            builder.append('?').append(queryString);
    }

    public static String getClientRelativeRequestURLWithQueryString(HttpServletRequest request) {
        StringBuilder builder = new StringBuilder(getClientRelativeRequestURL(request));
        addQueryString(builder, request);
        return builder.toString();
    }

    public static String getClientRelativeRequestURL(HttpServletRequest request) {
        String forwardPath = (String) request.getAttribute("javax.servlet.forward.path_info");
        if (forwardPath != null)
            return forwardPath;
        forwardPath = request.getPathInfo();
        if (forwardPath != null)
            return forwardPath;
        if ("/".equals(request.getContextPath()))
            return request.getRequestURI();
        return request.getRequestURI().replace(request.getContextPath(), "");
    }

    @SuppressWarnings("rawtypes")
    public static Map<String, Object> getRequestParams(HttpServletRequest request) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        Enumeration e = request.getParameterNames();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            map.put(key, request.getParameter(key));
        }
        return map;
    }
}
