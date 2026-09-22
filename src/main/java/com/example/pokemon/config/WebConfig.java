package com.example.pokemon.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 允許 Angular 開發伺服器（http://localhost:4200）呼叫 /api/** 底下的 API。
 * 沒有這個設定，瀏覽器會因為跨網域（CORS）擋掉前端打過來的請求。
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // 只開放 /api 開頭的路由，網頁本身的路由不受影響
                .allowedOrigins("http://localhost:4200") // 只信任這個來源網址
                .allowedMethods("GET", "POST", "PUT", "DELETE"); // 允許前端使用的 HTTP 方法
    }
}
