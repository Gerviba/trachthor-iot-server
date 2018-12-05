package hu.trackthor.server.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hu.trackthor.server.model.Poligon;

@Repository
public interface PoligonRepository extends CrudRepository<Poligon, Long> {

}
