package com.mirusoft.common.core.api;

public enum HttpErrorCode {

    /***************************************Global Exception *************************************************/

    BAD_REQUEST_ERROR(400, "Bad Request Exception"),

    /* @RequestBody 에 Header 데이터가 없다면 */
    REQUEST_HEADER_MISSING_ERROR(400, "Required request Header is missing"),

    /* @RequestBody 에 Body 데이터가 없다면 */
    REQUEST_BODY_MISSING_ERROR(400, "Required request body is missing"),

    /* 유효하지 않은 타입일때*/
    INVALID_TYPE_VALUE(400, "Invalid type value"),

    /* Request Parameter 로 데이터가 전달되지 않을 경우에 */
    MISSING_REQUEST_PARAMETER_ERROR(400, "Missing RequestParameter"),


    /* 권한이 없을 때 */
    FORBIDDEN_ERROR(403, "Forbidden Error"),

    /* 요청한 리소스가 없을 때*/
    NOT_FOUND_ERROR(404, "Not Found Exception"),

    /* Null Point Exception 발생 시 */
    NULL_POINT_ERROR(404, "Null Point Exception"),

    /* @RequestBody, @RequestParam, @PathVariable 값이 적절하지 않을 때*/
    NOT_VALID_ERROR(404, "Request Parameter Exception"),

    /* Header 값이 적절하지 않을 때 */
    NOT_VALID_HEADER_ERROR(404, "Http Header Parameter Exception"),

    /* Client 가 요청한 Http 메소드가 Server 에서 지원되지 않을 때 */
    METHOD_NOT_ALLOWED(405, "Http method is not supported"),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error Exception");

    /***************************************Custom Exception *************************************************/


    private final int status;

    private final String message;

    HttpErrorCode(final int status, final String message){
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
