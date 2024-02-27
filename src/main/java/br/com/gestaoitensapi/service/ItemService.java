package br.com.gestaoitensapi.service;

import br.com.gestaoitensapi.model.Item;

import java.math.BigDecimal;
import java.util.List;

public interface ItemService {
    List<Item> getAll();
    Item getById(Integer id);
    List<Item> findByName(String name);
    List<Item> findByMarca(String marca);
    String updateItem(Integer id, Item itemUpdated);
    String updatePrice(Integer id, BigDecimal novoPreco);
    String removeItem(Integer id);
    Item createItem(Item item);
    String removeQuantidade(Integer id, Integer quantidade);

}
