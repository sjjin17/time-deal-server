package com.timedeal_server.timedeal.domain.item.dto;

import com.timedeal_server.timedeal.domain.item.domain.Item;
import com.timedeal_server.timedeal.domain.item.domain.ItemImage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ItemDetailResDTO {
    private Long itemId;
    private String name;
    private int price;
    private int salePrice;
    private int stockQuantity;
    private String startDate;
    private String detail;

    private List<ItemImageResDTO> imageList;

    @Builder
    public ItemDetailResDTO(Long itemId, String name, int price, int salePrice, int stockQuantity, String startDate, String detail, List<ItemImageResDTO> imageList) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.salePrice = salePrice;
        this.stockQuantity = stockQuantity;
        this.startDate = startDate;
        this.detail = detail;
        this.imageList = imageList;
    }

    public static ItemDetailResDTO toResDTO(Item item) {
        return ItemDetailResDTO.builder()
                .itemId(item.getItemId())
                .name(item.getName())
                .price(item.getPrice())
                .salePrice(item.getSalePrice())
                .stockQuantity(item.getStockQuantity())
                .startDate(item.getStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .detail(item.getDetail())
                .imageList(item.getItemImageList().stream().map(itemImage -> ItemImageResDTO.toResponseDto(itemImage)).collect(Collectors.toList()))
                .build();
    }
}
