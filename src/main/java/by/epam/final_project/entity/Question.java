package by.epam.final_project.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Question implements Serializable {

    private static final long serialVersionUID = 508717400744783591L;

    private Integer questionId;
    private String title;
    private String question;
    private Theme theme;
    private User user;
    private List<Answer> answers = new ArrayList<>();


    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return Objects.equals(questionId, question1.questionId) &&
                Objects.equals(title, question1.title) &&
                Objects.equals(question, question1.question) &&
                Objects.equals(theme, question1.theme) &&
                Objects.equals(user, question1.user) &&
                Objects.equals(answers, question1.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, title, question, theme, user, answers);
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", title='" + title + '\'' +
                ", question='" + question + '\'' +
                ", theme=" + theme +
                ", user=" + user +
                ", answers=" + answers +
                '}';
    }

}
