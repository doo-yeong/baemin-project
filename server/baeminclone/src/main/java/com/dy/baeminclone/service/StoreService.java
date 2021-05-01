package com.dy.baeminclone.service;

import com.dy.baeminclone.domain.Menu;
import com.dy.baeminclone.domain.Store;
import com.dy.baeminclone.repository.MenuRepository;
import com.dy.baeminclone.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
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
        makeAndSaveStore("심슨 곱창", "1", 30, 14000, 2000,
                new ArrayList<String>(Arrays.asList("양곱창","소곱창","곱창 전골")),
                new ArrayList<Integer>(Arrays.asList(12000, 14000, 25000)),
                "https://images.unsplash.com/photo-1476718406336-bb5a9690ee2a?ixid=MXwxMjA3fDB8MHxzZWFyY2h8NHx8Zm9vZHxlbnwwfHwwfA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60");

        makeAndSaveStore("에그 하우스", "1", 50, 9000, 3000,
                new ArrayList<String>(Arrays.asList("계란말이","계란찜","계란 후라")),
                new ArrayList<Integer>(Arrays.asList(4000, 3000, 2000)),
                "https://images.unsplash.com/photo-1476718406336-bb5a9690ee2a?ixid=MXwxMjA3fDB8MHxzZWFyY2h8NHx8Zm9vZHxlbnwwfHwwfA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60");

        makeAndSaveStore("성수 차이나", "2", 40, 14000, 2000,
                new ArrayList<String>(Arrays.asList("짜장면","볶음밥","짬뽕")),
                new ArrayList<Integer>(Arrays.asList(7000, 8000, 7000)),
                "https://images.unsplash.com/photo-1476718406336-bb5a9690ee2a?ixid=MXwxMjA3fDB8MHxzZWFyY2h8NHx8Zm9vZHxlbnwwfHwwfA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60");

        makeAndSaveStore("파스타 하우스", "3", 60, 20000, 3000,
                new ArrayList<String>(Arrays.asList("파스타","스테이크","와인")),
                new ArrayList<Integer>(Arrays.asList(17000, 28000, 37000)),
                "https://images.unsplash.com/photo-1476718406336-bb5a9690ee2a?ixid=MXwxMjA3fDB8MHxzZWFyY2h8NHx8Zm9vZHxlbnwwfHwwfA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60");
    }

    private Store makeAndSaveStore(String name, String category, int time, int price, int tip, ArrayList<String> menuNameList, ArrayList<Integer> menuPriceList, String url) {
        Store store = Store.builder()
                .category(category)
                .name(name)
                .deliveryTime(time)
                .minPrice(price)
                .deliveryTip(tip)
                .build();

//        Menu menu1 = new Menu(store, "심슨 양곱창", 3000, "https://images.unsplash.com/photo-1476718406336-bb5a9690ee2a?ixid=MXwxMjA3fDB8MHxzZWFyY2h8NHx8Zm9vZHxlbnwwfHwwfA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60");
//        Menu menu2 = new Menu(store, "심슨 소곱창", 2000, "https://images.unsplash.com/photo-1484723091739-30a097e8f929?ixid=MXwxMjA3fDB8MHxzZWFyY2h8N3x8Zm9vZHxlbnwwfHwwfA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60");
//        Menu menu3 = new Menu(store, "곱창 전골", 6000, "https://images.unsplash.com/photo-1529042410759-befb1204b468?ixid=MXwxMjA3fDB8MHxzZWFyY2h8MTR8fGZvb2R8ZW58MHx8MHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60");
        ArrayList<Menu> menus = new ArrayList<>();

        for (int i = 0; i<menuNameList.size(); i++){
            menus.add(new Menu(store, menuNameList.get(i), menuPriceList.get(i), url));
        }

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
