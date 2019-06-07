package com.oguztopal.onlinebilet.api;

        import com.oguztopal.onlinebilet.dto.UcusseferleriDto;
        import com.oguztopal.onlinebilet.entity.Bilet;
        import com.oguztopal.onlinebilet.entity.Musteriler;
        import com.oguztopal.onlinebilet.entity.Ucusseferleri;
        import com.oguztopal.onlinebilet.service.IBiletImpl;
        import com.oguztopal.onlinebilet.util.VTUtil;
        import org.json.JSONObject;
        import org.springframework.http.MediaType;
        import org.springframework.http.ResponseEntity;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.*;

        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import javax.validation.Valid;
        import java.io.IOException;
        import java.text.ParseException;
        import java.util.Date;
        import java.util.List;

@RestController
@RequestMapping("/bilet")
@CrossOrigin(origins = "http://localhost:4200")
public class BiletController {

    private final IBiletImpl biletService;

    public BiletController(IBiletImpl biletService) {
        this.biletService = biletService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bilet> getBilet(@PathVariable("id") Long id) {
        Bilet bilet = biletService.getBiletId(id);
        return ResponseEntity.ok(bilet);
    }
    @PostMapping("/biletkayit")
    public ResponseEntity<Bilet> biletOlustur(@Valid @RequestBody Bilet bilet){
        return ResponseEntity.ok(biletService.biletOlustur(bilet));
    }

    @PostMapping(value = "/biletkayit1")
    public void biletkayit(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        String gidis = VTUtil.reqGetString(request.getParameter("adsoyad"),null);
    }

    @PutMapping(value = "/biletiptal/{biletId}")
    public ResponseEntity<String>  biletiptal(@PathVariable("biletId") Long biletId) throws IOException, ParseException {
        String mesaj = biletService.biletIptal(biletId);
        return ResponseEntity.ok(mesaj);
    }

    @RequestMapping(path = "/pnrsorgula/{pnr}")
    public ResponseEntity<Bilet>  pnr (@PathVariable("pnr") String pnr) throws IOException, ParseException {
        return ResponseEntity.ok(biletService.pnrSorgulama(pnr));
    }
}
