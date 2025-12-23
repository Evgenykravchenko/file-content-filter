package com.kravchenko.contentfilter.view;

/**
 * Интерфейс для отображения сообщений и ошибок пользователю.
 */
public interface UserOutput {

    /**
     * Выводит стандартное сообщение.
     *
     * @param message сообщение для вывода
     */
    void printMessage(String message);

    /**
     * Выводит сообщение об ошибке.
     *
     * @param message сообщение об ошибке
     */
    void printError(String message);

    /**
     * Выводит сообщение об ошибке с причиной.
     *
     * @param message сообщение об ошибке
     * @param cause   причина ошибки
     */
    void printError(String message, Throwable cause);
}
