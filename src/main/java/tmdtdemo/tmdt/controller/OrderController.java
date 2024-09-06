package tmdtdemo.tmdt.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tmdtdemo.tmdt.dto.request.OrderRequest;
import tmdtdemo.tmdt.service.OrderService;
import tmdtdemo.tmdt.utils.BaseResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderService orderService;
    @PostMapping("/new")
    public ResponseEntity<BaseResponse> createNewOrder(@RequestBody OrderRequest request, HttpServletRequest httprequest){
        String rs = orderService.orderByUser(request, httprequest.getHeader("x-client-username"));
        BaseResponse baseResponse = BaseResponse.builder()
                .code(String.valueOf(HttpStatus.CREATED))
                .message(rs).build();

        return ResponseEntity.ok(baseResponse);
    }
}
