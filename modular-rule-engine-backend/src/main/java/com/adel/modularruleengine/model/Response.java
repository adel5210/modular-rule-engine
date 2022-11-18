package com.adel.modularruleengine.model;

import java.util.Map;

public record Response(String code,
                       String message,
                       Map<String, Object> data) {
}
