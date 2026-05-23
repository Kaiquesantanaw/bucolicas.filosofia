package bucolicasfilos.example.demo.controller;

import bucolicasfilos.example.demo.model.Ensaio;
import bucolicasfilos.example.demo.service.EnsaioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final EnsaioService ensaioService;

    public AdminController(EnsaioService ensaioService) {
        this.ensaioService = ensaioService;
    }

    @GetMapping("/login")
    public String login() {
        return "admin/login";
    }

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("ensaios", ensaioService.findAll());
        return "admin/dashboard";
    }

    @GetMapping("/ensaios/novo")
    public String novoForm(Model model) {
        model.addAttribute("ensaio", new Ensaio());
        return "admin/formulario";
    }

    @GetMapping("/ensaios/{id}/editar")
    public String editarForm(@PathVariable Long id, Model model) {
        ensaioService.findById(id).ifPresent(e -> model.addAttribute("ensaio", e));
        return "admin/formulario";
    }

    @PostMapping("/ensaios/salvar")
    public String salvar(@ModelAttribute Ensaio ensaio) {
        if (ensaio.getId() != null) {
            ensaioService.update(ensaio.getId(), ensaio);
        } else {
            ensaioService.save(ensaio);
        }
        return "redirect:/admin";
    }

    @PostMapping("/ensaios/{id}/deletar")
    public String deletar(@PathVariable Long id) {
        ensaioService.delete(id);
        return "redirect:/admin";
    }
}
