package com.bigbank.portfolio.api.controller;

import com.bigbank.portfolio.api.dto.PortfolioDTO;
import com.bigbank.portfolio.api.service.PortfolioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping(value = "/opi/api/portfolio/v1.0")
@RestController
@Slf4j
public class PortfolioController {

    private final PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }


    @PostMapping(value = "/add")
    @ResponseStatus(HttpStatus.OK)
    public PortfolioDTO addToPortfolio(@RequestBody final PortfolioDTO portfolio)  {
        log.info("addToPortfolio: {} " , portfolio.getSymbol());
        PortfolioDTO responseDocument = portfolioService.add(portfolio);
        return responseDocument;
    }

    @GetMapping(value = "/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PortfolioDTO get(@PathVariable final Long id)  {
        log.info("addToPortfolio: {} " , id);
        return portfolioService.get(id);
    }

    @GetMapping(value = "/get")
    @ResponseStatus(HttpStatus.OK)
    public List<PortfolioDTO> get()  {
        log.info("getAll: {} ");
        return portfolioService.getAll();
    }


    @PutMapping(value = "/update")
    @ResponseStatus(HttpStatus.OK)
    public PortfolioDTO update(@RequestBody final PortfolioDTO portfolio)  {
        log.info("addToPortfolio: {} " , portfolio.getSymbol());
        PortfolioDTO responseDocument = portfolioService.update(portfolio);
        return responseDocument;
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable final Long id)  {
        log.info("addToPortfolio: {} " , id);
        portfolioService.delete(id);

    }


}
