package by.epam.final_project.entity;

import java.io.Serializable;
import java.util.Objects;

public class Answer implements Serializable {

    private static final long serialVersionUID = 2651881188733250257L;

    private Integer answerId;
    private String answer;
    private Question question;
    private User user;

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer1 = (Answer) o;
        return Objects.equals(answerId, answer1.answerId) &&
                Objects.equals(answer, answer1.answer) &&
                Objects.equals(question, answer1.question) &&
                Objects.equals(user, answer1.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answerId, answer, question, user);
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answerId=" + answerId +
                ", answer='" + answer + '\'' +
                ", question=" + question +
                ", user=" + user +
                '}';
    }

}
