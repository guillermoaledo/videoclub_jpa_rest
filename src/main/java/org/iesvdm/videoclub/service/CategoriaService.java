package org.iesvdm.videoclub.service;

import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.exception.CategoriaNotFoundException;
import org.iesvdm.videoclub.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public  CategoriaService(CategoriaRepository categoriaRepository) {this.categoriaRepository = categoriaRepository; }

    public List<Categoria> all() { return this.categoriaRepository.findAll(); }

    public List<Categoria> allByQueryFiltersStream(Optional<String> nombre, Optional<String> orden) {
        if(orden.get().equals("desc")) {
            return this.categoriaRepository.findAllByNombreContainingIgnoreCaseOrderByNombreDesc(nombre.get());
        } else {
            return this.categoriaRepository.findAllByNombreContainingIgnoreCaseOrderByNombre(nombre.get());

        }

    }

    public Categoria save(Categoria categoria) {
        categoria.setUltimaActualizacion(new Date());
        return this.categoriaRepository.save(categoria); }

    public  Categoria one(Long id) {
        return this.categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNotFoundException(id));
    }

    public Categoria replace(Long id, Categoria categoria) {

        categoria.setUltimaActualizacion(new Date());

        return this.categoriaRepository.findById(id).map(
                p -> (id.equals(categoria.getIdCategoria()) ? this.categoriaRepository.save(categoria) : null))
                .orElseThrow(() -> new CategoriaNotFoundException(id));


    }

    public void delete(Long id) {

        this.categoriaRepository.findById(id).map(
                p -> {this.categoriaRepository.delete(p);
                    return p;})
                .orElseThrow(() -> new CategoriaNotFoundException(id));

    }

}
