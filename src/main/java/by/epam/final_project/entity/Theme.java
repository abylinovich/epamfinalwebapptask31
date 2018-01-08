package by.epam.final_project.entity;

import java.io.Serializable;
import java.util.Objects;

public class Theme implements Serializable {

    private static final long serialVersionUID = -6053165616062052479L;

    private String title;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Theme theme = (Theme) o;
        return Objects.equals(title, theme.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return "Theme{" +
                "title='" + title + '\'' +
                '}';
    }

}
