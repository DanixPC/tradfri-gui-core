package com.marcguillem.tradfriguicore.Services.ControllerAdvice;

import com.marcguillem.tradfriguicore.Models.ErrorModel;
import com.marcguillem.tradfriguicore.Services.ConsoleLogTool.IConsoleLogToolService;
import com.marcguillem.tradfriguicore.Services.ControllerAdvice.Errors.GenericException;
import com.marcguillem.tradfriguicore.Services.ControllerAdvice.Errors.NotFoundException;
import com.marcguillem.tradfriguicore.Services.ControllerAdvice.Errors.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.lang.reflect.Type;

@ControllerAdvice
public class ControllerErrorHandler extends RequestBodyAdviceAdapter {

    @Autowired
    private IConsoleLogToolService consoleLogTool;

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorModel NotFoundException(NotFoundException e) {
        return this.setMessageBody(HttpStatus.NOT_FOUND, e);
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
    @ResponseBody
    public ErrorModel ValidationException(ValidationException e) {
        return this.setMessageBody(HttpStatus.PRECONDITION_FAILED, e);
    }

    @ExceptionHandler(GenericException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorModel GenericException(GenericException e) {
        return this.setMessageBody(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

    private ErrorModel setMessageBody(HttpStatus code, Exception exception) {
        ErrorModel errorModel = new ErrorModel();
        errorModel.setDescription(exception.getMessage());
        errorModel.setStackTrace(getStackTraceAsString(exception.getStackTrace()));
        errorModel.setStatus(code.toString());
        return errorModel;
    }

    private String getStackTraceAsString(StackTraceElement[] stackTraceElements) {
        String method = "";
        for (int i = 0; i < stackTraceElements.length; i++) {
            consoleLogTool.logError(this.getClass(), stackTraceElements[i].toString());
            method += stackTraceElements[i].toString() + " SEPARATOR ";
        }
        return method;
    }

}
