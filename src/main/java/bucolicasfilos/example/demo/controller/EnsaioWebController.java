package bucolicasfilos.example.demo.controller;

import bucolicasfilos.example.demo.service.EnsaioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ensaios")
public class EnsaioWebController {

    private final EnsaioService ensaioService;

    public EnsaioWebController(EnsaioService ensaioService) {
        this.ensaioService = ensaioService;
    }

    @GetMapping
    public String lista(Model model) {
        model.addAttribute("ensaios", ensaioService.findAll());
        return "ensaios/lista";
    }

    @GetMapping("/{id}")
    public String detalhe(@PathVariable Long id, Model model) {
        ensaioService.findById(id).ifPresent(e -> model.addAttribute("ensaio", e));
        return "ensaios/detalhe";
    }
}
