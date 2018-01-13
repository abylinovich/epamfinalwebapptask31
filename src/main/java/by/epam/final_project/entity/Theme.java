package by.epam.final_project.entity;

import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;

public class Theme implements Serializable {

    private static final long serialVersionUID = -6053165616062052479L;

    private Integer themeId;
    private Locale locale;
    private String title;

    public Integer getThemeId() {
        return themeId;
    }

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }

    public String getTitle() {
        return title;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Theme theme = (Theme) o;
        return themeId == theme.themeId &&
                Objects.equals(locale, theme.locale) &&
                Objects.equals(title, theme.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(themeId, locale, title);
    }

    @Override
    public String toString() {
        return "Theme{" +
                "themeId=" + themeId +
                ", locale=" + locale +
                ", title='" + title + '\'' +
                '}';
    }

}
