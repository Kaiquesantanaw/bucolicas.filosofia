package bucolicasfilos.example.demo.controller;

import bucolicasfilos.example.demo.service.EnsaioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final EnsaioService ensaioService;

    public HomeController(EnsaioService ensaioService) {
        this.ensaioService = ensaioService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("ensaios", ensaioService.findAll());
        return "index";
    }
}
