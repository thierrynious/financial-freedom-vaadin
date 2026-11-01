package com.lameefu.service;

import org.springframework.stereotype.Service;

@Service
public class AppStatusService {

    public String getStatus(){
        return "bereit";
    }
}
