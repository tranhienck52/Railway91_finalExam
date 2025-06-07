package com.data.config;

//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ProblemDetail;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.ErrorResponse;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//@RestControllerAdvice
//public class ConfigGlobalException extends ResponseEntityExceptionHandler {
//    @ExceptionHandler({Exception.class})
//    public ResponseEntity<Object> handleException(Exception ex){
//        ErrorResponse errorResponse = new ErrorResponse() {
//            @Override
//            public HttpStatusCode getStatusCode() {
//                return null;
//            }
//
//            @Override
//            public ProblemDetail getBody() {
//                return null;
//            }
//        }
//    }
//}
