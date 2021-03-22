package com.pluralsight.speldemo;

import com.pluralsight.speldemo.data.User;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class AppExpressionParser {

    public static void main(String[] args) {
        SpelExpressionParser parser = new SpelExpressionParser();

        System.out.println("\n########################################################\n");

        Expression exp1 = parser.parseExpression("'Hello World'");
        String message1 = (String) exp1.getValue();
        System.out.println(message1); // Prints Hello World


        Expression exp2 = parser.parseExpression("'Hello World'.length()");
        int message2 = (int) exp2.getValue();
        System.out.println(message2); // 11


        Expression exp3 = parser.parseExpression("'Hello World'.length() * 10");
        int message3 = (int) exp3.getValue();
        System.out.println(message3); // Prints 110


        Expression exp4 = parser.parseExpression("'Hello World'.length() > 10");
        boolean message4 = (boolean) exp4.getValue();
        System.out.println(message4); // Prints true


        Expression exp5 = parser.parseExpression("'Hello World'.length() > 10 and 'Hello World'.length() < 20");
        boolean message5 = (boolean) exp5.getValue();
        System.out.println(message5); // Prints true

        System.out.println("\n########################################################\n");

        StandardEvaluationContext ec1 = new StandardEvaluationContext();
        ec1.setVariable("greeting", "Hello USA");
        String msg = (String) parser.parseExpression("#greeting.substring(6)").getValue(ec1);
        System.out.println(msg); // Prints USA


        StandardEvaluationContext ec2 = new StandardEvaluationContext();
        ec2.setVariable("greeting", "Hello UK");
        String msg2 = (String) parser.parseExpression("#greeting.substring(6)").getValue(ec2);
        System.out.println(msg2); // Prints UK

        System.out.println("\n########################################################\n");

        User user = new User();
        StandardEvaluationContext userContext = new StandardEvaluationContext(user);
        parser.parseExpression("country").setValue(userContext, "USA"); // Sets field country of user to USA
        System.out.println(user.getCountry()); // Prints USA

        parser.parseExpression("timeZone").setValue(userContext, "Europe/Berlin"); // Sets field timeZone of user to Europe/Berlin
        System.out.println(user.getTimeZone()); // Prints Europe/Berlin

        parser.parseExpression("language").setValue(userContext, "DE"); // Sets field language of user to DE
        System.out.println(user.getLanguage()); // Prints DE

        parser.parseExpression("age").setValue(userContext, 19); // Sets field age of user to 19
        System.out.println(user.getAge()); // Prints Europe/Berlin

        parser.parseExpression("name").setValue(userContext, "Cedric"); // Sets field name of user to Cedric
        System.out.println(user.getName()); // Prints Cedric

        System.out.println("\n########################################################\n");
        System.out.println("Fetched from system properties\n");

        StandardEvaluationContext propsContext = new StandardEvaluationContext();
        propsContext.setVariable("systemProperties", System.getProperties());

        Expression expCountry = parser.parseExpression("#systemProperties['user.country']");
        parser.parseExpression("country").setValue(userContext, expCountry.getValue(propsContext));
        System.out.println(user.getCountry());

        Expression expLang = parser.parseExpression("#systemProperties['user.language']");
        parser.parseExpression("language").setValue(userContext, expLang.getValue(propsContext));
        System.out.println(user.getLanguage());

        Expression expTz = parser.parseExpression("#systemProperties['user.timezone']");
        parser.parseExpression("timeZone").setValue(userContext, expTz.getValue(propsContext));
        System.out.println(user.getTimeZone());

    }

}
