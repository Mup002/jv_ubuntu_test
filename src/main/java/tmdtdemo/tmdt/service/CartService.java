package tmdtdemo.tmdt.service;

import tmdtdemo.tmdt.dto.request.CartRequest;
import tmdtdemo.tmdt.dto.response.CartResponse;

import java.util.List;

public interface CartService {
    CartResponse addToCart(CartRequest cardRequest, String username);
    CartResponse getByUsername(String username);
    String cancelOrder(String username);
    List<CartResponse> getAllCart();
}
