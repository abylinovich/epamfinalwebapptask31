package by.epam.finalproject.controller.paginator;

import by.epam.finalproject.controller.paginator.impl.PaginatorImpl;

public class PaginatorFactory {

    private static final PaginatorFactory PAGINATOR_FACTORY = new PaginatorFactory();

    private final Paginator paginator = new PaginatorImpl();

    private PaginatorFactory() {
    }

    public static PaginatorFactory getInstance() {
        return PAGINATOR_FACTORY;
    }

    public Paginator getPaginator() {
        return paginator;
    }

}
