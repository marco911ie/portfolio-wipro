package com.bigbank.portfolio.api.service.impl;

import com.bigbank.portfolio.api.dto.SymbolSearchDTO;
import com.bigbank.portfolio.api.dto.SymbolSearchListDTO;
import com.bigbank.portfolio.api.datamodel.Portfolio;
import com.bigbank.portfolio.api.dto.PortfolioDTO;
import com.bigbank.portfolio.api.enumeration.AlphaAvantageEnum;
import com.bigbank.portfolio.api.repository.PortfolioRepository;
import com.bigbank.portfolio.api.service.PortfolioService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PortfolioServiceImpl implements PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final HttpUtilsService httpUtilsService;
    private final ModelMapper modelMapper;
    private final String url;
    private final String apiKey;


    @Autowired
    public PortfolioServiceImpl(PortfolioRepository portfolioRepository, HttpUtilsService httpUtilsService, ModelMapper modelMapper,
                                @Value("${stock.rest.url}") String url, @Value("${stock.rest.api-key}") String apiKey) {
        this.portfolioRepository = portfolioRepository;
        this.httpUtilsService = httpUtilsService;
        this.modelMapper = modelMapper;
        this.url = url;
        this.apiKey = apiKey;
    }

    @Override
    public PortfolioDTO add(PortfolioDTO portfolioDTO) {

        String params = AlphaAvantageEnum.SYMBOL_SEARCH.getParams();

        StringBuilder restCall = new StringBuilder(url);
        restCall.append(String.format(params, portfolioDTO.getSymbol(), apiKey));

        log.info("Call: {}", restCall.toString());


        try {
            SymbolSearchListDTO response = httpUtilsService.makeRestCall(restCall.toString(), SymbolSearchListDTO.class);
            List<SymbolSearchDTO> bestMatches = response.getBestMatches().stream()
                    .filter(s -> portfolioDTO.getSymbol().equals(s.getSymbol())).collect(Collectors.toList());
            SymbolSearchDTO result = null;
            if (CollectionUtils.isEmpty(bestMatches)) {

            } else if (bestMatches.size() == 1) {
                result = bestMatches.get(0);
            } else {
                if (!StringUtils.isEmpty(portfolioDTO.getName())) {
                    result = bestMatches.stream().filter(s -> portfolioDTO.getName().equals(s.getName())).findFirst().orElseThrow(() -> new RuntimeException("NOT FOUND"));
                } else {
                    throw new RuntimeException("MULTIPLE matches");
                }

            }

            Portfolio portfolio = new Portfolio();

            portfolio.setName(result.getName());
            portfolio.setSymbol(result.getSymbol());
            portfolio.setType(result.getType());
            portfolio.setNumber(portfolioDTO.getNumber());

            Portfolio saved = portfolioRepository.saveAndFlush(portfolio);

            return modelMapper.map(saved, PortfolioDTO.class);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<PortfolioDTO> getAll() {
        List<Portfolio> results = portfolioRepository.findAll();
        Type listType = new TypeToken<List<Portfolio>>() {
        }.getType();
        return modelMapper.map(results, listType);
    }

    @Override
    public PortfolioDTO get(Long id) {
        return modelMapper.map(getById(id), PortfolioDTO.class);
    }

    @Override
    public PortfolioDTO update(PortfolioDTO portfolioDTO) {
        Portfolio portfolio = getById(portfolioDTO.getId());
        portfolio.setNumber(portfolioDTO.getNumber());
        Portfolio saved = portfolioRepository.saveAndFlush(portfolio);
        return modelMapper.map(saved, PortfolioDTO.class);
    }

    @Override
    public void delete(Long id) {
        Portfolio portfolio = getById(id);
        portfolioRepository.delete(portfolio);
    }


    private Portfolio getById(Long id) {
        Optional<Portfolio> optional = portfolioRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RuntimeException("Portfolio not found");
        }
    }

}
