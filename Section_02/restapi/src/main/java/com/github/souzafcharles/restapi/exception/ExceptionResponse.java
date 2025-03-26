package com.github.souzafcharles.restapi.exception;

import java.util.Date;

public record ExceptionResponse(Date timestamp, String message, String details) {
}