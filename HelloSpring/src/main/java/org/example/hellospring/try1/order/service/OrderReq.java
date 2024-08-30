package org.example.hellospring.try1.order.service;

import java.math.BigDecimal;

public record OrderReq(String no, BigDecimal total) {
}
