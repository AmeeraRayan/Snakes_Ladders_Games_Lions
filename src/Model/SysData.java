package Model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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



public class SysData {
	private static SysData instance = null;
	private ArrayList<Questions> questions = new ArrayList<Questions>();
	private Map<String, String> adminCredentials;

    public SysData() {
        adminCredentials = new HashMap<>();
        adminCredentials.put("admin1", "123");
    }

	
	//  Singleton Instance
	public static SysData getInstance() {
		if (instance == null) {
			instance = new SysData();
		}
		return instance;
	}
	

	public ArrayList<Questions> getQuestions() { //return all the questions 
		return questions;
	}


	public void LoadQuestions() { // get all the question from json file 

		ArrayList<Questions> questions = new ArrayList<Questions>();
		this.getQuestions().clear();

		Gson gson = new Gson();
		JsonReader reader = null;
		try {
			reader = new JsonReader(new FileReader("QuestionsAndAnswers.json"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		@SuppressWarnings("deprecation")
		JsonObject jsonObject = new JsonParser().parse(reader).getAsJsonObject();

		final JsonArray data = jsonObject.getAsJsonArray("questions");

		for (JsonElement element : data) {

			Questions q;

			String questionText = ((JsonObject) element).get("question").getAsString();

			JsonArray answersArray = (((JsonObject) element).getAsJsonArray("answers"));
			@SuppressWarnings("unchecked")
			ArrayList<String> answers = gson.fromJson(answersArray, ArrayList.class);
			
			Integer correct = ((JsonObject) element).get("correct_ans").getAsInt();
			Integer diffculty = ((JsonObject) element).get("difficulty").getAsInt();
             
			 String[] answerArray = new String[answers.size()];
		        answers.toArray(answerArray);
	
			
			if (!this.getQuestions().isEmpty()) {
			
				q = new Questions(questionText,answerArray, correct, diffculty, this.getQuestions().size());
				this.getQuestions().add(q);

			} else {
				q = new Questions(questionText,answerArray, correct, diffculty,0);
				this.getQuestions().add(q);

			}

			questions.add(q);

		}

		this.getQuestions();
	
	}
	public boolean saveQuestions(List<Questions> questions) {
	    // Write JSON file
	    try (FileWriter file = new FileWriter("QuestionsAndAnswers.json")) {
	        JsonArray questionsArray = new JsonArray();

	        for (Questions question : questions) {
	            JsonObject questionObject = new JsonObject();

	            // Question text
	            questionObject.addProperty("question", question.getQuestionText());

	            // Answers array
	            JsonArray answersArray = new JsonArray();
	            for (int i = 0; i < question.getOptions().length; i++) {
	                answersArray.add(new JsonPrimitive(question.getOptions()[i]));
	            }
	            questionObject.add("answers", answersArray);

	            // Correct answer index
	            questionObject.addProperty("correct_ans", question.getCorrectOption());

	            // diffculty level
	            questionObject.addProperty("difficulty", question.getDiffculty());

	            questionsArray.add(questionObject);
	        }

	        JsonObject json = new JsonObject();
	        json.add("questions", questionsArray);

	        file.write(json.toString());
	        file.flush();
	        return true;
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	 public void writeQuestionsToJsonFile() {
	        JsonArray questionsArray = new JsonArray();

	        for (Questions q : getQuestions()) {
	            JsonObject questionObject = new JsonObject();

	            // Question text
	            questionObject.addProperty("question", q.getQuestionText());

	            // Answers array
	            JsonArray answersArray = new JsonArray();
	            for (int i = 0; i < q.getOptions().length; i++) {
	                JsonObject answerObject = new JsonObject();
	                answerObject.addProperty(String.valueOf(i + 1), q.getOptions().length);
	                answersArray.add(answerObject);
	            }
	            questionObject.add("answers", answersArray);

	            // Correct answer index
	            questionObject.addProperty("correct_ans", String.valueOf(q.getCorrectOption()));

	            // diffculty level
	            questionObject.addProperty("difficulty", String.valueOf(q.getDiffculty()));

	            questionsArray.add(questionObject);
	        }

	        JsonObject root = new JsonObject();
	        root.add("questions", questionsArray);

	        // Write to file
	        try (Writer w = new FileWriter("QuestionsAndAnswers.json")) {
	        	Gson gson=new Gson();
	            gson.toJson(root, w);
	            System.out.println("Success");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

}
