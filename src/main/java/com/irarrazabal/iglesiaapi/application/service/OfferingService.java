package com.irarrazabal.iglesiaapi.application.service;

import com.irarrazabal.iglesiaapi.application.dto.offering.CreateOfferingRequest;
import com.irarrazabal.iglesiaapi.application.dto.offering.OfferingMonthlySummaryResponse;
import com.irarrazabal.iglesiaapi.application.dto.offering.OfferingResponse;
import com.irarrazabal.iglesiaapi.application.dto.offering.OfferingSummaryResponse;
import com.irarrazabal.iglesiaapi.application.dto.offering.UpdateOfferingRequest;
import com.irarrazabal.iglesiaapi.domain.model.Offering;
import com.irarrazabal.iglesiaapi.domain.repository.OfferingRepository;
import com.irarrazabal.iglesiaapi.exceptions.OfferingNotFoundException;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OfferingService {

    private final OfferingRepository offeringRepository;

    public OfferingService(OfferingRepository offeringRepository) {
        this.offeringRepository = offeringRepository;
    }

    @Transactional(readOnly = true)
    public List<OfferingResponse> findAllOfferings() {

        return offeringRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }



    @Transactional(readOnly = true)
    public OfferingResponse findById(Long id) {

        Offering offering = offeringRepository.findById(id)
                .orElseThrow(() ->
                        new OfferingNotFoundException(
                                "La ofrenda no fue encontrada"
                        ));

        return toResponse(offering);
    }
@Transactional
    public OfferingResponse createOffering(
            CreateOfferingRequest request
    ) {

        Offering offering = new Offering();

        offering.setServiceDate(request.serviceDate());
        offering.setAmount(request.amount());
        offering.setNote(request.note());

        Offering saved = offeringRepository.save(offering);

        return toResponse(saved);
    }
@Transactional
    public OfferingResponse updateOffering(
            Long id,
            UpdateOfferingRequest request
    ) {

        Offering offering = offeringRepository.findById(id)
                .orElseThrow(() ->
                        new OfferingNotFoundException(
                                "La ofrenda no fue encontrada"
                        ));

        offering.setServiceDate(request.serviceDate());
        offering.setAmount(request.amount());
        offering.setNote(request.note());

        Offering updated = offeringRepository.save(offering);

        return toResponse(updated);
    }
@Transactional
    public void deleteOffering(Long id) {

        if (!offeringRepository.existsById(id)) {
            throw new OfferingNotFoundException(
                    "La ofrenda no existe"
            );
        }

        offeringRepository.deleteById(id);
    }


    @Transactional(readOnly = true)
    public List<OfferingResponse> findByServiceDate(
            LocalDate serviceDate
    ) {

        return offeringRepository.findByServiceDate(serviceDate)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public OfferingSummaryResponse getSummary() {

        return new OfferingSummaryResponse(
                offeringRepository.getTotalAmount(),
                offeringRepository.count()
        );
    }
    @Transactional(readOnly = true)
    public OfferingMonthlySummaryResponse getMonthlySummary(
            int year,
            int month
    ) {

        LocalDate startDate = LocalDate.of(year, month, 1);

        LocalDate endDate = startDate.withDayOfMonth(
                startDate.lengthOfMonth()
        );

        return new OfferingMonthlySummaryResponse(
                year,
                month,
                offeringRepository.getTotalAmountByDateRange(
                        startDate,
                        endDate
                ),
                offeringRepository.countByServiceDateBetween(
                        startDate,
                        endDate
                )
        );
    }

    private OfferingResponse toResponse(
            Offering offering
    ) {

        return new OfferingResponse(
                offering.getId(),
                offering.getServiceDate(),
                offering.getAmount(),
                offering.getNote()
        );
    }
}