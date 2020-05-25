package io.dicedev.pantry.domain.validate;

import io.dicedev.pantry.domain.dto.ProductDto;
import org.springframework.stereotype.Component;

import java.security.InvalidKeyException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ProductValidatorImpl implements ProductValidator {

    @Override
    public void isValid(ProductDto productDto) throws InvalidKeyException {
        Pattern pattern = Pattern.compile("^[A-Z].*$");
        Matcher matcher = pattern.matcher(productDto.getName());
        if (!matcher.matches()) {
            throw new InvalidKeyException("Nazwa z wielkiej litery");
        }
    }
}
