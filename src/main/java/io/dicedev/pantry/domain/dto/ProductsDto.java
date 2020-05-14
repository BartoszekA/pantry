package io.dicedev.pantry.domain.dto;

import java.util.Map;
import java.util.Objects;

public class ProductsDto {
    private Map<ProductDto, Integer> productsDto;

    public Map<ProductDto, Integer> getProductsDto() {
        return productsDto;
    }

    public void setProductsDto(Map<ProductDto, Integer> productsDto) {
        this.productsDto = productsDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductsDto)) return false;
        ProductsDto that = (ProductsDto) o;
        return Objects.equals(productsDto, that.productsDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productsDto);
    }

    @Override
    public String toString() {
        return "ProductsDto{" +
                "products=" + productsDto +
                '}';
    }
}
