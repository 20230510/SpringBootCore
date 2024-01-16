package com.mirusoft.common.core.api;


import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /*
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorDTO handleGenericException(HttpServletRequest request, Exception ex) {

		ErrorDTO error = new ErrorDTO();

		error.setTimestamp(new Date());
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.addError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		error.setPath(request.getServletPath());

		LOGGER.error(ex.getMessage(), ex);

		return error;
	}


	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		LOGGER.error(ex.getMessage(), ex);

		ErrorDTO error = new ErrorDTO();

		error.setTimestamp(new Date());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setPath(((ServletWebRequest) request).getRequest().getServletPath());

		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

		fieldErrors.forEach(fieldError -> {
			error.addError(fieldError.getDefaultMessage());
		});

		return new ResponseEntity<>(error, headers, status);
	}


	*/



    /*
     * RestControllerAdvice 와 ExceptionHandler 를 사용할 때 사용
     *
     * @ExceptionHandler(MethodArgumentNotValidException.class) protected
     * ResponseEntity<ErrorResponse>
     * handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
     *
     * LOGGER.error("handleMethodArgumentNotValidException", ex);
     *
     * BindingResult bindingResult = ex.getBindingResult();
     *
     * StringBuilder builder = new StringBuilder();
     *
     * for(FieldError fieldError : bindingResult.getFieldErrors()) {
     * builder.append(fieldError.getField()).append(":");
     * builder.append(fieldError.getDefaultMessage()).append(", "); }
     *
     * final ErrorResponse response =
     * ErrorResponse.of(HttpErrorCode.NOT_VALID_ERROR, String.valueOf(builder));
     *
     * return new ResponseEntity<>(response, HttpStatus.OK); }
     */


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        LOGGER.error("handleMethodArgumentNotValidException", ex);

        BindingResult bindingResult = ex.getBindingResult();

        final ErrorResponse response = ErrorResponse.of(HttpErrorCode.NOT_VALID_ERROR, bindingResult);

        return new ResponseEntity<>(response, headers, status);
    }


    /**
     * Header 값이 누락되거나 비어 있을 때
     */
    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        LOGGER.error("ServletRequestBindingException", ex);

        final ErrorResponse response = ErrorResponse.of(HttpErrorCode.REQUEST_HEADER_MISSING_ERROR, ex.getMessage());

        return new ResponseEntity<>(response, headers, status);
    }



    /**
     * Body 로 데이터가 넘어오지 않았을 경우
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        LOGGER.error("HttpMessageNotReadableException", ex);

        final ErrorResponse response = ErrorResponse.of(HttpErrorCode.BAD_REQUEST_ERROR, ex.getMessage());

        return new ResponseEntity<>(response, headers, status);

    }


    /**
     * 클라이언트가 요청한 HTTP 메소드가 서버에서 지원되지 않을 때
     */
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        LOGGER.error("HttpRequestMethodNotSupportedException", ex);

        final ErrorResponse response = ErrorResponse.of(HttpErrorCode.METHOD_NOT_ALLOWED, ex.getMessage());

        return new ResponseEntity<>(response, headers, status);
    }


    /**
     * 잘못된 주소로 요청 한 경우
     */
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        LOGGER.error("NoHandlerFoundException", ex);

        final ErrorResponse response = ErrorResponse.of(HttpErrorCode.NOT_FOUND_ERROR, ex.getMessage());

        return new ResponseEntity<>(response, headers, status);
    }


    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

        LOGGER.error("Exception", ex);


        final ErrorResponse response = ErrorResponse.of(HttpErrorCode.NOT_VALID_ERROR, ex.getMessage());

        return new ResponseEntity<>(response, headers, statusCode);
    }
}
