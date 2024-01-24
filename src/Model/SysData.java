package Model;

import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;



public class SysData {
	private static SysData instance = null;
	private ArrayList<Questions> questions = new ArrayList<Questions>();

	
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
			reader = new JsonReader(new FileReader("src/QuestionsAndAnswers.json"));
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
			Integer difficulty = ((JsonObject) element).get("level").getAsInt();
             
			 String[] answerArray = new String[answers.size()];
		        answers.toArray(answerArray);
	
			
			if (!this.getQuestions().isEmpty()) {
			
				q = new Questions(questionText,answerArray, correct, difficulty, this.getQuestions().size());
				this.getQuestions().add(q);

			} else {
				q = new Questions(questionText,answerArray, correct, difficulty,0);
				this.getQuestions().add(q);

			}

			questions.add(q);

		}

		this.getQuestions();
	
	}
	
	public void addNewQuestion(Questions q) { // add question to the arrayList of questions and call function that write the question to Json file 

		if (q != null) {
			int size = getQuestions().size();
			q.setId(size);
			this.getQuestions().add(q);
			WriteQuestionsToJsonFile();
		}

	}
	
	public void WriteQuestionsToJsonFile() { // write the questions to Json file 

		JsonArray questions = new JsonArray();
		for (Questions q : this.getQuestions()) {
			JsonObject question = new JsonObject();
			JsonArray answerArray = new JsonArray();
			for(String answer : q.getOptions()) {
				answerArray.add(answer);
			}
			Integer correct = q.getCorrectOption();
			Integer difficulty = q.getDiffculty();
			question.addProperty("question", q.getQuestionText());
			question.add("answers", answerArray);
			question.addProperty("correct_ans", String.valueOf(correct));
			question.addProperty("level", String.valueOf(difficulty));
			questions.add(question);
		}

		JsonObject root = new JsonObject();
		root.add("questions", questions);

		// write to file

		try {
			Writer w = new FileWriter("src/QuestionsAndAnswers.json");
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			gson.toJson(root, w);
			w.flush();
			w.close();
			System.out.println("Success");
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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
		
		WriteQuestionsToJsonFile();
		
	}
	
	

}
