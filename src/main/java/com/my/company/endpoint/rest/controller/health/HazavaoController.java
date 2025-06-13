package com.my.company.endpoint.rest.controller.health;

import com.my.company.service.HazavaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hazavao")
public class HazavaoController {

    private final HazavaoService hazavaoService;

    public HazavaoController(HazavaoService hazavaoService) {
        this.hazavaoService = hazavaoService;
    }

    @GetMapping
    public ResponseEntity<String> getDefinition(@RequestParam String teny) {
        String definition = hazavaoService.getDefinition(teny);
        return ResponseEntity.ok(definition);
    }
}
