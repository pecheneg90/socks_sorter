package skypro.socks_sorter.service.socksServiceImpl;

import org.springframework.stereotype.Service;
import skypro.socks_sorter.model.Socks;
import skypro.socks_sorter.repository.SocksRepository;
import skypro.socks_sorter.service.SocksService;

/**
 * Class serviceImpl
 */
@Service
public class SocksServiceImpl implements SocksService {

    private final SocksRepository socksRepository;

    public SocksServiceImpl(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }

    @Override
    public void income(Socks socks) {
        Socks socksFromDataBase = socksRepository.findSocksByColorAndCottonPart(
                socks.getColor(), socks.getCottonPart());
        if (socksFromDataBase != null) {
            socksFromDataBase.setQuantity(socksFromDataBase.getQuantity()
                    + socks.getQuantity());
            socksRepository.save(socksFromDataBase);
        } else {
            socksRepository.save(socks);
        }
    }

    public void outcome(Socks socks) {
        Socks socksFromDataBase = socksRepository
                .findSocksByColorAndCottonPart(socks.getColor(), socks.getCottonPart());
        if (socksFromDataBase != null) {
            socksFromDataBase
                    .setQuantity(socksFromDataBase.getQuantity() - socks.getQuantity());
            socksRepository.save(socksFromDataBase);
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public long getAllSocks(String color, String operation, Integer cottonPart) {
        if (cottonPart > 100 || cottonPart < 0) {
            throw new IllegalArgumentException();
        }
        if (operation.equals("moreThan")) {
            return socksRepository
                    .findByColorAndCottonPartGreaterThan(color, cottonPart)
                    .stream()
                    .mapToLong(Socks::getQuantity)
                    .sum();
        }
        if (operation.equals("lessThan")) {
            return socksRepository
                    .findByColorAndCottonPartLessThan(color, cottonPart)
                    .stream()
                    .mapToLong(Socks::getQuantity)
                    .sum();
        }
        if (operation.equals("equals")) {
            return socksRepository
                    .findByColorAndCottonPartEquals(color, cottonPart)
                    .stream()
                    .mapToLong(Socks::getQuantity)
                    .sum();
        }
        throw new NullPointerException();
    }
}