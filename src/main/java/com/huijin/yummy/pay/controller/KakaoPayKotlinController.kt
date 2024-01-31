package com.huijin.yummy.pay.controller

import com.huijin.yummy.pay.dto.KakaoPayApproveDTO
import com.huijin.yummy.pay.dto.KakaoPayReadyDTO
import com.huijin.yummy.pay.service.KakaoPayService
import jakarta.servlet.http.HttpSession
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class KakaoPayKotlinController(private val kakaoPayService: KakaoPayService) {
    //== @Autowired lateinit var kakaoPayService: KakaoPayService

    // 결제 버튼 클릭 시 호출
    @GetMapping("/pay/ready")
    @ResponseBody
    fun kakaoPay(@RequestParam params: Map<String?, Any?>?, session: HttpSession): KakaoPayReadyDTO? {
        val response: KakaoPayReadyDTO = kakaoPayService.kakaoPayReady(params)
        session.setAttribute("tid", response.tid)

        return response
    }

    // 결제 성공
    @GetMapping("/pay/success")
    fun paySuccess(@RequestParam("pg_token") pgToken: String?, session: HttpSession, model: Model): String? { //세션 삭제 예정
        val tid = session.getAttribute("tid") as String //세션 삭제 예정
        val response: KakaoPayApproveDTO = kakaoPayService.kakaoPayApprove(pgToken, tid)

        //요청 결과에 대해서 데이터 베이스에 저장 또는 업데이트한다
        kakaoPayService.updatePaymentStatus(tid, "성공")
        model.addAttribute("payResult", "결제 성공")
        return "pay/payResultPage"
    }

    // 결제 취소
    @GetMapping("/pay/cancel")
    fun payCancel(session: HttpSession, model: Model): String? { //세션 삭제 예정
        val tid = session.getAttribute("tid") as String //세션 삭제 예정

        //요청 결과에 대해서 데이터 베이스에 저장 또는 업데이트한다
        kakaoPayService.updatePaymentStatus(tid, "취소")
        model.addAttribute("payResult", "결제 취소")
        return "pay/payResultPage"
    }

    // 결제 실패
    @GetMapping("/pay/fail")
    fun payFail(session: HttpSession, model: Model): String? { //세션 삭제 예정
        val tid = session.getAttribute("tid") as String //세션 삭제 예정

        //요청 결과에 대해서 데이터 베이스에 저장 또는 업데이트한다
        kakaoPayService.updatePaymentStatus(tid, "실패")
        model.addAttribute("payResult", "결제 실패")
        return "/pay/payResultPage"
    }
}