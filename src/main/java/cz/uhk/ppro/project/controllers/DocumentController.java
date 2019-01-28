package cz.uhk.ppro.project.controllers;

import cz.uhk.ppro.project.model.*;
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

import javax.persistence.Id;
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

                attributes.addFlashAttribute("successMsg", "Dokumentace byla úspěšně přidána.");

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
    public String deleteDocumentId(@ModelAttribute("document") Document document, @PathVariable("id") long id,
                                   RedirectAttributes attributes){
        document = testService.findDocumentById(id);
        Workplace workplace = testService.findWorkplaceById(document.getWorkplace().getId());
        Hall hall = testService.findHallById(workplace.getHall().getId());

        if(document.getWorkerCreated() != null){
            Worker worker = testService.findWorkerById(document.getWorkerCreated().getId());
            worker.removeDocument(document);
        }
        workplace.removeDocument(document);

        attributes.addFlashAttribute("successMsg", "Dokumentace byla úspěšně smazána.");

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

    @GetMapping("/document/edit/{id}")
    public String editDocView(Model model, @PathVariable("id") long id){

        model.addAttribute("document", testService.findDocumentById(id));

        List<Workplace> workplaces = testService.findAllWorkplaces();
        model.addAttribute("workplaces", workplaces);
        List<Worker> workers = testService.findAllWorkers();
        model.addAttribute("workers", workers);

        return "addDocumentForm";
    }

    @PostMapping("/document/edit/{id}")
    public String editDoc(@ModelAttribute("document") @Valid Document document, BindingResult result,
                           @RequestParam String action, RedirectAttributes attributes, @PathVariable("id") long id,
                          @Valid @RequestParam("file") MultipartFile file){

        if( action.equals("save") ){
            if(result.hasErrors()){
                attributes.addFlashAttribute("org.springframework.validation.BindingResult.document", result);
                attributes.addFlashAttribute("document", document);
                return "addDocumentForm";
            }else {

                attributes.addFlashAttribute("successMsg", "Dokumentace byla úspěšně editována.");

                Document edittedDoc = testService.findDocumentById(id);

                if(file.getContentType() != null && file.getContentType()
                        .equalsIgnoreCase("application/pdf")){
                    document.setFilePath(file.getOriginalFilename());
                    try {
                        document.setFileData(file.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                edittedDoc.setName(document.getName());
                edittedDoc.setDescription(document.getDescription());
                edittedDoc.setWorkerCreated(document.getWorkerCreated());
                edittedDoc.setWorkplace(document.getWorkplace());
                edittedDoc.setDateCreated(document.getDateCreated());
                edittedDoc.setDateExpired(document.getDateExpired());
                edittedDoc.setFileData(document.getFileData());
                edittedDoc.setFilePath(document.getFilePath());

                testService.updateDoc(edittedDoc);
                return "redirect:/";
            }
        } else {
            return "redirect:/";
        }
    }
}
