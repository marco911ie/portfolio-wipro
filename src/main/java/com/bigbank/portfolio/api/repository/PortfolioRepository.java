package com.bigbank.portfolio.api.repository;

import com.bigbank.portfolio.api.datamodel.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PortfolioRepository  extends JpaRepository<Portfolio, Long> {
    List<Portfolio> findBySymbol(String symbol);
}
