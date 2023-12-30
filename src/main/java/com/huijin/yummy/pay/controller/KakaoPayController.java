package com.huijin.yummy.pay.controller;

import com.huijin.yummy.pay.dto.KakaoPayApproveDTO;
import com.huijin.yummy.pay.dto.KakaoPayReadyDTO;
import com.huijin.yummy.pay.service.KakaoPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class KakaoPayController {

    private final KakaoPayService payService;

    @Autowired
    public KakaoPayController(KakaoPayService payService) {
        this.payService = payService;
    }

    // 결제 버튼 클릭 시 호출
    @GetMapping("/pay/ready")
    public @ResponseBody KakaoPayReadyDTO kakaoPay(@RequestParam Map<String, Object> params){
        System.err.println("params = "+params);
        KakaoPayReadyDTO res = payService.kakaoPay(params);
        System.err.println("KakaoPayReadyDTO res = "+res);

        return res;
    }

    @GetMapping("/pay/success")
    public String Success(@RequestParam("pg_token") String pgToken) {
        KakaoPayApproveDTO res = payService.kakaoPayApprove(pgToken);
        System.err.println("KakaoPayApproveDTO res = "+res);
        /*
         * 요청 결과에 대해서 데이터 베이스에 저장 또는 업데이트 할 로직 추가
         * */

        return "/pay/success"; //추가 예정
    }
}
