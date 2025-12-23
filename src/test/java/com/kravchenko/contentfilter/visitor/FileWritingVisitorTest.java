package com.kravchenko.contentfilter.visitor;

import com.kravchenko.contentfilter.config.AppConfig;
import com.kravchenko.contentfilter.model.IntegerData;
import com.kravchenko.contentfilter.view.UserOutput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тесты посетителя записи файлов")
class FileWritingVisitorTest {

    @Mock
    private UserOutput output;

    @TempDir
    Path tempDir;

    @Test
    @DisplayName("Должен записывать данные в файлы по умолчанию")
    void shouldWriteDataToDefaultFiles() throws IOException {
        AppConfig config = AppConfig.builder()
                .outputDir(tempDir.toString())
                .build();

        try (FileWritingVisitor visitor = new FileWritingVisitor(config, output)) {
            visitor.visit(new IntegerData(BigInteger.valueOf(42)));
        }

        Path expectedFile = tempDir.resolve("integers.txt");
        assertTrue(Files.exists(expectedFile));
        List<String> lines = Files.readAllLines(expectedFile);
        assertEquals(1, lines.size());
        assertEquals("42", lines.get(0));
    }

    @Test
    @DisplayName("Должен использовать префикс для имен файлов")
    void shouldUsePrefix() throws IOException {
        AppConfig config = AppConfig.builder()
                .outputDir(tempDir.toString())
                .prefix("test_")
                .build();

        try (FileWritingVisitor visitor = new FileWritingVisitor(config, output)) {
            visitor.visit(new IntegerData(BigInteger.valueOf(100)));
        }

        Path expectedFile = tempDir.resolve("test_integers.txt");
        assertTrue(Files.exists(expectedFile));
    }
}
