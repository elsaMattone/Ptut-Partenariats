package partenariats.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Duplicate Found")
public class DoublonsException extends Exception {
        private static final long serialVersionUID = 1L;

        public DoublonsException(String msg) {
                super(msg);
        }
}