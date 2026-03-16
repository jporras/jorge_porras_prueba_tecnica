package com.example.polizas.repository;

import com.example.polizas.domain.Riesgo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class RiesgoRepository {

    private final Map<Long, Riesgo> data = new ConcurrentHashMap<>();

    public Optional<Riesgo> findById(Long id) {
        return Optional.ofNullable(data.get(id));
    }

    public List<Riesgo> findByPolizaId(Long polizaId) {
        return data.values().stream()
                .filter(r -> Objects.equals(r.getPolizaId(), polizaId))
                .toList();
    }

    public Riesgo save(Riesgo riesgo) {
        data.put(riesgo.getId(), riesgo);
        return riesgo;
    }
}