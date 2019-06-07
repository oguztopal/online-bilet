package com.oguztopal.onlinebilet.api;

import com.oguztopal.onlinebilet.entity.Musteriler;
import com.oguztopal.onlinebilet.entity.Sirketler;
import com.oguztopal.onlinebilet.service.IMusterilerImpl;
import com.oguztopal.onlinebilet.service.ISirketlerImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sirketler")
@CrossOrigin(origins = "http://localhost:4200")
public class SirketlerController {

    private final ISirketlerImpl sirketlerService;

    public SirketlerController(ISirketlerImpl sirketlerService) {
        this.sirketlerService = sirketlerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sirketler> sirketGetirById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(sirketlerService.sirketGetirById(id));
    }

    @PostMapping()
    public ResponseEntity<Sirketler> sirketEkle(@Valid @RequestBody Sirketler sirketler){
        return ResponseEntity.ok(sirketlerService.sirketEkle(sirketler));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> sirketSil(@PathVariable("id") Long id ){
        return ResponseEntity.ok(sirketlerService.sirketSil(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Sirketler>> butunSirketler(){
        return ResponseEntity.ok(sirketlerService.getButunSirketler());
    }
}
