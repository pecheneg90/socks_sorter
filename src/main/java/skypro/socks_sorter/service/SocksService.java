package skypro.socks_sorter.service;

import skypro.socks_sorter.model.Socks;

/**
 * Class service
 */
public interface SocksService {

    void income(Socks socks);

    void outcome(Socks socks);

    long getAllSocks(String color, String operation, Integer cottonPart);
}