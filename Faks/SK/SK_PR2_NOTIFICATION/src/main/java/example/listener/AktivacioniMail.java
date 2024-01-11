package example.listener;

import example.dto.*;
import example.helper.MailTekstFormater;
import example.helper.MessageHelper;
import example.service.impl.EmailServiceImpl;
import example.service.NotifikacijaService;
import example.service.TipNotifikacijeService;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.jms.JMSException;
import javax.jms.Message;
import java.sql.Date;
import java.time.LocalDate;

@Component
public class AktivacioniMail {
    private MessageHelper messageHelper;
    private NotifikacijaService notifikacijaService;
    private TipNotifikacijeService tipNotifikacijeService;
    private EmailServiceImpl emailService;
    private RestTemplate userServiceApiClient;


    public AktivacioniMail(MessageHelper messageHelper, NotifikacijaService notifikacijaService,
                           EmailServiceImpl emailService, RestTemplate userServiceApiClient, TipNotifikacijeService tipNotifikacijeService) {
        this.messageHelper = messageHelper;
        this.notifikacijaService = notifikacijaService;
        this.emailService = emailService;
        this.userServiceApiClient = userServiceApiClient;
        this.tipNotifikacijeService = tipNotifikacijeService;
    }
}