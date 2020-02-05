package br.com.dandrade.viagens.models;

import java.time.LocalDateTime;
import java.util.Collection;

public interface StretchMinimalInfo {

    LocalDateTime getDeparture();

    Collection<Stretch> getStretchs();
}
