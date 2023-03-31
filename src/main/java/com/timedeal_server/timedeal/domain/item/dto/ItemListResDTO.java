package com.timedeal_server.timedeal.domain.item.dto;

import com.timedeal_server.timedeal.domain.item.domain.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class ItemListResDTO {
    private Long itemId;
    private String name;
    private int price;
    private int salePrice;
    private int stockQuantity;
    private String startDate;

    private String titleImage;

    @Builder
    public ItemListResDTO(Long itemId, String name, int price, int salePrice, int stockQuantity, String startDate, String titleImage) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.salePrice = salePrice;
        this.stockQuantity = stockQuantity;
        this.startDate = startDate;
        this.titleImage = titleImage;
    }


    public static ItemListResDTO toResDTO(Item item) {
        return ItemListResDTO.builder()
                .itemId(item.getItemId())
                .name(item.getName())
                .price(item.getPrice())
                .salePrice(item.getSalePrice())
                .stockQuantity(item.getStockQuantity())
                .startDate(item.getStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .titleImage(item.getTitleImage())
                .build();
    }
}
