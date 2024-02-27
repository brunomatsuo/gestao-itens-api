package br.com.gestaoitensapi.controller;

import br.com.gestaoitensapi.model.Item;
import br.com.gestaoitensapi.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity getAllItems() {
        List<Item> items = itemService.getAll();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity getItemById(@PathVariable Integer id) {
        Item item = itemService.getById(id);
        if (item != null) {
            return ResponseEntity.ok(item);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/nome")
    public ResponseEntity findItemByName(@RequestParam String nome) {
        List<Item> items = itemService.findByName(nome);
        if (items.size() > 0) {
            return ResponseEntity.ok(items);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/marca")
    public ResponseEntity findItemByMarca(@RequestParam String marca) {
        List<Item> items = itemService.findByMarca(marca);
        if (items.size() > 0) {
            return ResponseEntity.ok(items);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateItem(@PathVariable Integer id, @RequestBody Item itemAtualizado) {
        String update = itemService.updateItem(id, itemAtualizado);
        if (update.equals("Item atualizado.")) {
            return ResponseEntity.ok(update);
        } else if (update.equals("Item não encontrado.")) {
            return ResponseEntity.badRequest().body(update);
        } else {
            return ResponseEntity.internalServerError().body(update);
        }
    }

    @PutMapping("/{id}/preco")
    public ResponseEntity updatePreco(@PathVariable Integer id, @RequestBody String novoPreco) {
        BigDecimal preco = new BigDecimal(novoPreco);
        String updateMessage = itemService.updatePrice(id, preco);
        if (updateMessage.equals("Preço do item atualizado")) {
            return ResponseEntity.ok(updateMessage);
        } else if (updateMessage.equals("Item não encontrado.")) {
            return ResponseEntity.badRequest().body(updateMessage);
        } else {
            return ResponseEntity.internalServerError().body(updateMessage);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteItem(@PathVariable Integer id) {
        String deleteMessage = itemService.removeItem(id);
        if (deleteMessage.equals("Item removido.")) {
            return ResponseEntity.ok(deleteMessage);
        } else if (deleteMessage.equals("Item não encontrado.")) {
            return ResponseEntity.badRequest().body(deleteMessage);
        } else {
            return ResponseEntity.internalServerError().body(deleteMessage);
        }
    }

    @PostMapping
    public ResponseEntity createItem(@RequestBody Item item) {
        Item createdItem = itemService.createItem(item);
        if (createdItem != null) {
            return ResponseEntity.created(URI.create(createdItem.getId().toString())).body(createdItem);
        } else {
            return ResponseEntity.internalServerError().body("Não foi possível criar o item.");
        }
    }

    @PutMapping("/{id}/removerEstoque/{quantidade}")
    public ResponseEntity removeEstoque(@PathVariable Integer id, @PathVariable Integer quantidade) {
        String removeText = itemService.removeQuantidade(id, quantidade);
        if (removeText.equals("Estoque atualizado")) {
            return ResponseEntity.ok(removeText);
        } else if (removeText.equals("Item não encontrado.")) {
            return ResponseEntity.badRequest().body(removeText);
        } else {
            return ResponseEntity.internalServerError().body(removeText);
        }
    }
}
