package br.com.dandrade.viagens.repository;

import br.com.dandrade.viagens.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TicketRepository
        extends JpaRepository<Ticket, Long> , JpaSpecificationExecutor<Ticket> {
}
