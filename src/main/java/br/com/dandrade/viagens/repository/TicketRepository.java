package br.com.dandrade.viagens.repository;

import br.com.dandrade.viagens.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
