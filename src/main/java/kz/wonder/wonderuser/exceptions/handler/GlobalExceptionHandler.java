package kz.wonder.wonderuser.exceptions.handler;

import kz.wonder.wonderuser.exceptions.DbObjectNotFoundException;
import kz.wonder.wonderuser.exceptions.dtos.ResponseErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.util.Arrays;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(DbObjectNotFoundException.class)
    public ResponseEntity<ResponseErrorDto> handlePositionNotFoundException(DbObjectNotFoundException ex) {
        log.error("DbObjectNotFoundException exception: ", ex);
        ResponseErrorDto errorResponse = new ResponseErrorDto(ex.getHttpStatus().getReasonPhrase(), ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseErrorDto> argumentExceptionHandler(IllegalArgumentException e) {
        log.error("Argument exception: ", e);
        var errorResponse = new ResponseErrorDto(HttpStatus.BAD_REQUEST.toString(), e.getMessage(), getStackTrace(e));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseErrorDto> handleValidationErrors(MethodArgumentNotValidException ex) {
        log.error("MethodArgumentNotValidException exception: ", ex);
        String error = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        ResponseErrorDto errorResponse = new ResponseErrorDto(HttpStatus.BAD_REQUEST.getReasonPhrase(), error, getStackTrace(ex));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ResponseErrorDto> handleIOException(IOException ex) {
        log.error("IOException exception: ", ex);
        ResponseErrorDto errorResponse = new ResponseErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), "Something get error...", getStackTrace(ex));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(FileUploadBase.FileSizeLimitExceededException.class)
    public ResponseEntity<ResponseErrorDto> handleFileSizeLimitExceededException(FileUploadBase.FileSizeLimitExceededException ex) {
        log.error("FileSizeLimitExceededException: ", ex);
        ResponseErrorDto errorResponse = new ResponseErrorDto(HttpStatus.BAD_REQUEST.getReasonPhrase(), "The field image exceeds its maximum permitted size of 100MB", getStackTrace(ex));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    private static String getStackTrace(Throwable e) {
        filterStackTracesByProjectPackage(e);
        filterStackTracesByProjectPackage(e.getCause());
        return ExceptionUtils.getStackTrace(e).trim();
    }

    private static void filterStackTracesByProjectPackage(Throwable ex) {
        if (ex == null) return;

        StackTraceElement[] stackTraces = Arrays.stream(ex.getStackTrace())
                .filter(se -> se.getClassName().startsWith("com."))
                .toArray(StackTraceElement[]::new);

        ex.setStackTrace(stackTraces);
    }

}
