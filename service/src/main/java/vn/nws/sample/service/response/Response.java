package vn.nws.sample.service.response;

import java.util.List;

//import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.experimental.Accessors;
import vn.nws.sample.service.common.BusinessErrorCode;
import vn.nws.sample.service.common.FieldViolation;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
    public static final int OK_CODE = 200_000;
    private final Metadata meta = new Metadata();
    private T data;

    public static <T> Response<T> ofSucceeded(T data) {
        Response<T> response = new Response<>();
        response.data = data;
        response.meta.code = OK_CODE;
        return response;
    }

//    public static <T> Response<List<T>> ofSucceeded(Page<T> data) {
//        Response<List<T>> response = new Response<>();
//        response.data = data.getContent();
//        response.meta.code = OK_CODE;
//        response.meta.page = data.getPageable().getPageNumber();
//        response.meta.size = data.getPageable().getPageSize();
//        response.meta.total = data.getTotalElements();
//        return response;
//    }

    public static <T> ResponseEntity<Response<T>> ofFailedEntity(BusinessErrorCode errorCode) {
        return ofFailedEntity(errorCode, errorCode.getMessage(), errorCode.getErrors());
    }

    public static <T> ResponseEntity<Response<T>> ofFailedEntity(BusinessErrorCode errorCode, String message) {
        return ofFailedEntity(errorCode, message, errorCode.getErrors());
    }

    public static <T> ResponseEntity<Response<T>> ofFailedEntity(BusinessErrorCode errorCode, String message, List<FieldViolation> errors) {
        Response<T> response = new Response<>();
        response.meta.code = errorCode.getCode();
        response.meta.message = message;
        response.meta.errors = errors;
        return new ResponseEntity<>(response, errorCode.getHttpStatus());
    }
    
    public static <T> Response<T> ofFailed(BusinessErrorCode errorCode) {
    	return ofFailed(errorCode, errorCode.getMessage(), errorCode.getErrors());
    }
    
    public static <T> Response<T> ofFailed(BusinessErrorCode errorCode, String message) {
    	return ofFailed(errorCode, message, errorCode.getErrors());
    }
    
    public static <T> Response<T> ofFailed(BusinessErrorCode errorCode, String message, List<FieldViolation> errors) {
        Response<T> response = new Response<>();
        response.meta.code = errorCode.getCode();
        response.meta.message = errorCode.getMessage();
        response.meta.errors = errorCode.getErrors();
        return response;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Metadata {
        int code;
        Integer page;
        Integer size;
        Long total;
        List<FieldViolation> errors;
        String message;
    }
}
