package com.huijin.yummy.pay.service;

import com.huijin.yummy.pay.dto.KakaoPayApproveDTO;
import com.huijin.yummy.pay.dto.KakaoPayReadyDTO;

import com.huijin.yummy.pay.entity.Payment;
import com.huijin.yummy.pay.repository.PaymentRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final PaymentRepository paymentRepository;
    private final EntityManager entityManager;

    @Value("${pay.admin-key}")
    private String adminKey;

    @Autowired
    public KakaoPayService(PaymentRepository paymentRepository, EntityManager entityManager) {
        this.paymentRepository = paymentRepository;
        this.entityManager = entityManager;
    }

    /**
     * 결제 준비
     * 카카오페이 결제를 시작하기 위해 결제정보를 카카오페이 서버에 전달하고 결제 고유번호(TID)와 URL을 응답받는 단계입니다.
     *
     * 1. Secret key를 헤더에 담아 파라미터 값들과 함께 POST로 요청합니다.
     * 2. 요청이 성공하면 응답 바디에 JSON 객체로 다음 단계 진행을 위한 값들을 받습니다.
     * 3. 서버(Server)는 tid를 저장하고, 클라이언트는 사용자 환경에 맞는 URL로 리다이렉트(redirect)합니다.
     * */
    public KakaoPayReadyDTO kakaoPayReady(Map<String, Object> params){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK "+adminKey); //발급 받은 adminkey

        /*
         * 결제번호는 고유한 결제번호로 생성해줘야 한다.
         * */
        MultiValueMap<String, Object> payload = new LinkedMultiValueMap<String, Object>();
        payload.add("cid", "TC0ONETIME"); //가맹점 코드, 10자
        payload.add("partner_order_id", "yummy001"); //가맹점 주문번호, 최대 100자
        payload.add("partner_user_id", "TestUserId"); //가맹점 회원 id, 최대 100자
        payload.add("item_name", params.get("item_name")); //상품명, 최대 100자
        payload.add("item_code", params.get("item_code")); //상품코드, 최대 100자, 필수 X
        payload.add("quantity", params.get("quantity")); //상품 수량
        payload.add("total_amount", params.get("total_amount")); //상품 총액
        payload.add("tax_free_amount", params.get("tax_free_amount")); //상품 비과세 금액
        payload.add("approval_url", "http://localhost:8080/pay/success"); //결제 성공 시 redirect url, 최대 255자
        payload.add("cancel_url", "http://localhost:8080/pay/cancel"); //결제 취소 시 redirect url, 최대 255자
        payload.add("fail_url", "http://localhost:8080/pay/fail"); //결제 실패 시 redirect url, 최대 255자

        //카카오페이 결제준비 api 요청
        RestTemplate template = new RestTemplate();
        String url = "https://kapi.kakao.com/v1/payment/ready";
        HttpEntity<Map> request = new HttpEntity<Map>(payload, headers);

        //요청 결과
        KakaoPayReadyDTO response = template.postForObject(url, request, KakaoPayReadyDTO.class);

        /*
         * 요청결과에서 응답받은 tid 값을 데이터베이스에 저장하는 로직 추가
         */
        //TODO 하드코딩 수정할 것
        Payment payment = new Payment.Builder()
                        .memberId(1L)
                        .storeId(3L)
                        .productId(4L)
                        .tid(response.getTid())
                        .payerName("test")
                        .productName("콤비네이션 피자")
                        .quantity(1)
                        .paymentAmount(15000)
                        .paymentMethodType("card")
                        .pgCompany("KakaoPay")
                        .paymentStatus("요청")
                        .build();

        paymentRepository.save(payment);

        return response;
    }

    /**
     * 결제 승인
     * 사용자가 결제 수단을 선택하고 비밀번호를 입력해 결제 인증을 완료한 뒤, 최종적으로 결제 완료 처리를 하는 단계입니다.
     *
     * - 인증완료시 응답받은 pg_token과 tid로 최종 승인요청합니다.
     * - 결제 승인 API를 호출하면 결제 준비 단계에서 시작된 결제건이 승인으로 완료 처리됩니다.
     * - 결제 승인 요청이 실패하면 카드사 등 결제 수단의 실패 정보가 필요에 따라 포함될 수 있습니다.
     * */
    public KakaoPayApproveDTO kakaoPayApprove(String pgToken, String tid){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK "+adminKey); //발급 받은 adminkey
        headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, Object> payParams = new LinkedMultiValueMap<String, Object>();
        /*
         * tid 불러오는 로직 추가
         * */
        payParams.add("cid", "TC0ONETIME"); //가맹점 코드, 10자
        payParams.add("tid", tid); //결제 고유번호, 결제 준비 API 응답에 포함
        payParams.add("partner_order_id", "yummy001"); //가맹점 주문번호, 결제 준비 API 요청과 일치해야 함
        payParams.add("partner_user_id", "TestUserId"); //가맹점 회원 id, 결제 준비 API 요청과 일치해야 함
        payParams.add("pg_token", pgToken); //결제승인 요청을 인증하는 토큰, 사용자 결제 수단 선택 완료 시, approval_url로 redirection해줄 때 pg_token을 query string으로 전달

        //카카오페이 결제요청 api 요청
        RestTemplate template = new RestTemplate();
        String url = "https://kapi.kakao.com/v1/payment/approve";
        HttpEntity<Map> request = new HttpEntity<Map>(payParams, headers);

        //요청 결과
        KakaoPayApproveDTO response = template.postForObject(url, request, KakaoPayApproveDTO.class);

        return response;
    }

    //요청 결과에 따라 결제 상태 업데이트 ex)요청, 취소, 실패
    public void updatePaymentStatus(String tid, String status) {
        Payment payment = paymentRepository.findByTid(tid);
        /*
        find() 메소드는 주로 엔터티의 기본 키(primary key)를 사용하여 엔터티를 찾는 데에 사용된다.
        기본 키는 엔터티를 고유하게 식별하는 데 사용되는 값이어야함.
        */
//        Payment payment = entityManager.find(Payment.class, tid);

        //결제 상태 업데이트
        payment.setPaymentStatus(status);
        paymentRepository.save(payment);
    }
}
