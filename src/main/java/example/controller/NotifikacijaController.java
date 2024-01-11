package example.controller;

import example.dto.NotifikacijaDTO;
import example.dto.NotifikacijeCreateDTO;
import example.security.CheckSecurity;
import example.security.service.TokenService;
import example.service.NotifikacijaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifikacija")
public class NotifikacijaController {
    private NotifikacijaService notifikacijaService;
    private TokenService tokenService;

    public NotifikacijaController(NotifikacijaService notifikacijaService, TokenService tokenService) {
        this.notifikacijaService = notifikacijaService;
        this.tokenService = tokenService;
    }

    @PostMapping("/insert")
    public ResponseEntity<NotifikacijaDTO> saveNotification(@RequestBody @Validated NotifikacijeCreateDTO notifikacijeCreateDTO) {
        return new ResponseEntity<>(notifikacijaService.dodajNotifikaciju(notifikacijeCreateDTO), HttpStatus.CREATED);
    }

    @GetMapping("/getNotificationsForClientId")
    public ResponseEntity<List<NotifikacijaDTO>> getNotificationsForClientId(@RequestHeader String authorization) {
        Long id = tokenService.parseId(authorization);
        return new ResponseEntity<>(notifikacijaService.getSveKorisniceNotifikacije(id), HttpStatus.OK);
    }

    @GetMapping("/getAllNotifications")
    @CheckSecurity(roles = {"ADMIN"})
    public ResponseEntity<List<NotifikacijaDTO>> getAllNotifications(@RequestHeader("Authorization") String authorization) {
        return new ResponseEntity<>(notifikacijaService.getSveNotifikacije(), HttpStatus.OK);
    }
}