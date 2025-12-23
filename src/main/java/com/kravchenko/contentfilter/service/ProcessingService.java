package com.kravchenko.contentfilter.service;

import com.kravchenko.contentfilter.config.AppConfig;
import com.kravchenko.contentfilter.model.DataType;
import com.kravchenko.contentfilter.parser.FloatParser;
import com.kravchenko.contentfilter.parser.InputProcessor;
import com.kravchenko.contentfilter.parser.IntegerParser;
import com.kravchenko.contentfilter.parser.Parser;
import com.kravchenko.contentfilter.parser.StringParser;
import com.kravchenko.contentfilter.view.UserOutput;
import com.kravchenko.contentfilter.visitor.CompositeVisitor;
import com.kravchenko.contentfilter.visitor.DataVisitor;
import com.kravchenko.contentfilter.visitor.FileWritingVisitor;
import com.kravchenko.contentfilter.visitor.StatisticsVisitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для обработки входных файлов с использованием настроенных парсеров и
 * посетителей.
 */
public final class ProcessingService {

    /**
     * Обработчик вывода.
     */
    private final UserOutput output;

    /**
     * Создает новый ProcessingService.
     *
     * @param userOutput обработчик вывода для сообщений и ошибок
     */
    public ProcessingService(final UserOutput userOutput) {
        this.output = userOutput;
    }

    /**
     * Запускает логику обработки.
     *
     * @param config     конфигурация приложения
     * @param inputFiles список входных файлов для обработки
     */
    public void run(final AppConfig config, final List<String> inputFiles) {
        List<Parser> parsers = List.of(
                new IntegerParser(),
                new FloatParser(),
                new StringParser());
        InputProcessor processor = new InputProcessor(parsers);

        StatisticsVisitor statsVisitor = new StatisticsVisitor(output);

        try (FileWritingVisitor fileVisitor = new FileWritingVisitor(
                config,
                output)) {
            List<DataVisitor> visitors = new ArrayList<>();
            visitors.add(statsVisitor);
            visitors.add(fileVisitor);

            CompositeVisitor compositeVisitor = new CompositeVisitor(visitors);

            for (String fileName : inputFiles) {
                processFile(fileName, processor, compositeVisitor);
            }

            statsVisitor.printReport(config.fullStats());

        } catch (RuntimeException e) {
            output.printError("Execution failed: " + e.getMessage());
        }
    }

    private void processFile(final String fileName,
            final InputProcessor processor,
            final DataVisitor visitor) {
        Path path = Paths.get(fileName);

        if (!Files.exists(path)) {
            output.printError("File not found: " + fileName);
            return;
        }

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            processLines(reader, fileName, processor, visitor);
        } catch (IOException e) {
            output.printError("Error reading file " + fileName + ": "
                    + e.getMessage());
        }
    }

    private void processLines(final BufferedReader reader,
            final String fileName,
            final InputProcessor processor,
            final DataVisitor visitor)
            throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                continue;
            }

            processLine(line, fileName, processor, visitor);
        }
    }

    private void processLine(final String line,
            final String fileName,
            final InputProcessor processor,
            final DataVisitor visitor) {
        try {
            DataType data = processor.process(line);
            data.accept(visitor);
        } catch (Exception e) {
            output.printError("Error processing line in " + fileName
                    + ": " + line + " -> " + e.getMessage());
        }
    }
}
