//package me.wonwoo.advice;
//
//import java.io.IOException;
//import java.nio.charset.Charset;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//
//import me.wonwoo.exception.BadRequestException;
//import me.wonwoo.exception.DataNotFoundException;
//import me.wonwoo.exception.DuplicateException;
//
//@ControllerAdvice
//@RestController
//public class GlobalException {
//
//	@ExceptionHandler(DuplicateException.class)
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	public void handleDuplicateKeyException(HttpServletResponse response, DuplicateException e) throws IOException {
//		response.sendError(HttpStatus.BAD_REQUEST.value(), e.getKey() + " duplicate key");
//	}
//
//	@ExceptionHandler(DataNotFoundException.class)
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	public void handleAccountNotFoundException(HttpServletResponse response, DataNotFoundException e)
//			throws IOException {
//		response.sendError(HttpStatus.BAD_REQUEST.value(), e.getId() + " no Data");
//	}
//
//	@ExceptionHandler(BadRequestException.class)
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	public void handleBadRequestException(HttpServletResponse response, BadRequestException e) throws IOException {
//		response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
//	}
//
//	@ExceptionHandler(Exception.class)
//	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//	public void handleException(HttpServletResponse response, Exception e) throws IOException {
//		response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
//	}
//
//	@ExceptionHandler(NullPointerException.class)
//	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//	public void handleNullPointerException(HttpServletResponse response, NullPointerException e) throws IOException {
//		response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "internal server error null pointer");
//	}
//}
