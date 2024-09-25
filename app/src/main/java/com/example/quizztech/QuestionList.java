package com.example.quizztech;

public class QuestionList {

    private String question, opcion1, opcion2, opcion3, opcion4, answer;
    private String usuarioSeleccionadoAnswer;

    public QuestionList(String question, String usuarioSeleccionadoAnswer, String opcion1, String opcion2, String opcion3,String opcion4, String answer) {
        this.question = question;
        this.usuarioSeleccionadoAnswer = usuarioSeleccionadoAnswer;
        this.opcion1 = opcion1;
        this.opcion2 = opcion2;
        this.opcion3 = opcion3;
        this.opcion4 = opcion4;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getOpcion1() {
        return opcion1;
    }

    public String getOpcion2() {
        return opcion2;
    }

    public String getOpcion3() {
        return opcion3;
    }

    public String getOpcion4() {
        return opcion4;
    }

    public String getAnswer() {
        return answer;
    }

    public String getUsuarioSeleccionadoAnswer() {
        return usuarioSeleccionadoAnswer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setOpcion1(String opcion1) {
        this.opcion1 = opcion1;
    }

    public void setOpcion2(String opcion2) {
        this.opcion2 = opcion2;
    }

    public void setOpcion3(String opcion3) {
        this.opcion3 = opcion3;
    }

    public void setOpcion4(String opcion4) {
        this.opcion4 = opcion4;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setUsuarioSeleccionadoAnswer(String usuarioSeleccionadoAnswer) {
        this.usuarioSeleccionadoAnswer = usuarioSeleccionadoAnswer;
    }
}
