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
    public String processForm(@ModelAttribute("workplace") Workplace workplace, @RequestParam String action){
        //TODO validace
        //TODO workspace service

        if( action.equals("save") ){
            Hall hall = testService.findHallById(workplace.getHall().getId());
            workplace.setHall(hall);
            hall.getWorkplaces().add(workplace);

            testService.updateHall(hall);
            return "redirect:/";
        }
        // cancel
        else {
            return "redirect:/";
        }
    }

    @RequestMapping("hall/{hall.id}/deleteWorkplace/{id}")
    public String deleteWorkplaceId(@ModelAttribute("workplace") Workplace workplace, @PathVariable("id") long id, @PathVariable("hall.id") long hallId){
        Hall hall = testService.findHallById(hallId);
        workplace = testService.findWorkplaceById(id);

        hall.removeWorkplace(workplace);

        System.out.println("Hala: " + hall.getId());
        System.out.println("Workplace: " + workplace.getId());
        testService.updateHall(hall);
        return "redirect:/";

    }

    @GetMapping("/hall/{id}")
    public String getHall(Model model, @PathVariable("id") long id){
        Hall hall = testService.findHallById(id);

        if(hall==null){
            throw new RuntimeException("Hall with id: " + id +" not found");
        }
        else {
            model.addAttribute("hala", hall);
            return "workplaceListView";
        }
    }

    @GetMapping("/hall/{hall.id}/workplace/{id}")
    public String getWorkplace(Model model, @PathVariable("id") long id,  @PathVariable("hall.id") long hallId){
        Workplace workplace = testService.findWorkplaceById(id);

        if(workplace==null){
            throw new RuntimeException("Workplace with id: " + id +" not found");
        }
        else {
            model.addAttribute("workplace", workplace);
            return "documentWorkerListView";
        }
    }

}