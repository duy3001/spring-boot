package com.project.shopapp.responses.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.shopapp.models.OrderDetail;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDetailResponse {

    private Long id;

    @JsonProperty("order_id")
    private Long orderID;

    @JsonProperty("product_id")
    private Long productID;

    private Float price;

    @JsonProperty("number_of_products")
    private int numberOfProducts;

    @JsonProperty("total_money")
    private Float totalMoney;

    private String color;

    public static OrderDetailResponse fromOrderDetail(OrderDetail orderDetail){
        return OrderDetailResponse.builder()
                .id(orderDetail.getId())
                .orderID(orderDetail.getOrder().getId())
                .productID(orderDetail.getProduct().getId())
                .price(orderDetail.getPrice())
                .numberOfProducts(orderDetail.getNumberOfProducts())
                .color(orderDetail.getColor())
                .totalMoney(orderDetail.getTotalMoney())
                .build();
    }
}
