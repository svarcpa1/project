package cz.uhk.ppro.project.controllers;

import cz.uhk.ppro.project.model.Hall;
import cz.uhk.ppro.project.model.Role;
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
public class WorkplaceController {

    @Autowired
    TestService testService;

    @GetMapping("/addWorkplace")
    public String showForm(Model model){

        if(!model.containsAttribute("workplace")) model.addAttribute("workplace", new Workplace());

        List<Hall> haly = testService.findAllHalls();
        model.addAttribute("haly", haly);

        return "addWorkplaceForm";
    }

    @PostMapping("/addWorkplace")
    public String processForm(@ModelAttribute("workplace") @Valid Workplace workplace, BindingResult result,
                              @RequestParam String action, RedirectAttributes attributes){

        if( action.equals("save") ){
            if (result.hasErrors()){
                attributes.addFlashAttribute("org.springframework.validation.BindingResult.workplace", result);
                attributes.addFlashAttribute("workplace", workplace);
                return "redirect:/addWorkplace";

            }else {
                Hall hall = testService.findHallById(workplace.getHall().getId());
                workplace.setHall(hall);
                hall.getWorkplaces().add(workplace);

                attributes.addFlashAttribute("successMsg", "Pracoviště bylo úspěšně přidáno.");

                testService.updateHall(hall);
                return "redirect:/";
            }
        } else{
            return "redirect:/";
        }
    }

    @RequestMapping("hall/{hall.id}/deleteWorkplace/{id}")
    public String deleteWorkplaceId(@ModelAttribute("workplace") Workplace workplace, @PathVariable("id") long id,
                                    @PathVariable("hall.id") long hallId, RedirectAttributes attributes){
        Hall hall = testService.findHallById(hallId);
        workplace = testService.findWorkplaceById(id);

        hall.removeWorkplace(workplace);

        attributes.addFlashAttribute("successMsg", "Pracoviště bylo úspěšně smazáno.");

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

            List<Worker> workers = testService.findAllWorkers();
            model.addAttribute("workers", workers);

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

    @GetMapping("/workplace/edit/{id}")
    public String editWorkplaceView(Model model, @PathVariable("id") long id){
        if(!model.containsAttribute("workplace"))  model.addAttribute("workplace", testService.findWorkplaceById(id));


        List<Hall> haly = testService.findAllHalls();
        model.addAttribute("haly", haly);

        return "addWorkplaceForm";
    }

    @PostMapping("/workplace/edit/{id}")
    public String editWorkplace(@ModelAttribute("workplace") @Valid Workplace workplace, BindingResult result,
                                @RequestParam String action, RedirectAttributes attributes, @PathVariable("id") long id){

        if( action.equals("save") ){
            if(result.hasErrors()){
                attributes.addFlashAttribute("org.springframework.validation.BindingResult.workplace", result);
                attributes.addFlashAttribute("workplace", workplace);
                return "redirect:/workplace/edit/{id}";
            }else {

                attributes.addFlashAttribute("successMsg", "Pracoviště bylo úspěšně editováno.");

                Workplace edittedWorkplace = testService.findWorkplaceById(id);

                edittedWorkplace.setName(workplace.getName());
                edittedWorkplace.setHall(workplace.getHall());
                edittedWorkplace.setDescription(workplace.getDescription());

                testService.updateWorkplace(edittedWorkplace);
                return "redirect:/";
            }
        } else {
            return "redirect:/";
        }
    }

}
