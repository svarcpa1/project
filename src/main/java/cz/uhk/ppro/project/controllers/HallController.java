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

import java.util.ArrayList;
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
            for (Workplace workplace: hala.getWorkplaces()) {
                testService.saveEntity(workplace);
                for (Worker worker: workplace.getWorkers()) {
                    testService.saveEntity(worker);
                }
                for (Document document: workplace.getDocuments()) {
                    testService.saveEntity(document);
                }
            }
        }

        model.addAttribute("haly", haly);
        return "hallListView";
    }



    @GetMapping("/addHall")
    public String showForm(Model model){
        model.addAttribute("hall", new Hall());
        return "addHallForm";
    }

    @PostMapping("/addHall")
    public String processForm(@ModelAttribute("hall") Hall hall){
        //TODO validace
        testService.saveEntity(hall);
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
        h.setDescription("Pppp lorem ipsum doc addao asdjasjdj Pppp lorem ipsum doc addao asdjasjdj asdoajsdoj asdijasdojiaPppp lorem ipsum doc addao asdjasjdj asdoajsdoj asdijasdojiaPppp lorem ipsum doc addao asdjasjdj asdoajsdoj asdijasdojiaasdoajsdoj asdijasdojia sad");
        Workplace wp1 = new Workplace("pracoviste1");
        wp1.setDescription("POpois pracoviště sadasd asdhas asdhgasjd asdgasjdg");
        Workplace wp2 = new Workplace("pracoviste2");
        wp2.setDescription("POpois pracoviště 2 sadasd asdhas asdhgasjd asdgasjdg");
        Document doc1 = new Document("dokument1", "xxx");
        Document doc2 = new Document("dokument2", "xxx");
        Worker wrk1 = new Worker("Pavel", "ŠVARC" , "BOSS");
        Worker wrk2 = new Worker("Ota", "Černý" , "Otrok");

        h.getWorkplaces().add(wp1);
        h.getWorkplaces().add(wp2);
        wp1.setHall(h);
        wp2.setHall(h);
        wp1.getDocuments().add(doc1);
        wp2.getDocuments().add(doc2);
        doc1.setWorkplace(wp1);
        doc2.setWorkplace(wp2);
        wp1.getWorkers().add(wrk1);
        wp2.getWorkers().add(wrk2);
        wrk1.setWorkplace(wp1);
        wrk2.setWorkplace(wp2);
        wrk1.getDocumentsCreated().add(doc1);
        wrk2.getDocumentsCreated().add(doc2);
        doc1.setWorkerCreated(wrk1);
        doc2.setWorkerCreated(wrk2);
        List<Hall> l = new ArrayList<>();
        l.add(h);
        return l;
    }

}
