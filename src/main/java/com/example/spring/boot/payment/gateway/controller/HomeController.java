package com.example.spring.boot.payment.gateway.controller;

import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import com.razorpay.*;


@Controller
public class HomeController {
    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/pay")
    public String pay() {
        return "pay.html";
    }

    @PostMapping("/create_order")
    @ResponseBody
    public String createOrder(@RequestBody Map<String, Object> data) throws RazorpayException {
        System.out.println(data);
        int amt = Integer.parseInt(data.get("amount").toString());
        var client = new RazorpayClient("rzp_test_iGTU8Hpg1DEbgP", "8e5YKbljHBUMr5A4Ctix7LuY");
        JSONObject options = new JSONObject();
        options.put("amount", amt*100);
        options.put("currency", "INR");
        options.put("receipt", "txn_123456");
        Order order = client.Orders.create(options);
        System.out.println(order);
        return order.toString();
    }
}
