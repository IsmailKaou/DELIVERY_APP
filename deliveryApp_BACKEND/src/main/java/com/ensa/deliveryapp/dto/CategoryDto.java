package com.ensa.deliveryapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDto {
    private String name;
    private String description;
    private String imageUrl;
}
