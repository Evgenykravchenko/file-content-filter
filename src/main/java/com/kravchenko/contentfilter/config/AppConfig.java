package com.kravchenko.contentfilter.config;

import lombok.Builder;

/**
 * Конфигурация приложения.
 *
 * @param outputDir выходная директория
 * @param prefix    префикс выходных файлов
 * @param append    режим добавления
 * @param fullStats режим полной статистики
 */
@Builder
public record AppConfig(
        String outputDir,
        String prefix,
        boolean append,
        boolean fullStats
) { }
