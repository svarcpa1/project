package cz.uhk.ppro.project.controllers;

import cz.uhk.ppro.project.model.*;
import cz.uhk.ppro.project.services.DBFileStorageService;
import cz.uhk.ppro.project.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@Controller
public class DocumentController {

    @Autowired
    TestService testService;
    @Autowired
    private cz.uhk.ppro.project.services.DBFileStorageService DBFileStorageService;

    @GetMapping("/addDocument")
    public String showForm(Model model){
        model.addAttribute("document", new Document());
        List<Workplace> workplaces = testService.findAllWorkplaces();
        model.addAttribute("workplaces", workplaces);
        List<Worker> workers = testService.findAllWorkers();
        model.addAttribute("workers", workers);
        return "addDocumentForm";
    }

    @PostMapping(value="/addDocument", consumes={"multipart/form-data"})
    public String processForm(@ModelAttribute("document") Document document, @RequestParam String action, @Valid @RequestParam("file")
            MultipartFile file){
        //TODO validace


        if( action.equals("save") ){

            System.out.print(file.getOriginalFilename());

            Workplace workplace = testService.findWorkplaceById(document.getWorkplace().getId());
            Hall hall = testService.findHallById(workplace.getHall().getId());
            document.setWorkplace(workplace);
            workplace.getDocuments().add(document);


            DBFile dbFile = DBFileStorageService.storeFile(file);
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/downloadFile/")
                    .path(dbFile.getId())
                    .toUriString();


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
