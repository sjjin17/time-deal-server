package com.timedeal_server.timedeal.domain.item.dto;

import com.timedeal_server.timedeal.domain.item.domain.Item;
import com.timedeal_server.timedeal.domain.item.domain.ItemImage;
import com.timedeal_server.timedeal.domain.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class ItemReqDTO {
    private String name;

    private int price;

    private int salePrice;

    private int stockQuantity;

    private String detail;

    private String startDate;

//    private MultipartFile titleImage;
//
//    private List<MultipartFile> imageList;



    @Builder
    public ItemReqDTO(String name, int price, int salePrice, int stockQuantity, String detail, String startDate) {
        this.name = name;
        this.price = price;
        this.salePrice = salePrice;
        this.stockQuantity = stockQuantity;
        this.detail = detail;
        this.startDate = startDate;
//        this.titleImage = titleImage;
//        this.imageList = imageList;
    }

    public static Item toEntity(ItemReqDTO itemReqDTO, String folderPath, String titleImage, User user) {
        return Item.builder()
                .name(itemReqDTO.getName())
                .price(itemReqDTO.getPrice())
                .salePrice(itemReqDTO.getSalePrice())
                .stockQuantity(itemReqDTO.getStockQuantity())
                .detail(itemReqDTO.getDetail())
                .startDate(LocalDateTime.parse(itemReqDTO.getStartDate()))
                .folderPath(folderPath)
                .titleImage(titleImage)
                .user(user)
                .build();

    }

}
