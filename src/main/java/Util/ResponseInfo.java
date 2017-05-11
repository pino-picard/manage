package Util;

/**
 * Created by caoxiao on 2017/5/9.
 */
public class ResponseInfo {

    private Object data;

    private boolean isSuccess;

    private String errorMsg;

    public ResponseInfo() {
    }

//    public ResponseInfo(String data, boolean isSuccess, String errorMsg) {
//        this.data = data;
//        this.isSuccess = isSuccess;
//        this.errorMsg = errorMsg;
//    }

    public void createSuccessResponse (Object data) {
        this.data = data;
        this.isSuccess = true;
        this.errorMsg = "";
    }

    public void createFailedResponse (Object data, String errorMsg) {
        this.data = data;
        this.isSuccess = false;
        this.errorMsg = errorMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
