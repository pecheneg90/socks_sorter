package skypro.socks_sorter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import skypro.socks_sorter.model.Socks;

import java.util.List;

/**
 * Class repoSocks
 */
@Repository
public interface SocksRepository extends JpaRepository<Socks, Integer> {
    Socks findSocksByColorAndCottonPart(String color, Integer cottonPart);

    List<Socks> findByColorAndCottonPartGreaterThan(String color, Integer cottonPart);

    List<Socks> findByColorAndCottonPartLessThan(String color, Integer cottonPart);

    List<Socks> findByColorAndCottonPartEquals(String color, Integer cottonPart);
}