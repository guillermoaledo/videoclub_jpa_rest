package org.iesvdm.videoclub.repository;

import org.iesvdm.videoclub.domain.Categoria;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaCustomRepository {
    public List<Categoria> queryCustomCategoriaConPageable(Optional<String> buscarOptional, Optional<String> ordenarOptional, Pageable pageable);
}
