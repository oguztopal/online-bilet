package com.oguztopal.onlinebilet.api;

        import com.oguztopal.onlinebilet.entity.Havalimanlari;
        import com.oguztopal.onlinebilet.entity.Sirketler;
        import com.oguztopal.onlinebilet.service.IHavalimanlariImpl;
        import org.json.JSONObject;
        import org.springframework.http.ResponseEntity;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.*;

        import javax.validation.Valid;
        import java.util.List;

@RestController
@RequestMapping(value = "/havalimanlari")
@CrossOrigin(origins = "http://localhost:4200")
public class HavalimanlariController {

    private final IHavalimanlariImpl havalimanlariService;

    public HavalimanlariController(IHavalimanlariImpl havalimanlari) {
        this.havalimanlariService = havalimanlari;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Havalimanlari> havalimaniGetirById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(havalimanlariService.havalimaniGetir(id));
    }

    @PostMapping()
    public ResponseEntity<Havalimanlari> havalimaniEkle(@Valid @RequestBody Havalimanlari havalimanlari){
        return ResponseEntity.ok(havalimanlariService.havalimaniEkle(havalimanlari));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> havalimaniEkle(@PathVariable("id") Long id ){
        return ResponseEntity.ok(havalimanlariService.havalimaniSil(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Havalimanlari>> getButunHavalimanlari(){
        return ResponseEntity.ok(havalimanlariService.getButunHavalimanlari());
    }
    @GetMapping("/havalimanlari")
    public JSONObject getButunHavalimanlariAdi(){
        JSONObject sendJson = new JSONObject();
        sendJson =  havalimanlariService.havalimanlari();
        return sendJson;
    }
}
