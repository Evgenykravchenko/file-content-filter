package com.kravchenko.contentfilter.util;

import com.kravchenko.contentfilter.config.AppConfig;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.util.ArrayList;
import java.util.List;

/**
 * Парсер аргументов командной строки.
 */
public final class CmdLineParser {

    /**
     * Результат парсинга.
     *
     * @param config     конфигурация приложения
     * @param inputFiles список входных файлов
     */
    public record Result(AppConfig config, List<String> inputFiles) {
        /**
         * Компактный конструктор, связанный с объявлением записи.
         */
        public Result {
            inputFiles = new ArrayList<>(inputFiles);
        }

        @Override
        public List<String> inputFiles() {
            return new ArrayList<>(inputFiles);
        }
    }

    /**
     * Парсит аргументы командной строки.
     *
     * @param args массив аргументов
     * @return результат парсинга, содержащий конфигурацию и входные файлы
     * @throws ParseException если парсинг не удался
     */
    public Result parse(final String[] args) throws ParseException {
        Options options = new Options();
        options.addOption("o", "output", true, "Output directory path");
        options.addOption("p", "prefix", true, "Output file name prefix");
        options.addOption("a", "append", false, "Append to existing files");
        options.addOption("s", "short", false, "Short statistics");
        options.addOption("f", "full", false, "Full statistics");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        String outputDir = cmd.getOptionValue("o", "");
        String prefix = cmd.getOptionValue("p", "");
        boolean append = cmd.hasOption("a");
        boolean fullStats = cmd.hasOption("f");

        List<String> inputFiles = cmd.getArgList();
        if (inputFiles.isEmpty()) {
            throw new ParseException("No input files specified");
        }

        AppConfig config = AppConfig.builder()
                .outputDir(outputDir)
                .prefix(prefix)
                .append(append)
                .fullStats(fullStats)
                .build();
        return new Result(config, inputFiles);
    }
}
