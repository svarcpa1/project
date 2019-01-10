package cz.uhk.ppro.project.controllers;

import cz.uhk.ppro.project.model.Hall;
import cz.uhk.ppro.project.model.Workplace;
import cz.uhk.ppro.project.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class WorkplaceController {

    @Autowired
    TestService testService;

    @GetMapping("/addWorkplace")
    public String showForm(Model model){
        model.addAttribute("workplace", new Workplace());
        List<Hall> haly = testService.findAllHalls();
        model.addAttribute("haly", haly);
        return "addWorkplaceForm";
    }

    @PostMapping("/addWorkplace")
    public String processForm(@ModelAttribute("workplace") Workplace workplace){
        //TODO validace
        //TODO workspace service
        Hall hall = testService.findById(workplace.getHall().getId());
        workplace.setHall(hall);
        hall.getWorkplaces().add(workplace);

        testService.updateHall(hall);
        testService.saveEntity(workplace);
        return "redirect:/";
    }

    @GetMapping("/hall/{id}")
    public String getHall(Model model, @PathVariable("id") long id){
        Hall hall = testService.findById(id);

        if(hall==null){
            throw new RuntimeException("Hall with id: " + id +" not found");
        }
        else {
            model.addAttribute("hala", hall);
            return "workplaceListView";
        }
    }

}
