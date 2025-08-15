package br.com.fiap.uforegistry.sighting;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/sighting")
public class UfoSightingController {

    private final UfoSightingService ufoSightingService;

    public UfoSightingController(UfoSightingService ufoSightingService) {
        this.ufoSightingService = ufoSightingService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("sightings", ufoSightingService.getAllSightings());
        return "sighting/index";
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("sighting", new UfoSighting());
        return "sighting/form";
    }

    @PostMapping("/form")
    public String create(UfoSighting ufoSighting, RedirectAttributes redirect) {
        ufoSightingService.save(ufoSighting);
        redirect.addFlashAttribute("message", "Avistamento de OVNI registrado com sucesso!");
        return "redirect:/sighting";
    }

    @GetMapping("/search")
    public String search(@RequestParam(required = false) String location,
                        @RequestParam(required = false) String shape,
                        Model model) {
        if (location != null && !location.trim().isEmpty()) {
            model.addAttribute("sightings", ufoSightingService.findByLocation(location));
            model.addAttribute("searchTerm", location);
        } else if (shape != null && !shape.trim().isEmpty()) {
            model.addAttribute("sightings", ufoSightingService.findByShape(shape));
            model.addAttribute("searchTerm", shape);
        } else {
            model.addAttribute("sightings", ufoSightingService.getAllSightings());
        }
        
        return "sighting/index";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        ufoSightingService.deleteById(id);
        redirect.addFlashAttribute("message", "Avistamento de OVNI removido com sucesso!");
        return "redirect:/sighting";
    }
}