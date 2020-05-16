package io.dicedev.pantry.domain.dto;

import java.util.List;
import java.util.Objects;

public class ProductsDto {
    private List<ProductDto> productsDto;

    public List<ProductDto> getProductsDto() {
        return productsDto;
    }

    public void setProductsDto(List<ProductDto> productsDto) {
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
