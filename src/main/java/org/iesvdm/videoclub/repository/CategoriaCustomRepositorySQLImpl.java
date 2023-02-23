package org.iesvdm.videoclub.repository;



import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.iesvdm.videoclub.domain.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoriaCustomRepositorySQLImpl implements CategoriaCustomRepository {

    @Autowired
    private EntityManager em;
    @Override
    public List<Categoria> queryCustomCategoriaConPageable(Optional<String> buscarOptional, Optional<String> ordenarOptional, Pageable pageable) {

        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM categoria");

        if (buscarOptional.isPresent()) {
            queryBuilder.append(" ").append("WHERE nombre like :nombre");
        }

        if (ordenarOptional.isPresent()) {
            if ("asc".equalsIgnoreCase(ordenarOptional.get())) {
                queryBuilder.append(" ").append("ORDER BY nombre ASC");
            } else if ("desc".equalsIgnoreCase(ordenarOptional.get())) {
                queryBuilder.append(" ").append("ORDER BY nombre DESC");
            }
        }

        Query query = em.createNativeQuery(queryBuilder.toString(), Categoria.class)
            .setMaxResults(pageable.getPageSize())
            .setFirstResult(pageable.getPageNumber() * pageable.getPageSize());

        if (buscarOptional.isPresent()) {
            query.setParameter("nombre", "%" + buscarOptional.get() + "%");
        }

        return query.getResultList();
    }
}
