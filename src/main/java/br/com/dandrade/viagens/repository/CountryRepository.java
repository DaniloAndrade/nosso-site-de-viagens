package br.com.dandrade.viagens.repository;

import br.com.dandrade.viagens.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository  extends JpaRepository<Country, Long> {

}
