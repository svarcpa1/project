package cz.uhk.ppro.project.controllers;

import cz.uhk.ppro.project.model.*;
import cz.uhk.ppro.project.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        List<Role> roles = testService.findAllRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("edit",false);
        return "addWorkerForm";
    }
    private int counter = 0;
    @PostMapping("/addWorker")
    public String processForm(@ModelAttribute("worker") @Valid Worker worker, BindingResult result,
                              @RequestParam String action, RedirectAttributes attributes){

        if( action.equals("save") ) {

            if (result.hasErrors()) {
                attributes.addFlashAttribute("org.springframework.validation.BindingResult.worker", result);
                attributes.addFlashAttribute("worker", worker);
                return "redirect:/addWorker";
            } else {
                counter+=1;
                Workplace workplace = testService.findWorkplaceById(worker.getWorkplace().getId());
                Hall hall = testService.findHallById(workplace.getHall().getId());
                Role role = testService.findRoleById(worker.getRole().getId());
                worker.setRole(role);
                worker.setWorkplace(workplace);
                worker.setLogin(worker.getFirstName() + "." + worker.getSurName()+counter);
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                worker.setPassword( passwordEncoder.encode(worker.getPassword()));

                workplace.getWorkers().add(worker);

                attributes.addFlashAttribute("successMsg", "Pracovník byl úspěšně přidán.");

                testService.updateHall(hall);
                return "redirect:/";
            }
        }else {
            return "redirect:/";
        }
    }

    @RequestMapping("/deleteWorker/{id}")
    public String deleteWorkerId(@ModelAttribute("worker") Worker worker,
                                 @PathVariable("id") long id, RedirectAttributes attributes){
        worker = testService.findWorkerById(id);
        Workplace workplace = testService.findWorkplaceById(worker.getWorkplace().getId());
        Hall hall = testService.findHallById(workplace.getHall().getId());

        workplace.removeWorker(worker);
        List<Document> documents = worker.getDocumentsCreated();
        for (Document document: documents) {
            document.setWorkerCreated(null);
        }

        attributes.addFlashAttribute("successMsg", "Pracovník byl úspěšně smazán.");

        testService.updateHall(hall);
        return "redirect:/";
    }

    @GetMapping("/worker/edit/{id}")
    public String editWorkerView(Model model, @PathVariable("id") long id){

        if(!model.containsAttribute("worker")) model.addAttribute("worker", testService.findWorkerById(id));
        List<Workplace> workplaces = testService.findAllWorkplaces();
        model.addAttribute("workplaces", workplaces);
        List<Role> roles = testService.findAllRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("edit",true);
        return "addWorkerForm";
    }

    @PostMapping("/worker/edit/{id}")
    public String editWorker(@ModelAttribute("worker") @Valid Worker worker, BindingResult result,
                             @RequestParam String action, RedirectAttributes attributes, @PathVariable("id") long id){

        if( action.equals("save") ){
            if(result.hasErrors()){
                attributes.addFlashAttribute("org.springframework.validation.BindingResult.worker", result);
                attributes.addFlashAttribute("worker", worker);
                return "redirect:/worker/edit/{id}";
            }else {

                Worker edittedWorker = testService.findWorkerById(id);

                edittedWorker.setFirstName(worker.getFirstName());
                edittedWorker.setSurName(worker.getSurName());
                edittedWorker.setRole(testService.findRoleById(worker.getRole().getId()));
                edittedWorker.setWorkplace(worker.getWorkplace());

                attributes.addFlashAttribute("successMsg", "Pracovník byl úspěšně editován.");

                testService.updateWorker(edittedWorker);
                return "redirect:/";
            }
        } else {
            return "redirect:/";
        }
    }
}
