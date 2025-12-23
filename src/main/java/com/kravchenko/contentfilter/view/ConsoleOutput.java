package com.kravchenko.contentfilter.view;

/**
 * Реализация UserOutput, которая выводит сообщения в консоль.
 */
public final class ConsoleOutput implements UserOutput {

    @Override
    public void printMessage(final String message) {
        System.out.println(message);
    }

    @Override
    public void printError(final String message) {
        System.err.println(message);
    }

    @Override
    public void printError(final String message, final Throwable cause) {
        System.err.println(message);
        if (cause != null) {
            cause.printStackTrace();
        }
    }
}
