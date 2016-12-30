package com.whut.smart.support.valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * 全局数据校验
 *
 * Created by null on 2016/12/30.
 */
@ControllerAdvice
public class GlobalValidAdvice {

    private static final Logger log = LoggerFactory.getLogger(GlobalValidAdvice.class);

    @Resource
    private MessageSource messageSource;

    /**
     * 处理MethodArgumentNotValidException异常
     * 当数据校验中 @Valid 后面没有跟着 BindingResult 时，Spring 会抛出 MethodArgumentNotValidException 异常
     * 因此，处理 MethodArgumentNotValidException 异常即可全局处理参数校验，而不需要每个方法中使用 BindingResult
     *
     * @param e MethodArgumentNotValidException，Spring会自动注入，包括原生的Servlet对象
     * @return 参数校验失败信息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, Object> validationHandler(MethodArgumentNotValidException e) {
        log.debug("参数校验异常处理");
        return processFieldErrors(e.getBindingResult());
    }

    /**
     * 处理字段错误
     *
     * @param result BindingResult，封装了错误信息
     * @return Map，自定义错误信息。例如：
     *          {"flag":false,"message":"字段校验失败","error":{"password":"长度需要在6和18之间","email":"必须是一个合法的邮件格式"}}
     *          error中的值为实体的属性
     */
    private Map<String, Object> processFieldErrors(BindingResult result) {
        final Map<String, Object> error = new HashMap<>();
        final Map<String, String> info = new HashMap<>();
        List<FieldError> fieldErrors = result.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            String field = fieldError.getField();
            String fieldErrorMessage = resolveFieldError(fieldError);
            log.debug("{} => {}", field, fieldErrorMessage);
            info.put(field, fieldErrorMessage);
        }
        error.put("flag", false);
        error.put("message", "字段校验失败");
        error.put("error", info);
        return error;
    }

    /**
     * 获取某个字段的错误信息
     *
     * @param fieldError FieldError
     * @return 字段错误信息
     *          NOTE：错误信息的优先级 大 => 小
     *                属性文件中定义 > 字段中校验规则指定的message属性中的值 > hibernate validation中定义的校验错误信息
     */
    private String resolveFieldError(FieldError fieldError) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(fieldError, locale);
    }
}
