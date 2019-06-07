package com.oguztopal.onlinebilet.api;


import com.oguztopal.onlinebilet.entity.Musteriler;
import com.oguztopal.onlinebilet.service.IMusterilerImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/musteriler")
public class MusterilerController {

    private final IMusterilerImpl musterilerService;

    public MusterilerController(IMusterilerImpl musterilerService) {
        this.musterilerService = musterilerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Musteriler> getMusteriById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(musterilerService.musteriGetirById(id));
    }

    @PostMapping()
    public ResponseEntity<Musteriler> saveKullanici(@Valid @RequestBody Musteriler musteriler){
        return ResponseEntity.ok(musterilerService.musteriEkle(musteriler));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> kullaniciSil(@PathVariable("id") Long id ){
        return ResponseEntity.ok(musterilerService.musteriSil(id));
    }
}
