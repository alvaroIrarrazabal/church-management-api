package com.irarrazabal.iglesiaapi.presentation.controller;

import com.irarrazabal.iglesiaapi.application.dto.MessageResponse;
import com.irarrazabal.iglesiaapi.application.dto.integrante.CreateIntegranteRequest;
import com.irarrazabal.iglesiaapi.application.dto.integrante.IntegranteResponse;
import com.irarrazabal.iglesiaapi.application.dto.integrante.UpdateIntegranteRequest;
import com.irarrazabal.iglesiaapi.application.service.IntegranteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class IntegranteController {


    private IntegranteService integranteService;

    public IntegranteController(IntegranteService integranteService) {
        this.integranteService = integranteService;
    }


    @PostMapping("/crearIntegrantes")
    @PreAuthorize("hasRole('ADMIN')")
    public IntegranteResponse  crearIntegrantes(@Valid @RequestBody CreateIntegranteRequest request) {

        return integranteService.crearIntegrante(request);

    }


    @GetMapping("/buscarporid/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','LIDER')")
    public  IntegranteResponse  buscarIntegrantes(@PathVariable Long id){
        return integranteService.buscarPorid(id);
    }

    @GetMapping("/listarTodos")
    @PreAuthorize("hasAnyRole('ADMIN','LIDER')")
    public List<IntegranteResponse> listarTodos(){
        return  integranteService.buscarTodos();
    }


    @PutMapping("/actualizarIntegrante/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public IntegranteResponse acualizarIntegrantes( @PathVariable Long id,@Valid @RequestBody UpdateIntegranteRequest request){

        return integranteService.actualizarIntegrante(id, request);
    }

    @DeleteMapping("eliminarIntegrante/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public MessageResponse eliminarIntegrantes(@PathVariable Long id){

         integranteService.eliminarIntegrante(id);

         return ResponseEntity.status(HttpStatus.OK)
                 .body( new MessageResponse("Eliminado correctamente")).getBody();
    }
}
