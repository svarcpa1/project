package cz.uhk.ppro.project.controllers;

import cz.uhk.ppro.project.model.Hall;
import cz.uhk.ppro.project.model.Worker;
import cz.uhk.ppro.project.model.Workplace;
import cz.uhk.ppro.project.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WorkerController {

    @Autowired
    TestService testService;

    @GetMapping("/addWorker")
    public String showForm(Model model){
        model.addAttribute("worker", new Worker());
        List<Workplace> workplaces = testService.findAllWorkplaces();
        model.addAttribute("workplaces", workplaces);
        return "addWorkerForm";
    }

    @PostMapping("/addWorker")
    public String processForm(@ModelAttribute("worker") Worker worker, @RequestParam String action){
        //TODO validace

        if( action.equals("save") ){

            Workplace workplace = testService.findWorkplaceById(worker.getWorkplace().getId());
            Hall hall = testService.findHallById(workplace.getHall().getId());
            worker.setWorkplace(workplace);
            workplace.getWorkers().add(worker);

            testService.updateHall(hall);
            return "redirect:/";
        }
        // cancel
        else {
            return "redirect:/";
        }
    }

}
