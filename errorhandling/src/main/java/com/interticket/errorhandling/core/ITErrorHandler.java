package com.interticket.errorhandling.core;


import com.interticket.errorhandling.core.model.ITErrorMessages;
import com.interticket.errorhandling.core.model.ITErrorObject;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class ITErrorHandler extends ResponseEntityExceptionHandler {

	public String serviceErrorCode = "00";

	@Override
	@ResponseBody
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<ITErrorMessages> itErrorMessages = new ArrayList<>();
		itErrorMessages.add(new ITErrorMessages("error_label", "Missing request parameter: " + ex.getParameterType() + ":" + ex.getParameterName()));
		return new ResponseEntity<>(new ITErrorObject(serviceErrorCode + "0001", "error", itErrorMessages), status);
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<ITErrorMessages> itErrorMessages = new ArrayList<>();
		itErrorMessages.add(new ITErrorMessages("error_label", "Missing path variable: " + ex.getVariableName()));
		return new ResponseEntity<>(new ITErrorObject(serviceErrorCode + "0002", "error", itErrorMessages), status);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<ITErrorMessages> itErrorMessages = new ArrayList<>();
		itErrorMessages.add(new ITErrorMessages("error_label", "Internal exception occured: " + ex.toString()));
		return new ResponseEntity<>(new ITErrorObject(serviceErrorCode + "0003", "error", itErrorMessages), status);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<ITErrorMessages> itErrorMessages = new ArrayList<>();
		itErrorMessages.add(new ITErrorMessages("error_label", "Request method not supported: " + ex.getMethod()));
		return new ResponseEntity<>(new ITErrorObject(serviceErrorCode + "0004", "error", itErrorMessages), status);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<ITErrorMessages> itErrorMessages = new ArrayList<>();
		itErrorMessages.add(new ITErrorMessages("error_label", "Content type is not supported: " + ex.getContentType()));
		return new ResponseEntity<>(new ITErrorObject(serviceErrorCode + "0005", "error", itErrorMessages), status);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<ITErrorMessages> itErrorMessages = new ArrayList<>();
		itErrorMessages.add(new ITErrorMessages("error_label", "Requested content type cannot be generated!"));
		return new ResponseEntity<>(new ITErrorObject(serviceErrorCode + "0006", "error", itErrorMessages), status);
	}

	@Override
	protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<ITErrorMessages> itErrorMessages = new ArrayList<>();
		itErrorMessages.add(new ITErrorMessages("error_label", "Fatal binding exception occured: " + ex.getMessage()));
		return new ResponseEntity<>(new ITErrorObject(serviceErrorCode + "0007", "error", itErrorMessages), status);
	}

	@Override
	protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<ITErrorMessages> itErrorMessages = new ArrayList<>();
		itErrorMessages.add(new ITErrorMessages("error_label", ex.getPropertyName() + " property cannot be converted because there isn't any suitable converter or editor for it."));
		return new ResponseEntity<>(new ITErrorObject(serviceErrorCode + "0008", "error", itErrorMessages), status);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<ITErrorMessages> itErrorMessages = new ArrayList<>();
		itErrorMessages.add(new ITErrorMessages("error_label", "Type mismatch error occured: " + ex.getMessage()));
		return new ResponseEntity<>(new ITErrorObject(serviceErrorCode + "0009", "error", itErrorMessages), status);
	}

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<ITErrorMessages> itErrorMessages = new ArrayList<>();
		itErrorMessages.add(new ITErrorMessages("error_label", "Http message is not readable: " + ex.getMessage()));
		return new ResponseEntity<>(new ITErrorObject(serviceErrorCode + "0009", "error", itErrorMessages), status);
    }

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<ITErrorMessages> itErrorMessages = new ArrayList<>();
		itErrorMessages.add(new ITErrorMessages("error_label", "Http message is not writeable: " + ex.getMessage()));
		return new ResponseEntity<>(new ITErrorObject(serviceErrorCode + "0011", "error", itErrorMessages), status);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<ITErrorMessages> itErrorMessages = new ArrayList<>();
		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
			itErrorMessages.add(new ITErrorMessages("error_label", error.getDefaultMessage()));
		}
		return new ResponseEntity<>(new ITErrorObject(serviceErrorCode + "0012", "error", itErrorMessages), HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<ITErrorMessages> itErrorMessages = new ArrayList<>();
		itErrorMessages.add(new ITErrorMessages("error_label", "Missing request part or request is not multipart/form-data type!"));
		return new ResponseEntity<>(new ITErrorObject(serviceErrorCode + "0013", "error", itErrorMessages), status);
	}

	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<ITErrorMessages> itErrorMessages = new ArrayList<>();
		itErrorMessages.add(new ITErrorMessages("error_label", "Fatal bindig exception occured: " + ex.getMessage()));
		return new ResponseEntity<>(new ITErrorObject(serviceErrorCode + "0014", "error", itErrorMessages), status);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<ITErrorMessages> itErrorMessages = new ArrayList<>();
		itErrorMessages.add(new ITErrorMessages("error_label", "No handler found error occured!"));
		return new ResponseEntity<>(new ITErrorObject(serviceErrorCode + "0015", "error", itErrorMessages), status);
	}

	@Override
	protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
		List<ITErrorMessages> itErrorMessages = new ArrayList<>();
		itErrorMessages.add(new ITErrorMessages("error_label", "Request timed out!"));
		return new ResponseEntity<>(new ITErrorObject(serviceErrorCode + "0016", "error", itErrorMessages), status);
	}

	@ExceptionHandler(value = ConstraintViolationException.class)
	public ResponseEntity<Object> ConstraintViolationExceptionHandler(ConstraintViolationException ex, WebRequest request) {
		String[] error_descriptions = ex.getMessage().split(",");
		List<ITErrorMessages> errors = new ArrayList<>();
		for(String error : error_descriptions) {
			String mess = error.substring(error.indexOf(".") + 1);
			errors.add(new ITErrorMessages("error_label", mess.substring(mess.indexOf(":") + 2)));
		}
		return new ResponseEntity<>(new ITErrorObject(serviceErrorCode + "0101", "error", errors), HttpStatus.BAD_REQUEST);
	}

	/*//feladata: kicsomagolja az ITFunctionException osztály storedException fieldjében alkotott fa struktúrából a nem ITFunctionException hibát (vagyis a valós hibát)
	public Exception prepareStoredException(ITFunctionException functionException){
		ITFunctionException currentFunctionException = functionException;
		Exception currentException;
		do{
			currentException = currentFunctionException.getStoredException();
			if(!(currentException instanceof ITFunctionException)){
				break;
			} else {
				currentFunctionException = (ITFunctionException) currentException;
			}
		} while (true);
		return currentException;
	}

	@ExceptionHandler(value = {ITFunctionException.class})
	@ResponseBody
	protected abstract ResponseEntity<?> handleITFunctionException(HttpServletRequest request, ITFunctionException e);

	protected abstract void createErrorResponse(HttpServletRequest request, ITFunctionException ex, String functionErrorCode);*/

}
