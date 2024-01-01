package com.huijin.yummy.pay.service;

import com.huijin.yummy.pay.dto.KakaoPayApproveDTO;
import com.huijin.yummy.pay.dto.KakaoPayReadyDTO;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class KakaoPayService {

@Value("${pay.admin-key}")
private String adminKey;

    public KakaoPayReadyDTO kakaoPay(Map<String, Object> params){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK "+adminKey); //발급받은 adminkey

        /*
         * 결제번호는 고유한 결제번호로 생성해줘야 한다.
         * 여기서는 임시로 그냥 KAO20230318001
         * */
        MultiValueMap<String, Object> payParams = new LinkedMultiValueMap<String, Object>();

        payParams.add("cid", "TC0ONETIME");
        payParams.add("partner_order_id", "KAO20230318001");
        payParams.add("partner_user_id", "kakaopayTest");
        payParams.add("item_name", params.get("item_name"));
        payParams.add("quantity", params.get("quantity"));
        payParams.add("total_amount", params.get("total_amount"));
        payParams.add("tax_free_amount", params.get("tax_free_amount"));
        payParams.add("approval_url", "http://localhost:8080/pay/success"); // 결제승인시 넘어갈 url
        payParams.add("cancel_url", "http://localhost:8080/pay/cancel"); // 결제취소시 넘어갈 url
        payParams.add("fail_url", "http://localhost:8080/pay/fail"); // 결제 실패시 넘어갈 url

        //카카오페이 결제준비 api 요청
        HttpEntity<Map> request = new HttpEntity<Map>(payParams, headers);
        RestTemplate template = new RestTemplate();
        String url = "https://kapi.kakao.com/v1/payment/ready";

        //요청결과
        KakaoPayReadyDTO res = template.postForObject(url, request, KakaoPayReadyDTO.class);

        /*
         * 요청결과에서 응답받은 tid 값을 데이터베이스에 저장하는 로직 추가
         * 주문번호랑-tid랑 연결하여 결제이력테이블로 관리?
         */
        return res;
    }

    public KakaoPayApproveDTO kakaoPayApprove(String pgToken, String tid){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK "+adminKey); //발급받은 adminkey
        headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, Object> payParams = new LinkedMultiValueMap<String, Object>();

        /*
         * 결제번호는 결제준비 api와 일치 하여야 한다.
         * tid 불러오는 로직 추가
         * */
        System.err.println("KakaoPayReadyDTO tid : ");
        //String tid = "1";
        payParams.add("cid", "TC0ONETIME");
        payParams.add("tid", tid);
        payParams.add("partner_order_id", "KAO20230318001");
        payParams.add("partner_user_id", "kakaopayTest");
        payParams.add("pg_token", pgToken);
        System.err.println("1");
        //카카오페이 결제요청 api 요청
        HttpEntity<Map> request = new HttpEntity<Map>(payParams, headers);
        System.err.println("2");
        RestTemplate template = new RestTemplate();
        String url = "https://kapi.kakao.com/v1/payment/approve";
        System.err.println("3");
        //요청결과
        KakaoPayApproveDTO res = template.postForObject(url, request, KakaoPayApproveDTO.class);
        System.err.println("4");
        return res;
    }
}
