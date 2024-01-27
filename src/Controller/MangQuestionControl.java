package Controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import Model.Questions;
import Model.SysData;



public class MangQuestionControl {
	private static MangQuestionControl instance = null;
	private ArrayList<Questions> questions = new ArrayList<Questions>();
	private Map<String, String> adminCredentials;
	public static SysData sysData= new SysData();


	
	//  Singleton Instance
	public static MangQuestionControl getInstance() {
		if (instance == null) {
			instance = new MangQuestionControl();
		}
		return instance;
	}
	

	public ArrayList<Questions> getQuestions() { //return all the questions 
		sysData.LoadQuestions();
		return sysData.getQuestions();
	}



	public void addNewQuestion(Questions q) { // add question to the arrayList of questions and call function that write the question to Json file 

		if (q != null) {
			int size = getQuestions().size();
			q.setId(size);
			this.getQuestions().add(q);
			sysData.saveQuestions(questions);
		}

	}
	
	
	public void removeQuestionLocaly(Integer QuestionId) {
		int i = -1;
		int index = 0;

		for (Questions q : this.getQuestions()) {

			if (q.getid() == QuestionId) {

				i = index;
				break;
			}

			index++;
		}

		if (i == -1) {
			return;
		}
		//update the id of all question -  because of the removing. 
	
		if (i != -1) {
			this.questions.remove(i);
			for (int c = i + 1; c < this.getQuestions().size(); c++) {
				this.getQuestions().get(c).setId(QuestionId);
				QuestionId++;

			}
		}
		
		sysData.writeQuestionsToJsonFile();
		
	}
	
	public void editQuestion(int questionId, Questions updatedQuestion) {
	    // Get the list of questions
	    List<Questions> questions = getQuestions();
	    System.out.println(questions.isEmpty());
	    for (Questions question : questions) {
	        if (question.getid() == questionId) {
	            System.out.println("Original Question: " + question.toString());

	            // Update the question with new values
	            question.setQuestionText(updatedQuestion.getQuestionText());
	            question.setOptions(updatedQuestion.getOptions());
	            question.setCorrectOption(updatedQuestion.getCorrectOption());
	            question.setDiffculty(updatedQuestion.getDiffculty());

	            // Write the updated questions to JSON
	            updateQuestionToJson(questions);
	            if (sysData.saveQuestions(questions)) {
	                System.out.println("Question Updated: " + question.toString());
	            } else {
	                System.out.println("Failed to save updated questions.");
	            }

	            return;
	        }
	    }

	    System.out.println("Question with ID " + questionId + " not found.");
	}
	
	private void updateQuestionToJson(List<Questions> questions) {
	    try (Reader reader = new FileReader("QuestionsAndAnswers.json")) {
	        JsonParser parser = new JsonParser();
	        JsonObject existingJson = parser.parse(reader).getAsJsonObject();


	        // Update only the "questions" part of the existing JSON
	        existingJson.remove("questions");

	        JsonArray questionsJsonArray = new JsonArray();

	        for (Questions question : questions) {
	            JsonObject questionJsonObject = new JsonObject();
	            questionJsonObject.addProperty("question", question.getQuestionText());

	            JsonArray answersArray = new JsonArray();
	            for (String answer : question.getOptions()) {
	                answersArray.add(answer);
	            }
	            questionJsonObject.add("answers", answersArray);

	            Integer correct = question.getCorrectOption() + 1;
	            Integer difficulty = question.getDiffculty();

	            questionJsonObject.addProperty("correct_ans", correct.toString());
	            questionJsonObject.addProperty("difficulty", difficulty.toString() );


	            questionsJsonArray.add(questionJsonObject);
	        }

	        existingJson.add("questions", questionsJsonArray);

	        // Write the updated JSON back to the file with proper indentation
	        try (Writer writer = new FileWriter("QuestionsAndAnswers.json")) {
	            JsonWriter jsonWriter = new JsonWriter(writer);
	            jsonWriter.setIndent(" "); // Set the desired indentation
	            new Gson().toJson(existingJson, jsonWriter);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	    
	public boolean validateAdminCredentials(String userName, String password) {
        String storedPassword = adminCredentials.get(userName);
        return storedPassword != null && storedPassword.equals(password);
    }

	

}
