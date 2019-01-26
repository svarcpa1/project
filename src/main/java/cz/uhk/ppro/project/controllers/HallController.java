package cz.uhk.ppro.project.controllers;

import cz.uhk.ppro.project.model.*;
import cz.uhk.ppro.project.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@Controller
public class HallController {

    @Autowired
    TestService testService;

    @RequestMapping("/createTest")
    public String actionCreateTestData(Model model){
        List<Hall> haly = fillList();
        for (Hall hala:haly) {
            testService.saveEntity(hala);
        }

        model.addAttribute("haly", haly);
        return "hallListView";
    }

    @GetMapping("/addHall")
    public String showForm(Model model){

        if(!model.containsAttribute("hall")) model.addAttribute("hall", new Hall());

        return "addHallForm";
    }

    @PostMapping("/addHall")
    public String processForm(@ModelAttribute("hall") @Valid Hall hall, BindingResult result,
                              @RequestParam String action, RedirectAttributes attributes){

        if(result.hasErrors()){
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.hall", result);
            attributes.addFlashAttribute("hall", hall);
            return "redirect:/addHall";
        }else {
            if( action.equals("save") ){
                testService.saveEntity(hall);
                return "redirect:/";
            }
            // cancel
            else {
                return "redirect:/";
            }
        }
    }

    @RequestMapping("/deleteHall/{id}")
    public String deleteHallId(@ModelAttribute("hall") Hall hall, @PathVariable("id") long id){

        testService.deleteHallById(id);
        return "redirect:/";

    }

    @GetMapping("/")
    public String getAllHalls(Model model){
        List<Hall> haly = testService.findAllHalls();
        model.addAttribute("haly", haly);
        return "hallListView";
    }

    private List<Hall> fillList(){
        Hall h = new Hall("plechy");
        h.setDescription("Pppp lorem ipsum doc addao asdjasjdj Pppp lorem ipsum doc addao asdjasjdj asdoajsdoj " +
                "asdijasdojiaPppp lorem ipsum doc addao asdjasjdj asdoajsdoj asdijasdojiaPppp " +
                "lorem ipsum doc addao asdjasjdj asdoajsdoj asdijasdojiaasdoajsdoj asdijasdojia sad");
        Workplace wp1 = new Workplace("pracoviste1");
        wp1.setDescription("POpois pracoviště sadasd asdhas asdhgasjd asdgasjdg");
        Workplace wp2 = new Workplace("pracoviste2");
        wp2.setDescription("POpois pracoviště 2 sadasd asdhas asdhgasjd asdgasjdg");
        Document doc1 = new Document("dokument1", "xxx");
        Document doc2 = new Document("dokument2", "xxx");

        Role rol1 = new Role("Admin");
        Role rol2 = new Role("Master");

        Worker wrk1 = new Worker("Pavel", "ŠVARC" , rol1);
        Worker wrk2 = new Worker("Ota", "Černý" , rol2);

        doc1.setDateCreated( new GregorianCalendar(2019, 1, 26).getTime());
        doc1.setDateExpired( new GregorianCalendar(2019, 7, 1).getTime());
        doc2.setDateCreated( new GregorianCalendar(2019, 1, 26).getTime());
        doc2.setDateExpired( new GregorianCalendar(2019, 7, 1).getTime());

        rol1.getWorker().add(wrk1);
        rol2.getWorker().add(wrk2);

        h.getWorkplaces().add(wp1);
        h.getWorkplaces().add(wp2);
        wp1.setHall(h);
        wp2.setHall(h);

        doc1.setWorkerCreated(wrk1);
        doc2.setWorkerCreated(wrk2);
        doc1.setWorkplace(wp1);
        doc2.setWorkplace(wp2);
        wp1.getWorkers().add(wrk1);
        wp2.getWorkers().add(wrk2);
        wrk1.setWorkplace(wp1);
        wrk2.setWorkplace(wp2);
        wrk1.getDocumentsCreated().add(doc1);
        wrk2.getDocumentsCreated().add(doc2);

        wp1.getDocuments().add(doc1);
        wp2.getDocuments().add(doc2);
        List<Hall> l = new ArrayList<>();
        l.add(h);

/*        testService.saveEntity(rol1);
        testService.saveEntity(rol2);*/

        return l;
    }

}
