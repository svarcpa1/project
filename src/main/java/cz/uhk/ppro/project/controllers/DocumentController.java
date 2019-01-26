package cz.uhk.ppro.project.controllers;

import cz.uhk.ppro.project.model.Document;
import cz.uhk.ppro.project.model.Hall;
import cz.uhk.ppro.project.model.Worker;
import cz.uhk.ppro.project.model.Workplace;
import cz.uhk.ppro.project.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
public class DocumentController {

    @Autowired
    TestService testService;

    @GetMapping("/addDocument")
    public String showForm(Model model){

        if(!model.containsAttribute("document")) model.addAttribute("document", new Document());

        List<Workplace> workplaces = testService.findAllWorkplaces();
        model.addAttribute("workplaces", workplaces);
        List<Worker> workers = testService.findAllWorkers();
        model.addAttribute("workers", workers);

        return "addDocumentForm";
    }

    @PostMapping(value="/addDocument", consumes={"multipart/form-data"})
    public String processForm(@ModelAttribute("document") @Valid Document document, BindingResult result,
                              @RequestParam String action, @Valid @RequestParam("file")
                                MultipartFile file, RedirectAttributes attributes){

        if( action.equals("save") ){

            if(result.hasErrors()) {
                attributes.addFlashAttribute("org.springframework.validation.BindingResult.document", result);
                attributes.addFlashAttribute("document", document);
                return "redirect:/addDocument";
            }else{
                if(file.getContentType() != null && file.getContentType()
                        .equalsIgnoreCase("application/pdf")){
                    document.setFilePath(file.getOriginalFilename());
                    try {
                        document.setFileData(file.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                Workplace workplace = testService.findWorkplaceById(document.getWorkplace().getId());
                Hall hall = testService.findHallById(workplace.getHall().getId());
                document.setWorkplace(workplace);
                workplace.getDocuments().add(document);

                testService.updateHall(hall);
                return "redirect:/";
            }
        }
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

    @GetMapping("/loadDocument/{id}")
    public ResponseEntity<byte[]> loadDocument(@PathVariable("id") long id){

        Document document = testService.findDocumentById(id);
        byte[] documentData = testService.loadDocumentFileById(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.add("content-disposition", "inline;filename=" +  document.getFilePath());
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return new ResponseEntity<>(documentData, headers, HttpStatus.OK);
    }

}
