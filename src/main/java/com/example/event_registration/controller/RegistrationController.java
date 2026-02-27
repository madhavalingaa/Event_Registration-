package com.example.event_registration.controller;

import com.example.event_registration.model.Participant;
import com.example.event_registration.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private ParticipantRepository participantRepository;

    @GetMapping("/")
    public String showRegistrationForm(Model model) {
        model.addAttribute("participant", new Participant());
        return "registration";
    }

    @PostMapping("/register")
    public String registerParticipant(@ModelAttribute Participant participant, Model model) {
        try {
            participantRepository.save(participant);
            return "redirect:/success";
        } catch (Exception e) {
            model.addAttribute("error", "Email address is already registered. Please use a different email.");
            return "registration";
        }
    }

    @GetMapping("/success")
    public String showSuccessPage() {
        return "success";
    }

    @GetMapping("/participants")
    public String viewParticipants(Model model) {
        model.addAttribute("participants", participantRepository.findAll());
        return "participants";
    }
}
