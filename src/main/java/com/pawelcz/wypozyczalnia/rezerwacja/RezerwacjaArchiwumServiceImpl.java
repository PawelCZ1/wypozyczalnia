package com.pawelcz.wypozyczalnia.rezerwacja;

import org.springframework.stereotype.Service;

@Service
public class RezerwacjaArchiwumServiceImpl implements RezerwacjaArchiwumService {

    private final RezerwacjaArchiwumRepozytorium rezerwacjaArchiwumRepozytorium;

    public RezerwacjaArchiwumServiceImpl(RezerwacjaArchiwumRepozytorium rezerwacjaArchiwumRepozytorium) {
        this.rezerwacjaArchiwumRepozytorium = rezerwacjaArchiwumRepozytorium;
    }

    @Override
    public RezerwacjaArchiwum dodajRezerwacjeDoArchiwum(RezerwacjaArchiwum rezerwacjaArchiwum) {
        return rezerwacjaArchiwumRepozytorium.save(rezerwacjaArchiwum);
    }
}
