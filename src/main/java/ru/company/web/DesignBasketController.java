package ru.company.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import ru.company.classes.Basket;
import ru.company.classes.Order;
import ru.company.classes.SanitaryWare;
import ru.company.classes.User;
import ru.company.data.BasketRepository;
import ru.company.data.SanitaryWareRepository;
import ru.company.data.UserRepository;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignBasketController {

    private final SanitaryWareRepository sanitaryWareRepository;

    private final BasketRepository basketRepository;

    private final UserRepository userRepository;

    @Autowired
    public DesignBasketController(
            SanitaryWareRepository sanitaryWareRepository,
            BasketRepository basketRepository,
            UserRepository userRepository) {
        this.sanitaryWareRepository = sanitaryWareRepository;
        this.basketRepository = basketRepository;
        this.userRepository = userRepository;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "design")
    public Basket design() {
        return new Basket();
    }

    @GetMapping
    public String showDesignForm(Model model, Principal principal) {
        List<SanitaryWare> sanitaryWares = new ArrayList<>();
        sanitaryWareRepository.findAll().forEach(i -> sanitaryWares.add(i));

        SanitaryWare.Type[] types = SanitaryWare.Type.values();
        for (SanitaryWare.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(sanitaryWares, type));
        }

        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        model.addAttribute("user", user);

        return "design";
    }

    @PostMapping
    public String processDesign(
            @Valid Basket basket, Errors errors,
            @ModelAttribute Order order) {

        if (errors.hasErrors()) {
            return "design";
        }

        Basket saved = basketRepository.save(basket);
        order.addDesign(saved);

        return "redirect:/orders/current";
    }

    private List<SanitaryWare> filterByType(
            List<SanitaryWare> ingredients, SanitaryWare.Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
