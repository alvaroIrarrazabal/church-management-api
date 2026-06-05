package com.irarrazabal.iglesiaapi.application.member.dto;

import com.irarrazabal.iglesiaapi.domain.Enum.EcclesiasticalOffice;

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
