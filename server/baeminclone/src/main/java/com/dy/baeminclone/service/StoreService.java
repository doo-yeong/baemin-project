package com.dy.baeminclone.service;

import com.dy.baeminclone.domain.Menu;
import com.dy.baeminclone.domain.Store;
import com.dy.baeminclone.repository.MenuRepository;
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
    private final MenuRepository menuRepository;

    public List<Store> getStoreListByCategory(String category) {
        List<Store> storeList = new ArrayList<>();
        storeList = storeRepository.getStoreListByCategory(category);

        return storeList.isEmpty() ? null : storeList;
    }

    public void loadStores() {
        makeAndSaveStore("store1", "1");
        makeAndSaveStore("store2", "2");
        makeAndSaveStore("store3", "2");
        makeAndSaveStore("store4", "1");
        makeAndSaveStore("store5", "3");

    }

    private Store makeAndSaveStore(String name, String category) {
        Store store = Store.builder()
                .category(category)
                .name(name)
                .build();
        Menu menu1 = new Menu(store, "menu 1", 3000, "https://images.unsplash.com/photo-1476718406336-bb5a9690ee2a?ixid=MXwxMjA3fDB8MHxzZWFyY2h8NHx8Zm9vZHxlbnwwfHwwfA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60");
        Menu menu2 = new Menu(store, "menu 2", 2000, "https://images.unsplash.com/photo-1484723091739-30a097e8f929?ixid=MXwxMjA3fDB8MHxzZWFyY2h8N3x8Zm9vZHxlbnwwfHwwfA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60");
        Menu menu3 = new Menu(store, "menu 3", 6000, "https://images.unsplash.com/photo-1529042410759-befb1204b468?ixid=MXwxMjA3fDB8MHxzZWFyY2h8MTR8fGZvb2R8ZW58MHx8MHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60");

        storeRepository.save(store);

        return store;
    }

    public List<Menu> getMenuListByStoreId(Long id) {
        Store store = storeRepository.getStoreById(id);
        return store == null ? null : store.getMenuList();
    }

    public List<Store> getAll() {
        return storeRepository.getStores();
    }
}
