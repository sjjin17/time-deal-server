package com.timedeal_server.timedeal.domain.item.dto;

import com.timedeal_server.timedeal.domain.item.domain.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemResDTO {
    private Long itemId;
    private String name;


    @Builder
    public ItemResDTO(Long itemId, String name) {
        this.itemId = itemId;
        this.name = name;
    }
    public static ItemResDTO toDto(Item item) {
        return ItemResDTO.builder()
                .itemId(item.getItemId())
                .name(item.getName())
                .build();
    }
}
