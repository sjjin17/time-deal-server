package com.timedeal_server.timedeal.domain.item.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class ItemImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="itemimage_id")
    private Long itemImageId;

    private String imgUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;


    @Builder
    public ItemImage(String imgUrl, Item item) {
        this.imgUrl = imgUrl;
        this.item = item;
    }

    public void updateImage(String imgUrl) {
        this.imgUrl = imgUrl;
    }

}
