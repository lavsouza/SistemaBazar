package web2.sistemabazar.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web2.sistemabazar.model.classes.Lote;
import web2.sistemabazar.model.repositories.RepositoryFacade;
import java.sql.SQLException;

@Controller
@RequestMapping("/lote")
public class LoteController {

    private RepositoryFacade facade = RepositoryFacade.getCurrentInstance();

    @GetMapping
    public String listar(Model model) throws SQLException, ClassNotFoundException {
        model.addAttribute("lotes", facade.readAllLotes());
        model.addAttribute("orgaosDonatarios", facade.readAllOrgaoDonatarios());
        model.addAttribute("orgaosFiscalizadores", facade.readAllOrgaoFiscalizadores());
        return "lote/lista";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) throws SQLException, ClassNotFoundException {
        model.addAttribute("lote", new Lote());
        model.addAttribute("orgaosDonatarios", facade.readAllOrgaoDonatarios());
        model.addAttribute("orgaosFiscalizadores", facade.readAllOrgaoFiscalizadores());
        return "lote/form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Lote lote) throws SQLException, ClassNotFoundException {
        if (lote.getId() == 0) {
            facade.createLote(lote);
        } else {
            facade.updateLote(lote);
        }
        return "redirect:/lote";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) throws SQLException, ClassNotFoundException {
        model.addAttribute("lote", facade.readLote(id));
        model.addAttribute("orgaosDonatarios", facade.readAllOrgaoDonatarios());
        model.addAttribute("orgaosFiscalizadores", facade.readAllOrgaoFiscalizadores());
        return "lote/form";
    }

    @GetMapping("/filtrar-lotes")
    public String filtrarLotes(Model model,
                               @RequestParam(required = false) Integer cd,
                               @RequestParam(required = false) Integer cf) throws SQLException, ClassNotFoundException {
        model.addAttribute("lotes", facade.buscarLotePorDonatarioEFiscalizador(cd, cf));
        model.addAttribute("orgaosDonatarios", facade.readAllOrgaoDonatarios());
        model.addAttribute("orgaosFiscalizadores", facade.readAllOrgaoFiscalizadores());
        model.addAttribute("cd", cd);
        model.addAttribute("cf", cf);
        return "lote/lista";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable int id) throws SQLException, ClassNotFoundException {
        Lote l = facade.readLote(id);
        if (l != null) {
            facade.deleteLote(l);
        }
        return "redirect:/lote";
    }
}
