package org.iesvdm.videoclub.repository;

import org.iesvdm.videoclub.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    public List<Categoria> findAllByNombreContainingIgnoreCaseOrderByNombre(String nombre);

    public List<Categoria> findAllByNombreContainingIgnoreCaseOrderByNombreDesc(String ordenar);


    @Query(value = "select count(P) from Pelicula P join P.categorias C where C.idCategoria = :idCategoria")
    public Integer queryContarPeliculas(int idCategoria);

}

