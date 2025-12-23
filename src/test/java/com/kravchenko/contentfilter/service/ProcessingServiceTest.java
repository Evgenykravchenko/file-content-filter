package com.kravchenko.contentfilter.service;

import com.kravchenko.contentfilter.config.AppConfig;
import com.kravchenko.contentfilter.view.UserOutput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тесты сервиса обработки")
class ProcessingServiceTest {

    @Mock
    private UserOutput output;

    @TempDir
    Path tempDir;

    @Test
    @DisplayName("Должен обрабатывать файл и выводить статистику")
    void shouldProcessFileAndPrintStats() throws IOException {
        Path inputFile = tempDir.resolve("input.txt");
        Files.writeString(inputFile, "10\nstring\n1.5");

        AppConfig config = AppConfig.builder()
                .outputDir(tempDir.toString())
                .build();

        ProcessingService service = new ProcessingService(output);

        service.run(config, List.of(inputFile.toString()));

        verify(output, atLeastOnce()).printMessage(anyString());
        verify(output, never()).printError(anyString());
    }

    @Test
    @DisplayName("Должен сообщать об ошибке если файл не найден")
    void shouldReportErrorIfFileNotFound() {
        AppConfig config = AppConfig.builder().build();
        ProcessingService service = new ProcessingService(output);

        service.run(config, List.of("non_existent_file.txt"));

        verify(output).printError(contains("File not found"));
    }
}
