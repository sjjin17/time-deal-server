package com.timedeal_server.timedeal.domain.item.dto;

import com.timedeal_server.timedeal.domain.item.domain.Item;
import com.timedeal_server.timedeal.domain.item.domain.ItemImage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemImageResDTO {
    private Long imgId;
    private String imgUrl;


    @Builder
    public ItemImageResDTO(Long imgId, String imgUrl) {
        this.imgId = imgId;
        this.imgUrl = imgUrl;
    }

    public static ItemImageResDTO toResponseDto(ItemImage itemImage) {
        return ItemImageResDTO.builder()
                .imgId(itemImage.getItemImageId())
                .imgUrl(itemImage.getImgUrl())
                .build();
    }
}
