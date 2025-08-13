package web2.sistemabazar.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web2.sistemabazar.model.classes.OrgaoFiscalizador;
import web2.sistemabazar.model.repositories.RepositoryFacade;

import java.sql.SQLException;

@Controller
@RequestMapping("/orgaofiscalizador")
public class OrgaoFiscalizadorController {

    private RepositoryFacade facade = RepositoryFacade.getCurrentInstance();

    @GetMapping
    public String listar(Model model) throws SQLException, ClassNotFoundException {
        model.addAttribute("orgaos", facade.readAllOrgaoFiscalizadores());
        return "orgaofiscalizador/lista";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("orgaofiscalizador", new OrgaoFiscalizador());
        return "orgaofiscalizador/form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute OrgaoFiscalizador orgao) throws SQLException, ClassNotFoundException {
        if (orgao.getId() == 0) {
            facade.createOrgaoFiscalizador(orgao);
        } else {
            facade.updateOrgaoFiscalizador(orgao);
        }
        return "redirect:/orgaofiscalizador";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) throws SQLException, ClassNotFoundException {
        model.addAttribute("orgaofiscalizador", facade.readOrgaoFiscalizador(id));
        return "orgaofiscalizador/form";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable int id) throws SQLException, ClassNotFoundException {
        OrgaoFiscalizador o = facade.readOrgaoFiscalizador(id);
        if (o != null) {
            facade.deleteOrgaoFiscalizador(o);
        }
        return "redirect:/orgaofiscalizador";
    }
}