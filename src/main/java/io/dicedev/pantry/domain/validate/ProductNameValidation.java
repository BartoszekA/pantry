package io.dicedev.pantry.domain.validate;

import io.dicedev.pantry.domain.dto.ProductDto;
import io.dicedev.pantry.domain.enums.ProductValidatorEnum;
import io.dicedev.pantry.domain.exception.PantryProductNameException;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

import static java.util.function.Predicate.not;

@Component
public class ProductNameValidation implements ProductValidator {

    @Override
    public void isValid(ProductDto productDto) {
        var matcher = Pattern.compile("^[A-Z].*$").asMatchPredicate();
        if (not(matcher).test(productDto.getName())) {
            throw new PantryProductNameException(ProductValidatorEnum.PANTRY_PRODUCT_NAME_NO_SMALL_LETTER.name());
        }
    }
}
