package com.hk.prj.userbook.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorDetails {
    private LocalDateTime timeStamp;
    private String errorCode;
    private String errorDescription;
}
