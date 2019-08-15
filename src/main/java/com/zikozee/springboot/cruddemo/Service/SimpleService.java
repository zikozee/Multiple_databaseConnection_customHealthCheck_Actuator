package com.zikozee.springboot.cruddemo.Service;

import com.zikozee.springboot.cruddemo.customHealthCheck.Code;
import org.springframework.stereotype.Service;

@Service
public class SimpleService {

    //Checking status of this service
    public Code getStatus() {
        return Code.randomCode();
    }

    //More Methods here....

}