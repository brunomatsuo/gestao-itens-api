package br.com.gestaoitensapi.controller;

import br.com.gestaoitensapi.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("estoque")
public class EstoqueController {
    @Autowired
    ItemService itemService;

    @PutMapping("/{id}/removerEstoque/{quantidade}")
    public ResponseEntity removeEstoque(@PathVariable String id, @PathVariable String quantidade) {
        String removeText = itemService.removeQuantidade(Integer.parseInt(id), Integer.parseInt(quantidade));
        if (removeText.equals("Estoque atualizado")) {
            return ResponseEntity.ok(removeText);
        } else if (removeText.equals("Item não encontrado.")) {
            return ResponseEntity.badRequest().body(removeText);
        } else {
            return ResponseEntity.internalServerError().body(removeText);
        }
    }
}
