package com.example.polizas.repository;

import com.example.polizas.domain.Poliza;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class PolizaRepository {

    private final Map<Long, Poliza> data = new ConcurrentHashMap<>();

    public List<Poliza> findAll() {
        return new ArrayList<>(data.values());
    }

    public Optional<Poliza> findById(Long id) {
        return Optional.ofNullable(data.get(id));
    }

    public Poliza save(Poliza poliza) {
        data.put(poliza.getId(), poliza);
        return poliza;
    }
}