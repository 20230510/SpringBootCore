package com.mirusoft.common.core.api;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ErrorResponse {

    private int status;

    private String resultMessage;

    private List<ErrorDetail> errors = new ArrayList<>();
    private String reason;

    public int getStatus() {
        return status;
    }
    public String getResultMessage() {
        return resultMessage;
    }

    public List<ErrorDetail> getErrors() {
        return errors;
    }

    public String getReason() {
        return reason;
    }

    protected ErrorResponse(final HttpErrorCode errorCode) {

        this.resultMessage = errorCode.getMessage();
        this.status = errorCode.getStatus();
    }

    protected ErrorResponse(final HttpErrorCode errorCode, final String reason) {

        this.resultMessage = errorCode.getMessage();
        this.status = errorCode.getStatus();
        this.reason = reason;
    }

    protected ErrorResponse(final HttpErrorCode errorCode, final List<ErrorDetail> errors) {

        this.resultMessage = errorCode.getMessage();
        this.status = errorCode.getStatus();
        this.errors = errors;
    }

    /***************************************************************************************/

    public static ErrorResponse of(final HttpErrorCode errorCode) {

        return new ErrorResponse(errorCode);
    }


    public static ErrorResponse of(final HttpErrorCode errorCode, final BindingResult bindingResult) {

        return new ErrorResponse(errorCode, ErrorDetail.of(bindingResult));
    }

    public static ErrorResponse of(final HttpErrorCode errorCode, final String reason) {

        return new ErrorResponse(errorCode, reason);
    }

    /***************************************************************************************/

    public static class ErrorDetail {

        private final String field;

        private final String value;

        private final String reason;


        public static List<ErrorDetail> of(final String field, final String value, final String reason){

            List<ErrorDetail> errorDetails = new ArrayList<>();
            errorDetails.add(new ErrorDetail(field, value, reason));
            return errorDetails;
        }

        private static List<ErrorDetail> of(final BindingResult bindingResult){

            final List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            return fieldErrors.stream()
                    .map(error -> new ErrorDetail(
                            error.getField(),
                            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());

        }

        public ErrorDetail(String field, String value, String reason){
            this.field = field;
            this.value = value;
            this.reason = reason;
        }

        public String getField() {
            return field;
        }

        public String getValue() {
            return value;
        }

        public String getReason() {
            return reason;
        }
    }
}
