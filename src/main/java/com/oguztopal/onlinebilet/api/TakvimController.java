package com.oguztopal.onlinebilet.api;

import com.oguztopal.onlinebilet.service.ITakvimImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/Takvim/*")
public class TakvimController {

    private final ITakvimImpl takvimService;

    public TakvimController(ITakvimImpl takvimService) {
        this.takvimService = takvimService;
    }
}
