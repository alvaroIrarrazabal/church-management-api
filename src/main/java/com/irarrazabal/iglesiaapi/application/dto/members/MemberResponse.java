package com.irarrazabal.iglesiaapi.application.dto.members;

import com.irarrazabal.iglesiaapi.domain.model.EcclesiasticalOffice;

import java.util.List;

public record MemberResponse(

    Long id,
    String name,
    String lastname,
    String email,
    Integer age,
    boolean asset,
    EcclesiasticalOffice ecclesiasticalOffice,
    List<String> ministry

) {
}
