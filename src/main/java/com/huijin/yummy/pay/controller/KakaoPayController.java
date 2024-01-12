package com.huijin.yummy.pay.controller;

import com.huijin.yummy.pay.dto.KakaoPayApproveDTO;
import com.huijin.yummy.pay.dto.KakaoPayReadyDTO;
import com.huijin.yummy.pay.service.KakaoPayService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public @ResponseBody KakaoPayReadyDTO kakaoPay(@RequestParam Map<String, Object> params, HttpSession session){ //세션 삭제 예정
        KakaoPayReadyDTO response = payService.kakaoPayReady(params);
        session.setAttribute("tid", response.getTid()); //세션 삭제 예정

        return response;
    }

    // 결제 성공
    @GetMapping("/pay/success")
    public String paySuccess(@RequestParam("pg_token") String pgToken, HttpSession session, Model model) { //세션 삭제 예정
        String tid = (String) session.getAttribute("tid"); //세션 삭제 예정
        KakaoPayApproveDTO response = payService.kakaoPayApprove(pgToken, tid);

        //요청 결과에 대해서 데이터 베이스에 저장 또는 업데이트한다
        payService.updatePaymentStatus(tid, "성공");
        model.addAttribute("payResult", "결제 성공");

        return "pay/payResultPage"; //추가 예정
    }

    // 결제 취소
    @GetMapping("/pay/cancel")
    public String payCancel(HttpSession session, Model model) { //세션 삭제 예정
        String tid = (String) session.getAttribute("tid"); //세션 삭제 예정

        //요청 결과에 대해서 데이터 베이스에 저장 또는 업데이트한다
        payService.updatePaymentStatus(tid, "취소");
        model.addAttribute("payResult", "결제 취소");

        return "pay/payResultPage"; //추가 예정
    }

    // 결제 실패
    @GetMapping("/pay/fail")
    public String payFail(HttpSession session, Model model) { //세션 삭제 예정
        String tid = (String) session.getAttribute("tid"); //세션 삭제 예정

        //요청 결과에 대해서 데이터 베이스에 저장 또는 업데이트한다
        payService.updatePaymentStatus(tid, "실패");
        model.addAttribute("payResult", "결제 실패");

        return "/pay/payResultPage"; //추가 예정
    }
}
