package br.com.gestaoitensapi.service;

import br.com.gestaoitensapi.model.Item;
import br.com.gestaoitensapi.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    @Override
    public Item getById(Integer id) {
        return itemRepository.findById(id).orElse(null);
    }

    @Override
    public List<Item> findByName(String name) {
        return itemRepository.findByNome(name);
    }

    @Override
    public List<Item> findByMarca(String marca) {
        return itemRepository.findByMarca(marca);
    }

    @Override
    public String updateItem(Integer id, Item itemUpdated) {
        Item item = itemRepository.findById(id).orElse(null);
        if(item != null) {
            item = itemUpdated;
            item.setId(id);
            try {
                itemRepository.save(item);
                return "Item atualizado.";
            }
            catch (Exception ex) {
                return "Não foi possível atualizar o item. Erro: " + ex;
            }
        }
        else {
            return "Item não encontrado.";
        }
    }

    @Override
    public String updatePrice(Integer id, BigDecimal novoPreco) {
        Item item = itemRepository.findById(id).orElse(null);
        if(item != null) {
            item.setPreco(novoPreco);
            try {
                itemRepository.save(item);
                return "Preço do item atualizado";
            }
            catch (Exception ex) {
                return "Não foi possível atualizar o preço. Erro: " + ex;
            }
        }
        else {
            return "Item não encontrado.";
        }
    }

    @Override
    public String removeItem(Integer id) {
        Item item = itemRepository.findById(id).orElse(null);
        if(item != null) {
            try {
                itemRepository.deleteById(id);
                return "Item removido.";
            }
            catch (Exception ex) {
                return "Não foi possível remover o item. Erro: " + ex;
            }
        }
        else {
            return "Item não encontrado.";
        }
    }

    @Override
    public Item createItem(Item item) {
        try {
            return itemRepository.save(item);
        }
        catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    @Override
    public String removeQuantidade(Integer id, Integer quantidade) {
        Item item = itemRepository.findById(id).orElse(null);
        if(item != null) {

                Integer quantidadeFinal = item.getQuantidade() - quantidade;
                if(quantidadeFinal < 0) {
                    return "Não há estoque suficiente para o pedido.";
                }
                item.setQuantidade(quantidadeFinal);
            try {
                itemRepository.save(item);
                return "Estoque atualizado";
            }
            catch (Exception ex) {
                return "Não foi possível atualizar o estoque do item. Erro: " + ex;
            }
        }
        else {
            return "Item não encontrado.";
        }
    }
}
