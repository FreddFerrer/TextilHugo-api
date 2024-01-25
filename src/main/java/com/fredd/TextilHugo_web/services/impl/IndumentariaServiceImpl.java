package com.fredd.TextilHugo_web.services.impl;

import com.fredd.TextilHugo_web.exceptions.ResourceNotFoundException;
import com.fredd.TextilHugo_web.model.dtos.IndumentariaDto;
import com.fredd.TextilHugo_web.model.dtos.request.CreateIndumentariaDto;
import com.fredd.TextilHugo_web.model.entities.Indumentaria;
import com.fredd.TextilHugo_web.model.mappers.IndumentariaDTOMapper;
import com.fredd.TextilHugo_web.model.repositories.IindumentariaRepository;
import com.fredd.TextilHugo_web.services.IindumentariaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IndumentariaServiceImpl implements IindumentariaService {

    private final IindumentariaRepository clothingRepository;
    private final IndumentariaDTOMapper indumentariaDTOMapper;

    @Override
    public List<IndumentariaDto> getAllClothings() {
        List<Indumentaria> indumentaria = clothingRepository.findAll();
        return indumentariaDTOMapper.toDtoList(indumentaria);
    }

    @Override
    public Optional<IndumentariaDto> getClothingById(Long clothingId) {
        Optional<Indumentaria> indumentaria = clothingRepository.findById(clothingId);
        return indumentaria.map(indumentariaDTOMapper::toDto);
    }

    @Override
    public IndumentariaDto saveIndumentaria(CreateIndumentariaDto createIndumentaria) {
        Indumentaria indumentaria = indumentariaDTOMapper.toEntity(createIndumentaria);
        indumentaria = clothingRepository.save(indumentaria);
        return indumentariaDTOMapper.toDto(indumentaria);
    }

    @Override
    public IndumentariaDto updateIndumentaria(Long id, IndumentariaDto updatedIndumentariaDto) {
        // Verificar si la indumentaria existe por el ID
        Optional<Indumentaria> optionalIndumentaria = clothingRepository.findById(id);

        if (optionalIndumentaria.isPresent()) {
            // Obtener la indumentaria existente
            Indumentaria existingIndumentaria = optionalIndumentaria.get();

            // Actualizar las propiedades de la indumentaria existente con las proporcionadas en updatedIndumentariaDto
            existingIndumentaria.setType(updatedIndumentariaDto.getTipo());
            existingIndumentaria.setDescription(updatedIndumentariaDto.getDescripcion());
            existingIndumentaria.setBrand(updatedIndumentariaDto.getMarca());
            existingIndumentaria.setGender(updatedIndumentariaDto.getGenero());
            existingIndumentaria.setColor(updatedIndumentariaDto.getColor());
            existingIndumentaria.setMaterial(updatedIndumentariaDto.getMaterial());
            existingIndumentaria.setCategory(updatedIndumentariaDto.getCategoria());
            existingIndumentaria.setSeason(updatedIndumentariaDto.getTemporada());
            existingIndumentaria.setClothingSize(updatedIndumentariaDto.getTalleIndumentaria());

            // Guardar la indumentaria actualizada en la base de datos
            Indumentaria updatedIndumentaria = clothingRepository.save(existingIndumentaria);

            // Mapear la indumentaria actualizada a DTO
            return indumentariaDTOMapper.toDto(updatedIndumentaria);
        } else {
            throw new ResourceNotFoundException("indumentaria", "id", id);
        }
    }

    @Override
    public boolean existsById(Long id) {
        return clothingRepository.existsById(id);
    }

    @Override
    public void deleteClothingById(Long clothingId) {
        clothingRepository.deleteById(clothingId);
    }

}
