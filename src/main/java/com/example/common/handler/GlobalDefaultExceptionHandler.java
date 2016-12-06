package com.example.common.handler;

import com.example.common.constant.CodeEnum;
import com.example.vo.common.ResponseVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理类,拦截所有RequestMapping注解方法抛出的异常
 * Created by liaoqianyang on 2016/10/27.
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseVO handleException(final HttpServletRequest request, final Exception e) {
        logger.error("========拦截Exception=======", e);
        String message = StringUtils.isBlank(e.getMessage()) ?  CodeEnum.FAILURE.getMessage() : e.getMessage();
        return ResponseVO.failure(CodeEnum.FAILURE.getCode(), message);
    }

}
