package com.my.company.endpoint.rest.controller.health;

import com.my.company.service.HazavaoSynonymeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hazavao/synonymes")
public class HazavaoSynonymeController {

    private final HazavaoSynonymeService service;

    public HazavaoSynonymeController(HazavaoSynonymeService service) {
        this.service = service;
    }

    @GetMapping
    public List<String> getSynonymes(@RequestParam String teny) throws Exception {
        return service.getSynonymes(teny);
    }
}
