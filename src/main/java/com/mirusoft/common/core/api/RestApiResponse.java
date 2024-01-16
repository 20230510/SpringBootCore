package com.mirusoft.common.core.api;

public class RestApiResponse<T> {

    private T result;

    private int resultCode;

    private String resultMessage;

    public RestApiResponse(final T result, final int resultCode, final String resultMessage) {
        this.result = result;
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    public T getResult() {
        return result;
    }

    public int getResultCode() {
        return resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public static <T> RestApiResponseBuilder<T> builder(){
        return new RestApiResponseBuilder<>();
    }


    public static class RestApiResponseBuilder<T> {

        private T result;
        private int resultCode;
        private String resultMessage;

        public RestApiResponseBuilder<T> result(T result) {
            this.result = result;
            return this;
        }

        public RestApiResponseBuilder<T> resultCode(int resultCode){
            this.resultCode = resultCode;
            return this;
        }

        public RestApiResponseBuilder<T> resultMessage(String resultMessage){
            this.resultMessage = resultMessage;
            return this;
        }

        public RestApiResponse<T> build(){
            return new RestApiResponse<>(result, resultCode, resultMessage);
        }
    }
}
