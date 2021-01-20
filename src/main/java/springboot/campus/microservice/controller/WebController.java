package springboot.campus.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springboot.campus.microservice.dao.CharacterDao;
import springboot.campus.microservice.enums.TypeList;
import springboot.campus.microservice.model.Character;

@Controller
public class WebController {

    @Autowired
    private CharacterDao characterDao;

    @Value("${name.not.empty}")
    private String nameErrorMessage;

    @Value("${type.not.empty}")
    private String typeErrorMessage;

    @GetMapping(value = "/")
    public String getTemplate(@RequestParam(name = "name", required = false, defaultValue = "Player") String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }

    @GetMapping(value = "list")
    public ModelAndView characters() {
        ModelAndView mav = new ModelAndView("characters");
        mav.addObject("characters", characterDao.findAll());
        return mav;
    }

    @GetMapping(value = "character/{id}")
    public ModelAndView character(@PathVariable int id) {
        ModelAndView mav = new ModelAndView("character");
        mav.addObject("character", characterDao.findById(id));
        return mav;
    }

    @GetMapping(value = "characters/new")
    public String register(Model model) {
        model.addAttribute("character", new Character());
        return "new";
    }

    @PostMapping(value = "characters/new")
    public String create(Model model, @ModelAttribute Character character) {
        String name = character.getName();
        TypeList type = character.getType();

        if (name == null || name.length() == 0) {

            model.addAttribute("nameErrorMessage", nameErrorMessage);
            return "new";
        }
        if (type == null) {
            model.addAttribute("typeErrorMessage", typeErrorMessage);
            return "new";
        }

        characterDao.save(character);
        return "redirect:/list";
    }
}
