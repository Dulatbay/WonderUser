package kz.wonder.wonderuser.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
public class DbObjectNotFoundException extends Exception {
    private HttpStatus httpStatus;
    private String message;
}
