package com.kravchenko.contentfilter.parser;

import com.kravchenko.contentfilter.model.DataType;
import java.util.Optional;

/**
 * Интерфейс для парсинга строки текста в определенный тип данных.
 */
public interface Parser {

    /**
     * Парсит заданную строку.
     *
     * @param line строка для парсинга
     * @return Optional, содержащий распарсенные данные, или пустой,
     * если парсинг не удался
     */
    Optional<DataType> parse(String line);
}
