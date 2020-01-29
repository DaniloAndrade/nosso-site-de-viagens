package br.com.dandrade.viagens.repository;

import br.com.dandrade.viagens.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}
