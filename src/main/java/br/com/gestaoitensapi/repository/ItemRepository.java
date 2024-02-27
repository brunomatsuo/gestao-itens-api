package br.com.gestaoitensapi.repository;

import br.com.gestaoitensapi.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {
    List<Item> findByNome(String name);
    List<Item> findByMarca(String marca);
    Optional<Item> findById(Integer id);
    void deleteById(Integer id);
}
