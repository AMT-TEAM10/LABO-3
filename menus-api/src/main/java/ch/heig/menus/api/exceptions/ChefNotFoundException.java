package ch.heig.menus.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ChefNotFoundException extends RuntimeException {
    public ChefNotFoundException(Integer id) {
        super("Chef " + id + " non trouvée");
    }
}
