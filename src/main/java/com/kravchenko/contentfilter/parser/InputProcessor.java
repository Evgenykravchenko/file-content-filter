package com.kravchenko.contentfilter.parser;

import com.kravchenko.contentfilter.model.DataType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Процессор, который делегирует парсинг списку парсеров.
 */
public final class InputProcessor {
    /**
     * Список зарегистрированных парсеров.
     */
    private final List<Parser> parsers;

    /**
     * Создает новый InputProcessor.
     *
     * @param parsersList список парсеров для использования
     */
    public InputProcessor(final List<Parser> parsersList) {
        this.parsers = new ArrayList<>(parsersList);
    }

    /**
     * Обрабатывает строку текста, используя сконфигурированные парсеры.
     *
     * @param line строка для обработки
     * @return распарсенные данные
     */
    public DataType process(final String line) {
        return parsers.stream()
                .map(parser -> parser.parse(line))
                .flatMap(Optional::stream)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        "No parser found for line: " + line));
    }
}
