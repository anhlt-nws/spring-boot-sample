package vn.nws.sample.service.common;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class FieldViolation {
    private String field;
    private String description;
    private Integer code;
    private Long id;
    private List<FieldViolation> subFields;

    public FieldViolation(String field, List<FieldViolation> subFields) {
        this.field = field;
        this.subFields = subFields;
    }

    public FieldViolation(String field, String description) {
        this.field = field;
        this.description = description;
    }

    public FieldViolation(String field, String description, Integer code) {
        this.field = field;
        this.description = description;
        this.code = code;
    }

    public FieldViolation(Long id, String description, List<FieldViolation> subFields) {
        this.id = id;
        this.description = description;
        this.subFields = subFields;
    }

    public FieldViolation(Long id, String field, String description, List<FieldViolation> subFields) {
        this.id = id;
        this.field = field;
        this.description = description;
        this.subFields = subFields;
    }

}