package vn.nws.sample.service.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.HttpStatus;

public class BusinessErrorCode implements Serializable {
    private static final long serialVersionUID = -2005206237438722822L;
    private final int code;
    private final String message;
    private HttpStatus httpStatus;
    private List<FieldViolation> errors;

    public BusinessErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public BusinessErrorCode(int code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.httpStatus = status;
    }

    public BusinessErrorCode(int code, String message, HttpStatus status, List<FieldViolation> errors) {
        this.code = code;
        this.message = message;
        this.httpStatus = status;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public List<FieldViolation>  getErrors() {
        return errors;
    }
}
