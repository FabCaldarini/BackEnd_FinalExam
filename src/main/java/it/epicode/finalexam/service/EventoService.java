package it.epicode.finalexam.service;

import it.epicode.finalexam.exception.NotFoundException;
import it.epicode.finalexam.model.Evento;
import it.epicode.finalexam.model.EventoRequest;
import it.epicode.finalexam.model.Utente;
import it.epicode.finalexam.repository.EventoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class EventoService {
    @Autowired
    private EventoRespository eventoRespository;

    @Autowired
    private UtenteService utenteService;

    public Page<Evento> cercaTuttiIBlogPosts(Pageable pageable){
        return  eventoRespository.findAll(pageable);
    }

    public Evento cercaEventoPerId(int id) throws NotFoundException{
        return eventoRespository.findById(id).
                orElseThrow(()->new NotFoundException("Evento con id="+id+" non trovato"));
    }

    public Evento salvaEvento(EventoRequest eventoRequest) throws NotFoundException{
        Utente utente = utenteService.getUtenteById(eventoRequest.getUtente_id());

        Evento evento = new Evento(
                eventoRequest.getDescrizione(),
                eventoRequest.getTitolo(),
                eventoRequest.getData(),
                eventoRequest.getLuogo(),
                eventoRequest.getNumeroPostiDisponibili());

        return eventoRespository.save(evento);

    }

    public Evento aggiornaEvento(int id, EventoRequest eventoRequest) throws NotFoundException{
        Evento evento = cercaEventoPerId(id);

        evento.setDescrizione(eventoRequest.getDescrizione());
        evento.setTitolo(eventoRequest.getTitolo());
        evento.setData(eventoRequest.getData());
        evento.setLuogo(eventoRequest.getLuogo());

        return eventoRespository.save(evento);
    }

    public Evento aggiornaListaPartecipanti(EventoRequest eventoRequest,int id)throws HttpClientErrorException.NotFound{
        Evento evento = cercaEventoPerId(id);
        Utente utente = utenteService.getUtenteById(eventoRequest.getUtente_id());
        evento.aggiungiUtente(utente);
        return eventoRespository.save(evento);
    }

    public void cancellaEvento(int id) throws NotFoundException{
        Evento post = cercaEventoPerId(id);
        eventoRespository.delete(post);
    }

}
