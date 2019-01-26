package cz.uhk.ppro.project.controllers;

import cz.uhk.ppro.project.model.Document;
import cz.uhk.ppro.project.model.Hall;
import cz.uhk.ppro.project.model.Worker;
import cz.uhk.ppro.project.model.Workplace;
import cz.uhk.ppro.project.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class WorkerController {

    @Autowired
    TestService testService;

    @GetMapping("/addWorker")
    public String showForm(Model model){

        if(!model.containsAttribute("worker")) model.addAttribute("worker", new Worker());

        List<Workplace> workplaces = testService.findAllWorkplaces();
        model.addAttribute("workplaces", workplaces);

        return "addWorkerForm";
    }

    @PostMapping("/addWorker")
    public String processForm(@ModelAttribute("worker") @Valid Worker worker, BindingResult result,
                              @RequestParam String action, RedirectAttributes attributes){

        if (result.hasErrors()){
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.worker", result);
            attributes.addFlashAttribute("worker", worker);
            return "redirect:/addWorker";
        }else {
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

    @RequestMapping("/deleteWorker/{id}")
    public String deleteWorkerId(@ModelAttribute("worker") Worker worker, @PathVariable("id") long id){
        worker = testService.findWorkerById(id);
        Workplace workplace = testService.findWorkplaceById(worker.getWorkplace().getId());
        Hall hall = testService.findHallById(workplace.getHall().getId());

        workplace.removeWorker(worker);
        List<Document> documents = worker.getDocumentsCreated();
        for (Document document: documents) {
            document.setWorkerCreated(null);
        }

        testService.updateHall(hall);
        return "redirect:/";

    }

}
