package web2.sistemabazar.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web2.sistemabazar.model.classes.OrgaoDonatario;
import web2.sistemabazar.model.repositories.RepositoryFacade;

import java.sql.SQLException;

@Controller
@RequestMapping("/orgaodonatario")
public class OrgaoDonatarioController {

    private RepositoryFacade facade = RepositoryFacade.getCurrentInstance();

    @GetMapping
    public String listar(Model model) throws SQLException, ClassNotFoundException {
        model.addAttribute("orgaos", facade.readAllOrgaoDonatarios());
        return "orgaodonatario/lista"; // nome do template Thymeleaf
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("orgaoDonatario", new OrgaoDonatario());
        return "orgaodonatario/form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute OrgaoDonatario orgao) throws SQLException, ClassNotFoundException {
        if (orgao.getId() == 0) {
            facade.createOrgaoDonatario(orgao);
        } else {
            facade.updateOrgaoDonatario(orgao);
        }
        return "redirect:/orgaodonatario";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) throws SQLException, ClassNotFoundException {
        model.addAttribute("orgaoDonatario", facade.readOrgaoDonatario(id));
        return "orgaodonatario/form";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable int id) throws SQLException, ClassNotFoundException {
        OrgaoDonatario o = facade.readOrgaoDonatario(id);
        if (o != null) {
            facade.deleteOrgaoDonatario(o);
        }
        return "redirect:/orgaodonatario";
    }
}