/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Working;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author conor
 */
public class Question {
    private String question;
    private int questionID;
    private Answer[] answers = new Answer[4];
    private int numAnswers=0;

    public Question(String question, int questionID) throws SQLException {
        this.question = question;
        this.questionID =questionID;
        String SQL="SELECT Answer,Correct FROM Answers WHERE questionFK ="+this.questionID;   
        ResultSet result = Manager.getStMan().query(SQL);
        while(result.next()){
            String answer=result.getString("Answewr");
            boolean correct=result.getBoolean("Correct");
            answers[numAnswers] =new Answer (answer, correct);
            numAnswers++;
        
        }
    }

    @Override
    public String toString() {
        String display="";
        String letters="ABCD";
        for (int i =0; i <numAnswers; i++){
            display+=answers[i].toString(letters.charAt(i))+"\n";
        
    }
        return question+"\n\n"+display;
    
    
    }
    public boolean isCorrect(char letter){
        String letters="ABCD";
        int pos=letters.indexOf(letters+"");
        return answers[pos].isCorrect();
    
    }

}    
    
 
