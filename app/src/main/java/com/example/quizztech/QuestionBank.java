package com.example.quizztech;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuestionBank {

    private static List<QuestionList> javaQuestions;
    private static List<QuestionList> phpQuestions;
    private static List<QuestionList> androidQuestions;
    private static List<QuestionList> htmlQuestions;

    // Cargar preguntas de Java
    private static List<QuestionList> javaQuestions() {
        List<QuestionList> questionLists = new ArrayList<>();

        questionLists.add(new QuestionList("¿Cuál es el tamaño de una variable int?", "", "16bits", "32bits", "160bits", "1bits", "32bits"));
        questionLists.add(new QuestionList("¿Cuál es el valor de una variable boolean?", "", "Verdadero", "Verdadero o falso", "Falso", "Ninguno", "Verdadero o falso"));
        questionLists.add(new QuestionList("¿En qué año se creó Java?", "", "1978", "1990", "1890", "1995", "1995"));
        questionLists.add(new QuestionList("¿Existen más de 2 tipos de Intent?", "", "Nulo", "Falso", "Verdadero", "No existe", "Verdadero"));
        questionLists.add(new QuestionList("¿Qué es Java?", "", "Lenguaje de programación", "Un programa", "Un juego", "Un sistema operativo", "Lenguaje de programación"));
        questionLists.add(new QuestionList("¿Cuántas clases hay en Java?", "", "2", "1", "4", "3", "4"));

        return questionLists;
    }

    // Cargar preguntas de PHP
    private static List<QuestionList> cargarPhpQuestions() {
        List<QuestionList> questionLists = new ArrayList<>();

        questionLists.add(new QuestionList("¿Qué significa PHP?", "", "Personal Home Page", "PHP: Hypertext Preprocessor", "Pre Hypertext Processor", "Página personal de inicio", "PHP: Hypertext Preprocessor"));
        questionLists.add(new QuestionList("¿PHP es un lenguaje de?", "", "Programación", "Scripting del lado del servidor", "Marcado", "Estilo", "Scripting del lado del servidor"));
        questionLists.add(new QuestionList("¿Qué símbolo se usa para iniciar una variable en PHP?", "", "#", "$", "&", "@", "$"));
        questionLists.add(new QuestionList("¿Cuál de las siguientes es una superglobal en PHP?", "", "$_FILES", "$POST", "$GLOBALS", "$_SERVER", "$_SERVER"));
        questionLists.add(new QuestionList("¿Qué extensión debe tener un archivo PHP?", "", ".html", ".xml", ".php", ".js", ".php"));
        questionLists.add(new QuestionList("¿PHP puede interactuar con bases de datos?", "", "Sí", "No", "Solo MySQL", "Solo Oracle", "Sí"));

        return questionLists;
    }

    // Cargar preguntas de HTML
    private static List<QuestionList> cargarHtmlQuestions() {
        List<QuestionList> questionLists = new ArrayList<>();

        questionLists.add(new QuestionList("¿Qué significa HTML?", "", "Hyper Trainer Marking Language", "Hyper Text Markup Language", "Hyper Text Marketing Language", "High Text Markup Language", "Hyper Text Markup Language"));
        questionLists.add(new QuestionList("¿Qué etiqueta se usa para un salto de línea en HTML?", "", "<br>", "<bl>", "<lb>", "<ln>", "<br>"));
        questionLists.add(new QuestionList("¿Cuál es la etiqueta correcta para una imagen?", "", "<img src=\"image.jpg\">", "<image src=\"image.jpg\">", "<img href=\"image.jpg\">", "<picture>", "<img src=\"image.jpg\">"));
        questionLists.add(new QuestionList("¿Cómo creas un enlace en HTML?", "", "<a href=\"url\">", "<link>", "<href>", "<a>", "<a href=\"url\">"));
        questionLists.add(new QuestionList("¿Cuál es el propósito del atributo 'alt' en la etiqueta <img>?", "", "Texto alternativo", "Tamaño", "Color", "Fuente", "Texto alternativo"));
        questionLists.add(new QuestionList("¿Cómo puedes aplicar negrita al texto?", "", "<b>", "<bold>", "<strong>", "<n>", "<b>"));


        return questionLists;
    }

    // Cargar preguntas de Android
    private static List<QuestionList> cargarAndroidQuestions() {
        List<QuestionList> questionLists = new ArrayList<>();

        questionLists.add(new QuestionList("¿Qué es Android?", "", "Sistema operativo", "Lenguaje de programación", "Aplicación", "Navegador", "Sistema operativo"));
        questionLists.add(new QuestionList("¿Quién desarrolló Android?", "", "Apple", "Microsoft", "Google", "IBM", "Google"));
        questionLists.add(new QuestionList("¿Qué lenguaje se usa principalmente para el desarrollo de Android?", "", "C++", "Java", "Python", "Swift", "Java"));
        questionLists.add(new QuestionList("¿Qué archivo contiene el manifiesto de la aplicación?", "", "main.xml", "AndroidManifest.xml", "app.xml", "manifest.xml", "AndroidManifest.xml"));
        questionLists.add(new QuestionList("¿Qué es una Activity en Android?", "", "Una vista", "Un componente de UI", "Una clase", "Un controlador", "Una clase"));
        questionLists.add(new QuestionList("¿Cuál es la última versión de Android?", "", "Oreo", "Pie", "Lollipop", "Depende de la fecha", "Depende de la fecha"));

        return questionLists;
    }

    // Método para obtener la lista de preguntas según el tema
    static List<QuestionList> getQuestions(String seleccionNombreTema) {
        switch (seleccionNombreTema.toLowerCase()) {
            case "java":
                return javaQuestions;
            case "php":
                return phpQuestions;
            case "android":
                return androidQuestions;
            default:
                return htmlQuestions;
        }
    }
}
