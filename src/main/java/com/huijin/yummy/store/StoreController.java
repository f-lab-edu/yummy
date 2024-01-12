package com.huijin.yummy.store;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/store")
public class StoreController {

    @GetMapping("/storeListPage")
    public String storeList() {
        return "store/storeListPage";
    }

    @GetMapping("/storeDetailPage")
    public String storeDetail() {
        return "store/storeDetailPage";
    }
}
