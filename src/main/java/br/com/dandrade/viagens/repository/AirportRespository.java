package br.com.dandrade.viagens.repository;

import br.com.dandrade.viagens.models.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRespository extends JpaRepository<Airport, Long> {
    boolean existsByName(String name);
}
