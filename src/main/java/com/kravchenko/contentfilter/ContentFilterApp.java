package com.kravchenko.contentfilter;

import com.kravchenko.contentfilter.config.AppConfig;
import com.kravchenko.contentfilter.service.ProcessingService;
import com.kravchenko.contentfilter.util.CmdLineParser;
import com.kravchenko.contentfilter.view.ConsoleOutput;
import com.kravchenko.contentfilter.view.UserOutput;
import org.apache.commons.cli.ParseException;

/**
 * Класс приложения, отвечающий за разбор аргументов и запуск логики.
 */
public final class ContentFilterApp {

    /**
     * Интерфейс для вывода пользователю.
     */
    private final UserOutput output;

    /**
     * Конструктор по умолчанию инициализирует зависимости.
     */
    public ContentFilterApp() {
        this.output = new ConsoleOutput();
    }

    /**
     * Запускает процесс фильтрации содержимого.
     *
     * @param args аргументы командной строки
     */
    public void run(final String[] args) {
        try {
            CmdLineParser cmdParser = new CmdLineParser();
            CmdLineParser.Result result = cmdParser.parse(args);
            AppConfig config = result.config();

            ProcessingService service = new ProcessingService(output);
            service.run(config, result.inputFiles());

        } catch (ParseException e) {
            output.printError("Error parsing arguments: " + e.getMessage());
        } catch (Exception e) {
            output.printError("An unexpected error occurred: "
                    + e.getMessage());
        }
    }
}
