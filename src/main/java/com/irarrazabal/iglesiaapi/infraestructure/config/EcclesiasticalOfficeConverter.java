package com.irarrazabal.iglesiaapi.infraestructure.config;

import com.irarrazabal.iglesiaapi.domain.model.EcclesiasticalOffice;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EcclesiasticalOfficeConverter implements Converter<String, EcclesiasticalOffice> {

    @Override
    public EcclesiasticalOffice convert(String source) {
        return EcclesiasticalOffice.valueOf(source.trim().toUpperCase());
    }

}
