package com.lameefu.service;

import org.springframework.stereotype.Service;

@Service
public class AppInfoService {

    private final AppStatusService appStatusService;

    public AppInfoService(AppStatusService appStatusService) {
        this.appStatusService = appStatusService;
    }
    public String getApplicationInfo() {
        String status = appStatusService.getStatus();

        return "Financial Freedom App - Spring Boot Backend: "+status;
    }
}
