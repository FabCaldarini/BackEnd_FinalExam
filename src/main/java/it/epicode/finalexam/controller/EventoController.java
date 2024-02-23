package it.epicode.finalexam.controller;

import it.epicode.finalexam.exception.BadRequestException;
import it.epicode.finalexam.model.Evento;
import it.epicode.finalexam.model.EventoRequest;
import it.epicode.finalexam.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping("/eventi")
    public Page<Evento> getAll(Pageable pageable){

        return eventoService.cercaTuttiIBlogPosts(pageable);
    }
    @GetMapping("/eventi/{id}")
    public Evento getBlogPostById(@PathVariable int id){
        return eventoService.cercaEventoPerId(id);

    }
    @PostMapping("/eventi")
    public Evento saveBlogPost(@RequestBody @Validated EventoRequest eventoRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        return eventoService.salvaEvento(eventoRequest);
    }
    @PutMapping("/eventi/{id}")
    public Evento updateBlogPost(@PathVariable int id, @RequestBody @Validated EventoRequest eventoRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        return eventoService.aggiornaEvento(id, eventoRequest);
    }
    @PatchMapping("/eventi/patchlist/{id}")
    public Evento aggiungiUtenteAdEvento(@PathVariable int id, @RequestBody @Validated EventoRequest eventoRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }
        return eventoService.aggiornaListaPartecipanti(eventoRequest,id);
    }

    @DeleteMapping("/eventi/{id}")
    public void deleteBlogPost(@PathVariable int id){
        eventoService.cancellaEvento(id);
    }
}


