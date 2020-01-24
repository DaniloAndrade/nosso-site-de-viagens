package br.com.dandrade.viagens.repository;

import br.com.dandrade.viagens.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    boolean existsByName(String name);
}
