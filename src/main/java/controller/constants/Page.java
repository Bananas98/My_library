package controller.constants;

public final class Page {

    public static String PREFIX = "/WEB-INF/pages/";
    public static String SUFFIX = ".jsp";

    public static String HOME = "/index" + SUFFIX;
    public static String PAGE_NOT_FOUND = PREFIX + "pageNotFound" + SUFFIX;

    public static String LOGIN_PAGE = PREFIX + "login" + SUFFIX;
    public static String SIGN_UP = PREFIX + "signUp" + SUFFIX;

    private Page() {}
}
