package com.oguztopal.onlinebilet.api;

import com.oguztopal.onlinebilet.entity.Kullanicilar;
import com.oguztopal.onlinebilet.service.IKullanicilarImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/kullanicilar")
public class KullanicilarController {

    private final IKullanicilarImpl kullanicilarService;

    public KullanicilarController(IKullanicilarImpl kullanicilarService) {
        this.kullanicilarService = kullanicilarService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Kullanicilar> getKullaniciById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(kullanicilarService.kullaniciGetirById(id));
    }

    @PostMapping()
    public ResponseEntity<Kullanicilar> saveKullanici(@Valid @RequestBody Kullanicilar kullanicilar){
        return ResponseEntity.ok(kullanicilarService.kullaniciEkle(kullanicilar));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> kullaniciSil(@PathVariable("id") Long id ){
        return ResponseEntity.ok(kullanicilarService.kullaniciSil(id));
    }

}
