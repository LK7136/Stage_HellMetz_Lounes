package com.hellmetz.festival.controller;

import com.hellmetz.festival.service.ArtisteService;
import com.hellmetz.festival.service.DashboardService;
import com.hellmetz.festival.service.EditionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping({"/", "/dashboard"})
    public String dashboard(Model model) {
        model.addAttribute("pageTitle", "Tableau de bord");
        model.addAttribute("activeMenu", "dashboard");
        model.addAttribute("vue", dashboardService.build());
        return "dashboard";
    }
}
