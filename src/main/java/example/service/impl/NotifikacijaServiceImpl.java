package example.service.impl;

import example.domain.Notifikacija;
import example.dto.NotifikacijaDTO;
import example.dto.NotifikacijeCreateDTO;
import example.mapper.NotifikacijaMapper;
import example.repository.NotifikacijaRepository;
import example.service.NotifikacijaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class NotifikacijaServiceImpl implements NotifikacijaService {

    private NotifikacijaRepository notifikacijaRepository;
    private NotifikacijaMapper notifikacijaMapper;

    public NotifikacijaServiceImpl(NotifikacijaRepository notifikacijaRepository, NotifikacijaMapper notifikacijaMapper) {
        this.notifikacijaRepository = notifikacijaRepository;
        this.notifikacijaMapper = notifikacijaMapper;
    }


    @Override
    public NotifikacijaDTO dodajNotifikaciju(NotifikacijeCreateDTO createNotificationDto) {
        Notifikacija notifikacija = notifikacijaMapper.createNotifikacijaDTOToNotifikacije(createNotificationDto);
        notifikacijaRepository.save(notifikacija);
        return notifikacijaMapper.notifikacijaToNotifikacijaDTO(notifikacija);
    }

    @Override
    public List<NotifikacijaDTO> getSveKorisniceNotifikacije(Long id) {
        List<Notifikacija> listaNotifikacija = notifikacijaRepository.findNotifikacijaByKorisnikId(id);

        List<NotifikacijaDTO> listaDTONotifikacije = new ArrayList<>();

        for(Notifikacija notif : listaNotifikacija)
        {
            listaDTONotifikacije.add(notifikacijaMapper.notifikacijaToNotifikacijaDTO(notif));
        }

        return listaDTONotifikacije;
    }

    @Override
    public List<NotifikacijaDTO> getSveNotifikacije() {
        List<Notifikacija> listaNotifikacija = notifikacijaRepository.getAllNotifikacije();
        List<NotifikacijaDTO> listaDTONotifikacije = new ArrayList<>();

        for(Notifikacija notif : listaNotifikacija)
        {
            listaDTONotifikacije.add(notifikacijaMapper.notifikacijaToNotifikacijaDTO(notif));
        }

        return listaDTONotifikacije;
    }
}
