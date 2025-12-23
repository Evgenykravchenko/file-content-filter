package com.kravchenko.contentfilter;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class Main {

    /**
     * Основная точка входа в приложение.
     *
     * @param args аргументы командной строки
     */
    public static void main(final String[] args) {
        new ContentFilterApp().run(args);
    }
}
