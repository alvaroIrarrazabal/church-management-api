    package com.irarrazabal.iglesiaapi.application.service;

    import com.irarrazabal.iglesiaapi.application.dto.integrante.CreateIntegranteRequest;
    import com.irarrazabal.iglesiaapi.application.dto.integrante.IntegranteResponse;
    import com.irarrazabal.iglesiaapi.application.dto.integrante.UpdateIntegranteRequest;
    import com.irarrazabal.iglesiaapi.domain.model.Integrante;
    import com.irarrazabal.iglesiaapi.domain.model.Ministerio;
    import com.irarrazabal.iglesiaapi.domain.repository.IntegranteRepository;
    import com.irarrazabal.iglesiaapi.domain.repository.MinisterioRepository;
    import com.irarrazabal.iglesiaapi.exceptions.IntegranteNotFoundExceptions;
    import com.irarrazabal.iglesiaapi.exceptions.MinisterioNotFoundException;
    import org.springframework.stereotype.Service;

    import java.util.List;

    @Service
    public class IntegranteService {


        private IntegranteRepository integranteRepository;
        private MinisterioRepository ministerioRepository;

        public IntegranteService(IntegranteRepository integranteRepository, MinisterioRepository ministerioRepository) {
            this.integranteRepository = integranteRepository;
            this.ministerioRepository = ministerioRepository;
        }

    //Crear nuevo integrante, busco por id los departamentos y agrego a la bd
        public IntegranteResponse crearIntegrante(CreateIntegranteRequest request ){

            List<Ministerio> ministerios = ministerioRepository.findAllById(
                    request.ministeriosIds()
            );
            if (ministerios.size() != request.ministeriosIds().size()) {
                throw new MinisterioNotFoundException("Uno o más ministerios no existen");
            }
            Integrante integrante = new Integrante(
                    null,
                    request.nombre(),
                    request.apellido(),
                    request.correo(),
                    request.fecaNacimiento(),
                    request.activo(),
                    ministerios        );

        Integrante guardado = integranteRepository.save(integrante);
        return  this.toResponse(guardado);
        }



        //Buscar por id
        public IntegranteResponse buscarPorid(Long id){
           Integrante integrante = integranteRepository.findById(id)
                   .orElseThrow(()-> new IntegranteNotFoundExceptions("Integrante no encontrado"));

           return this.toResponse(integrante);

        }

        //Listar todos los integrantes
        public List<IntegranteResponse> buscarTodos(){
           return integranteRepository.findAll()
                   .stream()
                   .map(this::toResponse).toList();
        }

        //Actualizar integrante, buscamos por id y si existe actualizamos
        public IntegranteResponse actualizarIntegrante(Long id, UpdateIntegranteRequest request){

            Integrante integrante = integranteRepository.findById(id)
                    .orElseThrow(()-> new IntegranteNotFoundExceptions("Integrante no encontrado"));


            List<Ministerio> ministerio = ministerioRepository.findAllById(
                    request.ministeriosIds());

            if(ministerio.size() != request.ministeriosIds().size()){
                throw  new MinisterioNotFoundException("Uno o mas ministerios no existen");
            }

    integrante.setNombre(request.nombre());
    integrante.setApellido(request.apellido());
    integrante.setCorreo(request.correo());
    integrante.setFechanacimiento(request.fecaNacimiento());
    integrante.setActivo(request.activo());
    integrante.setMinisterios(ministerio);


    Integrante guardado = integranteRepository.save(integrante);

    return  this.toResponse(guardado);




        }

    //Eliminar por id

        public void eliminarIntegrante(Long id){

            Integrante integrante = integranteRepository.findById(id)
                    .orElseThrow(() -> new IntegranteNotFoundExceptions("Integrante no encontrado"));

            integranteRepository.delete(integrante);
        }












        public IntegranteResponse toResponse(Integrante integrante){

            return new IntegranteResponse(
                integrante.getNombre(),
                    integrante.getApellido(),
                    integrante.getCorreo(),
                    integrante.isActivo(),
                    integrante.getMinisterios()
                            .stream()
                            .map(Ministerio::getNombre)
                            .toList()
            );
        }

    }
