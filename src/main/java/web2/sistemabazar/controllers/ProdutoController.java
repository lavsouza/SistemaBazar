package web2.sistemabazar.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web2.sistemabazar.model.classes.Produto;
import web2.sistemabazar.model.repositories.RepositoryFacade;

import java.sql.SQLException;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    private RepositoryFacade facade = RepositoryFacade.getCurrentInstance();

    @GetMapping
    public String listar(Model model) throws SQLException, ClassNotFoundException {
        model.addAttribute("produtos", facade.readAllProdutos());
        model.addAttribute("lotes", facade.readAllLotes());
        return "produto/lista";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) throws SQLException, ClassNotFoundException {
        model.addAttribute("produto", new Produto());
        model.addAttribute("lotes", facade.readAllLotes());
        return "produto/form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Produto produto) throws SQLException, ClassNotFoundException {
        if (produto.getCodigo() == 0) {
            facade.createProduto(produto);
        } else {
            facade.updateProduto(produto);
        }
        return "redirect:/produto";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) throws SQLException, ClassNotFoundException {
        model.addAttribute("produto", facade.readProduto(id));
        model.addAttribute("lotes", facade.readAllLotes());
        return "produto/form";
    }

    @GetMapping("/filtrar-produtos")
    public String filtrarLotes(Model model,
                               @RequestParam(required = true) Integer l) throws SQLException, ClassNotFoundException {
        model.addAttribute("produtos", facade.buscarProdutoPorLote(l));
        model.addAttribute("lotes", facade.readAllLotes());
        return "produto/lista";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable int id) throws SQLException, ClassNotFoundException {
        Produto p = facade.readProduto(id);
        if (p != null) {
            facade.deleteProduto(p);
        }
        return "redirect:/produto";
    }
}
