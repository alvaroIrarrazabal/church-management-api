package com.irarrazabal.iglesiaapi.application.dto.integrante;

import java.util.List;

public record IntegranteResponse(

    String nombre,
    String apellido,
    String correo,
    boolean activo,
    List<String> ministerios

) {
}
