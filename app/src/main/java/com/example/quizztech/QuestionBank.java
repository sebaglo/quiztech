package com.example.quizztech;

import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

public class QuestionBank {

    private static List<QuestionList> javaQuestions(){

        final List<QuestionList> questionLists = new ArrayList<>();

        //crear objetos de clase de lista de preguntas y pasar una pregunta junto con la opción y la respuesta
        final QuestionList question1 = new QuestionList("¿Cual es el tamaño de una variable int?", "", "16bits","32bits","160bits","1bits","16bits");
        final QuestionList question2 = new QuestionList("¿cual es el valor de una variable boolean?", "", "Verdadero","Verdadero o falso","Falso","Verdadero o falso","nulo");
        final QuestionList question3 = new QuestionList("¿En que año se creo java?", "", "1978","1990","1890","1995","1995");
        final QuestionList question4 = new QuestionList("¿Existen mas de 2 tipos de Intent?", "", "Nulo","Falso","Verdadero","No existe","Verdadero");
        final QuestionList question5 = new QuestionList("¿Ques es java?", "", "lenguaje de programacion","un programa","null","un juego","lenguaje de programacion");
        final QuestionList question6 = new QuestionList("¿Cuantas clases hay en java?", "", "2","1","4","3","4s ");

        //agregar todas las preguntas a la List<QuestionList>

        questionLists.add(question1);
        questionLists.add(question2);
        questionLists.add(question3);
        questionLists.add(question4);
        questionLists.add(question5);
        questionLists.add(question6);

        return questionLists;
    }

    private static List<QuestionList> phpQuestions(){

        final List<QuestionList> questionLists = new ArrayList<>();

        //crear objetos de clase de lista de preguntas y pasar una pregunta junto con la opción y la respuesta
        final QuestionList question1 = new QuestionList("¿Cual es el tamaño de una variable int?", "", "16bits","32bits","160bits","16bits","1bits");
        final QuestionList question2 = new QuestionList("¿cual es el valor de una variable boolean?", "", "Verdadero","Verdadero o falso","Falso","Verdadero o falso","nulo");
        final QuestionList question3 = new QuestionList("¿Cual es el tamaño de una variable int?", "", "16bits","32bits","160bits","16bits","1bits");
        final QuestionList question4 = new QuestionList("¿cual es el valor de una variable boolean?", "", "Verdadero","Verdadero o falso","Falso","Verdadero o falso","nulo");
        final QuestionList question5 = new QuestionList("¿Cual es el tamaño de una variable int?", "", "16bits","32bits","160bits","16bits","1bits");
        final QuestionList question6 = new QuestionList("¿cual es el valor de una variable boolean?", "", "Verdadero","Verdadero o falso","Falso","Verdadero o falso","nulo");

        //agregar todas las preguntas a la List<QuestionList>

        questionLists.add(question1);
        questionLists.add(question2);
        questionLists.add(question3);
        questionLists.add(question4);
        questionLists.add(question5);
        questionLists.add(question6);

        return questionLists;
    }

    private static List<QuestionList> htmlQuestions(){

        final List<QuestionList> questionLists = new ArrayList<>();

        //crear objetos de clase de lista de preguntas y pasar una pregunta junto con la opción y la respuesta
        final QuestionList question1 = new QuestionList("¿Cual es el tamaño de una variable int?", "", "16bits","32bits","160bits","16bits","1bits");
        final QuestionList question2 = new QuestionList("¿cual es el valor de una variable boolean?", "", "Verdadero","Verdadero o falso","Falso","Verdadero o falso","nulo");
        final QuestionList question3 = new QuestionList("¿Cual es el tamaño de una variable int?", "", "16bits","32bits","160bits","16bits","1bits");
        final QuestionList question4 = new QuestionList("¿cual es el valor de una variable boolean?", "", "Verdadero","Verdadero o falso","Falso","Verdadero o falso","nulo");
        final QuestionList question5 = new QuestionList("¿Cual es el tamaño de una variable int?", "", "16bits","32bits","160bits","16bits","1bits");
        final QuestionList question6 = new QuestionList("¿cual es el valor de una variable boolean?", "", "Verdadero","Verdadero o falso","Falso","Verdadero o falso","nulo");

        //agregar todas las preguntas a la List<QuestionList>

        questionLists.add(question1);
        questionLists.add(question2);
        questionLists.add(question3);
        questionLists.add(question4);
        questionLists.add(question5);
        questionLists.add(question6);

        return questionLists;
    }
    private static List<QuestionList> androidQuestions(){

        final List<QuestionList> questionLists = new ArrayList<>();

        //crear objetos de clase de lista de preguntas y pasar una pregunta junto con la opción y la respuesta
        final QuestionList question1 = new QuestionList("¿Cual es el tamaño de una variable int?", "", "16bits","32bits","160bits","16bits","1bits");
        final QuestionList question2 = new QuestionList("¿cual es el valor de una variable boolean?", "", "Verdadero","Verdadero o falso","Falso","Verdadero o falso","nulo");
        final QuestionList question3 = new QuestionList("¿Cual es el tamaño de una variable int?", "", "16bits","32bits","160bits","16bits","1bits");
        final QuestionList question4 = new QuestionList("¿cual es el valor de una variable boolean?", "", "Verdadero","Verdadero o falso","Falso","Verdadero o falso","nulo");
        final QuestionList question5 = new QuestionList("¿Cual es el tamaño de una variable int?", "", "16bits","32bits","160bits","16bits","1bits");
        final QuestionList question6 = new QuestionList("¿cual es el valor de una variable boolean?", "", "Verdadero","Verdadero o falso","Falso","Verdadero o falso","nulo");

        //agregar todas las preguntas a la List<QuestionList>

        questionLists.add(question1);
        questionLists.add(question2);
        questionLists.add(question3);
        questionLists.add(question4);
        questionLists.add(question5);
        questionLists.add(question6);

        return questionLists;
    }

    public static List<QuestionList> getQuestions(String seleccionNombreTema){
        switch (seleccionNombreTema){
            case "java":
                return javaQuestions();
            case "php":
                return phpQuestions();
            case "android":
                return androidQuestions();
            default:
                return htmlQuestions();
        }
    }
}
