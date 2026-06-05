    package com.irarrazabal.iglesiaapi.application.service;

    import com.irarrazabal.iglesiaapi.application.dto.dashboard.DashboardOfficeCountResponse;
    import com.irarrazabal.iglesiaapi.application.dto.members.CreateMemberRequest;
    import com.irarrazabal.iglesiaapi.application.dto.members.MemberResponse;
    import com.irarrazabal.iglesiaapi.application.dto.members.UpdateMemberRequest;
    import com.irarrazabal.iglesiaapi.domain.model.EcclesiasticalOffice;
    import com.irarrazabal.iglesiaapi.domain.model.Member;
    import com.irarrazabal.iglesiaapi.domain.model.Ministry;
    import com.irarrazabal.iglesiaapi.domain.repository.MemberRepository;
    import com.irarrazabal.iglesiaapi.domain.repository.MinistryRepository;
    import com.irarrazabal.iglesiaapi.exceptions.IntegranteNotFoundExceptions;
    import com.irarrazabal.iglesiaapi.exceptions.MinisterioNotFoundException;
    import org.springframework.stereotype.Service;

    import java.time.LocalDate;
    import java.time.Period;
    import java.util.List;

    @Service
    public class MemberService {


        private MemberRepository memberRepository;
        private MinistryRepository ministryRepository;

        public MemberService(MemberRepository memberRepository, MinistryRepository ministryRepository) {
            this.memberRepository = memberRepository;
            this.ministryRepository = ministryRepository;
        }

    //Crear nuevo integrante, busco por id los departamentos y agrego a la bd
        public MemberResponse createMember(CreateMemberRequest request ){

            List<Ministry> ministries = ministryRepository.findAllById(
                    request.ministryId()
            );
            if (ministries.size() != request.ministryId().size()) {
                throw new MinisterioNotFoundException("Uno o más ministerios no existen");
            }
            Member member = new Member(
                    null,
                    request.name(),
                    request.lastname(),
                    request.email(),
                    request.birthdate(),
                    request.asset(),
                    request.ecclesiasticalOffice(),
                    ministries);

        Member saved = memberRepository.save(member);
        return  this.toResponse(saved);
        }



        //Buscar por id
        public MemberResponse findMemberById(Long id){
           Member member = memberRepository.findById(id)
                   .orElseThrow(()-> new IntegranteNotFoundExceptions("Integrante no encontrado"));

           return this.toResponse(member);

        }

        //Listar todos los integrantes
        public List<MemberResponse> findAllMember(){
           return memberRepository.findAll()
                   .stream()
                   .map(this::toResponse).toList();
        }

        //Actualizar integrante, buscamos por id y si existe actualizamos
        public MemberResponse updateMember(Long id, UpdateMemberRequest request){

            Member member = memberRepository.findById(id)
                    .orElseThrow(()-> new IntegranteNotFoundExceptions("Integrante no encontrado"));


            List<Ministry> ministry = ministryRepository.findAllById(
                    request.ministryId());

            if(ministry.size() != request.ministryId().size()){
                throw  new MinisterioNotFoundException("Uno o mas ministerios no existen");
            }

    member.setName(request.name());
    member.setLastname(request.lastname());
    member.setEmail(request.email());
    member.setBirthdate(request.birthdate());
    member.setAsset(request.asset());
    member.setEcclesiasticalOffice(request.ecclesiasticalOffice());
    member.setMinistries(ministry);


    Member saved = memberRepository.save(member);

    return  this.toResponse(saved);

        }


    //Eliminar por id
        public void deleteMember(Long id){

            Member member = memberRepository.findById(id)
                    .orElseThrow(() -> new IntegranteNotFoundExceptions("Integrante no encontrado"));

            memberRepository.delete(member);
        }


        //Buscar integrantes activos

        public List<MemberResponse> findByActiveTrue(){

            return memberRepository.findByAssetTrue()
                    .stream()
                    .map(this::toResponse)
                    .toList();
        }

        //Buscar integrantes Inactivos

        public List<MemberResponse> findByInActiveFalse(){

            return memberRepository.findByAssetFalse()
                    .stream()
                    .map(this::toResponse)
                    .toList();
        }



        //buscar por cargo

        public List<MemberResponse> findByEcclesiasticalOffice(EcclesiasticalOffice ecclesiastical_office){

           return memberRepository.findByEcclesiasticalOffice(ecclesiastical_office)
                    .stream()
                    .map(this::toResponse)
                    .toList();

        }

        public List<DashboardOfficeCountResponse> dashboardOfficeCount(){
            return memberRepository.countMemberByOffice();

        }








        public MemberResponse toResponse(Member member){

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
