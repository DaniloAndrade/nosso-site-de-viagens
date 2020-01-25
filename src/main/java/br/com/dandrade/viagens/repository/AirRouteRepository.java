package br.com.dandrade.viagens.repository;

import br.com.dandrade.viagens.models.AirRoute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirRouteRepository extends JpaRepository<AirRoute, Long> {
}
