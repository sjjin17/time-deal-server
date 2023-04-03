package com.timedeal_server.timedeal.domain.item.repository;

import com.timedeal_server.timedeal.domain.item.domain.ItemImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemImageRepository extends JpaRepository<ItemImage, Long> {
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(nativeQuery = true, value="delete from ItemImage where ItemImage.item_id=(:itemId)")
    void deleteAllByItemId(@Param("itemId") Long itemId);
    void deleteAllByItemItemId(Long itemId);

    @Query(nativeQuery = true, value="select imgUrl from ItemImage where ItemImage.item_id=(:itemId)")
    List<String> findByItemItemId(@Param("itemId") Long itemId);

}
