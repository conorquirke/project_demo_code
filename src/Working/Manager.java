/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Working;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author conor
 */
public class Manager {
    private static StorageManager stMan;
    private Question[] questions=new Question[500];
    private int numQuestions;
    private int level;
    private Question currentQuestion;
    private int lives;
    
    public static int getRandom(int iMin, int iMax){
        int iRand=0;
        iRand=(int)Math.round(Math.random()*(iMax-iMin))+iMin;
        return iRand;
    }
    
    public Manager(String fileName) throws ClassNotFoundException, SQLException{
        stMan = new StorageManager (fileName);
        level =1;
        lives =3;
        populateQuestions();
    
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
    

    public static StorageManager getStMan() {
        return stMan;
    }
    public void addQuestion(String question, int questionID) throws SQLException{
        questions[numQuestions]=new Question(question, questionID);
        numQuestions++;
    
    }
    
    public void populateQuestions() throws SQLException{
        numQuestions=0;
        String SQL="SELECT QuestionID, Question, FROM Questions WHERE level="+level;
        ResultSet result=stMan.query(SQL);
        while(result.next()){
            int questionID=result.getInt("QuestionID");
            String question=result.getString("Question");
            addQuestion(question,questionID);
        
        }
    }
    public void increaseLevel() throws SQLException{
        
        level++;
        populateQuestions();
          
    }   
    public String getquestion(){
        currentQuestion=questions[getRandom(0, numQuestions-1)];
        return currentQuestion.toString();
    }
    public boolean answer(char answer){
        if(currentQuestion.isCorrect(answer)){
            level++;
            return true;
            
        }else{
            lives--;
            return false;
        }
        
        }
        
    }
   
    
    
    


