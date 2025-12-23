package com.kravchenko.contentfilter.util;

import com.kravchenko.contentfilter.config.AppConfig;
import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты парсера командной строки")
class CmdLineParserTest {

    private final CmdLineParser parser = new CmdLineParser();

    @Test
    @DisplayName("Должен корректно парсить флаги и входные файлы")
    void shouldParseFlagsAndInputFiles() throws ParseException {
        String[] args = { "-o", "out", "-p", "pre_", "-a", "-f", "in1.txt", "in2.txt" };

        CmdLineParser.Result result = parser.parse(args);

        AppConfig config = result.config();
        assertEquals("out", config.outputDir());
        assertEquals("pre_", config.prefix());
        assertTrue(config.append());
        assertTrue(config.fullStats());

        List<String> inputFiles = result.inputFiles();
        assertEquals(2, inputFiles.size());
        assertTrue(inputFiles.contains("in1.txt"));
        assertTrue(inputFiles.contains("in2.txt"));
    }

    @Test
    @DisplayName("Должен выбрасывать ошибку если нет входных файлов")
    void shouldThrowIfNoInputFiles() {
        String[] args = { "-o", "out" };

        assertThrows(ParseException.class, () -> parser.parse(args));
    }
}
