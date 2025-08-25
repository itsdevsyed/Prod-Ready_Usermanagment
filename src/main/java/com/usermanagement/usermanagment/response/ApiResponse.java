package com.usermanagement.usermanagment.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private int status;
    private String message;
    private T data;
    private String timestamp;
    private String path;



    public ApiResponse(HttpStatus httpStatus, String message,T data,String path){
        this.status = httpStatus.value();
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now().toString();
        this.path = path;
    }
    
}
