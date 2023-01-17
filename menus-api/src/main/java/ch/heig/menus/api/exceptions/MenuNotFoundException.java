package ch.heig.menus.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MenuNotFoundException extends RuntimeException {
    public MenuNotFoundException(Integer id) {
        super("Menu " + id + " non trouvée");
    }
}
