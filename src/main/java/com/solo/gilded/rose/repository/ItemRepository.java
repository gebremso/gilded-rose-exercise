package com.solo.gilded.rose.repository;

import com.solo.gilded.rose.entity.Item;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

@EnableJpaRepositories
public interface ItemRepository extends CrudRepository<Item, Long> {
    Item findByProduct_Name(String name);

    boolean existsByProduct_Name(String name);
}
