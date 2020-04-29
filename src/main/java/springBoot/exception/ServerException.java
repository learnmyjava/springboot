package springBoot.exception;

import java.text.MessageFormat;

/**
 * 自定义异常类型
 * @author pyy
 **/
public class ServerException extends RuntimeException {

    //错误代码
    ResultCode resultCode;

    public ServerException(ResultCode resultCode){
        super(resultCode.message());
        this.resultCode = resultCode;
    }

    public ServerException(ResultCode resultCode, Object... args){
        super(resultCode.message());
        String message = MessageFormat.format(resultCode.message(), args);
        resultCode.setMessage(message);
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode(){
        return resultCode;
    }

}
