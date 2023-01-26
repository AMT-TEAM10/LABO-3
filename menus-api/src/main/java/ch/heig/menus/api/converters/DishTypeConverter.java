package ch.heig.menus.api.converters;

import org.openapitools.model.DishType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DishTypeConverter implements Converter<String, DishType> {
    @Override
    public DishType convert(String value) {
        return DishType.valueOf(value.toUpperCase());
    }
}
