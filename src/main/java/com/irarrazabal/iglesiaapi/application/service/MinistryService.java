    package com.irarrazabal.iglesiaapi.application.service;

    import com.irarrazabal.iglesiaapi.application.dto.dashboard.DashboardMinistryCountResponse;
    import com.irarrazabal.iglesiaapi.application.dto.members.MemberResponse;
    import com.irarrazabal.iglesiaapi.application.dto.ministry.*;
    import com.irarrazabal.iglesiaapi.domain.model.Member;
    import com.irarrazabal.iglesiaapi.domain.model.Ministry;
    import com.irarrazabal.iglesiaapi.domain.repository.MemberRepository;
    import com.irarrazabal.iglesiaapi.domain.repository.MinistryRepository;
    import com.irarrazabal.iglesiaapi.exceptions.MinisterioNotFoundException;
    import org.springframework.stereotype.Service;

    import java.time.LocalDate;
    import java.time.Period;
    import java.util.List;

    @Service
    public class MinistryService {


        private final MinistryRepository ministryRepository;
        private final MemberRepository memberRepository;

        public MinistryService(MinistryRepository ministryRepository, MemberRepository memberRepository) {
            this.ministryRepository = ministryRepository;
            this.memberRepository = memberRepository;
        }




        public List<MinistryResponse> findAllMinisteries(){

            return ministryRepository.findAll()
                    .stream()
                    .map(this::toResponse)
                    .toList();

        }


        public MinistryResponse findMinistryById(Long id){

            Ministry ministry = ministryRepository.findById(id)
                    .orElseThrow(() -> new MinisterioNotFoundException("Ministerio no encontrado"));


              return new MinistryResponse(ministry.getName());

        }


        public MinistryResponse createMinistry(CreateMinistryRequest request){

            Ministry ministry = new Ministry();

            ministry.setName(request.name());
            ministry.setDescription(request.description());

            Ministry savedMinistry = ministryRepository.save(ministry);

            return toResponse(savedMinistry);
        }


        public MinistryResponse updateMinistry(Long id, UpdateMinistryRequest request){

            Ministry ministry = ministryRepository.findById(id)
                    .orElseThrow(() -> new MinisterioNotFoundException("Ministerio no encontrado"));

            ministry.setName(request.name());
            ministry.setDescription(request.description());

            Ministry ministrySaved = ministryRepository.save(ministry);

            return toResponse(ministrySaved);

        }


        public void deleteMinistery(Long id){

            Ministry ministry = ministryRepository.findById(id)
                    .orElseThrow(() -> new MinisterioNotFoundException("Ministerio no encontrado"));

             ministryRepository.delete(ministry);
        }


        //consultas del dashboard

        public List<MemberResponse> findByMinistry(String ministryName){

            List<Member> member = memberRepository.findByMinistries_Name(ministryName);

                   return member.stream()
                    .map(this::toMemberResponse)
                    .toList();


        }


        public List<DashboardMinistryCountResponse>dashboarMinistryCount(){
            return ministryRepository.dashboarMinistryCount();
        }

        private MinistryResponse toResponse(Ministry ministry){

            return new MinistryResponse(
                    ministry.getName()

            );
        }


        private ByMinistryResponse toMinistryresponse(Member member){
            return new ByMinistryResponse(
                    member.getId(),
                    member.getName(),
                    member.getLastname(),
                    member.getEmail(),
                    member.isAsset()






            );
        }


        public MemberResponse toMemberResponse(Member member){

            int edad = Period.between(
                    member.getBirthdate(),
                    LocalDate.now()
            ).getYears();
            return new MemberResponse(
                    member.getId(),
                    member.getName(),
                    member.getLastname(),
                    member.getEmail(),
                    edad,
                    member.isAsset(),
                    member.getEcclesiasticalOffice(),
                    member.getMinistries()
                            .stream()
                            .map(Ministry::getName)
                            .toList()
            );
        }








    }
