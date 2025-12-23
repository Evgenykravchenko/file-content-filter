package com.kravchenko.contentfilter.visitor;

import com.kravchenko.contentfilter.config.AppConfig;
import com.kravchenko.contentfilter.model.FloatData;
import com.kravchenko.contentfilter.model.IntegerData;
import com.kravchenko.contentfilter.model.StringData;
import com.kravchenko.contentfilter.view.UserOutput;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

/**
 * Реализация посетителя, которая записывает данные в файлы.
 */
public final class FileWritingVisitor implements DataVisitor, AutoCloseable {

    /**
     * Конфигурация приложения.
     */
    private final AppConfig config;

    /**
     * Стратегия вывода для сообщений об ошибках.
     */
    private final UserOutput output;

    /**
     * Карта писателей файлов (writers).
     */
    private final Map<String, BufferedWriter> writers = new HashMap<>();

    /**
     * Создает новый FileWritingVisitor.
     *
     * @param appConfig  конфигурация приложения
     * @param userOutput стратегия вывода
     */
    public FileWritingVisitor(final AppConfig appConfig,
            final UserOutput userOutput) {
        this.config = appConfig;
        this.output = userOutput;
    }

    @Override
    public void visit(final IntegerData data) {
        write("integers.txt", data.value().toString());
    }

    @Override
    public void visit(final FloatData data) {
        write("floats.txt", data.value().toString());
    }

    @Override
    public void visit(final StringData data) {
        write("strings.txt", data.value());
    }

    private void write(final String fileName, final String content) {
        try {
            BufferedWriter writer = getWriter(fileName);
            writer.write(content);
            writer.newLine();
        } catch (IOException e) {
            throw new UncheckedIOException("Error writing to file "
                    + fileName, e);
        }
    }

    private BufferedWriter getWriter(final String fileName) throws IOException {
        String fullPathKey;
        String nameWithPrefix = fileName;

        if (config.prefix() != null) {
            nameWithPrefix = config.prefix() + fileName;
        }

        Path path = Paths.get(nameWithPrefix);

        if (config.outputDir() != null && !config.outputDir().isEmpty()) {
            path = Paths.get(config.outputDir(), nameWithPrefix);
        }

        fullPathKey = path.toAbsolutePath().toString();

        if (writers.containsKey(fullPathKey)) {
            return writers.get(fullPathKey);
        }

        Path parent = path.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }

        StandardOpenOption[] options = {
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.WRITE
        };

        if (config.append()) {
            options = new StandardOpenOption[] {
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND,
                    StandardOpenOption.WRITE
            };
        }

        BufferedWriter writer = Files.newBufferedWriter(path,
                StandardCharsets.UTF_8, options);
        writers.put(fullPathKey, writer);

        return writer;
    }

    @Override
    public void close() {
        writers.values().forEach(w -> {
            try {
                w.close();
            } catch (IOException e) {
                output.printError("Error closing file", e);
            }
        });
    }
}
