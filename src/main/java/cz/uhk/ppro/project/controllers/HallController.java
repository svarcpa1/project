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
        }

        return "redirect:/";
    }

    @GetMapping("/addHall")
    public String showForm(Model model){

        if(!model.containsAttribute("hall")) model.addAttribute("hall", new Hall());

        return "addHallForm";
    }

    @PostMapping("/addHall")
    public String processForm(@ModelAttribute("hall") @Valid Hall hall, BindingResult result,
                              @RequestParam String action, RedirectAttributes attributes){

        if( action.equals("save") ){
            if(result.hasErrors()){
                attributes.addFlashAttribute("org.springframework.validation.BindingResult.hall", result);
                attributes.addFlashAttribute("hall", hall);
                return "redirect:/addHall";
            }else {

                attributes.addFlashAttribute("successMsg", "Hala byla úspěšně přidána.");
                testService.saveEntity(hall);
                return "redirect:/";
            }
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping("/deleteHall/{id}")
    public String deleteHallId(@ModelAttribute("hall") Hall hall, RedirectAttributes attributes,
                               @PathVariable("id") long id){

        attributes.addFlashAttribute("successMsg", "Hala byla úspěšně smazána.");

        testService.deleteHallById(id);
        return "redirect:/";
    }

    @GetMapping("/")
    public String getAllHalls(Model model){
        List<Hall> haly = testService.findAllHalls();
        model.addAttribute("haly", haly);

        return "hallListView";
    }

    @GetMapping("/hall/edit/{id}")
    public String editHallView(Model model, @PathVariable("id") long id){

        model.addAttribute("hall", testService.findHallById(id));
        return "addHallForm";
    }

    @PostMapping("/hall/edit/{id}")
    public String editHall(@ModelAttribute("hall") @Valid Hall hall, BindingResult result,
                           @RequestParam String action, RedirectAttributes attributes, @PathVariable("id") long id){

        if( action.equals("save") ){
            if(result.hasErrors()){
                attributes.addFlashAttribute("org.springframework.validation.BindingResult.hall", result);
                attributes.addFlashAttribute("hall", hall);
                return "addHallForm";
            }else {

                attributes.addFlashAttribute("successMsg", "Hala byla úspěšně editována.");

                Hall edittedHall = testService.findHallById(id);
                edittedHall.setName(hall.getName());
                edittedHall.setDescription(hall.getDescription());
                testService.updateHall(edittedHall);
                return "redirect:/";
            }
        } else {
            return "redirect:/";
        }
    }

    private boolean b = true;

    private List<Hall> fillList(){
        Hall h = new Hall("plechy");
        h.setDescription("Pppp lorem ipsum doc addao asdjasjdj Pppp lorem ipsum doc addao asdjasjdj asdoajsdoj " +
                "asdijasdojiaPppp lorem ipsum doc addao asdjasjdj asdoajsdoj asdijasdojiaPppp " +
                "lorem ipsum doc addao asdjasjdj asdoajsdoj asdijasdojiaasdoajsdoj asdijasdojia sad");
        Workplace wp1 = new Workplace("pracoviste1");
        wp1.setDescription("POpois pracoviště sadasd asdhas asdhgasjd asdgasjdg");
        Workplace wp2 = new Workplace("pracoviste2");
        wp2.setDescription("POpois pracoviště 2 sadasd asdhas asdhgasjd asdgasjdg");

        h.getWorkplaces().add(wp1);
        h.getWorkplaces().add(wp2);
        wp1.setHall(h);
        wp2.setHall(h);

      if(b){
          Role rol1 = new Role("ROLE_Admin");
          Role rol2 = new Role("ROLE_Master");
          Worker wrk1 = new Worker("Pavel", "ŠVARC" , rol1);
          BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
          wrk1.setPassword( passwordEncoder.encode("123"));
          Worker wrk2 = new Worker("Ota", "Černý" , rol2);
          wrk2.setPassword( passwordEncoder.encode("123"));
          wp1.getWorkers().add(wrk1);
          wp2.getWorkers().add(wrk2);
          wrk1.setWorkplace(wp1);
          wrk2.setWorkplace(wp2);
          b=false;
      }

        List<Hall> l = new ArrayList<>();
        l.add(h);

        return l;
    }
}
