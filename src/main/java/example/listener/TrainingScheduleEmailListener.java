package example.listener;

import example.dto.KorisniciDto;
import example.dto.NotifikacijeCreateDTO;
import example.dto.TipNotifikacijeDTO;
import example.dto.TrainingScheduleDto;
import example.helper.MailTekstFormater;
import example.helper.MessageHelper;
import example.service.NotifikacijaService;
import example.service.TipNotifikacijeService;
import example.service.impl.EmailServiceImpl;
import javassist.NotFoundException;
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
public class TrainingScheduleEmailListener {
    private MessageHelper messageHelper;
    private NotifikacijaService notifikacijaService;
    private TipNotifikacijeService tipNotifikacijeService;
    private EmailServiceImpl emailService;
    private RestTemplate userServiceApiClient;


    public TrainingScheduleEmailListener(MessageHelper messageHelper, NotifikacijaService notifikacijaService,
                           EmailServiceImpl emailService, RestTemplate userServiceApiClient, TipNotifikacijeService tipNotifikacijeService) {
        this.messageHelper = messageHelper;
        this.notifikacijaService = notifikacijaService;
        this.emailService = emailService;
        this.userServiceApiClient = userServiceApiClient;
        this.tipNotifikacijeService = tipNotifikacijeService;
    }

    @JmsListener(destination = "${destination.trainingScheduleNotification}", concurrency = "5-10")
    void sendCarRentNotificationEmail(Message message) throws JMSException, IllegalAccessException, NotFoundException {
        TrainingScheduleDto trainingScheduleDto = messageHelper.getMessage(message, TrainingScheduleDto.class);
        System.out.println("Zakazivanje treninga notifikacija");

        Long clientId = trainingScheduleDto.getKorisnikId();

        ResponseEntity<KorisniciDto> clientDto = userServiceApiClient.exchange("/client/" + clientId, HttpMethod.GET, null, KorisniciDto.class);
        String clientEmail = clientDto.getBody().getEmail();
        //String managerEmail = managerDto.getBody().getEmail();
        String firstName = clientDto.getBody().getIme();
        String lastName = clientDto.getBody().getPrezime();

        trainingScheduleDto.setIme(firstName);
        trainingScheduleDto.setPrezime(lastName);

        TipNotifikacijeDTO notificationTypeDto = tipNotifikacijeService.getTipoviNotifikacije(trainingScheduleDto.getTipNotifikacije());
        MailTekstFormater mailTextFormater = new MailTekstFormater();
        String mailMsg = mailTextFormater.formatirajTekst(notificationTypeDto.getMessage(), trainingScheduleDto);
        String type = trainingScheduleDto.getTipTreninga();

        emailService.sendSimpleMessage(clientEmail, type, mailMsg);
        //emailService.sendSimpleMessage(managerEmail,type, "Info about client rent: "+mailMsg);


        NotifikacijeCreateDTO createNotificationDto =
                new NotifikacijeCreateDTO(mailMsg,clientId,clientEmail, Date.valueOf(LocalDate.now()),new TipNotifikacijeDTO(type));
        notifikacijaService.dodajNotifikaciju(createNotificationDto);
    }
}