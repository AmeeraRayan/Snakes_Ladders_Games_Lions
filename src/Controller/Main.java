package Controller;

import java.util.ArrayList;

import Model.Questions;
import Model.SysData;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SysData instance = SysData.getInstance();
		instance.LoadQuestions();
		ArrayList<Questions> QuestionsFromSysData = instance.getQuestions();
		System.out.println(QuestionsFromSysData.size());
		
		String[] answers = {"1" , "2" , "3" , "4"};
		Questions newOne = new Questions("question", answers, 1, QuestionsFromSysData.size(), 1);
		System.out.println( newOne.getOptions()[1]);
	     instance.addNewQuestion(newOne);
	    System.out.println(QuestionsFromSysData.size());
		instance.removeQuestionLocaly(1);
		System.out.println(QuestionsFromSysData.size());



	}

}
