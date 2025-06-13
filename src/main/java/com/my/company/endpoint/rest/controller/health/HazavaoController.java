package com.my.company.endpoint.rest.controller.health;

import com.my.company.service.HazavaoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hazavao")
public class HazavaoController {

    private final HazavaoService hazavaoService;

    public HazavaoController(HazavaoService hazavaoService) {
        this.hazavaoService = hazavaoService;
    }

    @GetMapping
    public String hazavao(@RequestParam String teny) {
        try {
            return hazavaoService.getDefinition(teny);
        } catch (Exception e) {
            return "Nisy olana: " + e.getMessage();
        }
    }
}
