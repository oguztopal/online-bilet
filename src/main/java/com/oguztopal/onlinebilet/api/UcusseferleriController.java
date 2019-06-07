package com.oguztopal.onlinebilet.api;

import com.oguztopal.onlinebilet.dto.UcusseferleriDto;
import com.oguztopal.onlinebilet.entity.Bilet;
import com.oguztopal.onlinebilet.entity.Kupon;
import com.oguztopal.onlinebilet.entity.Musteriler;
import com.oguztopal.onlinebilet.entity.Ucusseferleri;
import com.oguztopal.onlinebilet.service.IBiletImpl;
import com.oguztopal.onlinebilet.service.IUcusseferleriImpl;
import com.oguztopal.onlinebilet.util.VTUtil;
import com.sun.org.apache.xpath.internal.operations.Bool;
import jdk.internal.org.objectweb.asm.commons.Method;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.FormSubmitEvent;
import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/ucusseferleri")
@CrossOrigin(origins = "http://localhost:4200")
public class UcusseferleriController {

    private final IUcusseferleriImpl ucusseferleriService;

    public UcusseferleriController(IUcusseferleriImpl ucusseferleri) {
        this.ucusseferleriService = ucusseferleri;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ucusseferleri> getUcusSeferleri(@PathVariable("id") Long id) {
        Ucusseferleri ucusseferleri = ucusseferleriService.getUcusSeferi(id);
        return ResponseEntity.ok(ucusseferleri);
    }

    @GetMapping("/ucuslar")
    public ResponseEntity<List<UcusseferleriDto>> getUcusSeferleriByGidisDonus(@Valid @RequestBody UcusseferleriDto ucusseferleri) {
        List<UcusseferleriDto>  ucusseferleris = ucusseferleriService.getUcusSeferiByGidisAndDonus(ucusseferleri);
        return ResponseEntity.ok(ucusseferleris);
    }

    @GetMapping("/hepsinigetir")
    public ResponseEntity<List<Ucusseferleri>> butunSeferler(){
        return ResponseEntity.ok(ucusseferleriService.butunUcusSeferleri());
    }

    @GetMapping("/ucuslar1")
    public void ucusBilgileriGetir(HttpServletRequest request, HttpServletResponse response) throws IOException , ParseException {
        Long gidis = VTUtil.reqGetLong(request.getParameter("gidis"),null);
        Long donus = VTUtil.reqGetLong(request.getParameter("donus"),null);
        Boolean iptal = VTUtil.reqGetBoolean(request.getParameter("iptaldurumu"),null);
        Date kalkisTarihi = VTUtil.reqGetDate(request.getParameter("kalkisTarihi"),null,VTUtil.strDateFormatVademecum);
        List<UcusseferleriDto>  ucusseferleris = ucusseferleriService.gidisveDonusUcusSeferleri(gidis,donus,kalkisTarihi,iptal);
        JSONObject sendJson = new JSONObject();
        sendJson.put("data",ucusseferleris);
        response.getWriter().write(sendJson.toString());
    }

    @RequestMapping(path = "/kuponsorgula/{kupon}" , method = RequestMethod.PUT)
    public ResponseEntity<Kupon>  kupon (@PathVariable("kupon") String kupon) throws IOException {
        return ResponseEntity.ok(ucusseferleriService.kuponsorgula(kupon));
    }
}
