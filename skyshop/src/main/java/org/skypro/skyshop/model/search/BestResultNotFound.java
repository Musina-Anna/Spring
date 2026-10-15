package org.skypro.skyshop.model.search;

public class BestResultNotFound extends Exception {
    public BestResultNotFound(String search) {
        super("Не найдено подходящего результата для поискового запроса:\"" + search + "\"");
    }
}
