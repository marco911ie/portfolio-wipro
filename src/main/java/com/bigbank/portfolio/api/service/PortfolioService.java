package com.bigbank.portfolio.api.service;

import com.bigbank.portfolio.api.dto.PortfolioDTO;

import java.util.List;

public interface PortfolioService {

    PortfolioDTO add(PortfolioDTO portfolioDTO);
    PortfolioDTO get(Long id);
    List<PortfolioDTO> getAll();
    PortfolioDTO update(PortfolioDTO portfolioDTO);
    void delete(Long id);
}
