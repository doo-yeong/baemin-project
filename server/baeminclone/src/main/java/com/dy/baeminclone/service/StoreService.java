package com.dy.baeminclone.service;

import com.dy.baeminclone.domain.Store;
import com.dy.baeminclone.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreService {
    private final StoreRepository storeRepository;

    public List<Store> getStoreListByCategory(String category) {
        List<Store> storeList = new ArrayList<>();
        storeList = storeRepository.getStoreListByCategory(category);

        return storeList.isEmpty() ? null : storeList;
    }

    public void loadStores() {
        Store store1 = Store.builder()
                .category("1")
                .name("store1")
                .build();

        Store store2 = Store.builder()
                .category("2")
                .name("store2")
                .build();

        Store store3 = Store.builder()
                .category("1")
                .name("store3")
                .build();

        Store store4 = Store.builder()
                .category("1")
                .name("store4")
                .build();

        Store store5 = Store.builder()
                .category("2")
                .name("store5")
                .build();

        storeRepository.save(store1);
        storeRepository.save(store2);
        storeRepository.save(store3);
        storeRepository.save(store4);
        storeRepository.save(store5);
    }
}
