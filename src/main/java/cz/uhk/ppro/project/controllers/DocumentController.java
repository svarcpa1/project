package cz.uhk.ppro.project.controllers;

import cz.uhk.ppro.project.model.Document;
import cz.uhk.ppro.project.model.Hall;
import cz.uhk.ppro.project.model.Worker;
import cz.uhk.ppro.project.model.Workplace;
import cz.uhk.ppro.project.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DocumentController {

    @Autowired
    TestService testService;

    @GetMapping("/addDocument")
    public String showForm(Model model){
        model.addAttribute("document", new Document());
        List<Workplace> workplaces = testService.findAllWorkplaces();
        model.addAttribute("workplaces", workplaces);
        List<Worker> workers = testService.findAllWorkers();
        model.addAttribute("workers", workers);
        return "addDocumentForm";
    }

    @PostMapping("/addDocument")
    public String processForm(@ModelAttribute("document") Document document, @RequestParam String action){
        //TODO validace

        if( action.equals("save") ){

            Workplace workplace = testService.findWorkplaceById(document.getWorkplace().getId());
            Hall hall = testService.findHallById(workplace.getHall().getId());
            document.setWorkplace(workplace);
            workplace.getDocuments().add(document);

            testService.updateHall(hall);
            return "redirect:/";
        }
        // cancel
        else {
            return "redirect:/";
        }
    }

    @RequestMapping("/deleteDocument/{id}")
    public String deleteDocumentId(@ModelAttribute("document") Document document, @PathVariable("id") long id){
        document = testService.findDocumentById(id);
        Workplace workplace = testService.findWorkplaceById(document.getWorkplace().getId());
        Hall hall = testService.findHallById(workplace.getHall().getId());

        if(document.getWorkerCreated() != null){
            Worker worker = testService.findWorkerById(document.getWorkerCreated().getId());
            worker.removeDocument(document);
        }
        workplace.removeDocument(document);


        testService.updateHall(hall);
        return "redirect:/";

    }

}
