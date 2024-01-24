package Model;


public class Questions {
	    private String questionText;
	    private String[] options;
	    private int correctOption;
		private Integer id;
		private Integer diffcultyLevel;  // level 3 hard

        
	    public Questions(String questionText, String[] options, int correctOption, int id , int diffcultyLevel) {
	        this.questionText = questionText;
	        this.options = options;
	        this.correctOption = correctOption;
	        this.id=id;
	        this.diffcultyLevel = diffcultyLevel ; 
	    }

		public String getQuestionText() {
			return questionText;
		}

		public void setQuestionText(String questionText) {
			this.questionText = questionText;
		}

		public String[] getOptions() {
			return options;
		}

		public void setOptions(String[] options) {
			this.options = options;
		}

		public int getCorrectOption() {
			return correctOption;
		}

		public void setCorrectOption(int correctOption) {
			this.correctOption = correctOption;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getid() {
			return id;
		}
		public void setDiffculty(int diffcultyLevel) {
			this.diffcultyLevel= diffcultyLevel;
		}
		public int getDiffculty() {
			return diffcultyLevel;
		}
	 
	 

	}


