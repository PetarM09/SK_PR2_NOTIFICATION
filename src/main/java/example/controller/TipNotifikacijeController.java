package example.controller;

import example.dto.TipNotifikacijeDTO;
import example.service.TipNotifikacijeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tip_notifikacije")
public class TipNotifikacijeController {

    private TipNotifikacijeService tipNotifikacijeService;

    public TipNotifikacijeController(TipNotifikacijeService tipNotifikacijeService) {
        this.tipNotifikacijeService = tipNotifikacijeService;
    }

    @PostMapping("/insert")
    public ResponseEntity<TipNotifikacijeDTO> saveNotificationType(@RequestBody @Validated TipNotifikacijeDTO tipNotifikacijeDTO) {
        return new ResponseEntity<>(tipNotifikacijeService.dodajTipNotifikacije(tipNotifikacijeDTO), HttpStatus.CREATED);
    }


}